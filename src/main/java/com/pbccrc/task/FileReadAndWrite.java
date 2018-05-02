package com.pbccrc.task;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pbccrc.common.Constants;
import com.pbccrc.po.CarPO;
import com.pbccrc.po.HousePO;
import com.pbccrc.po.UserPO;
import com.pbccrc.service.*;
import com.pbccrc.util.DateUtils;
import com.pbccrc.util.FileUtils;
import com.pbccrc.util.MQManageUtils;

@Component
public class FileReadAndWrite {
	private Logger logger=LoggerFactory.getLogger(FileReadAndWrite.class);
	@Value("${file.local.splitFilePath}")
	private String targetPath;
	@Autowired
	private Environment ev;
	@Autowired 
	private UserBusiService userBusiService;
	@Autowired 
	private HouseBusiService houseBusiService;
	@Autowired 
	private CarBusiService carBusiService;
	
	/**
	 * 读取文件内容，将内容存入消息队列
	 * @param targetFile
	 */
	public void productMQ(File targetFile) {
		FileUtils.readSourceFile(targetFile);
	}
	
	@SuppressWarnings("unchecked")
	public void concumeMQ() {
		BlockingQueue<Object> mq=MQManageUtils.getBlockingQueue();
		ExecutorService pool=Executors.newFixedThreadPool(5);
		while(!mq.isEmpty()) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					Map<String, JSONArray> mapList=null;
					try {
						mapList = (Map<String,JSONArray>)mq.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Iterator<Entry<String, JSONArray>> iterator= mapList.entrySet().iterator();
					Map.Entry<String, JSONArray> entry=null;
					while(iterator.hasNext()) {
						entry=(Map.Entry<String, JSONArray> )iterator.next();
						String filePath=targetPath+"\\"+DateUtils.getStrOfToday();
						String fileName=createFileName(entry.getKey(),"SourceFile"); 
						File file=FileUtils.getNewOrOrigFile(filePath,fileName);
						writeTargetFile(entry,file);
					}
				}
			});
		}
		
	}

	public void writeTargetFile(Map.Entry<String, JSONArray> entry,File file ) {
		String key=entry.getKey();
		String columns=ev.getProperty(key);
		JSONArray list=entry.getValue();
		String[] splits=columns.split(",");
		StringBuffer sf=null;
		if(list!=null&&list.size()>0) {
			JSONObject obj=null;
			for(int i=0;i<list.size();i++) {
				//读取一定行数进行输出
				if(i%Constants.WRITE_LINES==0) {
					//将字符串写入到文件中
					if(sf!=null&&sf.length()>0) {
						FileUtils.writeFile(file, sf.toString());
					}
					sf=new StringBuffer();
				}
				if(list.getJSONObject(i) instanceof JSONObject){
					obj=list.getJSONObject(i);
				}
				//暂时不查询数据库
//				String content= queryAndProcData(key,obj);
//				if(content!=null&&content.length()>0) {
//					System.out.println("content++++++++++++++:"+content);
//				}
				
				for(String name:splits) {
					sf.append(obj.getString(name)).append(",");
				}
				sf.deleteCharAt(sf.length()-1);
				sf.append("\r\n");
			}
			//将剩余字符写入文件
			if(sf!=null&&sf.length()>0) {
				FileUtils.writeFile(file, sf.toString());
			}
		}
	}
	
	/**
	 * 查找正式表中是否存在数据，如果存在删除该数据
	 * @param key
	 * @param obj
	 * @return
	 */
	private String queryAndProcData(String key,JSONObject obj) {
		String retStr=null;
		if("userList".equals(key)) {
			Long userID=Long.valueOf(obj.getString("userID"));
			UserPO po=userBusiService.selectUserByID(userID);
			if(po!=null&&po.getUserId()>0) {
				userBusiService.deleteByUserID(userID);
				retStr=po.toString();
			}
		}else if("houseList".equals(key)){
			Long houseID=Long.valueOf(obj.getString("houseID"));
			HousePO po=houseBusiService.selectHouseByID(houseID);
			if(po!=null&&po.getHouseId()>0) {
				houseBusiService.deleteHouseByID(houseID);
				retStr=po.toString();
			}
		}else if("carList".equals(key)) {
			Long carID=Long.valueOf(obj.getString("carID"));
			CarPO po=carBusiService.selectCarByID(carID);
			if(po!=null&&po.getCarId()>0) {
				carBusiService.deleteCarByID(carID);
				retStr=po.toString();
			}
		}
		
		return retStr;
	}
	
	
	/**
	 * 将list按照一定的格式转换为字符串
	 * @param list
	 * @return
	 */
	public String listToString(String formatStr,List<JSONObject> list) {
		//根据格式获取输出列
		String columns="";
		String[] splits=columns.split(",");
		StringBuffer sf=new StringBuffer();
		StringBuffer tempSF=null;
		for(JSONObject obj:list) {
			tempSF=new StringBuffer();
			for(int i=0;i<splits.length;i++) {
				tempSF.append(obj.getString(splits[i])).append(",");
			}
			String record=tempSF.toString();
			sf.append(record.substring(0,record.length()-1)).append("\\n"); //每行后面换行
		}
		return sf.toString();
	}
	
	public  String createFileName(String key,String sourceFileName) {
		StringBuffer filename=new StringBuffer();
		filename.append(sourceFileName.substring(0, sourceFileName.length()-4));
		filename.append("-");
		filename.append(key.substring(0, key.length()-5));
		filename.append(".csv");
		return filename.toString();
	}
	
}



package com.pbccrc.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pbccrc.common.Constants;

/**
 * 文件处理工具类
 * @author mchunguang
 *
 */
public class FileUtils {
	/**
	 * 读取文件内容数据
	 * @param file
	 * @param start 开始读取行
	 * @param end   结束读取行
	 * @return
	 */
	public static String readFile(File file,Long start,Long end) {
		if(start==null){
			start=0L;
		}
		if(end==null){
			end=Long.MAX_VALUE;
		}
		StringBuffer sf=new StringBuffer();
		try {
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String line;
			while((line=br.readLine())!=null){
				sf.append(line);
				line=null;
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sf.toString();
		
	}
	/**
	 * 将字符串写入文件
	 * @param file
	 * @param str
	 */
	public static void writeFile(File file,String str) {
		FileWriter fw=null;
		BufferedWriter out=null;
		try {
			//如果文件存在需要追加文本内容
			fw = new FileWriter(file,true);
			out = new BufferedWriter(fw);
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取文件，如果文件不存在则创建
	 * @param filePath
	 * @return
	 */
	public static File getNewOrOrigFile(String filePath,String fileName) {
		File dir =new File(filePath);
		File file=new File(filePath+"\\"+fileName);
		try {
			if(!file.exists()) {
				if(!dir.exists()) {
					dir.mkdirs();
				}
				if(!file.exists()) {
					file.createNewFile();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file ;
	}
	
	/**
	 * 读取目标文件，将记录集存储到map中
	 * @param file
	 * @return 返回集合
	 */
	public static void readSourceFile(File targetFile) {
		BlockingQueue<Object> bq=MQManageUtils.getBlockingQueue();
		Map<String,JSONArray> mapList=null;
		 try {
//			FileReader in=new FileReader(targetFile);
			FileInputStream fs=new FileInputStream(targetFile);
			BufferedInputStream bs=new BufferedInputStream(fs,5*1024*1024);
			InputStreamReader is=new InputStreamReader(bs,Constants.CHARACTER_ENCODING_IN);
			BufferedReader bufin=new BufferedReader(is,5*1024*1024);
			String lineStr;
			JSONArray userList=new JSONArray();
			JSONArray houseList=new JSONArray();
			JSONArray carList=new JSONArray();
			mapList=new HashMap<String,JSONArray>();
			while((lineStr=bufin.readLine())!=null) {
				JSONObject userObj=JSON.parseObject(lineStr);
				userList.add(userObj);
				houseList.addAll(userObj.getJSONArray("houses"));
				carList.addAll(userObj.getJSONArray("cars"));
			}
			mapList.put("user_List", userList);
			mapList.put("house_List", houseList);
			mapList.put("car_List", carList);
			try {
				bq.put(mapList);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void convert(String str){
		JSONObject obj=JSON.parseObject(str);
		Iterator it= obj.keySet().iterator();
		while(it.hasNext()){
			Map.Entry entry=(Map.Entry)it.next();
		}
	}
	
	public static File[] readFilePath(String filePath) {
		File[] files=null;
//		File file=new File(filePath+File.separator+DateUtils.getStrOfToday());
		File file=new File(filePath);
		if(file.exists()&&file.isDirectory()) {
			files=file.listFiles();
		}
		return files;
	} 
}





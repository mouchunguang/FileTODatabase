package com.pbccrc.task;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class ParseFileTask implements Callable<Boolean> {
	private Logger log=LoggerFactory.getLogger(ParseFileTask.class);
	private CountDownLatch cdl;
	public ParseFileTask(CountDownLatch cdl) {
		this.cdl=cdl;
	}
	@Override
	public Boolean call() throws Exception {
		Boolean retValue=true;
		try {
			
		}catch(Exception ex){
			retValue=false;
			ex.printStackTrace();
		}finally {
			cdl.countDown();
		}
		return retValue;
	}
	
	/**
	 * 查询数据并做相应处理
	 * 1.根据ID查询数据在主表中是否存在
	 * 2.若存在，将数据写入历史文件，删除数据库中的数据
	 * @param key 可以判断表的字符串
	 * @param o	  记录对象
	 * @return   数据库中是否存在该数据
	 */
	private boolean queryProcData(String key,JSONObject o) {
		boolean isExist=false;
		
		return isExist;
	}
	
	private String createNFileName(String key,String sourceFileName) {
		String fileName=null;
		
		return fileName;
	}
}

package com.pbccrc.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MQManageUtils {
	private MQManageUtils(){}
	
	static class MQManageUtilsHolder{
		 private static BlockingQueue<Object> bq=new LinkedBlockingQueue<>();
	}
	
	public static BlockingQueue<Object> getBlockingQueue() {
		return MQManageUtilsHolder.bq;
	}
}

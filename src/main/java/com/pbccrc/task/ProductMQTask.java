package com.pbccrc.task;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pbccrc.util.FileUtils;

public class ProductMQTask implements Runnable {
	private Logger logger=LoggerFactory.getLogger(ProductMQTask.class);
	private File targetFile;
//	private CountDownLatch cl;
//	ProductMQTask(File targetFile,CountDownLatch cl){
//		this.targetFile=targetFile;
//		this.cl=cl;
//	}
	ProductMQTask(File targetFile){
		this.targetFile=targetFile;
	}
	@Override
	public void run() {
		logger.info("解析文件执行的线程："+Thread.currentThread().getName());
		FileUtils.readSourceFile(targetFile);
	}
	
}

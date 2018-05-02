package com.pbccrc.task;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbccrc.common.ThreadPoolManage;
import com.pbccrc.util.LocalFileUtils;

@Controller("/FileSplitJob")
public class FileSplitJob {
	private Logger log=LoggerFactory.getLogger(FileSplitJob.class);
	@Value("${file.achiveFilesPath}")
	private String sourceFilePath;
	
	@RequestMapping("/startSplit")
	public void startJob() {
		File[] files=LocalFileUtils.getFilesByPath(sourceFilePath);
		if(files==null||files.length<1) {
			log.info("待拆分文件不存在,请联系档案增量文件提供者！");
			return;
		}
		CountDownLatch cdl=new CountDownLatch(files.length);
		ThreadPoolManage tpmanage=new ThreadPoolManage(2,10,100,60000);
		ThreadPoolTaskExecutor executor= tpmanage.getThreadPool();
		for(File file: files) {
//			executor.submit(task);
		}
	}
}

package com.pbccrc.task;

import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.pbccrc.service.CarBusiService;
import com.pbccrc.service.HouseBusiService;
import com.pbccrc.service.UserBusiService;
import com.pbccrc.util.FileUtils;
import com.pbccrc.util.MQManageUtils;

@Controller
//@Component
public class SplitFilesTask {
	private final int threadNum=2;
	private final int consumeThreadNum=6;
	@Value("${file.achiveFilesPath}")
	private String dirPath;
	@Autowired
	private FileReadAndWrite fileReadAndWrite;
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
	 * 每十分钟执行一次拆分文件任务
	 */
	@RequestMapping("/parseFileToMQ")
//	@Scheduled(cron="${task.start.time}")
	private void parseFileToMQ() {
		BlockingQueue<Object> bq=MQManageUtils.getBlockingQueue();
		System.out.println("开始解析文件！");
		System.out.println("队列值大小为："+bq.size());
		final File[] files=FileUtils.readFilePath(dirPath);
		ExecutorService exec=Executors.newFixedThreadPool(threadNum);
//		CountDownLatch cl=new CountDownLatch(files.length);
		for(File file:files) {
			exec.execute(new ProductMQTask(file));
		}
//		try {
//			cl.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("解析文件完毕！");
		System.out.println("队列值大小为："+bq.size());
	}
	
	/**
	 * 循环执行文件写入任务
	 */
	@RequestMapping("/consumeMQToFile")
//	@Scheduled(cron="${task.start.time}")
	private void consumeMQToFile() {
		ExecutorService execs= Executors.newFixedThreadPool(consumeThreadNum);
		BlockingQueue<Object> mq = MQManageUtils.getBlockingQueue();
		while(true) { 
			if(!mq.isEmpty()) {
				Map<String, JSONArray> mapList=null;
				try {
					mapList = (Map<String, JSONArray>)mq.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				execs.execute(
						new ConsumeMQTask(targetPath,ev,userBusiService,houseBusiService,carBusiService,mapList)
					);
			}
			
		}
	}
}







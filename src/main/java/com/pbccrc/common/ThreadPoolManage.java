package com.pbccrc.common;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadPoolManage {
	private int corePoolSize=5;
	private int maxPoolSize=20;
	private int queueCapacity=100;
	private int keepAliveSeconds=60000;
	
	public ThreadPoolManage() {
		
	}
	
	public ThreadPoolManage(int corePoolSize,int maxPoolSize,int queueCapacity,int keepAliveSeconds) {
		this.corePoolSize=keepAliveSeconds;
		this.maxPoolSize=maxPoolSize;
		this.queueCapacity=queueCapacity;
		this.keepAliveSeconds=keepAliveSeconds;
	}
	/*
	 * 获取线程池方法类
	 */
	public synchronized ThreadPoolTaskExecutor getThreadPool() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	     executor.setCorePoolSize(corePoolSize);
	     executor.setMaxPoolSize(maxPoolSize);
	     executor.setQueueCapacity(queueCapacity);
	     executor.setThreadNamePrefix("mqExecutor-");
	     // rejection-policy：当pool已经达到max size的时候，如何处理新任务  
	     // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行  
	     executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
	     executor.setKeepAliveSeconds(keepAliveSeconds);
	     executor.initialize();
	     return executor;
	}
}

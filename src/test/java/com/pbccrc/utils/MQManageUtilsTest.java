package com.pbccrc.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pbccrc.util.MQManageUtils;

public class MQManageUtilsTest {
	public static void main(String[] args) {
		BlockingQueue<Object> bq= MQManageUtils.getBlockingQueue();
		putAll(bq);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("bq.size():"+bq.size());
		getAll(bq);
	}
	
	public static void putAll(BlockingQueue<Object> bq) {
		ExecutorService exec= Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					for(int j=1000;j<1100;j++) {
						try {
							bq.put(Thread.currentThread().getName()+":"+j);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
	
	public static void getAll(BlockingQueue<Object> bq) {
		ExecutorService exec= Executors.newFixedThreadPool(50);
		for(int i=0;i<50;i++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					while(!bq.isEmpty()) {
						try {
							System.out.println(bq.take().toString());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
}

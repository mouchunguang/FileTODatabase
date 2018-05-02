package com.pbccrc.util;

import java.util.UUID;
/**
 * 序列生成器
 * @author mchunguang
 *
 */
public class GeneratorUtils {
	/**
	 * 获取UUID的字符串
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}

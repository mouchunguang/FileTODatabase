package com.pbccrc.util;

import java.io.File;

public class LocalFileUtils {
	public static File[] getFilesByPath(String filePath) {
		File[] files=null;
		File file=new File(filePath+File.separator+DateUtils.getStrOfToday());
		if(file.exists()&&file.isDirectory()) {
			files=file.listFiles();
		}
		return files;
	}
}

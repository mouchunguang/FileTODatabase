package com.pbccrc.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.ibatis.session.SqlSessionFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pbccrc.util.DateUtils;

public class TestConvertFormat {
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("100", "张三");
		map.put("101", "李四");
		aa(map);
		System.out.println(aa(map).get("100"));
		System.out.println(aa(map).get("101"));
	}
	
	private static Map<String, String> aa(Map<String,String> map){
		map.remove("100");
		return map;
	}
	
	public static void changeLine() {
		StringBuffer sf=new StringBuffer();
		sf.append("100001,张三,88,true,处长,习近平在出席解放军和武警部队代表团全体会议时强调\r\n");
		sf.append("100001,张三,88,true,处长,习近平在出席解放军和武警部队代表团全体会议时强调\r\n");
		System.out.print(sf);
	}
	
	
	public static void runThread() {
		final String[] strs="a,b,c,d,e,f,h,i,1,2,3,4,5,6,7,8,9,10".split(","); 
		ExecutorService exec=Executors.newFixedThreadPool(3);
		int count=0;
		while(count<strs.length-1) {
			final int num=count++; 
			exec.execute(new Runnable() {
				@Override
				public void run() {
					String str=strs[num]; 
					System.out.println("线程名称："+Thread.currentThread().getName()+"输出字符串："+str);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	
	public static void createFile() {
		File file=new File("D:\\development\\backup\\data\\temp.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("文件是否存在："+file.exists());
				System.out.println("文件名称为："+file.getName().lastIndexOf("."));
				file.delete();
//				System.out.println("文件是否存在："+file.exists());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
	
	public static void createStr() {
		String str=null;
		for (int i=0;i<10000000;i++) {
			str=("新华社北京3月12日电（记者李宣良、梅世雄）中共中央总书记、国家主席、中央军委主席习近平12日上午"
					+ "在出席十三届全国人大一次会议解放军和武警部队代表团全体会议时强调，实施军民融合发展战略是构建一体"
					+ "化国家战略体系和能力的必然选择，也是实现党在新时代的强军目标的必然选择，要加强战略引领，加强改革创新，"
					+ "加强军地协同，加强任务落实，努力开创新时代军民融合深度发展新局面，为实现中国梦强军梦提供强大动力和战略支撑。")+i;
			System.out.println(i);
		}
	}
	
	public static void convert() {
		String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27"
				+ ",\"course\":{\"courseName\":\"english\",\"code\":1270}"
				+ ",\"students\":[{\"studentName\":\"lily\",\"studentAge\":12}"
				+ ",{\"studentName\":\"lucy\",\"studentAge\":15}]}";
		JSONObject jsonObj=JSON.parseObject(COMPLEX_JSON_STR);
		System.out.println("teacherName:"+jsonObj.getString("teacherName")
		+" studentName:"+jsonObj.getString("studentName"));
		
		JSONArray students = jsonObj.getJSONArray("students");
		JSONObject tempJsonObj=null;
		for(int i=0;i<students.size();i++) {
			tempJsonObj=students.getJSONObject(i);
			System.out.println("student:"+tempJsonObj.getString("studentName"));
		}
	}
	
}

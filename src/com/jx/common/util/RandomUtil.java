package com.jx.common.util;

import java.util.Random;

import com.jx.common.config.PageData;

public class RandomUtil {

	public static void main(String[] args) {
		PageData pd = new PageData();
		pd.put("username", "zhangsan");

	}
	
	public static char randomChar() {
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
	
	
	//生成十位随机数小于4,000,000,000
	public static String inviteCode(){
		StringBuilder sb = new StringBuilder();//定义变长字符串
		Random r = new Random();
		//随机生成数字，并添加到字符串
		for(int i=0; i<10; i++){
			int a = r.nextInt(10);
			if(a==0 || (i==0 && a>3)){
				i--;
				continue;
			}else{
				sb.append(a);
			}
		}
		return String.valueOf(sb);
	}
	
}

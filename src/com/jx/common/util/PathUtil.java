package com.jx.common.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 路径工具类
 * @author
 */
public class PathUtil {

	public static void main(String[] args) {
//		System.out.println("getPicturePath---"+getPicturePath("visit", "topic"));
		System.out.println("getProjectPath---"+getProjectPath());
		System.out.println("getClassPath---"+getClassPath());
		System.out.println("PathAddress---"+PathAddress());
		
	}
	
	/**
	 * 图片访问路径
	 * @param pathType 图片类型 visit-访问；save-保存
	 * @param pathCategory 图片类别，如：话题图片-topic、话题回复图片-reply、商家图片
	 * @return
	 */
	public static String getPicturePath(String pathType, String pathCategory) {
		String strResult = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StringBuffer strBuf = new StringBuffer();
		if ("visit".equals(pathType)) {
		} else if ("save".equals(pathType)) {
			String projectPath = PublicUtil.getPorjectPath().replaceAll("\\\\", "/");
			projectPath = splitString(projectPath, "bin/");

			strBuf.append(projectPath);
			strBuf.append("webapps/ROOT/");
		}

		strResult = strBuf.toString();

		return strResult;
	}

	private static String splitString(String str, String param) {
		String result = str;

		if (str.contains(param)) {
			int start = str.indexOf(param);
			result = str.substring(0, start);
		}

		return result;
	}

	/*
	 * 获取获取项目根路径
	 */
	public static String getProjectPath() {
		String path = getClassPath()+"../../";
		return path;
	}

	/*
	 * 获取获取class文件根路径
	 */
	public static String getClassPath() {
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")))
				.replaceAll("file:/", "").replaceAll("%20", " ").trim();
		if (path.indexOf(":") != 1) {
			path = File.separator + path;
		}
		return path;
	}
	
	public static String PathAddress() {
		String strResult = "";

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		StringBuffer strBuf = new StringBuffer();

		strBuf.append(request.getScheme() + "://");
		strBuf.append(request.getServerName() + ":");
		strBuf.append(request.getServerPort() + "");

		strBuf.append(request.getContextPath() + "/");

		strResult = strBuf.toString();// +"ss/";//加入项目的名称

		return strResult;
	}
	
	/*
	 * 获取获取web文件根路径
	 */
	public static String getRealPath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		return path;
	}
	

}

package com.jx.common.config;

import java.io.Serializable;

public class ResultInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 结果代号 :
	 * success:成功;false:失败
	 */
	private String resultCode;
	
	/** 结果内容 */
	private String resultContent;
	
	
	
	/**
	 * 设置 结果代号
	 * success:成功;false:失败
	 * @param String resultCode
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	/**
	 * 获取 结果代号
	 * success:成功;false:失败
	 * @return String resultCode
	 */
	public String getResultCode() {
		return this.resultCode;
	}
	
	/**
	 * 设置 结果内容
	 * 
	 * @param String resultContent
	 */
	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}
	
	/**
	 * 获取 结果内容
	 * 
	 * @return String resultContent
	 */
	public String getResultContent() {
		return this.resultContent;
	}
}

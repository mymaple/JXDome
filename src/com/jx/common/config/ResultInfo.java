package com.jx.common.config;

import java.io.Serializable;

import com.jx.common.util.MapleStringUtil;

public class ResultInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 结果代号 :
	 * success:成功;failure:失败
	 */
	private String resultCode;
	
	/** 结果内容 */
	private String resultContent;
	
	/** 实体类名 */
	private String resultEntity;
	
	/**
	 * 设置 结果代号
	 * success:成功;failure:失败
	 * @param String resultCode
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = MapleStringUtil.trim(resultCode);
	}
	
	/**
	 * 获取 结果代号
	 * success:成功;failure:失败
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
		this.resultContent = MapleStringUtil.trim(resultContent);
	}
	
	/**
	 * 获取 结果内容
	 * 
	 * @return String resultContent
	 */
	public String getResultContent() {
		return this.resultContent;
	}
	
	/**
	 * 设置 实体类名
	 * 
	 * @param String resultEntity
	 */
	public void setResultEntity(String resultEntity) {
		this.resultEntity = MapleStringUtil.trim(resultEntity);
	}
	
	/**
	 * 获取 实体类名
	 * 
	 * @return String resultEntity
	 */
	public String getResultEntity() {
		return this.resultEntity;
	}
}

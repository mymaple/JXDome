package com.jx.common.service;

import com.jx.common.entity.ComAppUserExt;

public interface ComAppUserExtService{

	
	/****************************custom * start***********************************/
	
	/**
	 * 初始化扩展属性
	 * @param String appUserId
	 * @throws Exception
	 */
	public void init(String appUserId) throws Exception ;
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComAppUserExt comAppUserExt
	 * @throws Exception
	 */
	public void add(ComAppUserExt comAppUserExt) throws Exception ;
	
	/**
	 * 加减改值
	 * @param String appUserId, String extCode, String addValue
	 * @throws Exception
	 */
	public void addValue(String appUserId, String extCode, String addValue) throws Exception ;
	
	/**
	 * 更改值
	 * @param String appUserId, String extCode, String changeValue
	 * @throws Exception
	 */
	public void changeValue(String appUserId, String extCode, String changeValue) throws Exception ;

	/**
	 * 删除 
	 * @param String appUserId, String extCode
	 * @throws Exception
	 */
	public void delete(String appUserId, String extCode) throws Exception ;
	
	/**
	 * 删除 
	 * @param String appUserId
	 * @throws Exception
	 */
	public void delete(String appUserId) throws Exception ;
	
	/**
	 * 获取(类)数据
	 * @param String appUserId, String extCode
	 * @return ComAppUserExt
	 * @throws Exception
	 */
	public ComAppUserExt find(String appUserId, String extCode) throws Exception ;
	
	/****************************common * end***********************************/
}
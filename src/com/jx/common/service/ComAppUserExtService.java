package com.jx.common.service;

import java.util.List;

import com.jx.common.entity.ComAppUserExt;

public interface ComAppUserExtService{

	
	/****************************custom * start***********************************/
	
	/**
	 * 初始化扩展属性
	 * @param String appUserId
	 * @throws Exception
	 */
	public void toInit(String appUserId, String integralCount, String mediaExpiry
			, String mediaId, String wxQRcodeExpiry, String wxQRcodeSrc, String openId) throws Exception ;
	
	/**
	 * 获得 积分数量
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetIntegralCount(String appUserId) throws Exception ;
	
	/**
	 * 增加、减少 积分数量
	 * @param String appUserId, String addValue
	 * @throws Exception
	 */
	public void addIntegralCount(String appUserId, String addValue) throws Exception ;
	
	/**
	 * 获得 微信二维码地址
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetWxQRcodeSrc(String appUserId) throws Exception ;
	
	/**
	 * 修改 微信二维码地址
	 * @param String appUserId, String wxQRcodeSrc
	 * @throws Exception
	 */
	public void changeWxQRcodeSrc(String appUserId, String wxQRcodeSrc) throws Exception ;
	
	/**
	 * 获得 微信二维码有效期
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetWxQRcodeExpiry(String appUserId) throws Exception ;
	
	/**
	 * 修改 微信二维码信息
	 * @param String appUserId
	 * @throws Exception
	 */
	public void changeWxQRcodeExpiry(String appUserId, String wxQRcodeSrc) throws Exception ;
	
	/**
	 * 获得 媒体文件id
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetMediaId(String appUserId) throws Exception ;
	
	/**
	 * 修改 媒体文件id
	 * @param String appUserId, String mediaId
	 * @throws Exception
	 */
	public void changeMediaId(String appUserId, String mediaId) throws Exception ;
	
	/**
	 * 获得 媒体文件有效时间
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetMediaExpiry(String appUserId) throws Exception ;
	
	/**
	 * 修改 媒体文件有效时间
	 * @param String appUserId, String mediaExpiry
	 * @throws Exception
	 */
	public void changeMediaExpiry(String appUserId, String mediaExpiry) throws Exception ;
	
	/**
	 * 获取 平台用户Id
	 * @param String openId
	 * @throws Exception
	 */
	public String toGetAppUserId(String openId) throws Exception ;
	
	/**
	 * 微信公众号个人唯一标识
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetOpenId(String appUserId) throws Exception ;
	
	/**
	 * 修改 微信公众号个人唯一标识
	 * @param String appUserId, String openId
	 * @throws Exception
	 */
	public void changeOpenId(String appUserId, String openId) throws Exception ;
	
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
	
	/**
	 * 获取(类)数据
	 * @param String extValue, String extCode
	 * @return ComAppUserExt
	 * @throws Exception
	 */
	public List<ComAppUserExt> list(String extValue, String extCode) throws Exception ;
	
	/****************************common * end***********************************/
}
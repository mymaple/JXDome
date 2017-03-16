package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComReceiveAddress;

public interface ComReceiveAddressService {

	
	/****************************custom * start***********************************/
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComReceiveAddress> listByUserId(String appUserId) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String appUserId, String receiveAddressId
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findByUserIdAndId(String appUserId, String receiveAddressId) throws Exception ;
	
	/**
	 * 成为默认
	 * @param String appUserId, String receiveAddressId
	 * @throws Exception
	 */
	public void toDefault(String appUserId, String receiveAddressId) throws Exception ;
	
	/**
	 * 成为默认
	 * @param String appUserId
	 * @throws Exception
	 */
	public void toDisdefault(String appUserId) throws Exception ;
	
	/**
	 * 微信修改 
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void editWx(ComReceiveAddress comReceiveAddress) throws Exception ;
	
	
	/**
	 * 删除 
	 * @param String appUserId, String receiveAddressId
	 * @throws Exception
	 */
	public void deleteByUserIdAndId(String appUserId, String receiveAddressId) throws Exception ;
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void add(ComReceiveAddress comReceiveAddress) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void edit(ComReceiveAddress comReceiveAddress) throws Exception ;
	
	/**
	 * 更改
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void change(ComReceiveAddress comReceiveAddress) throws Exception ;

	/**
	 * 删除 
	 * @param String receiveAddressId
	 * @throws Exception
	 */
	public void deleteById(String receiveAddressId) throws Exception ;
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;

	/**
	 * 通过id获取(类)数据
	 * @param String receiveAddressId
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findById(String receiveAddressId) throws Exception ;
	
	/**
	 * 通过pd获取(ComReceiveAddress)数据 
	 * @param PageData pd
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComReceiveAddress> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComReceiveAddress> otherHave(ComReceiveAddress comReceiveAddress) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComReceiveAddress> otherHaveCode(String receiveAddressId, String receiveAddressCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
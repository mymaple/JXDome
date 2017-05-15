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
	public List<ComReceiveAddress> listByUE(String appUserId) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String appUserId
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findByUSE(String appUserId) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String appUserId, String receiveAddressId
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findByIdUE(String appUserId, String receiveAddressId) throws Exception ;
	
	/**
	 * 成为默认
	 * @param String appUserId, String receiveAddressId
	 * @throws Exception
	 */
	public void toDefaultByIdUSE(String appUserId, String receiveAddressId) throws Exception ;
	
	/**
	 * 成为不默认
	 * @param String appUserId
	 * @throws Exception
	 */
	public void toDisdefaultByUSE(String appUserId) throws Exception ;
	
	/**
	 * 失效 
	 * @param String appUserId, String receiveAddressId
	 * @throws Exception
	 */
	public void toDisableByIdUE(String appUserId, String receiveAddressId) throws Exception ;
	
	/**
	 * 用户修改 
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void editByIdUE(ComReceiveAddress comReceiveAddress) throws Exception ;
	
	
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
	 * 更改状态 flag 00
	 * @param String flag, String receiveAddressId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String receiveAddressId) throws Exception ;
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String receiveAddressId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String receiveAddressId) throws Exception ;
	
	/**
	 * 删除 
	 * @param String receiveAddressId
	 * @throws Exception
	 */
	public void deleteById(String receiveAddressId) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
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
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComReceiveAddress> listAll() throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComOrder;

public interface ComOrderService {

	
	/****************************custom * start***********************************/

	/**
	 * 新增 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void toConfirmOrder1(ComOrder comOrder) throws Exception ;
	
	/**
	 * 新增 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void toConfirmOrder2(List<ComOrder> comOrderList,String[] shopCarIdArr) throws Exception ;
	
	
	/**
	 * 用户修改地址
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void changeReceiveAddressIdByU(String orderId, String userId, String receiveAddressId) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrder> listByOrderIdsSED(String orderStatus, String appUserId, String[] orderIdArr) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String orderId
	 * @return ComOrder
	 * @throws Exception
	 */
	public ComOrder findByUserED(String orderId, String appUserId) throws Exception ;
	
	/**
	 * 支付
	 * @param appUserId
	 * @param orderIdArr
	 * @throws Exception
	 */
	public void toPayByUserE(String appUserId, String[] orderIdArr) throws Exception ;
		
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void add(ComOrder comOrder) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void edit(ComOrder comOrder) throws Exception ;
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String orderId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String orderId) throws Exception ;
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String orderId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String orderId) throws Exception ;
	
	/**
	 * 删除 
	 * @param String orderId
	 * @throws Exception
	 */
	public void deleteById(String orderId) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String orderId
	 * @return ComOrder
	 * @throws Exception
	 */
	public ComOrder findById(String orderId) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrder> listAll() throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrder> otherHaveCode(String orderId, String orderCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
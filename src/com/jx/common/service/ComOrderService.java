package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComOrder;

public interface ComOrderService {

	
	/****************************custom * start***********************************/

	
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
	 * 更改
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void change(ComOrder comOrder) throws Exception ;

	/**
	 * 删除 
	 * @param String orderId
	 * @throws Exception
	 */
	public void deleteById(String orderId) throws Exception ;
	
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
	 * @param String orderId
	 * @return ComOrder
	 * @throws Exception
	 */
	public ComOrder findById(String orderId) throws Exception ;
	
	/**
	 * 通过pd获取(ComOrder)数据 
	 * @param PageData pd
	 * @return ComOrder
	 * @throws Exception
	 */
	public ComOrder findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrder> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrder> otherHave(ComOrder comOrder) throws Exception ;
	
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
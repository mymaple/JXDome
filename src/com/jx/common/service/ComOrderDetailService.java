package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComOrderDetail;

public interface ComOrderDetailService {

	
	/****************************custom * start***********************************/

		
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrderDetail> listByOrderId(String orderId) throws Exception ;
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComOrderDetail comOrderDetail
	 * @throws Exception
	 */
	public void add(ComOrderDetail comOrderDetail) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComOrderDetail comOrderDetail
	 * @throws Exception
	 */
	public void edit(ComOrderDetail comOrderDetail) throws Exception ;

	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String orderDetailId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String orderDetailId) throws Exception ;
	
	/**
	 * 删除 
	 * @param String orderDetailId
	 * @throws Exception
	 */
	public void deleteById(String orderDetailId) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String orderDetailId
	 * @return ComOrderDetail
	 * @throws Exception
	 */
	public ComOrderDetail findById(String orderDetailId) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrderDetail> listAll() throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
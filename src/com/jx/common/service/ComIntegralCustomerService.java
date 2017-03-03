package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComIntegralCustomer;

public interface ComIntegralCustomerService {

	
	/****************************custom * start***********************************/

	
	/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComIntegralCustomer> parentList, String pId) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralCustomer> listByParentId(String parentId) throws Exception ;
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String integralCustomerId
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralCustomer> listInRank(String integralCustomerId) throws Exception ;
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String integralCustomerId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String integralCustomerId) throws Exception ;
	
	/**
	 * 批量删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void batchDeleteInRank(String[] ids) throws Exception ;
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComIntegralCustomer comIntegralCustomer
	 * @throws Exception
	 */
	public void add(ComIntegralCustomer comIntegralCustomer) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComIntegralCustomer comIntegralCustomer
	 * @throws Exception
	 */
	public void edit(ComIntegralCustomer comIntegralCustomer) throws Exception ;
	
	/**
	 * 更改
	 * @param ComIntegralCustomer comIntegralCustomer
	 * @throws Exception
	 */
	public void change(ComIntegralCustomer comIntegralCustomer) throws Exception ;

	/**
	 * 删除 
	 * @param String integralCustomerId
	 * @throws Exception
	 */
	public void deleteById(String integralCustomerId) throws Exception ;
	
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
	 * @param String integralCustomerId
	 * @return ComIntegralCustomer
	 * @throws Exception
	 */
	public ComIntegralCustomer findById(String integralCustomerId) throws Exception ;
	
	/**
	 * 通过pd获取(ComIntegralCustomer)数据 
	 * @param PageData pd
	 * @return ComIntegralCustomer
	 * @throws Exception
	 */
	public ComIntegralCustomer findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralCustomer> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralCustomer> otherHave(ComIntegralCustomer comIntegralCustomer) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralCustomer> otherHaveCode(String integralCustomerId, String integralCustomerCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
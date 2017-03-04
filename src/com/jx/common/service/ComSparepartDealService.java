package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComSparepartDeal;

public interface ComSparepartDealService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComSparepartDeal comSparepartDeal
	 * @throws Exception
	 */
	public void add(ComSparepartDeal comSparepartDeal) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComSparepartDeal comSparepartDeal
	 * @throws Exception
	 */
	public void edit(ComSparepartDeal comSparepartDeal) throws Exception ;
	
	/**
	 * 更改
	 * @param ComSparepartDeal comSparepartDeal
	 * @throws Exception
	 */
	public void change(ComSparepartDeal comSparepartDeal) throws Exception ;

	/**
	 * 删除 
	 * @param String sparepartDealId
	 * @throws Exception
	 */
	public void deleteById(String sparepartDealId) throws Exception ;
	
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
	 * @param String sparepartDealId
	 * @return ComSparepartDeal
	 * @throws Exception
	 */
	public ComSparepartDeal findById(String sparepartDealId) throws Exception ;
	
	/**
	 * 通过pd获取(ComSparepartDeal)数据 
	 * @param PageData pd
	 * @return ComSparepartDeal
	 * @throws Exception
	 */
	public ComSparepartDeal findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSparepartDeal> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSparepartDeal> otherHave(ComSparepartDeal comSparepartDeal) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSparepartDeal> otherHaveCode(String sparepartDealId, String sparepartDealCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
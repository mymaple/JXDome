package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComProductCategory;

public interface ComProductCategoryService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProductCategory comProductCategory
	 * @throws Exception
	 */
	public void add(ComProductCategory comProductCategory) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComProductCategory comProductCategory
	 * @throws Exception
	 */
	public void edit(ComProductCategory comProductCategory) throws Exception ;
	
	/**
	 * 更改
	 * @param ComProductCategory comProductCategory
	 * @throws Exception
	 */
	public void change(ComProductCategory comProductCategory) throws Exception ;

	/**
	 * 删除 
	 * @param String productCategoryId
	 * @throws Exception
	 */
	public void deleteById(String productCategoryId) throws Exception ;
	
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
	 * @param String productCategoryId
	 * @return ComProductCategory
	 * @throws Exception
	 */
	public ComProductCategory findById(String productCategoryId) throws Exception ;
	
	/**
	 * 通过pd获取(ComProductCategory)数据 
	 * @param PageData pd
	 * @return ComProductCategory
	 * @throws Exception
	 */
	public ComProductCategory findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategory> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategory> otherHave(ComProductCategory comProductCategory) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategory> otherHaveCode(String productCategoryId, String productCategoryCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
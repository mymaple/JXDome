package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComStyleCategory;

public interface ComStyleCategoryService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComStyleCategory comStyleCategory
	 * @throws Exception
	 */
	public void add(ComStyleCategory comStyleCategory) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComStyleCategory comStyleCategory
	 * @throws Exception
	 */
	public void edit(ComStyleCategory comStyleCategory) throws Exception ;
	
	/**
	 * 更改
	 * @param ComStyleCategory comStyleCategory
	 * @throws Exception
	 */
	public void change(ComStyleCategory comStyleCategory) throws Exception ;

	/**
	 * 删除 
	 * @param String styleCategoryId
	 * @throws Exception
	 */
	public void deleteById(String styleCategoryId) throws Exception ;
	
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
	 * @param String styleCategoryId
	 * @return ComStyleCategory
	 * @throws Exception
	 */
	public ComStyleCategory findById(String styleCategoryId) throws Exception ;
	
	/**
	 * 通过pd获取(ComStyleCategory)数据 
	 * @param PageData pd
	 * @return ComStyleCategory
	 * @throws Exception
	 */
	public ComStyleCategory findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> otherHave(ComStyleCategory comStyleCategory) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> otherHaveCode(String styleCategoryId, String styleCategoryCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
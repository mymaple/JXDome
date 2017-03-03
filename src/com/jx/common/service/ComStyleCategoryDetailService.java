package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComStyleCategoryDetail;

public interface ComStyleCategoryDetailService {

	
	/****************************custom * start***********************************/

	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategoryDetail> listByStyleCategoryId(String styleCategoryId) throws Exception ;
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComStyleCategoryDetail comStyleCategoryDetail
	 * @throws Exception
	 */
	public void add(ComStyleCategoryDetail comStyleCategoryDetail) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComStyleCategoryDetail comStyleCategoryDetail
	 * @throws Exception
	 */
	public void edit(ComStyleCategoryDetail comStyleCategoryDetail) throws Exception ;
	
	/**
	 * 更改
	 * @param ComStyleCategoryDetail comStyleCategoryDetail
	 * @throws Exception
	 */
	public void change(ComStyleCategoryDetail comStyleCategoryDetail) throws Exception ;

	/**
	 * 删除 
	 * @param String styleCategoryDetailId
	 * @throws Exception
	 */
	public void deleteById(String styleCategoryDetailId) throws Exception ;
	
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
	 * @param String styleCategoryDetailId
	 * @return ComStyleCategoryDetail
	 * @throws Exception
	 */
	public ComStyleCategoryDetail findById(String styleCategoryDetailId) throws Exception ;
	
	/**
	 * 通过pd获取(ComStyleCategoryDetail)数据 
	 * @param PageData pd
	 * @return ComStyleCategoryDetail
	 * @throws Exception
	 */
	public ComStyleCategoryDetail findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategoryDetail> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategoryDetail> otherHave(ComStyleCategoryDetail comStyleCategoryDetail) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategoryDetail> otherHaveCode(String styleCategoryDetailId, String styleCategoryDetailCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
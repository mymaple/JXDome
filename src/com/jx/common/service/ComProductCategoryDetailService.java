package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComProductCategoryDetail;

public interface ComProductCategoryDetailService {

	
	/****************************custom * start***********************************/

		
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategoryDetail> listByProductCategoryId(String productCategoryId) throws Exception ;
	
	
	/**
	 * 通过id获取(类)数据
	 * @param String productCategoryId, String productId
	 * @return ComProductCategoryDetail
	 * @throws Exception
	 */
	public ComProductCategoryDetail findById1(String productCategoryId, String productId) throws Exception ;
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @throws Exception
	 */
	public void add(ComProductCategoryDetail comProductCategoryDetail) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @throws Exception
	 */
	public void edit(ComProductCategoryDetail comProductCategoryDetail) throws Exception ;
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String productCategoryDetailId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String productCategoryDetailId) throws Exception ;
	
	/**
	 * 删除 
	 * @param String productCategoryDetailId
	 * @throws Exception
	 */
	public void deleteById(String productCategoryDetailId) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String productCategoryDetailId
	 * @return ComProductCategoryDetail
	 * @throws Exception
	 */
	public ComProductCategoryDetail findById(String productCategoryDetailId) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategoryDetail> listAll() throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
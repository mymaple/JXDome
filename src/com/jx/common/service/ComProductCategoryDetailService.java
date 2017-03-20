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
	 * 更改
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @throws Exception
	 */
	public void change(ComProductCategoryDetail comProductCategoryDetail) throws Exception ;

	/**
	 * 删除 
	 * @param String productCategoryDetailId
	 * @throws Exception
	 */
	public void deleteById(String productCategoryDetailId) throws Exception ;
	
	/**
	 * 删除 
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @throws Exception
	 */
	public void delete(ComProductCategoryDetail comProductCategoryDetail) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;

	/**
	 * 生效 
	 * @param String productCategoryDetailId
	 * @throws Exception
	 */
	public void toEffective(String productCategoryDetailId) throws Exception ;
	
	/**
	 * 失效
	 * @param String productCategoryDetailId
	 * @throws Exception
	 */
	public void toDisEffective(String productCategoryDetailId) throws Exception ;
	
	
	/**
	 * 通过id获取(类)数据
	 * @param String productCategoryDetailId
	 * @return ComProductCategoryDetail
	 * @throws Exception
	 */
	public ComProductCategoryDetail findById(String productCategoryDetailId) throws Exception ;
	
	/**
	 * 通过pd获取(ComProductCategoryDetail)数据 
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @return ComProductCategoryDetail
	 * @throws Exception
	 */
	public ComProductCategoryDetail find(ComProductCategoryDetail comProductCategoryDetail) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategoryDetail> list(ComProductCategoryDetail comProductCategoryDetail) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategoryDetail> otherHave(ComProductCategoryDetail comProductCategoryDetail) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategoryDetail> otherHaveCode(String productCategoryDetailId, String productCategoryDetailCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
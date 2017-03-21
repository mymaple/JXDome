package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComStyleCategory;

public interface ComStyleCategoryService {

	
	/****************************custom * start***********************************/

		
	
	/**
	 * 根据parentId 获取所有直接
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComStyleCategory> parentList, String pId) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> listByParentId(String productId, String parentId) throws Exception ;
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String productId, String styleCategoryId
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> listInRank(String productId, String styleCategoryId) throws Exception ;
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String styleCategoryId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String styleCategoryId) throws Exception ;
	
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
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String styleCategoryId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String styleCategoryId) throws Exception ;
	
	/**
	 * 删除 
	 * @param String styleCategoryId
	 * @throws Exception
	 */
	public void deleteById(String styleCategoryId) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
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
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> listAll() throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> otherHaveName(String productId, String styleCategoryId, String styleCategoryName) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComProduct;

public interface ComProductService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProduct comProduct
	 * @throws Exception
	 */
	public void add(ComProduct comProduct) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComProduct comProduct
	 * @throws Exception
	 */
	public void edit(ComProduct comProduct) throws Exception ;
	
	/**
	 * 更改
	 * @param ComProduct comProduct
	 * @throws Exception
	 */
	public void change(ComProduct comProduct) throws Exception ;

	/**
	 * 删除 
	 * @param String productId
	 * @throws Exception
	 */
	public void deleteById(String productId) throws Exception ;
	
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
	 * @param String productId
	 * @return ComProduct
	 * @throws Exception
	 */
	public ComProduct findById(String productId) throws Exception ;
	
	/**
	 * 通过pd获取(ComProduct)数据 
	 * @param PageData pd
	 * @return ComProduct
	 * @throws Exception
	 */
	public ComProduct findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProduct> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProduct> otherHave(ComProduct comProduct) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProduct> otherHaveCode(String productId, String productCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
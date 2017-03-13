package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComOrderProduct;

public interface ComOrderProductService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComOrderProduct comOrderProduct
	 * @throws Exception
	 */
	public void add(ComOrderProduct comOrderProduct) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComOrderProduct comOrderProduct
	 * @throws Exception
	 */
	public void edit(ComOrderProduct comOrderProduct) throws Exception ;
	
	/**
	 * 更改
	 * @param ComOrderProduct comOrderProduct
	 * @throws Exception
	 */
	public void change(ComOrderProduct comOrderProduct) throws Exception ;

	/**
	 * 删除 
	 * @param String orderProductId
	 * @throws Exception
	 */
	public void deleteById(String orderProductId) throws Exception ;
	
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
	 * @param String orderProductId
	 * @return ComOrderProduct
	 * @throws Exception
	 */
	public ComOrderProduct findById(String orderProductId) throws Exception ;
	
	/**
	 * 通过pd获取(ComOrderProduct)数据 
	 * @param PageData pd
	 * @return ComOrderProduct
	 * @throws Exception
	 */
	public ComOrderProduct findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrderProduct> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrderProduct> otherHave(ComOrderProduct comOrderProduct) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrderProduct> otherHaveCode(String orderProductId, String orderProductCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
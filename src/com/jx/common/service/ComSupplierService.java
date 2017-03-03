package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComSupplier;

public interface ComSupplierService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComSupplier comSupplier
	 * @throws Exception
	 */
	public void add(ComSupplier comSupplier) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComSupplier comSupplier
	 * @throws Exception
	 */
	public void edit(ComSupplier comSupplier) throws Exception ;
	
	/**
	 * 更改
	 * @param ComSupplier comSupplier
	 * @throws Exception
	 */
	public void change(ComSupplier comSupplier) throws Exception ;

	/**
	 * 删除 
	 * @param String supplierId
	 * @throws Exception
	 */
	public void deleteById(String supplierId) throws Exception ;
	
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
	 * @param String supplierId
	 * @return ComSupplier
	 * @throws Exception
	 */
	public ComSupplier findById(String supplierId) throws Exception ;
	
	/**
	 * 通过pd获取(ComSupplier)数据 
	 * @param PageData pd
	 * @return ComSupplier
	 * @throws Exception
	 */
	public ComSupplier findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSupplier> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSupplier> otherHave(ComSupplier comSupplier) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSupplier> otherHaveCode(String supplierId, String supplierCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
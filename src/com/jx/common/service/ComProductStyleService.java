package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComProductStyle;

public interface ComProductStyleService {

	
	/****************************custom * start***********************************/

	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductStyle> listByProductIdSE(String productId) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String productStyleId
	 * @return ComProductStyle
	 * @throws Exception
	 */
	public ComProductStyle findByIdSE(String productStyleId) throws Exception ;
		
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProductStyle comProductStyle
	 * @throws Exception
	 */
	public void add(ComProductStyle comProductStyle) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComProductStyle comProductStyle
	 * @throws Exception
	 */
	public void edit(ComProductStyle comProductStyle) throws Exception ;
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String productStyleId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String productStyleId) throws Exception ;
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String productStyleId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String productStyleId) throws Exception ;
	
	/**
	 * 删除 
	 * @param String productStyleId
	 * @throws Exception
	 */
	public void deleteById(String productStyleId) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String productStyleId
	 * @return ComProductStyle
	 * @throws Exception
	 */
	public ComProductStyle findById(String productStyleId) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductStyle> listAll() throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductStyle> otherHaveCode(String productStyleId, String productStyleCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
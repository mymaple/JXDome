package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComProductStyle;

public interface ComProductStyleService {

	
	/****************************custom * start***********************************/

	
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
	 * 更改
	 * @param ComProductStyle comProductStyle
	 * @throws Exception
	 */
	public void change(ComProductStyle comProductStyle) throws Exception ;

	/**
	 * 删除 
	 * @param String productStyleId
	 * @throws Exception
	 */
	public void deleteById(String productStyleId) throws Exception ;
	
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
	 * @param String productStyleId
	 * @return ComProductStyle
	 * @throws Exception
	 */
	public ComProductStyle findById(String productStyleId) throws Exception ;
	
	/**
	 * 通过pd获取(ComProductStyle)数据 
	 * @param PageData pd
	 * @return ComProductStyle
	 * @throws Exception
	 */
	public ComProductStyle findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductStyle> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductStyle> otherHave(ComProductStyle comProductStyle) throws Exception ;
	
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
package com.jx.background.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgMaple;

public interface BgMapleService {

	
	/****************************custom * start***********************************/

	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgMaple bgMaple
	 * @throws Exception
	 */
	public void add(BgMaple bgMaple) throws Exception ;
	
	/**
	 * 修改 
	 * @param BgMaple bgMaple
	 * @throws Exception
	 */
	public void edit(BgMaple bgMaple) throws Exception ;
	
	/**
	 * 更改
	 * @param BgMaple bgMaple
	 * @throws Exception
	 */
	public void change(BgMaple bgMaple) throws Exception ;

	/**
	 * 删除 
	 * @param String mapleId
	 * @throws Exception
	 */
	public void deleteById(String mapleId) throws Exception ;
	
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
	 * @param String mapleId
	 * @return BgMaple
	 * @throws Exception
	 */
	public BgMaple findById(String mapleId) throws Exception ;
	
	/**
	 * 通过pd获取(BgMaple)数据 
	 * @param PageData pd
	 * @return BgMaple
	 * @throws Exception
	 */
	public BgMaple findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMaple> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMaple> otherHave(BgMaple bgMaple) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMaple> otherHaveCode(String mapleId, String mapleCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
package com.jx.background.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgMapleDetail;

public interface BgMapleDetailService {

	
	/****************************custom * start***********************************/
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMapleDetail> listByMapleId(String mapleId) throws Exception ;
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgMapleDetail bgMapleDetail
	 * @throws Exception
	 */
	public void add(BgMapleDetail bgMapleDetail) throws Exception ;
	
	/**
	 * 修改 
	 * @param BgMapleDetail bgMapleDetail
	 * @throws Exception
	 */
	public void edit(BgMapleDetail bgMapleDetail) throws Exception ;
	
	/**
	 * 更改
	 * @param BgMapleDetail bgMapleDetail
	 * @throws Exception
	 */
	public void change(BgMapleDetail bgMapleDetail) throws Exception ;

	/**
	 * 删除 
	 * @param String mapleDetailId
	 * @throws Exception
	 */
	public void deleteById(String mapleDetailId) throws Exception ;
	
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
	 * @param String mapleDetailId
	 * @return BgMapleDetail
	 * @throws Exception
	 */
	public BgMapleDetail findById(String mapleDetailId) throws Exception ;
	
	/**
	 * 通过pd获取(BgMapleDetail)数据 
	 * @param PageData pd
	 * @return BgMapleDetail
	 * @throws Exception
	 */
	public BgMapleDetail findByPd(PageData pd) throws Exception ;
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param String mapleDetailId
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(String mapleDetailId) throws Exception ;
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMapleDetail> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMapleDetail> has(BgMapleDetail bgMapleDetail) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMapleDetail> hasCode(String mapleDetailId, String mapleDetailCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
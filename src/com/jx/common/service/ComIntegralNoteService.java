package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComIntegralNote;

public interface ComIntegralNoteService {

	
	/****************************custom * start***********************************/

	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralNote> listByUserE(String appUserId, String yearMonth) throws Exception ;
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComIntegralNote comIntegralNote
	 * @throws Exception
	 */
	public void add(ComIntegralNote comIntegralNote) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComIntegralNote comIntegralNote
	 * @throws Exception
	 */
	public void edit(ComIntegralNote comIntegralNote) throws Exception ;
	
	/**
	 * 更改
	 * @param ComIntegralNote comIntegralNote
	 * @throws Exception
	 */
	public void change(ComIntegralNote comIntegralNote) throws Exception ;

	/**
	 * 删除 
	 * @param String integralNoteId
	 * @throws Exception
	 */
	public void deleteById(String integralNoteId) throws Exception ;
	
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
	 * @param String integralNoteId
	 * @return ComIntegralNote
	 * @throws Exception
	 */
	public ComIntegralNote findById(String integralNoteId) throws Exception ;
	
	/**
	 * 通过pd获取(ComIntegralNote)数据 
	 * @param PageData pd
	 * @return ComIntegralNote
	 * @throws Exception
	 */
	public ComIntegralNote findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralNote> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralNote> otherHave(ComIntegralNote comIntegralNote) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralNote> otherHaveCode(String integralNoteId, String integralNoteCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
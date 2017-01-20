package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComWxAccount;

public interface ComWxAccountService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComWxAccount comWxAccount
	 * @throws Exception
	 */
	public void add(ComWxAccount comWxAccount) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComWxAccount comWxAccount
	 * @throws Exception
	 */
	public void edit(ComWxAccount comWxAccount) throws Exception ;
	
	/**
	 * 更改
	 * @param ComWxAccount comWxAccount
	 * @throws Exception
	 */
	public void change(ComWxAccount comWxAccount) throws Exception ;

	/**
	 * 删除 
	 * @param String wxAccountId
	 * @throws Exception
	 */
	public void deleteById(String wxAccountId) throws Exception ;
	
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
	 * @param String wxAccountId
	 * @return ComWxAccount
	 * @throws Exception
	 */
	public ComWxAccount findById(String wxAccountId) throws Exception ;
	
	/**
	 * 通过pd获取(ComWxAccount)数据 
	 * @param PageData pd
	 * @return ComWxAccount
	 * @throws Exception
	 */
	public ComWxAccount findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComWxAccount> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComWxAccount> otherHave(ComWxAccount comWxAccount) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComWxAccount> otherHaveCode(String wxAccountId, String wxAccountCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
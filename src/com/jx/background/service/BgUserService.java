package com.jx.background.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgUser;

public interface BgUserService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgUser bgUser
	 * @throws Exception
	 */
	public void add(BgUser bgUser) throws Exception ;
	
	/**
	 * 修改 
	 * @param BgUser bgUser
	 * @throws Exception
	 */
	public void edit(BgUser bgUser) throws Exception ;
	
	/**
	 * 更改
	 * @param BgUser bgUser
	 * @throws Exception
	 */
	public void change(BgUser bgUser) throws Exception ;

	/**
	 * 删除 
	 * @param String userId
	 * @throws Exception
	 */
	public void deleteById(String userId) throws Exception ;
	
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
	 * @param String userId
	 * @return BgUser
	 * @throws Exception
	 */
	public BgUser findById(String userId) throws Exception ;
	
	/**
	 * 通过pd获取(BgUser)数据 
	 * @param PageData pd
	 * @return BgUser
	 * @throws Exception
	 */
	public BgUser findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgUser> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgUser> otherHave(BgUser bgUser) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgUser> otherHaveCode(String userId, String userCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
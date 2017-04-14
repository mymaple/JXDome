package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComInvite;

public interface ComAppUserService {

	
	/****************************custom * start***********************************/
	
	/**
	 * 通过phone获取(类)数据
	 * @param String phone
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findByPhone(String phone) throws Exception ;
	
	/**
	 * 通过appUserCode获取(类)数据
	 * @param String appUserCode
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findByCode(String appUserCode) throws Exception ;
	
	/**
	 * 微信注册
	 * @param String phone, ComInvite comInvite
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser toWxRegister(String phone, ComInvite comInvite) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComAppUser> parentList, String pId) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> listByParentId(String parentId) throws Exception ;
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String appUserId
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> listInRank(String appUserId) throws Exception ;
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String appUserId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String appUserId) throws Exception ;
	
	/**
	 * 批量删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void batchDeleteInRank(String[] ids) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void changeMyInfoByUE(ComAppUser comAppUser) throws Exception ;
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void add(ComAppUser comAppUser) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void edit(ComAppUser comAppUser) throws Exception ;
	
	/**
	 * 更改
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void change(ComAppUser comAppUser) throws Exception ;

	/**
	 * 删除 
	 * @param String appUserId
	 * @throws Exception
	 */
	public void deleteById(String appUserId) throws Exception ;
	
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
	 * @param String appUserId
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findById(String appUserId) throws Exception ;
	
	/**
	 * 通过pd获取(ComAppUser)数据 
	 * @param PageData pd
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> otherHave(ComAppUser comAppUser) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> otherHaveCode(String appUserId, String appUserCode) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> otherHavePhone(String appUserId, String phone) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
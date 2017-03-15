package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComInvite;

public interface ComInviteService {

	
	/****************************custom * start***********************************/

	/**
	 * 微信邀请
	 * @param String code, String pId, String openId
	 * @throws Exception
	 */
	public void toWxInvite(String code, String pId, String openId) throws Exception ;
	
	/**
	 * 获取邀请中的用户
	 * @param String openId
	 * @return ComInvite
	 * @throws Exception
	 */
	public ComInvite findByState00(String openId) throws Exception ;
	
	/**
	 * 成功
	 * @param ComInvite comInvite
	 * @throws Exception
	 */
	public void toSuccess(ComInvite comInvite) throws Exception ;
	
	
	/**
	 * 通过invitedUserId获取(类)数据
	 * @param String invitedUserId
	 * @return ComInvite
	 * @throws Exception
	 */
	public ComInvite findByInvitedUserId(String invitedUserId) throws Exception ;
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComInvite comInvite
	 * @throws Exception
	 */
	public void add(ComInvite comInvite) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComInvite comInvite
	 * @throws Exception
	 */
	public void edit(ComInvite comInvite) throws Exception ;
	
	/**
	 * 更改
	 * @param ComInvite comInvite
	 * @throws Exception
	 */
	public void change(ComInvite comInvite) throws Exception ;

	/**
	 * 删除 
	 * @param String inviteId
	 * @throws Exception
	 */
	public void deleteById(String inviteId) throws Exception ;
	
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
	 * @param String inviteId
	 * @return ComInvite
	 * @throws Exception
	 */
	public ComInvite findById(String inviteId) throws Exception ;
	
	/**
	 * 通过pd获取(ComInvite)数据 
	 * @param PageData pd
	 * @return ComInvite
	 * @throws Exception
	 */
	public ComInvite findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComInvite> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComInvite> otherHave(ComInvite comInvite) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComInvite> otherHaveCode(String inviteId, String inviteCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComAppUserRole;

public interface ComAppUserRoleService {

	
	/****************************custom * start***********************************/

		
	
	/**
	 * 根据parentId 获取所有直接
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComAppUserRole> parentList, String pId) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUserRole> listByParentId(String parentId) throws Exception ;
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String appUserRoleId, String appUserRoleIds
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUserRole> listInRankCheck(String appUserRoleId, String appUserRoleIds) throws Exception ;	
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String appUserRoleId, String appUserRoleIds
	 * @return
	 * @throws Exception
	 */
	public void listInParentRankByE(List<ComAppUserRole> comAppUserRoleList, String parentId) throws Exception ;
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String appUserRoleId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String appUserRoleId) throws Exception ;
	
	/**
	 * 批量删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void batchDeleteInRank(String[] ids) throws Exception ;
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComAppUserRole comAppUserRole
	 * @throws Exception
	 */
	public void add(ComAppUserRole comAppUserRole) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComAppUserRole comAppUserRole
	 * @throws Exception
	 */
	public void edit(ComAppUserRole comAppUserRole) throws Exception ;
	
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String appUserRoleId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String appUserRoleId) throws Exception ;
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String appUserRoleId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String appUserRoleId) throws Exception ;
	
	/**
	 * 删除 
	 * @param String appUserRoleId
	 * @throws Exception
	 */
	public void deleteById(String appUserRoleId) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String appUserRoleId
	 * @return ComAppUserRole
	 * @throws Exception
	 */
	public ComAppUserRole findById(String appUserRoleId) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUserRole> listAll() throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUserRole> otherHaveCode(String appUserRoleId, String appUserRoleCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
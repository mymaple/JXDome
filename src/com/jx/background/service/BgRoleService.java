package com.jx.background.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgRole;

public interface BgRoleService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgRole bgRole
	 * @throws Exception
	 */
	public void add(BgRole bgRole) throws Exception ;
	
	/**
	 * 修改 
	 * @param BgRole bgRole
	 * @throws Exception
	 */
	public void edit(BgRole bgRole) throws Exception ;
	
	/**
	 * 更改
	 * @param BgRole bgRole
	 * @throws Exception
	 */
	public void change(BgRole bgRole) throws Exception ;

	/**
	 * 删除 
	 * @param String roleId
	 * @throws Exception
	 */
	public void deleteById(String roleId) throws Exception ;
	
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
	 * @param String roleId
	 * @return BgRole
	 * @throws Exception
	 */
	public BgRole findById(String roleId) throws Exception ;
	
	/**
	 * 通过pd获取(BgRole)数据 
	 * @param PageData pd
	 * @return BgRole
	 * @throws Exception
	 */
	public BgRole findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> otherHave(BgRole bgRole) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> otherHaveCode(String roleId, String roleCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
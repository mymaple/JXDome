package com.jx.background.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgWxMenuBtn;

public interface BgWxMenuBtnService {

	
	/****************************custom * start***********************************/

	
	/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<BgWxMenuBtn> parentList, String pId) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<BgWxMenuBtn> listByParentId(String parentId) throws Exception ;
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String wxMenuBtnId
	 * @return
	 * @throws Exception
	 */
	public List<BgWxMenuBtn> listInRank(String wxMenuBtnId) throws Exception ;
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String wxMenuBtnId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String wxMenuBtnId) throws Exception ;
	
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
	 * @param BgWxMenuBtn bgWxMenuBtn
	 * @throws Exception
	 */
	public void add(BgWxMenuBtn bgWxMenuBtn) throws Exception ;
	
	/**
	 * 修改 
	 * @param BgWxMenuBtn bgWxMenuBtn
	 * @throws Exception
	 */
	public void edit(BgWxMenuBtn bgWxMenuBtn) throws Exception ;
	
	/**
	 * 更改
	 * @param BgWxMenuBtn bgWxMenuBtn
	 * @throws Exception
	 */
	public void change(BgWxMenuBtn bgWxMenuBtn) throws Exception ;

	/**
	 * 删除 
	 * @param String wxMenuBtnId
	 * @throws Exception
	 */
	public void deleteById(String wxMenuBtnId) throws Exception ;
	
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
	 * @param String wxMenuBtnId
	 * @return BgWxMenuBtn
	 * @throws Exception
	 */
	public BgWxMenuBtn findById(String wxMenuBtnId) throws Exception ;
	
	/**
	 * 通过pd获取(BgWxMenuBtn)数据 
	 * @param PageData pd
	 * @return BgWxMenuBtn
	 * @throws Exception
	 */
	public BgWxMenuBtn findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgWxMenuBtn> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgWxMenuBtn> otherHave(BgWxMenuBtn bgWxMenuBtn) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgWxMenuBtn> otherHaveCode(String wxMenuBtnId, String wxMenuBtnCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
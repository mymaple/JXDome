package com.jx.background.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgMenu;

public interface BgMenuService {

	
	/****************************custom * start***********************************/

	/**
	 * 更改
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void changeMenuIcon(BgMenu bgMenu) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listByParentId(String parentId) throws Exception ;
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String menuId
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listInRank(String menuId) throws Exception ;
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String menuId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String menuId) throws Exception ;
	
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
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void add(BgMenu bgMenu) throws Exception ;
	
	/**
	 * 修改 
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void edit(BgMenu bgMenu) throws Exception ;
	
	/**
	 * 更改
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void change(BgMenu bgMenu) throws Exception ;

	/**
	 * 删除 
	 * @param String menuId
	 * @throws Exception
	 */
	public void deleteById(String menuId) throws Exception ;
	
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
	 * @param String menuId
	 * @return BgMenu
	 * @throws Exception
	 */
	public BgMenu findById(String menuId) throws Exception ;
	
	/**
	 * 通过pd获取(BgMenu)数据 
	 * @param PageData pd
	 * @return BgMenu
	 * @throws Exception
	 */
	public BgMenu findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> otherHave(BgMenu bgMenu) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> otherHaveCode(String menuId, String menuCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
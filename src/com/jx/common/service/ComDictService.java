package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComDict;

public interface ComDictService {

	
	/****************************custom * start***********************************/
	
	/**
	 * 显示名称获取
	 * @param type
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getDisplayName(String type, String value) throws Exception ;
	
	/**
	 * 根据code 获取所有直接子
	 * @param String code
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> listSelect(String type) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> listByParentId(String parentId) throws Exception ;
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> listInRank(String dictId) throws Exception ;
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String dictId) throws Exception ;
	
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
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void add(ComDict comDict) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void edit(ComDict comDict) throws Exception ;
	
	/**
	 * 更改
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void change(ComDict comDict) throws Exception ;

	/**
	 * 删除 
	 * @param String dictId
	 * @throws Exception
	 */
	public void deleteById(String dictId) throws Exception ;
	
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
	 * @param String dictId
	 * @return ComDict
	 * @throws Exception
	 */
	public ComDict findById(String dictId) throws Exception ;
	
	/**
	 * 通过pd获取(ComDict)数据 
	 * @param PageData pd
	 * @return ComDict
	 * @throws Exception
	 */
	public ComDict findByPd(PageData pd) throws Exception ;
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param String dictId
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(String dictId) throws Exception ;
	
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
	public List<ComDict> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> has(ComDict comDict) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> hasCode(String dictCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
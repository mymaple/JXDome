package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComLbt;

public interface ComLbtService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void add(ComLbt comLbt) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void edit(ComLbt comLbt) throws Exception ;
	
	/**
	 * 更改
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void change(ComLbt comLbt) throws Exception ;

	/**
	 * 删除 
	 * @param String lbtId
	 * @throws Exception
	 */
	public void deleteById(String lbtId) throws Exception ;
	
	/**
	 * 删除 
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void delete(ComLbt comLbt) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;

	/**
	 * 通过id获取(类)数据
	 * @param String lbtId
	 * @return ComLbt
	 * @throws Exception
	 */
	public ComLbt findById(String lbtId) throws Exception ;
	
	/**
	 * 通过pd获取(ComLbt)数据 
	 * @param ComLbt comLbt
	 * @return ComLbt
	 * @throws Exception
	 */
	public ComLbt find(ComLbt comLbt) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComLbt> list(ComLbt comLbt) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComLbt> otherHave(ComLbt comLbt) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComLbt> otherHaveCode(String lbtId, String lbtCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
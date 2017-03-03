package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComSparepart;

public interface ComSparepartService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComSparepart comSparepart
	 * @throws Exception
	 */
	public void add(ComSparepart comSparepart) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComSparepart comSparepart
	 * @throws Exception
	 */
	public void edit(ComSparepart comSparepart) throws Exception ;
	
	/**
	 * 更改
	 * @param ComSparepart comSparepart
	 * @throws Exception
	 */
	public void change(ComSparepart comSparepart) throws Exception ;

	/**
	 * 删除 
	 * @param String sparepartId
	 * @throws Exception
	 */
	public void deleteById(String sparepartId) throws Exception ;
	
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
	 * @param String sparepartId
	 * @return ComSparepart
	 * @throws Exception
	 */
	public ComSparepart findById(String sparepartId) throws Exception ;
	
	/**
	 * 通过pd获取(ComSparepart)数据 
	 * @param PageData pd
	 * @return ComSparepart
	 * @throws Exception
	 */
	public ComSparepart findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSparepart> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSparepart> otherHave(ComSparepart comSparepart) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSparepart> otherHaveCode(String sparepartId, String sparepartCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
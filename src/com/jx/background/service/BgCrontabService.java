package com.jx.background.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgCrontab;

public interface BgCrontabService {

	
	/****************************custom * start***********************************/

	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgCrontab bgCrontab
	 * @throws Exception
	 */
	public void add(BgCrontab bgCrontab) throws Exception ;
	
	/**
	 * 修改 
	 * @param BgCrontab bgCrontab
	 * @throws Exception
	 */
	public void edit(BgCrontab bgCrontab) throws Exception ;
	
	/**
	 * 更改
	 * @param BgCrontab bgCrontab
	 * @throws Exception
	 */
	public void change(BgCrontab bgCrontab) throws Exception ;

	/**
	 * 删除 
	 * @param String crontabId
	 * @throws Exception
	 */
	public void deleteById(String crontabId) throws Exception ;
	
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
	 * @param String crontabId
	 * @return BgCrontab
	 * @throws Exception
	 */
	public BgCrontab findById(String crontabId) throws Exception ;
	
	/**
	 * 通过pd获取(BgCrontab)数据 
	 * @param PageData pd
	 * @return BgCrontab
	 * @throws Exception
	 */
	public BgCrontab findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgCrontab> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgCrontab> otherHave(BgCrontab bgCrontab) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgCrontab> otherHaveCode(String crontabId, String crontabCode) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}
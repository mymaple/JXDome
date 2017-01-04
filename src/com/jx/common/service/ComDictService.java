package com.jx.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComDict;

@Service("comDictService")
public class ComDictService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void add(ComDict comDict) throws Exception {
		dao.add("ComDictMapper.add", comDict);
	}
	
	/**
	 * 修改 
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void edit(ComDict comDict) throws Exception {
		dao.edit("ComDictMapper.edit", comDict);
	}

	/**
	 * 删除 
	 * @param String dictId ,String parentId
	 * @throws Exception
	 */
	public void deleteById(String dictId ,String parentId) throws Exception {
		PageData pd = new PageData();
		pd.put("$dictId",dictId);
		pd.put("parentId",parentId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComDictMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("BgUserMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String dictId ,String parentId
	 * @return ComDict
	 * @throws Exception
	 */
	public ComDict findById(String dictId ,String parentId) throws Exception {
		PageData pd = new PageData();
	pd.put("$dictId",dictId);
		pd.put("parentId",parentId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComDict)数据 
	 * @param PageData pd
	 * @return ComDict
	 * @throws Exception
	 */
	public ComDict findByPd(PageData pd) throws Exception {
		return (ComDict) dao.findForObject("ComDictMapper.findByPd", pd);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param String dictId ,String parentId
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(String dictId ,String parentId) throws Exception {
		PageData pd = new PageData();
	pd.put("$dictId",dictId);
		pd.put("parentId",parentId);
		return this.findPdByPd(pd);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ComDictMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDict> listByPd(PageData pd) throws Exception {
		return (List<ComDict>) dao.findForList("ComDictMapper.listByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComDictMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
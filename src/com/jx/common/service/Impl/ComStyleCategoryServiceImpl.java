package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComStyleCategory;
import com.jx.common.service.ComStyleCategoryService;
import com.jx.background.util.BgSessionUtil;

@Service("comStyleCategoryService")
public class ComStyleCategoryServiceImpl implements ComStyleCategoryService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComStyleCategory comStyleCategory
	 * @throws Exception
	 */
	public void add(ComStyleCategory comStyleCategory) throws Exception {
		
		Date nowTime = new Date();
		comStyleCategory.setStyleCategoryId(UuidUtil.get32UUID());
		comStyleCategory.setStyleCategoryStatus("00");
		comStyleCategory.setEffective("01");
		comStyleCategory.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comStyleCategory.setCreateTime(nowTime);
		comStyleCategory.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comStyleCategory.setModifyTime(nowTime);
		
		dao.add("ComStyleCategoryMapper.add", comStyleCategory);
	}
	
	/**
	 * 修改 
	 * @param ComStyleCategory comStyleCategory
	 * @throws Exception
	 */
	public void edit(ComStyleCategory comStyleCategory) throws Exception {
		Date nowTime = new Date();
		comStyleCategory.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comStyleCategory.setModifyTime(nowTime);
		comStyleCategory.setLastModifyTime(this.findById(comStyleCategory.getStyleCategoryId()).getModifyTime());
		if(comStyleCategory.getModifyTime().compareTo(comStyleCategory.getLastModifyTime()) == 0){
			comStyleCategory.setModifyTime(MapleDateUtil.getNextSecond(comStyleCategory.getModifyTime()));
		}
	
		dao.edit("ComStyleCategoryMapper.edit", comStyleCategory);
	}
	
	/**
	 * 更改
	 * @param ComStyleCategory comStyleCategory
	 * @throws Exception
	 */
	public void change(ComStyleCategory comStyleCategory) throws Exception {
		Date nowTime = new Date();
		comStyleCategory.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comStyleCategory.setModifyTime(nowTime);
		comStyleCategory.setLastModifyTime(this.findById(comStyleCategory.getStyleCategoryId()).getModifyTime());
		if(comStyleCategory.getModifyTime().compareTo(comStyleCategory.getLastModifyTime()) == 0){
			comStyleCategory.setModifyTime(MapleDateUtil.getNextSecond(comStyleCategory.getModifyTime()));
		}
		dao.edit("ComStyleCategoryMapper.change", comStyleCategory);
	}

	/**
	 * 删除 
	 * @param String styleCategoryId
	 * @throws Exception
	 */
	public void deleteById(String styleCategoryId) throws Exception {
		PageData pd = new PageData();
		pd.put("styleCategoryId",styleCategoryId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComStyleCategoryMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComStyleCategoryMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String styleCategoryId
	 * @return ComStyleCategory
	 * @throws Exception
	 */
	public ComStyleCategory findById(String styleCategoryId) throws Exception {
		PageData pd = new PageData();
		pd.put("styleCategoryId",styleCategoryId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComStyleCategory)数据 
	 * @param PageData pd
	 * @return ComStyleCategory
	 * @throws Exception
	 */
	public ComStyleCategory findByPd(PageData pd) throws Exception {
		return (ComStyleCategory) dao.findForObject("ComStyleCategoryMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComStyleCategory> listByPd(PageData pd) throws Exception {
		return (List<ComStyleCategory>) dao.findForList("ComStyleCategoryMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComStyleCategory> otherHave(ComStyleCategory comStyleCategory) throws Exception {
		return (List<ComStyleCategory>) dao.findForList("ComStyleCategoryMapper.otherHave", comStyleCategory);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> otherHaveCode(String styleCategoryId, String styleCategoryCode) throws Exception {
		ComStyleCategory comStyleCategory = new ComStyleCategory();
		comStyleCategory.setStyleCategoryId(styleCategoryId);
		comStyleCategory.setStyleCategoryCode(styleCategoryCode);
		return this.otherHave(comStyleCategory);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComStyleCategoryMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
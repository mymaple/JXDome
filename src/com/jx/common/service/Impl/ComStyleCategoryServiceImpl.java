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
	
	
		/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComStyleCategory> parentList, String pId) throws Exception {
		if("0".equals(pId)) return;
		ComStyleCategory comStyleCategory = this.findById(pId);
		comStyleCategory.setSubComStyleCategoryPath("background/styleCategory/list.do?pId="+pId);
		parentList.add(0, comStyleCategory);
		this.getParentList(parentList, comStyleCategory.getParentId());
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> listByParentId(String productId, String parentId) throws Exception {
		PageData pd = new PageData();
		pd.put("productId",productId);
		pd.put("parentId",parentId);
		return this.listByPd(pd);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String styleCategoryId
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategory> listInRank(String productId, String styleCategoryId) throws Exception {
		List<ComStyleCategory> comStyleCategoryList = this.listByParentId(productId, styleCategoryId);
		for(ComStyleCategory comStyleCategory : comStyleCategoryList){
			comStyleCategory.setSubComStyleCategoryPath("background/styleCategory/list.do?pId="+comStyleCategory.getStyleCategoryId()+"&productId="+productId);
			comStyleCategory.setSubComStyleCategoryList(this.listInRank(productId, comStyleCategory.getStyleCategoryId()));
			comStyleCategory.setTarget("treeFrame");
		}
		return comStyleCategoryList;
	}
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String styleCategoryId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String styleCategoryId) throws Exception {
		this.deleteById(styleCategoryId);
		List<ComStyleCategory> comStyleCategoryList = this.listByParentId(null,styleCategoryId);
		for(ComStyleCategory comStyleCategory : comStyleCategoryList){
			this.deleteInRank(comStyleCategory.getStyleCategoryId());
		}
	}
	
	/**
	 * 批量删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void batchDeleteInRank(String[] ids) throws Exception {
		for(String id : ids){
			this.deleteInRank(id);
		}
	}
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
	public List<ComStyleCategory> otherHaveName(String styleCategoryId, String styleCategoryName) throws Exception {
		ComStyleCategory comStyleCategory = new ComStyleCategory();
		comStyleCategory.setStyleCategoryId(styleCategoryId);
		comStyleCategory.setStyleCategoryName(styleCategoryName);
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
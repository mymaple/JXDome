package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComStyleCategory;
import com.jx.common.entity.ComSupplier;
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
	 * @param String productId, String parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComStyleCategory> listByParentId(String productId, String parentId) throws Exception {
		ComStyleCategory comStyleCategory = new ComStyleCategory();
		comStyleCategory.setProductId(productId);
		comStyleCategory.setParentId(parentId);
		return (List<ComStyleCategory>) dao.findForList("ComStyleCategoryMapper.listByParentId", comStyleCategory);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String productId, String styleCategoryId
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
		comStyleCategory.setEffective("01");
		comStyleCategory.setCreateUserId(ShiroSessionUtil.getUserId());
		comStyleCategory.setCreateTime(nowTime);
		comStyleCategory.setModifyUserId(ShiroSessionUtil.getUserId());
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
		comStyleCategory.setModifyUserId(ShiroSessionUtil.getUserId());
		comStyleCategory.setModifyTime(nowTime);
	
		dao.update("ComStyleCategoryMapper.edit", comStyleCategory);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String styleCategoryId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String styleCategoryId) throws Exception {
		ComStyleCategory comStyleCategory = new ComStyleCategory();
		if("00".equals(flag)){
			comStyleCategory.setOldValue("01");
		}else if("01".equals(flag)){
			comStyleCategory.setOldValue("00");
		}else{
			comStyleCategory.setOldValue("flag");
		}
		comStyleCategory.setEffective(flag);
		
		comStyleCategory.setStyleCategoryId(styleCategoryId);
		Date nowTime = new Date();
		comStyleCategory.setModifyUserId(ShiroSessionUtil.getUserId());
		comStyleCategory.setModifyTime(nowTime);
		dao.update("ComStyleCategoryMapper.changeEffective", comStyleCategory);
	}
	
	/**
	 * 删除 
	 * @param String styleCategoryId
	 * @throws Exception
	 */
	public void deleteById(String styleCategoryId) throws Exception {
		dao.delete("ComStyleCategoryMapper.deleteById", styleCategoryId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
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
		return (ComStyleCategory) dao.findForObject("ComStyleCategoryMapper.findById", styleCategoryId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComStyleCategory> listAll() throws Exception {
		return (List<ComStyleCategory>) dao.findForList("ComStyleCategoryMapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComStyleCategory> otherHaveName(String productId, String styleCategoryId, String styleCategoryName) throws Exception {
		ComStyleCategory comStyleCategory = new ComStyleCategory();
		comStyleCategory.setProductId(productId);
		comStyleCategory.setStyleCategoryId(styleCategoryId);
		comStyleCategory.setStyleCategoryName(styleCategoryName);
		return (List<ComStyleCategory>) dao.findForList("ComStyleCategoryMapper.otherHaveName", comStyleCategory);
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
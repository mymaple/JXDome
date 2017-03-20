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
import com.jx.common.entity.ComProductCategory;
import com.jx.common.service.ComProductCategoryService;

@Service("comProductCategoryService")
public class ComProductCategoryServiceImpl implements ComProductCategoryService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProductCategory comProductCategory
	 * @throws Exception
	 */
	public void add(ComProductCategory comProductCategory) throws Exception {
		
		Date nowTime = new Date();
		comProductCategory.setProductCategoryId(UuidUtil.get32UUID());
		comProductCategory.setProductCategoryStatus("00");
		comProductCategory.setEffective("01");
		comProductCategory.setCreateUserId(ShiroSessionUtil.getUserId());
		comProductCategory.setCreateTime(nowTime);
		comProductCategory.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategory.setModifyTime(nowTime);
		
		dao.add("ComProductCategoryMapper.add", comProductCategory);
	}
	
	/**
	 * 修改 
	 * @param ComProductCategory comProductCategory
	 * @throws Exception
	 */
	public void edit(ComProductCategory comProductCategory) throws Exception {
		Date nowTime = new Date();
		comProductCategory.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategory.setModifyTime(nowTime);
	
		dao.update("ComProductCategoryMapper.edit", comProductCategory);
	}
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String productCategoryId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String productCategoryId) throws Exception {
		ComProductCategory comProductCategory = new ComProductCategory();
		if("00".equals(flag)){
			comProductCategory.setOldValue("01");
		}else if("01".equals(flag)){
			comProductCategory.setOldValue("00");
		}else{
			comProductCategory.setOldValue("flag");
		}
		comProductCategory.setProductCategoryStatus(flag);
		
		comProductCategory.setProductCategoryId(productCategoryId);
		Date nowTime = new Date();
		comProductCategory.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategory.setModifyTime(nowTime);
		dao.update("ComProductCategoryMapper.changeStatus", comProductCategory);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String productCategoryId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String productCategoryId) throws Exception {
		ComProductCategory comProductCategory = new ComProductCategory();
		if("00".equals(flag)){
			comProductCategory.setOldValue("01");
		}else if("01".equals(flag)){
			comProductCategory.setOldValue("00");
		}else{
			comProductCategory.setOldValue("flag");
		}
		comProductCategory.setEffective(flag);
		
		comProductCategory.setProductCategoryId(productCategoryId);
		Date nowTime = new Date();
		comProductCategory.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategory.setModifyTime(nowTime);
		dao.update("ComProductCategoryMapper.changeEffective", comProductCategory);
	}
	
	/**
	 * 删除 
	 * @param String productCategoryId
	 * @throws Exception
	 */
	public void deleteById(String productCategoryId) throws Exception {
		dao.delete("ComProductCategoryMapper.deleteById", productCategoryId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComProductCategoryMapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String productCategoryId
	 * @return ComProductCategory
	 * @throws Exception
	 */
	public ComProductCategory findById(String productCategoryId) throws Exception {
		return (ComProductCategory) dao.findForObject("ComProductCategoryMapper.findById", productCategoryId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductCategory> listAll() throws Exception {
		return (List<ComProductCategory>) dao.findForList("ComProductCategoryMapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductCategory> otherHaveCode(String productCategoryId, String productCategoryCode) throws Exception {
		ComProductCategory comProductCategory = new ComProductCategory();
		comProductCategory.setProductCategoryId(productCategoryId);
		comProductCategory.setProductCategoryCode(productCategoryCode);
		return (List<ComProductCategory>) dao.findForList("ComProductCategoryMapper.otherHaveCode", comProductCategory);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComProductCategoryMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
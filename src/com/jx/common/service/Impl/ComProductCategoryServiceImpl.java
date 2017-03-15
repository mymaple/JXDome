package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.Const;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComProductCategory;
import com.jx.common.service.ComProductCategoryService;
import com.jx.background.util.BgSessionUtil;

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
		
		comProductCategory.setHeadImgSrc(MapleFileUtil.transferCache(Const.PATH_FILEUPCACHE, Const.PATH_PC_HEADIMG, comProductCategory.getHeadImgSrc()));
		comProductCategory.setImgSrc1(MapleFileUtil.transferCache(Const.PATH_FILEUPCACHE, Const.PATH_PC_IMG1, comProductCategory.getImgSrc1()));
		comProductCategory.setImgSrc2(MapleFileUtil.transferCache(Const.PATH_FILEUPCACHE, Const.PATH_PC_IMG2, comProductCategory.getImgSrc2()));
		
		
		
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
		
		comProductCategory.setHeadImgSrc(MapleFileUtil.transferCache(Const.PATH_FILEUPCACHE, Const.PATH_PC_HEADIMG, comProductCategory.getHeadImgSrc()));
		comProductCategory.setImgSrc1(MapleFileUtil.transferCache(Const.PATH_FILEUPCACHE, Const.PATH_PC_IMG1, comProductCategory.getImgSrc1()));
		comProductCategory.setImgSrc2(MapleFileUtil.transferCache(Const.PATH_FILEUPCACHE, Const.PATH_PC_IMG2, comProductCategory.getImgSrc2()));
		
		Date nowTime = new Date();
		comProductCategory.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategory.setModifyTime(nowTime);
		comProductCategory.setLastModifyTime(this.findById(comProductCategory.getProductCategoryId()).getModifyTime());
		if(comProductCategory.getModifyTime().compareTo(comProductCategory.getLastModifyTime()) == 0){
			comProductCategory.setModifyTime(MapleDateUtil.getNextSecond(comProductCategory.getModifyTime()));
		}
	
		dao.update("ComProductCategoryMapper.edit", comProductCategory);
	}
	
	/**
	 * 更改
	 * @param ComProductCategory comProductCategory
	 * @throws Exception
	 */
	public void change(ComProductCategory comProductCategory) throws Exception {
		Date nowTime = new Date();
		comProductCategory.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategory.setModifyTime(nowTime);
		comProductCategory.setLastModifyTime(this.findById(comProductCategory.getProductCategoryId()).getModifyTime());
		if(comProductCategory.getModifyTime().compareTo(comProductCategory.getLastModifyTime()) == 0){
			comProductCategory.setModifyTime(MapleDateUtil.getNextSecond(comProductCategory.getModifyTime()));
		}
		dao.update("ComProductCategoryMapper.change", comProductCategory);
	}

	/**
	 * 删除 
	 * @param String productCategoryId
	 * @throws Exception
	 */
	public void deleteById(String productCategoryId) throws Exception {
		PageData pd = new PageData();
		pd.put("productCategoryId",productCategoryId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComProductCategoryMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
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
		PageData pd = new PageData();
		pd.put("productCategoryId",productCategoryId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComProductCategory)数据 
	 * @param PageData pd
	 * @return ComProductCategory
	 * @throws Exception
	 */
	public ComProductCategory findByPd(PageData pd) throws Exception {
		return (ComProductCategory) dao.findForObject("ComProductCategoryMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductCategory> listByPd(PageData pd) throws Exception {
		return (List<ComProductCategory>) dao.findForList("ComProductCategoryMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductCategory> otherHave(ComProductCategory comProductCategory) throws Exception {
		return (List<ComProductCategory>) dao.findForList("ComProductCategoryMapper.otherHave", comProductCategory);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategory> otherHaveCode(String productCategoryId, String productCategoryCode) throws Exception {
		ComProductCategory comProductCategory = new ComProductCategory();
		comProductCategory.setProductCategoryId(productCategoryId);
		comProductCategory.setProductCategoryCode(productCategoryCode);
		return this.otherHave(comProductCategory);
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
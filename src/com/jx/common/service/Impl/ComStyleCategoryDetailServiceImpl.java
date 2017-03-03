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
import com.jx.common.entity.ComStyleCategoryDetail;
import com.jx.common.service.ComStyleCategoryDetailService;
import com.jx.background.util.BgSessionUtil;

@Service("comStyleCategoryDetailService")
public class ComStyleCategoryDetailServiceImpl implements ComStyleCategoryDetailService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategoryDetail> listByStyleCategoryId(String styleCategoryId) throws Exception {
		PageData pd = new PageData();
		pd.put("styleCategoryId",styleCategoryId);
		return this.listByPd(pd);
	}
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComStyleCategoryDetail comStyleCategoryDetail
	 * @throws Exception
	 */
	public void add(ComStyleCategoryDetail comStyleCategoryDetail) throws Exception {
		
		Date nowTime = new Date();
		comStyleCategoryDetail.setStyleCategoryDetailId(UuidUtil.get32UUID());
		comStyleCategoryDetail.setStyleCategoryDetailStatus("00");
		comStyleCategoryDetail.setEffective("01");
		comStyleCategoryDetail.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comStyleCategoryDetail.setCreateTime(nowTime);
		comStyleCategoryDetail.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comStyleCategoryDetail.setModifyTime(nowTime);
		
		dao.add("ComStyleCategoryDetailMapper.add", comStyleCategoryDetail);
	}
	
	/**
	 * 修改 
	 * @param ComStyleCategoryDetail comStyleCategoryDetail
	 * @throws Exception
	 */
	public void edit(ComStyleCategoryDetail comStyleCategoryDetail) throws Exception {
		Date nowTime = new Date();
		comStyleCategoryDetail.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comStyleCategoryDetail.setModifyTime(nowTime);
		comStyleCategoryDetail.setLastModifyTime(this.findById(comStyleCategoryDetail.getStyleCategoryDetailId()).getModifyTime());
		if(comStyleCategoryDetail.getModifyTime().compareTo(comStyleCategoryDetail.getLastModifyTime()) == 0){
			comStyleCategoryDetail.setModifyTime(MapleDateUtil.getNextSecond(comStyleCategoryDetail.getModifyTime()));
		}
	
		dao.edit("ComStyleCategoryDetailMapper.edit", comStyleCategoryDetail);
	}
	
	/**
	 * 更改
	 * @param ComStyleCategoryDetail comStyleCategoryDetail
	 * @throws Exception
	 */
	public void change(ComStyleCategoryDetail comStyleCategoryDetail) throws Exception {
		Date nowTime = new Date();
		comStyleCategoryDetail.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comStyleCategoryDetail.setModifyTime(nowTime);
		comStyleCategoryDetail.setLastModifyTime(this.findById(comStyleCategoryDetail.getStyleCategoryDetailId()).getModifyTime());
		if(comStyleCategoryDetail.getModifyTime().compareTo(comStyleCategoryDetail.getLastModifyTime()) == 0){
			comStyleCategoryDetail.setModifyTime(MapleDateUtil.getNextSecond(comStyleCategoryDetail.getModifyTime()));
		}
		dao.edit("ComStyleCategoryDetailMapper.change", comStyleCategoryDetail);
	}

	/**
	 * 删除 
	 * @param String styleCategoryDetailId
	 * @throws Exception
	 */
	public void deleteById(String styleCategoryDetailId) throws Exception {
		PageData pd = new PageData();
		pd.put("styleCategoryDetailId",styleCategoryDetailId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComStyleCategoryDetailMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComStyleCategoryDetailMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String styleCategoryDetailId
	 * @return ComStyleCategoryDetail
	 * @throws Exception
	 */
	public ComStyleCategoryDetail findById(String styleCategoryDetailId) throws Exception {
		PageData pd = new PageData();
		pd.put("styleCategoryDetailId",styleCategoryDetailId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComStyleCategoryDetail)数据 
	 * @param PageData pd
	 * @return ComStyleCategoryDetail
	 * @throws Exception
	 */
	public ComStyleCategoryDetail findByPd(PageData pd) throws Exception {
		return (ComStyleCategoryDetail) dao.findForObject("ComStyleCategoryDetailMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComStyleCategoryDetail> listByPd(PageData pd) throws Exception {
		return (List<ComStyleCategoryDetail>) dao.findForList("ComStyleCategoryDetailMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComStyleCategoryDetail> otherHave(ComStyleCategoryDetail comStyleCategoryDetail) throws Exception {
		return (List<ComStyleCategoryDetail>) dao.findForList("ComStyleCategoryDetailMapper.otherHave", comStyleCategoryDetail);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComStyleCategoryDetail> otherHaveCode(String styleCategoryDetailId, String styleCategoryDetailCode) throws Exception {
		ComStyleCategoryDetail comStyleCategoryDetail = new ComStyleCategoryDetail();
		comStyleCategoryDetail.setStyleCategoryDetailId(styleCategoryDetailId);
		comStyleCategoryDetail.setStyleCategoryDetailCode(styleCategoryDetailCode);
		return this.otherHave(comStyleCategoryDetail);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComStyleCategoryDetailMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
package com.jx.background.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgMapleDetail;
import com.jx.background.service.BgMapleDetailService;

@Service("bgMapleDetailService")
public class BgMapleDetailServiceImpl implements BgMapleDetailService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgMapleDetail bgMapleDetail
	 * @throws Exception
	 */
	public void add(BgMapleDetail bgMapleDetail) throws Exception {
		dao.add("BgMapleDetailMapper.add", bgMapleDetail);
	}
	
	/**
	 * 修改 
	 * @param BgMapleDetail bgMapleDetail
	 * @throws Exception
	 */
	public void edit(BgMapleDetail bgMapleDetail) throws Exception {
		dao.edit("BgMapleDetailMapper.edit", bgMapleDetail);
	}
	
	/**
	 * 更改
	 * @param BgMapleDetail bgMapleDetail
	 * @throws Exception
	 */
	public void change(BgMapleDetail bgMapleDetail) throws Exception {
		dao.edit("BgMapleDetailMapper.change", bgMapleDetail);
	}

	/**
	 * 删除 
	 * @param String mapleDetailId
	 * @throws Exception
	 */
	public void deleteById(String mapleDetailId) throws Exception {
		PageData pd = new PageData();
		pd.put("mapleDetailId",mapleDetailId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgMapleDetailMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("BgMapleDetailMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String mapleDetailId
	 * @return BgMapleDetail
	 * @throws Exception
	 */
	public BgMapleDetail findById(String mapleDetailId) throws Exception {
		PageData pd = new PageData();
		pd.put("mapleDetailId",mapleDetailId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(BgMapleDetail)数据 
	 * @param PageData pd
	 * @return BgMapleDetail
	 * @throws Exception
	 */
	public BgMapleDetail findByPd(PageData pd) throws Exception {
		return (BgMapleDetail) dao.findForObject("BgMapleDetailMapper.findByPd", pd);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param String mapleDetailId
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(String mapleDetailId) throws Exception {
		PageData pd = new PageData();
		pd.put("mapleDetailId",mapleDetailId);
		return this.findPdByPd(pd);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgMapleDetailMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgMapleDetail> listByPd(PageData pd) throws Exception {
		return (List<BgMapleDetail>) dao.findForList("BgMapleDetailMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgMapleDetail> has(BgMapleDetail bgMapleDetail) throws Exception {
		return (List<BgMapleDetail>) dao.findForList("BgMapleDetailMapper.has", bgMapleDetail);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMapleDetail> hasCode(String mapleDetailCode) throws Exception {
		BgMapleDetail bgMapleDetail = new BgMapleDetail();
		bgMapleDetail.setMapleDetailCode(mapleDetailCode);
		return this.has(bgMapleDetail);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgMapleDetailMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
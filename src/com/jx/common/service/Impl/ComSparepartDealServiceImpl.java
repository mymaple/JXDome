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
import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComIntegralNote;
import com.jx.common.entity.ComSparepart;
import com.jx.common.entity.ComSparepartDeal;
import com.jx.common.service.ComAppUserService;
import com.jx.common.service.ComIntegralNoteService;
import com.jx.common.service.ComSparepartDealService;
import com.jx.common.service.ComSparepartService;
import com.jx.background.util.BgSessionUtil;

@Service("comSparepartDealService")
public class ComSparepartDealServiceImpl implements ComSparepartDealService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name = "comSparepartService")
	private ComSparepartService comSparepartService;
	@Resource(name = "comIntegralNoteService")
	private ComIntegralNoteService comIntegralNoteService;
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 通过审核 
	 * @param ComSparepartDeal comSparepartDeal
	 * @throws Exception
	 */
	public void toPass(ComSparepartDeal comSparepartDeal, ComSparepartDeal comSparepartDealChange) throws Exception {
		this.change(comSparepartDealChange);
		if(comSparepartDeal == null){
			comSparepartDeal = this.findById(comSparepartDealChange.getSparepartDealId());
		}
		String appUserId = comSparepartDeal.getAppUserId();
		String sparepartId = comSparepartDeal.getSparepartId();
		int count = Integer.parseInt(comSparepartDeal.getCount());
		
		ComSparepart comSparepart = comSparepartService.findById(sparepartId);
		ComAppUser comAppUser = comAppUserService.findById(appUserId);
		
		String noteName = comAppUser.getAppUserName()+"出售“"+comSparepart.getSparepartName()+"*"+count+"”获得积分";
		
		ComIntegralNote comIntegralNote = new ComIntegralNote();
		comIntegralNote.setIntegralNoteCode(comSparepartDeal.getSparepartDealId());
		comIntegralNote.setIntegralNoteType("01");
		comIntegralNote.setIntegralNoteName(noteName);
		//4s店积分记录
		comIntegralNote.setAppUserId(appUserId);
		comIntegralNote.setIntegralDealCount(""+Double.valueOf(comSparepart.getIntegral3())*count);
		comIntegralNoteService.add(comIntegralNote);
		//小区经理积分记录
		comIntegralNote.setAppUserId(comAppUser.getParentId());
		comIntegralNote.setIntegralDealCount(""+Double.valueOf(comSparepart.getIntegral2())*count);
		comIntegralNoteService.add(comIntegralNote);
		//大区经理积分记录
		comIntegralNote.setAppUserId(comAppUserService.findById(comAppUser.getParentId()).getParentId());
		comIntegralNote.setIntegralDealCount(""+Double.valueOf(comSparepart.getIntegral1())*count);
		comIntegralNoteService.add(comIntegralNote);
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComSparepartDeal comSparepartDeal
	 * @throws Exception
	 */
	public void add(ComSparepartDeal comSparepartDeal) throws Exception {
		
		Date nowTime = new Date();
		comSparepartDeal.setSparepartDealId(UuidUtil.get32UUID());
		comSparepartDeal.setSparepartDealStatus("00");
		comSparepartDeal.setCheckId("");
		comSparepartDeal.setRemarks("");
		comSparepartDeal.setCheckTime(nowTime);
		comSparepartDeal.setEffective("01");
		comSparepartDeal.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comSparepartDeal.setCreateTime(nowTime);
		comSparepartDeal.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comSparepartDeal.setModifyTime(nowTime);
		
		dao.add("ComSparepartDealMapper.add", comSparepartDeal);
	}
	
	/**
	 * 修改 
	 * @param ComSparepartDeal comSparepartDeal
	 * @throws Exception
	 */
	public void edit(ComSparepartDeal comSparepartDeal) throws Exception {
		Date nowTime = new Date();
		comSparepartDeal.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comSparepartDeal.setModifyTime(nowTime);
		comSparepartDeal.setLastModifyTime(this.findById(comSparepartDeal.getSparepartDealId()).getModifyTime());
		if(comSparepartDeal.getModifyTime().compareTo(comSparepartDeal.getLastModifyTime()) == 0){
			comSparepartDeal.setModifyTime(MapleDateUtil.getNextSecond(comSparepartDeal.getModifyTime()));
		}
	
		dao.edit("ComSparepartDealMapper.edit", comSparepartDeal);
	}
	
	/**
	 * 更改
	 * @param ComSparepartDeal comSparepartDeal
	 * @throws Exception
	 */
	public void change(ComSparepartDeal comSparepartDeal) throws Exception {
		Date nowTime = new Date();
		comSparepartDeal.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comSparepartDeal.setModifyTime(nowTime);
		comSparepartDeal.setLastModifyTime(this.findById(comSparepartDeal.getSparepartDealId()).getModifyTime());
		if(comSparepartDeal.getModifyTime().compareTo(comSparepartDeal.getLastModifyTime()) == 0){
			comSparepartDeal.setModifyTime(MapleDateUtil.getNextSecond(comSparepartDeal.getModifyTime()));
		}
		dao.edit("ComSparepartDealMapper.change", comSparepartDeal);
	}

	/**
	 * 删除 
	 * @param String sparepartDealId
	 * @throws Exception
	 */
	public void deleteById(String sparepartDealId) throws Exception {
		PageData pd = new PageData();
		pd.put("sparepartDealId",sparepartDealId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComSparepartDealMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComSparepartDealMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String sparepartDealId
	 * @return ComSparepartDeal
	 * @throws Exception
	 */
	public ComSparepartDeal findById(String sparepartDealId) throws Exception {
		PageData pd = new PageData();
		pd.put("sparepartDealId",sparepartDealId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComSparepartDeal)数据 
	 * @param PageData pd
	 * @return ComSparepartDeal
	 * @throws Exception
	 */
	public ComSparepartDeal findByPd(PageData pd) throws Exception {
		return (ComSparepartDeal) dao.findForObject("ComSparepartDealMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComSparepartDeal> listByPd(PageData pd) throws Exception {
		return (List<ComSparepartDeal>) dao.findForList("ComSparepartDealMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComSparepartDeal> otherHave(ComSparepartDeal comSparepartDeal) throws Exception {
		return (List<ComSparepartDeal>) dao.findForList("ComSparepartDealMapper.otherHave", comSparepartDeal);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSparepartDeal> otherHaveCode(String sparepartDealId, String sparepartDealCode) throws Exception {
		ComSparepartDeal comSparepartDeal = new ComSparepartDeal();
		comSparepartDeal.setSparepartDealId(sparepartDealId);
		comSparepartDeal.setSparepartDealCode(sparepartDealCode);
		return this.otherHave(comSparepartDeal);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComSparepartDealMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
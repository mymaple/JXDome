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
import com.jx.common.entity.ComAppUserExt;
import com.jx.common.entity.ComIntegralNote;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComIntegralNoteService;
import com.jx.background.util.BgSessionUtil;

@Service("comIntegralNoteService")
public class ComIntegralNoteServiceImpl implements ComIntegralNoteService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name = "comAppUserExtService")
	private ComAppUserExtService comAppUserExtService;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComIntegralNote comIntegralNote
	 * @throws Exception
	 */
	public void add(ComIntegralNote comIntegralNote) throws Exception {
		
		Date nowTime = new Date();
		comIntegralNote.setIntegralNoteId(UuidUtil.get32UUID());
		comIntegralNote.setIntegralDealStatus("00");
		comIntegralNote.setOrderNum(""+nowTime.getTime());
		comIntegralNote.setEffective("01");
		comIntegralNote.setCreateUserId(ShiroSessionUtil.getUserId());
		comIntegralNote.setCreateTime(nowTime);
		comIntegralNote.setModifyUserId(ShiroSessionUtil.getUserId());
		comIntegralNote.setModifyTime(nowTime);
		
		dao.add("ComIntegralNoteMapper.add", comIntegralNote);
		
		String appUserId = comIntegralNote.getAppUserId();
		String addValue = "01".equals(comIntegralNote.getIntegralNoteType())?"":"-";
		addValue += comIntegralNote.getIntegralDealCount();
		comAppUserExtService.addValue(appUserId, ComAppUserExt.INTEGRALCOUNT, addValue);
		
	}
	
	/**
	 * 修改 
	 * @param ComIntegralNote comIntegralNote
	 * @throws Exception
	 */
	public void edit(ComIntegralNote comIntegralNote) throws Exception {
		Date nowTime = new Date();
		comIntegralNote.setModifyUserId(ShiroSessionUtil.getUserId());
		comIntegralNote.setModifyTime(nowTime);
		comIntegralNote.setLastModifyTime(this.findById(comIntegralNote.getIntegralNoteId()).getModifyTime());
		if(comIntegralNote.getModifyTime().compareTo(comIntegralNote.getLastModifyTime()) == 0){
			comIntegralNote.setModifyTime(MapleDateUtil.getNextSecond(comIntegralNote.getModifyTime()));
		}
	
		dao.edit("ComIntegralNoteMapper.edit", comIntegralNote);
	}
	
	/**
	 * 更改
	 * @param ComIntegralNote comIntegralNote
	 * @throws Exception
	 */
	public void change(ComIntegralNote comIntegralNote) throws Exception {
		Date nowTime = new Date();
		comIntegralNote.setModifyUserId(ShiroSessionUtil.getUserId());
		comIntegralNote.setModifyTime(nowTime);
		comIntegralNote.setLastModifyTime(this.findById(comIntegralNote.getIntegralNoteId()).getModifyTime());
		if(comIntegralNote.getModifyTime().compareTo(comIntegralNote.getLastModifyTime()) == 0){
			comIntegralNote.setModifyTime(MapleDateUtil.getNextSecond(comIntegralNote.getModifyTime()));
		}
		dao.edit("ComIntegralNoteMapper.change", comIntegralNote);
	}

	/**
	 * 删除 
	 * @param String integralNoteId
	 * @throws Exception
	 */
	public void deleteById(String integralNoteId) throws Exception {
		PageData pd = new PageData();
		pd.put("integralNoteId",integralNoteId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComIntegralNoteMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComIntegralNoteMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String integralNoteId
	 * @return ComIntegralNote
	 * @throws Exception
	 */
	public ComIntegralNote findById(String integralNoteId) throws Exception {
		PageData pd = new PageData();
		pd.put("integralNoteId",integralNoteId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComIntegralNote)数据 
	 * @param PageData pd
	 * @return ComIntegralNote
	 * @throws Exception
	 */
	public ComIntegralNote findByPd(PageData pd) throws Exception {
		return (ComIntegralNote) dao.findForObject("ComIntegralNoteMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComIntegralNote> listByPd(PageData pd) throws Exception {
		return (List<ComIntegralNote>) dao.findForList("ComIntegralNoteMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComIntegralNote> otherHave(ComIntegralNote comIntegralNote) throws Exception {
		return (List<ComIntegralNote>) dao.findForList("ComIntegralNoteMapper.otherHave", comIntegralNote);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralNote> otherHaveCode(String integralNoteId, String integralNoteCode) throws Exception {
		ComIntegralNote comIntegralNote = new ComIntegralNote();
		comIntegralNote.setIntegralNoteId(integralNoteId);
		comIntegralNote.setIntegralNoteCode(integralNoteCode);
		return this.otherHave(comIntegralNote);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComIntegralNoteMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
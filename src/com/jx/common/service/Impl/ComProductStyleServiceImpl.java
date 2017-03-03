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
import com.jx.common.entity.ComProductStyle;
import com.jx.common.service.ComProductStyleService;
import com.jx.background.util.BgSessionUtil;

@Service("comProductStyleService")
public class ComProductStyleServiceImpl implements ComProductStyleService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProductStyle comProductStyle
	 * @throws Exception
	 */
	public void add(ComProductStyle comProductStyle) throws Exception {
		
		Date nowTime = new Date();
		comProductStyle.setProductStyleId(UuidUtil.get32UUID());
		comProductStyle.setProductStyleInitId("");
		comProductStyle.setProductStyleStatus("00");
		comProductStyle.setEffective("01");
		comProductStyle.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comProductStyle.setCreateTime(nowTime);
		comProductStyle.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comProductStyle.setModifyTime(nowTime);
		
		dao.add("ComProductStyleMapper.add", comProductStyle);
	}
	
	/**
	 * 修改 
	 * @param ComProductStyle comProductStyle
	 * @throws Exception
	 */
	public void edit(ComProductStyle comProductStyle) throws Exception {
		Date nowTime = new Date();
		comProductStyle.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comProductStyle.setModifyTime(nowTime);
		comProductStyle.setLastModifyTime(this.findById(comProductStyle.getProductStyleId()).getModifyTime());
		if(comProductStyle.getModifyTime().compareTo(comProductStyle.getLastModifyTime()) == 0){
			comProductStyle.setModifyTime(MapleDateUtil.getNextSecond(comProductStyle.getModifyTime()));
		}
	
		dao.edit("ComProductStyleMapper.edit", comProductStyle);
	}
	
	/**
	 * 更改
	 * @param ComProductStyle comProductStyle
	 * @throws Exception
	 */
	public void change(ComProductStyle comProductStyle) throws Exception {
		Date nowTime = new Date();
		comProductStyle.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comProductStyle.setModifyTime(nowTime);
		comProductStyle.setLastModifyTime(this.findById(comProductStyle.getProductStyleId()).getModifyTime());
		if(comProductStyle.getModifyTime().compareTo(comProductStyle.getLastModifyTime()) == 0){
			comProductStyle.setModifyTime(MapleDateUtil.getNextSecond(comProductStyle.getModifyTime()));
		}
		dao.edit("ComProductStyleMapper.change", comProductStyle);
	}

	/**
	 * 删除 
	 * @param String productStyleId
	 * @throws Exception
	 */
	public void deleteById(String productStyleId) throws Exception {
		PageData pd = new PageData();
		pd.put("productStyleId",productStyleId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComProductStyleMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComProductStyleMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String productStyleId
	 * @return ComProductStyle
	 * @throws Exception
	 */
	public ComProductStyle findById(String productStyleId) throws Exception {
		PageData pd = new PageData();
		pd.put("productStyleId",productStyleId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComProductStyle)数据 
	 * @param PageData pd
	 * @return ComProductStyle
	 * @throws Exception
	 */
	public ComProductStyle findByPd(PageData pd) throws Exception {
		return (ComProductStyle) dao.findForObject("ComProductStyleMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductStyle> listByPd(PageData pd) throws Exception {
		return (List<ComProductStyle>) dao.findForList("ComProductStyleMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductStyle> otherHave(ComProductStyle comProductStyle) throws Exception {
		return (List<ComProductStyle>) dao.findForList("ComProductStyleMapper.otherHave", comProductStyle);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductStyle> otherHaveCode(String productStyleId, String productStyleCode) throws Exception {
		ComProductStyle comProductStyle = new ComProductStyle();
		comProductStyle.setProductStyleId(productStyleId);
		comProductStyle.setProductStyleCode(productStyleCode);
		return this.otherHave(comProductStyle);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComProductStyleMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
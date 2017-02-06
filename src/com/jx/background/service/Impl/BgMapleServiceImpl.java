package com.jx.background.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.UuidUtil;
import com.jx.background.entity.BgMaple;
import com.jx.background.entity.BgMapleDetail;
import com.jx.background.service.BgMapleDetailService;
import com.jx.background.service.BgMapleService;
import com.jx.background.util.BgSessionUtil;

@Service("bgMapleService")
public class BgMapleServiceImpl implements BgMapleService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="bgMapleDetailService")
	private BgMapleDetailService bgMapleDetailService;
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgMaple bgMaple
	 * @throws Exception
	 */
	public void add(BgMaple bgMaple) throws Exception {
		
		Date nowTime = new Date();
		bgMaple.setMapleId(UuidUtil.get32UUID());
		bgMaple.setMapleStatus("00");
		bgMaple.setEffective("01");
		bgMaple.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setCreateTime(nowTime);
		bgMaple.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setModifyTime(nowTime);
		
		dao.add("BgMapleMapper.add", bgMaple);
		
		List<BgMapleDetail> bgMapleDetailList = bgMapleDetailService.listByMapleId("27a853950d0e4876ba0eccf8d7e2dd8f");
		for(int i=0;i<bgMapleDetailList.size();i++){
			BgMapleDetail bgMapleDetail = bgMapleDetailList.get(i);
			bgMapleDetail.setMapleDetailId(UuidUtil.get32UUID());
			bgMapleDetail.setMapleId(bgMaple.getMapleId());
			bgMapleDetail.setMapleDetailCode(bgMaple.getMapleCode() + MapleStringUtil.firstToUpper(bgMapleDetail.getMapleDetailCode()));
			bgMapleDetail.setMapleDetailName(bgMaple.getMapleName() + bgMapleDetail.getMapleDetailName());
			if("05".equals(bgMapleDetail.getMapleDetailType())){
				bgMapleDetail.setTypeCode(bgMaple.getEntityPackage()+"_"+bgMapleDetail.getMapleDetailCode());
			}
			
			bgMapleDetail.setOrderNum(String.valueOf(new Date().getTime()));
			bgMapleDetail.setCreateUserId(bgMaple.getCreateUserId());
			bgMapleDetail.setCreateTime(bgMaple.getCreateTime());
			bgMapleDetail.setModifyUserId(bgMaple.getModifyUserId());
			bgMapleDetail.setModifyTime(bgMaple.getModifyTime());
			bgMapleDetailService.add(bgMapleDetail);
		}
	}
	
	/**
	 * 修改 
	 * @param BgMaple bgMaple
	 * @throws Exception
	 */
	public void edit(BgMaple bgMaple) throws Exception {
		Date nowTime = new Date();
		bgMaple.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setModifyTime(nowTime);
		bgMaple.setLastModifyTime(this.findById(bgMaple.getMapleId()).getModifyTime());
		if(bgMaple.getModifyTime().compareTo(bgMaple.getLastModifyTime()) == 0){
			bgMaple.setModifyTime(MapleDateUtil.getNextSecond(bgMaple.getModifyTime()));
		}
	
		dao.edit("BgMapleMapper.edit", bgMaple);
	}
	
	/**
	 * 更改
	 * @param BgMaple bgMaple
	 * @throws Exception
	 */
	public void change(BgMaple bgMaple) throws Exception {
		Date nowTime = new Date();
		bgMaple.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setModifyTime(nowTime);
		bgMaple.setLastModifyTime(this.findById(bgMaple.getMapleId()).getModifyTime());
		if(bgMaple.getModifyTime().compareTo(bgMaple.getLastModifyTime()) == 0){
			bgMaple.setModifyTime(MapleDateUtil.getNextSecond(bgMaple.getModifyTime()));
		}
		dao.edit("BgMapleMapper.change", bgMaple);
	}

	/**
	 * 删除 
	 * @param String mapleId
	 * @throws Exception
	 */
	public void deleteById(String mapleId) throws Exception {
		PageData pd = new PageData();
		pd.put("mapleId",mapleId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgMapleMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("BgMapleMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String mapleId
	 * @return BgMaple
	 * @throws Exception
	 */
	public BgMaple findById(String mapleId) throws Exception {
		PageData pd = new PageData();
		pd.put("mapleId",mapleId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(BgMaple)数据 
	 * @param PageData pd
	 * @return BgMaple
	 * @throws Exception
	 */
	public BgMaple findByPd(PageData pd) throws Exception {
		return (BgMaple) dao.findForObject("BgMapleMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgMaple> listByPd(PageData pd) throws Exception {
		return (List<BgMaple>) dao.findForList("BgMapleMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgMaple> otherHave(BgMaple bgMaple) throws Exception {
		return (List<BgMaple>) dao.findForList("BgMapleMapper.otherHave", bgMaple);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMaple> otherHaveCode(String mapleId, String mapleCode) throws Exception {
		BgMaple bgMaple = new BgMaple();
		bgMaple.setMapleId(mapleId);
		bgMaple.setMapleCode(mapleCode);
		return this.otherHave(bgMaple);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgMapleMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
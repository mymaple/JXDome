package com.jx.background.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.background.entity.BgCrontab;
import com.jx.background.service.BgCrontabService;
import com.jx.background.util.BgSessionUtil;

@Service("bgCrontabService")
public class BgCrontabServiceImpl implements BgCrontabService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgCrontab bgCrontab
	 * @throws Exception
	 */
	public void add(BgCrontab bgCrontab) throws Exception {
		
		Date nowTime = new Date();
		bgCrontab.setCrontabId(UuidUtil.get32UUID());
		bgCrontab.setCrontabStatus("00");
		bgCrontab.setStartupTimes("0");
		bgCrontab.setTriggerTimes("0");
		bgCrontab.setEndTime(nowTime);
		bgCrontab.setEffective("01");
		bgCrontab.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgCrontab.setCreateTime(nowTime);
		bgCrontab.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgCrontab.setModifyTime(nowTime);
		
		dao.add("BgCrontabMapper.add", bgCrontab);
	}
	
	/**
	 * 修改 
	 * @param BgCrontab bgCrontab
	 * @throws Exception
	 */
	public void edit(BgCrontab bgCrontab) throws Exception {
		Date nowTime = new Date();
		bgCrontab.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgCrontab.setModifyTime(nowTime);
		bgCrontab.setLastModifyTime(this.findById(bgCrontab.getCrontabId()).getModifyTime());
		if(bgCrontab.getModifyTime().compareTo(bgCrontab.getLastModifyTime()) == 0){
			bgCrontab.setModifyTime(MapleDateUtil.getNextSecond(bgCrontab.getModifyTime()));
		}
	
		dao.edit("BgCrontabMapper.edit", bgCrontab);
	}
	
	/**
	 * 更改
	 * @param BgCrontab bgCrontab
	 * @throws Exception
	 */
	public void change(BgCrontab bgCrontab) throws Exception {
		Date nowTime = new Date();
		bgCrontab.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgCrontab.setModifyTime(nowTime);
		bgCrontab.setLastModifyTime(this.findById(bgCrontab.getCrontabId()).getModifyTime());
		if(bgCrontab.getModifyTime().compareTo(bgCrontab.getLastModifyTime()) == 0){
			bgCrontab.setModifyTime(MapleDateUtil.getNextSecond(bgCrontab.getModifyTime()));
		}
		dao.edit("BgCrontabMapper.change", bgCrontab);
	}

	/**
	 * 删除 
	 * @param String crontabId
	 * @throws Exception
	 */
	public void deleteById(String crontabId) throws Exception {
		PageData pd = new PageData();
		pd.put("crontabId",crontabId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgCrontabMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("BgCrontabMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String crontabId
	 * @return BgCrontab
	 * @throws Exception
	 */
	public BgCrontab findById(String crontabId) throws Exception {
		PageData pd = new PageData();
		pd.put("crontabId",crontabId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(BgCrontab)数据 
	 * @param PageData pd
	 * @return BgCrontab
	 * @throws Exception
	 */
	public BgCrontab findByPd(PageData pd) throws Exception {
		return (BgCrontab) dao.findForObject("BgCrontabMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgCrontab> listByPd(PageData pd) throws Exception {
		return (List<BgCrontab>) dao.findForList("BgCrontabMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgCrontab> otherHave(BgCrontab bgCrontab) throws Exception {
		return (List<BgCrontab>) dao.findForList("BgCrontabMapper.otherHave", bgCrontab);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgCrontab> otherHaveCode(String crontabId, String crontabCode) throws Exception {
		BgCrontab bgCrontab = new BgCrontab();
		bgCrontab.setCrontabId(crontabId);
		bgCrontab.setCrontabCode(crontabCode);
		return this.otherHave(bgCrontab);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgCrontabMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
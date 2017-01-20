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
import com.jx.common.entity.ComWxAccount;
import com.jx.common.service.ComWxAccountService;
import com.jx.background.util.BgSessionUtil;

@Service("comWxAccountService")
public class ComWxAccountServiceImpl implements ComWxAccountService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComWxAccount comWxAccount
	 * @throws Exception
	 */
	public void add(ComWxAccount comWxAccount) throws Exception {
		
		Date nowTime = new Date();
		comWxAccount.setWxAccountId(UuidUtil.get32UUID());
		comWxAccount.setWxAccountStatus("00");
		comWxAccount.setAccessToken("");
		comWxAccount.setJsApiTicket("");
		comWxAccount.setEffective("01");
		comWxAccount.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comWxAccount.setCreateTime(nowTime);
		comWxAccount.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comWxAccount.setModifyTime(nowTime);
		
		dao.add("ComWxAccountMapper.add", comWxAccount);
	}
	
	/**
	 * 修改 
	 * @param ComWxAccount comWxAccount
	 * @throws Exception
	 */
	public void edit(ComWxAccount comWxAccount) throws Exception {
		Date nowTime = new Date();
		comWxAccount.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comWxAccount.setModifyTime(nowTime);
		comWxAccount.setLastModifyTime(this.findById(comWxAccount.getWxAccountId()).getModifyTime());
		if(comWxAccount.getModifyTime().compareTo(comWxAccount.getLastModifyTime()) == 0){
			comWxAccount.setModifyTime(MapleDateUtil.getNextSecond(comWxAccount.getModifyTime()));
		}
	
		dao.edit("ComWxAccountMapper.edit", comWxAccount);
	}
	
	/**
	 * 更改
	 * @param ComWxAccount comWxAccount
	 * @throws Exception
	 */
	public void change(ComWxAccount comWxAccount) throws Exception {
		Date nowTime = new Date();
		comWxAccount.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comWxAccount.setModifyTime(nowTime);
		comWxAccount.setLastModifyTime(this.findById(comWxAccount.getWxAccountId()).getModifyTime());
		if(comWxAccount.getModifyTime().compareTo(comWxAccount.getLastModifyTime()) == 0){
			comWxAccount.setModifyTime(MapleDateUtil.getNextSecond(comWxAccount.getModifyTime()));
		}
		dao.edit("ComWxAccountMapper.change", comWxAccount);
	}

	/**
	 * 删除 
	 * @param String wxAccountId
	 * @throws Exception
	 */
	public void deleteById(String wxAccountId) throws Exception {
		PageData pd = new PageData();
		pd.put("wxAccountId",wxAccountId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComWxAccountMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComWxAccountMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String wxAccountId
	 * @return ComWxAccount
	 * @throws Exception
	 */
	public ComWxAccount findById(String wxAccountId) throws Exception {
		PageData pd = new PageData();
		pd.put("wxAccountId",wxAccountId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComWxAccount)数据 
	 * @param PageData pd
	 * @return ComWxAccount
	 * @throws Exception
	 */
	public ComWxAccount findByPd(PageData pd) throws Exception {
		return (ComWxAccount) dao.findForObject("ComWxAccountMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComWxAccount> listByPd(PageData pd) throws Exception {
		return (List<ComWxAccount>) dao.findForList("ComWxAccountMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComWxAccount> otherHave(ComWxAccount comWxAccount) throws Exception {
		return (List<ComWxAccount>) dao.findForList("ComWxAccountMapper.otherHave", comWxAccount);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComWxAccount> otherHaveCode(String wxAccountId, String wxAccountCode) throws Exception {
		ComWxAccount comWxAccount = new ComWxAccount();
		comWxAccount.setWxAccountId(wxAccountId);
		comWxAccount.setWxAccountCode(wxAccountCode);
		return this.otherHave(comWxAccount);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComWxAccountMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
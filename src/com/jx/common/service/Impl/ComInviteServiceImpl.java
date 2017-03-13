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
import com.jx.common.entity.ComInvite;
import com.jx.common.service.ComInviteService;
import com.jx.background.util.BgSessionUtil;

@Service("comInviteService")
public class ComInviteServiceImpl implements ComInviteService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 通过invitedUserId获取(类)数据
	 * @param String invitedUserId
	 * @return ComInvite
	 * @throws Exception
	 */
	public ComInvite findByInvitedUserId(String invitedUserId) throws Exception {
		PageData pd = new PageData();
		pd.put("invitedUserId",invitedUserId);
		return this.findByPd(pd);
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComInvite comInvite
	 * @throws Exception
	 */
	public void add(ComInvite comInvite) throws Exception {
		
		Date nowTime = new Date();
		comInvite.setInviteId(UuidUtil.get32UUID());
		comInvite.setEffective("01");
		comInvite.setCreateTime(nowTime);
		comInvite.setModifyTime(nowTime);
		
		dao.add("ComInviteMapper.add", comInvite);
	}
	
	/**
	 * 修改 
	 * @param ComInvite comInvite
	 * @throws Exception
	 */
	public void edit(ComInvite comInvite) throws Exception {
		Date nowTime = new Date();
		comInvite.setModifyUserId(ShiroSessionUtil.getUserId());
		comInvite.setModifyTime(nowTime);
		comInvite.setLastModifyTime(this.findById(comInvite.getInviteId()).getModifyTime());
		if(comInvite.getModifyTime().compareTo(comInvite.getLastModifyTime()) == 0){
			comInvite.setModifyTime(MapleDateUtil.getNextSecond(comInvite.getModifyTime()));
		}
	
		dao.edit("ComInviteMapper.edit", comInvite);
	}
	
	/**
	 * 更改
	 * @param ComInvite comInvite
	 * @throws Exception
	 */
	public void change(ComInvite comInvite) throws Exception {
		Date nowTime = new Date();
		comInvite.setModifyUserId(ShiroSessionUtil.getUserId());
		comInvite.setModifyTime(nowTime);
		comInvite.setLastModifyTime(this.findById(comInvite.getInviteId()).getModifyTime());
		if(comInvite.getModifyTime().compareTo(comInvite.getLastModifyTime()) == 0){
			comInvite.setModifyTime(MapleDateUtil.getNextSecond(comInvite.getModifyTime()));
		}
		dao.edit("ComInviteMapper.change", comInvite);
	}

	/**
	 * 删除 
	 * @param String inviteId
	 * @throws Exception
	 */
	public void deleteById(String inviteId) throws Exception {
		PageData pd = new PageData();
		pd.put("inviteId",inviteId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComInviteMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComInviteMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String inviteId
	 * @return ComInvite
	 * @throws Exception
	 */
	public ComInvite findById(String inviteId) throws Exception {
		PageData pd = new PageData();
		pd.put("inviteId",inviteId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComInvite)数据 
	 * @param PageData pd
	 * @return ComInvite
	 * @throws Exception
	 */
	public ComInvite findByPd(PageData pd) throws Exception {
		return (ComInvite) dao.findForObject("ComInviteMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComInvite> listByPd(PageData pd) throws Exception {
		return (List<ComInvite>) dao.findForList("ComInviteMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComInvite> otherHave(ComInvite comInvite) throws Exception {
		return (List<ComInvite>) dao.findForList("ComInviteMapper.otherHave", comInvite);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComInvite> otherHaveCode(String inviteId, String inviteCode) throws Exception {
		ComInvite comInvite = new ComInvite();
		comInvite.setInviteId(inviteId);
		comInvite.setInviteCode(inviteCode);
		return this.otherHave(comInvite);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComInviteMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
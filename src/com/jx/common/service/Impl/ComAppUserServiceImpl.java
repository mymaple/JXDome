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
import com.jx.common.service.ComAppUserService;
import com.jx.background.util.BgSessionUtil;

@Service("comAppUserService")
public class ComAppUserServiceImpl implements ComAppUserService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void add(ComAppUser comAppUser) throws Exception {
		
		Date nowTime = new Date();
		comAppUser.setAppUserId(UuidUtil.get32UUID());
		comAppUser.setEffective("01");
		comAppUser.setCreateTime(nowTime);
		comAppUser.setModifyTime(nowTime);
		
		dao.add("ComAppUserMapper.add", comAppUser);
	}
	
	/**
	 * 修改 
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void edit(ComAppUser comAppUser) throws Exception {
		Date nowTime = new Date();
		comAppUser.setModifyTime(nowTime);
		comAppUser.setLastModifyTime(this.findById(comAppUser.getAppUserId()).getModifyTime());
		if(comAppUser.getModifyTime().compareTo(comAppUser.getLastModifyTime()) == 0){
			comAppUser.setModifyTime(MapleDateUtil.getNextSecond(comAppUser.getModifyTime()));
		}
	
		dao.edit("ComAppUserMapper.edit", comAppUser);
	}
	
	/**
	 * 更改
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void change(ComAppUser comAppUser) throws Exception {
		Date nowTime = new Date();
		comAppUser.setModifyTime(nowTime);
		comAppUser.setLastModifyTime(this.findById(comAppUser.getAppUserId()).getModifyTime());
		if(comAppUser.getModifyTime().compareTo(comAppUser.getLastModifyTime()) == 0){
			comAppUser.setModifyTime(MapleDateUtil.getNextSecond(comAppUser.getModifyTime()));
		}
		dao.edit("ComAppUserMapper.change", comAppUser);
	}

	/**
	 * 删除 
	 * @param String appUserId
	 * @throws Exception
	 */
	public void deleteById(String appUserId) throws Exception {
		PageData pd = new PageData();
		pd.put("appUserId",appUserId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComAppUserMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComAppUserMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String appUserId
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findById(String appUserId) throws Exception {
		PageData pd = new PageData();
		pd.put("appUserId",appUserId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComAppUser)数据 
	 * @param PageData pd
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findByPd(PageData pd) throws Exception {
		return (ComAppUser) dao.findForObject("ComAppUserMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUser> listByPd(PageData pd) throws Exception {
		return (List<ComAppUser>) dao.findForList("ComAppUserMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUser> otherHave(ComAppUser comAppUser) throws Exception {
		return (List<ComAppUser>) dao.findForList("ComAppUserMapper.otherHave", comAppUser);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> otherHaveCode(String appUserId, String appUserCode) throws Exception {
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setAppUserId(appUserId);
		comAppUser.setAppUserCode(appUserCode);
		return this.otherHave(comAppUser);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComAppUserMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
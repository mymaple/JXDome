package com.jx.background.service.Impl;

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
import com.jx.background.entity.BgRole;
import com.jx.background.service.BgRoleService;
import com.jx.background.util.BgSessionUtil;

@Service("bgRoleService")
public class BgRoleServiceImpl implements BgRoleService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgRole bgRole
	 * @throws Exception
	 */
	public void add(BgRole bgRole) throws Exception {
		
		Date nowTime = new Date();
		bgRole.setRoleId(UuidUtil.get32UUID());
		bgRole.setRoleStatus("00");
		bgRole.setRoleRights("");
		bgRole.setAddRights("");
		bgRole.setDelRights("");
		bgRole.setEditRights("");
		bgRole.setSeleRights("");
		bgRole.setEffective("01");
		bgRole.setCreateUserId(ShiroSessionUtil.getUserId());
		bgRole.setCreateTime(nowTime);
		bgRole.setModifyUserId(ShiroSessionUtil.getUserId());
		bgRole.setModifyTime(nowTime);
		
		dao.add("BgRoleMapper.add", bgRole);
	}
	
	/**
	 * 修改 
	 * @param BgRole bgRole
	 * @throws Exception
	 */
	public void edit(BgRole bgRole) throws Exception {
		Date nowTime = new Date();
		bgRole.setModifyUserId(ShiroSessionUtil.getUserId());
		bgRole.setModifyTime(nowTime);
		bgRole.setLastModifyTime(this.findById(bgRole.getRoleId()).getModifyTime());
		if(bgRole.getModifyTime().compareTo(bgRole.getLastModifyTime()) == 0){
			bgRole.setModifyTime(MapleDateUtil.getNextSecond(bgRole.getModifyTime()));
		}
	
		dao.edit("BgRoleMapper.edit", bgRole);
	}
	
	/**
	 * 更改
	 * @param BgRole bgRole
	 * @throws Exception
	 */
	public void change(BgRole bgRole) throws Exception {
		Date nowTime = new Date();
		bgRole.setModifyUserId(ShiroSessionUtil.getUserId());
		bgRole.setModifyTime(nowTime);
		bgRole.setLastModifyTime(this.findById(bgRole.getRoleId()).getModifyTime());
		if(bgRole.getModifyTime().compareTo(bgRole.getLastModifyTime()) == 0){
			bgRole.setModifyTime(MapleDateUtil.getNextSecond(bgRole.getModifyTime()));
		}
		dao.edit("BgRoleMapper.change", bgRole);
	}

	/**
	 * 删除 
	 * @param String roleId
	 * @throws Exception
	 */
	public void deleteById(String roleId) throws Exception {
		PageData pd = new PageData();
		pd.put("roleId",roleId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgRoleMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("BgRoleMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String roleId
	 * @return BgRole
	 * @throws Exception
	 */
	public BgRole findById(String roleId) throws Exception {
		PageData pd = new PageData();
		pd.put("roleId",roleId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(BgRole)数据 
	 * @param PageData pd
	 * @return BgRole
	 * @throws Exception
	 */
	public BgRole findByPd(PageData pd) throws Exception {
		return (BgRole) dao.findForObject("BgRoleMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgRole> listByPd(PageData pd) throws Exception {
		return (List<BgRole>) dao.findForList("BgRoleMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgRole> otherHave(BgRole bgRole) throws Exception {
		return (List<BgRole>) dao.findForList("BgRoleMapper.otherHave", bgRole);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> otherHaveCode(String roleId, String roleCode) throws Exception {
		BgRole bgRole = new BgRole();
		bgRole.setRoleId(roleId);
		bgRole.setRoleCode(roleCode);
		return this.otherHave(bgRole);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgRoleMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
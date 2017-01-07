package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRole;

@Service("bgRoleService")
public class BgRoleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 修改 
	 * @param BgRole bgRole
	 * @throws Exception
	 */
	public void change(BgRole bgRole) throws Exception {
		dao.edit("BgRoleMapper.change", bgRole);
	}
	
	
	/**
	 * 根据parentId 获取所有直接子菜单 
	 * @param int parentId
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> listSubBgRoleByParentId(int parentId) throws Exception {
		return (List<BgRole>) dao.findForList("BgRoleMapper.listSubBgRoleByParentId", parentId);
	}
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgRole bgRole
	 * @return 主键 id
	 * @throws Exception
	 */
	public int add(BgRole bgRole) throws Exception {
		return (int)dao.add("BgRoleMapper.add", bgRole);
	}
	
	/**
	 * 新增
	 * @param PageData pd
	 * @return 主键 id
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("BgRoleMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param BgRole bgRole
	 * @throws Exception
	 */
	public void edit(BgRole bgRole) throws Exception {
		dao.edit("BgRoleMapper.edit", bgRole);
	}

	/**
	 * 修改 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("BgRoleMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param int id
	 * @throws Exception
	 */
	@Transactional
	public void deleteById(int id) throws Exception {
		dao.delete("BgRoleMapper.deleteById", id);
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
	public void batchDeleteByIds(String ids) throws Exception {
		dao.delete("BgRoleMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param int id
	 * @return BgRole
	 * @throws Exception
	 */
	public BgRole findById(int id) throws Exception {
		return (BgRole) dao.findForObject("BgRoleMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param int id
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("BgRoleMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgRoleMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> listAllByPd(PageData pd) throws Exception {
		return (List<BgRole>) dao.findForList("BgRoleMapper.listAllByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgRoleMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
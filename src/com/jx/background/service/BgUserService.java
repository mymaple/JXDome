package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgUser;

@Service("bgUserService")
public class BgUserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 更新用户IP
	 */
	public void changeLoginInfo(BgUser bgUser) throws Exception {
		dao.edit("BgUserMapper.changeLoginInfo", bgUser);
	}
	
	/**
	 * 登录判断
	 */
	public BgUser checkUserLogin(PageData pd) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.checkUserLogin", pd);
	}
	
	/**
	 * 通过userId获取用户角色
	 */
	public BgUser getUserRoleById(int userId) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.getUserRoleById", userId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgUser> listAllByRoleId(int roleId) throws Exception {
		return (List<BgUser>) dao.findForList("BgUserMapper.listAllByRoleId", null);
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgUser bgUser
	 * @return 主键 id
	 * @throws Exception
	 */
	public int add(BgUser bgUser) throws Exception {
		return (int)dao.add("BgUserMapper.add", bgUser);
	}
	
	/**
	 * 新增
	 * @param PageData pd
	 * @return 主键 id
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("BgUserMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param BgUser bgUser
	 * @throws Exception
	 */
	public void edit(BgUser bgUser) throws Exception {
		dao.edit("BgUserMapper.edit", bgUser);
	}

	/**
	 * 修改 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("BgUserMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param int id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("BgUserMapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgUserMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String ids) throws Exception {
		dao.delete("BgUserMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param int id
	 * @return BgUser
	 * @throws Exception
	 */
	public BgUser findById(int id) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param int id
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("BgUserMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgUserMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgUser> listAllByPd(PageData pd) throws Exception {
		return (List<BgUser>) dao.findForList("BgUserMapper.listAllByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgUserMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
package com.jx.common.service.Impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.exception.UncheckedException;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.RandomUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComAppUserRole;
import com.jx.common.service.ComAppUserRoleService;
import com.jx.common.service.ComAppUserService;

@Service("comAppUserRoleService")
public class ComAppUserRoleServiceImpl implements ComAppUserRoleService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	
	/****************************custom * start***********************************/
	
	
	
	/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComAppUserRole> parentList, String pId) throws Exception {
		if("0".equals(pId)) return;
		ComAppUserRole comAppUserRole = this.findById(pId);
		comAppUserRole.setSubComAppUserRolePath("background/appUserRole/list.do?pId="+pId);
		parentList.add(0, comAppUserRole);
		this.getParentList(parentList, comAppUserRole.getParentId());
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUserRole> listByParentId(String parentId) throws Exception {
		return (List<ComAppUserRole>) dao.findForList("ComAppUserRoleMapper.listByParentId", parentId);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String appUserRoleId, String appUserRoleIds
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUserRole> listInRankCheck(String appUserRoleId, String appUserRoleIds) throws Exception {
		List<ComAppUserRole> comAppUserRoleList = this.listByParentId(appUserRoleId);
		for(ComAppUserRole comAppUserRole : comAppUserRoleList){
			comAppUserRole.setSubComAppUserRolePath("background/appUserRole/list.do?pId="+comAppUserRole.getAppUserRoleId());
			comAppUserRole.setSubComAppUserRoleList(this.listInRankCheck(comAppUserRole.getAppUserRoleId(), appUserRoleIds));
			comAppUserRole.setTarget("treeFrame");
			comAppUserRole.setHasAppUserRole(Arrays.asList(appUserRoleIds.split(",")).contains(comAppUserRole.getAppUserRoleId()));
		}
		return comAppUserRoleList;
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String appUserRoleId, String appUserRoleIds
	 * @return
	 * @throws Exception
	 */
	public void listInParentRankByE(List<ComAppUserRole> comAppUserRoleList, String parentId) throws Exception {
		if("0".equals(parentId)) return; 	
		ComAppUserRole comAppUserRoleParent = this.findById(parentId);
		if(comAppUserRoleParent == null){
			throw new UncheckedException("40001", null, "角色查询失败");
		}else{
			comAppUserRoleParent.setComAppUserList(comAppUserService.listInRoleIdE(parentId));
			comAppUserRoleList.add(0, comAppUserRoleParent);
			if(!"0".equals(comAppUserRoleParent.getParentId())){
				listInParentRankByE(comAppUserRoleList, comAppUserRoleParent.getParentId());
			}
		}
	}
	
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String appUserRoleId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String appUserRoleId) throws Exception {
		this.deleteById(appUserRoleId);
		List<ComAppUserRole> comAppUserRoleList = this.listByParentId(appUserRoleId);
		for(ComAppUserRole comAppUserRole : comAppUserRoleList){
			this.deleteInRank(comAppUserRole.getAppUserRoleId());
		}
	}
	
	/**
	 * 批量删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void batchDeleteInRank(String[] ids) throws Exception {
		for(String id : ids){
			this.deleteInRank(id);
		}
	}
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComAppUserRole comAppUserRole
	 * @throws Exception
	 */
	public void add(ComAppUserRole comAppUserRole) throws Exception {
		
		Date nowTime = new Date();
		String appUserRoleId = UuidUtil.get32UUID();
		comAppUserRole.setAppUserRoleId(appUserRoleId);
		comAppUserRole.setEffective("01");
		comAppUserRole.setCreateUserId(ShiroSessionUtil.getUserId());
		comAppUserRole.setCreateTime(nowTime);
		comAppUserRole.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUserRole.setModifyTime(nowTime);
		
		dao.add("ComAppUserRoleMapper.add", comAppUserRole);
		
		this.updateCode(appUserRoleId);
		
		if("03".equals(comAppUserRole.getAppUserRoleType())){
			ComAppUserRole comAppUserRole1 = new ComAppUserRole();
			comAppUserRole1.setParentId(comAppUserRole.getAppUserRoleId());
			comAppUserRole1.setAppUserRoleName(comAppUserRole.getAppUserRoleName()+"销售员");
			comAppUserRole1.setAppUserRoleType("04");
			comAppUserRole1.setAppUserRoleStatus(comAppUserRole.getAppUserRoleStatus());
			comAppUserRole1.setRemarks(comAppUserRole1.getAppUserRoleName());
			comAppUserRole1.setOrderNum(""+nowTime.getTime());
			this.add(comAppUserRole1);
		}
		
	}
	
	/**
	 * 生成code 
	 * @param String appUserRoleId
	 * @throws Exception
	 */
	public void updateCode(String appUserRoleId) throws Exception {
		//生成固定id
		PageData pd = new PageData();
		pd.put("appUserRoleId", appUserRoleId);
		pd.put("startC", "AUR");
		pd.put("startN", 100001);
		pd.put("addValue", RandomUtil.getRandomRange(11, 20));
		dao.update("ComAppUserRoleMapper.updateCode", pd);
	}	
	
	/**
	 * 修改 
	 * @param ComAppUserRole comAppUserRole
	 * @throws Exception
	 */
	public void edit(ComAppUserRole comAppUserRole) throws Exception {
	
		Date nowTime = new Date();
		comAppUserRole.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUserRole.setModifyTime(nowTime);
	
		dao.update("ComAppUserRoleMapper.edit", comAppUserRole);
	}
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String appUserRoleId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String appUserRoleId) throws Exception {
		ComAppUserRole comAppUserRole = new ComAppUserRole();
		if("00".equals(flag)){
			comAppUserRole.setOldValue("01");
		}else if("01".equals(flag)){
			comAppUserRole.setOldValue("00");
		}else{
			comAppUserRole.setOldValue("flag");
		}
		comAppUserRole.setAppUserRoleStatus(flag);
		
		comAppUserRole.setAppUserRoleId(appUserRoleId);
		Date nowTime = new Date();
		comAppUserRole.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUserRole.setModifyTime(nowTime);
		dao.update("ComAppUserRoleMapper.changeStatus", comAppUserRole);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String appUserRoleId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String appUserRoleId) throws Exception {
		ComAppUserRole comAppUserRole = new ComAppUserRole();
		if("00".equals(flag)){
			comAppUserRole.setOldValue("01");
		}else if("01".equals(flag)){
			comAppUserRole.setOldValue("00");
		}else{
			comAppUserRole.setOldValue("flag");
		}
		comAppUserRole.setEffective(flag);
		
		comAppUserRole.setAppUserRoleId(appUserRoleId);
		Date nowTime = new Date();
		comAppUserRole.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUserRole.setModifyTime(nowTime);
		dao.update("ComAppUserRoleMapper.changeEffective", comAppUserRole);
	}
	
	/**
	 * 删除 
	 * @param String appUserRoleId
	 * @throws Exception
	 */
	public void deleteById(String appUserRoleId) throws Exception {
		dao.delete("ComAppUserRoleMapper.deleteById", appUserRoleId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComAppUserRoleMapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String appUserRoleId
	 * @return ComAppUserRole
	 * @throws Exception
	 */
	public ComAppUserRole findById(String appUserRoleId) throws Exception {
		return (ComAppUserRole) dao.findForObject("ComAppUserRoleMapper.findById", appUserRoleId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUserRole> listAll() throws Exception {
		return (List<ComAppUserRole>) dao.findForList("ComAppUserRoleMapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUserRole> otherHaveCode(String appUserRoleId, String appUserRoleCode) throws Exception {
		ComAppUserRole comAppUserRole = new ComAppUserRole();
		comAppUserRole.setAppUserRoleId(appUserRoleId);
		comAppUserRole.setAppUserRoleCode(appUserRoleCode);
		return (List<ComAppUserRole>) dao.findForList("ComAppUserRoleMapper.otherHaveCode", comAppUserRole);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComAppUserRoleMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
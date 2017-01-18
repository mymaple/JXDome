package com.jx.background.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.background.entity.BgUser;
import com.jx.background.service.BgUserService;
import com.jx.background.util.BgSessionUtil;

@Service("bgUserService")
public class BgUserServiceImpl implements BgUserService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 通过loginName获取(类)数据
	 * @param String loginName
	 * @return BgUser
	 * @throws Exception
	 */
	public BgUser findByLoginName(String loginName) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.findByLoginName", loginName);
	}
	
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
	 * @throws Exception
	 */
	public void add(BgUser bgUser) throws Exception {
		
		bgUser.setPassword(new SimpleHash("SHA-512", bgUser.getUserCode(), bgUser.getPassword(),2).toString());
		
		Date nowTime = new Date();
		bgUser.setUserId(UuidUtil.get32UUID());
		bgUser.setUserStatus("00");
		bgUser.setUserIconSrc("static/ace/avatars/user.jpg");
		bgUser.setEffective("01");
		bgUser.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgUser.setCreateTime(nowTime);
		bgUser.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgUser.setModifyTime(nowTime);
		
		dao.add("BgUserMapper.add", bgUser);
	}
	
	/**
	 * 修改 
	 * @param BgUser bgUser
	 * @throws Exception
	 */
	public void edit(BgUser bgUser) throws Exception {
		Date nowTime = new Date();
		bgUser.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgUser.setModifyTime(nowTime);
		bgUser.setLastModifyTime(this.findById(bgUser.getUserId()).getModifyTime());
		if(bgUser.getModifyTime().compareTo(bgUser.getLastModifyTime()) == 0){
			bgUser.setModifyTime(MapleDateUtil.getNextSecond(bgUser.getModifyTime()));
		}
	
		dao.edit("BgUserMapper.edit", bgUser);
	}
	
	/**
	 * 更改
	 * @param BgUser bgUser
	 * @throws Exception
	 */
	public void change(BgUser bgUser) throws Exception {
		Date nowTime = new Date();
		bgUser.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgUser.setModifyTime(nowTime);
		bgUser.setLastModifyTime(this.findById(bgUser.getUserId()).getModifyTime());
		if(bgUser.getModifyTime().compareTo(bgUser.getLastModifyTime()) == 0){
			bgUser.setModifyTime(MapleDateUtil.getNextSecond(bgUser.getModifyTime()));
		}
		dao.edit("BgUserMapper.change", bgUser);
	}

	/**
	 * 删除 
	 * @param String userId
	 * @throws Exception
	 */
	public void deleteById(String userId) throws Exception {
		PageData pd = new PageData();
		pd.put("userId",userId);
		this.deleteByPd(pd);
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
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("BgUserMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String userId
	 * @return BgUser
	 * @throws Exception
	 */
	public BgUser findById(String userId) throws Exception {
		PageData pd = new PageData();
		pd.put("userId",userId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(BgUser)数据 
	 * @param PageData pd
	 * @return BgUser
	 * @throws Exception
	 */
	public BgUser findByPd(PageData pd) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgUser> listByPd(PageData pd) throws Exception {
		return (List<BgUser>) dao.findForList("BgUserMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgUser> otherHave(BgUser bgUser) throws Exception {
		return (List<BgUser>) dao.findForList("BgUserMapper.otherHave", bgUser);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgUser> otherHaveCode(String userId, String userCode) throws Exception {
		BgUser bgUser = new BgUser();
		bgUser.setUserId(userId);
		bgUser.setUserCode(userCode);
		return this.otherHave(bgUser);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgUserMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
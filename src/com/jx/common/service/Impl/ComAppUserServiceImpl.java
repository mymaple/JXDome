package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.Const;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.util.HttpManager;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.PathUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.util.WxConnUtil;

import net.sf.json.JSONObject;

import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComAppUserExt;
import com.jx.common.entity.ComInvite;
import com.jx.common.entity.UserInfo;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComAppUserService;
import com.jx.common.service.ComInviteService;
import com.jx.background.util.BgSessionUtil;

@Service("comAppUserService")
public class ComAppUserServiceImpl implements ComAppUserService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name = "comAppUserExtService")
	private ComAppUserExtService comAppUserExtService;
	@Resource(name = "comInviteService")
	private ComInviteService comInviteService;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 通过phone获取(类)数据
	 * @param String phone
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findByPhone(String phone) throws Exception {
		PageData pd = new PageData();
		pd.put("phone",phone);
		return this.findByPd(pd);
	}
	
	/**
	 * 微信注册
	 * @param String openId
	 * @param String phone
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser wxRegister(String openId, String phone) throws Exception {
		ComAppUser comAppUser = new ComAppUser();
		Date nowtime = new Date();
		//获取个人用户信息
		String json = WxConnUtil.getUserInfo(openId);
		UserInfo userInfo = new UserInfo();
		userInfo = (UserInfo)MapleUtil.convertJson(userInfo.getClass(), JSONObject.fromObject(json));
		String fileSrc = PathUtil.getProjectPath() + Const.PATH_MYHEADIMG + "/"+ openId+"_headimg.jpg";
		//下载头像图片
		if(StringUtils.isNotEmpty(userInfo.getHeadimgurl())){
			HttpManager.download(userInfo.getHeadimgurl(), null, fileSrc);
		}else{
			String headimgDefault = PathUtil.getProjectPath() + Const.PATH_MYHEADIMG + "/default_headimg.jpg";
			MapleFileUtil.copyFile(headimgDefault , fileSrc);
		}
		
		//
		ComInvite comInvite = comInviteService.findByInvitedUserId(comAppUser.getOpenId());
		if(comInvite != null){
			comAppUser.setParentId(comInvite.getInviteCode());
			
			comInvite.setInviteStatus("00");
			comInviteService.edit(comInvite);
		}
		String parentId = comInvite == null ? "" : comInvite.getInviteUserId();
		
		
		//生成个人编号
		String appUserNum = "";
		
		comAppUser.setOpenId(openId);
		comAppUser.setPhone(phone);
		
		comAppUser.setAppUserCode(StringUtils.isEmpty(userInfo.getNickname())?appUserNum:userInfo.getNickname());
		comAppUser.setSex("1".equals(userInfo.getSex())?"01":"02");
		comAppUser.setBrithday(nowtime);
		comAppUser.setWxQRcodeExpiry(nowtime);
		comAppUser.setMediaExpiry(nowtime);
		comAppUser.setParentId(parentId);
		comAppUser.setHeadImgSrc(Const.PATH_MYHEADIMG+"/"+openId+"_headimg.jpg");
		comAppUser.setOrderNum(""+nowtime.getTime());
		comAppUser.setAppUserNum(appUserNum);
		
		//微信关注
		comAppUser.setAppUserStatus("01");
		//自主微信关注用户
		comAppUser.setAppUserType("01");
		comAppUser.setCreateUserId(openId);
		comAppUser.setModifyUserId(openId);
		
		this.add(comAppUser);
		
		return comAppUser;
	}
	
		/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComAppUser> parentList, String pId) throws Exception {
		if("0".equals(pId)) return;
		ComAppUser comAppUser = this.findById(pId);
		comAppUser.setSubComAppUserPath("background/appUser/list.do?pId="+pId);
		parentList.add(0, comAppUser);
		this.getParentList(parentList, comAppUser.getParentId());
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> listByParentId(String parentId) throws Exception {
		PageData pd = new PageData();
		pd.put("parentId",parentId);
		return this.listByPd(pd);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String appUserId
	 * @return
	 * @throws Exception
	 */
	public List<ComAppUser> listInRank(String appUserId) throws Exception {
		List<ComAppUser> comAppUserList = this.listByParentId(appUserId);
		for(ComAppUser comAppUser : comAppUserList){
			comAppUser.setSubComAppUserPath("background/appUser/list.do?pId="+comAppUser.getAppUserId());
			comAppUser.setSubComAppUserList(this.listInRank(comAppUser.getAppUserId()));
			comAppUser.setTarget("treeFrame");
		}
		return comAppUserList;
	}
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String appUserId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String appUserId) throws Exception {
		this.deleteById(appUserId);
		List<ComAppUser> comAppUserList = this.listByParentId(appUserId);
		for(ComAppUser comAppUser : comAppUserList){
			this.deleteInRank(comAppUser.getAppUserId());
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
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void add(ComAppUser comAppUser) throws Exception {
		
		comAppUser.setPassword(new SimpleHash("SHA-512", comAppUser.getAppUserId(), comAppUser.getPassword(), 2).toString());
		
		Date nowTime = new Date();
		comAppUser.setAppUserId(UuidUtil.get32UUID());
		comAppUser.setAppUserStatus("00");
		comAppUser.setHeadImgSrc("");
		comAppUser.setEffective("01");
		comAppUser.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comAppUser.setCreateTime(nowTime);
		comAppUser.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comAppUser.setModifyTime(nowTime);
		
		dao.add("ComAppUserMapper.add", comAppUser);
		
		comAppUserExtService.init(comAppUser.getAppUserId());
		
	}
	
	/**
	 * 修改 
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void edit(ComAppUser comAppUser) throws Exception {
		if(StringUtils.isNotEmpty(comAppUser.getPassword())){
			comAppUser.setPassword(new SimpleHash("SHA-512", comAppUser.getAppUserId(), comAppUser.getPassword(), 2).toString());
		}
		Date nowTime = new Date();
		comAppUser.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
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
		if(StringUtils.isNotEmpty(comAppUser.getPassword())){
			comAppUser.setPassword(new SimpleHash("SHA-512", comAppUser.getAppUserId(), comAppUser.getPassword(), 2).toString());
		}
		
		Date nowTime = new Date();
		comAppUser.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
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
		comAppUserExtService.delete(appUserId);
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
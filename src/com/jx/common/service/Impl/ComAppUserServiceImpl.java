package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.HttpManager;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.PathUtil;
import com.jx.common.util.RandomUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.util.WxConnUtil;

import net.sf.json.JSONObject;

import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComAppUserExt;
import com.jx.common.entity.ComIntegralNote;
import com.jx.common.entity.ComInvite;
import com.jx.common.entity.UserInfo;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComAppUserService;
import com.jx.common.service.ComIntegralNoteService;
import com.jx.common.service.ComInviteService;

@Service("comAppUserService")
public class ComAppUserServiceImpl implements ComAppUserService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name = "comAppUserExtService")
	private ComAppUserExtService comAppUserExtService;
	@Resource(name = "comInviteService")
	private ComInviteService comInviteService;
	@Resource(name = "comIntegralNoteService")
	private ComIntegralNoteService comIntegralNoteService;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 
	 * @param String roleId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUser> listSubUser1(String appUserId) throws Exception {
		return (List<ComAppUser>) dao.findForList("ComAppUserMapper.listSubUser1", appUserId);
	}
	
	
	/**
	 * 
	 * @param String roleId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUser> listInRoleIdE(String roleId) throws Exception {
		PageData pd = new PageData();
		pd.put("roleId",roleId);
		return (List<ComAppUser>) dao.findForList("ComAppUserMapper.listInRoleIdE", pd);
	}
	
	
	/**
	 * 转账
	 * @param String appUserId, String parentId
	 * @throws Exception
	 */
	public void toTransferByUE(String fromAppUserId, String toAppUserId, double integralCount) throws Exception {
		
		//支付记录
		ComIntegralNote comIntegralNote = new ComIntegralNote();
		comIntegralNote.setIntegralNoteCode("");
		comIntegralNote.setIntegralDealCount(""+integralCount);
		
		comIntegralNote.setIntegralNoteType("03");
		comIntegralNote.setIntegralDealStatus("00");
		comIntegralNote.setIntegralNoteName(this.findById(toAppUserId).getAppUserName()+"-转赠消耗");
		comIntegralNote.setAppUserId(fromAppUserId);
		comIntegralNoteService.add(comIntegralNote);
		comAppUserExtService.addValue(fromAppUserId, ComAppUserExt.INTEGRALCOUNT, "-"+integralCount);
		
		
		comIntegralNote.setIntegralNoteType("04");
		comIntegralNote.setIntegralDealStatus("01");
		comIntegralNote.setIntegralNoteName(this.findById(fromAppUserId).getAppUserName()+"-转赠获得");
		comIntegralNote.setAppUserId(toAppUserId);
		comIntegralNoteService.add(comIntegralNote);
		comAppUserExtService.addValue(toAppUserId, ComAppUserExt.INTEGRALCOUNT, ""+integralCount);
	}
	
	/**
	 * 修改 上级
	 * @param String appUserId, String parentId
	 * @throws Exception
	 */
	public void changeParentByU(String appUserId, String parentId) throws Exception {
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setAppUserId(appUserId);
		comAppUser.setParentId(StringUtils.isEmpty(parentId)?"0":parentId);
		Date nowTime = new Date();
		comAppUser.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUser.setModifyTime(nowTime);
	
		dao.update("ComAppUserMapper.changeParentByU", comAppUser);
	}
	
	/**
	 * 修改 角色
	 * @param String appUserId, String roleId
	 * @throws Exception
	 */
	public void changeRoleByU(String appUserId, String roleId) throws Exception {
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setAppUserId(appUserId);
		comAppUser.setRoleId(roleId);
		
		Date nowTime = new Date();
		comAppUser.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUser.setModifyTime(nowTime);
	
		dao.update("ComAppUserMapper.changeRoleByU", comAppUser);
	}
	
	
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
	 * 通过appUserCode获取(类)数据
	 * @param String appUserCode
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findByCode(String appUserCode) throws Exception {
		PageData pd = new PageData();
		pd.put("appUserCode",appUserCode);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过appUserCode获取(类)数据
	 * @param String appUserCode
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser findByRemarks(String remarks) throws Exception {
		PageData pd = new PageData();
		pd.put("remarks",remarks);
		return this.findByPd(pd);
	}
	
	/**
	 * 微信注册
	 * @param String phone, ComInvite comInvite
	 * @return ComAppUser
	 * @throws Exception
	 */
	public ComAppUser toWxRegister(String phone, ComInvite comInvite) throws Exception {
		
		String openId = comInvite.getInvitedUserId();
		String appUserId = UuidUtil.get32UUID();
		
		ComAppUser comAppUser = new ComAppUser();
		Date nowTime = new Date();
		//获取个人用户信息
		String json = WxConnUtil.getUserInfo(openId);
		UserInfo userInfo = new UserInfo();
		userInfo = (UserInfo)MapleUtil.convertJson(userInfo.getClass(), JSONObject.fromObject(json));
		String fileSrc = PathUtil.getProjectPath() + ComAppUser.PATH_IMG_AU_HEADIMG + "/"+ appUserId+"_headimg.jpg";
		//下载头像图片
		if(StringUtils.isNotEmpty(userInfo.getHeadimgurl())){
			HttpManager.download(userInfo.getHeadimgurl(), null, fileSrc);
		}else{
			String headimgDefault = PathUtil.getProjectPath() + ComAppUser.PATH_IMG_AU_HEADIMG + "/default_headimg.jpg";
			MapleFileUtil.copyFile(headimgDefault , fileSrc);
		}
		
		
		comAppUser.setPhone(phone);
		comAppUser.setAppUserNum("");
		comAppUser.setAppUserCode("");
		String appUserName = StringUtils.isEmpty(userInfo.getNickname())?
				"小伙伴":MapleStringUtil.filterOffUtf8Mb4(userInfo.getNickname());
		comAppUser.setAppUserName(appUserName);
		
		comAppUser.setSex("2".equals(userInfo.getSex())?"02":"01");
		comAppUser.setBrithday(nowTime);
		comAppUser.setHeadImgSrc(ComAppUser.PATH_IMG_AU_HEADIMG+"/"+appUserId+"_headimg.jpg");
		comAppUser.setOrderNum(""+nowTime.getTime());
	
		comAppUser.setPassword("");
		comAppUser.setRemarks("");
		
		//微信关注中
		comAppUser.setAppUserStatus("01");
		//自主微信关注用户
		comAppUser.setAppUserType("02");
		comAppUser.setEffective("01");
		
		comAppUser.setAppUserId(appUserId);
		
		comAppUser.setCreateUserId(appUserId);
		comAppUser.setCreateTime(nowTime);
		comAppUser.setModifyUserId(appUserId);
		comAppUser.setModifyTime(nowTime);
		
		String parentId = comInvite.getInviteUserId();
		comAppUser.setParentId(parentId);
		ComAppUser comAppUser1 = this.findById(parentId);
		int level = Integer.parseInt(comAppUser1.getLevel())+1;
		comAppUser.setLevel(""+level);
		comAppUser.setRoleId("0"+level);
		
		dao.add("ComAppUserMapper.add", comAppUser);
		
		this.updateCode(appUserId);
		comAppUserExtService.toInit(appUserId, "0", MapleDateUtil.formatDate(nowTime)
				, "", MapleDateUtil.formatDate(nowTime), "", openId);
		
		//完成绑定
		comInvite.setInviteStatus("01");
		comInvite.setInvitedUserId(appUserId);
		comInvite.setModifyUserId(appUserId);
		comInvite.setModifyTime(nowTime);
		comInviteService.toSuccess(comInvite);
		
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
	public List<ComAppUser> listInRankCheck(String appUserId, String check) throws Exception {
		List<ComAppUser> comAppUserList = this.listByParentId(appUserId);
		for(ComAppUser comAppUser : comAppUserList){
			comAppUser.setSubComAppUserPath("background/appUser/list.do?pId="+comAppUser.getAppUserId());
			comAppUser.setSubComAppUserList(this.listInRankCheck(comAppUser.getAppUserId(), check));
			comAppUser.setTarget("treeFrame");
			comAppUser.setHasAppUser(check.equals(comAppUser.getAppUserId()));
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
	
	/**
	 * 修改 
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void changeMyInfoByUE(ComAppUser comAppUser) throws Exception {
		Date nowTime = new Date();
		comAppUser.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUser.setModifyTime(nowTime);
	
		dao.update("ComAppUserMapper.changeMyInfoByUE", comAppUser);
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComAppUser comAppUser
	 * @throws Exception
	 */
	public void add(ComAppUser comAppUser) throws Exception {
		
		String appUserId = UuidUtil.get32UUID();
		Date nowTime = new Date();
		
		comAppUser.setAppUserNum("");
		comAppUser.setPassword(new SimpleHash("SHA-512", appUserId, comAppUser.getPassword(), 2).toString());
		comAppUser.setHeadImgSrc("static/ace/avatars/user.jpg");
		
		
		comAppUser.setAppUserStatus("00");
		comAppUser.setEffective("01");	
		
		
		comAppUser.setAppUserId(appUserId);
		
		comAppUser.setCreateUserId(ShiroSessionUtil.getUserId());
		comAppUser.setCreateTime(nowTime);
		comAppUser.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUser.setModifyTime(nowTime);
		
		String parentId = comAppUser.getParentId();
		comAppUser.setParentId(parentId);
		ComAppUser comAppUser1 = this.findById(parentId);
		int level = "0".equals(parentId)?1:(Integer.parseInt(comAppUser1.getLevel())+1);
		comAppUser.setLevel(""+level);
		
		dao.add("ComAppUserMapper.add", comAppUser);
		
		this.updateCode(appUserId);
		comAppUserExtService.toInit(appUserId, "0", MapleDateUtil.formatDate(nowTime)
				, "", MapleDateUtil.formatDate(nowTime), "", "");
	}
	
	/**
	 * 生成code 
	 * @param String appUserId
	 * @throws Exception
	 */
	public void updateCode(String appUserId) throws Exception {
		//生成固定id
		PageData pd = new PageData();
		pd.put("appUserId", appUserId);
		pd.put("startC", "AU");
		pd.put("startN", 100001);
		pd.put("addValue", RandomUtil.getRandomRange(11, 20));
		dao.update("ComAppUserMapper.updateCode", pd);
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
		comAppUser.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUser.setModifyTime(nowTime);
		comAppUser.setLastModifyTime(this.findById(comAppUser.getAppUserId()).getModifyTime());
		if(comAppUser.getModifyTime().compareTo(comAppUser.getLastModifyTime()) == 0){
			comAppUser.setModifyTime(MapleDateUtil.getNextSecond(comAppUser.getModifyTime()));
		}
	
		dao.update("ComAppUserMapper.edit", comAppUser);
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
		comAppUser.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUser.setModifyTime(nowTime);
		comAppUser.setLastModifyTime(this.findById(comAppUser.getAppUserId()).getModifyTime());
		if(comAppUser.getModifyTime().compareTo(comAppUser.getLastModifyTime()) == 0){
			comAppUser.setModifyTime(MapleDateUtil.getNextSecond(comAppUser.getModifyTime()));
		}
		dao.update("ComAppUserMapper.change", comAppUser);
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
		for (int i = 0; i < ids.length; i++) {
			comAppUserExtService.delete(ids[i]);
		}
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
	@SuppressWarnings("unchecked")
	public List<ComAppUser> otherHaveCode(String appUserId, String appUserCode) throws Exception {
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setAppUserId(appUserId);
		comAppUser.setAppUserCode(appUserCode);
		return (List<ComAppUser>) dao.findForList("ComAppUserMapper.otherHave", comAppUser);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUser> otherHavePhone(String appUserId, String phone) throws Exception {
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setAppUserId(appUserId);
		comAppUser.setPhone(phone);
		return (List<ComAppUser>) dao.findForList("ComAppUserMapper.otherHave", comAppUser);
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
package com.jx.common.service.Impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.exception.UncheckedException;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComAppUserExt;
import com.jx.common.service.ComAppUserExtService;
import com.jx.background.util.BgSessionUtil;

@Service("comAppUserExtService")
public class ComAppUserExtServiceImpl implements ComAppUserExtService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/****************************custom * start***********************************/
	/**
	 * 初始化扩展属性
	 * @param String appUserId
	 * @throws Exception
	 */
	public void init(String appUserId) throws Exception {
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setExtValue("");
		comAppUserExt.setAppUserId(appUserId);
		
		comAppUserExt.setExtCode(ComAppUserExt.INTEGRALCOUNT);
		comAppUserExt.setExtName("积分数");
		this.add(comAppUserExt);
		comAppUserExt.setExtCode(ComAppUserExt.MEDIAEXPIRY);
		comAppUserExt.setExtName("媒体文件有效时间");
		this.add(comAppUserExt);
		comAppUserExt.setExtCode(ComAppUserExt.MEDIAID);
		comAppUserExt.setExtName("媒体文件id");
		this.add(comAppUserExt);
		comAppUserExt.setExtCode(ComAppUserExt.WXQRCODEEXPIRY);
		comAppUserExt.setExtName("微信二维码有效期");
		this.add(comAppUserExt);
		comAppUserExt.setExtCode(ComAppUserExt.WXQRCODESRC);
		comAppUserExt.setExtName("微信二维码");
		this.add(comAppUserExt);

	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComAppUserExt comAppUserExt
	 * @throws Exception
	 */
	public void add(ComAppUserExt comAppUserExt) throws Exception {
		
		if(this.find(comAppUserExt.getAppUserId(), comAppUserExt.getExtCode())!=null){
			
		}
		Date nowTime = new Date();
		
		comAppUserExt.setAppUserExtId(UuidUtil.get32UUID());
		comAppUserExt.setOrderNum(""+nowTime.getTime());
		comAppUserExt.setEffective("01");
		comAppUserExt.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comAppUserExt.setCreateTime(nowTime);
		comAppUserExt.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comAppUserExt.setModifyTime(nowTime);
		
		dao.add("ComAppUserExtMapper.add", comAppUserExt);
	}
	
	/**
	 * 加减改值
	 * @param String appUserId, String extCode, String changeValue
	 * @throws Exception
	 */
	public void addValue(String appUserId, String extCode, String addValue) throws Exception {
		if(StringUtils.isEmpty(appUserId)
				|| StringUtils.isEmpty(extCode)
				|| StringUtils.isEmpty(addValue)
				){
			throw new UncheckedException("", null, "参数不足");
		}
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		comAppUserExt.setExtCode(extCode);
		comAppUserExt.setExtValue(addValue);
		
		Date nowTime = new Date();
		comAppUserExt.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comAppUserExt.setModifyTime(nowTime);
		if(comAppUserExt.getLastModifyTime() == null){
			comAppUserExt.setLastModifyTime(this.find(appUserId, extCode).getModifyTime());
		}
		if(comAppUserExt.getModifyTime().compareTo(comAppUserExt.getLastModifyTime()) == 0){
			comAppUserExt.setModifyTime(MapleDateUtil.getNextSecond(comAppUserExt.getModifyTime()));
		}
		dao.edit("ComAppUserExtMapper.changeValue", comAppUserExt);
	}
	
	/**
	 * 更改
	 * @param String appUserId, String extCode, String changeValue
	 * @throws Exception
	 */
	public void changeValue(String appUserId, String extCode, String changeValue) throws Exception {
		if(StringUtils.isEmpty(appUserId)
				|| StringUtils.isEmpty(extCode)
				|| StringUtils.isEmpty(changeValue)
				){
			throw new UncheckedException("", null, "参数不足");
		}
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		comAppUserExt.setExtCode(extCode);
		comAppUserExt.setExtValue(changeValue);
		
		Date nowTime = new Date();
		comAppUserExt.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comAppUserExt.setModifyTime(nowTime);
		if(comAppUserExt.getLastModifyTime() == null){
			comAppUserExt.setLastModifyTime(this.find(appUserId, extCode).getModifyTime());
		}
		if(comAppUserExt.getModifyTime().compareTo(comAppUserExt.getLastModifyTime()) == 0){
			comAppUserExt.setModifyTime(MapleDateUtil.getNextSecond(comAppUserExt.getModifyTime()));
		}
		dao.edit("ComAppUserExtMapper.changeValue", comAppUserExt);
	}

	/**
	 * 删除 
	 * @param String appUserId, String extCode
	 * @throws Exception
	 */
	public void delete(String appUserId, String extCode) throws Exception {
		PageData pd = new PageData();
		pd.put("appUserId", appUserId);
		pd.put("extCode", extCode);
		dao.delete("ComAppUserExtMapper.delete", pd);
	}
	
	/**
	 * 删除 
	 * @param String appUserId
	 * @throws Exception
	 */
	public void delete(String appUserId) throws Exception {
		PageData pd = new PageData();
		pd.put("appUserId", appUserId);
		dao.delete("ComAppUserExtMapper.delete", pd);
	}
	
	/**
	 * 获取(类)数据
	 * @param String appUserId, String extCode
	 * @return ComAppUserExt
	 * @throws Exception
	 */
	public ComAppUserExt find(String appUserId, String extCode) throws Exception {
		PageData pd = new PageData();
		pd.put("appUserId", appUserId);
		pd.put("extCode", extCode);
		return (ComAppUserExt) dao.findForObject("ComAppUserExtMapper.find", pd);
	}
	
	/****************************common * end***********************************/
}
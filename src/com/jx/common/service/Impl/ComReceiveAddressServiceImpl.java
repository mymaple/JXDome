package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.exception.UncheckedException;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComProductCategoryDetail;
import com.jx.common.entity.ComReceiveAddress;
import com.jx.common.service.ComReceiveAddressService;

@Service("comReceiveAddressService")
public class ComReceiveAddressServiceImpl implements ComReceiveAddressService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComReceiveAddress> listByUserIdE(String appUserId) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setAppUserId(appUserId);
		comReceiveAddress.setEffective("01");
		return this.list(comReceiveAddress);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String appUserId, String receiveAddressId
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findByUserIdAndIdE(String appUserId, String receiveAddressId) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setReceiveAddressId(receiveAddressId);
		comReceiveAddress.setAppUserId(appUserId);
		comReceiveAddress.setEffective("01");
		return this.find(comReceiveAddress);
	}
	
	/**
	 * 成为默认
	 * @param String appUserId, String receiveAddressId
	 * @throws Exception
	 */
	public void toDefaultE(String appUserId, String receiveAddressId) throws Exception {
		
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setAppUserId(appUserId);
		comReceiveAddress.setModifyTime(new Date());
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		dao.update("ComReceiveAddressMapper.toDisdefaultE", comReceiveAddress);
		
		comReceiveAddress.setReceiveAddressId(receiveAddressId);
		dao.update("ComReceiveAddressMapper.toDefaultE", comReceiveAddress);
	}
	
	/**
	 * 成为不默认
	 * @param String appUserId
	 * @throws Exception
	 */
	public void toDisdefaultE(String appUserId) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setAppUserId(appUserId);
		comReceiveAddress.setModifyTime(new Date());
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		try{
			dao.update("ComReceiveAddressMapper.toDisdefaultE", comReceiveAddress);
		}catch(UncheckedException e){
			
		}
	}
	
	/**
	 * 失效 
	 * @param String appUserId, String receiveAddressId
	 * @throws Exception
	 */
	public void toDisableByUserIdAndIdE(String appUserId, String receiveAddressId) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setReceiveAddressId(receiveAddressId);
		comReceiveAddress.setAppUserId(appUserId);
		comReceiveAddress.setModifyTime(new Date());
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		dao.update("ComReceiveAddressMapper.toDisableByUserIdAndIdE", comReceiveAddress);
	}
	
	/**
	 * 用户修改 
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void editByUserE(ComReceiveAddress comReceiveAddress) throws Exception {
		Date nowTime = new Date();
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		comReceiveAddress.setModifyTime(nowTime);
		
		if("01".equals(comReceiveAddress.getDefaultStatus())){
			this.toDisdefaultE(comReceiveAddress.getAppUserId());
		}
		dao.update("ComReceiveAddressMapper.editByUserE", comReceiveAddress);
	}
	
	
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void add(ComReceiveAddress comReceiveAddress) throws Exception {
		
		Date nowTime = new Date();
		comReceiveAddress.setReceiveAddressId(UuidUtil.get32UUID());
		comReceiveAddress.setEffective("01");
		comReceiveAddress.setCreateUserId(ShiroSessionUtil.getUserId());
		comReceiveAddress.setCreateTime(nowTime);
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		comReceiveAddress.setModifyTime(nowTime);
		
		if("01".equals(comReceiveAddress.getDefaultStatus())){
			this.toDisdefaultE(comReceiveAddress.getAppUserId());
		}
		dao.add("ComReceiveAddressMapper.add", comReceiveAddress);
	}
	
	/**
	 * 修改 
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void edit(ComReceiveAddress comReceiveAddress) throws Exception {
		Date nowTime = new Date();
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		comReceiveAddress.setModifyTime(nowTime);

		if("01".equals(comReceiveAddress.getDefaultStatus())){
			this.toDisdefaultE(comReceiveAddress.getAppUserId());
		}
		dao.update("ComReceiveAddressMapper.edit", comReceiveAddress);
	}
	
	/**
	 * 更改
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void change(ComReceiveAddress comReceiveAddress) throws Exception {
		Date nowTime = new Date();
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		comReceiveAddress.setModifyTime(nowTime);
		
		if("01".equals(comReceiveAddress.getDefaultStatus())){
			this.toDisdefaultE(comReceiveAddress.getAppUserId());
		}
		
		dao.update("ComReceiveAddressMapper.change", comReceiveAddress);
	}

	/**
	 * 删除 
	 * @param String receiveAddressId
	 * @throws Exception
	 */
	public void deleteById(String receiveAddressId) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setReceiveAddressId(receiveAddressId);
		this.delete(comReceiveAddress);
	}
	
	/**
	 * 删除 
	 * @param ComReceiveAddress comReceiveAddress
	 * @throws Exception
	 */
	public void delete(ComReceiveAddress comReceiveAddress) throws Exception {
		dao.delete("ComReceiveAddressMapper.delete", comReceiveAddress);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComReceiveAddressMapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 生效 
	 * @param String receiveAddressId
	 * @throws Exception
	 */
	public void toEffective(String receiveAddressId) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setReceiveAddressId(receiveAddressId);
		Date nowTime = new Date();
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		comReceiveAddress.setModifyTime(nowTime);
		dao.update("ComReceiveAddressMapper.toEffective", comReceiveAddress);
	}
	
	/**
	 * 失效
	 * @param String receiveAddressId
	 * @throws Exception
	 */
	public void toDisEffective(String receiveAddressId) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setReceiveAddressId(receiveAddressId);
		Date nowTime = new Date();
		comReceiveAddress.setModifyUserId(ShiroSessionUtil.getUserId());
		comReceiveAddress.setModifyTime(nowTime);
		dao.update("ComReceiveAddressMapper.toDisEffective", comReceiveAddress);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String receiveAddressId
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findById(String receiveAddressId) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setReceiveAddressId(receiveAddressId);
		return this.find(comReceiveAddress);
	}
	
	/**
	 * 通过pd获取(ComReceiveAddress)数据 
	 * @param ComReceiveAddress comReceiveAddress
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress find(ComReceiveAddress comReceiveAddress) throws Exception {
		return (ComReceiveAddress) dao.findForObject("ComReceiveAddressMapper.find", comReceiveAddress);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComReceiveAddress> list(ComReceiveAddress comReceiveAddress) throws Exception {
		return (List<ComReceiveAddress>) dao.findForList("ComReceiveAddressMapper.list", comReceiveAddress);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComReceiveAddress> otherHave(ComReceiveAddress comReceiveAddress) throws Exception {
		return (List<ComReceiveAddress>) dao.findForList("ComReceiveAddressMapper.otherHave", comReceiveAddress);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComReceiveAddressMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
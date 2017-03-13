package com.jx.common.service.Impl;

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
import com.jx.common.entity.ComReceiveAddress;
import com.jx.common.service.ComReceiveAddressService;
import com.jx.background.util.BgSessionUtil;

@Service("comReceiveAddressService")
public class ComReceiveAddressServiceImpl implements ComReceiveAddressService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
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
		comReceiveAddress.setLastModifyTime(this.findById(comReceiveAddress.getReceiveAddressId()).getModifyTime());
		if(comReceiveAddress.getModifyTime().compareTo(comReceiveAddress.getLastModifyTime()) == 0){
			comReceiveAddress.setModifyTime(MapleDateUtil.getNextSecond(comReceiveAddress.getModifyTime()));
		}
	
		dao.edit("ComReceiveAddressMapper.edit", comReceiveAddress);
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
		comReceiveAddress.setLastModifyTime(this.findById(comReceiveAddress.getReceiveAddressId()).getModifyTime());
		if(comReceiveAddress.getModifyTime().compareTo(comReceiveAddress.getLastModifyTime()) == 0){
			comReceiveAddress.setModifyTime(MapleDateUtil.getNextSecond(comReceiveAddress.getModifyTime()));
		}
		dao.edit("ComReceiveAddressMapper.change", comReceiveAddress);
	}

	/**
	 * 删除 
	 * @param String receiveAddressId
	 * @throws Exception
	 */
	public void deleteById(String receiveAddressId) throws Exception {
		PageData pd = new PageData();
		pd.put("receiveAddressId",receiveAddressId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComReceiveAddressMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComReceiveAddressMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String receiveAddressId
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findById(String receiveAddressId) throws Exception {
		PageData pd = new PageData();
		pd.put("receiveAddressId",receiveAddressId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComReceiveAddress)数据 
	 * @param PageData pd
	 * @return ComReceiveAddress
	 * @throws Exception
	 */
	public ComReceiveAddress findByPd(PageData pd) throws Exception {
		return (ComReceiveAddress) dao.findForObject("ComReceiveAddressMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComReceiveAddress> listByPd(PageData pd) throws Exception {
		return (List<ComReceiveAddress>) dao.findForList("ComReceiveAddressMapper.listByPd", pd);
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
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComReceiveAddress> otherHaveCode(String receiveAddressId, String receiveAddressCode) throws Exception {
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setReceiveAddressId(receiveAddressId);
		return this.otherHave(comReceiveAddress);
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
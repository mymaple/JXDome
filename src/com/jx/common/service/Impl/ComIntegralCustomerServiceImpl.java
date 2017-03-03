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
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComIntegralCustomer;
import com.jx.common.service.ComIntegralCustomerService;
import com.jx.background.util.BgSessionUtil;

@Service("comIntegralCustomerService")
public class ComIntegralCustomerServiceImpl implements ComIntegralCustomerService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	
		/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComIntegralCustomer> parentList, String pId) throws Exception {
		if("0".equals(pId)) return;
		ComIntegralCustomer comIntegralCustomer = this.findById(pId);
		comIntegralCustomer.setSubComIntegralCustomerPath("background/integralCustomer/list.do?pId="+pId);
		parentList.add(0, comIntegralCustomer);
		this.getParentList(parentList, comIntegralCustomer.getParentId());
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralCustomer> listByParentId(String parentId) throws Exception {
		PageData pd = new PageData();
		pd.put("parentId",parentId);
		return this.listByPd(pd);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String integralCustomerId
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralCustomer> listInRank(String integralCustomerId) throws Exception {
		List<ComIntegralCustomer> comIntegralCustomerList = this.listByParentId(integralCustomerId);
		for(ComIntegralCustomer comIntegralCustomer : comIntegralCustomerList){
			comIntegralCustomer.setSubComIntegralCustomerPath("background/integralCustomer/list.do?pId="+comIntegralCustomer.getIntegralCustomerId());
			comIntegralCustomer.setSubComIntegralCustomerList(this.listInRank(comIntegralCustomer.getIntegralCustomerId()));
			comIntegralCustomer.setTarget("treeFrame");
		}
		return comIntegralCustomerList;
	}
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String integralCustomerId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String integralCustomerId) throws Exception {
		this.deleteById(integralCustomerId);
		List<ComIntegralCustomer> comIntegralCustomerList = this.listByParentId(integralCustomerId);
		for(ComIntegralCustomer comIntegralCustomer : comIntegralCustomerList){
			this.deleteInRank(comIntegralCustomer.getIntegralCustomerId());
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
	 * @param ComIntegralCustomer comIntegralCustomer
	 * @throws Exception
	 */
	public void add(ComIntegralCustomer comIntegralCustomer) throws Exception {
		
		Date nowTime = new Date();
		comIntegralCustomer.setIntegralCustomerId(UuidUtil.get32UUID());
		
		comIntegralCustomer.setPassword(new SimpleHash("SHA-512", comIntegralCustomer.getIntegralCustomerCode(), comIntegralCustomer.getPassword(), 2).toString());
		comIntegralCustomer.setIntegralCustomerStatus("00");
		comIntegralCustomer.setUserIconSrc("");
		comIntegralCustomer.setEffective("01");
		comIntegralCustomer.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comIntegralCustomer.setCreateTime(nowTime);
		comIntegralCustomer.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comIntegralCustomer.setModifyTime(nowTime);
		
		dao.add("ComIntegralCustomerMapper.add", comIntegralCustomer);
	}
	
	/**
	 * 修改 
	 * @param ComIntegralCustomer comIntegralCustomer
	 * @throws Exception
	 */
	public void edit(ComIntegralCustomer comIntegralCustomer) throws Exception {
		
		if(StringUtils.isNotEmpty(comIntegralCustomer.getPassword())){
			comIntegralCustomer.setPassword(new SimpleHash("SHA-512", comIntegralCustomer.getIntegralCustomerCode(), comIntegralCustomer.getPassword(), 2).toString());
		}
		
		Date nowTime = new Date();
		comIntegralCustomer.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comIntegralCustomer.setModifyTime(nowTime);
		comIntegralCustomer.setLastModifyTime(this.findById(comIntegralCustomer.getIntegralCustomerId()).getModifyTime());
		if(comIntegralCustomer.getModifyTime().compareTo(comIntegralCustomer.getLastModifyTime()) == 0){
			comIntegralCustomer.setModifyTime(MapleDateUtil.getNextSecond(comIntegralCustomer.getModifyTime()));
		}
	
		dao.edit("ComIntegralCustomerMapper.edit", comIntegralCustomer);
	}
	
	/**
	 * 更改
	 * @param ComIntegralCustomer comIntegralCustomer
	 * @throws Exception
	 */
	public void change(ComIntegralCustomer comIntegralCustomer) throws Exception {
		if(StringUtils.isNotEmpty(comIntegralCustomer.getPassword())){
			comIntegralCustomer.setPassword(new SimpleHash("SHA-512", comIntegralCustomer.getIntegralCustomerCode(), comIntegralCustomer.getPassword(), 2).toString());
		}
		Date nowTime = new Date();
		comIntegralCustomer.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comIntegralCustomer.setModifyTime(nowTime);
		comIntegralCustomer.setLastModifyTime(this.findById(comIntegralCustomer.getIntegralCustomerId()).getModifyTime());
		if(comIntegralCustomer.getModifyTime().compareTo(comIntegralCustomer.getLastModifyTime()) == 0){
			comIntegralCustomer.setModifyTime(MapleDateUtil.getNextSecond(comIntegralCustomer.getModifyTime()));
		}
		dao.edit("ComIntegralCustomerMapper.change", comIntegralCustomer);
	}

	/**
	 * 删除 
	 * @param String integralCustomerId
	 * @throws Exception
	 */
	public void deleteById(String integralCustomerId) throws Exception {
		PageData pd = new PageData();
		pd.put("integralCustomerId",integralCustomerId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComIntegralCustomerMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComIntegralCustomerMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String integralCustomerId
	 * @return ComIntegralCustomer
	 * @throws Exception
	 */
	public ComIntegralCustomer findById(String integralCustomerId) throws Exception {
		PageData pd = new PageData();
		pd.put("integralCustomerId",integralCustomerId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComIntegralCustomer)数据 
	 * @param PageData pd
	 * @return ComIntegralCustomer
	 * @throws Exception
	 */
	public ComIntegralCustomer findByPd(PageData pd) throws Exception {
		return (ComIntegralCustomer) dao.findForObject("ComIntegralCustomerMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComIntegralCustomer> listByPd(PageData pd) throws Exception {
		return (List<ComIntegralCustomer>) dao.findForList("ComIntegralCustomerMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComIntegralCustomer> otherHave(ComIntegralCustomer comIntegralCustomer) throws Exception {
		return (List<ComIntegralCustomer>) dao.findForList("ComIntegralCustomerMapper.otherHave", comIntegralCustomer);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComIntegralCustomer> otherHaveCode(String integralCustomerId, String integralCustomerCode) throws Exception {
		ComIntegralCustomer comIntegralCustomer = new ComIntegralCustomer();
		comIntegralCustomer.setIntegralCustomerId(integralCustomerId);
		comIntegralCustomer.setIntegralCustomerCode(integralCustomerCode);
		return this.otherHave(comIntegralCustomer);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComIntegralCustomerMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
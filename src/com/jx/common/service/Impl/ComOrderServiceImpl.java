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
import com.jx.common.entity.ComOrder;
import com.jx.common.service.ComOrderService;
import com.jx.background.util.BgSessionUtil;

@Service("comOrderService")
public class ComOrderServiceImpl implements ComOrderService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void add(ComOrder comOrder) throws Exception {
		
		Date nowTime = new Date();
		comOrder.setOrderId(UuidUtil.get32UUID());
		comOrder.setOrderStatus("00");
		comOrder.setSupplierName("");
		comOrder.setPayTime(nowTime);
		comOrder.setSendTime(nowTime);
		comOrder.setEffective("01");
		comOrder.setCreateUserId(ShiroSessionUtil.getUserId());
		comOrder.setCreateTime(nowTime);
		comOrder.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrder.setModifyTime(nowTime);
		
		dao.add("ComOrderMapper.add", comOrder);
	}
	
	/**
	 * 修改 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void edit(ComOrder comOrder) throws Exception {
		Date nowTime = new Date();
		comOrder.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrder.setModifyTime(nowTime);
		comOrder.setLastModifyTime(this.findById(comOrder.getOrderId()).getModifyTime());
		if(comOrder.getModifyTime().compareTo(comOrder.getLastModifyTime()) == 0){
			comOrder.setModifyTime(MapleDateUtil.getNextSecond(comOrder.getModifyTime()));
		}
	
		dao.edit("ComOrderMapper.edit", comOrder);
	}
	
	/**
	 * 更改
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void change(ComOrder comOrder) throws Exception {
		Date nowTime = new Date();
		comOrder.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrder.setModifyTime(nowTime);
		comOrder.setLastModifyTime(this.findById(comOrder.getOrderId()).getModifyTime());
		if(comOrder.getModifyTime().compareTo(comOrder.getLastModifyTime()) == 0){
			comOrder.setModifyTime(MapleDateUtil.getNextSecond(comOrder.getModifyTime()));
		}
		dao.edit("ComOrderMapper.change", comOrder);
	}

	/**
	 * 删除 
	 * @param String orderId
	 * @throws Exception
	 */
	public void deleteById(String orderId) throws Exception {
		PageData pd = new PageData();
		pd.put("orderId",orderId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComOrderMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComOrderMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String orderId
	 * @return ComOrder
	 * @throws Exception
	 */
	public ComOrder findById(String orderId) throws Exception {
		PageData pd = new PageData();
		pd.put("orderId",orderId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComOrder)数据 
	 * @param PageData pd
	 * @return ComOrder
	 * @throws Exception
	 */
	public ComOrder findByPd(PageData pd) throws Exception {
		return (ComOrder) dao.findForObject("ComOrderMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrder> listByPd(PageData pd) throws Exception {
		return (List<ComOrder>) dao.findForList("ComOrderMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrder> otherHave(ComOrder comOrder) throws Exception {
		return (List<ComOrder>) dao.findForList("ComOrderMapper.otherHave", comOrder);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrder> otherHaveCode(String orderId, String orderCode) throws Exception {
		ComOrder comOrder = new ComOrder();
		comOrder.setOrderId(orderId);
		comOrder.setOrderCode(orderCode);
		return this.otherHave(comOrder);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComOrderMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
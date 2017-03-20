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
	
		dao.update("ComOrderMapper.edit", comOrder);
	}
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String orderId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String orderId) throws Exception {
		ComOrder comOrder = new ComOrder();
		if("00".equals(flag)){
			comOrder.setOldValue("01");
		}else if("01".equals(flag)){
			comOrder.setOldValue("00");
		}else{
			comOrder.setOldValue("flag");
		}
		comOrder.setOrderStatus(flag);
		
		comOrder.setOrderId(orderId);
		Date nowTime = new Date();
		comOrder.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrder.setModifyTime(nowTime);
		dao.update("ComOrderMapper.changeStatus", comOrder);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String orderId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String orderId) throws Exception {
		ComOrder comOrder = new ComOrder();
		if("00".equals(flag)){
			comOrder.setOldValue("01");
		}else if("01".equals(flag)){
			comOrder.setOldValue("00");
		}else{
			comOrder.setOldValue("flag");
		}
		comOrder.setEffective(flag);
		
		comOrder.setOrderId(orderId);
		Date nowTime = new Date();
		comOrder.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrder.setModifyTime(nowTime);
		dao.update("ComOrderMapper.changeEffective", comOrder);
	}
	
	/**
	 * 删除 
	 * @param String orderId
	 * @throws Exception
	 */
	public void deleteById(String orderId) throws Exception {
		dao.delete("ComOrderMapper.deleteById", orderId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
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
		return (ComOrder) dao.findForObject("ComOrderMapper.findById", orderId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrder> listAll() throws Exception {
		return (List<ComOrder>) dao.findForList("ComOrderMapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrder> otherHaveCode(String orderId, String orderCode) throws Exception {
		ComOrder comOrder = new ComOrder();
		comOrder.setOrderId(orderId);
		comOrder.setOrderCode(orderCode);
		return (List<ComOrder>) dao.findForList("ComOrderMapper.otherHaveCode", comOrder);
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
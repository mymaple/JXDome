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
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.RandomUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.util.MapleDateUtil.SDF;
import com.jx.common.entity.ComAppUserExt;
import com.jx.common.entity.ComIntegralNote;
import com.jx.common.entity.ComOrder;
import com.jx.common.entity.ComOrderDetail;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComIntegralNoteService;
import com.jx.common.service.ComOrderDetailService;
import com.jx.common.service.ComOrderService;
import com.jx.common.service.ComProductStyleService;
import com.jx.common.service.ComShopCarService;

@Service("comOrderService")
public class ComOrderServiceImpl implements ComOrderService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "comOrderDetailService")
	private ComOrderDetailService comOrderDetailService;
	@Resource(name = "comShopCarService")
	private ComShopCarService comShopCarService;
	@Resource(name = "comAppUserExtService")
	private ComAppUserExtService comAppUserExtService;
	@Resource(name = "comIntegralNoteService")
	private ComIntegralNoteService comIntegralNoteService;
	@Resource(name = "comProductStyleService")
	private ComProductStyleService comProductStyleService;
	
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 新增 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void toConfirmOrder1(ComOrder comOrder) throws Exception {
		comOrder.setOrderNum(""+new Date().getTime());
		this.add(comOrder);
		String orderId = comOrder.getOrderId();
		List<ComOrderDetail> comOrderDetailList = comOrder.getComOrderDetailList();
		for (int i = 0; i < comOrderDetailList.size(); i++) {
			ComOrderDetail comOrderDetail = comOrderDetailList.get(i);
			comOrderDetail.setOrderId(orderId);
			comOrderDetail.setOrderNum(""+new Date().getTime());
			comOrderDetailService.add(comOrderDetail);
		}
	}
	
	/**
	 * 新增 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void toConfirmOrder2(List<ComOrder> comOrderList,String[] shopCarIdArr) throws Exception {
		
		for (int i = 0; i < comOrderList.size(); i++) {
			ComOrder comOrder = comOrderList.get(i);
			this.toConfirmOrder1(comOrder);
		}
		
		for (int i = 0; i < shopCarIdArr.length; i++) {
			String shopCarId = shopCarIdArr[i];
			comShopCarService.changeStatus("02", shopCarId);
		}
	}
	
	/**
	 * 新增 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void changeReceiveAddressIdByU(String orderId, String userId, String receiveAddressId) throws Exception {
		ComOrder comOrder = new ComOrder();
		
		comOrder.setOrderId(orderId);
		comOrder.setAppUserId(userId);
		comOrder.setReceiveAddressId(receiveAddressId);
		
		Date nowTime = new Date();
		comOrder.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrder.setModifyTime(nowTime);
		dao.update("ComOrderMapper.changeReceiveAddressIdByU", comOrder);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrder> listByOrderIdsSED(String orderStatus, String appUserId, String[] orderIdArr) throws Exception {
		PageData pd = new PageData();
		pd.put("orderStatus", orderStatus);
		pd.put("appUserId", appUserId);
		pd.put("orderIdArr", orderIdArr);
		
		return (List<ComOrder>) dao.findForList("ComOrderMapper.listByOrderIdsSED", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrder> listByUserE(String userId) throws Exception {
		return (List<ComOrder>) dao.findForList("ComOrderMapper.listByUserE", userId);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String orderId
	 * @return ComOrder
	 * @throws Exception
	 */
	public ComOrder findByUserED(String orderId, String appUserId) throws Exception {
		ComOrder comOrder = new ComOrder();
		comOrder.setOrderId(orderId);
		comOrder.setAppUserId(appUserId);
		
		return (ComOrder) dao.findForObject("ComOrderMapper.findByUserED", comOrder);
	}
	
	/**
	 * 支付
	 * @param appUserId
	 * @param orderIdArr
	 * @throws Exception
	 */
	public void toPayByUserE(String appUserId, String[] orderIdArr) throws Exception {
		List<ComOrder> comOrderList = this.listByOrderIdsSED("01", appUserId, orderIdArr);
		if(comOrderList==null || comOrderList.size()==0){
			throw new UncheckedException("40001", null, "订单异常");
		}
		for (int i = 0; i < orderIdArr.length; i++) {
			ComOrder comOrder = comOrderList.get(i);
			//修改为已支付状态
			this.changeStatus("02", orderIdArr[i]);
			
			//支付记录
			ComIntegralNote comIntegralNote = new ComIntegralNote();
			comIntegralNote.setIntegralNoteCode(comOrder.getOrderCode());
			comIntegralNote.setIntegralNoteType("00");
			comIntegralNote.setIntegralNoteName("支付订单“"+comOrder.getOrderCode()+"”");
			comIntegralNote.setAppUserId(appUserId);
			comIntegralNote.setIntegralDealCount(""+comOrder.getAllActPrice());
			comIntegralNoteService.add(comIntegralNote);
			
			//积分扣除
			String addValue = "-"+comOrder.getAllActPrice();
			comAppUserExtService.addValue(appUserId, ComAppUserExt.INTEGRALCOUNT, addValue);
			
			//商品库存减少
			List<ComOrderDetail> comOrderDetailList = comOrder.getComOrderDetailList();
			for (int j = 0; j < comOrderDetailList.size(); j++) {
				ComOrderDetail comOrderDetail = comOrderDetailList.get(j);
				comProductStyleService.toReduceStockNum(comOrderDetail.getProductStyleId(), comOrderDetail.getCount());
			}
			
		}
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComOrder comOrder
	 * @throws Exception
	 */
	public void add(ComOrder comOrder) throws Exception {
		
		Date nowTime = new Date();
		String orderId = UuidUtil.get32UUID();
		comOrder.setOrderId(orderId);
		comOrder.setEffective("01");
		comOrder.setCreateUserId(ShiroSessionUtil.getUserId());
		comOrder.setCreateTime(nowTime);
		comOrder.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrder.setModifyTime(nowTime);
		
		dao.add("ComOrderMapper.add", comOrder);
		
		this.updateCode(orderId, comOrder.getOrderType());
	}
	
	/**
	 * 生成code 
	 * @param String productId
	 * @throws Exception
	 */
	public void updateCode(String orderId, String orderType) throws Exception {
		//生成固定id
		PageData pd = new PageData();
		
		pd.put("orderId", orderId);
		String startN = orderType.substring(1) + MapleDateUtil.formatDate(SDF.TIME1, new Date()).substring(2);
		pd.put("startN", startN);
		pd.put("endN", "1"+RandomUtil.getRandomRange(11, 50));
		dao.update("ComOrderMapper.updateCode", pd);
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
		switch(flag) {  
			case "02" : comOrder.setOldValue("01");break;
			case "03" : comOrder.setOldValue("02");break;
			case "04" : comOrder.setOldValue("03");break;
			case "05" : comOrder.setOldValue("04");break;
			case "06" : comOrder.setOldValue("05");break;
			default : comOrder.setOldValue("flag");break;
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
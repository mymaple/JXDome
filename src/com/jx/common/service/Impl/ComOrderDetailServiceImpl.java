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
import com.jx.common.entity.ComOrderDetail;
import com.jx.common.service.ComOrderDetailService;

@Service("comOrderDetailService")
public class ComOrderDetailServiceImpl implements ComOrderDetailService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrderDetail> listByOrderId(String orderId) throws Exception {
		return (List<ComOrderDetail>) dao.findForList("ComOrderDetailMapper.listByOrderId", orderId);
	}
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComOrderDetail comOrderDetail
	 * @throws Exception
	 */
	public void add(ComOrderDetail comOrderDetail) throws Exception {
		
		Date nowTime = new Date();
		String orderDetailId = UuidUtil.get32UUID();
		comOrderDetail.setOrderDetailId(orderDetailId);
		comOrderDetail.setEffective("01");
		comOrderDetail.setCreateUserId(ShiroSessionUtil.getUserId());
		comOrderDetail.setCreateTime(nowTime);
		comOrderDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrderDetail.setModifyTime(nowTime);
		
		dao.add("ComOrderDetailMapper.add", comOrderDetail);
	}
	
	/**
	 * 修改 
	 * @param ComOrderDetail comOrderDetail
	 * @throws Exception
	 */
	public void edit(ComOrderDetail comOrderDetail) throws Exception {
		Date nowTime = new Date();
		comOrderDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrderDetail.setModifyTime(nowTime);
	
		dao.update("ComOrderDetailMapper.edit", comOrderDetail);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String orderDetailId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String orderDetailId) throws Exception {
		ComOrderDetail comOrderDetail = new ComOrderDetail();
		if("00".equals(flag)){
			comOrderDetail.setOldValue("01");
		}else if("01".equals(flag)){
			comOrderDetail.setOldValue("00");
		}else{
			comOrderDetail.setOldValue("flag");
		}
		comOrderDetail.setEffective(flag);
		
		comOrderDetail.setOrderDetailId(orderDetailId);
		Date nowTime = new Date();
		comOrderDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrderDetail.setModifyTime(nowTime);
		dao.update("ComOrderDetailMapper.changeEffective", comOrderDetail);
	}
	
	/**
	 * 删除 
	 * @param String orderDetailId
	 * @throws Exception
	 */
	public void deleteById(String orderDetailId) throws Exception {
		dao.delete("ComOrderDetailMapper.deleteById", orderDetailId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComOrderDetailMapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String orderDetailId
	 * @return ComOrderDetail
	 * @throws Exception
	 */
	public ComOrderDetail findById(String orderDetailId) throws Exception {
		return (ComOrderDetail) dao.findForObject("ComOrderDetailMapper.findById", orderDetailId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrderDetail> listAll() throws Exception {
		return (List<ComOrderDetail>) dao.findForList("ComOrderDetailMapper.listAll", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComOrderDetailMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComShopCar;
import com.jx.common.service.ComShopCarService;

@Service("comShopCarService")
public class ComShopCarServiceImpl implements ComShopCarService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 通过id获取(类)数据
	 * @param String userId, String productStyleId
	 * @return ComShopCar
	 * @throws Exception
	 */
	public ComShopCar findByIdUSE(String userId, String productStyleId) throws Exception {
		ComShopCar comShopCar = new ComShopCar();
		comShopCar.setAppUserId(userId);
		comShopCar.setProductStyleId(productStyleId);
		return (ComShopCar) dao.findForObject("ComShopCarMapper.findByIdUSE", comShopCar);
	}
	
	/**
	 * 删除 
	 * @param String appUserId, String shopCarIds
	 * @throws Exception
	 */
	public void deleteByIdsUSE(String appUserId, String shopCarIds) throws Exception {
		Date nowTime = new Date();
		PageData pd = new PageData();
		pd.put("modifyUserId", ShiroSessionUtil.getUserId());
		pd.put("modifyTime", nowTime);
		pd.put("shopCarIds", shopCarIds.split(","));
		pd.put("appUserId", appUserId);
		
		dao.update("ComShopCarMapper.deleteByIdsUSE", pd);
	}
	
	/**
	 * 修改数量
	 * @param String shopCarId, String count
	 * @throws Exception
	 */
	public void changeCountUSE(String appUserId, String shopCarId, String count) throws Exception {
		ComShopCar comShopCar = new ComShopCar();
		
		comShopCar.setAppUserId(appUserId);
		comShopCar.setShopCarId(shopCarId);
		comShopCar.setCount(count);
		
		Date nowTime = new Date();
		comShopCar.setModifyUserId(ShiroSessionUtil.getUserId());
		comShopCar.setModifyTime(nowTime);
		
		dao.update("ComShopCarMapper.changeCountUSE", comShopCar);
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComShopCar comShopCar
	 * @throws Exception
	 */
	public void add(ComShopCar comShopCar) throws Exception {
		
		Date nowTime = new Date();
		String shopCarId = UuidUtil.get32UUID();
		comShopCar.setShopCarId(shopCarId);
		comShopCar.setEffective("01");
		comShopCar.setCreateUserId(ShiroSessionUtil.getUserId());
		comShopCar.setCreateTime(nowTime);
		comShopCar.setModifyUserId(ShiroSessionUtil.getUserId());
		comShopCar.setModifyTime(nowTime);
		
		dao.add("ComShopCarMapper.add", comShopCar);
		
	}
	
	
	/**
	 * 修改 
	 * @param ComShopCar comShopCar
	 * @throws Exception
	 */
	public void edit(ComShopCar comShopCar) throws Exception {
	
		Date nowTime = new Date();
		comShopCar.setModifyUserId(ShiroSessionUtil.getUserId());
		comShopCar.setModifyTime(nowTime);
	
		dao.update("ComShopCarMapper.edit", comShopCar);
	}
	
	/**
	 * 更改状态 flag 02：生成订单 03：失效
	 * @param String flag, String shopCarId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String shopCarId) throws Exception {
		ComShopCar comShopCar = new ComShopCar();
		if("02".equals(flag)){
			comShopCar.setOldValue("01");
		}else if("03".equals(flag)){
			comShopCar.setOldValue("01");
		}else{
			comShopCar.setOldValue("flag");
		}
		comShopCar.setShopCarStatus(flag);
		
		comShopCar.setShopCarId(shopCarId);
		Date nowTime = new Date();
		comShopCar.setModifyUserId(ShiroSessionUtil.getUserId());
		comShopCar.setModifyTime(nowTime);
		dao.update("ComShopCarMapper.changeStatus", comShopCar);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String shopCarId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String shopCarId) throws Exception {
		ComShopCar comShopCar = new ComShopCar();
		if("00".equals(flag)){
			comShopCar.setOldValue("01");
		}else if("01".equals(flag)){
			comShopCar.setOldValue("00");
		}else{
			comShopCar.setOldValue("flag");
		}
		comShopCar.setEffective(flag);
		
		comShopCar.setShopCarId(shopCarId);
		Date nowTime = new Date();
		comShopCar.setModifyUserId(ShiroSessionUtil.getUserId());
		comShopCar.setModifyTime(nowTime);
		dao.update("ComShopCarMapper.changeEffective", comShopCar);
	}
	
	/**
	 * 删除 
	 * @param String shopCarId
	 * @throws Exception
	 */
	public void deleteById(String shopCarId) throws Exception {
		dao.delete("ComShopCarMapper.deleteById", shopCarId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComShopCarMapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String shopCarId
	 * @return ComShopCar
	 * @throws Exception
	 */
	public ComShopCar findById(String shopCarId) throws Exception {
		return (ComShopCar) dao.findForObject("ComShopCarMapper.findById", shopCarId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComShopCar> listAll() throws Exception {
		return (List<ComShopCar>) dao.findForList("ComShopCarMapper.listAll", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComShopCarMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
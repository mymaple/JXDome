package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.Const;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.RandomUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComProduct;
import com.jx.common.entity.ComProductStyle;
import com.jx.common.entity.ComShopCar;
import com.jx.common.service.ComProductSEService;
import com.jx.common.service.ComProductService;

@Service("comProductSEService")
public class ComProductSEServiceImpl implements ComProductSEService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProduct> listByCategoryCodeSE(String categoryCode) throws Exception {
		return (List<ComProduct>) dao.findForList("ComProductSEMapper.listProductByCategoryCodeSE", categoryCode);
	}
	
	/**
	 * 获取(类)数据
	 * @return
	 * @throws Exception
	 */
	public ComProduct findProductByProductStyleIdSE(String productStyleId) throws Exception {
		return (ComProduct) dao.findForObject("ComProductSEMapper.findProductByProductStyleIdSE", productStyleId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComShopCar> listShopCarByUserSE(String appUserId, String[] shopCarIdArr) throws Exception {
		PageData pd = new PageData();
		pd.put("appUserId", appUserId);
		pd.put("shopCarIdArr", shopCarIdArr);
		return (List<ComShopCar>) dao.findForList("ComProductSEMapper.listShopCarByUserSE", pd);
	}
	
	
}
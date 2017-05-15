package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComProduct;
import com.jx.common.entity.ComShopCar;

public interface ComProductSEService {

	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProduct> listByCategoryCodeSE(String categoryCode) throws Exception ;
	
	/**
	 * 获取(类)数据
	 * @return
	 * @throws Exception
	 */
	public ComProduct findProductByProductStyleIdSE(String productStyleId) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComShopCar> listShopCarByUSE(String appUserId, String[] shopCarIdArr) throws Exception ;
	
}
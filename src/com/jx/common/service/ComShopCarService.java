package com.jx.common.service;

import java.util.List;

import com.jx.background.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComShopCar;

public interface ComShopCarService {

	
	/****************************custom * start***********************************/

	/**
	 * 通过id获取(类)数据
	 * @param String userId, String productStyleId
	 * @return ComShopCar
	 * @throws Exception
	 */
	public ComShopCar findByUserSE(String userId, String productStyleId) throws Exception ;
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComShopCar comShopCar
	 * @throws Exception
	 */
	public void add(ComShopCar comShopCar) throws Exception ;
	
	/**
	 * 修改 
	 * @param ComShopCar comShopCar
	 * @throws Exception
	 */
	public void edit(ComShopCar comShopCar) throws Exception ;
	
	
	/**
	 * 更改状态  flag 02：生成订单 ，03：失效
	 * @param String flag, String shopCarId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String shopCarId) throws Exception ;
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String shopCarId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String shopCarId) throws Exception ;
	
	/**
	 * 删除 
	 * @param String shopCarId
	 * @throws Exception
	 */
	public void deleteById(String shopCarId) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;
	
	/**
	 * 通过id获取(类)数据
	 * @param String shopCarId
	 * @return ComShopCar
	 * @throws Exception
	 */
	public ComShopCar findById(String shopCarId) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComShopCar> listAll() throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;

	
	/****************************common * end***********************************/
}
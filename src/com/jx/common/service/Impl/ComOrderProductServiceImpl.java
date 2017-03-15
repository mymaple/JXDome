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
import com.jx.common.entity.ComOrderProduct;
import com.jx.common.service.ComOrderProductService;
import com.jx.background.util.BgSessionUtil;

@Service("comOrderProductService")
public class ComOrderProductServiceImpl implements ComOrderProductService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComOrderProduct comOrderProduct
	 * @throws Exception
	 */
	public void add(ComOrderProduct comOrderProduct) throws Exception {
		
		Date nowTime = new Date();
		comOrderProduct.setOrderProductId(UuidUtil.get32UUID());
		comOrderProduct.setEffective("01");
		comOrderProduct.setCreateUserId(ShiroSessionUtil.getUserId());
		comOrderProduct.setCreateTime(nowTime);
		comOrderProduct.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrderProduct.setModifyTime(nowTime);
		
		dao.add("ComOrderProductMapper.add", comOrderProduct);
	}
	
	/**
	 * 修改 
	 * @param ComOrderProduct comOrderProduct
	 * @throws Exception
	 */
	public void edit(ComOrderProduct comOrderProduct) throws Exception {
		Date nowTime = new Date();
		comOrderProduct.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrderProduct.setModifyTime(nowTime);
		comOrderProduct.setLastModifyTime(this.findById(comOrderProduct.getOrderProductId()).getModifyTime());
		if(comOrderProduct.getModifyTime().compareTo(comOrderProduct.getLastModifyTime()) == 0){
			comOrderProduct.setModifyTime(MapleDateUtil.getNextSecond(comOrderProduct.getModifyTime()));
		}
	
		dao.update("ComOrderProductMapper.edit", comOrderProduct);
	}
	
	/**
	 * 更改
	 * @param ComOrderProduct comOrderProduct
	 * @throws Exception
	 */
	public void change(ComOrderProduct comOrderProduct) throws Exception {
		Date nowTime = new Date();
		comOrderProduct.setModifyUserId(ShiroSessionUtil.getUserId());
		comOrderProduct.setModifyTime(nowTime);
		comOrderProduct.setLastModifyTime(this.findById(comOrderProduct.getOrderProductId()).getModifyTime());
		if(comOrderProduct.getModifyTime().compareTo(comOrderProduct.getLastModifyTime()) == 0){
			comOrderProduct.setModifyTime(MapleDateUtil.getNextSecond(comOrderProduct.getModifyTime()));
		}
		dao.update("ComOrderProductMapper.change", comOrderProduct);
	}

	/**
	 * 删除 
	 * @param String orderProductId
	 * @throws Exception
	 */
	public void deleteById(String orderProductId) throws Exception {
		PageData pd = new PageData();
		pd.put("orderProductId",orderProductId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComOrderProductMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComOrderProductMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String orderProductId
	 * @return ComOrderProduct
	 * @throws Exception
	 */
	public ComOrderProduct findById(String orderProductId) throws Exception {
		PageData pd = new PageData();
		pd.put("orderProductId",orderProductId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComOrderProduct)数据 
	 * @param PageData pd
	 * @return ComOrderProduct
	 * @throws Exception
	 */
	public ComOrderProduct findByPd(PageData pd) throws Exception {
		return (ComOrderProduct) dao.findForObject("ComOrderProductMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrderProduct> listByPd(PageData pd) throws Exception {
		return (List<ComOrderProduct>) dao.findForList("ComOrderProductMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComOrderProduct> otherHave(ComOrderProduct comOrderProduct) throws Exception {
		return (List<ComOrderProduct>) dao.findForList("ComOrderProductMapper.otherHave", comOrderProduct);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComOrderProduct> otherHaveCode(String orderProductId, String orderProductCode) throws Exception {
		ComOrderProduct comOrderProduct = new ComOrderProduct();
		comOrderProduct.setOrderProductId(orderProductId);
		return this.otherHave(comOrderProduct);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComOrderProductMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
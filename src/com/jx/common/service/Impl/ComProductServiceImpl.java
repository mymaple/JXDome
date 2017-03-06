package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComProduct;
import com.jx.common.service.ComProductService;
import com.jx.background.util.BgSessionUtil;

@Service("comProductService")
public class ComProductServiceImpl implements ComProductService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProduct comProduct
	 * @throws Exception
	 */
	public void add(ComProduct comProduct) throws Exception {
		
		Date nowTime = new Date();
		comProduct.setProductId(UuidUtil.get32UUID());
		comProduct.setProductStatus("00");
		comProduct.setEffective("01");
		comProduct.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comProduct.setCreateTime(nowTime);
		comProduct.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comProduct.setModifyTime(nowTime);
		
		dao.add("ComProductMapper.add", comProduct);
	}
	
	/**
	 * 修改 
	 * @param ComProduct comProduct
	 * @throws Exception
	 */
	public void edit(ComProduct comProduct) throws Exception {
		Date nowTime = new Date();
		comProduct.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comProduct.setModifyTime(nowTime);
		comProduct.setLastModifyTime(this.findById(comProduct.getProductId()).getModifyTime());
		if(comProduct.getModifyTime().compareTo(comProduct.getLastModifyTime()) == 0){
			comProduct.setModifyTime(MapleDateUtil.getNextSecond(comProduct.getModifyTime()));
		}
	
		dao.edit("ComProductMapper.edit", comProduct);
	}
	
	/**
	 * 更改
	 * @param ComProduct comProduct
	 * @throws Exception
	 */
	public void change(ComProduct comProduct) throws Exception {
		Date nowTime = new Date();
		comProduct.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comProduct.setModifyTime(nowTime);
		comProduct.setLastModifyTime(this.findById(comProduct.getProductId()).getModifyTime());
		if(comProduct.getModifyTime().compareTo(comProduct.getLastModifyTime()) == 0){
			comProduct.setModifyTime(MapleDateUtil.getNextSecond(comProduct.getModifyTime()));
		}
		dao.edit("ComProductMapper.change", comProduct);
	}

	/**
	 * 删除 
	 * @param String productId
	 * @throws Exception
	 */
	public void deleteById(String productId) throws Exception {
		PageData pd = new PageData();
		pd.put("productId",productId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComProductMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComProductMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String productId
	 * @return ComProduct
	 * @throws Exception
	 */
	public ComProduct findById(String productId) throws Exception {
		PageData pd = new PageData();
		pd.put("productId",productId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComProduct)数据 
	 * @param PageData pd
	 * @return ComProduct
	 * @throws Exception
	 */
	public ComProduct findByPd(PageData pd) throws Exception {
		return (ComProduct) dao.findForObject("ComProductMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProduct> listByPd(PageData pd) throws Exception {
		return (List<ComProduct>) dao.findForList("ComProductMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProduct> otherHave(ComProduct comProduct) throws Exception {
		return (List<ComProduct>) dao.findForList("ComProductMapper.otherHave", comProduct);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProduct> otherHaveCode(String productId, String productCode) throws Exception {
		ComProduct comProduct = new ComProduct();
		comProduct.setProductId(productId);
		comProduct.setProductCode(productCode);
		return this.otherHave(comProduct);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComProductMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
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
import com.jx.common.entity.ComSupplier;
import com.jx.common.service.ComSupplierService;

@Service("comSupplierService")
public class ComSupplierServiceImpl implements ComSupplierService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComSupplier comSupplier
	 * @throws Exception
	 */
	public void add(ComSupplier comSupplier) throws Exception {
		
		Date nowTime = new Date();
		comSupplier.setSupplierId(UuidUtil.get32UUID());
		comSupplier.setSupplierStatus("00");
		comSupplier.setEffective("01");
		comSupplier.setCreateUserId(ShiroSessionUtil.getUserId());
		comSupplier.setCreateTime(nowTime);
		comSupplier.setModifyUserId(ShiroSessionUtil.getUserId());
		comSupplier.setModifyTime(nowTime);
		
		dao.add("ComSupplierMapper.add", comSupplier);
	}
	
	/**
	 * 修改 
	 * @param ComSupplier comSupplier
	 * @throws Exception
	 */
	public void edit(ComSupplier comSupplier) throws Exception {
		Date nowTime = new Date();
		comSupplier.setModifyUserId(ShiroSessionUtil.getUserId());
		comSupplier.setModifyTime(nowTime);
		comSupplier.setLastModifyTime(this.findById(comSupplier.getSupplierId()).getModifyTime());
		if(comSupplier.getModifyTime().compareTo(comSupplier.getLastModifyTime()) == 0){
			comSupplier.setModifyTime(MapleDateUtil.getNextSecond(comSupplier.getModifyTime()));
		}
	
		dao.update("ComSupplierMapper.edit", comSupplier);
	}
	
	/**
	 * 更改
	 * @param ComSupplier comSupplier
	 * @throws Exception
	 */
	public void change(ComSupplier comSupplier) throws Exception {
		Date nowTime = new Date();
		comSupplier.setModifyUserId(ShiroSessionUtil.getUserId());
		comSupplier.setModifyTime(nowTime);
		comSupplier.setLastModifyTime(this.findById(comSupplier.getSupplierId()).getModifyTime());
		if(comSupplier.getModifyTime().compareTo(comSupplier.getLastModifyTime()) == 0){
			comSupplier.setModifyTime(MapleDateUtil.getNextSecond(comSupplier.getModifyTime()));
		}
		dao.update("ComSupplierMapper.change", comSupplier);
	}

	/**
	 * 删除 
	 * @param String supplierId
	 * @throws Exception
	 */
	public void deleteById(String supplierId) throws Exception {
		PageData pd = new PageData();
		pd.put("supplierId",supplierId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComSupplierMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComSupplierMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String supplierId
	 * @return ComSupplier
	 * @throws Exception
	 */
	public ComSupplier findById(String supplierId) throws Exception {
		PageData pd = new PageData();
		pd.put("supplierId",supplierId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComSupplier)数据 
	 * @param PageData pd
	 * @return ComSupplier
	 * @throws Exception
	 */
	public ComSupplier findByPd(PageData pd) throws Exception {
		return (ComSupplier) dao.findForObject("ComSupplierMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComSupplier> listByPd(PageData pd) throws Exception {
		return (List<ComSupplier>) dao.findForList("ComSupplierMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComSupplier> otherHave(ComSupplier comSupplier) throws Exception {
		return (List<ComSupplier>) dao.findForList("ComSupplierMapper.otherHave", comSupplier);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSupplier> otherHaveCode(String supplierId, String supplierCode) throws Exception {
		ComSupplier comSupplier = new ComSupplier();
		comSupplier.setSupplierId(supplierId);
		comSupplier.setSupplierCode(supplierCode);
		return this.otherHave(comSupplier);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComSupplierMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
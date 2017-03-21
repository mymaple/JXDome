package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.RandomUtil;
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
		String supplierId = UuidUtil.get32UUID();
		comSupplier.setSupplierId(supplierId);
		comSupplier.setSupplierStatus("00");
		comSupplier.setEffective("01");
		comSupplier.setCreateUserId(ShiroSessionUtil.getUserId());
		comSupplier.setCreateTime(nowTime);
		comSupplier.setModifyUserId(ShiroSessionUtil.getUserId());
		comSupplier.setModifyTime(nowTime);
		
		dao.add("ComSupplierMapper.add", comSupplier);
		this.updateCode(supplierId);
	}
	
	/**
	 * 生成code 
	 * @param String supplierId
	 * @throws Exception
	 */
	public void updateCode(String supplierId) throws Exception {
		//生成固定id
		PageData pd = new PageData();
		pd.put("supplierId", supplierId);
		pd.put("startC", "gys");
		pd.put("startN", 100001);
		pd.put("addValue", RandomUtil.getRandomRange(11, 20));
		dao.update("ComSupplierMapper.updateCode", pd);
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
	
		dao.update("ComSupplierMapper.edit", comSupplier);
	}
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String supplierId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String supplierId) throws Exception {
		ComSupplier comSupplier = new ComSupplier();
		if("00".equals(flag)){
			comSupplier.setOldValue("01");
		}else if("01".equals(flag)){
			comSupplier.setOldValue("00");
		}else{
			comSupplier.setOldValue("flag");
		}
		comSupplier.setSupplierStatus(flag);
		
		comSupplier.setSupplierId(supplierId);
		Date nowTime = new Date();
		comSupplier.setModifyUserId(ShiroSessionUtil.getUserId());
		comSupplier.setModifyTime(nowTime);
		dao.update("ComSupplierMapper.changeStatus", comSupplier);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String supplierId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String supplierId) throws Exception {
		ComSupplier comSupplier = new ComSupplier();
		if("00".equals(flag)){
			comSupplier.setOldValue("01");
		}else if("01".equals(flag)){
			comSupplier.setOldValue("00");
		}else{
			comSupplier.setOldValue("flag");
		}
		comSupplier.setEffective(flag);
		
		comSupplier.setSupplierId(supplierId);
		Date nowTime = new Date();
		comSupplier.setModifyUserId(ShiroSessionUtil.getUserId());
		comSupplier.setModifyTime(nowTime);
		dao.update("ComSupplierMapper.changeEffective", comSupplier);
	}
	
	/**
	 * 删除 
	 * @param String supplierId
	 * @throws Exception
	 */
	public void deleteById(String supplierId) throws Exception {
		dao.delete("ComSupplierMapper.deleteById", supplierId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
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
		return (ComSupplier) dao.findForObject("ComSupplierMapper.findById", supplierId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComSupplier> listAll() throws Exception {
		return (List<ComSupplier>) dao.findForList("ComSupplierMapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComSupplier> otherHaveCode(String supplierId, String supplierCode) throws Exception {
		ComSupplier comSupplier = new ComSupplier();
		comSupplier.setSupplierId(supplierId);
		comSupplier.setSupplierCode(supplierCode);
		return (List<ComSupplier>) dao.findForList("ComSupplierMapper.otherHaveCode", comSupplier);
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
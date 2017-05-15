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
import com.jx.common.entity.ComSparepart;
import com.jx.common.service.ComSparepartService;

@Service("comSparepartService")
public class ComSparepartServiceImpl implements ComSparepartService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 通过id获取(类)数据
	 * @param String sparepartId
	 * @return ComSparepart
	 * @throws Exception
	 */
	public ComSparepart findByCode(String sparepartCode) throws Exception {
		PageData pd = new PageData();
		pd.put("sparepartCode",sparepartCode);
		return this.findByPd(pd);
	}
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComSparepart comSparepart
	 * @throws Exception
	 */
	public void add(ComSparepart comSparepart) throws Exception {
		
		Date nowTime = new Date();
		comSparepart.setSparepartId(UuidUtil.get32UUID());
		comSparepart.setSparepartStatus("00");
		comSparepart.setEffective("01");
		comSparepart.setCreateUserId(ShiroSessionUtil.getUserId());
		comSparepart.setCreateTime(nowTime);
		comSparepart.setModifyUserId(ShiroSessionUtil.getUserId());
		comSparepart.setModifyTime(nowTime);
		
		dao.add("ComSparepartMapper.add", comSparepart);
	}
	
	/**
	 * 修改 
	 * @param ComSparepart comSparepart
	 * @throws Exception
	 */
	public void edit(ComSparepart comSparepart) throws Exception {
		Date nowTime = new Date();
		comSparepart.setModifyUserId(ShiroSessionUtil.getUserId());
		comSparepart.setModifyTime(nowTime);
		comSparepart.setLastModifyTime(this.findById(comSparepart.getSparepartId()).getModifyTime());
		if(comSparepart.getModifyTime().compareTo(comSparepart.getLastModifyTime()) == 0){
			comSparepart.setModifyTime(MapleDateUtil.getNextSecond(comSparepart.getModifyTime()));
		}
	
		dao.update("ComSparepartMapper.edit", comSparepart);
	}
	
	/**
	 * 更改
	 * @param ComSparepart comSparepart
	 * @throws Exception
	 */
	public void change(ComSparepart comSparepart) throws Exception {
		Date nowTime = new Date();
		comSparepart.setModifyUserId(ShiroSessionUtil.getUserId());
		comSparepart.setModifyTime(nowTime);
		comSparepart.setLastModifyTime(this.findById(comSparepart.getSparepartId()).getModifyTime());
		if(comSparepart.getModifyTime().compareTo(comSparepart.getLastModifyTime()) == 0){
			comSparepart.setModifyTime(MapleDateUtil.getNextSecond(comSparepart.getModifyTime()));
		}
		dao.update("ComSparepartMapper.change", comSparepart);
	}

	/**
	 * 删除 
	 * @param String sparepartId
	 * @throws Exception
	 */
	public void deleteById(String sparepartId) throws Exception {
		PageData pd = new PageData();
		pd.put("sparepartId",sparepartId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComSparepartMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComSparepartMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String sparepartId
	 * @return ComSparepart
	 * @throws Exception
	 */
	public ComSparepart findById(String sparepartId) throws Exception {
		PageData pd = new PageData();
		pd.put("sparepartId",sparepartId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComSparepart)数据 
	 * @param PageData pd
	 * @return ComSparepart
	 * @throws Exception
	 */
	public ComSparepart findByPd(PageData pd) throws Exception {
		return (ComSparepart) dao.findForObject("ComSparepartMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComSparepart> listByPd(PageData pd) throws Exception {
		return (List<ComSparepart>) dao.findForList("ComSparepartMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComSparepart> otherHave(ComSparepart comSparepart) throws Exception {
		return (List<ComSparepart>) dao.findForList("ComSparepartMapper.otherHave", comSparepart);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComSparepart> otherHaveCode(String sparepartId, String sparepartCode) throws Exception {
		ComSparepart comSparepart = new ComSparepart();
		comSparepart.setSparepartId(sparepartId);
		comSparepart.setSparepartCode(sparepartCode);
		return this.otherHave(comSparepart);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComSparepartMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
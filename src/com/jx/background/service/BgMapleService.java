package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgMaple;

@Service("bgMapleService")
public class BgMapleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgMaple bgMaple
	 * @return 主键 id
	 * @throws Exception
	 */
	public int add(BgMaple bgMaple) throws Exception {
		return (int)dao.add("BgMapleMapper.add", bgMaple);
	}
	
	/**
	 * 新增
	 * @param PageData pd
	 * @return 主键 id
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("BgMapleMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param BgMaple bgMaple
	 * @throws Exception
	 */
	public void edit(BgMaple bgMaple) throws Exception {
		dao.edit("BgMapleMapper.edit", bgMaple);
	}

	/**
	 * 修改 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("BgMapleMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param int id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("BgMapleMapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgMapleMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String ids) throws Exception {
		dao.delete("BgMapleMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param int id
	 * @return BgMaple
	 * @throws Exception
	 */
	public BgMaple findById(int id) throws Exception {
		return (BgMaple) dao.findForObject("BgMapleMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param int id
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("BgMapleMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgMapleMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMaple> listAllByPd(PageData pd) throws Exception {
		return (List<BgMaple>) dao.findForList("BgMapleMapper.listAllByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgMapleMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
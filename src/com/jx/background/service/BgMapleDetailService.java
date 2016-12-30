package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgMapleDetail;

@Service("bgMapleDetailService")
public class BgMapleDetailService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgMapleDetail bgMapleDetail
	 * @return 主键 id
	 * @throws Exception
	 */
	public int add(BgMapleDetail bgMapleDetail) throws Exception {
		return (int)dao.add("BgMapleDetailMapper.add", bgMapleDetail);
	}
	
	/**
	 * 新增
	 * @param PageData pd
	 * @return 主键 id
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("BgMapleDetailMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param BgMapleDetail bgMapleDetail
	 * @throws Exception
	 */
	public void edit(BgMapleDetail bgMapleDetail) throws Exception {
		dao.edit("BgMapleDetailMapper.edit", bgMapleDetail);
	}

	/**
	 * 修改 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("BgMapleDetailMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param int id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("BgMapleDetailMapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgMapleDetailMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String ids) throws Exception {
		dao.delete("BgMapleDetailMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param int id
	 * @return BgMapleDetail
	 * @throws Exception
	 */
	public BgMapleDetail findById(int id) throws Exception {
		return (BgMapleDetail) dao.findForObject("BgMapleDetailMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param int id
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("BgMapleDetailMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgMapleDetailMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMapleDetail> listAllByPd(PageData pd) throws Exception {
		return (List<BgMapleDetail>) dao.findForList("BgMapleDetailMapper.listAllByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllPd(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgMapleDetailMapper.listAllPd", bgPage);
	}
	
	/****************************common * end***********************************/
}
package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComProductCategoryDetail;
import com.jx.common.service.ComProductCategoryDetailService;

@Service("comProductCategoryDetailService")
public class ComProductCategoryDetailServiceImpl implements ComProductCategoryDetailService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategoryDetail> listByProductCategoryId(String productCategoryId) throws Exception {
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		comProductCategoryDetail.setProductCategoryId(productCategoryId);
		return this.list(comProductCategoryDetail);
	}
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @throws Exception
	 */
	public void add(ComProductCategoryDetail comProductCategoryDetail) throws Exception {
		
		Date nowTime = new Date();
		comProductCategoryDetail.setProductCategoryDetailId(UuidUtil.get32UUID());
		comProductCategoryDetail.setEffective("01");
		comProductCategoryDetail.setCreateUserId(ShiroSessionUtil.getUserId());
		comProductCategoryDetail.setCreateTime(nowTime);
		comProductCategoryDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategoryDetail.setModifyTime(nowTime);
		
		dao.add("ComProductCategoryDetailMapper.add", comProductCategoryDetail);
	}
	
	/**
	 * 修改 
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @throws Exception
	 */
	public void edit(ComProductCategoryDetail comProductCategoryDetail) throws Exception {
		Date nowTime = new Date();
		comProductCategoryDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategoryDetail.setModifyTime(nowTime);
	
		dao.update("ComProductCategoryDetailMapper.edit", comProductCategoryDetail);
	}
	
	/**
	 * 更改
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @throws Exception
	 */
	public void change(ComProductCategoryDetail comProductCategoryDetail) throws Exception {
		Date nowTime = new Date();
		comProductCategoryDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategoryDetail.setModifyTime(nowTime);
		dao.update("ComProductCategoryDetailMapper.change", comProductCategoryDetail);
	}

	/**
	 * 删除 
	 * @param String productCategoryDetailId
	 * @throws Exception
	 */
	public void deleteById(String productCategoryDetailId) throws Exception {
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		comProductCategoryDetail.setProductCategoryDetailId(productCategoryDetailId);
		this.delete(comProductCategoryDetail);
	}
	
	/**
	 * 删除 
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @throws Exception
	 */
	public void delete(ComProductCategoryDetail comProductCategoryDetail) throws Exception {
		dao.delete("ComProductCategoryDetailMapper.delete", comProductCategoryDetail);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComProductCategoryDetailMapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 生效 
	 * @param String productCategoryDetailId
	 * @throws Exception
	 */
	public void toEffective(String productCategoryDetailId) throws Exception {
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		comProductCategoryDetail.setProductCategoryDetailId(productCategoryDetailId);
		Date nowTime = new Date();
		comProductCategoryDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategoryDetail.setModifyTime(nowTime);
		dao.update("ComProductCategoryDetailMapper.toEffective", comProductCategoryDetail);
	}
	
	/**
	 * 失效
	 * @param String productCategoryDetailId
	 * @throws Exception
	 */
	public void toDisEffective(String productCategoryDetailId) throws Exception {
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		comProductCategoryDetail.setProductCategoryDetailId(productCategoryDetailId);
		Date nowTime = new Date();
		comProductCategoryDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategoryDetail.setModifyTime(nowTime);
		dao.update("ComProductCategoryDetailMapper.toDisEffective", comProductCategoryDetail);
	}
	
	
	

	/**
	 * 通过id获取(类)数据
	 * @param String productCategoryDetailId
	 * @return ComProductCategoryDetail
	 * @throws Exception
	 */
	public ComProductCategoryDetail findById(String productCategoryDetailId) throws Exception {
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		comProductCategoryDetail.setProductCategoryDetailId(productCategoryDetailId);
		return this.find(comProductCategoryDetail);
	}
	
	/**
	 * 通过pd获取(ComProductCategoryDetail)数据 
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @return ComProductCategoryDetail
	 * @throws Exception
	 */
	public ComProductCategoryDetail find(ComProductCategoryDetail comProductCategoryDetail) throws Exception {
		return (ComProductCategoryDetail) dao.findForObject("ComProductCategoryDetailMapper.find", comProductCategoryDetail);
	}
	
	/**
	 * 获取(类)List数据
	 * @param ComProductCategoryDetail comProductCategoryDetail
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductCategoryDetail> list(ComProductCategoryDetail comProductCategoryDetail) throws Exception {
		return (List<ComProductCategoryDetail>) dao.findForList("ComProductCategoryDetailMapper.list", comProductCategoryDetail);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductCategoryDetail> otherHave(ComProductCategoryDetail comProductCategoryDetail) throws Exception {
		return (List<ComProductCategoryDetail>) dao.findForList("ComProductCategoryDetailMapper.otherHave", comProductCategoryDetail);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComProductCategoryDetail> otherHaveCode(String productCategoryDetailId, String productCategoryDetailCode) throws Exception {
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		comProductCategoryDetail.setProductCategoryDetailId(productCategoryDetailId);
		return this.otherHave(comProductCategoryDetail);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComProductCategoryDetailMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
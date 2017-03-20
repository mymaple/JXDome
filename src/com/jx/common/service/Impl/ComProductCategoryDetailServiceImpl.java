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
	@SuppressWarnings("unchecked")
	public List<ComProductCategoryDetail> listByProductCategoryId(String productCategoryId) throws Exception {
		return (List<ComProductCategoryDetail>) dao.findForList("ComProductCategoryDetailMapper.listByProductCategoryId", productCategoryId);
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
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String productCategoryDetailId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String productCategoryDetailId) throws Exception {
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		if("00".equals(flag)){
			comProductCategoryDetail.setOldValue("01");
		}else if("01".equals(flag)){
			comProductCategoryDetail.setOldValue("00");
		}else{
			comProductCategoryDetail.setOldValue("flag");
		}
		comProductCategoryDetail.setEffective(flag);
		
		comProductCategoryDetail.setProductCategoryDetailId(productCategoryDetailId);
		Date nowTime = new Date();
		comProductCategoryDetail.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductCategoryDetail.setModifyTime(nowTime);
		dao.update("ComProductCategoryDetailMapper.changeEffective", comProductCategoryDetail);
	}
	
	/**
	 * 删除 
	 * @param String productCategoryDetailId
	 * @throws Exception
	 */
	public void deleteById(String productCategoryDetailId) throws Exception {
		dao.delete("ComProductCategoryDetailMapper.deleteById", productCategoryDetailId);
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
	 * 通过id获取(类)数据
	 * @param String productCategoryDetailId
	 * @return ComProductCategoryDetail
	 * @throws Exception
	 */
	public ComProductCategoryDetail findById(String productCategoryDetailId) throws Exception {
		return (ComProductCategoryDetail) dao.findForObject("ComProductCategoryDetailMapper.findById", productCategoryDetailId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductCategoryDetail> listAll() throws Exception {
		return (List<ComProductCategoryDetail>) dao.findForList("ComProductCategoryDetailMapper.listAll", null);
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
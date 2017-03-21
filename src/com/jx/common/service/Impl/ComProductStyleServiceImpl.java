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
import com.jx.common.entity.ComProductStyle;
import com.jx.common.service.ComProductStyleService;

@Service("comProductStyleService")
public class ComProductStyleServiceImpl implements ComProductStyleService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProductStyle comProductStyle
	 * @throws Exception
	 */
	public void add(ComProductStyle comProductStyle) throws Exception {
		
		Date nowTime = new Date();
		comProductStyle.setProductStyleId(UuidUtil.get32UUID());
		comProductStyle.setEffective("01");
		comProductStyle.setCreateUserId(ShiroSessionUtil.getUserId());
		comProductStyle.setCreateTime(nowTime);
		comProductStyle.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductStyle.setModifyTime(nowTime);
		
		dao.add("ComProductStyleMapper.add", comProductStyle);
	}
	
	/**
	 * 修改 
	 * @param ComProductStyle comProductStyle
	 * @throws Exception
	 */
	public void edit(ComProductStyle comProductStyle) throws Exception {
	
		Date nowTime = new Date();
		comProductStyle.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductStyle.setModifyTime(nowTime);
	
		dao.update("ComProductStyleMapper.edit", comProductStyle);
	}
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String productStyleId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String productStyleId) throws Exception {
		ComProductStyle comProductStyle = new ComProductStyle();
		if("00".equals(flag)){
			comProductStyle.setOldValue("01");
		}else if("01".equals(flag)){
			comProductStyle.setOldValue("00");
		}else{
			comProductStyle.setOldValue("flag");
		}
		comProductStyle.setProductStyleStatus(flag);
		
		comProductStyle.setProductStyleId(productStyleId);
		Date nowTime = new Date();
		comProductStyle.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductStyle.setModifyTime(nowTime);
		dao.update("ComProductStyleMapper.changeStatus", comProductStyle);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String productStyleId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String productStyleId) throws Exception {
		ComProductStyle comProductStyle = new ComProductStyle();
		if("00".equals(flag)){
			comProductStyle.setOldValue("01");
		}else if("01".equals(flag)){
			comProductStyle.setOldValue("00");
		}else{
			comProductStyle.setOldValue("flag");
		}
		comProductStyle.setEffective(flag);
		
		comProductStyle.setProductStyleId(productStyleId);
		Date nowTime = new Date();
		comProductStyle.setModifyUserId(ShiroSessionUtil.getUserId());
		comProductStyle.setModifyTime(nowTime);
		dao.update("ComProductStyleMapper.changeEffective", comProductStyle);
	}
	
	/**
	 * 删除 
	 * @param String productStyleId
	 * @throws Exception
	 */
	public void deleteById(String productStyleId) throws Exception {
		dao.delete("ComProductStyleMapper.deleteById", productStyleId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComProductStyleMapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String productStyleId
	 * @return ComProductStyle
	 * @throws Exception
	 */
	public ComProductStyle findById(String productStyleId) throws Exception {
		return (ComProductStyle) dao.findForObject("ComProductStyleMapper.findById", productStyleId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductStyle> listAll() throws Exception {
		return (List<ComProductStyle>) dao.findForList("ComProductStyleMapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProductStyle> otherHaveCode(String productStyleId, String productStyleCode) throws Exception {
		ComProductStyle comProductStyle = new ComProductStyle();
		comProductStyle.setProductStyleId(productStyleId);
		comProductStyle.setProductStyleCode(productStyleCode);
		return (List<ComProductStyle>) dao.findForList("ComProductStyleMapper.otherHaveCode", comProductStyle);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComProductStyleMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
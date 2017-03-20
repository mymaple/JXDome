package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.Const;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComLbt;
import com.jx.common.entity.ComProduct;
import com.jx.common.service.ComLbtService;

@Service("comLbtService")
public class ComLbtServiceImpl implements ComLbtService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void add(ComLbt comLbt) throws Exception {
		
		comLbt.setLbtImgSrc(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
				ComLbt.PATH_IMG_GBT, comLbt.getLbtImgSrc()));
		
		Date nowTime = new Date();
		comLbt.setLbtId(UuidUtil.get32UUID());
		comLbt.setLbtStatus("00");
		comLbt.setEffective("01");
		comLbt.setCreateUserId(ShiroSessionUtil.getUserId());
		comLbt.setCreateTime(nowTime);
		comLbt.setModifyUserId(ShiroSessionUtil.getUserId());
		comLbt.setModifyTime(nowTime);
		
		dao.add("ComLbtMapper.add", comLbt);
	}
	
	/**
	 * 修改 
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void edit(ComLbt comLbt) throws Exception {
		comLbt.setLbtImgSrc(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
				ComLbt.PATH_IMG_GBT, comLbt.getLbtImgSrc()));
		Date nowTime = new Date();
		comLbt.setModifyUserId(ShiroSessionUtil.getUserId());
		comLbt.setModifyTime(nowTime);
	
		dao.update("ComLbtMapper.edit", comLbt);
	}
	
	/**
	 * 更改
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void change(ComLbt comLbt) throws Exception {
		comLbt.setLbtImgSrc(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
				ComLbt.PATH_IMG_GBT, comLbt.getLbtImgSrc()));
		Date nowTime = new Date();
		comLbt.setModifyUserId(ShiroSessionUtil.getUserId());
		comLbt.setModifyTime(nowTime);
		dao.update("ComLbtMapper.change", comLbt);
	}

	/**
	 * 删除 
	 * @param String lbtId
	 * @throws Exception
	 */
	public void deleteById(String lbtId) throws Exception {
		ComLbt comLbt = new ComLbt();
		comLbt.setLbtId(lbtId);
		this.delete(comLbt);
	}
	
	/**
	 * 删除 
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void delete(ComLbt comLbt) throws Exception {
		dao.delete("ComLbtMapper.delete", comLbt);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComLbtMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String lbtId
	 * @return ComLbt
	 * @throws Exception
	 */
	public ComLbt findById(String lbtId) throws Exception {
		ComLbt comLbt = new ComLbt();
		comLbt.setLbtId(lbtId);
		return this.find(comLbt);
	}
	
	/**
	 * 通过pd获取(ComLbt)数据 
	 * @param ComLbt comLbt
	 * @return ComLbt
	 * @throws Exception
	 */
	public ComLbt find(ComLbt comLbt) throws Exception {
		return (ComLbt) dao.findForObject("ComLbtMapper.find", comLbt);
	}
	
	/**
	 * 获取(类)List数据
	 * @param ComLbt comLbt
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComLbt> list(ComLbt comLbt) throws Exception {
		return (List<ComLbt>) dao.findForList("ComLbtMapper.list", comLbt);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComLbt> otherHave(ComLbt comLbt) throws Exception {
		return (List<ComLbt>) dao.findForList("ComLbtMapper.otherHave", comLbt);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComLbt> otherHaveCode(String lbtId, String lbtCode) throws Exception {
		ComLbt comLbt = new ComLbt();
		comLbt.setLbtId(lbtId);
		comLbt.setLbtCode(lbtCode);
		return this.otherHave(comLbt);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComLbtMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
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
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComLbt;
import com.jx.common.service.ComLbtService;

@Service("comLbtService")
public class ComLbtServiceImpl implements ComLbtService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComLbt> listByE() throws Exception {
		return (List<ComLbt>) dao.findForList("ComLbtMapper.listByE", null);
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComLbt comLbt
	 * @throws Exception
	 */
	public void add(ComLbt comLbt) throws Exception {
		
		Date nowTime = new Date();
		comLbt.setLbtId(UuidUtil.get32UUID());
		comLbt.setLbtStatus("00");
		comLbt.setLbtImgSrc(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComLbt.PATH_IMG_LBT_LBTIMG, comLbt.getLbtImgSrc()));
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
			ComLbt.PATH_IMG_LBT_LBTIMG, comLbt.getLbtImgSrc()));
		Date nowTime = new Date();
		comLbt.setModifyUserId(ShiroSessionUtil.getUserId());
		comLbt.setModifyTime(nowTime);
	
		dao.update("ComLbtMapper.edit", comLbt);
	}
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String lbtId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String lbtId) throws Exception {
		ComLbt comLbt = new ComLbt();
		if("00".equals(flag)){
			comLbt.setOldValue("01");
		}else if("01".equals(flag)){
			comLbt.setOldValue("00");
		}else{
			comLbt.setOldValue("flag");
		}
		comLbt.setLbtStatus(flag);
		
		comLbt.setLbtId(lbtId);
		Date nowTime = new Date();
		comLbt.setModifyUserId(ShiroSessionUtil.getUserId());
		comLbt.setModifyTime(nowTime);
		dao.update("ComLbtMapper.changeStatus", comLbt);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String lbtId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String lbtId) throws Exception {
		ComLbt comLbt = new ComLbt();
		if("00".equals(flag)){
			comLbt.setOldValue("01");
		}else if("01".equals(flag)){
			comLbt.setOldValue("00");
		}else{
			comLbt.setOldValue("flag");
		}
		comLbt.setEffective(flag);
		
		comLbt.setLbtId(lbtId);
		Date nowTime = new Date();
		comLbt.setModifyUserId(ShiroSessionUtil.getUserId());
		comLbt.setModifyTime(nowTime);
		dao.update("ComLbtMapper.changeEffective", comLbt);
	}
	
	/**
	 * 删除 
	 * @param String lbtId
	 * @throws Exception
	 */
	public void deleteById(String lbtId) throws Exception {
		dao.delete("ComLbtMapper.deleteById", lbtId);
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
		return (ComLbt) dao.findForObject("ComLbtMapper.findById", lbtId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComLbt> listAll() throws Exception {
		return (List<ComLbt>) dao.findForList("ComLbtMapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComLbt> otherHaveCode(String lbtId, String lbtCode) throws Exception {
		ComLbt comLbt = new ComLbt();
		comLbt.setLbtId(lbtId);
		comLbt.setLbtCode(lbtCode);
		return (List<ComLbt>) dao.findForList("ComLbtMapper.otherHaveCode", comLbt);
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
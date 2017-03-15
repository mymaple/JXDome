package com.jx.background.service.Impl;

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
import com.jx.background.entity.BgWxMenuBtn;
import com.jx.background.service.BgWxMenuBtnService;
import com.jx.background.util.BgSessionUtil;

@Service("bgWxMenuBtnService")
public class BgWxMenuBtnServiceImpl implements BgWxMenuBtnService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	
		/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<BgWxMenuBtn> parentList, String pId) throws Exception {
		if("0".equals(pId)) return;
		BgWxMenuBtn bgWxMenuBtn = this.findById(pId);
		bgWxMenuBtn.setSubBgWxMenuBtnPath("background/wxMenuBtn/list.do?pId="+pId);
		parentList.add(0, bgWxMenuBtn);
		this.getParentList(parentList, bgWxMenuBtn.getParentId());
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<BgWxMenuBtn> listByParentId(String parentId) throws Exception {
		PageData pd = new PageData();
		pd.put("parentId",parentId);
		return this.listByPd(pd);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String wxMenuBtnId
	 * @return
	 * @throws Exception
	 */
	public List<BgWxMenuBtn> listInRank(String wxMenuBtnId) throws Exception {
		List<BgWxMenuBtn> bgWxMenuBtnList = this.listByParentId(wxMenuBtnId);
		for(BgWxMenuBtn bgWxMenuBtn : bgWxMenuBtnList){
			bgWxMenuBtn.setSubBgWxMenuBtnPath("background/wxMenuBtn/list.do?pId="+bgWxMenuBtn.getWxMenuBtnId());
			bgWxMenuBtn.setSubBgWxMenuBtnList(this.listInRank(bgWxMenuBtn.getWxMenuBtnId()));
			bgWxMenuBtn.setTarget("treeFrame");
		}
		return bgWxMenuBtnList;
	}
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String wxMenuBtnId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String wxMenuBtnId) throws Exception {
		this.deleteById(wxMenuBtnId);
		List<BgWxMenuBtn> bgWxMenuBtnList = this.listByParentId(wxMenuBtnId);
		for(BgWxMenuBtn bgWxMenuBtn : bgWxMenuBtnList){
			this.deleteInRank(bgWxMenuBtn.getWxMenuBtnId());
		}
	}
	
	/**
	 * 批量删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void batchDeleteInRank(String[] ids) throws Exception {
		for(String id : ids){
			this.deleteInRank(id);
		}
	}
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgWxMenuBtn bgWxMenuBtn
	 * @throws Exception
	 */
	public void add(BgWxMenuBtn bgWxMenuBtn) throws Exception {
		
		Date nowTime = new Date();
		bgWxMenuBtn.setWxMenuBtnId(UuidUtil.get32UUID());
		bgWxMenuBtn.setWxMenuBtnStatus("00");
		bgWxMenuBtn.setEffective("01");
		bgWxMenuBtn.setCreateUserId(ShiroSessionUtil.getUserId());
		bgWxMenuBtn.setCreateTime(nowTime);
		bgWxMenuBtn.setModifyUserId(ShiroSessionUtil.getUserId());
		bgWxMenuBtn.setModifyTime(nowTime);
		
		dao.add("BgWxMenuBtnMapper.add", bgWxMenuBtn);
	}
	
	/**
	 * 修改 
	 * @param BgWxMenuBtn bgWxMenuBtn
	 * @throws Exception
	 */
	public void edit(BgWxMenuBtn bgWxMenuBtn) throws Exception {
		Date nowTime = new Date();
		bgWxMenuBtn.setModifyUserId(ShiroSessionUtil.getUserId());
		bgWxMenuBtn.setModifyTime(nowTime);
		bgWxMenuBtn.setLastModifyTime(this.findById(bgWxMenuBtn.getWxMenuBtnId()).getModifyTime());
		if(bgWxMenuBtn.getModifyTime().compareTo(bgWxMenuBtn.getLastModifyTime()) == 0){
			bgWxMenuBtn.setModifyTime(MapleDateUtil.getNextSecond(bgWxMenuBtn.getModifyTime()));
		}
	
		dao.update("BgWxMenuBtnMapper.edit", bgWxMenuBtn);
	}
	
	/**
	 * 更改
	 * @param BgWxMenuBtn bgWxMenuBtn
	 * @throws Exception
	 */
	public void change(BgWxMenuBtn bgWxMenuBtn) throws Exception {
		Date nowTime = new Date();
		bgWxMenuBtn.setModifyUserId(ShiroSessionUtil.getUserId());
		bgWxMenuBtn.setModifyTime(nowTime);
		bgWxMenuBtn.setLastModifyTime(this.findById(bgWxMenuBtn.getWxMenuBtnId()).getModifyTime());
		if(bgWxMenuBtn.getModifyTime().compareTo(bgWxMenuBtn.getLastModifyTime()) == 0){
			bgWxMenuBtn.setModifyTime(MapleDateUtil.getNextSecond(bgWxMenuBtn.getModifyTime()));
		}
		dao.update("BgWxMenuBtnMapper.change", bgWxMenuBtn);
	}

	/**
	 * 删除 
	 * @param String wxMenuBtnId
	 * @throws Exception
	 */
	public void deleteById(String wxMenuBtnId) throws Exception {
		PageData pd = new PageData();
		pd.put("wxMenuBtnId",wxMenuBtnId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgWxMenuBtnMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("BgWxMenuBtnMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String wxMenuBtnId
	 * @return BgWxMenuBtn
	 * @throws Exception
	 */
	public BgWxMenuBtn findById(String wxMenuBtnId) throws Exception {
		PageData pd = new PageData();
		pd.put("wxMenuBtnId",wxMenuBtnId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(BgWxMenuBtn)数据 
	 * @param PageData pd
	 * @return BgWxMenuBtn
	 * @throws Exception
	 */
	public BgWxMenuBtn findByPd(PageData pd) throws Exception {
		return (BgWxMenuBtn) dao.findForObject("BgWxMenuBtnMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgWxMenuBtn> listByPd(PageData pd) throws Exception {
		return (List<BgWxMenuBtn>) dao.findForList("BgWxMenuBtnMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgWxMenuBtn> otherHave(BgWxMenuBtn bgWxMenuBtn) throws Exception {
		return (List<BgWxMenuBtn>) dao.findForList("BgWxMenuBtnMapper.otherHave", bgWxMenuBtn);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgWxMenuBtn> otherHaveCode(String wxMenuBtnId, String wxMenuBtnCode) throws Exception {
		BgWxMenuBtn bgWxMenuBtn = new BgWxMenuBtn();
		bgWxMenuBtn.setWxMenuBtnId(wxMenuBtnId);
		bgWxMenuBtn.setWxMenuBtnCode(wxMenuBtnCode);
		return this.otherHave(bgWxMenuBtn);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgWxMenuBtnMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
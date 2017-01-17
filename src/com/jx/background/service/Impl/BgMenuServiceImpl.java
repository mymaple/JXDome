package com.jx.background.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.background.entity.BgMenu;
import com.jx.background.service.BgMenuService;
import com.jx.background.util.BgSessionUtil;

@Service("bgMenuService")
public class BgMenuServiceImpl implements BgMenuService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<BgMenu> parentList, String pId) throws Exception {
		if("0".equals(pId)) return;
		BgMenu bgMenu = this.findById(pId);
		bgMenu.setSubBgMenuPath("background/menu/list.do?pId="+pId);
		parentList.add(0, bgMenu);
		this.getParentList(parentList, bgMenu.getParentId());
	}
	
	/**
	 * 更改
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void changeMenuIcon(BgMenu bgMenu) throws Exception {
		BgMenu bgMenu1 = new BgMenu();
		bgMenu1.setMenuIcon(bgMenu.getMenuIcon());
		bgMenu1.setMenuId(bgMenu.getMenuId());
		this.change(bgMenu1);
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listByParentId(String parentId) throws Exception {
		PageData pd = new PageData();
		pd.put("parentId",parentId);
		return this.listByPd(pd);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String menuId
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listInRank(String menuId) throws Exception {
		List<BgMenu> bgMenuList = this.listByParentId(menuId);
		for(BgMenu bgMenu : bgMenuList){
			bgMenu.setSubBgMenuPath("background/menu/list.do?pId="+bgMenu.getMenuId());
			bgMenu.setSubBgMenuList(this.listInRank(bgMenu.getMenuId()));
			bgMenu.setTarget("treeFrame");
		}
		return bgMenuList;
	}
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String menuId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String menuId) throws Exception {
		this.deleteById(menuId);
		List<BgMenu> bgMenuList = this.listByParentId(menuId);
		for(BgMenu bgMenu : bgMenuList){
			this.deleteInRank(bgMenu.getMenuId());
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
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void add(BgMenu bgMenu) throws Exception {
		
		Date nowTime = new Date();
		bgMenu.setMenuId(UuidUtil.get32UUID());
		bgMenu.setMenuStatus("00");
		bgMenu.setEffective("01");
		bgMenu.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMenu.setCreateTime(nowTime);
		bgMenu.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMenu.setModifyTime(nowTime);
		
		dao.add("BgMenuMapper.add", bgMenu);
	}
	
	/**
	 * 修改 
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void edit(BgMenu bgMenu) throws Exception {
		Date nowTime = new Date();
		bgMenu.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMenu.setModifyTime(nowTime);
		bgMenu.setLastModifyTime(this.findById(bgMenu.getMenuId()).getModifyTime());
		if(bgMenu.getModifyTime().compareTo(bgMenu.getLastModifyTime()) == 0){
			bgMenu.setModifyTime(MapleDateUtil.getNextSecond(bgMenu.getModifyTime()));
		}
	
		dao.edit("BgMenuMapper.edit", bgMenu);
	}
	
	/**
	 * 更改
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void change(BgMenu bgMenu) throws Exception {
		Date nowTime = new Date();
		bgMenu.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMenu.setModifyTime(nowTime);
		bgMenu.setLastModifyTime(this.findById(bgMenu.getMenuId()).getModifyTime());
		if(bgMenu.getModifyTime().compareTo(bgMenu.getLastModifyTime()) == 0){
			bgMenu.setModifyTime(MapleDateUtil.getNextSecond(bgMenu.getModifyTime()));
		}
		dao.edit("BgMenuMapper.change", bgMenu);
	}

	/**
	 * 删除 
	 * @param String menuId
	 * @throws Exception
	 */
	public void deleteById(String menuId) throws Exception {
		PageData pd = new PageData();
		pd.put("menuId",menuId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgMenuMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("BgMenuMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String menuId
	 * @return BgMenu
	 * @throws Exception
	 */
	public BgMenu findById(String menuId) throws Exception {
		PageData pd = new PageData();
		pd.put("menuId",menuId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(BgMenu)数据 
	 * @param PageData pd
	 * @return BgMenu
	 * @throws Exception
	 */
	public BgMenu findByPd(PageData pd) throws Exception {
		return (BgMenu) dao.findForObject("BgMenuMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgMenu> listByPd(PageData pd) throws Exception {
		return (List<BgMenu>) dao.findForList("BgMenuMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgMenu> otherHave(BgMenu bgMenu) throws Exception {
		return (List<BgMenu>) dao.findForList("BgMenuMapper.otherHave", bgMenu);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> otherHaveCode(String menuId, String menuCode) throws Exception {
		BgMenu bgMenu = new BgMenu();
		bgMenu.setMenuId(menuId);
		bgMenu.setMenuCode(menuCode);
		return this.otherHave(bgMenu);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgMenuMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
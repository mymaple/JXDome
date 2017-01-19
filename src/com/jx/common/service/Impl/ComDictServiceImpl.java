package com.jx.common.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComDict;
import com.jx.common.service.ComDictService;
import com.jx.background.util.BgSessionUtil;

@Service("comDictService")
public class ComDictServiceImpl implements ComDictService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<ComDict> parentList, String pId) throws Exception {
		if("0".equals(pId)) return;
		ComDict comDict = this.findById(pId);
		comDict.setSubComDictPath("background/dict/list.do?pId="+pId);
		parentList.add(0, comDict);
		this.getParentList(parentList, comDict.getParentId());
	}
	
	/**
	 * 显示名称获取
	 * @param type
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getDisplayName(String type, String value) throws Exception {
		PageData pd = new PageData();
		pd.put("type",type);
		pd.put("value",value);
		List<ComDict> comDictList = this.otherHaveCode("",type);
		if(MapleUtil.notEmptyList(comDictList)){
			String dictType = comDictList.get(0).getDictType();
			if("01".equals(dictType)){
				return (String) dao.findForObject("ComDictMapper.getDisplayName", pd);
			}else if("02".equals(dictType)){
				if("0".equals(value)) return "顶级";
				ComDict comDict = (ComDict)dao.findForObject("SQLDictMapper."+type, value);
				return comDict == null ?  "" : comDict.getDictName();
			}
		}
		return "";
	}
	
	/**
	 * 根据code 获取所有直接子
	 * @param String code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDict> listSelect(String type) throws Exception {
		List<ComDict> comDictList = this.otherHaveCode("",type);
		if(MapleUtil.notEmptyList(comDictList)){
			String dictType = comDictList.get(0).getDictType();
			if("01".equals(dictType)){
				return (List<ComDict>) dao.findForList("ComDictMapper.listSelect", type);
			}else if("02".equals(dictType)){
				return (List<ComDict>) dao.findForList("SQLDictMapper."+type, null);
			}
		}
		return new ArrayList<ComDict>();
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> listByParentId(String parentId) throws Exception {
		PageData pd = new PageData();
		pd.put("parentId",parentId);
		return this.listByPd(pd);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> listInRank(String dictId) throws Exception {
		List<ComDict> comDictList = this.listByParentId(dictId);
		for(ComDict comDict : comDictList){
			comDict.setSubComDictPath("background/dict/list.do?pId="+comDict.getDictId());
			comDict.setSubComDictList(this.listInRank(comDict.getDictId()));
			comDict.setTarget("treeFrame");
		}
		return comDictList;
	}
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String dictId) throws Exception {
		this.deleteById(dictId);
		List<ComDict> comDictList = this.listByParentId(dictId);
		for(ComDict comDict : comDictList){
			this.deleteInRank(comDict.getDictId());
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
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void add(ComDict comDict) throws Exception {
		
		Date nowTime = new Date();
		comDict.setDictId(UuidUtil.get32UUID());
		comDict.setDictStatus("00");
		comDict.setEffective("01");
		comDict.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comDict.setCreateTime(nowTime);
		comDict.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comDict.setModifyTime(nowTime);
		
		dao.add("ComDictMapper.add", comDict);
	}
	
	/**
	 * 修改 
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void edit(ComDict comDict) throws Exception {
		Date nowTime = new Date();
		comDict.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comDict.setModifyTime(nowTime);
		comDict.setLastModifyTime(this.findById(comDict.getDictId()).getModifyTime());
		if(comDict.getModifyTime().compareTo(comDict.getLastModifyTime()) == 0){
			comDict.setModifyTime(MapleDateUtil.getNextSecond(comDict.getModifyTime()));
		}
	
		dao.edit("ComDictMapper.edit", comDict);
	}
	
	/**
	 * 更改
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void change(ComDict comDict) throws Exception {
		Date nowTime = new Date();
		comDict.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		comDict.setModifyTime(nowTime);
		comDict.setLastModifyTime(this.findById(comDict.getDictId()).getModifyTime());
		if(comDict.getModifyTime().compareTo(comDict.getLastModifyTime()) == 0){
			comDict.setModifyTime(MapleDateUtil.getNextSecond(comDict.getModifyTime()));
		}
		dao.edit("ComDictMapper.change", comDict);
	}

	/**
	 * 删除 
	 * @param String dictId
	 * @throws Exception
	 */
	public void deleteById(String dictId) throws Exception {
		PageData pd = new PageData();
		pd.put("dictId",dictId);
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComDictMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComDictMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String dictId
	 * @return ComDict
	 * @throws Exception
	 */
	public ComDict findById(String dictId) throws Exception {
		PageData pd = new PageData();
		pd.put("dictId",dictId);
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(ComDict)数据 
	 * @param PageData pd
	 * @return ComDict
	 * @throws Exception
	 */
	public ComDict findByPd(PageData pd) throws Exception {
		return (ComDict) dao.findForObject("ComDictMapper.findByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDict> listByPd(PageData pd) throws Exception {
		return (List<ComDict>) dao.findForList("ComDictMapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDict> otherHave(ComDict comDict) throws Exception {
		return (List<ComDict>) dao.findForList("ComDictMapper.otherHave", comDict);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> otherHaveCode(String dictId, String dictCode) throws Exception {
		ComDict comDict = new ComDict();
		comDict.setDictId(dictId);
		comDict.setDictCode(dictCode);
		return this.otherHave(comDict);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComDictMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
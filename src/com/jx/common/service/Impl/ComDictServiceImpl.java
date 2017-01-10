package com.jx.common.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComDict;
import com.jx.common.service.ComDictService;
import com.jx.common.util.MapleUtil;

@Service("comDictService")
public class ComDictServiceImpl implements ComDictService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
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
		List<ComDict> comDictList = hasCode(type);
		if(MapleUtil.notEmptyList(comDictList)){
			String dictType = comDictList.get(0).getDictType();
			if("01".equals(dictType)){
				
			}else if("02".equals(dictType)){
				
			}
		}
		return (String) dao.findForObject("ComDictMapper.getDisplayName", pd);
	}
	
	/**
	 * 根据code 获取所有直接子
	 * @param String code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDict> listSelect(String type) throws Exception {
		List<ComDict> comDictList = hasCode(type);
		if(MapleUtil.notEmptyList(comDictList)){
			String dictType = comDictList.get(0).getDictType();
			if("01".equals(dictType)){
				
			}else if("02".equals(dictType)){
				
			}
		}
		return (List<ComDict>) dao.findForList("ComDictMapper.listSelect", type);
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDict> listByParentId(String parentId) throws Exception {
		return (List<ComDict>) dao.findForList("ComDictMapper.listByParentId", parentId);
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
		dao.add("ComDictMapper.add", comDict);
	}
	
	/**
	 * 修改 
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void edit(ComDict comDict) throws Exception {
		dao.edit("ComDictMapper.edit", comDict);
	}
	
	/**
	 * 更改
	 * @param ComDict comDict
	 * @throws Exception
	 */
	public void change(ComDict comDict) throws Exception {
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
	 * 通过id获取(PageData)数据 
	 * @param String dictId
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(String dictId) throws Exception {
		PageData pd = new PageData();
		pd.put("dictId",dictId);
		return this.findPdByPd(pd);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ComDictMapper.findPdByPd", pd);
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
	public List<ComDict> has(ComDict comDict) throws Exception {
		return (List<ComDict>) dao.findForList("ComDictMapper.has", comDict);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> hasCode(String dictCode) throws Exception {
		ComDict comDict = new ComDict();
		comDict.setDictCode(dictCode);
		return this.has(comDict);
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
package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgConfig;

@Service("bgConfigService")
public class BgConfigService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 根据configType 获取配置
	 * @return BgConfig
	 * @throws Exception
	 */
	public BgConfig findByConfigType(String configType) throws Exception {
		return (BgConfig) dao.findForObject("BgConfigMapper.findByConfigType", configType);
	}
	
	/**
	 * 根据configType 获取配置
	 * @return BgConfig
	 * @throws Exception
	 */
	public BgConfig findSessionConfig(String configType) {
		Session session = SecurityUtils.getSubject().getSession();
		BgConfig bgConfig = (BgConfig) session.getAttribute(configType);
		if (bgConfig == null) {
			try {
				bgConfig = this.findByConfigType(configType);
				session.setAttribute(configType,bgConfig);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bgConfig;
	}

	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgConfig bgConfig
	 * @return 主键 id
	 * @throws Exception
	 */
	public int add(BgConfig bgConfig) throws Exception {
		return (int)dao.add("BgConfigMapper.add", bgConfig);
	}
	
	/**
	 * 新增
	 * @param PageData pd
	 * @return 主键 id
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("BgConfigMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param BgConfig bgConfig
	 * @throws Exception
	 */
	public void edit(BgConfig bgConfig) throws Exception {
		dao.update("BgConfigMapper.edit", bgConfig);
	}

	/**
	 * 修改 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.update("BgConfigMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param int id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("BgConfigMapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgConfigMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String ids) throws Exception {
		dao.delete("BgConfigMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param int id
	 * @return BgConfig
	 * @throws Exception
	 */
	public BgConfig findById(int id) throws Exception {
		return (BgConfig) dao.findForObject("BgConfigMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param int id
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("BgConfigMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgConfigMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgConfig> listAllByPd(PageData pd) throws Exception {
		return (List<BgConfig>) dao.findForList("BgConfigMapper.listAllByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgConfigMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
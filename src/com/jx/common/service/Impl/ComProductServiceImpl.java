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
import com.jx.common.util.RandomUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.entity.ComProduct;
import com.jx.common.service.ComProductService;

@Service("comProductService")
public class ComProductServiceImpl implements ComProductService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComProduct comProduct
	 * @throws Exception
	 */
	public void add(ComProduct comProduct) throws Exception {
		
		Date nowTime = new Date();
		String productId = UuidUtil.get32UUID();
		comProduct.setProductId(productId);
		comProduct.setProductStatus("00");
		comProduct.setHeadImgSrc(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComProduct.PATH_IMG_PRODUCT_HEADIMG, comProduct.getHeadImgSrc())
				.replaceAll(",", Const.REG_COM_SPLIT));
		comProduct.setImgSrc1(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComProduct.PATH_IMG_PRODUCT_IMG1, comProduct.getImgSrc1())
				.replaceAll(",", Const.REG_COM_SPLIT));
		comProduct.setImgSrc2(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComProduct.PATH_IMG_PRODUCT_IMG2, comProduct.getImgSrc2())
				.replaceAll(",", Const.REG_COM_SPLIT));
		comProduct.setImgSrc3(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComProduct.PATH_IMG_PRODUCT_IMG3, comProduct.getImgSrc3())
				.replaceAll(",", Const.REG_COM_SPLIT));
		comProduct.setEffective("01");
		comProduct.setCreateUserId(ShiroSessionUtil.getUserId());
		comProduct.setCreateTime(nowTime);
		comProduct.setModifyUserId(ShiroSessionUtil.getUserId());
		comProduct.setModifyTime(nowTime);
		
		dao.add("ComProductMapper.add", comProduct);
		
		this.updateCode(productId);
	}
	
	/**
	 * 生成code 
	 * @param String productId
	 * @throws Exception
	 */
	public void updateCode(String productId) throws Exception {
		//生成固定id
		PageData pd = new PageData();
		pd.put("productId", productId);
		pd.put("startC", "sp");
		pd.put("startN", 100001);
		pd.put("addValue", RandomUtil.getRandomRange(11, 20));
		dao.update("ComProductMapper.updateCode", pd);
	}
	
	/**
	 * 修改 
	 * @param ComProduct comProduct
	 * @throws Exception
	 */
	public void edit(ComProduct comProduct) throws Exception {
	
		comProduct.setHeadImgSrc(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComProduct.PATH_IMG_PRODUCT_HEADIMG, comProduct.getHeadImgSrc()));
		comProduct.setImgSrc1(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComProduct.PATH_IMG_PRODUCT_IMG1, comProduct.getImgSrc1()));
		comProduct.setImgSrc2(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComProduct.PATH_IMG_PRODUCT_IMG2, comProduct.getImgSrc2()));
		comProduct.setImgSrc3(MapleFileUtil.transfer(Const.PATH_FILEUPCACHE, 
			ComProduct.PATH_IMG_PRODUCT_IMG3, comProduct.getImgSrc3()));
		Date nowTime = new Date();
		comProduct.setModifyUserId(ShiroSessionUtil.getUserId());
		comProduct.setModifyTime(nowTime);
	
		dao.update("ComProductMapper.edit", comProduct);
	}
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String productId
	 * @throws Exception
	 */
	public void changeStatus(String flag, String productId) throws Exception {
		ComProduct comProduct = new ComProduct();
		if("00".equals(flag)){
			comProduct.setOldValue("01");
		}else if("01".equals(flag)){
			comProduct.setOldValue("00");
		}else{
			comProduct.setOldValue("flag");
		}
		comProduct.setProductStatus(flag);
		
		comProduct.setProductId(productId);
		Date nowTime = new Date();
		comProduct.setModifyUserId(ShiroSessionUtil.getUserId());
		comProduct.setModifyTime(nowTime);
		dao.update("ComProductMapper.changeStatus", comProduct);
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String productId
	 * @throws Exception
	 */
	public void changeEffective(String flag, String productId) throws Exception {
		ComProduct comProduct = new ComProduct();
		if("00".equals(flag)){
			comProduct.setOldValue("01");
		}else if("01".equals(flag)){
			comProduct.setOldValue("00");
		}else{
			comProduct.setOldValue("flag");
		}
		comProduct.setEffective(flag);
		
		comProduct.setProductId(productId);
		Date nowTime = new Date();
		comProduct.setModifyUserId(ShiroSessionUtil.getUserId());
		comProduct.setModifyTime(nowTime);
		dao.update("ComProductMapper.changeEffective", comProduct);
	}
	
	/**
	 * 删除 
	 * @param String productId
	 * @throws Exception
	 */
	public void deleteById(String productId) throws Exception {
		dao.delete("ComProductMapper.deleteById", productId);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("ComProductMapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String productId
	 * @return ComProduct
	 * @throws Exception
	 */
	public ComProduct findById(String productId) throws Exception {
		return (ComProduct) dao.findForObject("ComProductMapper.findById", productId);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProduct> listAll() throws Exception {
		return (List<ComProduct>) dao.findForList("ComProductMapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComProduct> otherHaveCode(String productId, String productCode) throws Exception {
		ComProduct comProduct = new ComProduct();
		comProduct.setProductId(productId);
		comProduct.setProductCode(productCode);
		return (List<ComProduct>) dao.findForList("ComProductMapper.otherHaveCode", comProduct);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComProductMapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}
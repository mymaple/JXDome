package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.BaseEntity.ValidationAdd;
import com.jx.common.config.BaseEntity.ValidationEdit;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComOrder;
import com.jx.common.entity.ComOrderDetail;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComOrderService;
import com.jx.common.service.ComOrderDetailService;

/** 
 * 类名称：BgOrderDetailController
 * 创建人：maple
 * 创建时间：2017-03-20
 */
@Controller
@RequestMapping(value="/background/orderDetail")
public class BgOrderDetailController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_order";
	
	@Resource(name="comOrderDetailService")
	private ComOrderDetailService comOrderDetailService;
	@Resource(name="comOrderService")
	private ComOrderService comOrderService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String orderId = pd.getString("orderId");								//订单商品 id 
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	comOrderDetailList = comOrderDetailService.listPage(bgPage);	//列出comOrderDetail列表
		
		mv.addObject("comOrderDetailList", comOrderDetailList);
		mv.addObject("comOrder", comOrderService.findById(orderId ));
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/orderDetail/bgOrderDetailList");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String orderId = pd.getString("orderId");
		if(comOrderService.findById(orderId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}
		ComOrderDetail comOrderDetail = new ComOrderDetail();
		comOrderDetail.setOrderId(orderId);
		comOrderDetail.setProductId("");
		comOrderDetail.setSummary("");
		comOrderDetail.setProductStyleId("");
		comOrderDetail.setHeadImgSrc("");
		comOrderDetail.setOriginalPrice("0.00");
		comOrderDetail.setCurrentPrice("0.00");
		comOrderDetail.setCount("0");
		comOrderDetail.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comOrderDetail);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/orderDetail/bgOrderDetailEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComOrderDetail comOrderDetail, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comOrderDetail");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String orderId = comOrderDetail.getOrderId();
		if(comOrderService.findById(orderId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comOrderDetailService.add(comOrderDetail);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String orderDetailId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComOrderDetail comOrderDetail = comOrderDetailService.findById(orderDetailId);	//根据ID读取
		if(comOrderDetail == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comOrderDetail);
		resultInfo.setResultCode("success");
		mv.setViewName("background/orderDetail/bgOrderDetailEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComOrderDetail comOrderDetail, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comOrderDetail");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		comOrderDetailService.edit(comOrderDetail);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String orderDetailId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comOrderDetailService.deleteById(orderDetailId);	//根据ID删除
		resultInfo.setResultCode("success");

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/toBatchDelete")
	@ResponseBody
	public Object toBatchDelete() throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		String ids = pd.getString("ids");
		if(MapleStringUtil.isEmpty(ids)){
			return AppUtil.returnResult(pd, resultInfo);
		}
		comOrderDetailService.batchDeleteByIds(ids.split(","));	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 更改有效性
	 */
	@RequestMapping(value="/changeEffective")
	@ResponseBody
	public Object changeEffective(@RequestParam String flag, @RequestParam String orderDetailId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comOrderDetailService.changeEffective(flag, orderDetailId);	
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/toExportExcel")
	public ModelAndView toExportExcel() throws Exception{
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("订单商品 主键id");		//0
		titles.add("订单商品 id");	//1
		titles.add("商品Id");	//2
		titles.add("商品名称");	//3
		titles.add("摘要");	//4
		titles.add("商品类型id");	//5
		titles.add("商品类型名称");	//6
		titles.add("产品头像");	//7
		titles.add("原价");	//8
		titles.add("现价");	//9
		titles.add("购买数量");	//10
		titles.add("排序编号");	//11
		titles.add("有效标志");	//12
		titles.add("创建人员id");	//13
		titles.add("创建时间");	//14
		titles.add("修改人员id");	//15
		titles.add("修改时间");	//16
		dataMap.put("titles", titles);
		List<ComOrderDetail> varOList = comOrderDetailService.listAll();
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getOrderDetailId());			//0
			vpd.put("var1",varOList.get(i).getOrderId());	//1
			vpd.put("var2", varOList.get(i).getProductId());	//2
			vpd.put("var3", varOList.get(i).getProductName());	//3
			vpd.put("var4", varOList.get(i).getSummary());	//4
			vpd.put("var5", varOList.get(i).getProductStyleId());	//5
			vpd.put("var6", varOList.get(i).getProductStyleName());	//6
			vpd.put("var7", varOList.get(i).getHeadImgSrc());	//7
			vpd.put("var8", varOList.get(i).getOriginalPrice());	//8
			vpd.put("var9", varOList.get(i).getCurrentPrice());	//9
			vpd.put("var10", varOList.get(i).getCount());	//10
			vpd.put("var11", varOList.get(i).getOrderNum());		//11
			vpd.put("var12", varOList.get(i).getEffective());	//12
			vpd.put("var13", varOList.get(i).getCreateUserId());	//13
			vpd.put("var14", varOList.get(i).getCreateTime());	//14
			vpd.put("var15", varOList.get(i).getModifyUserId());//15
			vpd.put("var16", varOList.get(i).getModifyTime());	//16
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUploadExcel")
	public ModelAndView toUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String pId = pd.getString("pId");//上级id
				
		if(comOrderService.findById(pId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}	
			
		mv.addObject("pId",pId);
		mv.addObject("controllerPath", "background_orderDetail");
		mv.setViewName("background/bgUploadExcel");

		mv.addObject(resultInfo);					
		return mv;
	}
	
	/**下载模版
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcelModel")
	public ModelAndView downExcelModel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("商品Id");	//0
		titles.add("摘要");	//1
		titles.add("商品类型id");	//2
		titles.add("产品头像");	//3
		titles.add("原价");	//4
		titles.add("现价");	//5
		titles.add("购买数量");	//6
		dataMap.put("titles", titles);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
		
	
	/**从EXCEL导入到数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/uploadExcel")
	public ModelAndView uploadExcel(
			@RequestParam(value="excel",required=false) MultipartFile file
			) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		if (null != file && !file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getProjectPath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "orderDetailexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComOrderDetail comOrderDetail = new ComOrderDetail();
		String orderId = pd.getString("pId");//上级id	
		comOrderDetail.setOrderId(orderId);
				
		/**
		 * var0 :商品Id;	//0
		 * var1 :摘要;	//1
		 * var2 :商品类型id;	//2
		 * var3 :产品头像;	//3
		 * var4 :原价;	//4
		 * var5 :现价;	//5
		 * var6 :购买数量;	//6
		 */
		for(int i=0;i<listPd.size();i++){	
			comOrderDetail.setOrderDetailId(this.get32UUID());
			comOrderDetail.setProductId(listPd.get(i).getString("var0"));
			comOrderDetail.setSummary(listPd.get(i).getString("var1"));
			comOrderDetail.setProductStyleId(listPd.get(i).getString("var2"));
			comOrderDetail.setHeadImgSrc(listPd.get(i).getString("var3"));
			comOrderDetail.setOriginalPrice(listPd.get(i).getString("var4"));
			comOrderDetail.setCurrentPrice(listPd.get(i).getString("var5"));
			comOrderDetail.setCount(listPd.get(i).getString("var6"));
			comOrderDetailService.add(comOrderDetail);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toInfo")
	public ModelAndView toInfo(@RequestParam String orderDetailId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComOrderDetail comOrderDetail = comOrderDetailService.findById(orderDetailId);	//根据ID读取
		if(comOrderDetail == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject(comOrderDetail);
		resultInfo.setResultCode("success");
		mv.setViewName("background/orderDetail/bgOrderDetailInfo");
		
		mv.addObject(resultInfo);						
		return mv;
	}	

}

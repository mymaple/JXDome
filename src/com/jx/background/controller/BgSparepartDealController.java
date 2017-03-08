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
import com.jx.common.entity.ComSparepartDeal;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComSparepartDealService;

/** 
 * 类名称：BgSparepartDealController
 * 创建人：maple
 * 创建时间：2017-03-06
 */
@Controller
@RequestMapping(value="/background/sparepartDeal")
public class BgSparepartDealController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_sparepartDeal";
	
	@Resource(name="comSparepartDealService")
	private ComSparepartDealService comSparepartDealService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	comSparepartDealList = comSparepartDealService.listPage(bgPage);	//列出comSparepartDeal列表
		
		mv.addObject("comSparepartDealList", comSparepartDealList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/sparepartDeal/bgSparepartDealList");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		Date nowTime = new Date();
		
		ComSparepartDeal comSparepartDeal = new ComSparepartDeal();
		comSparepartDeal.setSparepartDealCode("");
		comSparepartDeal.setAppUserId("");
		comSparepartDeal.setSparepartId("");
		comSparepartDeal.setCount("0");
		comSparepartDeal.setDealAmt("0.00");
		comSparepartDeal.setOrderTime(nowTime);
		comSparepartDeal.setOrderNum(String.valueOf(nowTime.getTime()));
		
		mv.addObject(comSparepartDeal);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/sparepartDeal/bgSparepartDealEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComSparepartDeal comSparepartDeal, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comSparepartDeal");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComSparepartDeal> comSparepartDealList = comSparepartDealService.otherHaveCode("", comSparepartDeal.getSparepartDealCode());
		if(MapleUtil.notEmptyList(comSparepartDealList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comSparepartDealService.add(comSparepartDeal);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String sparepartDealId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComSparepartDeal comSparepartDeal = comSparepartDealService.findById(sparepartDealId);	//根据ID读取
		if(comSparepartDeal == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comSparepartDeal);
		resultInfo.setResultCode("success");
		mv.setViewName("background/sparepartDeal/bgSparepartDealEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComSparepartDeal comSparepartDeal, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comSparepartDeal");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComSparepartDeal> comSparepartDealList = comSparepartDealService.otherHaveCode(comSparepartDeal.getSparepartDealId(), comSparepartDeal.getSparepartDealCode());	
		if(MapleUtil.notEmptyList(comSparepartDealList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comSparepartDealService.edit(comSparepartDeal);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String sparepartDealId, @RequestParam String sparepartDealCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComSparepartDeal> comSparepartDealList = comSparepartDealService.otherHaveCode(sparepartDealId, sparepartDealCode);	
		if(MapleUtil.emptyList(comSparepartDealList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String sparepartDealId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comSparepartDealService.deleteById(sparepartDealId);	//根据ID删除
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
		comSparepartDealService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("零部件交易 主键id");		//0
		titles.add("零部件交易订单号");	//1
		titles.add("零部件交易审核状态");	//2
		titles.add("零部件销售客户");	//3
		titles.add("零部件");	//4
		titles.add("出售数量");	//5
		titles.add("交易金额");	//6
		titles.add("订单日期");	//7
		titles.add("审核人");	//8
		titles.add("审核结果");	//9
		titles.add("审核日期");	//10
		titles.add("排序编号");	//11
		titles.add("有效标志");	//12
		titles.add("创建人员id");	//13
		titles.add("创建时间");	//14
		titles.add("修改人员id");	//15
		titles.add("修改时间");	//16
		dataMap.put("titles", titles);
		List<ComSparepartDeal> varOList = comSparepartDealService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getSparepartDealId());			//0
			vpd.put("var1", varOList.get(i).getSparepartDealCode());	//1
			vpd.put("var2", varOList.get(i).getSparepartDealStatus());	//2
			vpd.put("var3", varOList.get(i).getAppUserId());	//3
			vpd.put("var4", varOList.get(i).getSparepartId());	//4
			vpd.put("var5", varOList.get(i).getCount());	//5
			vpd.put("var6", varOList.get(i).getDealAmt());	//6
			vpd.put("var7", varOList.get(i).getOrderTime());	//7
			vpd.put("var8", varOList.get(i).getCheckId());	//8
			vpd.put("var9", varOList.get(i).getRemarks());	//9
			vpd.put("var10", varOList.get(i).getCheckTime());	//10
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		mv.addObject("controllerPath", "background_sparepartDeal");
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
		titles.add("零部件交易订单号");	//0
		titles.add("零部件销售客户");	//1
		titles.add("零部件");	//2
		titles.add("出售数量");	//3
		titles.add("交易金额");	//4
		titles.add("订单日期");	//5
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		if (null != file && !file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getProjectPath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "sparepartDealexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComSparepartDeal comSparepartDeal = new ComSparepartDeal();
				
		/**
		 * var0 :零部件交易订单号;	//0
		 * var1 :零部件销售客户;	//1
		 * var2 :零部件;	//2
		 * var3 :出售数量;	//3
		 * var4 :交易金额;	//4
		 * var5 :订单日期;	//5
		 */
		for(int i=0;i<listPd.size();i++){	
			comSparepartDeal.setSparepartDealId(this.get32UUID());
			comSparepartDeal.setSparepartDealCode(listPd.get(i).getString("var0"));
			comSparepartDeal.setAppUserId(listPd.get(i).getString("var1"));
			comSparepartDeal.setSparepartId(listPd.get(i).getString("var2"));
			comSparepartDeal.setCount(listPd.get(i).getString("var3"));
			comSparepartDeal.setDealAmt(listPd.get(i).getString("var4"));
			comSparepartDeal.setOrderTimeStr(listPd.get(i).getString("var5"));
			comSparepartDealService.add(comSparepartDeal);
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
	@RequestMapping(value="/toChangeStatus")
	@ResponseBody
	public Object toChangeStatus(@RequestParam String change, @RequestParam String sparepartDealId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		ComSparepartDeal comSparepartDeal = comSparepartDealService.findById(sparepartDealId);	//根据ID读取
		if(comSparepartDeal == null){
			return AppUtil.returnResult(pd, resultInfo);
		}
		
		String sparepartDealStatus = comSparepartDeal.getSparepartDealStatus();
		ComSparepartDeal comSparepartDealChange = new ComSparepartDeal();
		comSparepartDealChange.setSparepartDealId(sparepartDealId);
		//申请
		if("01".equals(change)&&"00".equals(sparepartDealStatus)){
			comSparepartDealChange.setSparepartDealStatus("01");
			comSparepartDealService.change(comSparepartDealChange);
			resultInfo.setResultCode("success");
		}else
		//成功
		if("02".equals(change)&&"01".equals(sparepartDealStatus)){
			comSparepartDealChange.setSparepartDealStatus("02");
			comSparepartDealChange.setCheckId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
			comSparepartDealChange.setCheckTime(new Date());
			comSparepartDealChange.setRemarks("通过！");
			comSparepartDealService.toPass(comSparepartDeal, comSparepartDealChange);
			resultInfo.setResultCode("success");
		}else
		//失败
		if("03".equals(change)&&"01".equals(sparepartDealStatus)){
			comSparepartDealChange.setSparepartDealStatus("03");
			comSparepartDealChange.setCheckId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
			comSparepartDealChange.setCheckTime(new Date());
			comSparepartDealChange.setRemarks("失败！");
			comSparepartDealService.change(comSparepartDealChange);
			resultInfo.setResultCode("success");
		}else
		//重新申请
		if("04".equals(change)&&"03".equals(sparepartDealStatus)){
			comSparepartDealChange.setSparepartDealStatus("01");
			comSparepartDealService.change(comSparepartDealChange);
			resultInfo.setResultCode("success");
		}
		
		
		return AppUtil.returnResult(pd, resultInfo);
	}	

}

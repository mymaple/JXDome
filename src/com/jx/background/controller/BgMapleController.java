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
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.config.ValidationAdd;
import com.jx.common.config.ValidationEdit;
import com.jx.background.entity.BgMaple;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.background.service.BgMapleService;

/** 
 * 类名称：BgMapleController
 * 创建人：maple
 * 创建时间：2017-01-11
 */
@Controller
@RequestMapping(value="/background/maple")
public class BgMapleController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background/maple";
	
	@Resource(name="bgMapleService")
	private BgMapleService bgMapleService;
	
	
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
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	bgMapleList = bgMapleService.listPage(bgPage);	//列出bgMaple列表
			
		mv.addObject("bgMapleList", bgMapleList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/maple/bgMapleList");

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
		BgMaple bgMaple = new BgMaple();
		bgMaple.setMapleCode("");
		bgMaple.setMapleName("");
		bgMaple.setMapleType("01");
		bgMaple.setControllerPackage("bg");
		bgMaple.setEntityPackage("bg");
		bgMaple.setOrderNum(String.valueOf(nowTime.getTime()));
		
		mv.addObject(bgMaple);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/maple/bgMapleEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) BgMaple bgMaple, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgMaple");
			mv.addObject(resultInfo);
			return mv; 
		}
		
		List<BgMaple> bgMapleList = bgMapleService.hasCode(bgMaple.getMapleId(), bgMaple.getMapleCode());
		if(MapleUtil.notEmptyList(bgMapleList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		Date nowTime = new Date();
		bgMaple.setMapleId(this.get32UUID());
		bgMaple.setMapleStatus("00");
		bgMaple.setEffective("01");
		bgMaple.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setCreateTime(nowTime);
		bgMaple.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setModifyTime(nowTime);
			
		bgMapleService.add(bgMaple);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String mapleId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		BgMaple bgMaple = bgMapleService.findById(mapleId);	//根据ID读取
		if(bgMaple == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(bgMaple);
		resultInfo.setResultCode("success");
		mv.setViewName("background/maple/bgMapleEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) BgMaple bgMaple, BindingResult result) throws Exception{
		logBefore(logger, "修改bgMaple");
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgMaple");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgMaple> bgMapleList = bgMapleService.hasCode(bgMaple.getMapleId(), bgMaple.getMapleCode());	
		if(MapleUtil.notEmptyList(bgMapleList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		Date nowTime = new Date();
	        
		bgMaple.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setModifyTime(nowTime);
	        
		bgMapleService.edit(bgMaple);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/hasCode")
	@ResponseBody
	public Object hasCode(@RequestParam String mapleId, @RequestParam String mapleCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<BgMaple> bgMapleList = bgMapleService.hasCode(mapleId, mapleCode);	
		if(MapleUtil.emptyList(bgMapleList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String mapleId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		bgMapleService.deleteById(mapleId);	//根据ID删除
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
		if(null == ids || "".equals(ids)){
			return AppUtil.returnResult(pd, resultInfo);
		}
		bgMapleService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("代码生成 主键id");
		titles.add("代码生成代号");	//1
		titles.add("代码生成名称");	//2
		titles.add("代码生成类型");	//3
		titles.add("代码生成状态");	//4
		titles.add("控制器包代号");	//5
		titles.add("实体类包代号");	//6
		titles.add("排序编号");	//7
		titles.add("有效标志");	//8
		titles.add("创建人员id");	//9
		titles.add("创建时间");	//10
		titles.add("修改人员id");	//11
		titles.add("修改时间");	//12
		dataMap.put("titles", titles);
		List<BgMaple> varOList = bgMapleService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
				
			vpd.put("var0",varOList.get(i).getMapleId());
			vpd.put("var1", varOList.get(i).getMapleCode());	//1
			vpd.put("var2", varOList.get(i).getMapleName());	//2
			vpd.put("var3", varOList.get(i).getMapleType());	//3
			vpd.put("var4", varOList.get(i).getMapleStatus());	//4
			vpd.put("var5", varOList.get(i).getControllerPackage());	//5
			vpd.put("var6", varOList.get(i).getEntityPackage());	//6
			vpd.put("var7", varOList.get(i).getOrderNum());	//7
			vpd.put("var8", varOList.get(i).getEffective());	//8
			vpd.put("var9", varOList.get(i).getCreateUserId());	//9
			vpd.put("var10", varOList.get(i).getCreateTime());	//10
			vpd.put("var11", varOList.get(i).getModifyUserId());	//11
			vpd.put("var12", varOList.get(i).getModifyTime());	//12
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

		mv.addObject("controllerPath", RIGHTS_BG_MENUCODE_STR);
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
		logBefore(logger, "导出bgUser到excel");
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("代码生成代号");	//0
		titles.add("代码生成名称");	//1
		titles.add("代码生成类型");	//2
		titles.add("控制器包代号");	//3
		titles.add("实体类包代号");	//4
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
		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "mapleexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
			
			
		BgMaple bgMaple = new BgMaple();
		Date nowTime = new Date();
		bgMaple.setMapleStatus("00");
		bgMaple.setEffective("01");
		bgMaple.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setCreateTime(nowTime);
		bgMaple.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMaple.setModifyTime(nowTime);
				
		/**
		 * var0 :代码生成代号;	//0
		 * var1 :代码生成名称;	//1
		 * var2 :代码生成类型;	//2
		 * var3 :控制器包代号;	//3
		 * var4 :实体类包代号;	//4
		 */
		for(int i=0;i<listPd.size();i++){	
			bgMaple.setMapleId(this.get32UUID());
			bgMaple.setMapleCode(listPd.get(i).getString("var0"));
			bgMaple.setMapleName(listPd.get(i).getString("var1"));
			bgMaple.setMapleType(listPd.get(i).getString("var2"));
			bgMaple.setControllerPackage(listPd.get(i).getString("var3"));
			bgMaple.setEntityPackage(listPd.get(i).getString("var4"));
			nowTime = new Date();
			bgMaple.setOrderNum(String.valueOf(nowTime.getTime()));
			bgMapleService.add(bgMaple);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

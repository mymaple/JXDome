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
import com.jx.background.entity.BgMaple;
import com.jx.background.entity.BgMapleDetail;
import com.jx.background.entity.BgMapleDetail.ValidationAdd;
import com.jx.background.entity.BgMapleDetail.ValidationEdit;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.background.service.BgMapleDetailService;
import com.jx.background.service.BgMapleService;

/** 
 * 类名称：BgMapleDetailController
 * 创建人：maple
 * 创建时间：2017-01-11
 */
@Controller
@RequestMapping(value="/background/mapleDetail")
public class BgMapleDetailController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background/maple";
	
	@Resource(name="bgMapleDetailService")
	private BgMapleDetailService bgMapleDetailService;
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

		String mapleId = pd.getString("mapleId");
		
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	bgMapleDetailList = bgMapleDetailService.listPage(bgPage);	//列出bgMapleDetail列表
		
		mv.addObject("bgMapleDetailList", bgMapleDetailList);
		mv.addObject("bgMaple", bgMapleService.findById(mapleId));
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/mapleDetail/bgMapleDetailList");

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
		
		String mapleId = pd.getString("mapleId");
		if(bgMapleService.findById(mapleId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}
		BgMapleDetail bgMapleDetail = new BgMapleDetail();
		bgMapleDetail.setMapleId(mapleId);
		mv.addObject(bgMapleDetail);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/mapleDetail/bgMapleDetailEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) BgMapleDetail bgMapleDetail, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgMapleDetail");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String mapleId = bgMapleDetail.getMapleId();
		if(bgMapleService.findById(mapleId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<BgMapleDetail> bgMapleDetailList = bgMapleDetailService.hasCode(bgMapleDetail.getMapleDetailCode());	
		if(MapleUtil.notEmptyList(bgMapleDetailList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		Date nowTime = new Date();
		bgMapleDetail.setMapleDetailId(this.get32UUID());
		bgMapleDetail.setMapleDetailStatus("00");
		bgMapleDetail.setEffective("01");
		bgMapleDetail.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMapleDetail.setCreateTime(nowTime);
		bgMapleDetail.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMapleDetail.setModifyTime(nowTime);
		if(MapleStringUtil.isEmpty(bgMapleDetail.getOrderNum())){
			bgMapleDetail.setOrderNum(String.valueOf(nowTime.getTime()));
		}
			
		bgMapleDetailService.add(bgMapleDetail);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String mapleDetailId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		BgMapleDetail bgMapleDetail = bgMapleDetailService.findById(mapleDetailId);	//根据ID读取
		if(bgMapleDetail == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(bgMapleDetail);
		resultInfo.setResultCode("success");
		mv.setViewName("background/mapleDetail/bgMapleDetailEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) BgMapleDetail bgMapleDetail, BindingResult result) throws Exception{
		logBefore(logger, "修改bgMapleDetail");
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgMapleDetail");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgMapleDetail> bgMapleDetailList = bgMapleDetailService.hasCode(bgMapleDetail.getMapleDetailCode());	
		if(MapleUtil.notEmptyList(bgMapleDetailList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		Date nowTime = new Date();
	        
		bgMapleDetail.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMapleDetail.setModifyTime(nowTime);
	        
		bgMapleDetailService.edit(bgMapleDetail);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/hasCode")
	@ResponseBody
	public Object hasCode(@RequestParam String mapleDetailCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<BgMapleDetail> bgMapleDetailList = bgMapleDetailService.hasCode(mapleDetailCode);	
		if(MapleUtil.emptyList(bgMapleDetailList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String mapleDetailId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		bgMapleDetailService.deleteById(mapleDetailId);	//根据ID删除
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
		bgMapleDetailService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("代码生成详细 主键id");
		titles.add("代码生成详细代号");	//1
		titles.add("代码生成详细名称");	//2
		titles.add("代码生成详细类型");	//3
		titles.add("代码生成详细状态");	//4
		titles.add("代码生成详情代号（大写）");	//5
		titles.add("代码生成id");	//6
		titles.add("总长度");	//7
		titles.add("小数长度");	//8
		titles.add("类型代号");	//9
		titles.add("是否录入");	//10
		titles.add("是否null");	//11
		titles.add("是否主键");	//12
		titles.add("默认值");	//13
		titles.add("排序编号");	//14
		titles.add("有效性");	//15
		titles.add("创建人员id");	//16
		titles.add("创建时间");	//17
		titles.add("修改人员id");	//18
		titles.add("修改时间");	//19
		dataMap.put("titles", titles);
		List<BgMapleDetail> varOList = bgMapleDetailService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
				
			vpd.put("var0",varOList.get(i).getMapleDetailId());
			vpd.put("var1", varOList.get(i).getMapleDetailCode());	//1
			vpd.put("var2", varOList.get(i).getMapleDetailName());	//2
			vpd.put("var3", varOList.get(i).getMapleDetailType());	//3
			vpd.put("var4", varOList.get(i).getMapleDetailStatus());	//4
			vpd.put("var5", varOList.get(i).getMapleDetailCodeUpper());	//5
			vpd.put("var6", varOList.get(i).getMapleId());	//6
			vpd.put("var7", varOList.get(i).getTotalLength());	//7
			vpd.put("var8", varOList.get(i).getDecimalLength());	//8
			vpd.put("var9", varOList.get(i).getTypeCode());	//9
			vpd.put("var10", varOList.get(i).getIsEdit());	//10
			vpd.put("var11", varOList.get(i).getIsNull());	//11
			vpd.put("var12", varOList.get(i).getIsKey());	//12
			vpd.put("var13", varOList.get(i).getDefaultValue());	//13
			vpd.put("var14", varOList.get(i).getOrderNum());	//14
			vpd.put("var15", varOList.get(i).getEffective());	//15
			vpd.put("var16", varOList.get(i).getCreateUserId());	//16
			vpd.put("var17", varOList.get(i).getCreateTime());	//17
			vpd.put("var18", varOList.get(i).getModifyUserId());	//18
			vpd.put("var19", varOList.get(i).getModifyTime());	//19
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
		titles.add("代码生成详细代号");	//0
		titles.add("代码生成详细名称");	//1
		titles.add("代码生成详细类型");	//2
		titles.add("总长度");	//6
		titles.add("小数长度");	//7
		titles.add("类型代号");	//8
		titles.add("是否录入");	//9
		titles.add("是否null");	//10
		titles.add("是否主键");	//11
		titles.add("默认值");	//12
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

		Date nowTime = new Date();
		if (null != file && !file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "mapleDetailexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
			
			
		BgMapleDetail bgMapleDetail = new BgMapleDetail();
		bgMapleDetail.setMapleDetailStatus("00");
		bgMapleDetail.setMapleDetailCodeUpper("");
		bgMapleDetail.setMapleId("");
		bgMapleDetail.setOrderNum(String.valueOf(nowTime.getTime()));
		bgMapleDetail.setEffective("01");
		bgMapleDetail.setCreateUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMapleDetail.setCreateTime(nowTime);
		bgMapleDetail.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		bgMapleDetail.setModifyTime(nowTime);
			
		/**
		 * var0 :代码生成详细代号;	//0
		 * var1 :代码生成详细名称;	//1
		 * var2 :代码生成详细类型;	//2
		 * var3 :总长度;	//3
		 * var4 :小数长度;	//4
		 * var5 :类型代号;	//5
		 * var6 :是否录入;	//6
		 * var7 :是否null;	//7
		 * var8 :是否主键;	//8
		 * var9 :默认值;	//9
		 */
		for(int i=0;i<listPd.size();i++){	
			bgMapleDetail.setMapleDetailId(this.get32UUID());
			bgMapleDetail.setMapleDetailCode(listPd.get(i).getString("var0"));
			bgMapleDetail.setMapleDetailName(listPd.get(i).getString("var1"));
			bgMapleDetail.setMapleDetailType(listPd.get(i).getString("var2"));
			bgMapleDetail.setTotalLength(listPd.get(i).getString("var3"));
			bgMapleDetail.setDecimalLength(listPd.get(i).getString("var4"));
			bgMapleDetail.setTypeCode(listPd.get(i).getString("var5"));
			bgMapleDetail.setIsEdit(listPd.get(i).getString("var6"));
			bgMapleDetail.setIsNull(listPd.get(i).getString("var7"));
			bgMapleDetail.setIsKey(listPd.get(i).getString("var8"));
			bgMapleDetail.setDefaultValue(listPd.get(i).getString("var9"));
			bgMapleDetailService.add(bgMapleDetail);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

package com.jx.${bgMaple.controllerPackage}.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jx.${bgMaple.controllerPackage}.config.BgPage;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.${bgMaple.mapleEntityUpper};
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.${bgMaple.entityPackage}.service.${bgMaple.mapleEntityUpper}Service;

import net.sf.json.JSONArray;

/** 
 * 类名称：${bgMaple.mapleControllerUpper}Controller
 * 创建人：maple
 * 创建时间：${nowDate?string("yyyy-MM-dd")}
 */
@Controller
@RequestMapping(value="/${bgMaple.controllerPackage}/${bgMaple.mapleCode}")
public class ${bgMaple.mapleControllerUpper}Controller extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "${bgMaple.controllerPackage}/${bgMaple.mapleCode}";
	
	@Resource(name="${bgMaple.mapleEntityLower}Service")
	private ${bgMaple.mapleEntityUpper}Service ${bgMaple.mapleEntityLower}Service;
	
	
	/**
	 * 显示列表ztree
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("${bgMaple.controllerPackage}/bgResult");

		String parentId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");
		JSONArray arr = JSONArray.fromObject(${bgMaple.mapleEntityLower}Service.listInRank("0"));
		String json = arr.toString();
		json = json.replaceAll("${bgMaple.mapleCode}Id", "id").replaceAll("parentId", "pId")
				.replaceAll("${bgMaple.mapleCode}Name", "name").replaceAll("sub${bgMaple.mapleEntityUpper}List", "nodes")
				.replaceAll("has${bgMaple.mapleCodeUpper}", "checked").replaceAll("sub${bgMaple.mapleEntityUpper}Path", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", RIGHTS_BG_MENUCODE_STR);
		mv.addObject("parentId", parentId);
		mv.addObject("currentPage", pd.get("currentPage"));
		mv.addObject("showCount", pd.get("showCount"));
		resultInfo.setResultCode("success");
		mv.setViewName("${bgMaple.controllerPackage}/bgMainTree");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("${bgMaple.controllerPackage}/bgResult");

		String parentId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");
		pd.put("parentId", parentId);											//上级id
			
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	${bgMaple.mapleEntityLower}List = ${bgMaple.mapleEntityLower}Service.listPage(bgPage);	//列出${bgMaple.mapleEntityLower}列表
			
		mv.addObject("${bgMaple.mapleEntityLower}List", ${bgMaple.mapleEntityLower}List);
		mv.addObject("parent${bgMaple.mapleEntityUpper}", ${bgMaple.mapleEntityLower}Service.findById(parentId<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>));
		mv.addObject("parentId", parentId);
		mv.addObject("returnMsg", MapleStringUtil.isEmpty(pd.getString("returnMsg"))?"list":pd.getString("returnMsg")); //MSG=change 则为新增编辑或删除后跳转过来的
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${bgMaple.mapleControllerLower}List");

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
		mv.setViewName("${bgMaple.controllerPackage}/bgResult");

		String parentId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
			
		${bgMaple.mapleEntityUpper} parent${bgMaple.mapleEntityUpper} = ${bgMaple.mapleEntityLower}Service.findById(parentId<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>);
		if(!"0".equals(parentId) && parent${bgMaple.mapleEntityUpper}==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = new ${bgMaple.mapleEntityUpper}();
		${bgMaple.mapleEntityLower}.setParentId(parentId);
		mv.addObject("methodPath", "add");
		mv.addObject(${bgMaple.mapleEntityLower});
		resultInfo.setResultCode("success");
		mv.setViewName("${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${bgMaple.mapleControllerLower}Edit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Valid ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("${bgMaple.controllerPackage}/bgResult");

		Date nowTime = new Date();
		String parentId = comDict.getParentId();
		${bgMaple.mapleEntityUpper} parent${bgMaple.mapleEntityUpper} = ${bgMaple.mapleEntityLower}Service.findById(parentId<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>);
		if(!"0".equals(parentId) && parent${bgMaple.mapleEntityUpper}==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		if(result.hasErrors()) {
			mv.addObject(resultInfo);					
			return mv; 
        }
			
		${bgMaple.mapleEntityLower}.set${bgMaple.mapleCodeUpper}Id(this.get32UUID());
		<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isEdit == '00'>
			<#if bgMapleDetail.mapleDetailType == '01'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>""</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '02'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '03'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>nowTime</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '04'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0.00</#if>);
			</#if>
			</#if>
		</#list>
		if(MapleStringUtil.isEmpty(${bgMaple.mapleEntityLower}.getOrderNum())){
			${bgMaple.mapleEntityLower}.setOrderNum(String.valueOf(nowTime.getTime()));
		}
			
		${bgMaple.mapleEntityLower}Service.add(${bgMaple.mapleEntityLower});
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, @RequestParam String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("${bgMaple.controllerPackage}/bgResult");
		
		${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = ${bgMaple.mapleEntityLower}Service.findById(${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>);	//根据ID读取
		if(${bgMaple.mapleEntityLower} == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(${bgMaple.mapleEntityLower});
		resultInfo.setResultCode("success");
		mv.setViewName("${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${bgMaple.mapleControllerLower}Edit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Valid ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}, BindingResult result) throws Exception{
		logBefore(logger, "修改${bgMaple.mapleEntityLower}");
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("${bgMaple.controllerPackage}/bgResult");
		
		Date nowTime = new Date();
			
		if(result.hasErrors()) {
            return mv;  
        }
	        
		${bgMaple.mapleEntityLower}.setModifyUserId(String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId()));
		${bgMaple.mapleEntityLower}.setModifyTime(nowTime);
	        
		${bgMaple.mapleEntityLower}Service.edit(${bgMaple.mapleEntityLower});
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, @RequestParam String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		${bgMaple.mapleEntityLower}Service.deleteById(${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>);	//根据ID删除
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
		${bgMaple.mapleEntityLower}Service.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("${bgMaple.mapleName} 主键id");
		titles.add("上级 id");
	<#list bgMapleDetailList as bgMapleDetail>
		titles.add("${bgMapleDetail.mapleDetailName}");	//${bgMapleDetail_index+2}
	</#list>
		dataMap.put("titles", titles);
		List<${bgMaple.mapleEntityUpper}> varOList = ${bgMaple.mapleEntityLower}Service.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
				
			vpd.put("var0",varOList.get(i).get${bgMaple.mapleCodeUpper}Id());
			vpd.put("var1",varOList.get(i).getParentId());
		<#list bgMapleDetailList as bgMapleDetail>
			vpd.put("var${bgMapleDetail_index+2}", varOList.get(i).get${(bgMapleDetail.mapleDetailCodeUpper)}());	//${bgMapleDetail_index+2}
		</#list>
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

		String parentId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
			
		ComDict parentComDict = comDictService.findById(parentId);
		if(!"0".equals(parentId) && parentComDict==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("parentId",parentId);
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
<#list bgMapleDetailList as bgMapleDetail>
	<#if bgMapleDetail.isEdit == '01'>	
		titles.add("${bgMapleDetail.mapleDetailName}");	//${bgMapleDetail_index}
	</#if>
</#list>
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

		Date nowTime = new Date();
		String parentId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
		${bgMaple.mapleEntityUpper} parent${bgMaple.mapleEntityUpper} = ${bgMaple.mapleEntityLower}Service.findById(parentId<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>);
		if(!"0".equals(parentId) && parent${bgMaple.mapleEntityUpper}==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		if (null != file && !file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "${bgMaple.mapleCode}excel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
			
			
		${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = new ${bgMaple.mapleEntityUpper}();
		${bgMaple.mapleEntityLower}.setParentId(parentId);
		<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isEdit == '00'>
			<#if bgMapleDetail.mapleDetailType == '01'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>""</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '02'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '03'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>nowTime</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '04'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0.00</#if>);
			</#if>
			</#if>
		</#list>
			
		/**
		<#assign iii = 0 >
		<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isEdit == '01'>	
		 * var#{iii} :${bgMapleDetail.mapleDetailName};	//#{iii}
			<#assign iii=iii+1 />
			</#if>
		</#list>
		 */
		for(int i=0;i<listPd.size();i++){	
			${bgMaple.mapleEntityLower}.set${bgMaple.mapleCodeUpper}Id(this.get32UUID());
			<#assign iii = 0 >
			<#list bgMapleDetailList as bgMapleDetail>
				<#if bgMapleDetail.isEdit == '01'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(listPd.get(i).getString("var#{iii}"));
				<#assign iii=iii+1 />
				</#if>
			</#list>
			${bgMaple.mapleEntityLower}Service.add(${bgMaple.mapleEntityLower});
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("${bgMaple.controllerPackage}/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

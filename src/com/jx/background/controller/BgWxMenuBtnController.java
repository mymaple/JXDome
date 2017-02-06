package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.jx.background.entity.BgWxMenuBtn;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.background.service.BgWxMenuBtnService;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgWxMenuBtnController
 * 创建人：maple
 * 创建时间：2017-02-06
 */
@Controller
@RequestMapping(value="/background/wxMenuBtn")
public class BgWxMenuBtnController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_wxMenuBtn";
	
	@Resource(name="bgWxMenuBtnService")
	private BgWxMenuBtnService bgWxMenuBtnService;
	
	
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
		mv.setViewName("background/bgResult");

		if(MapleStringUtil.isEmpty(pd.getString("pId"))){
			pd.put("pId", "0");
		}
		JSONArray arr = JSONArray.fromObject(bgWxMenuBtnService.listInRank("0"));
		String json = arr.toString();
		json = json.replaceAll("wxMenuBtnId", "id").replaceAll("parentId", "pId")
				.replaceAll("wxMenuBtnName", "name").replaceAll("subBgWxMenuBtnList", "nodes")
				.replaceAll("hasWxMenuBtn", "checked").replaceAll("subBgWxMenuBtnPath", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", "background/wxMenuBtn");
		mv.addObject("pd", pd);
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgMainTree");
	
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
		mv.setViewName("background/bgResult");
		
		if(MapleStringUtil.isEmpty(pd.getString("pId"))){
			pd.put("pId", "0");
		}
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	bgWxMenuBtnList = bgWxMenuBtnService.listPage(bgPage);	//列出bgWxMenuBtn列表
		
		List<BgWxMenuBtn> parentList = new ArrayList<BgWxMenuBtn>();
		bgWxMenuBtnService.getParentList(parentList, pd.getString("pId"));			//导航栏链接
		
		mv.addObject("bgWxMenuBtnList", bgWxMenuBtnList);
		mv.addObject("parentList", parentList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/wxMenuBtn/bgWxMenuBtnList");

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
		
		String parentId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id	
		BgWxMenuBtn parentBgWxMenuBtn = bgWxMenuBtnService.findById(parentId);
		if(!"0".equals(parentId) && parentBgWxMenuBtn==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		BgWxMenuBtn bgWxMenuBtn = new BgWxMenuBtn();
		bgWxMenuBtn.setParentId(parentId);
		bgWxMenuBtn.setWxMenuBtnCode("");
		bgWxMenuBtn.setWxMenuBtnName("");
		bgWxMenuBtn.setWxMenuBtnType("01");
		bgWxMenuBtn.setMenuBtnKey("");
		bgWxMenuBtn.setMenuBtnUrl("");
		bgWxMenuBtn.setMedia_id("");
		bgWxMenuBtn.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(bgWxMenuBtn);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/wxMenuBtn/bgWxMenuBtnEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) BgWxMenuBtn bgWxMenuBtn, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgWxMenuBtn");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String parentId = bgWxMenuBtn.getParentId();
		BgWxMenuBtn parentBgWxMenuBtn = bgWxMenuBtnService.findById(parentId);
		if(!"0".equals(parentId) && parentBgWxMenuBtn==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<BgWxMenuBtn> bgWxMenuBtnList = bgWxMenuBtnService.otherHaveCode("", bgWxMenuBtn.getWxMenuBtnCode());
		if(MapleUtil.notEmptyList(bgWxMenuBtnList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		bgWxMenuBtnService.add(bgWxMenuBtn);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String wxMenuBtnId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		BgWxMenuBtn bgWxMenuBtn = bgWxMenuBtnService.findById(wxMenuBtnId);	//根据ID读取
		if(bgWxMenuBtn == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(bgWxMenuBtn);
		resultInfo.setResultCode("success");
		mv.setViewName("background/wxMenuBtn/bgWxMenuBtnEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) BgWxMenuBtn bgWxMenuBtn, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgWxMenuBtn");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgWxMenuBtn> bgWxMenuBtnList = bgWxMenuBtnService.otherHaveCode(bgWxMenuBtn.getWxMenuBtnId(), bgWxMenuBtn.getWxMenuBtnCode());	
		if(MapleUtil.notEmptyList(bgWxMenuBtnList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		bgWxMenuBtnService.edit(bgWxMenuBtn);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String wxMenuBtnId, @RequestParam String wxMenuBtnCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<BgWxMenuBtn> bgWxMenuBtnList = bgWxMenuBtnService.otherHaveCode(wxMenuBtnId, wxMenuBtnCode);	
		if(MapleUtil.emptyList(bgWxMenuBtnList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String wxMenuBtnId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		bgWxMenuBtnService.deleteInRank(wxMenuBtnId);	//根据ID删除
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
		bgWxMenuBtnService.batchDeleteInRank(ids.split(","));	//根据ID删除
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
		titles.add("微信菜单按钮 主键id");		//0
		titles.add("上级 id");					//1
		titles.add("微信菜单按钮代号");	//2
		titles.add("微信菜单按钮名称");	//3
		titles.add("微信菜单按钮类型");	//4
		titles.add("微信菜单按钮状态");	//5
		titles.add("菜单KEY值");	//6
		titles.add("网页链接");	//7
		titles.add("永久素材id");	//8
		titles.add("排序编号");	//9
		titles.add("有效标志");	//10
		titles.add("创建人员id");	//11
		titles.add("创建时间");	//12
		titles.add("修改人员id");	//13
		titles.add("修改时间");	//14
		dataMap.put("titles", titles);
		List<BgWxMenuBtn> varOList = bgWxMenuBtnService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getWxMenuBtnId());			//0
			vpd.put("var1",varOList.get(i).getParentId());						//1
			vpd.put("var2", varOList.get(i).getWxMenuBtnCode());	//2
			vpd.put("var3", varOList.get(i).getWxMenuBtnName());	//3
			vpd.put("var4", varOList.get(i).getWxMenuBtnType());	//4
			vpd.put("var5", varOList.get(i).getWxMenuBtnStatus());	//5
			vpd.put("var6", varOList.get(i).getMenuBtnKey());	//6
			vpd.put("var7", varOList.get(i).getMenuBtnUrl());	//7
			vpd.put("var8", varOList.get(i).getMedia_id());	//8
			vpd.put("var9", varOList.get(i).getOrderNum());		//9
			vpd.put("var10", varOList.get(i).getEffective());	//10
			vpd.put("var11", varOList.get(i).getCreateUserId());	//11
			vpd.put("var12", varOList.get(i).getCreateTime());	//12
			vpd.put("var13", varOList.get(i).getModifyUserId());//13
			vpd.put("var14", varOList.get(i).getModifyTime());	//14
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
		
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
			
		BgWxMenuBtn parentBgWxMenuBtn = bgWxMenuBtnService.findById(pId);
		if(!"0".equals(pId) && parentBgWxMenuBtn==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("pId",pId);

		mv.addObject("controllerPath", "background_wxMenuBtn");
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
		titles.add("微信菜单按钮代号");	//0
		titles.add("微信菜单按钮名称");	//1
		titles.add("微信菜单按钮类型");	//2
		titles.add("菜单KEY值");	//3
		titles.add("网页链接");	//4
		titles.add("永久素材id");	//5
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
		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "wxMenuBtnexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		BgWxMenuBtn bgWxMenuBtn = new BgWxMenuBtn();
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
		bgWxMenuBtn.setParentId(pId);
				
		/**
		 * var0 :微信菜单按钮代号;	//0
		 * var1 :微信菜单按钮名称;	//1
		 * var2 :微信菜单按钮类型;	//2
		 * var3 :菜单KEY值;	//3
		 * var4 :网页链接;	//4
		 * var5 :永久素材id;	//5
		 */
		for(int i=0;i<listPd.size();i++){	
			bgWxMenuBtn.setWxMenuBtnId(this.get32UUID());
			bgWxMenuBtn.setWxMenuBtnCode(listPd.get(i).getString("var0"));
			bgWxMenuBtn.setWxMenuBtnName(listPd.get(i).getString("var1"));
			bgWxMenuBtn.setWxMenuBtnType(listPd.get(i).getString("var2"));
			bgWxMenuBtn.setMenuBtnKey(listPd.get(i).getString("var3"));
			bgWxMenuBtn.setMenuBtnUrl(listPd.get(i).getString("var4"));
			bgWxMenuBtn.setMedia_id(listPd.get(i).getString("var5"));
			bgWxMenuBtnService.add(bgWxMenuBtn);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

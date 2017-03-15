package com.jx.weixin.controller;

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
import com.jx.common.entity.ComIntegralNote;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComIntegralNoteService;

/** 
 * 类名称：BgIntegralNoteController
 * 创建人：maple
 * 创建时间：2017-03-06
 */
@Controller
@RequestMapping(value="/background/integralNote")
public class WxIntegralNoteController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_integralNote";
	
	@Resource(name="comIntegralNoteService")
	private ComIntegralNoteService comIntegralNoteService;
	
	
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
		List<PageData>	comIntegralNoteList = comIntegralNoteService.listPage(bgPage);	//列出comIntegralNote列表
		
		mv.addObject("comIntegralNoteList", comIntegralNoteList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/integralNote/bgIntegralNoteList");

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
		
		ComIntegralNote comIntegralNote = new ComIntegralNote();
		comIntegralNote.setIntegralNoteCode("");
		comIntegralNote.setIntegralNoteName("");
		comIntegralNote.setIntegralNoteType("01");
		comIntegralNote.setAppUserId("");
		comIntegralNote.setIntegralDealCount("0.00");
		comIntegralNote.setIntegralCountBefore("0.00");
		comIntegralNote.setIntegralCountAfter("0.00");
		comIntegralNote.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comIntegralNote);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/integralNote/bgIntegralNoteEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComIntegralNote comIntegralNote, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comIntegralNote");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComIntegralNote> comIntegralNoteList = comIntegralNoteService.otherHaveCode("", comIntegralNote.getIntegralNoteCode());
		if(MapleUtil.notEmptyList(comIntegralNoteList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comIntegralNoteService.add(comIntegralNote);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String integralNoteId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComIntegralNote comIntegralNote = comIntegralNoteService.findById(integralNoteId);	//根据ID读取
		if(comIntegralNote == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comIntegralNote);
		resultInfo.setResultCode("success");
		mv.setViewName("background/integralNote/bgIntegralNoteEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComIntegralNote comIntegralNote, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comIntegralNote");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComIntegralNote> comIntegralNoteList = comIntegralNoteService.otherHaveCode(comIntegralNote.getIntegralNoteId(), comIntegralNote.getIntegralNoteCode());	
		if(MapleUtil.notEmptyList(comIntegralNoteList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comIntegralNoteService.edit(comIntegralNote);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String integralNoteId, @RequestParam String integralNoteCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComIntegralNote> comIntegralNoteList = comIntegralNoteService.otherHaveCode(integralNoteId, integralNoteCode);	
		if(MapleUtil.emptyList(comIntegralNoteList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String integralNoteId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comIntegralNoteService.deleteById(integralNoteId);	//根据ID删除
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
		comIntegralNoteService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("积分记录 主键id");		//0
		titles.add("积分记录代号");	//1
		titles.add("积分记录名称");	//2
		titles.add("积分记录类型");	//3
		titles.add("积分交易状态");	//4
		titles.add("平台用户");	//5
		titles.add("积分交易数量");	//6
		titles.add("交易前积分数量");	//7
		titles.add("交易后积分数量");	//8
		titles.add("排序编号");	//9
		titles.add("有效标志");	//10
		titles.add("创建人员id");	//11
		titles.add("创建时间");	//12
		titles.add("修改人员id");	//13
		titles.add("修改时间");	//14
		dataMap.put("titles", titles);
		List<ComIntegralNote> varOList = comIntegralNoteService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getIntegralNoteId());			//0
			vpd.put("var1", varOList.get(i).getIntegralNoteCode());	//1
			vpd.put("var2", varOList.get(i).getIntegralNoteName());	//2
			vpd.put("var3", varOList.get(i).getIntegralNoteType());	//3
			vpd.put("var4", varOList.get(i).getIntegralDealStatus());	//4
			vpd.put("var5", varOList.get(i).getAppUserId());	//5
			vpd.put("var6", varOList.get(i).getIntegralDealCount());	//6
			vpd.put("var7", varOList.get(i).getIntegralCountBefore());	//7
			vpd.put("var8", varOList.get(i).getIntegralCountAfter());	//8
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		mv.addObject("controllerPath", "background_integralNote");
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
		titles.add("积分记录代号");	//0
		titles.add("积分记录名称");	//1
		titles.add("积分记录类型");	//2
		titles.add("平台用户");	//3
		titles.add("积分交易数量");	//4
		titles.add("交易前积分数量");	//5
		titles.add("交易后积分数量");	//6
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "integralNoteexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComIntegralNote comIntegralNote = new ComIntegralNote();
				
		/**
		 * var0 :积分记录代号;	//0
		 * var1 :积分记录名称;	//1
		 * var2 :积分记录类型;	//2
		 * var3 :平台用户;	//3
		 * var4 :积分交易数量;	//4
		 * var5 :交易前积分数量;	//5
		 * var6 :交易后积分数量;	//6
		 */
		for(int i=0;i<listPd.size();i++){	
			comIntegralNote.setIntegralNoteId(this.get32UUID());
			comIntegralNote.setIntegralNoteCode(listPd.get(i).getString("var0"));
			comIntegralNote.setIntegralNoteName(listPd.get(i).getString("var1"));
			comIntegralNote.setIntegralNoteType(listPd.get(i).getString("var2"));
			comIntegralNote.setAppUserId(listPd.get(i).getString("var3"));
			comIntegralNote.setIntegralDealCount(listPd.get(i).getString("var4"));
			comIntegralNote.setIntegralCountBefore(listPd.get(i).getString("var5"));
			comIntegralNote.setIntegralCountAfter(listPd.get(i).getString("var6"));
			comIntegralNoteService.add(comIntegralNote);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

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
import com.jx.common.entity.ComInvite;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComInviteService;

/** 
 * 类名称：BgInviteController
 * 创建人：maple
 * 创建时间：2017-02-20
 */
@Controller
@RequestMapping(value="/background/invite")
public class BgInviteController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_invite";
	
	@Resource(name="comInviteService")
	private ComInviteService comInviteService;
	
	
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
		List<PageData>	comInviteList = comInviteService.listPage(bgPage);	//列出comInvite列表
		
		mv.addObject("comInviteList", comInviteList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/invite/bgInviteList");

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
		
		ComInvite comInvite = new ComInvite();
		comInvite.setInviteCode("");
		comInvite.setInviteName("");
		comInvite.setInviteType("01");
		comInvite.setInviteUserId("");
		comInvite.setInvitedUserId("");
		comInvite.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comInvite);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/invite/bgInviteEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComInvite comInvite, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comInvite");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComInvite> comInviteList = comInviteService.otherHaveCode("", comInvite.getInviteCode());
		if(MapleUtil.notEmptyList(comInviteList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comInviteService.add(comInvite);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String inviteId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComInvite comInvite = comInviteService.findById(inviteId);	//根据ID读取
		if(comInvite == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comInvite);
		resultInfo.setResultCode("success");
		mv.setViewName("background/invite/bgInviteEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComInvite comInvite, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comInvite");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComInvite> comInviteList = comInviteService.otherHaveCode(comInvite.getInviteId(), comInvite.getInviteCode());	
		if(MapleUtil.notEmptyList(comInviteList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comInviteService.edit(comInvite);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String inviteId, @RequestParam String inviteCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComInvite> comInviteList = comInviteService.otherHaveCode(inviteId, inviteCode);	
		if(MapleUtil.emptyList(comInviteList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String inviteId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comInviteService.deleteById(inviteId);	//根据ID删除
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
		comInviteService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("邀请 主键id");		//0
		titles.add("邀请代号");	//1
		titles.add("邀请名称");	//2
		titles.add("邀请类型");	//3
		titles.add("邀请状态");	//4
		titles.add("邀请人id");	//5
		titles.add("被邀请人id");	//6
		titles.add("排序编号");	//7
		titles.add("有效标志");	//8
		titles.add("创建人员id");	//9
		titles.add("创建时间");	//10
		titles.add("修改人员id");	//11
		titles.add("修改时间");	//12
		dataMap.put("titles", titles);
		List<ComInvite> varOList = comInviteService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getInviteId());			//0
			vpd.put("var1", varOList.get(i).getInviteCode());	//1
			vpd.put("var2", varOList.get(i).getInviteName());	//2
			vpd.put("var3", varOList.get(i).getInviteType());	//3
			vpd.put("var4", varOList.get(i).getInviteStatus());	//4
			vpd.put("var5", varOList.get(i).getInviteUserId());	//5
			vpd.put("var6", varOList.get(i).getInvitedUserId());	//6
			vpd.put("var7", varOList.get(i).getOrderNum());		//7
			vpd.put("var8", varOList.get(i).getEffective());	//8
			vpd.put("var9", varOList.get(i).getCreateUserId());	//9
			vpd.put("var10", varOList.get(i).getCreateTime());	//10
			vpd.put("var11", varOList.get(i).getModifyUserId());//11
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
		
		mv.addObject("controllerPath", "background_invite");
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
		titles.add("邀请代号");	//0
		titles.add("邀请名称");	//1
		titles.add("邀请类型");	//2
		titles.add("邀请人id");	//3
		titles.add("被邀请人id");	//4
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "inviteexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComInvite comInvite = new ComInvite();
				
		/**
		 * var0 :邀请代号;	//0
		 * var1 :邀请名称;	//1
		 * var2 :邀请类型;	//2
		 * var3 :邀请人id;	//3
		 * var4 :被邀请人id;	//4
		 */
		for(int i=0;i<listPd.size();i++){	
			comInvite.setInviteId(this.get32UUID());
			comInvite.setInviteCode(listPd.get(i).getString("var0"));
			comInvite.setInviteName(listPd.get(i).getString("var1"));
			comInvite.setInviteType(listPd.get(i).getString("var2"));
			comInvite.setInviteUserId(listPd.get(i).getString("var3"));
			comInvite.setInvitedUserId(listPd.get(i).getString("var4"));
			comInviteService.add(comInvite);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

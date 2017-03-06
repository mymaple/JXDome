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
import com.jx.common.entity.ComAppUser;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComAppUserService;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgAppUserController
 * 创建人：maple
 * 创建时间：2017-03-06
 */
@Controller
@RequestMapping(value="/background/appUser")
public class BgAppUserController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_appUser";
	
	@Resource(name="comAppUserService")
	private ComAppUserService comAppUserService;
	
	
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
		JSONArray arr = JSONArray.fromObject(comAppUserService.listInRank("0"));
		String json = arr.toString();
		json = json.replaceAll("appUserId", "id").replaceAll("parentId", "pId")
				.replaceAll("appUserName", "name").replaceAll("subComAppUserList", "nodes")
				.replaceAll("hasAppUser", "checked").replaceAll("subComAppUserPath", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", "background/appUser");
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
		List<PageData>	comAppUserList = comAppUserService.listPage(bgPage);	//列出comAppUser列表
		
		List<ComAppUser> parentList = new ArrayList<ComAppUser>();
		comAppUserService.getParentList(parentList, pd.getString("pId"));			//导航栏链接
		
		mv.addObject("comAppUserList", comAppUserList);
		mv.addObject("parentList", parentList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUser/bgAppUserList");

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
		ComAppUser parentComAppUser = comAppUserService.findById(parentId);
		if(!"0".equals(parentId) && parentComAppUser==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setParentId(parentId);
		comAppUser.setRoleId("");
		comAppUser.setAppUserCode("");
		comAppUser.setAppUserName("");
		comAppUser.setAppUserType("01");
		comAppUser.setAppUserNum("");
		comAppUser.setPhone("");
		comAppUser.setPassword("");
		comAppUser.setSex("");
		comAppUser.setBrithday(new Date());
		comAppUser.setRemarks("");
		comAppUser.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comAppUser);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUser/bgAppUserEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComAppUser comAppUser, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comAppUser");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String parentId = comAppUser.getParentId();
		ComAppUser parentComAppUser = comAppUserService.findById(parentId);
		if(!"0".equals(parentId) && parentComAppUser==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<ComAppUser> comAppUserList = comAppUserService.otherHaveCode("", comAppUser.getAppUserCode());
		if(MapleUtil.notEmptyList(comAppUserList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comAppUser.setLevel("0".equals(parentId)?"1":String.valueOf(Integer.parseInt(parentComAppUser.getLevel())+1));
		comAppUserService.add(comAppUser);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String appUserId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComAppUser comAppUser = comAppUserService.findById(appUserId);	//根据ID读取
		if(comAppUser == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comAppUser);
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUser/bgAppUserEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComAppUser comAppUser, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comAppUser");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComAppUser> comAppUserList = comAppUserService.otherHaveCode(comAppUser.getAppUserId(), comAppUser.getAppUserCode());	
		if(MapleUtil.notEmptyList(comAppUserList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comAppUserService.edit(comAppUser);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String appUserId, @RequestParam String appUserCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComAppUser> comAppUserList = comAppUserService.otherHaveCode(appUserId, appUserCode);	
		if(MapleUtil.emptyList(comAppUserList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String appUserId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comAppUserService.deleteInRank(appUserId);	//根据ID删除
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
		comAppUserService.batchDeleteInRank(ids.split(","));	//根据ID删除
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
		titles.add("平台用户 主键id");		//0
		titles.add("上级 id");					//1
		titles.add("角色");	//2
		titles.add("平台用户代号");	//3
		titles.add("平台用户名称");	//4
		titles.add("平台用户类型");	//5
		titles.add("平台用户状态");	//6
		titles.add("平台用户编号");	//7
		titles.add("电话号码");	//8
		titles.add("密码");	//9
		titles.add("性别");	//10
		titles.add("用户头像");	//11
		titles.add("生日");	//12
		titles.add("备注信息");	//13
		titles.add("级别");	//14
		titles.add("排序编号");	//15
		titles.add("有效标志");	//16
		titles.add("创建人员id");	//17
		titles.add("创建时间");	//18
		titles.add("修改人员id");	//19
		titles.add("修改时间");	//20
		dataMap.put("titles", titles);
		List<ComAppUser> varOList = comAppUserService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getAppUserId());			//0
			vpd.put("var1",varOList.get(i).getParentId());						//1
			vpd.put("var2", varOList.get(i).getRoleId());	//2
			vpd.put("var3", varOList.get(i).getAppUserCode());	//3
			vpd.put("var4", varOList.get(i).getAppUserName());	//4
			vpd.put("var5", varOList.get(i).getAppUserType());	//5
			vpd.put("var6", varOList.get(i).getAppUserStatus());	//6
			vpd.put("var7", varOList.get(i).getAppUserNum());	//7
			vpd.put("var8", varOList.get(i).getPhone());	//8
			vpd.put("var9", varOList.get(i).getPassword());	//9
			vpd.put("var10", varOList.get(i).getSex());	//10
			vpd.put("var11", varOList.get(i).getHeadImgSrc());	//11
			vpd.put("var12", varOList.get(i).getBrithday());	//12
			vpd.put("var13", varOList.get(i).getRemarks());	//13
			vpd.put("var14", varOList.get(i).getLevel());	//14
			vpd.put("var15", varOList.get(i).getOrderNum());		//15
			vpd.put("var16", varOList.get(i).getEffective());	//16
			vpd.put("var17", varOList.get(i).getCreateUserId());	//17
			vpd.put("var18", varOList.get(i).getCreateTime());	//18
			vpd.put("var19", varOList.get(i).getModifyUserId());//19
			vpd.put("var20", varOList.get(i).getModifyTime());	//20
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
			
		ComAppUser parentComAppUser = comAppUserService.findById(pId);
		if(!"0".equals(pId) && parentComAppUser==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("pId",pId);

		mv.addObject("controllerPath", "background_appUser");
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
		titles.add("角色");	//0
		titles.add("平台用户代号");	//1
		titles.add("平台用户名称");	//2
		titles.add("平台用户类型");	//3
		titles.add("平台用户编号");	//4
		titles.add("电话号码");	//5
		titles.add("密码");	//6
		titles.add("性别");	//7
		titles.add("生日");	//8
		titles.add("备注信息");	//9
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "appUserexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComAppUser comAppUser = new ComAppUser();
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
		comAppUser.setParentId(pId);
				
		/**
		 * var0 :角色;	//0
		 * var1 :平台用户代号;	//1
		 * var2 :平台用户名称;	//2
		 * var3 :平台用户类型;	//3
		 * var4 :平台用户编号;	//4
		 * var5 :电话号码;	//5
		 * var6 :密码;	//6
		 * var7 :性别;	//7
		 * var8 :生日;	//8
		 * var9 :备注信息;	//9
		 */
		for(int i=0;i<listPd.size();i++){	
			comAppUser.setAppUserId(this.get32UUID());
			comAppUser.setRoleId(listPd.get(i).getString("var0"));
			comAppUser.setAppUserCode(listPd.get(i).getString("var1"));
			comAppUser.setAppUserName(listPd.get(i).getString("var2"));
			comAppUser.setAppUserType(listPd.get(i).getString("var3"));
			comAppUser.setAppUserNum(listPd.get(i).getString("var4"));
			comAppUser.setPhone(listPd.get(i).getString("var5"));
			comAppUser.setPassword(listPd.get(i).getString("var6"));
			comAppUser.setSex(listPd.get(i).getString("var7"));
			comAppUser.setBrithdayStr(listPd.get(i).getString("var8"));
			comAppUser.setRemarks(listPd.get(i).getString("var9"));
			comAppUserService.add(comAppUser);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

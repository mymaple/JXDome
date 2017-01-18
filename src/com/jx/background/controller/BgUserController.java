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
import com.jx.background.entity.BgUser;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;

/** 
 * 类名称：BgUserController
 * 创建人：maple
 * 创建时间：2016-12-20
 */
@Controller
@RequestMapping(value="/background/user")
public class BgUserController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_user";
	
	@Resource(name="bgUserService")
	private BgUserService bgUserService;
	@Resource(name="bgRoleService")
	private BgRoleService bgRoleService;
	
	
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
		List<PageData>	bgUserList = bgUserService.listPage(bgPage);	//列出bgUser列表
		
		mv.addObject("bgUserList", bgUserList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/user/bgUserList");

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
		
		BgUser bgUser = new BgUser();
		bgUser.setRoleId("");
		bgUser.setUserCode("");
		bgUser.setPassword("");
		bgUser.setUserName("");
		bgUser.setUserType("01");
		bgUser.setEmail("");
		bgUser.setPhone("");
		bgUser.setRemarks("");
		bgUser.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(bgUser);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/user/bgUserEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) BgUser bgUser, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgUser");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgUser> bgUserList = bgUserService.otherHaveCode("", bgUser.getUserCode());
		if(MapleUtil.notEmptyList(bgUserList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		bgUserService.add(bgUser);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String userId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		BgUser bgUser = bgUserService.findById(userId);	//根据ID读取
		if(bgUser == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(bgUser);
		resultInfo.setResultCode("success");
		mv.setViewName("background/user/bgUserEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) BgUser bgUser, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgUser");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgUser> bgUserList = bgUserService.otherHaveCode(bgUser.getUserId(), bgUser.getUserCode());	
		if(MapleUtil.notEmptyList(bgUserList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		bgUserService.edit(bgUser);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String userId, @RequestParam String userCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<BgUser> bgUserList = bgUserService.otherHaveCode(userId, userCode);	
		if(MapleUtil.emptyList(bgUserList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String userId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		bgUserService.deleteById(userId);	//根据ID删除
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
		bgUserService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("后台用户 主键id");		//0
		titles.add("角色id");	//1
		titles.add("后台用户代号");	//2
		titles.add("密码");	//3
		titles.add("后台用户名称");	//4
		titles.add("后台用户类型");	//5
		titles.add("后台用户状态");	//6
		titles.add("用户头像路径");	//7
		titles.add("电子邮箱");	//8
		titles.add("手机号码");	//9
		titles.add("备注信息");	//10
		titles.add("排序编号");	//11
		titles.add("有效标志");	//12
		titles.add("创建人员id");	//13
		titles.add("创建时间");	//14
		titles.add("修改人员id");	//15
		titles.add("修改时间");	//16
		dataMap.put("titles", titles);
		List<BgUser> varOList = bgUserService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getUserId());			//0
			vpd.put("var1", varOList.get(i).getRoleId());	//1
			vpd.put("var2", varOList.get(i).getUserCode());	//2
			vpd.put("var3", varOList.get(i).getPassword());	//3
			vpd.put("var4", varOList.get(i).getUserName());	//4
			vpd.put("var5", varOList.get(i).getUserType());	//5
			vpd.put("var6", varOList.get(i).getUserStatus());	//6
			vpd.put("var7", varOList.get(i).getUserIconSrc());	//7
			vpd.put("var8", varOList.get(i).getEmail());	//8
			vpd.put("var9", varOList.get(i).getPhone());	//9
			vpd.put("var10", varOList.get(i).getRemarks());	//10
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
		
		mv.addObject("controllerPath", "background/user");
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
		titles.add("角色id");	//0
		titles.add("后台用户代号");	//1
		titles.add("密码");	//2
		titles.add("后台用户名称");	//3
		titles.add("后台用户类型");	//4
		titles.add("电子邮箱");	//5
		titles.add("手机号码");	//6
		titles.add("备注信息");	//7
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "userexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		BgUser bgUser = new BgUser();
				
		/**
		 * var0 :角色id;	//0
		 * var1 :后台用户代号;	//1
		 * var2 :密码;	//2
		 * var3 :后台用户名称;	//3
		 * var4 :后台用户类型;	//4
		 * var5 :电子邮箱;	//5
		 * var6 :手机号码;	//6
		 * var7 :备注信息;	//7
		 */
		for(int i=0;i<listPd.size();i++){	
			bgUser.setUserId(this.get32UUID());
			bgUser.setRoleId(listPd.get(i).getString("var0"));
			bgUser.setUserCode(listPd.get(i).getString("var1"));
			bgUser.setPassword(listPd.get(i).getString("var2"));
			bgUser.setUserName(listPd.get(i).getString("var3"));
			bgUser.setUserType(listPd.get(i).getString("var4"));
			bgUser.setEmail(listPd.get(i).getString("var5"));
			bgUser.setPhone(listPd.get(i).getString("var6"));
			bgUser.setRemarks(listPd.get(i).getString("var7"));
			bgUserService.add(bgUser);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

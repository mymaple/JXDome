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
import com.jx.background.util.JudgeRightsUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.BaseEntity.ValidationAdd;
import com.jx.common.config.BaseEntity.ValidationEdit;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRole;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;

import net.sf.json.JSONArray;

import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;

/** 
 * 类名称：BgRoleController
 * 创建人：maple
 * 创建时间：2016-12-20
 */
@Controller
@RequestMapping(value="/background/role")
public class BgRoleController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_role";
	
	@Resource(name="bgRoleService")
	private BgRoleService bgRoleService;
	@Resource(name="bgUserService")
	private BgUserService bgUserService;
	@Resource(name="bgMenuService")
	private BgMenuService bgMenuService;
	
	
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
		List<PageData>	bgRoleList = bgRoleService.listPage(bgPage);	//列出bgRole列表
		
		mv.addObject("bgRoleList", bgRoleList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/role/bgRoleList");

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
		
		BgRole bgRole = new BgRole();
		bgRole.setRoleCode("");
		bgRole.setRoleName("");
		bgRole.setRoleType("01");
		bgRole.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(bgRole);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/role/bgRoleEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) BgRole bgRole, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgRole");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgRole> bgRoleList = bgRoleService.otherHaveCode("", bgRole.getRoleCode());
		if(MapleUtil.notEmptyList(bgRoleList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		bgRoleService.add(bgRole);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String roleId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		BgRole bgRole = bgRoleService.findById(roleId);	//根据ID读取
		if(bgRole == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(bgRole);
		resultInfo.setResultCode("success");
		mv.setViewName("background/role/bgRoleEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) BgRole bgRole, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgRole");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgRole> bgRoleList = bgRoleService.otherHaveCode(bgRole.getRoleId(), bgRole.getRoleCode());	
		if(MapleUtil.notEmptyList(bgRoleList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		bgRoleService.edit(bgRole);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String roleId, @RequestParam String roleCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<BgRole> bgRoleList = bgRoleService.otherHaveCode(roleId, roleCode);	
		if(MapleUtil.emptyList(bgRoleList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String roleId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		bgRoleService.deleteById(roleId);	//根据ID删除
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
		bgRoleService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("后台角色 主键id");		//0
		titles.add("后台角色代号");	//1
		titles.add("后台角色名称");	//2
		titles.add("后台角色类型");	//3
		titles.add("后台角色状态");	//4
		titles.add("角色权限");	//5
		titles.add("新增权限");	//6
		titles.add("删除权限");	//7
		titles.add("修改权限");	//8
		titles.add("查询权限");	//9
		titles.add("排序编号");	//10
		titles.add("有效标志");	//11
		titles.add("创建人员id");	//12
		titles.add("创建时间");	//13
		titles.add("修改人员id");	//14
		titles.add("修改时间");	//15
		dataMap.put("titles", titles);
		List<BgRole> varOList = bgRoleService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getRoleId());			//0
			vpd.put("var1", varOList.get(i).getRoleCode());	//1
			vpd.put("var2", varOList.get(i).getRoleName());	//2
			vpd.put("var3", varOList.get(i).getRoleType());	//3
			vpd.put("var4", varOList.get(i).getRoleStatus());	//4
			vpd.put("var5", varOList.get(i).getRoleRights());	//5
			vpd.put("var6", varOList.get(i).getAddRights());	//6
			vpd.put("var7", varOList.get(i).getDelRights());	//7
			vpd.put("var8", varOList.get(i).getEditRights());	//8
			vpd.put("var9", varOList.get(i).getSeleRights());	//9
			vpd.put("var10", varOList.get(i).getOrderNum());		//10
			vpd.put("var11", varOList.get(i).getEffective());	//11
			vpd.put("var12", varOList.get(i).getCreateUserId());	//12
			vpd.put("var13", varOList.get(i).getCreateTime());	//13
			vpd.put("var14", varOList.get(i).getModifyUserId());//14
			vpd.put("var15", varOList.get(i).getModifyTime());	//15
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
		
		mv.addObject("controllerPath", "background_role");
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
		titles.add("后台角色代号");	//0
		titles.add("后台角色名称");	//1
		titles.add("后台角色类型");	//2
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "roleexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		BgRole bgRole = new BgRole();
				
		/**
		 * var0 :后台角色代号;	//0
		 * var1 :后台角色名称;	//1
		 * var2 :后台角色类型;	//2
		 */
		for(int i=0;i<listPd.size();i++){	
			bgRole.setRoleId(this.get32UUID());
			bgRole.setRoleCode(listPd.get(i).getString("var0"));
			bgRole.setRoleName(listPd.get(i).getString("var1"));
			bgRole.setRoleType(listPd.get(i).getString("var2"));
			bgRoleService.add(bgRole);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
		
	/**
	 * 显示菜单列表ztree(菜单授权菜单)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toChangeRoleRights")
	public ModelAndView toChangeRoleRights(@RequestParam String roleId,@RequestParam String rightsMsg,Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			BgRole bgRole = bgRoleService.findById(roleId);								//根据角色ID获取角色对象
			String rights = "0";
			if("role".equals(rightsMsg)){
				rights = bgRole.getRoleRights();
			}else if("add".equals(rightsMsg)){
				rights = bgRole.getAddRights();
			}else if("del".equals(rightsMsg)){
				rights = bgRole.getDelRights();
			}else if("edit".equals(rightsMsg)){
				rights = bgRole.getEditRights();
			}else if("sele".equals(rightsMsg)){
				rights = bgRole.getSeleRights();
			}
			List<BgMenu> bgMenuList = bgMenuService.listInRank("0");					//获取所有菜单
			bgMenuList = JudgeRightsUtil.bgMenuListTestRights(bgMenuList, rights);					//根据角色权限处理菜单权限状态(递归处理)
			JSONArray arr = JSONArray.fromObject(bgMenuList);
			String json = arr.toString();
			json = json.replaceAll("menuTag", "id").replaceAll("parentId", "pId")
					.replaceAll("menuName", "name").replaceAll("subBgMenuList", "nodes")
					.replaceAll("hasMenu", "checked");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("roleId",roleId);
			mv.addObject("rightsMsg", rightsMsg);
			mv.setViewName("background/role/bgRoleRights");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**保存角色菜单权限
	 * @param roleId 角色ID
	 * @param menuIds 菜单ID集合
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/changeRoleRights")
	@ResponseBody
	public Object changeRoleRights()throws Exception{
		logBefore(logger, "修改菜单权限");
		PageData pd = new PageData();
		pd = this.getPageData();
		String roleId = pd.getString("roleId");
		String tags = pd.getString("tags");
		String rightsMsg = pd.getString("rightsMsg");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		
		try{
			BgRole bgRole = new BgRole();
			bgRole.setRoleId(roleId);
			bgRole.setModifyTime(new Date());
			String rights = "0";
			if(null != tags && !"".equals(tags.trim())){
				rights = JudgeRightsUtil.sumRights(MapleStringUtil.str2StrArray(tags)).toString();//用菜单ID做权处理
			}
			
			if("role".equals(rightsMsg)){
				bgRole.setRoleRights(rights);
			}else if("add".equals(rightsMsg)){
				bgRole.setAddRights(rights);
			}else if("del".equals(rightsMsg)){
				bgRole.setDelRights(rights);
			}else if("edit".equals(rightsMsg)){
				bgRole.setEditRights(rights);
			}else if("sele".equals(rightsMsg)){
				bgRole.setSeleRights(rights);
			}
			
			bgRoleService.change(bgRole);				//更新当前角色菜单权限
			errInfo = "success";
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	

}

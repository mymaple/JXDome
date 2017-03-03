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
import com.jx.common.entity.ComIntegralCustomer;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComIntegralCustomerService;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgIntegralCustomerController
 * 创建人：maple
 * 创建时间：2017-03-03
 */
@Controller
@RequestMapping(value="/background/integralCustomer")
public class BgIntegralCustomerController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_integralCustomer";
	
	@Resource(name="comIntegralCustomerService")
	private ComIntegralCustomerService comIntegralCustomerService;
	
	
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
		JSONArray arr = JSONArray.fromObject(comIntegralCustomerService.listInRank("0"));
		String json = arr.toString();
		json = json.replaceAll("integralCustomerId", "id").replaceAll("parentId", "pId")
				.replaceAll("integralCustomerName", "name").replaceAll("subComIntegralCustomerList", "nodes")
				.replaceAll("hasIntegralCustomer", "checked").replaceAll("subComIntegralCustomerPath", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", "background/integralCustomer");
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
		List<PageData>	comIntegralCustomerList = comIntegralCustomerService.listPage(bgPage);	//列出comIntegralCustomer列表
		
		List<ComIntegralCustomer> parentList = new ArrayList<ComIntegralCustomer>();
		comIntegralCustomerService.getParentList(parentList, pd.getString("pId"));			//导航栏链接
		
		mv.addObject("comIntegralCustomerList", comIntegralCustomerList);
		mv.addObject("parentList", parentList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/integralCustomer/bgIntegralCustomerList");

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
		ComIntegralCustomer parentComIntegralCustomer = comIntegralCustomerService.findById(parentId);
		if(!"0".equals(parentId) && parentComIntegralCustomer==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		ComIntegralCustomer comIntegralCustomer = new ComIntegralCustomer();
		comIntegralCustomer.setParentId(parentId);
		comIntegralCustomer.setRoleId("");
		comIntegralCustomer.setIntegralCustomerCode("");
		comIntegralCustomer.setIntegralCustomerName("");
		comIntegralCustomer.setIntegralCustomerType("01");
		comIntegralCustomer.setPhone("");
		comIntegralCustomer.setPassword("");
		comIntegralCustomer.setRemarks("");
		comIntegralCustomer.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comIntegralCustomer);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/integralCustomer/bgIntegralCustomerEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComIntegralCustomer comIntegralCustomer, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comIntegralCustomer");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String parentId = comIntegralCustomer.getParentId();
		ComIntegralCustomer parentComIntegralCustomer = comIntegralCustomerService.findById(parentId);
		if(!"0".equals(parentId) && parentComIntegralCustomer==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<ComIntegralCustomer> comIntegralCustomerList = comIntegralCustomerService.otherHaveCode("", comIntegralCustomer.getIntegralCustomerCode());
		if(MapleUtil.notEmptyList(comIntegralCustomerList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		
		comIntegralCustomer.setLevel("0".equals(parentId)?"1":String.valueOf(Integer.parseInt(parentComIntegralCustomer.getLevel())+1));
		comIntegralCustomerService.add(comIntegralCustomer);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String integralCustomerId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComIntegralCustomer comIntegralCustomer = comIntegralCustomerService.findById(integralCustomerId);	//根据ID读取
		if(comIntegralCustomer == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comIntegralCustomer);
		resultInfo.setResultCode("success");
		mv.setViewName("background/integralCustomer/bgIntegralCustomerEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComIntegralCustomer comIntegralCustomer, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comIntegralCustomer");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComIntegralCustomer> comIntegralCustomerList = comIntegralCustomerService.otherHaveCode(comIntegralCustomer.getIntegralCustomerId(), comIntegralCustomer.getIntegralCustomerCode());	
		if(MapleUtil.notEmptyList(comIntegralCustomerList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comIntegralCustomerService.edit(comIntegralCustomer);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String integralCustomerId, @RequestParam String integralCustomerCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComIntegralCustomer> comIntegralCustomerList = comIntegralCustomerService.otherHaveCode(integralCustomerId, integralCustomerCode);	
		if(MapleUtil.emptyList(comIntegralCustomerList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String integralCustomerId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comIntegralCustomerService.deleteInRank(integralCustomerId);	//根据ID删除
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
		comIntegralCustomerService.batchDeleteInRank(ids.split(","));	//根据ID删除
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
		titles.add("积分客户 主键id");		//0
		titles.add("上级 id");					//1
		titles.add("角色");	//2
		titles.add("积分客户代号");	//3
		titles.add("积分客户名称");	//4
		titles.add("积分客户类型");	//5
		titles.add("积分客户状态");	//6
		titles.add("手机号码");	//7
		titles.add("密码");	//8
		titles.add("客户头像");	//9
		titles.add("备注信息");	//10
		titles.add("级别");	//11
		titles.add("排序编号");	//12
		titles.add("有效标志");	//13
		titles.add("创建人员id");	//14
		titles.add("创建时间");	//15
		titles.add("修改人员id");	//16
		titles.add("修改时间");	//17
		dataMap.put("titles", titles);
		List<ComIntegralCustomer> varOList = comIntegralCustomerService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getIntegralCustomerId());			//0
			vpd.put("var1",varOList.get(i).getParentId());						//1
			vpd.put("var2", varOList.get(i).getRoleId());	//2
			vpd.put("var3", varOList.get(i).getIntegralCustomerCode());	//3
			vpd.put("var4", varOList.get(i).getIntegralCustomerName());	//4
			vpd.put("var5", varOList.get(i).getIntegralCustomerType());	//5
			vpd.put("var6", varOList.get(i).getIntegralCustomerStatus());	//6
			vpd.put("var7", varOList.get(i).getPhone());	//7
			vpd.put("var8", varOList.get(i).getPassword());	//8
			vpd.put("var9", varOList.get(i).getUserIconSrc());	//9
			vpd.put("var10", varOList.get(i).getRemarks());	//10
			vpd.put("var11", varOList.get(i).getLevel());	//11
			vpd.put("var12", varOList.get(i).getOrderNum());		//12
			vpd.put("var13", varOList.get(i).getEffective());	//13
			vpd.put("var14", varOList.get(i).getCreateUserId());	//14
			vpd.put("var15", varOList.get(i).getCreateTime());	//15
			vpd.put("var16", varOList.get(i).getModifyUserId());//16
			vpd.put("var17", varOList.get(i).getModifyTime());	//17
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
			
		ComIntegralCustomer parentComIntegralCustomer = comIntegralCustomerService.findById(pId);
		if(!"0".equals(pId) && parentComIntegralCustomer==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("pId",pId);

		mv.addObject("controllerPath", "background_integralCustomer");
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
		titles.add("积分客户代号");	//1
		titles.add("积分客户名称");	//2
		titles.add("积分客户类型");	//3
		titles.add("手机号码");	//4
		titles.add("密码");	//5
		titles.add("备注信息");	//6
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "integralCustomerexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComIntegralCustomer comIntegralCustomer = new ComIntegralCustomer();
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
		comIntegralCustomer.setParentId(pId);
				
		/**
		 * var0 :角色;	//0
		 * var1 :积分客户代号;	//1
		 * var2 :积分客户名称;	//2
		 * var3 :积分客户类型;	//3
		 * var4 :手机号码;	//4
		 * var5 :密码;	//5
		 * var6 :备注信息;	//6
		 */
		for(int i=0;i<listPd.size();i++){	
			comIntegralCustomer.setIntegralCustomerId(this.get32UUID());
			comIntegralCustomer.setRoleId(listPd.get(i).getString("var0"));
			comIntegralCustomer.setIntegralCustomerCode(listPd.get(i).getString("var1"));
			comIntegralCustomer.setIntegralCustomerName(listPd.get(i).getString("var2"));
			comIntegralCustomer.setIntegralCustomerType(listPd.get(i).getString("var3"));
			comIntegralCustomer.setPhone(listPd.get(i).getString("var4"));
			comIntegralCustomer.setPassword(listPd.get(i).getString("var5"));
			comIntegralCustomer.setRemarks(listPd.get(i).getString("var6"));
			comIntegralCustomerService.add(comIntegralCustomer);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

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
import com.jx.common.entity.ComWxAccount;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComWxAccountService;

/** 
 * 类名称：BgWxAccountController
 * 创建人：maple
 * 创建时间：2017-01-20
 */
@Controller
@RequestMapping(value="/background/wxAccount")
public class BgWxAccountController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_wxAccount";
	
	@Resource(name="comWxAccountService")
	private ComWxAccountService comWxAccountService;
	
	
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
		List<PageData>	comWxAccountList = comWxAccountService.listPage(bgPage);	//列出comWxAccount列表
		
		mv.addObject("comWxAccountList", comWxAccountList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/wxAccount/bgWxAccountList");

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
		
		ComWxAccount comWxAccount = new ComWxAccount();
		comWxAccount.setWxAccountCode("");
		comWxAccount.setWxAccountName("");
		comWxAccount.setWxAccountType("01");
		comWxAccount.setAppId("");
		comWxAccount.setAppSecret("");
		comWxAccount.setToken("");
		comWxAccount.setMchId("");
		comWxAccount.setApiKey("");
		comWxAccount.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comWxAccount);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/wxAccount/bgWxAccountEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComWxAccount comWxAccount, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comWxAccount");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComWxAccount> comWxAccountList = comWxAccountService.otherHaveCode("", comWxAccount.getWxAccountCode());
		if(MapleUtil.notEmptyList(comWxAccountList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comWxAccountService.add(comWxAccount);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String wxAccountId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComWxAccount comWxAccount = comWxAccountService.findById(wxAccountId);	//根据ID读取
		if(comWxAccount == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comWxAccount);
		resultInfo.setResultCode("success");
		mv.setViewName("background/wxAccount/bgWxAccountEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComWxAccount comWxAccount, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comWxAccount");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComWxAccount> comWxAccountList = comWxAccountService.otherHaveCode(comWxAccount.getWxAccountId(), comWxAccount.getWxAccountCode());	
		if(MapleUtil.notEmptyList(comWxAccountList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comWxAccountService.edit(comWxAccount);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String wxAccountId, @RequestParam String wxAccountCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComWxAccount> comWxAccountList = comWxAccountService.otherHaveCode(wxAccountId, wxAccountCode);	
		if(MapleUtil.emptyList(comWxAccountList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String wxAccountId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comWxAccountService.deleteById(wxAccountId);	//根据ID删除
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
		comWxAccountService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("微信账户 主键id");		//0
		titles.add("微信账户代号");	//1
		titles.add("微信账户名称");	//2
		titles.add("微信账户类型");	//3
		titles.add("微信账户状态");	//4
		titles.add("AppID(应用ID)");	//5
		titles.add("AppSecret(应用密钥)");	//6
		titles.add("Token(令牌)");	//7
		titles.add("微信支付商户号");	//8
		titles.add("API密钥");	//9
		titles.add("公众号的全局唯一票据");	//10
		titles.add("微信JS接口的临时票据");	//11
		titles.add("排序编号");	//12
		titles.add("有效标志");	//13
		titles.add("创建人员id");	//14
		titles.add("创建时间");	//15
		titles.add("修改人员id");	//16
		titles.add("修改时间");	//17
		dataMap.put("titles", titles);
		List<ComWxAccount> varOList = comWxAccountService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getWxAccountId());			//0
			vpd.put("var1", varOList.get(i).getWxAccountCode());	//1
			vpd.put("var2", varOList.get(i).getWxAccountName());	//2
			vpd.put("var3", varOList.get(i).getWxAccountType());	//3
			vpd.put("var4", varOList.get(i).getWxAccountStatus());	//4
			vpd.put("var5", varOList.get(i).getAppId());	//5
			vpd.put("var6", varOList.get(i).getAppSecret());	//6
			vpd.put("var7", varOList.get(i).getToken());	//7
			vpd.put("var8", varOList.get(i).getMchId());	//8
			vpd.put("var9", varOList.get(i).getApiKey());	//9
			vpd.put("var10", varOList.get(i).getAccessToken());	//10
			vpd.put("var11", varOList.get(i).getJsApiTicket());	//11
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		mv.addObject("controllerPath", "background_wxAccount");
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
		titles.add("微信账户代号");	//0
		titles.add("微信账户名称");	//1
		titles.add("微信账户类型");	//2
		titles.add("AppID(应用ID)");	//3
		titles.add("AppSecret(应用密钥)");	//4
		titles.add("Token(令牌)");	//5
		titles.add("微信支付商户号");	//6
		titles.add("API密钥");	//7
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "wxAccountexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComWxAccount comWxAccount = new ComWxAccount();
				
		/**
		 * var0 :微信账户代号;	//0
		 * var1 :微信账户名称;	//1
		 * var2 :微信账户类型;	//2
		 * var3 :AppID(应用ID);	//3
		 * var4 :AppSecret(应用密钥);	//4
		 * var5 :Token(令牌);	//5
		 * var6 :微信支付商户号;	//6
		 * var7 :API密钥;	//7
		 */
		for(int i=0;i<listPd.size();i++){	
			comWxAccount.setWxAccountId(this.get32UUID());
			comWxAccount.setWxAccountCode(listPd.get(i).getString("var0"));
			comWxAccount.setWxAccountName(listPd.get(i).getString("var1"));
			comWxAccount.setWxAccountType(listPd.get(i).getString("var2"));
			comWxAccount.setAppId(listPd.get(i).getString("var3"));
			comWxAccount.setAppSecret(listPd.get(i).getString("var4"));
			comWxAccount.setToken(listPd.get(i).getString("var5"));
			comWxAccount.setMchId(listPd.get(i).getString("var6"));
			comWxAccount.setApiKey(listPd.get(i).getString("var7"));
			comWxAccountService.add(comWxAccount);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

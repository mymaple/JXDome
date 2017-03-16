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
import com.jx.common.entity.ComReceiveAddress;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComReceiveAddressService;

/** 
 * 类名称：BgReceiveAddressController
 * 创建人：maple
 * 创建时间：2017-03-11
 */
@Controller
@RequestMapping(value="/background/receiveAddress")
public class BgReceiveAddressController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_receiveAddress";
	
	@Resource(name="comReceiveAddressService")
	private ComReceiveAddressService comReceiveAddressService;
	
	
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
		List<PageData>	comReceiveAddressList = comReceiveAddressService.listPage(bgPage);	//列出comReceiveAddress列表
		
		mv.addObject("comReceiveAddressList", comReceiveAddressList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/receiveAddress/bgReceiveAddressList");

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
		
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setAppUserId("");
		comReceiveAddress.setReceicerName("");
		comReceiveAddress.setPhone("");
		comReceiveAddress.setProvince("");
		comReceiveAddress.setCity("");
		comReceiveAddress.setDistrict("");
		comReceiveAddress.setStreet("");
		comReceiveAddress.setDetail("");
		comReceiveAddress.setDefaultStatus("");
		comReceiveAddress.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comReceiveAddress);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/receiveAddress/bgReceiveAddressEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComReceiveAddress comReceiveAddress, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comReceiveAddress");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		comReceiveAddressService.add(comReceiveAddress);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String receiveAddressId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComReceiveAddress comReceiveAddress = comReceiveAddressService.findById(receiveAddressId);	//根据ID读取
		if(comReceiveAddress == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comReceiveAddress);
		resultInfo.setResultCode("success");
		mv.setViewName("background/receiveAddress/bgReceiveAddressEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComReceiveAddress comReceiveAddress, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comReceiveAddress");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		comReceiveAddressService.edit(comReceiveAddress);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String receiveAddressId, @RequestParam String receiveAddressCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComReceiveAddress> comReceiveAddressList = comReceiveAddressService.otherHaveCode(receiveAddressId, receiveAddressCode);	
		if(MapleUtil.emptyList(comReceiveAddressList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String receiveAddressId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comReceiveAddressService.deleteById(receiveAddressId);	//根据ID删除
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
		comReceiveAddressService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("收货地址 主键id");		//0
		titles.add("平台用户");	//1
		titles.add("收货人");	//2
		titles.add("手机号码");	//3
		titles.add("省");	//4
		titles.add("城市");	//5
		titles.add("区");	//6
		titles.add("街道");	//7
		titles.add("详细地址");	//8
		titles.add("默认状态");	//9
		titles.add("排序编号");	//10
		titles.add("有效标志");	//11
		titles.add("创建人员id");	//12
		titles.add("创建时间");	//13
		titles.add("修改人员id");	//14
		titles.add("修改时间");	//15
		dataMap.put("titles", titles);
		List<ComReceiveAddress> varOList = comReceiveAddressService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getReceiveAddressId());			//0
			vpd.put("var1", varOList.get(i).getAppUserId());	//1
			vpd.put("var2", varOList.get(i).getReceicerName());	//2
			vpd.put("var3", varOList.get(i).getPhone());	//3
			vpd.put("var4", varOList.get(i).getProvince());	//4
			vpd.put("var5", varOList.get(i).getCity());	//5
			vpd.put("var6", varOList.get(i).getDistrict());	//6
			vpd.put("var7", varOList.get(i).getStreet());	//7
			vpd.put("var8", varOList.get(i).getDetail());	//8
			vpd.put("var9", varOList.get(i).getDefaultStatus());	//9
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
		
		mv.addObject("controllerPath", "background_receiveAddress");
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
		titles.add("平台用户");	//0
		titles.add("收货人");	//1
		titles.add("手机号码");	//2
		titles.add("省");	//3
		titles.add("城市");	//4
		titles.add("区");	//5
		titles.add("街道");	//6
		titles.add("详细地址");	//7
		titles.add("默认状态");	//8
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "receiveAddressexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
				
		/**
		 * var0 :平台用户;	//0
		 * var1 :收货人;	//1
		 * var2 :手机号码;	//2
		 * var3 :省;	//3
		 * var4 :城市;	//4
		 * var5 :区;	//5
		 * var6 :街道;	//6
		 * var7 :详细地址;	//7
		 * var8 :默认状态;	//8
		 */
		for(int i=0;i<listPd.size();i++){	
			comReceiveAddress.setReceiveAddressId(this.get32UUID());
			comReceiveAddress.setAppUserId(listPd.get(i).getString("var0"));
			comReceiveAddress.setReceicerName(listPd.get(i).getString("var1"));
			comReceiveAddress.setPhone(listPd.get(i).getString("var2"));
			comReceiveAddress.setProvince(listPd.get(i).getString("var3"));
			comReceiveAddress.setCity(listPd.get(i).getString("var4"));
			comReceiveAddress.setDistrict(listPd.get(i).getString("var5"));
			comReceiveAddress.setStreet(listPd.get(i).getString("var6"));
			comReceiveAddress.setDetail(listPd.get(i).getString("var7"));
			comReceiveAddress.setDefaultStatus(listPd.get(i).getString("var8"));
			comReceiveAddressService.add(comReceiveAddress);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

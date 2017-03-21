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
import com.jx.common.entity.ComSupplier;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComSupplierService;

/** 
 * 类名称：BgSupplierController
 * 创建人：maple
 * 创建时间：2017-02-24
 */
@Controller
@RequestMapping(value="/background/supplier")
public class BgSupplierController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_supplier";
	
	@Resource(name="comSupplierService")
	private ComSupplierService comSupplierService;
	
	
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
		List<PageData>	comSupplierList = comSupplierService.listPage(bgPage);	//列出comSupplier列表
		
		mv.addObject("comSupplierList", comSupplierList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/supplier/bgSupplierList");

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
		
		ComSupplier comSupplier = new ComSupplier();
		comSupplier.setSupplierCode("");
		comSupplier.setSupplierName("");
		comSupplier.setSupplierType("01");
		comSupplier.setLinkman("");
		comSupplier.setLinkPhone("");
		comSupplier.setRemarks("");
		comSupplier.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comSupplier);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/supplier/bgSupplierEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComSupplier comSupplier, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comSupplier");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComSupplier> comSupplierList = comSupplierService.otherHaveCode("", comSupplier.getSupplierCode());
		if(MapleUtil.notEmptyList(comSupplierList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comSupplierService.add(comSupplier);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String supplierId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComSupplier comSupplier = comSupplierService.findById(supplierId);	//根据ID读取
		if(comSupplier == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comSupplier);
		resultInfo.setResultCode("success");
		mv.setViewName("background/supplier/bgSupplierEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComSupplier comSupplier, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comSupplier");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComSupplier> comSupplierList = comSupplierService.otherHaveCode(comSupplier.getSupplierId(), comSupplier.getSupplierCode());	
		if(MapleUtil.notEmptyList(comSupplierList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comSupplierService.edit(comSupplier);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String supplierId, @RequestParam String supplierCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComSupplier> comSupplierList = comSupplierService.otherHaveCode(supplierId, supplierCode);	
		if(MapleUtil.emptyList(comSupplierList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String supplierId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comSupplierService.deleteById(supplierId);	//根据ID删除
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
		comSupplierService.batchDeleteByIds(ids.split(","));	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 更改状态
	 */
	@RequestMapping(value="/changeStatus")
	@ResponseBody
	public Object changeStatus(@RequestParam String flag, @RequestParam String supplierId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comSupplierService.changeStatus(flag, supplierId);	
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 更改有效性
	 */
	@RequestMapping(value="/changeEffective")
	@ResponseBody
	public Object changeEffective(@RequestParam String flag, @RequestParam String supplierId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comSupplierService.changeEffective(flag, supplierId);	
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
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("供应商 主键id");		//0
		titles.add("供应商代号");	//1
		titles.add("供应商名称");	//2
		titles.add("供应商类型");	//3
		titles.add("供应商状态");	//4
		titles.add("联系人");	//5
		titles.add("联系电话");	//6
		titles.add("备注");	//7
		titles.add("排序编号");	//8
		titles.add("有效标志");	//9
		titles.add("创建人员id");	//10
		titles.add("创建时间");	//11
		titles.add("修改人员id");	//12
		titles.add("修改时间");	//13
		dataMap.put("titles", titles);
		List<ComSupplier> varOList = comSupplierService.listAll();
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getSupplierId());			//0
			vpd.put("var1", varOList.get(i).getSupplierCode());	//1
			vpd.put("var2", varOList.get(i).getSupplierName());	//2
			vpd.put("var3", varOList.get(i).getSupplierType());	//3
			vpd.put("var4", varOList.get(i).getSupplierStatus());	//4
			vpd.put("var5", varOList.get(i).getLinkman());	//5
			vpd.put("var6", varOList.get(i).getLinkPhone());	//6
			vpd.put("var7", varOList.get(i).getRemarks());	//7
			vpd.put("var8", varOList.get(i).getOrderNum());		//8
			vpd.put("var9", varOList.get(i).getEffective());	//9
			vpd.put("var10", varOList.get(i).getCreateUserId());	//10
			vpd.put("var11", varOList.get(i).getCreateTime());	//11
			vpd.put("var12", varOList.get(i).getModifyUserId());//12
			vpd.put("var13", varOList.get(i).getModifyTime());	//13
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
		
		mv.addObject("controllerPath", "background_supplier");
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
		titles.add("供应商代号");	//0
		titles.add("供应商名称");	//1
		titles.add("供应商类型");	//2
		titles.add("联系人");	//3
		titles.add("联系电话");	//4
		titles.add("备注");	//5
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "supplierexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComSupplier comSupplier = new ComSupplier();
				
		/**
		 * var0 :供应商代号;	//0
		 * var1 :供应商名称;	//1
		 * var2 :供应商类型;	//2
		 * var3 :联系人;	//3
		 * var4 :联系电话;	//4
		 * var5 :备注;	//5
		 */
		for(int i=0;i<listPd.size();i++){	
			comSupplier.setSupplierId(this.get32UUID());
			comSupplier.setSupplierCode(listPd.get(i).getString("var0"));
			comSupplier.setSupplierName(listPd.get(i).getString("var1"));
			comSupplier.setSupplierType(listPd.get(i).getString("var2"));
			comSupplier.setLinkman(listPd.get(i).getString("var3"));
			comSupplier.setLinkPhone(listPd.get(i).getString("var4"));
			comSupplier.setRemarks(listPd.get(i).getString("var5"));
			comSupplierService.add(comSupplier);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

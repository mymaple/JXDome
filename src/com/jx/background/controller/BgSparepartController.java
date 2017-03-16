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
import com.jx.common.entity.ComSparepart;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComSparepartService;

/** 
 * 类名称：BgSparepartController
 * 创建人：maple
 * 创建时间：2017-03-03
 */
@Controller
@RequestMapping(value="/background/sparepart")
public class BgSparepartController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_sparepart";
	
	@Resource(name="comSparepartService")
	private ComSparepartService comSparepartService;
	
	
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
		List<PageData>	comSparepartList = comSparepartService.listPage(bgPage);	//列出comSparepart列表
		
		mv.addObject("comSparepartList", comSparepartList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/sparepart/bgSparepartList");

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
		
		ComSparepart comSparepart = new ComSparepart();
		comSparepart.setSparepartCode("");
		comSparepart.setSparepartName("");
		comSparepart.setSparepartType("01");
		comSparepart.setAllIntegral("0.00");
		comSparepart.setIntegral1("0.00");
		comSparepart.setIntegral2("0.00");
		comSparepart.setIntegral3("0.00");
		comSparepart.setIntegral4("0.00");
		comSparepart.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comSparepart);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/sparepart/bgSparepartEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComSparepart comSparepart, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comSparepart");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComSparepart> comSparepartList = comSparepartService.otherHaveCode("", comSparepart.getSparepartCode());
		if(MapleUtil.notEmptyList(comSparepartList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comSparepartService.add(comSparepart);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String sparepartId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComSparepart comSparepart = comSparepartService.findById(sparepartId);	//根据ID读取
		if(comSparepart == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comSparepart);
		resultInfo.setResultCode("success");
		mv.setViewName("background/sparepart/bgSparepartEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComSparepart comSparepart, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comSparepart");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComSparepart> comSparepartList = comSparepartService.otherHaveCode(comSparepart.getSparepartId(), comSparepart.getSparepartCode());	
		if(MapleUtil.notEmptyList(comSparepartList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comSparepartService.edit(comSparepart);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String sparepartId, @RequestParam String sparepartCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComSparepart> comSparepartList = comSparepartService.otherHaveCode(sparepartId, sparepartCode);	
		if(MapleUtil.emptyList(comSparepartList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String sparepartId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comSparepartService.deleteById(sparepartId);	//根据ID删除
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
		comSparepartService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("零配件 主键id");		//0
		titles.add("零配件代号");	//1
		titles.add("零配件名称");	//2
		titles.add("零配件类型");	//3
		titles.add("零配件状态");	//4
		titles.add("总积分");	//5
		titles.add("大区经理可获积分");	//6
		titles.add("小区经理可获积分");	//7
		titles.add("4S店可获积分");	//8
		titles.add("销售员可获积分");	//9
		titles.add("排序编号");	//10
		titles.add("有效标志");	//11
		titles.add("创建人员id");	//12
		titles.add("创建时间");	//13
		titles.add("修改人员id");	//14
		titles.add("修改时间");	//15
		dataMap.put("titles", titles);
		List<ComSparepart> varOList = comSparepartService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getSparepartId());			//0
			vpd.put("var1", varOList.get(i).getSparepartCode());	//1
			vpd.put("var2", varOList.get(i).getSparepartName());	//2
			vpd.put("var3", varOList.get(i).getSparepartType());	//3
			vpd.put("var4", varOList.get(i).getSparepartStatus());	//4
			vpd.put("var5", varOList.get(i).getAllIntegral());	//5
			vpd.put("var6", varOList.get(i).getIntegral1());	//6
			vpd.put("var7", varOList.get(i).getIntegral2());	//7
			vpd.put("var8", varOList.get(i).getIntegral3());	//8
			vpd.put("var9", varOList.get(i).getIntegral4());	//9
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
		
		mv.addObject("controllerPath", "background_sparepart");
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
		titles.add("零配件代号");	//0
		titles.add("零配件名称");	//1
		titles.add("零配件类型");	//2
		titles.add("总积分");	//3
		titles.add("大区经理可获积分");	//4
		titles.add("小区经理可获积分");	//5
		titles.add("4S店可获积分");	//6
		titles.add("销售员可获积分");	//7
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "sparepartexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComSparepart comSparepart = new ComSparepart();
				
		/**
		 * var0 :零配件代号;	//0
		 * var1 :零配件名称;	//1
		 * var2 :零配件类型;	//2
		 * var3 :总积分;	//3
		 * var4 :大区经理可获积分;	//4
		 * var5 :小区经理可获积分;	//5
		 * var6 :4S店可获积分;	//6
		 * var7 :销售员可获积分;	//7
		 */
		for(int i=0;i<listPd.size();i++){	
			comSparepart.setSparepartId(this.get32UUID());
			comSparepart.setSparepartCode(listPd.get(i).getString("var0"));
			comSparepart.setSparepartName(listPd.get(i).getString("var1"));
			comSparepart.setSparepartType(listPd.get(i).getString("var2"));
			comSparepart.setAllIntegral(listPd.get(i).getString("var3"));
			comSparepart.setIntegral1(listPd.get(i).getString("var4"));
			comSparepart.setIntegral2(listPd.get(i).getString("var5"));
			comSparepart.setIntegral3(listPd.get(i).getString("var6"));
			comSparepart.setIntegral4(listPd.get(i).getString("var7"));
			comSparepartService.add(comSparepart);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

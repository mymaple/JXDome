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
import com.jx.background.entity.BgCrontab;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.background.service.BgCrontabService;

/** 
 * 类名称：BgCrontabController
 * 创建人：maple
 * 创建时间：2017-01-22
 */
@Controller
@RequestMapping(value="/background/crontab")
public class BgCrontabController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_crontab";
	
	@Resource(name="bgCrontabService")
	private BgCrontabService bgCrontabService;
	
	
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
		List<PageData>	bgCrontabList = bgCrontabService.listPage(bgPage);	//列出bgCrontab列表
		
		mv.addObject("bgCrontabList", bgCrontabList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/crontab/bgCrontabList");

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
		
		BgCrontab bgCrontab = new BgCrontab();
		bgCrontab.setCrontabCode("");
		bgCrontab.setCrontabName("");
		bgCrontab.setCrontabType("01");
		bgCrontab.setJobName("");
		bgCrontab.setJobGroupName("");
		bgCrontab.setJobClass("");
		bgCrontab.setTriggerName("");
		bgCrontab.setTriggerGroupName("");
		bgCrontab.setTimeExp("");
		bgCrontab.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(bgCrontab);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/crontab/bgCrontabEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) BgCrontab bgCrontab, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgCrontab");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgCrontab> bgCrontabList = bgCrontabService.otherHaveCode("", bgCrontab.getCrontabCode());
		if(MapleUtil.notEmptyList(bgCrontabList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		bgCrontabService.add(bgCrontab);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String crontabId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		BgCrontab bgCrontab = bgCrontabService.findById(crontabId);	//根据ID读取
		if(bgCrontab == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(bgCrontab);
		resultInfo.setResultCode("success");
		mv.setViewName("background/crontab/bgCrontabEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) BgCrontab bgCrontab, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgCrontab");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgCrontab> bgCrontabList = bgCrontabService.otherHaveCode(bgCrontab.getCrontabId(), bgCrontab.getCrontabCode());	
		if(MapleUtil.notEmptyList(bgCrontabList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		bgCrontabService.edit(bgCrontab);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String crontabId, @RequestParam String crontabCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<BgCrontab> bgCrontabList = bgCrontabService.otherHaveCode(crontabId, crontabCode);	
		if(MapleUtil.emptyList(bgCrontabList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String crontabId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		bgCrontabService.deleteById(crontabId);	//根据ID删除
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
		bgCrontabService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("定时任务 主键id");		//0
		titles.add("定时任务代号");	//1
		titles.add("定时任务名称");	//2
		titles.add("定时任务类型");	//3
		titles.add("定时任务状态");	//4
		titles.add("启动次数");	//5
		titles.add("任务名");	//6
		titles.add("任务组名");	//7
		titles.add("任务");	//8
		titles.add("触发器名");	//9
		titles.add("触发器组名");	//10
		titles.add("运作次数");	//11
		titles.add("停止时间");	//12
		titles.add("时间表达式");	//13
		titles.add("排序编号");	//14
		titles.add("有效标志");	//15
		titles.add("创建人员id");	//16
		titles.add("创建时间");	//17
		titles.add("修改人员id");	//18
		titles.add("修改时间");	//19
		dataMap.put("titles", titles);
		List<BgCrontab> varOList = bgCrontabService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getCrontabId());			//0
			vpd.put("var1", varOList.get(i).getCrontabCode());	//1
			vpd.put("var2", varOList.get(i).getCrontabName());	//2
			vpd.put("var3", varOList.get(i).getCrontabType());	//3
			vpd.put("var4", varOList.get(i).getCrontabStatus());	//4
			vpd.put("var5", varOList.get(i).getStartupTimes());	//5
			vpd.put("var6", varOList.get(i).getJobName());	//6
			vpd.put("var7", varOList.get(i).getJobGroupName());	//7
			vpd.put("var8", varOList.get(i).getJobClass());	//8
			vpd.put("var9", varOList.get(i).getTriggerName());	//9
			vpd.put("var10", varOList.get(i).getTriggerGroupName());	//10
			vpd.put("var11", varOList.get(i).getTriggerTimes());	//11
			vpd.put("var12", varOList.get(i).getEndTime());	//12
			vpd.put("var13", varOList.get(i).getTimeExp());	//13
			vpd.put("var14", varOList.get(i).getOrderNum());		//14
			vpd.put("var15", varOList.get(i).getEffective());	//15
			vpd.put("var16", varOList.get(i).getCreateUserId());	//16
			vpd.put("var17", varOList.get(i).getCreateTime());	//17
			vpd.put("var18", varOList.get(i).getModifyUserId());//18
			vpd.put("var19", varOList.get(i).getModifyTime());	//19
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
		
		mv.addObject("controllerPath", "background_crontab");
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
		titles.add("定时任务代号");	//0
		titles.add("定时任务名称");	//1
		titles.add("定时任务类型");	//2
		titles.add("任务名");	//3
		titles.add("任务组名");	//4
		titles.add("任务");	//5
		titles.add("触发器名");	//6
		titles.add("触发器组名");	//7
		titles.add("时间表达式");	//8
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "crontabexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		BgCrontab bgCrontab = new BgCrontab();
				
		/**
		 * var0 :定时任务代号;	//0
		 * var1 :定时任务名称;	//1
		 * var2 :定时任务类型;	//2
		 * var3 :任务名;	//3
		 * var4 :任务组名;	//4
		 * var5 :任务;	//5
		 * var6 :触发器名;	//6
		 * var7 :触发器组名;	//7
		 * var8 :时间表达式;	//8
		 */
		for(int i=0;i<listPd.size();i++){	
			bgCrontab.setCrontabId(this.get32UUID());
			bgCrontab.setCrontabCode(listPd.get(i).getString("var0"));
			bgCrontab.setCrontabName(listPd.get(i).getString("var1"));
			bgCrontab.setCrontabType(listPd.get(i).getString("var2"));
			bgCrontab.setJobName(listPd.get(i).getString("var3"));
			bgCrontab.setJobGroupName(listPd.get(i).getString("var4"));
			bgCrontab.setJobClass(listPd.get(i).getString("var5"));
			bgCrontab.setTriggerName(listPd.get(i).getString("var6"));
			bgCrontab.setTriggerGroupName(listPd.get(i).getString("var7"));
			bgCrontab.setTimeExp(listPd.get(i).getString("var8"));
			bgCrontabService.add(bgCrontab);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}

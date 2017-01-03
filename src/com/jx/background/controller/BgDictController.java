package com.jx.background.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComDict;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComDictService;

/** 
 * 类名称：BgDictController
 * 创建人：maple
 * 创建时间：2017-01-04
 */
@Controller
@RequestMapping(value="/background/dict")
public class BgDictController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background/dict";
	
	@Resource(name="comDictService")
	private ComDictService comDictService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		logBefore(logger, "列表comDict");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			bgPage.setPd(pd);
			List<PageData>	comDictList = comDictService.listPage(bgPage);	//列出comDict列表
			
			mv.addObject("comDictList", comDictList);
			mv.addObject("pd", pd);
			mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());	//按钮权限
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("background/dict/bgDictList");
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		logBefore(logger, "去新增comDict页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			mv.addObject("pathMsg", "add");
			mv.addObject("pd", pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		mv.setViewName("background/dict/bgDictEdit");
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Valid ComDict comDict, BindingResult result) throws Exception{
		logBefore(logger, "新增comDict");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			Date nowTime = new Date();
			
			if(result.hasErrors()) {
				mv.setViewName("background/dict/bgDictEdit");
	            return mv;  
	        }
			
			comDictService.add(comDict);
			mv.addObject("msg","success");
		} catch (Exception e) {
			mv.addObject("msg","false");
			logger.error(e.toString(), e);
		}
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit() throws Exception{
		logBefore(logger, "去修改comDict页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			ComDict comDict = comDictService.findById(pd);	//根据ID读取
			mv.addObject("pathMsg", "edit");
			mv.addObject("comDict", comDict);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		mv.setViewName("background/dict/bgDictEdit");
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Valid ComDict comDict, BindingResult result) throws Exception{
		logBefore(logger, "修改comDict");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			Date nowTime = new Date();
			
			if(result.hasErrors()) {
				mv.setViewName("background/dict/bgDictEdit");
	            return mv;  
	        }
			comDictService.edit(comDict);
			mv.addObject("msg","success");
		} catch (Exception e) {
			mv.addObject("msg","false");
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String dictId)throws Exception{
		logBefore(logger, "删除comDict");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		PageData pd = new PageData();
		
		try{
			pd = this.getPageData();
			comDictService.deleteById(dictId);
			errInfo = "success";
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "false";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/toBatchDelete")
	@ResponseBody
	public Object toBatchDelete() {
		logBefore(logger, "批量删除comDict");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			String dictIds = pd.getString("dictIds");
			if(null != dictIds && !"".equals(dictIds)){
				comDictService.batchDeleteByIds(dictIds.split(","));
				pd.put("msg", "success");
			}else{
				pd.put("msg", "false");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			pd.put("msg", "false");
		} 
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出comDict到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("dict 主键id");   	//1
			titles.add("数据字典主键id");	//2
			titles.add("数据字典代号");	//3
			titles.add("数据字典名称");	//4
			titles.add("数据字典类型");	//5
			titles.add("数据字典状态");	//6
			titles.add("数据字典值");	//7
			titles.add("级别");	//8
			titles.add("上级id");	//9
			titles.add("有效性");	//10
			titles.add("创建人员id");	//11
			titles.add("创建时间");	//12
			titles.add("修改人员id");	//13
			titles.add("修改时间");	//14
			dataMap.put("titles", titles);
			List<ComDict> varOList = comDictService.listByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var1",varOList.get(i).getDictId());		//1
				vpd.put("var2", varOList.get(i).getDictId);	//2
				vpd.put("var3", varOList.get(i).getDictCode);	//3
				vpd.put("var4", varOList.get(i).getDictName);	//4
				vpd.put("var5", varOList.get(i).getDictType);	//5
				vpd.put("var6", varOList.get(i).getDictStatus);	//6
				vpd.put("var7", varOList.get(i).getDictValue);	//7
				vpd.put("var8", varOList.get(i).getLevel);	//8
				vpd.put("var9", varOList.get(i).getParentId);	//9
				vpd.put("var10", varOList.get(i).getEffective);	//10
				vpd.put("var11", varOList.get(i).getCreateUserId);	//11
				vpd.put("var12", varOList.get(i).getCreateTime);	//12
				vpd.put("var13", varOList.get(i).getModifyUserId);	//13
				vpd.put("var14", varOList.get(i).getModifyTime);	//14
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUploadExcel")
	public ModelAndView goUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.addObject("pathObj","dict");
		mv.setViewName("background/bgUploadExcel");
		return mv;
	}
	
	/**下载模版
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcelModel")
	public ModelAndView downExcelModel()throws Exception{
		
		logBefore(logger, "导出bgUser到excel");
		ModelAndView mv = new ModelAndView();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("用户名");				//0+2
			dataMap.put("titles", titles);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
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
		Date nowTime = new Date();
		try {
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
				String fileName =  MapleFileUtil.fileUp(file, filePath, "userexcel");							//执行上传
				List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
				/*存入数据库操作======================================*/
				
				BgUser bgUser = new BgUser();
				bgUser.setUserRights("0");			// 权限
				bgUser.setLastLoginTime(nowTime);	// 最后登录时间
				bgUser.setLastLoginIp("127.0.0.1"); // loginIp
				bgUser.setUserIconSrc("static/ace/avatars/user.jpg"); // userIconSrc
				bgUser.setStatus("1");				// 状态
				bgUser.setModifyTime(nowTime); 		// 修改时间时间
				bgUser.setRoleId(5);
				
				/**
				 * var0 :用户名
				 * var1 :姓名
				 * var2 :用户编号
				 * var3 :电子邮箱
				 * var4 :手机号码
				 * var5 :备注
				 */
				for(int i=0;i<listPd.size();i++){	
					
					bgUser.setUserCode(listPd.get(i).getString("var0"));
					bgUser.setUserName(listPd.get(i).getString("var1"));
					bgUser.setUserNumber(listPd.get(i).getString("var2")); // loginIp
					bgUser.setEmail(listPd.get(i).getString("var3")); // loginIp
					bgUser.setPhone(listPd.get(i).getString("var4")); // loginIp
					bgUser.setRemarks(listPd.get(i).getString("var5")); // loginIp
					
					bgUser.setPassword(new SimpleHash("SHA-512", bgUser.getUserName(), bgUser.getUserName(),2).toString());
					bgUserService.add(bgUser);
				}
				/*存入数据库操作======================================*/
				mv.addObject("msg","success");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}	
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
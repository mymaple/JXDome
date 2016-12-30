package com.jx.background.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.service.BgMapleService;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.PageData;
import com.jx.common.util.MapleStringUtil;

@Controller
@RequestMapping(value="/background/maple")
public class BgMapleController extends BaseController {
	
	/**
	 * 后台 菜单标记名称(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background/maple";
	@Resource(name="bgMapleService")
	private BgMapleService bgMapleService;
	
	/**列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String keywords = pd.getString("keywords");	//检索条件
			if(null != keywords && !"".equals(keywords)){
				keywords = keywords.trim();
				pd.put("keywords", keywords);
			}
			bgPage.setPd(pd);
			List<PageData> bgMapleList = bgMapleService.listPage(bgPage);	//列出CreateCode列表
			mv.addObject("bgMapleList", bgMapleList);
			mv.addObject("pd", pd);
			mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());	//按钮权限
			
			mv.setViewName("background/bgMaple/bgMapleList");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(){
		logBefore(logger, "去新增bgMaple页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("msg", "add");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/maple/bgMapleEdit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add() throws Exception{
		logBefore(logger, "新增bgMaple");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try{
			bgMapleService.addByPd(pd); //保存菜单
			mv.addObject("msg","success");
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(){
		logBefore(logger, "去修改bgMaple页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			int mapleId = Integer.parseInt(pd.get("mapleId").toString());
			pd = bgMapleService.findPdByPd(pd);	//根据ID读取
			
			mv.addObject("mapleId", mapleId);
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/maple/bgMapleEdit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改bgMaple");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			bgMapleService.editByPd(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	
	
	/**去代码生成器页面(进入弹窗)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toDetail")
	public ModelAndView goProductCode() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String CREATECODE_ID = pd.getString("CREATECODE_ID");
		if(!"add".equals(CREATECODE_ID)){
			pd = bgMapleService.findById(pd);
			mv.addObject("pd", pd);
			mv.addObject("msg", "edit");
			
		}else{
			mv.addObject("msg", "add");
		}
		List<PageData> varList = bgMapleService.listFa(); //列出所有主表结构的
		mv.addObject("varList", varList);
		mv.setViewName("system/bgMaple/productCode");
		return mv;
	}
	
	/**生成代码
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/proCode")
	public void proCode(HttpServletResponse response) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){} 		//校验权限
		logBefore(logger, Jurisdiction.getUsername()+"执行代码生成器");
		PageData pd = new PageData();
		pd = this.getPageData();
		save(pd);	//保存到数据库
		/* ============================================================================================= */
		String faobject = pd.getString("faobject");  				//主表名				========参数0-1 主附结构用
		String FHTYPE = pd.getString("FHTYPE");  					//模块类型			========参数0-2 类型，单表、树形结构、主表明细表
		String TITLE = pd.getString("TITLE");  						//说明				========参数0
		String packageName = pd.getString("packageName");  			//包名				========参数1
		String objectName = pd.getString("objectName");	   			//类名				========参数2
		String tabletop = pd.getString("tabletop");	   				//表前缀				========参数3
		tabletop = null == tabletop?"":tabletop.toUpperCase();		//表前缀转大写
		String zindext = pd.getString("zindex");	   	   			//属性总数
		int zindex = 0;
		if(null != zindext && !"".equals(zindext)){
			zindex = Integer.parseInt(zindext);
		}
		List<String[]> fieldList = new ArrayList<String[]>();   	//属性集合			========参数4
		for(int i=0; i< zindex; i++){
			fieldList.add(pd.getString("field"+i).split(",fh,"));	//属性放到集合里面
		}
		Map<String,Object> root = new HashMap<String,Object>();		//创建数据模型
		root.put("fieldList", fieldList);
		root.put("faobject", faobject.toUpperCase());				//主附结构用，主表名
		root.put("TITLE", TITLE);									//说明
		root.put("packageName", packageName);						//包名
		root.put("objectName", objectName);							//类名
		root.put("objectNameLower", objectName.toLowerCase());		//类名(全小写)
		root.put("objectNameUpper", objectName.toUpperCase());		//类名(全大写)
		root.put("tabletop", tabletop);								//表前缀	
		root.put("nowDate", new Date());							//当前日期
		
		DelAllFile.delFolder(PathUtil.getClasspath()+"admin/ftl"); //生成代码前,先清空之前生成的代码
		/* ============================================================================================= */
		String filePath = "admin/ftl/code/";						//存放路径
		String ftlPath = "bgMaple";								//ftl路径
		if("tree".equals(FHTYPE)){
			ftlPath = "createTreeCode";
			/*生成实体类*/
			Freemarker.printFile("entityTemplate.ftl", root, "entity/"+packageName+"/"+objectName+".java", filePath, ftlPath);
			/*生成jsp_tree页面*/
			Freemarker.printFile("jsp_tree_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_tree.jsp", filePath, ftlPath);
		}else if("fathertable".equals(FHTYPE)){
			ftlPath = "createFaCode";	//主表
		}else if("sontable".equals(FHTYPE)){
			ftlPath = "createSoCode";	//明细表
		}
		/*生成controller*/
		Freemarker.printFile("controllerTemplate.ftl", root, "controller/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Controller.java", filePath, ftlPath);
		/*生成service*/
		Freemarker.printFile("serviceTemplate.ftl", root, "service/"+packageName+"/"+objectName.toLowerCase()+"/impl/"+objectName+"Service.java", filePath, ftlPath);
		/*生成manager*/
		Freemarker.printFile("managerTemplate.ftl", root, "service/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Manager.java", filePath, ftlPath);
		/*生成mybatis xml*/
		Freemarker.printFile("mapperMysqlTemplate.ftl", root, "mybatis_mysql/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
		Freemarker.printFile("mapperOracleTemplate.ftl", root, "mybatis_oracle/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
		Freemarker.printFile("mapperSqlserverTemplate.ftl", root, "mybatis_sqlserver/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
		/*生成SQL脚本*/
		Freemarker.printFile("mysql_SQL_Template.ftl", root, "mysql数据库脚本/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
		Freemarker.printFile("oracle_SQL_Template.ftl", root, "oracle数据库脚本/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
		Freemarker.printFile("sqlserver_SQL_Template.ftl", root, "sqlserver数据库脚本/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
		/*生成jsp页面*/
		Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_list.jsp", filePath, ftlPath);
		Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_edit.jsp", filePath, ftlPath);
		/*生成说明文档*/
		Freemarker.printFile("docTemplate.ftl", root, "部署说明.doc", filePath, ftlPath);
		//this.print("oracle_SQL_Template.ftl", root);  控制台打印
		/*生成的全部代码压缩成zip文件*/
		if(FileZip.zip(PathUtil.getClasspath()+"admin/ftl/code", PathUtil.getClasspath()+"admin/ftl/code.zip")){
			/*下载代码*/
			FileDownload.fileDownload(response, PathUtil.getClasspath()+"admin/ftl/code.zip", "code.zip");
		}
	}
	
	/**保存到数据库
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception{
		pd.put("PACKAGENAME", pd.getString("packageName"));	//包名
		pd.put("OBJECTNAME", pd.getString("objectName"));	//类名
		pd.put("TABLENAME", pd.getString("tabletop")+",fh,"+pd.getString("objectName").toUpperCase());	//表名
		pd.put("FIELDLIST", pd.getString("FIELDLIST"));		//属性集合
		pd.put("CREATETIME", DateUtil.getTime());			//创建时间
		pd.put("TITLE", pd.getString("TITLE"));				//说明
		pd.put("CREATECODE_ID", this.get32UUID());			//主键
		bgMapleService.save(pd);
	}
	
	/**
	 * 通过ID获取数据
	 */
	@RequestMapping(value="/findById")
	@ResponseBody
	public Object findById() throws Exception {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = bgMapleService.findById(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}
	
	/**删除
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除CreateCode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		bgMapleService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception {
		logBefore(logger, Jurisdiction.getUsername()+"批量删除CreateCode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				bgMapleService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
}
//FHQ 3 1 3 5 9 6 7 9 0
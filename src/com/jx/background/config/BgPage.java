package com.jx.background.config;

import javax.annotation.Resource;

import com.jx.background.service.BgConfigService;
import com.jx.common.config.PageData;

public class BgPage {

	@Resource(name = "bgConfigService")
	private BgConfigService bgConfigService;
	
	
	private int showCount; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private PageData pd = new PageData();

	public BgPage() {
		try {
			/*// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			BgConfig bgConfigSystem = (BgConfig) session.getAttribute(Const.CONFIG_BG_SYSTEM_OBJ);
			if (bgConfigSystem == null) {
				bgConfigSystem = bgConfigService.findByConfigType(Const.CONFIG_BG_SYSTEM_OBJ);
				session.setAttribute(Const.CONFIG_BG_SYSTEM_OBJ,bgConfigSystem);
			}
			String count = bgConfigSystem.getParam2();
			if(null != count && !"".equals(count)){
				this.showCount = Integer.parseInt(count);
			}*/
			this.showCount = 10;
		} catch (Exception e) {
			this.showCount = 5;
		}
	}

	public int getTotalPage() {
		if(totalResult%showCount==0)
			totalPage = totalResult/showCount;
		else
			totalPage = totalResult/showCount+1;
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		if(currentPage>getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	//拼接分页 页面及JS函数
	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("	<ul class=\"pagination pull-right no-margin\">\n");
			if(currentPage==1){
				sb.append("	<li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
				sb.append("	<li><a>首页</a></li>\n");
				sb.append("	<li><a>上页</a></li>\n");
			}else{
				sb.append("	<li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">首页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage-1)+")\">上页</a></li>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li class=\"active\"><a><font color='white'>"+i+"</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+i+")\">"+i+"</a></li>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<li><a>下页</a></li>\n");
				sb.append("	<li><a>尾页</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage+1)+")\">下页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+totalPage+")\">尾页</a></li>\n");
			}
			sb.append("	<li><a>共"+totalPage+"页</a></li>\n");
			sb.append("	<li><select title='显示条数' style=\"width:55px;float:left;margin-top:1px;\" onchange=\"changeCount(this.value)\">\n");
			sb.append("	<option style=\"background-color:#B1B1B1;\" value='"+showCount+"'>"+showCount+"</option>\n");
			if(10!=showCount){
				sb.append("	<option value='10'>10</option>\n");
			}
			if(20!=showCount){
				sb.append("	<option value='20'>20</option>\n");
			}
			if(50!=showCount){
				sb.append("	<option value='50'>50</option>\n");
			}
			if(100!=showCount){
				sb.append("	<option value='100'>100</option>\n");
			}
			if(1000!=showCount){
				sb.append("	<option value='1000'>1000</option>\n");
			}
			sb.append("	</select>\n");
			sb.append("	</li>\n");
			
			sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");
			
			//换页函数
			sb.append("function nextPage(pageNum){\n");
			sb.append(" top.jzts();\n");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"bgPage.currentPage")+"=\";}\n");
			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"bgPage.currentPage")+"=\";}\n");
			sb.append("		url = url + pageNum + \"&" +(entityOrField?"showCount":"bgPage.showCount")+"="+showCount+"\";\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage='+pageNum);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"currentPage":"bgPage.currentPage")+"=\" + pageNum;\n") ;
			sb.append("			}\n");
			sb.append("			if(url.indexOf('showCount')>-1){\n");
			sb.append("				var reg = /showCount=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'showCount="+showCount+ "');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"showCount":"bgPage.showCount") + "=" + showCount + "\";\n") ;
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"bgPage.currentPage")+"=\" + pageNum+ \"&" +(entityOrField?"showCount":"bgPage.showCount") + "=" + showCount + "\";}\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//调整每页显示条数
			sb.append("function changeCount(value){\n");
			sb.append(" top.jzts();\n");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"bgPage.currentPage")+"=\";}\n");
			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"bgPage.currentPage")+"=\";}\n");
			sb.append("		url = url + \"1&" +(entityOrField?"showCount":"bgPage.showCount")+"=\"+value;\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=1');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"currentPage":"bgPage.currentPage")+"=1\" ;\n") ;
			sb.append("			}\n");
			sb.append("			if(url.indexOf('showCount')>-1){\n");
			sb.append("				var reg = /showCount=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'showCount='+value );\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"showCount":"bgPage.showCount") + "=\" + value;\n") ;
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"bgPage.currentPage")+"=\" + pageNum+ \"&" +(entityOrField?"showCount":"bgPage.showCount")  + "=\" + value};\n") ;
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//跳转函数 
			sb.append("function toTZ(){\n");
			sb.append("	var toPageVlue = document.getElementById(\"toGoPage\").value;\n");
			sb.append("	if(toPageVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}\n");
			sb.append("	if(isNaN(Number(toPageVlue))){document.getElementById(\"toGoPage\").value=1;return;}\n");
			sb.append("	nextPage(toPageVlue);\n");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	
	public int getShowCount() {
		return showCount;
	}
	
	public void setShowCount(int showCount) {
		
		this.showCount = showCount;
	}
	
	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getShowCount();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	
	public boolean isEntityOrField() {
		return entityOrField;
	}
	
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	
	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}
	
}


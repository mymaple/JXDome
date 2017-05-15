package com.jx.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.jx.common.config.Const;

public class FileUpimgTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
	private String count;
	private String maxWidth;
	private String maxHight;
	private String cssClass;
	private String styleClass;
	

	public int doEndTag() throws JspException {

		StringBuffer sb = new StringBuffer();
		JspWriter out = pageContext.getOut();
		
		sb.append("<div class=\"upimg_box ");
		if (!StringUtils.isEmpty(this.getCssClass())) {
			sb.append(this.getCssClass() + "\"");
		}
		if (!StringUtils.isEmpty(this.getStyleClass())) {
			sb.append("style=\"" + this.getStyleClass() + "\"");
		}
		sb.append("\">");
		sb.append("	<input type=\"hidden\" class=\"upimg_name\" value=\""
					+this.name+"\" />");
		int maxCount = (StringUtils.isEmpty(this.count)?0:Integer.parseInt(this.count));
		sb.append("	<input type=\"hidden\" class=\"upimg_count\" value=\""
				+maxCount+"\" />");
		sb.append("	<input type=\"hidden\" class=\"upimg_maxWidth\" value=\""
				+(StringUtils.isEmpty(this.maxWidth)?"0":this.maxWidth)+"\" />");
		sb.append("	<input type=\"hidden\" class=\"upimg_maxHight\" value=\""
				+(StringUtils.isEmpty(this.maxHight)?"0":this.maxHight)+"\" />");
		
		sb.append("	<ul>");
		
		int valCount = 0;
		if(!StringUtils.isEmpty(this.value)){
			String[] srcs = this.value.split(",");
			valCount = srcs.length;
			for(int i=0;i<valCount&&(i<maxCount||maxCount==0);i++){
		sb.append("		<li>");		
		sb.append("			<div class=\"upimg_cell upimg_edit\">");
		sb.append("				<input type=\"hidden\" name=\""+this.name+"\" value=\""+srcs[i]+"\" />");
		sb.append("				<div class=\"upimg_error\"></div>");
		sb.append("				<img src=\"plugins/myupimg/img/loading.gif\" data-original=\""+srcs[i]+"\" class=\"lazy\" />");
		sb.append("				<div class=\"upimg_tools\">");
		sb.append("					<div class=\"upimg_tools_item left\">");
		sb.append("						<input type=\"file\" class=\"upimg_upload\""
				+ " accept=\"image/*\" onchange=\"upimg_toUpdateUploadImg(this);\"/>");
		sb.append("						<i class=\"iconfont icon-edit\"></i>");
		sb.append("					</div>");
		sb.append("					<div class=\"upimg_tools_item right\">");
		sb.append("						<div onclick=\"upimg_toDeleteUploadImg(this);\">"
				+ "<i class=\"iconfont icon-delete\"></i></div>");
		sb.append("					</div>");
		sb.append("				</div>");
		
		sb.append("			</div>");
		sb.append("		</li>");		
			}
		}
		if(maxCount!=0 && maxCount<=valCount){
		sb.append("		<li style=\"display: none;\">");
		}else{
		sb.append("		<li>");
		}
		sb.append("			<div class=\"upimg_cell upimg_add\">");
		sb.append("				<div class=\"upimg_error\"></div>");
		sb.append("				<em class=\"upimg_add_b abs\"></em>");
		sb.append("				<em class=\"upimg_add_a abs\"></em>");
		sb.append("				<input type=\"file\" class=\"upimg_upload\""
				+ " accept=\"image/*\" onchange=\"upimg_toAddUploadImg(this);\""
				+ ("1".equals(this.count)?"":" multiple=\"multiple\"")
				+ "/>");
		sb.append("			</div>");
		sb.append("		</li>");
		sb.append("	</ul>");
		sb.append("</div>");
		
		
		
		
		try {
			out.write(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return TagSupport.EVAL_PAGE;

	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}


	public String getValue() {
		return this.value;
	}


	public void setValue(String value) {
		this.value = StringUtils.trim(value);
	}


	public String getCount() {
		return this.count;
	}


	public void setCount(String count) {
		this.count = StringUtils.trim(count);
	}


	public String getMaxWidth() {
		return this.maxWidth;
	}


	public void setMaxWidth(String maxWidth) {
		this.maxWidth = StringUtils.trim(maxWidth);
	}


	public String getMaxHight() {
		return this.maxHight;
	}


	public void setMaxHight(String maxHight) {
		this.maxHight = StringUtils.trim(maxHight);
	}


	public String getCssClass() {
		return this.cssClass;
	}


	public void setCssClass(String cssClass) {
		this.cssClass = StringUtils.trim(cssClass);
	}


	public String getStyleClass() {
		return this.styleClass;
	}


	public void setStyleClass(String styleClass) {
		this.styleClass = StringUtils.trim(styleClass);
	}
	
}

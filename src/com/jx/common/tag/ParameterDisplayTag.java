package com.jx.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.jx.common.service.ComDictService;
import com.jx.common.util.ServletContextUtil;

public class ParameterDisplayTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ComDictService comDictService;
	
	private String id;
	
	private String name;
	
	private String type;

	private String value;
	
	private boolean hidden = false;
	
	@Override
	public int doEndTag() throws JspException {

		if (comDictService == null) {
			comDictService = (ComDictService) ServletContextUtil.getBean("comDictService");
		}
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		if(this.isHidden()){
			sb.append("<input type='hidden' ");
			if (!StringUtils.isEmpty(this.getId())) {
				sb.append("id=\"" + this.getId() + "\"");
			}
			if (!StringUtils.isEmpty(this.getName())) {
				sb.append("name=\"" + this.getName() + "\"");
			}
			if (!StringUtils.isEmpty(this.getValue())) {
				sb.append("value=\"" + this.getValue() + "\"");
			}
			sb.append("/>");
		}
		try {
			sb.append(comDictService.getDisplayName(this.getType(), this.getValue()));
			out.write(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		} catch (Exception e) {
			throw new JspException(e);
		}

		return TagSupport.EVAL_PAGE;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
}

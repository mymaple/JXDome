package com.jx.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.jx.common.service.ComDictService;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.SpringContextUtil;

public class ParameterDisplayTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	
	private String type;

	private String value;
	
	private boolean hidden = false;
	
	@Override
	public int doEndTag() throws JspException {
		ComDictService comDictService = (ComDictService) SpringContextUtil.getBean("comDictService");
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
			String displayName = comDictService.getDisplayName(this.getType(), this.getValue());
			displayName = MapleStringUtil.isEmpty(displayName)?this.getValue():displayName;
			sb.append(displayName);
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

package com.jx.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

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

	private String type;

	private String value;

	public int doEndTag() throws JspException {

		if (comDictService == null) {
			comDictService = (ComDictService) ServletContextUtil.getBean("comDictService");
		}
		JspWriter out = pageContext.getOut();
		
		try {
			out.write(comDictService.getDisplayName(this.getType(), this.getValue()));
		} catch (IOException e) {
			throw new JspException(e);
		} catch (Exception e) {
			throw new JspException(e);
		}

		return TagSupport.EVAL_PAGE;

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

}

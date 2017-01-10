package com.jx.common.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.jx.common.entity.ComDict;
import com.jx.common.service.ComDictService;
import com.jx.common.util.ServletContextUtil;

public class ParameterSelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ComDictService comDictService;

	private String id;
	private String name;
	private String cssClass;
	private String styleClass;
	private String type;
	private String multiple;
	private String onChange;

	private String value;
	private String placeholder;
	private String title;
	

	public int doEndTag() throws JspException {

		if (comDictService == null) {
			comDictService = (ComDictService) ServletContextUtil.getBean("comDictService");
		}
		List<ComDict> comDictList = new ArrayList<ComDict>();
		try {
			comDictList = comDictService.listSelect(this.getType());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		JspWriter out = pageContext.getOut();

		sb.append("<select name=\"" + this.getName() + "\"");

		if (!StringUtils.isEmpty(this.getId())) {
			sb.append("id=\"" + this.getId() + "\"");
		}
		if (!StringUtils.isEmpty(this.getCssClass())) {
			sb.append("class=\"" + this.getCssClass() + "\"");
		}
		if (!StringUtils.isEmpty(this.getStyleClass())) {
			sb.append("style=\"" + this.getStyleClass() + "\"");
		}
		if (!StringUtils.isEmpty(this.getMultiple())) {
			sb.append("multiple=\"" + this.getMultiple() + "\"");
		}
		if (!StringUtils.isEmpty(this.getOnChange())) {
			sb.append("onchange=\"" + this.getOnChange() + "\"");
		}
		if (!StringUtils.isEmpty(this.getPlaceholder())) {
			sb.append("data-placeholder=\"" + this.getPlaceholder() + "\"");
		}
		if (!StringUtils.isEmpty(this.getTitle())) {
			sb.append("title=\"" + this.getTitle() + "\"");
		}
		sb.append(">");
		sb.append("<option value=\"\">");
		for (ComDict comDict : comDictList) {
			if (comDict.getDictValue().equals(this.getValue())) {
				sb.append("<option value=\"" + comDict.getDictValue() + "\" selected>");
			} else {
				sb.append("<option value=\"" + comDict.getDictValue() + "\">");
			}
			sb.append(comDict.getDictName() + "</option>");
		}
		sb.append("</select>");
		try {
			out.write(sb.toString());
		} catch (IOException e) {
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

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
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

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}

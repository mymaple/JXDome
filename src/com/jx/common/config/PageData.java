package com.jx.common.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleDateUtil.SDF;

@SuppressWarnings("rawtypes")
public class PageData extends HashMap implements Map {

	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;

	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry;
			String name = "";
			String value = "";
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value += values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public PageData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		Object obj = this.get(key);
		String str = "";
		if (obj instanceof String) {
			str = (String) obj;
		} else if (obj instanceof Integer) {
			str = String.valueOf(((Integer) obj).intValue());
	    } else if (obj instanceof Double) {
	    	str = String.valueOf(((Double) obj).doubleValue());
	    } else if (obj instanceof Float) {
	    	str = String.valueOf(((Float) obj).floatValue());
	    } else if (obj instanceof Long) {
	    	str = String.valueOf(((Long) obj).longValue());
	    } else if (obj instanceof Boolean) {
		   	str = String.valueOf(((Boolean) obj).booleanValue());
	    } else if (obj instanceof Date) {
	    	str = MapleDateUtil.formatDate(SDF.TIME, (Date) obj);
	    }  
		
		return str;
	}
	
	public int getInt(Object key) {
		return (int) get(key);
	}
	
	
	public int getStringToInt(Object key) {
		String num = (String) get(key);
		if(num != null && !"".equals(num) && num.matches(Const.REG_COM_FFZS_STR)){
			return Integer.parseInt(num);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set keySet() {
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}

}

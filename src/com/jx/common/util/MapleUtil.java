package com.jx.common.util;

import java.util.List;

public class MapleUtil {
	
	/**
	 * 检测List是否为empty
	 * @param List list
	 * @return 不为空则返回true，否则返回false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean emptyList(List list) {
		return list == null || list.size() == 0;
	}
	
	/**
	 * 检测List是否不为empty
	 * @param List list
	 * @return 不为空则返回true，否则返回false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean notEmptyList(List list) {
		return list != null && list.size() > 0;
	}
}

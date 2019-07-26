package com.yc.votesys.util;

public class StringUtil {
	/**
	 * 判断是否为空
	 * @param strs
	 * @return
	 */
	public static boolean isNull(String ... strs) {
		if (strs == null || strs.length <= 0) {
			return true;
		}
		
		for (String str : strs) {
			if (str == null || "".equals(str)) {
				return true;
			}
		}
		return false;
	}
}

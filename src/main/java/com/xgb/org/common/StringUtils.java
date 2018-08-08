package com.xgb.org.common;

import java.util.UUID;

/**
 * StringUtils
 * <p>Title: StringUtils 工具类</p>
 * <p>Description: </p>
 *  	
 * @author XuGongBin
 * @version 1.0.0
 * @date 2018/7/16 16:20
 */
public class StringUtils {

	/**
	 * 获取UUID
	 * 经过字符串处理的UUID（替换"-"为""）
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	
	
	/**
	 * 去掉字符串的第一个‘[’ 和最后一个 ‘]’
	 * 
	 * @param str
	 * @return 返回去掉后的字符串
	 */
	public static String removeBracket(String str) {
		if (str != null && !"".equals(str)) {
			StringBuffer sb = new StringBuffer(str);
			if ("[".equals(String.valueOf(sb.charAt(0)))) {
				sb = sb.deleteCharAt(0);
				if ("]".equals(String.valueOf(sb.charAt(sb.length() - 1)))) {
					sb = sb.deleteCharAt(sb.length() - 1);
				}
			}
			return sb.toString();
		}
		return str;
	}
}

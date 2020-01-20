package com.wandu.adb.util;

/**
 * 断言工具类
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public final class AssertUtils {
	
	private AssertUtils() {
	}
	
	/**
	 * 是否为true
	 * @param isTrue true，条件符合通过；false，条件不符合中断；
	 * @param msg 条件不合适时，抛出的异常信息
	 */
	public static void assertTrue(boolean isTrue, String msg) {
		if (!isTrue) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	/**
	 * @param str是否为非空
	 * @param str @param str非空，条件符合通过；@param str为空，条件不符合中断；
	 * @param msg 条件不合适时，抛出的异常信息
	 */
	public static void assertNotEmpty(String str, String msg) {
		if (Utility.isEmpty(str)) {
			throw new IllegalArgumentException(msg);
		}
	}
}

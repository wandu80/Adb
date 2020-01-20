package com.wandu.adb.util;

import java.util.List;

public final class Utility {
	
	private Utility() {
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static boolean isNullOrEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}
	
	@SuppressWarnings("finally")
	public static int parseInt(String str, int def) {
		int result = def;
		try {
			result = Integer.parseInt(str);
		} catch(Exception e) {
		} finally {
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public static long parseLong(String str, long def) {
		long result = def;
		try {
			result = Long.parseLong(str);
		} catch(Exception e) {
		} finally {
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean isTrue(Object value) {
		boolean result = false;
		try {
			result = Boolean.valueOf(value.toString());
		} catch(Exception e) {
		} finally {
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public static float parseFloat(String str, float def) {
		float result = def;
		try {
			result = Float.parseFloat(str);
		} catch(Exception e) {
		} finally {
			return result;
		}
	}
	
	public static String contact(Object...args) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			sb.append(arg);
		}
		return sb.toString();
	}
	

}

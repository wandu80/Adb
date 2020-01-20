package com.wandu.adb.util;

public final class Log {
	
	private static boolean sEnable = false;
	
	private Log() {
	}
	
	public static void setEnabled(boolean enabled) {
		sEnable = enabled;
	}
	
	public static void d(Object...messages) {
		if (!sEnable) {
			return;
		}
		System.out.println(Utility.contact(messages));
	}
	
	public static void e(Object...messages) {
		if (!sEnable) {
			return;
		}
		System.err.println(Utility.contact(messages));
	}
}

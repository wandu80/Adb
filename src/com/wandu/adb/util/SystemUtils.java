package com.wandu.adb.util;

public final class SystemUtils {
	
	private SystemUtils() {
	}
	
	private static final String OS = System.getProperty("os.name");
	
	public static final boolean isOtherOS = OS == null;
	public static final boolean isLinuxOS = !isOtherOS && OS.trim().toLowerCase().contains("linux");
	public static final boolean isMacOS = !isOtherOS && OS.trim().toLowerCase().contains("mac");
	public static final boolean isWindosOS = !isOtherOS && OS.trim().toLowerCase().contains("windows");
	
}

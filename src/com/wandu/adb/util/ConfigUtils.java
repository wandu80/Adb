package com.wandu.adb.util;

import com.wandu.adb.ConfigManager;

public final class ConfigUtils {
	
	private static final String ADB_INITED = "adb_inited";
	private static final String FILES_SERIALNO = "files_serialno";
	
	private ConfigUtils() {
	}
	
	public static boolean isAdbInit() {
		return ConfigManager.getInstance().isTrue(ADB_INITED);
	}
	
	public static boolean setAdbInit(boolean inited) {
		return ConfigManager.getInstance().put(ADB_INITED, inited);
	}
	
	public static long fileSerialno() {
		long no = ConfigManager.getInstance().getLong(FILES_SERIALNO);
		ConfigManager.getInstance().put(FILES_SERIALNO, no + 1);
		return no;
	}
}

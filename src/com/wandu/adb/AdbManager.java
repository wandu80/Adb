package com.wandu.adb;

import java.io.File;
import java.util.List;

import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.AssertUtils;
import com.wandu.adb.util.ConfigUtils;
import com.wandu.adb.util.FileUtils;
import com.wandu.adb.util.Log;
import com.wandu.adb.util.SystemUtils;

public final class AdbManager {
	
	private static final boolean DEBUG = true;
	
	private static final AdbManager INSTANCE = new AdbManager();
	
	private AdbManager() {
		Log.setEnabled(DEBUG);
	}
	
	public static final AdbManager getInstance() {
		return INSTANCE;
	}
	
	private boolean hasInit = false;
	
	public final boolean hasInit() {
		return hasInit;
	}
	
	public void init() {
		if (hasInit) {
			return;
		}
		Log.d("开始初始化...");
		hasInit = tryInitAdb();
		AssertUtils.assertTrue(hasInit, "初始化失败！");
		Log.d("初始化完毕.");
	}
	
	private boolean tryInitAdb() {
		if (!SystemUtils.isMacOS && !SystemUtils.isLinuxOS) {
			return true;
		}
		
		if (ConfigUtils.isAdbInit()) {
			Log.d("adb已初始化过！");
			return true;
		}
		List<File> files = FileUtils.findAllFile(new File(FileUtils.ADB_TOOLS_PATH));
		for (File file : files) {
			boolean success = !AdbUtils.exec(String.format("chmod a+x %s", file.getAbsoluteFile())).contains(AdbUtils.FAILURE);
			if (!success) {
				AssertUtils.assertTrue(hasInit, "初始化失败！");
				ConfigUtils.setAdbInit(false);
				return false;
			}
		}
		List<String> info = AdbUtils.exec(String.format("%sadb version", FileUtils.ADB_TOOLS_PATH));
		for (String inf : info) {
			if (inf != null && inf.contains(FileUtils.ADB_TOOLS_PATH)) {
				ConfigUtils.setAdbInit(true);
				return true;
			}
		}
		ConfigUtils.setAdbInit(false);
		return false;
	}
}

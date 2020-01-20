package com.wandu.adb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import com.wandu.adb.util.FileUtils;
import com.wandu.adb.util.Utility;

/**
 * 配置信息处理Manager
 *
 * @author kejunyao
 * @since 2020年01月17日
 */
public final class ConfigManager {
	
	private static final ConfigManager INSTANCE = new ConfigManager();
	
	private ConfigManager() {
	}
	
	public static ConfigManager getInstance() {
		return INSTANCE;
	}
	
	@SuppressWarnings("finally")
	public synchronized boolean put(String key, String value) {
		shouldLoadProperties();
		boolean success = false;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(FileUtils.CONFIG_FILE_PATH);
			properties.setProperty(key, value);
			properties.store(fos, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeSafely(fos);
			return success;
		}
	}
	
	public synchronized boolean put(String key, Object value) {
		return put(key, String.valueOf(value));
	}
	
	public boolean isTrue(String key) {
		shouldLoadProperties();
		Object value = properties.get(key);
		return value == null ? false : Utility.isTrue(value);
	}
	
	public int getInt(String key) {
		shouldLoadProperties();
		Object value = properties.get(key);
		return value == null ? 0 : Utility.parseInt(value.toString(), 0);
	}
	
	public long getLong(String key) {
		shouldLoadProperties();
		Object value = properties.get(key);
		return value == null ? 0 : Utility.parseLong(value.toString(), 0);
	}
	
	private final Properties properties = new Properties();
	private boolean ifPropertiesLoaded;
	private void shouldLoadProperties() {
		if (ifPropertiesLoaded) {
			return;
		}
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(FileUtils.CONFIG_FILE_PATH));
			properties.load(bufferedReader);
			ifPropertiesLoaded = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeSafely(bufferedReader);
		}
	}
}

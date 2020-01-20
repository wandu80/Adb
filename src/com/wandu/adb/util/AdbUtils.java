package com.wandu.adb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class AdbUtils {
	
	public static final String PATTERN_BLANK = "\\s+";
	public static final String PATTERN_COLON = ":";
	
	public static final String SUCCESS = "adb-exec-success";
	public static final String FAILURE = "adb-exec-failure";
	
	private AdbUtils() {
	}
	
	@SuppressWarnings("finally")
	public static List<String> exec(String cmd) {
		List<String> result = new ArrayList<>();
		Process process = null;
		InputStream inputStream = null;
		BufferedReader reader = null;
		boolean success = false;
		try {
			process = Runtime.getRuntime().exec(cmd);
			inputStream = process.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				if (line.length() > 0) {
					result.add(line.trim());
				}
			}
			success = waitForSafely(process);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeSafely(inputStream);
			FileUtils.closeSafely(reader);
			destroySafely(process);
			result.add(success ? SUCCESS : FAILURE);
			return result;
		}
	}


	
	@SuppressWarnings("finally")
	private static boolean waitForSafely(Process process) {
		boolean success = false;
		try {
			success = process.waitFor() == 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return success;
		}
	}
	
	private static void destroySafely(Process process) {
		if (process != null) {
			try {
				process.destroy();
			} catch (Exception e) {
			}
		}
	}
	
	public static void sleepSafely(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

}

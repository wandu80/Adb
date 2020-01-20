package com.wandu.adb.bean;

import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.Utility;

public class WmSizeInfo {
	private int width;
	private int height;
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	@Override
	public String toString() {
		return "{width=" + width + ", height=" + height + "}";
	}

	public static WmSizeInfo parse(String result) {
		String[] array = result.split(AdbUtils.PATTERN_COLON);
		if (array == null || array.length < 2) {
			return null;
		}
		String s = array[1];
		if (s == null) {
			return null;
		}
		array = s.trim().split("x");
		if (array == null || array.length < 2) {
			return null;
		}
		WmSizeInfo info = new WmSizeInfo();
		info.width = Utility.parseInt(array[0], 0);
		info.height = Utility.parseInt(array[1], 0);
		return info;
	}
}

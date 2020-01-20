package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.FileUtils;
import com.wandu.adb.util.Utility;

/**
 * 截取设备屏幕保存为图片
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class Screencap extends AddressBooleanResult {
	
	private String filepath;
	public final Screencap setFilepath(String path) {
		this.filepath = path;
		return this;
	}
	
	@Override
	protected String command() {
		// AdbUtils.assertNotEmpty(filepath, "截图路径不能为空，请设置有效的路径！");
		if (Utility.isEmpty(filepath)) {
			filepath = FileUtils.createDefalutImageRemotePath();
		}
		checkDevice();
		return String.format("adb%1$sshell screencap %2$s", getSerialnoOption(), filepath);
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = execResult.contains(AdbUtils.SUCCESS);
		return result;
	}
}

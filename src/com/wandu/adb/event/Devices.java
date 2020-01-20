package com.wandu.adb.event;

import java.util.ArrayList;
import java.util.List;
import com.wandu.adb.bean.DeviceInfo;

/**
 * 查看开发计算机上所有连接设备
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class Devices extends Adb<List<DeviceInfo>> {

	private static final String COMMAND = "adb devices";
	private static final String COMMAND_L = "adb devices -l";
	
	private final List<DeviceInfo> devices = new ArrayList<>();
	
	private boolean isL;
	public Devices setL(boolean isL) {
		this.isL = isL;
		return this;
	}

	@Override
	protected String command() {
		return isL ? COMMAND_L : COMMAND;
	}

	@Override
	protected boolean process(List<String> execResult) {
		devices.clear();
		for (String str : execResult) {
			DeviceInfo device = DeviceInfo.parse(str);
			if (device != null) {
				devices.add(device);
			}
		}
		return !devices.isEmpty();
	}
	
	@Override
	public List<DeviceInfo> getResult() {
		return devices;
	}

}

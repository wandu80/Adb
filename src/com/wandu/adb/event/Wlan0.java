package com.wandu.adb.event;

import java.util.List;
import com.wandu.adb.bean.Wlan0Info;

/**
 * 获取设备Wlan0信息
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class Wlan0 extends AddressEvent<Wlan0Info> {

	private Wlan0Info result = null;
	
	@Override
	protected String command() {
		checkDevice();
		return String.format("adb%sshell ifconfig wlan0", getSerialnoOption());
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = Wlan0Info.parse(execResult);
		return result != null;
	}

	@Override
	public Wlan0Info getResult() {
		return result;
	}
}

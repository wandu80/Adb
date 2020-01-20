package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AssertUtils;

/**
 * WiFi环境下连接设备
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class Connect extends AddressBooleanResult {

	@Override
	protected String command() {
		checkDevice();
		AssertUtils.assertNotEmpty(serialno, "请设置ip及port！");
		return String.format("adb connect %s", serialno);
	}

	@Override
	protected boolean process(List<String> execResult) {
		for (String str : execResult) {
			if (str != null && str.contains(String.format("connected to %s", serialno))) {
				result =  true;
				break;
			}
		}
		return result;
	}
}

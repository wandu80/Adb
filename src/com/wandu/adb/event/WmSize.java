package com.wandu.adb.event;

import java.util.List;
import com.wandu.adb.bean.WmSizeInfo;

/**
 * 获取设备宽、高信息
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class WmSize extends AddressEvent<WmSizeInfo> {

	private static final String PHYSICAL = "Physical";
	private WmSizeInfo result = null;
	
	@Override
	protected String command() {
		checkDevice();
		return String.format("adb%sshell wm size", getSerialnoOption());
	}

	@Override
	protected boolean process(List<String> execResult) {
		for (String line : execResult) {
			if (line != null && line.contains(PHYSICAL)) {
				result = WmSizeInfo.parse(line);
				if (result != null) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public WmSizeInfo getResult() {
		return result;
	}

}

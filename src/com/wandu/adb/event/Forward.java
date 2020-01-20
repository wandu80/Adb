package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.AssertUtils;

/**
 * 将对特定主机端口上的请求转发到设备上的其他端口
 *
 * @author kejunyao
 * @since 2020年01月17日
 */
public class Forward extends AddressBooleanResult {
	
	/** 开发计算机的端口 */
	private int pcPort;
	public final Forward setPcPort(int port) {
		pcPort = port;
		return this;
	}
	
	/** 被转发设备的端口 */
	private int devicePort;
	public final Forward setDevicePort(int port) {
		devicePort = port;
		return this;
	}
	
	/** 当isLocal=true时，设置主机端口pcPort到local:logd的转发 */
	private boolean isLocal;
	public final Forward setLocal(boolean isLocal) {
		this.isLocal = isLocal;
		return this;
	}
	
	@Override
	protected String command() {
		AssertUtils.assertTrue(pcPort > 0, "开发计算机端口pcPort非法，请设置和法值！");
		if (!isLocal) {
			AssertUtils.assertTrue(devicePort > 0, "被转发设备端口devicePort非法，请设置和法值！");
		}
		checkDevice();
		if (isLocal) {
			return String.format("adb%sforward tcp:%d local:logd", getSerialnoOption(), pcPort);
		}
		return String.format("adb%sforward tcp:%1$d tcp:%2$d", getSerialnoOption(), pcPort, devicePort);
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = execResult.contains(AdbUtils.SUCCESS);
		return result;
	}

}

package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AssertUtils;
/**
 * 监听设备指定端口port的TCP/IP连接
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class TcpIp extends Adb<Boolean> {

	private boolean result = false;
	
	private int port;
	
	public final TcpIp setPort(int port) {
		this.port = port;
		return this;
	}
	
	@Override
	protected String command() {
		AssertUtils.assertTrue(port > 0, "port参数值非法！");
		return String.format("adb tcpip %d", port);
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = execResult.contains(String.format("restarting in TCP mode port: %d", port));
		return result;
	}

	@Override
	public Boolean getResult() {
		return result;
	}
}

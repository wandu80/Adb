package com.wandu.adb.event;

import com.wandu.adb.util.Utility;

/**
 * WiFi、蓝牙连接模式下的ADB命令基类
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public abstract class AddressEvent<R> extends SerialnoEvent<R> {
	protected String host;
	public AddressEvent<R> setHost(String host) {
		this.host = host;
		return this;
	}
	
	protected int port;
	public AddressEvent<R> setPort(int port) {
		this.port = port;
		return this;
	}
	
	/**
	 * 检查设备序列号
	 * 当设备序列号为空时，将”主机host：端口port“作为设备序列号
	 */
	protected final void checkDevice() {
		if (Utility.isEmpty(serialno)) {
			if (host == null) {
				return;
			}
			serialno = String.format("%1$s:%2$d", host, port);
		}
	}
}

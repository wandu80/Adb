package com.wandu.adb.event;

/**
 * WiFi、蓝牙连接模式下的ADB命令基类(带boolean处理结果)
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public abstract class AddressBooleanResult extends AddressEvent<Boolean> {
	/** 命令执行结果 */
	protected boolean result = false;
	
	@Override
	public Boolean getResult() {
		return result;
	}

}

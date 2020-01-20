package com.wandu.adb.event;

import com.wandu.adb.util.Utility;

/**
 * 需要设备序号处理事件基类
 * @author kejunyao
 *
 * @param <R>
 */
public abstract class SerialnoEvent<R> extends Adb<R>{
	/** 设备序号 */
	protected String serialno;
	
	public final SerialnoEvent<R> setSerialno(String serialno) {
		this.serialno = serialno;
		return this;
	}
	
	/**
	 * 生成指定设备的option
	 * @return 指定设备option
	 */
	protected final String getSerialnoOption() {
		if (Utility.isEmpty(serialno)) {
			return " ";
		}
		return String.format(" -s %s ", serialno);
	}
}

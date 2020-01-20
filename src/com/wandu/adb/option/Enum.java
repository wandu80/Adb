package com.wandu.adb.option;

/**
 * 选项基类
 *
 * @author kejunyao
 * @since 2020年01月18日
 */
public class Enum {
	
	private final String option;
	
	protected Enum(String option) {
		this.option = option;
	}
	
	@Override
	public String toString() {
		return option;
	}
	
	public String name() {
		return option;
	}
}

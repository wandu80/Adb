package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AdbUtils;

public class Swipe extends AddressBooleanResult {

	private int startX, endX, startY, endY;

	public final Swipe setStartX(int startX) {
		this.startX = startX;
		return this;
	}

	public final Swipe setEndX(int endX) {
		this.endX = endX;
		return this;
	}

	public final Swipe setStartY(int startY) {
		this.startY = startY;
		return this;
	}

	public final Swipe setEndY(int endY) {
		this.endY = endY;
		return this;
	}

	@Override
	protected String command() {
		checkDevice();
		return String.format("adb%1$sshell input swipe %2$d %3$d %4$d %5$d", getSerialnoOption(), startX, startY, endX, endY);
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = execResult.contains(AdbUtils.SUCCESS);
		return result;
	}

}

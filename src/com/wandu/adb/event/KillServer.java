package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AdbUtils;

/**
 * 终止adb服务器进程
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class KillServer extends Adb<Boolean> {

	private boolean result = false;
	
	@Override
	protected String command() {
		return "adb kill-server";
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = execResult.contains(AdbUtils.SUCCESS);
		return result;
	}

	@Override
	public Boolean getResult() {
		return result;
	}

}

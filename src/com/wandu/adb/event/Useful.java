package com.wandu.adb.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.AssertUtils;
import com.wandu.adb.util.Utility;

/**
 * 适合所有命令
 *
 * @author kejunyao
 * @since 2020年01月18日
 */
public class Useful extends Adb<List<String>> {

	private String command;
	private final List<String> result = new ArrayList<>();
	
	@Override
	protected String command() {
		AssertUtils.assertNotEmpty(command, "命令command为空，请设置！");
		return command;
	}

	@Override
	protected boolean process(List<String> execResult) {
		if (!result.isEmpty()) {
			result.clear();
		}
		if (!Utility.isNullOrEmpty(execResult)) {
			result.addAll(execResult);
		}
		if (result.isEmpty()) {
			result.add(AdbUtils.FAILURE);
		}
		return execResult.contains(AdbUtils.SUCCESS);
	}

	@Override
	public List<String> getResult() {
		return Collections.unmodifiableList(result);
	}

}

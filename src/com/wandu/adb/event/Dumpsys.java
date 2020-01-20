package com.wandu.adb.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.Utility;

/**
 * 关系统服务的信息命令，由于dumpsys比较庞大，请参考以下链接：
 * https://developer.android.com/studio/command-line/dumpsys.html
 *
 * @author kejunyao
 * @since 2020年01月18日
 */
public class Dumpsys extends AddressEvent<List<String>> {

	private static final String COMMAND = "adb%1$sshell dumpsys %2$s";
	
	private final List<String> result = new ArrayList<>();
	
	private String suffix = "";
	/**
	 * 设置adb shell dumpsys命令之后拼接的尾缀
	 * @param suffix 命令拼接尾缀
	 * @return {@link Dumpsys}
	 */
	public Dumpsys setSuffix(String suffix) {
		if (suffix == null) {
			suffix = "";
			return this;
		}
		this.suffix = suffix;
		return this;
	}
	
	@Override
	protected String command() {
		checkDevice();
		return String.format(COMMAND, getSerialnoOption(), suffix);
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

	public boolean contains(String key) {
		for (String line : result) {
			if (line != null && line.contains(key)) {
				return true;
			}
		}
		return false;
	}
}

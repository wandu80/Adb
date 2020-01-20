package com.wandu.adb.event.am;

import java.util.List;

import com.wandu.adb.event.AddressBooleanResult;
import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.AssertUtils;

/**
 * 关闭packageName指定的App
 *
 * @author kejunyao
 * @since 2020年01月18日
 */
public class ForStop extends AddressBooleanResult {

	/** adb shell am force-stop 命令模板 */
	private static final String COMMAND = "adb%1$sshell am force-stop %2$s";
	
	private String packageName;
	/**
	 * 设置要关闭的应用包名
	 * @param packageName 应用包名
	 * @return {@link ForStop}
	 */
	public ForStop setPackageName(String packageName) {
		this.packageName = packageName;
		return this;
	}
	
	@Override
	protected String command() {
		AssertUtils.assertNotEmpty(packageName, "应用包名packageName不能为空，请设置！");
		checkDevice();
		return String.format(COMMAND, getSerialnoOption(), packageName);
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = execResult.contains(AdbUtils.SUCCESS);
		return result;
	}

}

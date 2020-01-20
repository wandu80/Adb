package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.option.KeyCode;
import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.AssertUtils;

public class KeyEvent extends AddressBooleanResult {

	/** adb shell input keyevent命令模板 */
	private static final String COMMAND = "adb%1$sshell input keyevent %2$d";
	
	private int keyCode;
	/**
	 * 设置按键code
	 * @param keyCode {@link KeyCode}
	 * @return {@link KeyEvent}
	 */
	public KeyEvent setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		return this;
	}
	
	@Override
	protected String command() {
		AssertUtils.assertTrue(keyCode >= KeyCode.KEYCODE_UNKNOWN && keyCode <= KeyCode.MAX_CODE, String.format("按键code值%d不合法，请设置合法值！", keyCode));
		checkDevice();
		return String.format(COMMAND, getSerialnoOption(), keyCode);
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = execResult.contains(AdbUtils.SUCCESS);
		return result;
	}

}

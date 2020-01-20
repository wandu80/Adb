package com.wandu.adb.event.am;

import java.util.List;
import com.wandu.adb.event.AddressBooleanResult;
import com.wandu.adb.option.GlobalOption;
import com.wandu.adb.option.StartOption;
import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.AssertUtils;

/**
 * 启动intent指定的Activity
 *
 * @author kejunyao
 * @since 2020年01月18日
 */
public class Start extends AddressBooleanResult {
	/** adb shell am start命令模板 */
	private static final String COMMAND = "adb%1$sshell am start%2$s%3$s%4$s%5$s";
	
	private GlobalOption globalOption;
	public Start setGlobalOption(GlobalOption globalOption) {
		this.globalOption = globalOption;
		return this;
	}
	
	private StartOption option;
	public Start setOption(StartOption option) {
		this.option = option;
		return this;
	}
	
	private String intent;
	public Start setIntent(String intent) {
		this.intent = intent;
		return this;
	}
	
	private String param;
	public Start setParam(String param) {
		this.param = param;
		return this;
	}
	
	@Override
	protected String command() {
		AssertUtils.assertNotEmpty(intent, "intent不能为空，请设置！");
		checkDevice();
		return String.format(
				COMMAND, 
				getSerialnoOption(),
				globalOption == null ? " " : globalOption.name(),
				intent, 
				option == null ? "" : option.name(), 
				param == null ? "" : param
		);
	}

	@Override
	protected boolean process(List<String> execResult) {
		if (execResult.contains(AdbUtils.FAILURE)) {
			result = false;
			return false;
		}
		if (execResult.contains("Complete")) {
			result = true;
			return true;
		}
		for (String line : execResult) {
			if (line != null && line.contains("Error:")) {
				result = false;
				return false;
			}
		}
		result = true;
		return result;
	}

}
/**
adb shell start [options] intent	启动intent指定的Activity。
请参阅 intent 参数的规范。

具体选项包括：

-D：启用调试。
-W：等待启动完成。
--start-profiler file：启动分析器并将结果发送到 file。
-P file：类似于 --start-profiler，但当应用进入空闲状态时分析停止。
-R count：重复启动 Activity count 次。在每次重复前，将完成顶层 Activity。
-S：启动 Activity 前强行停止目标应用。
--opengl-trace：启用对 OpenGL 函数的跟踪。
--user user_id | current：指定要作为哪个用户运行；如果未指定，则作为当前用户运行。 
 */
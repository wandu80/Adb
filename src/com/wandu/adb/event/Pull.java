package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AssertUtils;
import com.wandu.adb.util.FileUtils;
import com.wandu.adb.util.Utility;

/**
 * 从设备中复制某个文件或目录（及其子目录)到开发计算机上
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class Pull extends AddressBooleanResult {

	/** 复制成功返回的字符串 */
	private static final String RESULT_SUCCESS = "file pulled.";
	
	/** 开发计算机（本地）上的目标文件/目录的路径 */
	private String local;
	public final Pull setLocal(String local) {
		this.local = local;
		return this;
	}
	
	/** 设备（远程）上的目标文件/目录的路径 */
	private String remote;
	public final Pull setRemote(String remote) {
		this.remote = remote;
		return this;
	}
	
	@Override
	protected String command() {
		AssertUtils.assertNotEmpty(remote, "被拷贝的文件地址remote，不能为空！");
		checkDevice();
		if (Utility.isEmpty(local)) {
			local = FileUtils.PROJECT_OUTPUT_DIRECTORY;
		}
		return String.format("adb%spull %2$s %3$s", getSerialnoOption(), remote, local);
	}

	@Override
	protected boolean process(List<String> execResult) {
		for (String line : execResult) {
			if (line != null && line.contains(RESULT_SUCCESS)) {
				result = true;
				break;
			}
		}
		return result;
	}

}

package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AssertUtils;

/**
 * 将开发计算机上某个文件或目录（及其子目录）复制到某个设备
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class Push extends AddressBooleanResult {

	/** 复制成功返回的字符串 */
	private static final String RESULT_SUCCESS = "file pushed.";
	
	/** 开发计算机（本地）上的目标文件/目录的路径 */
	private String local;
	public final Push setLocal(String local) {
		this.local = local;
		return this;
	}
	
	/** 设备（远程）上的目标文件/目录的路径 */
	private String remote;
	public final Push setRemote(String remote) {
		this.remote = remote;
		return this;
	}
	
	@Override
	protected String command() {
		AssertUtils.assertNotEmpty(local, "开发计算机（本地）上的目标文件/目录的路径local，不能为空！");
		AssertUtils.assertNotEmpty(remote, "设备（远程）上的目标文件/目录的路径remote，不能为空！");
		checkDevice();
		return String.format("adb%spush %2$s %3$s", getSerialnoOption(), local, remote);
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

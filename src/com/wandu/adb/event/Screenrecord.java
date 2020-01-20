package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.bean.Video;
import com.wandu.adb.util.AssertUtils;
import com.wandu.adb.util.FileUtils;
import com.wandu.adb.util.Utility;

/**
 * 录制设备（搭载Android 4.4（API 级别 19）及更高版本）显示屏
 * 使用格式：adb shell screenrecord [options] filename
 * 
 * @author kejunyao
 * @since 2020年01月15日
 */
public class Screenrecord extends AddressEvent<Video> {
	
	private static final int MAX_RATE = 200000000;
	private static final int MIN_RATE = 100000;
	
	private Video result;
	
	private String size = "";
	/**
	 * 设置视频大小
	 * 默认值是设备的本机显示分辨率（如果支持）；如果不支持，则使用 1280x720。
	 * 为获得最佳效果，请使用设备的Advanced Video Coding (AVC)编码器支持的大小。
	 * @param width 视频宽度
	 * @param height 视频高度
	 * @return {@link Screenrecord}
	 */
	public Screenrecord size(int width, int height) {
		AssertUtils.assertTrue(width > 0, "视频宽度width非法，请设置合法值！");
		AssertUtils.assertTrue(height > 0, "视频高度height非法，请设置合法值！");
		size = String.format(" --size %1$dx%2$d", width, height);
		return this;
	}
	
	private String rate = "";
	/**
	 * 设置视频的视频比特率（以 MB/秒为单位）
	 * 默认值为 4Mbps。您可以增加比特率以提升视频品质，但这么做会导致视频文件变大。
	 * @param bitRate 比特率
	 * @return {@link Screenrecord}
	 */
	public Screenrecord rate(int bitRate) {
		AssertUtils.assertTrue(bitRate >= MIN_RATE && bitRate <= MAX_RATE, "视频的视频比特率rate非法，请设置[100000,200000000]合法值！");
		this.rate = String.format(" --bit-rate %d", bitRate);
		return this;
	}
	
	private String time  = "";
	/**
	 * 设置最大录制时长（以秒为单位）
	 * 默认值和最大值均为 180（3 分钟）。
	 * @param time 最大录制时长
	 * @return {@link Screenrecord}
	 */
	public Screenrecord setTime(int time) {
		AssertUtils.assertTrue(time > 0, "最大录制时长time非法，请设置合法值！");
		this.time = String.format(" --time-limit %d", time);
		return this;
	}
	
	private String rotate = "";
	/**
	 * 将输出旋转 90 度。
	 * 此功能处于实验阶段。
	 * @return {@link Screenrecord}
	 */
	public Screenrecord rotate() {
		rotate = " --rotate";
		return this;
	}
	
	private String filepath;
	public Screenrecord setFilePath(String filepath) {
		this.filepath = filepath;
		return this;
	}
	
	@Override
	protected String command() {
		// AdbUtils.assertNotEmpty(filepath, "录制视频的路径不能为空，请设置有效的路径！");
		if (Utility.isEmpty(filepath)) {
			filepath = FileUtils.createDefalutVideoRemotePath();
		}
		checkDevice();
		return String.format(
				"adb%1$sshell screenrecord --verbose%2$s%3$s%4$s%5$s %6$s", 
				getSerialnoOption(), 
				size, 
				rate, 
				time, 
				rotate, 
				filepath);
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = Video.parse(execResult);
		if (result != null) {
			result.setFilepath(filepath);
			return true;
		}
		return false;
	}

	@Override
	public Video getResult() {
		return result;
	}

}

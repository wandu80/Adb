package com.wandu.adb.bean;

import java.util.List;

import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.Utility;

public class Video {
	private int width;
	private int height;
	private String filepath;
	private float fps;
	private int orientation;
	private String codeFormat;
	private float bps;
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getFps() {
		return fps;
	}

	public int getOrientation() {
		return orientation;
	}

	public String getCodeFormat() {
		return codeFormat;
	}

	public float getBps() {
		return bps;
	}
	
	public String getFilepath() {
		return filepath;
	}
	
	
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "{width=" + width + ", height=" + height + ", filepath=" + filepath + ", fps=" + fps
				+ ", orientation=" + orientation + ", codeFormat=" + codeFormat + ", bps=" + bps + "}";
	}

	private static final String KEY_MAIN_DISPLAY = "Main display";
	private static final String KEY_CONFIGURING_RECORDER = "Configuring recorder";

	public static Video parse(List<String> execResult) {
		if (execResult.size() == 1 && !execResult.contains(AdbUtils.SUCCESS)) {
			return null;
		}
		if (!execResult.contains("Broadcast completed: result=0")) {
			return null;
		}
		Video video = new Video();
		for (String line : execResult) {
			if (line == null) {
				continue;
			}
			if (line.startsWith(KEY_MAIN_DISPLAY)) {
				parseFirstLine(video, line);
				continue;
			}
			if (line.startsWith(KEY_CONFIGURING_RECORDER)) {
				parseSecordLine(video, line);
				continue;
			}
		}
		return video;
	}
	
	private static void parseFirstLine(Video video, String line) {
		String[] array = line.trim().split(AdbUtils.PATTERN_BLANK);
		for (String str : array) {
			if (str == null) {
				continue;
			}
			str = str.trim();
			if (str.contains("x")) {
				String[] disp = str.trim().split("x");
				if (disp != null && disp.length >= 2) {
					video.width = Utility.parseInt(disp[0], 0);
					video.height = Utility.parseInt(disp[1], 0);
				}
			} else if (str.indexOf('@') >= 0) {
				String f = str.substring(1, str.length() - 3);
				video.fps = Utility.parseFloat(f, 0f);
			} else if (str.contains("orientation")) {
				String o = str.substring(str.length() - 2, str.length() - 1);
				video.orientation = Utility.parseInt(o, 0);
			}
		}
	}
	
	private static void parseSecordLine(Video video, String line) {
		String[] array = line.trim().split(AdbUtils.PATTERN_BLANK);
		for (String str : array) {
			if (str == null) {
				continue;
			}
			if (str.indexOf('/') >= 0) {
				video.codeFormat = str;
			} else if (str.endsWith("Mbps")) {
				String m = str.substring(0, str.length() - 4);
				video.bps = Utility.parseFloat(m, 0f);
			}
		}
	}
}
/**
	Main display is 1080x2340 @60.00fps (orientation=0)
	Configuring recorder for 1080x2340 video/avc at 20.00Mbps
	Content area is 1080x2340 at offset x=0 y=0
	Time limit reached
	Encoder stopping; recorded 7 frames in 5 seconds
	Stopping encoder and muxer
	Executing: /system/bin/am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///sdcard/demo.mp4
	Broadcasting: Intent { act=android.intent.action.MEDIA_SCANNER_SCAN_FILE dat=file:///sdcard/demo.mp4 flg=0x400000 }
	Broadcast completed: result=0
	MacBook-Pro:open kejunyao$ adb pull /sdcard/demo.mp4 /Users/kejunyao/Desktop
	/sdcard/demo.mp4: 1 file pulled. 21.4 MB/s (227015 bytes in 0.010s)
	MacBook-Pro:open kejunyao$ adb  shell screenrecord --verbose   --time-limit 5  /sdcard/demo.mp4
	Main display is 1080x2340 @60.00fps (orientation=0)
	Configuring recorder for 1080x2340 video/avc at 20.00Mbps
	Content area is 1080x2340 at offset x=0 y=0
	Time limit reached
	Encoder stopping; recorded 3 frames in 5 seconds
	Stopping encoder and muxer
	Executing: /system/bin/am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///sdcard/demo.mp4
	Broadcasting: Intent { act=android.intent.action.MEDIA_SCANNER_SCAN_FILE dat=file:///sdcard/demo.mp4 flg=0x400000 }
	Broadcast completed: result=0
**/

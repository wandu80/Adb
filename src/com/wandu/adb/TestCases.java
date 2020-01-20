package com.wandu.adb;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.wandu.adb.bean.DeviceInfo;
import com.wandu.adb.bean.Video;
import com.wandu.adb.bean.Wlan0Info;
import com.wandu.adb.event.Adb;
import com.wandu.adb.event.Connect;
import com.wandu.adb.event.Devices;
import com.wandu.adb.event.Dumpsys;
import com.wandu.adb.event.Install;
import com.wandu.adb.event.KeyEvent;
import com.wandu.adb.event.KillServer;
import com.wandu.adb.event.Pull;
import com.wandu.adb.event.Screencap;
import com.wandu.adb.event.Screenrecord;
import com.wandu.adb.event.Swipe;
import com.wandu.adb.event.TcpIp;
import com.wandu.adb.event.Wlan0;
import com.wandu.adb.event.WmSize;
import com.wandu.adb.event.am.ForStop;
import com.wandu.adb.event.am.Start;
import com.wandu.adb.option.GlobalOption;
import com.wandu.adb.option.KeyCode;
import com.wandu.adb.option.StartOption;
import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.AssertUtils;
import com.wandu.adb.util.FileUtils;
import com.wandu.adb.util.Log;
import com.wandu.adb.util.SystemUtils;

class TestCases {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void testDevices() {
		Devices devices = Adb.get(Devices.class);
		devices.setL(true);
		boolean success = devices.exec();
		if (success) {
			Log.d(devices.getResult());
		}
	}
	
	@Test
	void testIpAddress() {
		Wlan0 wlan = Adb.get(Wlan0.class);
		if (wlan.exec()) {
			Log.d(wlan.getResult());
		}
	}
	
	@Test
	void testSwipe() {
		Swipe swipe = Adb.get(Swipe.class);
		swipe.setEndX(0);
		swipe.setStartX(0);
		swipe.setStartY(900);
		swipe.setEndY(300);
		Log.d(swipe.exec());
	}
	
	@Test
	void testTcpId() {
		TcpIp tcpIp = Adb.get(TcpIp.class);
		tcpIp.setPort(5555);
		Log.d(tcpIp.exec());
	}
	
	@Test
	void testConnect() {
		Connect connect = Adb.get(Connect.class);
		connect.setHost("172.16.132.185").setPort(5555);
		Log.d(connect.exec());
	}
	
	@Test
	void testKillServer() {
		Log.d(Adb.get(KillServer.class).exec());
	}
	
	@Test
	void screenCap() {
		String device = "4ef22a5d";
		String path = FileUtils.createDefalutImageRemotePath();
		Screencap cap = Adb.get(Screencap.class);
		cap.setSerialno(device);
		cap.setFilepath(path);
		Log.d(cap.exec());
		Pull pull = Adb.get(Pull.class);
		pull.setSerialno(device);
		pull.setRemote(path);
		// pull.setLocal("/Users/kejunyao/Desktop/");
		Log.d(pull.exec());
	}
	
	@Test
	void testWmSize() {
		WmSize wmSize = Adb.get(WmSize.class);
		wmSize.setSerialno("4ef22a5d");
		wmSize.exec();
		Log.d(wmSize.getResult());
	}
	
	@Test
	void testInstall() {
		Install install = Adb.get(Install.class);
		install.setSerialno("4ef22a5d");
		install.setOption(Install.Option.OPTION_T);
		install.setPath("/Users/kejunyao/kk/kkmh-android/Kuaikan/build/outputs/apk/debug/Kuaikan_debug_5.61.0.apk");
		Log.d(install.exec());
	}
	
	@Test
	void testVideo() {
		int time = 30;
		Screenrecord record = Adb.get(Screenrecord.class);
		// record.setSerialno("4ef22a5d");
		String path = FileUtils.createDefalutVideoRemotePath();
		record.setFilePath(path);
		record.setTime(time);
		record.size(540, 1170);
		record.rate(100000);
		record.exec();
		Video video = record.getResult();
		Log.d(video);
		
		Pull pull = Adb.get(Pull.class);
		// pull.setSerialno("4ef22a5d");
		pull.setRemote(path);
		pull.setLocal(FileUtils.PROJECT_OUTPUT_DIRECTORY);
		pull.exec();
		Log.d(pull.getResult());
	}
	
	@Test
	void testAutoConnect() {
		Adb.get(KillServer.class).exec(2000);
		TcpIp tcp = Adb.get(TcpIp.class);
		boolean success = tcp.setPort(5555).exec(1500);
		AssertUtils.assertTrue(success, "tcp失败！");
		Log.d("tcp成功");
		Devices devices = Adb.get(Devices.class);
		success = devices.exec(1000);
		AssertUtils.assertTrue(success, "设备获取失败！");
		List<DeviceInfo> info = devices.getResult();
		Log.d(info);
		DeviceInfo device = null;
		for (DeviceInfo d : info) {
			if (d != null && d.isOnline()) {
				device = d;
				break;
			}
		}
		AssertUtils.assertTrue(device != null, "设备获取失败2！");
		Wlan0 wlan = Adb.get(Wlan0.class);
		wlan.setSerialno(device.getSerialno());
		success = wlan.exec(1000);
		AssertUtils.assertTrue(success, "设备Wlan0获取失败！");
		Wlan0Info wlanInfo = wlan.getResult();
		AssertUtils.assertTrue(wlanInfo != null, "设备Wlan0获取失败2！");
		Log.d(wlanInfo);
		AdbUtils.sleepSafely(5000);
		Log.e("请拔掉USB！");
		Connect connect = Adb.get(Connect.class);
		connect.setHost(wlanInfo.getInetAddr());
		connect.setPort(5555);
		success = connect.exec(1000);
		AssertUtils.assertTrue(success, "设备连接失败！");
		Log.d("设备连接成功");
	}
	
	@Test
	void testConfig() {
		boolean isTrue = ConfigManager.getInstance().isTrue("adb_inited");
		Log.d("adb_inited: ", isTrue);
	}
	
	@Test
	void testStartActivity() {
		Start start = Start.get(Start.class);
		boolean success = start.setGlobalOption(GlobalOption.A)
		.setOption(StartOption.W)
		.setIntent("com.kuaishou.nebula/com.yxcorp.gifshow.HomeActivity")
		.exec();
		if (success) {
			Log.d("应用启动成功！");
		}
	}
	
	@Test
	void testKeyEvent() {
		KeyEvent event = KeyEvent.get(KeyEvent.class);
		boolean success = event.setKeyCode(KeyCode.KEYCODE_POWER)
		.exec();
		if (success) {
			Log.d("屏幕点亮成功！");
		}
	}
	
	@Test
	void testForStop() {
		ForStop stop = ForStop.get(ForStop.class);
		boolean success = stop.setPackageName("com.kuaishou.nebula").exec();
		if (success) {
			Log.d("应用关闭成功！");
		}
	}
	
	@Test
	void flushKuaiShow() {
		
		// testAutoConnect();
		
		Dumpsys dumpsys = Dumpsys.get(Dumpsys.class);
		// dumpsys.setHost(host);
		// dumpsys.setPort(port);
		dumpsys.setSuffix(
				String.format(
						"window policy | %s -E \"mScreenOnFully|mShowingLockscreen\"", 
						(SystemUtils.isMacOS || SystemUtils.isLinuxOS) ? "grep" : "findstr"
				)
		);
		boolean success = dumpsys.exec(300);
		AssertUtils.assertTrue(success, "获取屏幕操作信息失败");
		success = dumpsys.contains("mScreenOnFully=true");
		if (!success) {
			Log.d("屏幕是暗，尝试点亮......");
			KeyEvent event = KeyEvent.get(KeyEvent.class);
			success = event.setKeyCode(KeyCode.KEYCODE_POWER)
			.exec(300);
			AssertUtils.assertTrue(success, "屏幕点亮失败！");
			Log.d("屏幕点亮成功！");
		}
		success = dumpsys.contains("mShowingLockscreen=true");
		if (success) {
			Log.d("屏幕被锁, 尝试屏幕解锁......");
			Swipe swipe = Swipe.get(Swipe.class);
			success = swipe.setStartX(0).setEndX(0).setStartY(900).setEndY(300).exec(300);
			AssertUtils.assertTrue(success, "屏幕解锁失败！");
			Log.d("屏幕解锁成功！");
		}
		
		Start start = Start.get(Start.class);
		// start.setHost(host);
		// start.setPort(port);
		success = start.setGlobalOption(GlobalOption.N)
		.setOption(StartOption.W)
		// .setParam(" ")
		.setIntent("com.kuaishou.nebula/com.yxcorp.gifshow.HomeActivity")
		.exec(2000);
		AssertUtils.assertTrue(success, "step3, 快手极速版启动失败！");
		Log.d("step3, 快手极速版启动成功, 进入疯狂刷屏模式！");
		SwipeWithLoop loop = new SwipeWithLoop();
		loop.swipeLoop();
	}
	
	@Test
	void testScreenCanOperation() {
		Dumpsys dumpsys = Dumpsys.get(Dumpsys.class);
		dumpsys.setSuffix(
				String.format(
						"window policy | %s -E \"mScreenOnFully|mShowingLockscreen\"", 
						(SystemUtils.isMacOS || SystemUtils.isLinuxOS) ? "grep" : "findstr"
				)
		);
		AssertUtils.assertTrue(dumpsys.exec(), "获取屏幕操作信息失败");
		Log.d(dumpsys.contains("mScreenOnFully=true") ? "当前设备屏幕是亮的" : "当前设备屏幕是暗的");
		Log.d(dumpsys.contains("mShowingLockscreen=true") ? "当前设备屏幕被锁屏" : "当前设备屏已解锁");
	}
	
	/**
	 * 捕捉摄像头图像
	 */
	@Test
	void testCatchCamera() {
		
//		Wlan0 wlan = Adb.get(Wlan0.class);
//		// wlan.setSerialno(device.getSerialno());
//		boolean success = wlan.exec(1000);
		
		
		
		Start start = Start.get(Start.class);
		start.setGlobalOption(GlobalOption.A);
		// .setHost(wlan.getResult().getInetAddr())
		// .setPort(5555);
		// start.setHost("172.20.10.12");
		// start.setPort(5555);
		start.setOption(StartOption.W).setIntent("android.media.action.STILL_IMAGE_CAMERA");
		boolean success = start.exec(1000);
		AssertUtils.assertTrue(success, "摄像头打开失败!");
		Log.d("开始尝试抓取摄像头视频......");
		Screenrecord record = Adb.get(Screenrecord.class);
		String path = FileUtils.createDefalutVideoRemotePath();
		record.setFilePath(path);
		record.rate(1000000);
		record.setTime(30);
		success = record.exec();
		
		KeyEvent keyEvent = KeyEvent.get(KeyEvent.class);
		keyEvent.setKeyCode(KeyCode.KEYCODE_BACK).exec();
		
		AssertUtils.assertTrue(success, "摄像头图像抓取失败！");
		Video video = record.getResult();
		Log.d(video);
		Pull pull = Adb.get(Pull.class);
		pull.setRemote(path);
		pull.setLocal(FileUtils.PROJECT_OUTPUT_DIRECTORY);
		success = pull.exec();
		AssertUtils.assertTrue(success, "拷贝设备上的视频文件失败！å");
		Log.d(pull.getResult());
	}
}

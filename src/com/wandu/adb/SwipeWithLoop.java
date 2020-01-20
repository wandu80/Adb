package com.wandu.adb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

import com.wandu.adb.bean.DeviceInfo;
import com.wandu.adb.event.Adb;
import com.wandu.adb.event.Devices;
import com.wandu.adb.event.Swipe;
import com.wandu.adb.util.Utility;

public class SwipeWithLoop {
	
	private static final int MAX_TIME = 6000;
	private static final int MIN_TIME = 5000;
	
	private static final List<String> DEVICE_FILTER = new ArrayList<String>();
	
	static {
		DEVICE_FILTER.add("4ef22a5d");
		// DEVICE_FILTER.add("64de4185");
	}
	
	public static void main(String[] args) {
		new SwipeWithLoop().swipeLoop();
	}
	
	void swipeLoop() {
		Executors.newFixedThreadPool(1).execute(new Runnable() {
			boolean isSwitch = true;
			Random random = new Random();
			Devices device = Adb.get(Devices.class);
			Swipe swipe = Adb.get(Swipe.class);
			
			private boolean swipeUp(String device) {
				swipe.setSerialno(device);
				return swipe.setStartX(0).setEndX(0).setStartY(900).setEndY(300).exec();
			}
			
			private boolean swipeDown(String device) {
				swipe.setSerialno(device);
				return swipe.setStartX(0).setEndX(0).setStartY(300).setEndY(900).exec();
			}
			
			@Override
			public void run() {
				while (true) {
					final int time = random.nextInt(MAX_TIME) % (MAX_TIME - MIN_TIME + 1) + MIN_TIME;
					device.exec();
					List<DeviceInfo> devices = device.getResult();
					for (DeviceInfo device : devices) {
						if (device == null || DEVICE_FILTER.contains(device.getSerialno())) {
							continue;
						}
						boolean reuslt = isSwitch ? swipeUp(device.getSerialno()) : swipeUp(device.getSerialno());
						System.out.println(Utility.contact("device: ", device, ", isSwitch: ", isSwitch, ", reuslt: ", reuslt + ", time: " + time));
					}
					isSwitch = !isSwitch;
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
}

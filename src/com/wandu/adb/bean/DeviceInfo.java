package com.wandu.adb.bean;

import java.util.HashMap;
import java.util.Map;

import com.wandu.adb.util.AdbUtils;

public class DeviceInfo {
	
	public enum Status {
		ONLINE, // 设备在线
		OFFLINE // 设备离线
	}
	
	private static final String DEVICE = "device";
	private static final String LIST_OF = "List of";
	private static final String USB = "usb";
	private static final String PRODUCT = "product";
	private static final String MODEL = "model";
	private static final String TRANSPORT_ID = "transport_id";
	
	private String serialno;
	private String usb;
	private String product;
	private String model;
	private String device;
	private String transportId;
	private Status status = Status.ONLINE;
	
	public String getSerialno() {
		return serialno;
	}
	public String getUsb() {
		return usb;
	}
	public String getProduct() {
		return product;
	}
	public String getModel() {
		return model;
	}
	public String getDevice() {
		return device;
	}
	public String getTransportId() {
		return transportId;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public boolean isOnline() {
		return Status.ONLINE.equals(status);
	}
	
	@Override
	public String toString() {
		return "\n{serialno=" + serialno + ", usb=" + usb + ", product=" + product + ", model=" + model + ", device="
				+ device + ", transportId=" + transportId + "}\n";
	}
	public static DeviceInfo parse(String line) {
		if (line == null) {
			return null;
		}
		if (line.contains(LIST_OF)) {
			return null;
		}
		final String offline = Status.OFFLINE.name().toLowerCase();
		if (line.endsWith(offline)) {
			DeviceInfo device = new DeviceInfo();
			device.serialno = line.replace(offline, "").trim();
			device.status = Status.OFFLINE;
			return device;
		}
		if (line.endsWith(DEVICE)) {
			DeviceInfo device = new DeviceInfo();
			String[] array = line.trim().split(AdbUtils.PATTERN_BLANK);
			String name = array[0];
			device.serialno = name == null ? null : name.trim();
			return device;
		}
		
		if (line.contains(DEVICE) && line.contains(MODEL)) {
			String[] array = line.trim().split(AdbUtils.PATTERN_BLANK);
			DeviceInfo device = new DeviceInfo();
			Map<String, String> map = new HashMap<>();
			for (String s : array) {
				if (s == null) {
					continue;
				}
				if (s.contains(AdbUtils.PATTERN_COLON)) {
					String[] pair = s.trim().split(AdbUtils.PATTERN_COLON);
					if (pair.length > 1) {
						map.put(pair[0], pair[1]);
					}
				} else {
					if (line.startsWith(s)) {
						device.serialno = s.trim();
					}
				}
			}
			device.usb = map.get(USB);
			device.model = map.get(MODEL);
			device.product = map.get(PRODUCT);
			device.transportId = map.get(TRANSPORT_ID);
			device.device = map.get(DEVICE);
			return device;
		}
		return null;
	}
}

/** 
 * MacBook-Pro:open XXXX$ adb devices -l
 * List of devices attached
 * 3ef32f7d               device usb:326542886X product:lavender model:Redmi_Note_7 device:lavender transport_id:4
 * 172.16.132.185:5555    device product:cancro_wc_lte model:MI_4LTE device:cancro transport_id:3
 *  
 * MacBook-Pro:open XXXX$ adb devices
 * List of devices attached
 * 3ef32f7d	device
 * 172.16.132.185:5555	device
 *  */

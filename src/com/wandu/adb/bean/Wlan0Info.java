package com.wandu.adb.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.Utility;

public class Wlan0Info {
	private static final String ENCAP = "Link encap";
	private static final String INET_ADDR = "inet addr";
	private static final String BCAST = "Bcast";
	private static final String MASK = "Mask";
	private static final String INET6_ADDR = "inet6 addr";
	private static final String SCOPE = "Scope";
	private static final String MTU = "MTU";
	private static final String METRIC = "Metric";
	private static final String RX_PACKETS = "RX packets";
	private static final String ERRORS = "errors";
	private static final String DROPPED = "dropped";
	private static final String OVERRUNS = "overruns";
	private static final String CARRIER = "carrier";
	private static final String COLLISIONS = "collisions";
	private static final String TXQUEUELEN = "txqueuelen";
	private static final String RX_BYTES = "RX bytes";
	private static final String TX_BYTES = "TX bytes";
	private static final List<String> IGNORE_PARAM = new ArrayList<>();
	static {
		IGNORE_PARAM.add(ERRORS);
		IGNORE_PARAM.add(DROPPED);
		IGNORE_PARAM.add(OVERRUNS);
	}
	// private static final String FRAME = "frame";
	
	private String encap;
	private String inetAddr;
	private String bcast;
	private String mask;
	private String inet6Addr;
	private String scope;
	private String mtu;
	private String metric;
	private String rxPackets;
	private String carrier;
	private String collisions;
	private String txqueuelen;
	private String rxBytes;
	private String txBytes;
	public String getEncap() {
		return encap;
	}
	public String getInetAddr() {
		return inetAddr;
	}
	public String getBcast() {
		return bcast;
	}
	public String getMask() {
		return mask;
	}
	public String getInet6Addr() {
		return inet6Addr;
	}
	public String getScope() {
		return scope;
	}
	public String getMtu() {
		return mtu;
	}
	public String getMetric() {
		return metric;
	}
	public String getRxPackets() {
		return rxPackets;
	}
	public String getCarrier() {
		return carrier;
	}
	public String getCollisions() {
		return collisions;
	}
	public String getTxqueuelen() {
		return txqueuelen;
	}
	public String getRxBytes() {
		return rxBytes;
	}
	public String getTxBytes() {
		return txBytes;
	}
	
	
	
	@Override
	public String toString() {
		return "{encap=" + encap + ", inetAddr=" + inetAddr + ", bcast=" + bcast + ", mask=" + mask
				+ ", inet6Addr=" + inet6Addr + ", scope=" + scope + ", mtu=" + mtu + ", metric=" + metric
				+ ", rxPackets=" + rxPackets + ", carrier=" + carrier + ", collisions=" + collisions + ", txqueuelen="
				+ txqueuelen + ", rxBytes=" + rxBytes + ", txBytes=" + txBytes + "}";
	}
	
	public static Wlan0Info parse(List<String> execResult) {
		Map<String, String> map = new HashMap<>();
		for (String line : execResult) {
			if (line == null) {
				continue;
			}
			String[] array = line.trim().split(AdbUtils.PATTERN_BLANK);
			if (line.contains(INET6_ADDR)) {
				if (array.length >= 5) {
					map.put(INET6_ADDR, array[2]);
					map.put(SCOPE, array[4]);
				}
				continue;
			}
			if (line.contains(RX_PACKETS)) {
				match(map, array, RX_PACKETS, 1);
				continue;
			}
			if (line.contains(RX_BYTES)) {
				match(map, array, RX_BYTES, 1);
				match(map, array, TX_BYTES, 3);
				continue;
			}
			for (String s : array) {
				if (s == null) {
					continue;
				}
				String[] pair = s.trim().split(AdbUtils.PATTERN_COLON);
				if (pair != null && pair.length > 1) {
					String key = pair[0];
					if (Utility.isEmpty(key)) {
						continue;
					}
					if (IGNORE_PARAM.contains(key)) {
						continue;
					}
					if (line.contains(ENCAP) && ENCAP.contains(key)) {
						map.put(ENCAP, pair[1]);
					} else if (line.contains(INET_ADDR) && INET_ADDR.contains(key)) {
						map.put(INET_ADDR, pair[1]);
					} else {
						map.put(pair[0], pair[1]);
					}
				}
			}
		}
		if (map.isEmpty()) {
			return null;
		}
		Wlan0Info wlan = new Wlan0Info();
		wlan.bcast = map.get(BCAST);
		wlan.carrier = map.get(CARRIER);
		wlan.collisions = map.get(COLLISIONS);
		wlan.encap = map.get(ENCAP);
		wlan.inet6Addr = map.get(INET6_ADDR);
		wlan.inetAddr = map.get(INET_ADDR);
		wlan.mask = map.get(MASK);
		wlan.metric = map.get(METRIC);
		wlan.mtu = map.get(MTU);
		wlan.rxBytes = map.get(RX_BYTES);
		wlan.rxPackets = map.get(RX_PACKETS);
		wlan.scope = map.get(SCOPE);
		wlan.txBytes = map.get(TX_BYTES);
		wlan.txqueuelen = map.get(TXQUEUELEN);
		return wlan;
	}
	
	private static void match(Map<String, String> map, String[] array, String key, int index) {
		if (array.length >= 2) {
			String pair = array[index];
			if (pair != null) {
				array = pair.split(AdbUtils.PATTERN_COLON);
				if (array != null && array.length >= 2) {
					map.put(key, array[1]);
				}
			}
		}
	}
}

/** 
 *	MacBook-Pro:open kejunyao$ adb shell ifconfig wlan0
    wlan0     Link encap:UNSPEC    Driver icnss
          inet addr:172.16.132.32  Bcast:172.16.135.255  Mask:255.255.248.0 
          inet6 addr: fe80::da32:e3ff:fe0b:371/64 Scope: Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:13816355 errors:0 dropped:0 overruns:0 frame:0 
          TX packets:10137692 errors:0 dropped:110 overruns:0 carrier:0 
          collisions:0 txqueuelen:3000 
          RX bytes:16569707603 TX bytes:1152685273
 */
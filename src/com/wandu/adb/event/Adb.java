package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.AdbManager;
import com.wandu.adb.util.AdbUtils;
import com.wandu.adb.util.FileUtils;
import com.wandu.adb.util.Log;

/**
 * ADB命令基类
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public abstract class Adb<R> {
	
	public Adb() {
	}

	public final boolean exec() {
		AdbManager.getInstance().init();
		String cmd = String.format("%1$s%2$s", FileUtils.ADB_TOOLS_PATH, command());
		Log.d("command: ", cmd);
		List<String> execResult = AdbUtils.exec(cmd);
		Log.e("result: ", execResult);
		return process(execResult);
	}
	
	/**
	 * 执行完后延迟delayMills毫秒
	 * @param delayMills 延迟时间（单位：毫秒）
	 * @return true，执行成功；false，执行失败
	 */
	public final boolean exec(long delayMills) {
		boolean success = exec();
		AdbUtils.sleepSafely(delayMills);
		return success;
	}
	
	protected abstract String command();
	
	protected abstract boolean process(List<String> execResult);
	
	public abstract R getResult();
	
	@SuppressWarnings({ "unchecked", "finally" })
	public static final <T extends Adb<?>> T get(Class<? extends Adb<?>> clazz) {
		Adb<?> adb = null;
		try {
			adb = (Adb<?>) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return adb == null ? null : (T) adb;
		}
	}
}

package com.wandu.adb.option;

/**
 * 全局选项
 *
 * @author kejunyao
 * @since 2020年01月18日
 */
public final class GlobalOption extends Enum {

	private GlobalOption(String option) {
		super(option);
	}
	
	/** 在所有网络接口上监听，而非只在 localhost 上监听。 */
	public static final GlobalOption A = new GlobalOption(" -a ");
	/** 将 adb 命令发送至唯一连接的 USB 设备。如果连接了多个 USB 设备，则返回错误。 */
	public static final GlobalOption D = new GlobalOption(" -d");
	/** 将 adb 命令发送至唯一运行的模拟器。如果有多个模拟器在运行，则返回错误。 */
	public static final GlobalOption E = new GlobalOption(" -e ");
	/** adb 服务器主机的名称。默认值为 localhost */
	public static final GlobalOption H = h("localhost");
	/** adb服务器端口号。默认值为 5037 */
	public static final GlobalOption P = p(5307);
	/** 在提供的 adb 服务器套接字上监听。默认值为 tcp:localhost:5037。 */
	public static final GlobalOption L = l("localhost", 5037);
	/** 指定当前用户运行 */
	public static final GlobalOption CURRENT = user("current");
	public static final GlobalOption N = new GlobalOption(" -n ");
	
	/**
	 * 将 adb 命令发送至以其 adb 分配的序列号命名的特定设备（如“emulator-5556”）。替换存储在 $ANDROID_SERIAL 环境变量中的序列号值。
	 * @param serialno 设备序列号
	 * @return {@link GlobalOption}
	 */
	public static GlobalOption s(String serialno) {
		return new GlobalOption(String.format(" -s %s ", serialno));
	}
	/**
	 * adb服务器主机的名称
	 * @param server 服务器主机
	 * @return {@link GlobalOption}
	 */
	public static GlobalOption h(String server) {
		return new GlobalOption(String.format(" -H %s ", server));
	}
	
	/**
	 * adb服务器端口号
	 * @param port 端口
	 * @return {@link GlobalOption}
	 */
	public static GlobalOption p(int port) {
		return new GlobalOption(String.format(" -P %d ", port));
	}
	
	/**
	 * 在提供的 adb 服务器套接字上监听
	 * @param host 主机地址
	 * @param port 主机端口
	 * @return {@link GlobalOption}
	 */
	public static GlobalOption l(String host, int port) {
		return new GlobalOption(String.format(" -L tcp:%1$s:%2$d ", host, port));
	}
	
	/**
	 * 指定要作为哪个用户运行；如果未指定，则作为当前用户运行
	 * @param userId 用户ID
	 * @return {@link GlobalOption}
	 */
	public static GlobalOption user(String userId) {
		return new GlobalOption(optionUser(userId));
	}
	
	/**
	 *  生成--user选项
	 * @param userId 用户ID
	 * @return --user选项
	 */
	public static String optionUser(String userId) {
		return String.format(" --user %s ", userId);
	}
}
/**
 全局选项			说明
	-a			在所有网络接口上监听，而非只在 localhost 上监听。
	-d			将 adb 命令发送至唯一连接的 USB 设备。如果连接了多个 USB 设备，则返回错误。
	-e			将 adb 命令发送至唯一运行的模拟器。如果有多个模拟器在运行，则返回错误。
	-s 			serial_number	将 adb 命令发送至以其 adb 分配的序列号命名的特定设备（如“emulator-5556”）。替换存储在 $ANDROID_SERIAL 环境变量中的序列号值。请参阅将命令发送至特定设备。
	-H 			server	adb 服务器主机的名称。默认值为 localhost。
	-P 			port	adb 服务器端口号。默认值为 5037。
	-L 			socket	在提供的 adb 服务器套接字上监听。默认值为 tcp:localhost:5037。
*/
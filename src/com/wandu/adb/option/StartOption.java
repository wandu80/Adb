package com.wandu.adb.option;

/**
 * am start命令选项
 *
 * @author kejunyao
 * @since 2020年01月18日
 */
public class StartOption extends Enum {
	
	private StartOption(String option) {
		super(option);
	}
	
	/** 启用调试 */
	public static final StartOption D = new StartOption(" -D ");
	/** 等待启动完成 */
	public static final StartOption W = new StartOption(" -W ");
	/** 启用对 OpenGL 函数的跟踪 */
	public static final StartOption OPENGL_TRACE = new StartOption(" --opengl-trace ");
	/** 指定当前用户运行 */
	public static final StartOption CURRENT = user("current");
	
	/**
	 * 启动分析器并将结果发送到file
	 * @param file 要分析的文件
	 * @return {@link StartOption}
	 */
	public static StartOption startProfiler(String file) {
		return new StartOption(String.format(" --start-profiler %s ", file));
	}
	
	/**
	 * 类似于--start-profiler，但当应用进入空闲状态时分析停止。
	 * @param file 要分析的文件
	 * @return {@link StartOption}
	 */
	public static StartOption p(String file) {
		return new StartOption(String.format(" -P %s ", file));
	}
	
	/**
	 * 重复启动Activity count次。在每次重复前，将完成顶层Activity。
	 * @param count 重复启动Activity次数
	 * @return {@link StartOption}
	 */
	public static StartOption r(int count) {
		return new StartOption(String.format(" -R %d ", count));
	}
	
	/**
	 * 指定要作为哪个用户运行；如果未指定，则作为当前用户运行
	 * @param userId 用户ID
	 * @return {@link StartOption}
	 */
	public static StartOption user(String userId) {
		return new StartOption(GlobalOption.optionUser(userId));
	}
}

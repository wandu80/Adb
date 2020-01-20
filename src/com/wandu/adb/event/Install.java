package com.wandu.adb.event;

import java.util.List;

import com.wandu.adb.util.AssertUtils;

/**
 * 安装APK
 *
 * @author kejunyao
 * @since 2020年01月15日
 */
public class Install extends AddressBooleanResult {
	/** 安装成功返回的字符串 */
	private static final String RESULT_SUCCESS = "Success";
	
	/** 安装具有转发锁定功能的软件包 */
	private static final String L = "-l";
	/** 重新安装现有应用，保留其数据 */
	private static final String R = "-r";
	/** 允许安装测试APK。如果您只运行或调试了应用，
	 * 或者使用了Android Studio的Build > Build APK命令，
	 * Gradle便会生成一个测试APK。
	 * 如果使用开发者预览版SDK（如果 targetSdkVersion是字母，而非数字）编译APK，
	 * 则安装测试APK时必须在install命令中包含 -t 选项
	 *  */
	private static final String T = "-t";
	/** installer_package_name：指定安装程序软件包名称 */
	private static final String I = "-i";
	/** 在共享的大容量存储设备（如 sdcard）上安装软件包 */
	private static final String S = "-s";
	/** 在内部系统内存上安装软件包 */
	private static final String F = "-f";
	/** 允许版本代码降级 */
	private static final String D = "-d";
	/** 授予应用清单中列出的所有权限 */
	private static final String G = "-g";
	
	public enum Option {
		OPTION_L(L),
		OPTION_R(R),
		OPTION_T(T),
		OPTION_I(I),
		OPTION_S(S),
		OPTION_F(F),
		OPTION_D(D),
		OPTION_G(G);
		
		private final String option;
		Option(String option) {
			this.option = option;
		}
	}
	
	private boolean result = false;
	
	/** 具体选项 */
	private Option option;
	public Install setOption(Option option) {
		this.option = option;
		return this;
	}
	
	/** 将软件包（通过path指定）安装到系统 */
	private String path;
	public Install setPath(String path) {
		this.path = path;
		return this;
	}
	
	@Override
	protected String command() {
		AssertUtils.assertNotEmpty(path, "请输入软件包路径path！");
		checkDevice();
		return String.format(
				"adb%1$sinstall%2$s %3$s",
				getSerialnoOption(),
				option == null ? " " : option.option, 
				path
			);
	}

	@Override
	protected boolean process(List<String> execResult) {
		result = execResult.contains(RESULT_SUCCESS);
		return result;
	}

}
/**
 * 命令执行成功：
 * Performing Streamed Install
 * Success
 * */

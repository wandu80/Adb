package com.wandu.adb.option;

/**
 * 键盘按键值
 *
 * @author kejunyao
 * @since 2020年01月18日
 */
public interface KeyCode {
	int KEYCODE_UNKNOWN = 0;
	// int KEYCODE_MENU = 1;
	int KEYCODE_SOFT_RIGHT = 2;
	/** 按键Home */
	int KEYCODE_HOME = 3;
	int KEYCODE_BACK = 4;
	/** 拨号键 */
	int KEYCODE_CALL = 5;
	/** 挂机键 */
	int KEYCODE_ENDCALL = 6;
	/** 按键'0' */
	int KEYCODE_0 = 7;
	/** 按键'1' */
	int KEYCODE_1 = 8;
	/** 按键'2' */
	int KEYCODE_2 = 9;
	/** 按键'3' */
	int KEYCODE_3 = 10;
	/** 按键'4' */
	int KEYCODE_4 = 11;
	/** 按键'5' */
	int KEYCODE_5 = 12;
	/** 按键'6' */
	int KEYCODE_6 = 13;
	/** 按键'7' */
	int KEYCODE_7 = 14;
	/** 按键'8' */
	int KEYCODE_8 = 15;
	/** 按键'9' */
	int KEYCODE_9 = 16;
	/** 按键'*' */
	int KEYCODE_STAR = 17;
	/** 按键'#' */
	int KEYCODE_POUND = 18;
	/** 导航键 向上 */
	int KEYCODE_DPAD_UP = 19;
	/** 导航键 向下 */
	int KEYCODE_DPAD_DOWN = 20;
	/** 导航键 向左 */
	int KEYCODE_DPAD_LEFT = 21;
	/** 导航键 向右 */
	int KEYCODE_DPAD_RIGHT = 22;
	/** 导航键 确定键 */
	int KEYCODE_DPAD_CENTER = 23;
	/** 音量增加键 */
	int KEYCODE_VOLUME_UP = 24;
	/** 音量减小键 */
	int KEYCODE_VOLUME_DOWN = 25;
	/** 电源键 */
	int KEYCODE_POWER = 26;
	/** 拍照键 */
	int KEYCODE_CAMERA = 27;
	/** 按键Clear */
	int KEYCODE_CLEAR = 28;
	/** 按键'A' */
	int KEYCODE_A = 29;
	/** 按键'B' */
	int KEYCODE_B = 30;
	/** 按键'C' */
	int KEYCODE_C = 31;
	/** 按键'D' */
	int KEYCODE_D = 32;
	/** 按键'E' */
	int KEYCODE_E = 33;
	/** 按键'F' */
	int KEYCODE_F = 34;
	/** 按键'G' */
	int KEYCODE_G = 35;
	/** 按键'H' */
	int KEYCODE_H = 36;
	/** 按键'I' */
	int KEYCODE_I = 37;
	/** 按键'J' */
	int KEYCODE_J = 38;
	/** 按键'K' */
	int KEYCODE_K = 39;
	/** 按键'L' */
	int KEYCODE_L = 40;
	/** 按键'M' */
	int KEYCODE_M = 41;
	/** 按键'N' */
	int KEYCODE_N = 42;
	/** 按键'O' */
	int KEYCODE_O = 43;
	/** 按键'P' */
	int KEYCODE_P = 44;
	/** 按键'Q' */
	int KEYCODE_Q = 45;
	/** 按键'R' */
	int KEYCODE_R = 46;
	/** 按键'S' */
	int KEYCODE_S = 47;
	/** 按键'T' */
	int KEYCODE_T = 48;
	/** 按键'U' */
	int KEYCODE_U = 49;
	/** 按键'V' */
	int KEYCODE_V = 50;
	/** 按键'W' */
	int KEYCODE_W = 51;
	/** 按键'X' */
	int KEYCODE_X = 52;
	/** 按键'Y' */
	int KEYCODE_Y = 53;
	/** 按键'Z' */
	int KEYCODE_Z = 54;
	/** 按键',' */
	int KEYCODE_COMMA = 55;
	/** 按键'.' */
	int KEYCODE_PERIOD = 56;
	/** Alt+Left */
	int KEYCODE_ALT_LEFT = 57;
	/** Alt+Right */
	int KEYCODE_ALT_RIGHT = 58;
	/** Shift+Left */
	int KEYCODE_SHIFT_LEFT = 59;
	/** Shift+Right */
	int KEYCODE_SHIFT_RIGHT = 60;
	/** Tab键 */
	int KEYCODE_TAB = 61;
	/** 空格键 */
	int KEYCODE_SPACE = 62;
	/** 按键Symbol modifier */
	int KEYCODE_SYM = 63;
	/** 按键Explorer special function */
	int KEYCODE_EXPLORER = 64;
	/** 按键Envelope special function */
	int KEYCODE_ENVELOPE = 65;
	/** 回车键 */
	int KEYCODE_ENTER = 66;
	/** 退格键 */
	int KEYCODE_DEL = 67;
	/** 按键'`' */
	int KEYCODE_GRAVE = 68;
	/** 按键'-' */
	int KEYCODE_MINUS = 69;
	/** 按键'=' */
	int KEYCODE_EQUALS = 70;
	/** 按键'[' */
	int KEYCODE_LEFT_BRACKET = 71;
	/** 按键']' */
	int KEYCODE_RIGHT_BRACKET = 72;
	/** 按键'\' */
	int KEYCODE_BACKSLASH = 73;
	/** 按键';' */
	int KEYCODE_SEMICOLON = 74;
	/** 按键''' (单引号) */
	int KEYCODE_APOSTROPHE = 75;
	/** 按键'/' */
	int KEYCODE_SLASH = 76;
	/** 按键'@' */
	int KEYCODE_AT = 77;
	/** 按键Number modifier */
	int KEYCODE_NUM = 78;
	/** 按键Headset Hook */
	int KEYCODE_HEADSETHOOK = 79;
	/** 拍照对焦键 */
	int KEYCODE_FOCUS = 80;
	/** 按键'+' */
	int KEYCODE_PLUS = 81;
	/** 菜单键 */
	int KEYCODE_MENU = 82;
	/** 通知键 */
	int KEYCODE_NOTIFICATION = 83;
	/** 搜索键 */
	int KEYCODE_SEARCH = 84;
	/** 按键'Z' */
	int TAG_LAST_KEYCODE = 85;
	/** 向上翻页键 */
	int KEYCODE_PAGE_UP = 92;
	/** 向下翻页键 */
	int KEYCODE_PAGE_DOWN = 93;
	/** ESC键 */
	int KEYCODE_ESCAPE = 111;
	/** 删除键 */
	int KEYCODE_FORWARD_DEL = 112;
	/** 滚动锁定键 */
	int KEYCODE_SCROLL_LOCK = 116;
	/** 大写锁定键 */
	int KEYCODE_CAPS_LOCK = 115;
	/** Break/Pause键 */
	int KEYCODE_BREAK = 121;
	/** 光标移动到开始键 */
	int KEYCODE_MOVE_HOME = 122;
	/** 光标移动到末尾键 */
	int KEYCODE_MOVE_END = 123;
	/** 插入键 */
	int KEYCODE_INSERT = 124;
	/** 小键盘锁 */
	int KEYCODE_NUM_LOCK = 143;
	/** 放大键 */
	int KEYCODE_ZOOM_IN = 168;
	/** 缩小键 */
	int KEYCODE_ZOOM_OUT = 169;
	
	int MAX_CODE = 170;
}

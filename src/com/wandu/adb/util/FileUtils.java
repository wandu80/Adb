package com.wandu.adb.util;

import java.io.Closeable;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件处理工具类
 *
 * @author kejunyao
 * @since 2020年01月17日
 */
public final class FileUtils {
	
	/** 本程序在计算机中的目录 */
	private static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
	/** 本程序所输出的文件的目录 */
	public static final String PROJECT_OUTPUT_DIRECTORY = String.format("%s/output/", PROJECT_DIRECTORY);
	/** 本程序使用到的Android ADB工具目录 */
	public static final String ADB_TOOLS_PATH = String.format("%s/adb-tools/", PROJECT_DIRECTORY);
	/** 本程序配置文件信息目录 */
	public static final String CONFIG_FILE_PATH = String.format("%s/config/config.properties", PROJECT_DIRECTORY);
	
	private FileUtils() {
	}
	
	public static void closeSafely(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
			}
		}
	}

    /**
     * 获取指定文件夹下所有的文件夹及文件
     * @param dir 指定文件夹
     * @return 指定文件夹下所有的文件夹及文件
     */
    public static List<File> findAllFile(File dir) {
        if (dir == null || !dir.exists()) {
            return null;
        }
        LinkedList<File> tmpDirectory = new LinkedList<>();
        LinkedList<File> allFile = new LinkedList<>();
        tmpDirectory.add(dir);
        File tmp;
        while (!tmpDirectory.isEmpty()) {
            tmp = tmpDirectory.removeFirst();
            if (tmp == null) {
                continue;
            }
            allFile.add(tmp);
            if (tmp.isDirectory()) {
                File[] files = tmp.listFiles();
                if (files == null || files.length == 0) {
                    continue;
                }
                for (File file : files) {
                    allFile.add(file);
                    if (file.isDirectory()) {
                        tmpDirectory.add(file);
                    }
                }
            }
        }
        return allFile;
    }
    
    public static String createDefalutImageRemotePath() {
    	return String.format("/sdcard/image_%d.png", ConfigUtils.fileSerialno());
    }
    
    public static String createDefalutVideoRemotePath() {
    	return String.format("/sdcard/video_%d.mp4", ConfigUtils.fileSerialno());
    }
}

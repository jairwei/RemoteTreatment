package com.example.remotetreatment.util;

import java.io.File;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.GlobalApplication;

public class FileUtil {
	// static File mSDCard = Environment.getExternalStorageDirectory();

	private static File CACHE_DIR = GlobalApplication.mApp.getCacheDir();

	// private static File SDCARD_DIR = Environment.getExternalStorageDirectory();

	public static void initFilePath() {
		File serialDir = new File(CACHE_DIR.getAbsolutePath() + Base.PATH_SERIAL);
		if (!serialDir.exists()) {
			serialDir.mkdirs();
		}
	}

	public static String getSerialPath() {
		File dir = new File(CACHE_DIR.getAbsolutePath() + Base.PATH_SERIAL);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir.getAbsolutePath() + File.separator;
	}
}

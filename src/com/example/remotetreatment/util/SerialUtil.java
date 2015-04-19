package com.example.remotetreatment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialUtil {

	public static void writeObject(Object data, String fileName) {
		ObjectOutputStream oos = null;
		try {
			String path = FileUtil.getSerialPath();
			File file = new File(path + fileName);
			if (file.exists()) {
				file.delete();
				// Util.log(TAG, "file exists.");
			}
			if (data == null) {
				return;
			}

			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Object readObject(String fileName) {
		ObjectInputStream ois = null;
		try {
			String path = FileUtil.getSerialPath();
			File file = new File(path + fileName);
			if (file.exists() == false) {
				// Util.log(TAG, "file not exists.");
				return null;
			}
			ois = new ObjectInputStream(new FileInputStream(file));
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}

package com.example.remotetreatment.util;

import android.widget.Toast;

import com.example.remotetreatment.GlobalApplication;

public class Toaster {
	public static void show(String str) {
		try {
			Toast.makeText(GlobalApplication.mApp, str, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void show(int resId) {
		String str = GlobalApplication.mApp.getString(resId);
		show(str);
	}
}

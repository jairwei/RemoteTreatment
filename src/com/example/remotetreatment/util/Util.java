package com.example.remotetreatment.util;

import android.util.DisplayMetrics;
import android.util.Log;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.GlobalApplication;

public class Util {
	public static DisplayMetrics getDisplayMetrics() {
		DisplayMetrics dm = new DisplayMetrics();
		try {
			dm = GlobalApplication.mApp.getResources().getDisplayMetrics();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dm;
	}

	public static float dip2px(float dipValue) {
		final float scale = getDisplayMetrics().density;
		return (dipValue * scale + 0.5f);
	}

	public static int dip2pxInt(float dipValue) {
		return (int) dip2px(dipValue);
	}

	public static float px2dip(float pxValue) {
		final float scale = getDisplayMetrics().density;
		return (pxValue / scale + 0.5f);
	}

	public static void log(String tag, String s) {
		Log.i(tag, s);
	}

	public static void log(Object obj) {
		if (obj == null) {
			return;
		}
		log(Base.TAG, obj.toString());
	}
}

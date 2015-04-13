package com.example.remotetreatment.util;

import com.example.remotetreatment.Base;

import android.util.Log;

public class Util {
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

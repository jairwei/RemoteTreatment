package com.example.remotetreatment.util;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import com.example.remotetreatment.task.CommonTask;

public class TaskUtil {

	// @SafeVarargs
	// @SuppressLint("NewApi")
	// public static <P, T extends AsyncTask<P, ?, ?>> void execute1(T task, P... params) {
	// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	// task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
	// } else {
	// task.execute(params);
	// }
	// }

	@SafeVarargs
	@SuppressLint("NewApi")
	public static <P, T extends CommonTask> void execute(T task, P... params) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
		} else {
			task.execute(params);
		}
	}
}
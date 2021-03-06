package com.example.remotetreatment.task;

import com.example.remotetreatment.util.Util;

public abstract class OnTaskExecuteListener {
	public String TAG = "OnTaskExecuteListener";

	public void onPreExecute() {
		Util.log(TAG, "onPreExecute");
	}

	public void onCancelled() {
		Util.log(TAG, "onCancelled");
	}

	public void onSucceed(Object result) {
		Util.log(TAG, "onSucceed");
	}

	public void onFailed(Throwable thr) {
		Util.log(TAG, "onFailed");
	}

	public void onFailed(Throwable thr, Object result) {
		Util.log(TAG, "onFailed");
	}
}

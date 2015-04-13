package com.example.remotetreatment.task;

import android.os.AsyncTask;

public abstract class CommonTask extends AsyncTask<Object, Object, Boolean> {

	public boolean isCancelled = false;
	private OnTaskExecuteListener mTaskListener;
	protected Object object;
	protected Throwable excption;
	private Object params;

	// private Handler succeedHandler;

	public CommonTask(OnTaskExecuteListener listener) {
		this.mTaskListener = listener;
	}

	@Override
	protected void onPreExecute() {
		if (mTaskListener != null) {
			mTaskListener.onPreExecute();
		}
	}

	@Override
	protected Boolean doInBackground(Object... params) {

		return false;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if (result) {
			if (mTaskListener != null) {
				mTaskListener.onSucceed(object);
			}
			// if (succeedHandler != null) {
			// succeedHandler.sendEmptyMessage(0);
			// }
		} else {
			if (mTaskListener != null && object != null) {
				mTaskListener.onFailed(excption, object);
			} else if (mTaskListener != null) {
				mTaskListener.onFailed(excption);
			}
		}
	}

	@Override
	protected void onCancelled() {
		isCancelled = true;
		try {
			this.cancel(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (mTaskListener != null) {
			mTaskListener.onCancelled();
		}
	}

	public void cancel() {
		onCancelled();
	}

	public OnTaskExecuteListener getTaskListener() {
		return mTaskListener;
	}

	public void setTaskListener(OnTaskExecuteListener mTaskListener) {
		this.mTaskListener = mTaskListener;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	// public Handler getSucceedHandler() {
	// return succeedHandler;
	// }
	//
	// public void setSucceedHandler(Handler succeedHandler) {
	// this.succeedHandler = succeedHandler;
	// }
}

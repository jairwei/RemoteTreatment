package com.example.remotetreatment.task;

import android.content.Context;

import com.example.remotetreatment.rpc.RPCHelper;

public class GetPrescsTask extends CommonTask {

	private Context mContext;

	private RPCHelper mRPCHelper;

	public GetPrescsTask(Context context, OnTaskExecuteListener listener) {
		super(listener);
		this.mContext = context;
	}

	public GetPrescsTask(OnTaskExecuteListener listener) {
		super(listener);
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		if (mRPCHelper == null) {
			mRPCHelper = RPCHelper.getInstance(mContext);
		}
		try {
			int page = Integer.parseInt(params[0].toString());
			object = mRPCHelper.getPrescs(page);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			excption = e;
		}
		return false;
	}
}

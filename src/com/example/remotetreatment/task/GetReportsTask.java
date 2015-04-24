package com.example.remotetreatment.task;

import android.content.Context;

import com.example.remotetreatment.rpc.RPCHelper;

public class GetReportsTask extends CommonTask {

	private Context mContext;

	private RPCHelper mRPCHelper;

	public GetReportsTask(Context context, OnTaskExecuteListener listener) {
		super(listener);
		this.mContext = context;
	}

	public GetReportsTask(OnTaskExecuteListener listener) {
		super(listener);
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		if (mRPCHelper == null) {
			mRPCHelper = RPCHelper.getInstance(mContext);
		}
		try {
			int page = Integer.parseInt(params[0].toString());
			object = mRPCHelper.getReports(page);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			excption = e;
		}
		return false;
	}
}

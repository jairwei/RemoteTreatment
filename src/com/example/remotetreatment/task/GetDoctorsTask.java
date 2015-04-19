package com.example.remotetreatment.task;

import android.content.Context;

import com.example.remotetreatment.rpc.RPCHelper;

public class GetDoctorsTask extends CommonTask {

	private Context mContext;

	private RPCHelper mRPCHelper;

	public GetDoctorsTask(Context context, OnTaskExecuteListener listener) {
		super(listener);
		this.mContext = context;
	}

	public GetDoctorsTask(OnTaskExecuteListener listener) {
		super(listener);
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		if (mRPCHelper == null) {
			mRPCHelper = RPCHelper.getInstance(mContext);
		}
		try {
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			int page = Integer.parseInt(params[0].toString());
			object = mRPCHelper.getDoctors(page);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			excption = e;
		}
		return false;
	}
}

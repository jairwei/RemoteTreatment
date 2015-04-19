package com.example.remotetreatment.task;

import android.content.Context;

import com.example.remotetreatment.rpc.RPCHelper;

public class GetReservesTask extends CommonTask {

	private Context mContext;

	private RPCHelper mRPCHelper;

	public GetReservesTask(Context context, OnTaskExecuteListener listener) {
		super(listener);
		this.mContext = context;
	}

	public GetReservesTask(OnTaskExecuteListener listener) {
		super(listener);
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		if (mRPCHelper == null) {
			mRPCHelper = RPCHelper.getInstance(mContext);
		}
		try {
			// try {
			// Thread.sleep(2000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			int status = Integer.parseInt(params[0].toString());
			int page = Integer.parseInt(params[1].toString());
			object = mRPCHelper.getReserves(status, page);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			excption = e;
		}
		return false;
	}
}

package com.example.remotetreatment.task;

import java.util.ArrayList;

import android.content.Context;

import com.example.remotetreatment.model.Test;
import com.example.remotetreatment.rpc.RPCHelper;

public class GetTestsTask extends CommonTask {

	private Context mContext;

	private RPCHelper mRPCHelper;

	public GetTestsTask(Context context, OnTaskExecuteListener listener) {
		super(listener);
		this.mContext = context;
	}

	public GetTestsTask(OnTaskExecuteListener listener) {
		super(listener);
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		if (mRPCHelper == null) {
			mRPCHelper = RPCHelper.getInstance(mContext);
		}
		try {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int page = Integer.parseInt(params[0].toString());

			ArrayList<Test> list = new ArrayList<Test>();
			list.add(Test.fromTest(page * 10 + 0));
			list.add(Test.fromTest(page * 10 + 1));
			list.add(Test.fromTest(page * 10 + 2));
			list.add(Test.fromTest(page * 10 + 3));
			list.add(Test.fromTest(page * 10 + 4));
			list.add(Test.fromTest(page * 10 + 5));
			list.add(Test.fromTest(page * 10 + 6));
			list.add(Test.fromTest(page * 10 + 7));
			list.add(Test.fromTest(page * 10 + 8));
			list.add(Test.fromTest(page * 10 + 9));

			object = list;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			excption = e;
		}
		return false;
	}
}

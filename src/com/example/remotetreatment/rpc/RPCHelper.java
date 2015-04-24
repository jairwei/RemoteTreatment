package com.example.remotetreatment.rpc;

import java.util.ArrayList;

import android.content.Context;

import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.model.Presc;
import com.example.remotetreatment.model.Report;
import com.example.remotetreatment.model.Reserve;

public class RPCHelper extends RPCBase {

	private static RPCHelper mRPCHelper;

	private RPCHelper(Context context) {
		mContext = context;
	}

	public static RPCHelper getInstance(Context context) {
		if (mRPCHelper == null) {
			mRPCHelper = new RPCHelper(context.getApplicationContext());
		}
		return mRPCHelper;
	}

	public ArrayList<Report> getReports(int page) {
		ArrayList<Report> list = new ArrayList<Report>();
		list.add(Report.fromTest(page * 10 + 0));
		list.add(Report.fromTest(page * 10 + 1));
		list.add(Report.fromTest(page * 10 + 2));
		list.add(Report.fromTest(page * 10 + 3));
		list.add(Report.fromTest(page * 10 + 4));
		list.add(Report.fromTest(page * 10 + 5));
		list.add(Report.fromTest(page * 10 + 6));
		list.add(Report.fromTest(page * 10 + 7));
		list.add(Report.fromTest(page * 10 + 8));
		list.add(Report.fromTest(page * 10 + 9));
		return list;
	}

	public ArrayList<Doctor> getDoctors(int page) {
		ArrayList<Doctor> list = new ArrayList<Doctor>();
		list.add(Doctor.fromTest(page * 10 + 0));
		list.add(Doctor.fromTest(page * 10 + 1));
		list.add(Doctor.fromTest(page * 10 + 2));
		list.add(Doctor.fromTest(page * 10 + 3));
		list.add(Doctor.fromTest(page * 10 + 4));
		list.add(Doctor.fromTest(page * 10 + 5));
		list.add(Doctor.fromTest(page * 10 + 6));
		list.add(Doctor.fromTest(page * 10 + 7));
		list.add(Doctor.fromTest(page * 10 + 8));
		list.add(Doctor.fromTest(page * 10 + 9));
		return list;
	}

	public ArrayList<Reserve> getReserves(int status, int page) {
		ArrayList<Reserve> list = new ArrayList<Reserve>();
		list.add(Reserve.fromTest(status, page * 10 + 0));
		list.add(Reserve.fromTest(status, page * 10 + 1));
		list.add(Reserve.fromTest(status, page * 10 + 2));
		list.add(Reserve.fromTest(status, page * 10 + 3));
		list.add(Reserve.fromTest(status, page * 10 + 4));
		list.add(Reserve.fromTest(status, page * 10 + 5));
		list.add(Reserve.fromTest(status, page * 10 + 6));
		list.add(Reserve.fromTest(status, page * 10 + 7));
		list.add(Reserve.fromTest(status, page * 10 + 8));
		list.add(Reserve.fromTest(status, page * 10 + 9));
		return list;
	}

	public ArrayList<Presc> getPrescs(int page) {
		ArrayList<Presc> list = new ArrayList<Presc>();
		list.add(Presc.fromTest(page * 10 + 0));
		list.add(Presc.fromTest(page * 10 + 1));
		list.add(Presc.fromTest(page * 10 + 2));
		list.add(Presc.fromTest(page * 10 + 3));
		list.add(Presc.fromTest(page * 10 + 4));
		list.add(Presc.fromTest(page * 10 + 5));
		list.add(Presc.fromTest(page * 10 + 6));
		list.add(Presc.fromTest(page * 10 + 7));
		list.add(Presc.fromTest(page * 10 + 8));
		list.add(Presc.fromTest(page * 10 + 9));
		return list;
	}

	// /** 获取推送消息详情 */
	// public String getPushMsgDetail(JuanReqData data) throws ConnectTimeoutException, RpcException, ApiException, UnsupportedEncodingException {
	// JuanHttp http = new JuanHttp();
	// http.setAction(Base.URL_ACTION_GET_PUSH_MSG);
	// http.setData(data);
	// String reqJson = JsonUtil.toJsonString(http);
	// StringEntity entity = new StringEntity(reqJson, "utf-8");
	// return execute(Base.URL_PATH_GET_PUSH_MSG, entity);
	// }
}

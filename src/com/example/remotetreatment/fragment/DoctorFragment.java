package com.example.remotetreatment.fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.task.GetDoctorsTask;
import com.example.remotetreatment.task.OnTaskExecuteListener;
import com.example.remotetreatment.util.TaskUtil;
import com.example.remotetreatment.view.listview.PullToRefreshListView;
import com.example.remotetreatment.view.listview.PullToRefreshListView.IDataController;
import com.example.remotetreatment.view.listview.adapter.DoctorAdapter;

@SuppressLint("ValidFragment")
public class DoctorFragment extends BaseFragment {

	final static String TAG = DoctorFragment.class.getSimpleName();
	private View mRoot;
	private PullToRefreshListView mRefreshLayout;

	// private ViewGroup mLayoutContent;

	public static DoctorFragment newInstance() {
		return newInstance(null);
	}

	public static DoctorFragment newInstance(Bundle args) {
		DoctorFragment f = new DoctorFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mRoot = mAct.getLayoutInflater().inflate(R.layout.fragment_doctor, null);

		initFragment();
		// registerDoctorReceiver();
		return mRoot;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		// unregisterDoctorReceiver();
	}

	@Override
	public void initFragment() {
		if (inited) {
			return;
		}
		super.initFragment();

		initView();
	}

	private void initView() {
		mRefreshLayout = (PullToRefreshListView) mRoot.findViewById(R.id.refresh_layout);
		mRefreshLayout.useDefaultFooterView();
		mRefreshLayout.setAdapter(new DoctorAdapter(mAct));

		mRefreshLayout.setDataController(new IDataController() {
			@Override
			public void onLoadmore(int page) {
				GetDoctorsTask task = new GetDoctorsTask(mAct, new OnTaskExecuteListener() {
					@Override
					public void onSucceed(Object result) {
						super.onSucceed(result);
						ArrayList<Doctor> list = (ArrayList<Doctor>) result;
						mRefreshLayout.onLoadMoreFinish(list);
					}

					@Override
					public void onFailed(Throwable thr) {
						super.onFailed(thr);
					}
				});
				TaskUtil.execute(task, page);
			}

			@Override
			public void onRefresh() {
				GetDoctorsTask task = new GetDoctorsTask(mAct, new OnTaskExecuteListener() {
					@Override
					public void onSucceed(Object result) {
						super.onSucceed(result);
						ArrayList<Doctor> list = (ArrayList<Doctor>) result;
						mRefreshLayout.onRefreshFinish(list);
					}

					@Override
					public void onFailed(Throwable thr) {
						super.onFailed(thr);
					}
				});
				TaskUtil.execute(task, 0);
			}
		});
		mRefreshLayout.setRefreshing(true);
		mRefreshLayout.refresh();
	}
}
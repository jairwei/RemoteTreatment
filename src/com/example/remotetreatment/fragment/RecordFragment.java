package com.example.remotetreatment.fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Presc;
import com.example.remotetreatment.model.Report;
import com.example.remotetreatment.task.GetPrescsTask;
import com.example.remotetreatment.task.GetReportsTask;
import com.example.remotetreatment.task.OnTaskExecuteListener;
import com.example.remotetreatment.util.TaskUtil;
import com.example.remotetreatment.view.listview.PullToRefreshListView;
import com.example.remotetreatment.view.listview.PullToRefreshListView.IDataController;
import com.example.remotetreatment.view.listview.adapter.PrescAdapter;
import com.example.remotetreatment.view.listview.adapter.ReportAdapter;

@SuppressLint("ValidFragment")
public class RecordFragment extends BaseFragment {

	final static String TAG = RecordFragment.class.getSimpleName();
	private View mRoot;
	private RadioGroup mGroup;
	private PullToRefreshListView mRefreshLayout;
	private PrescAdapter mPrescAdapter;
	private ReportAdapter mReportAdapter;

	private IDataController mPrescControl;
	private IDataController mReportControl;

	public static RecordFragment newInstance() {
		return newInstance(null);
	}

	public static RecordFragment newInstance(Bundle args) {
		RecordFragment f = new RecordFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mRoot = mAct.getLayoutInflater().inflate(R.layout.fragment_record, null);

		initFragment();
		return mRoot;
	}

	@Override
	public void initFragment() {
		if (inited) {
			return;
		}
		super.initFragment();

		initControl();
		initView();
	}

	private void initView() {

		mGroup = (RadioGroup) mRoot.findViewById(R.id.group);
		mRefreshLayout = (PullToRefreshListView) mRoot.findViewById(R.id.refresh_layout);

		mPrescAdapter = new PrescAdapter(mAct);
		mReportAdapter = new ReportAdapter(mAct);

		mGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup groupd, int checkedId) {
				if (checkedId == R.id.butn_presc) {
					mRefreshLayout.setAdapter(mPrescAdapter);
					mRefreshLayout.setDataController(mPrescControl);
				} else if (checkedId == R.id.butn_report) {
					mRefreshLayout.setAdapter(mReportAdapter);
					mRefreshLayout.setDataController(mReportControl);
				}
				refresh();
			}
		});

		mRefreshLayout.useDefaultFooterView();
		mRefreshLayout.setAdapter(mPrescAdapter);
		mRefreshLayout.setDataController(mPrescControl);

		refresh();
	}

	private void refresh() {
		mRefreshLayout.setRefreshing(true);
		mRefreshLayout.refresh();
	}

	private void initControl() {
		mPrescControl = new IDataController() {
			@Override
			public void onLoadmore(int page) {
				GetPrescsTask task = new GetPrescsTask(mAct, new OnTaskExecuteListener() {
					@Override
					public void onSucceed(Object result) {
						super.onSucceed(result);
						ArrayList<Presc> list = (ArrayList<Presc>) result;
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
				GetPrescsTask task = new GetPrescsTask(mAct, new OnTaskExecuteListener() {
					@Override
					public void onSucceed(Object result) {
						super.onSucceed(result);
						ArrayList<Presc> list = (ArrayList<Presc>) result;
						mRefreshLayout.onRefreshFinish(list);
					}

					@Override
					public void onFailed(Throwable thr) {
						super.onFailed(thr);
					}
				});
				TaskUtil.execute(task, 0);
			}
		};

		mReportControl = new IDataController() {
			@Override
			public void onLoadmore(int page) {
				GetReportsTask task = new GetReportsTask(mAct, new OnTaskExecuteListener() {
					@Override
					public void onSucceed(Object result) {
						super.onSucceed(result);
						ArrayList<Report> list = (ArrayList<Report>) result;
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
				GetReportsTask task = new GetReportsTask(mAct, new OnTaskExecuteListener() {
					@Override
					public void onSucceed(Object result) {
						super.onSucceed(result);
						ArrayList<Report> list = (ArrayList<Report>) result;
						mRefreshLayout.onRefreshFinish(list);
					}

					@Override
					public void onFailed(Throwable thr) {
						super.onFailed(thr);
					}
				});
				TaskUtil.execute(task, 0);
			}
		};
	}
}
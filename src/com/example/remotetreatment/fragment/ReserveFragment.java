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
import com.example.remotetreatment.model.Reserve;
import com.example.remotetreatment.task.GetReservesTask;
import com.example.remotetreatment.task.OnTaskExecuteListener;
import com.example.remotetreatment.util.TaskUtil;
import com.example.remotetreatment.view.listview.PullToRefreshListView;
import com.example.remotetreatment.view.listview.PullToRefreshListView.IDataController;
import com.example.remotetreatment.view.listview.adapter.ReserveAdapter;

@SuppressLint("ValidFragment")
public class ReserveFragment extends BaseFragment {

	final static String TAG = ReserveFragment.class.getSimpleName();
	private View mRoot;
	private RadioGroup mGroup;
	private PullToRefreshListView mRefreshLayout;
	private int mStatus = 0;// 0全部，1完成，2进行中

	public static ReserveFragment newInstance() {
		return newInstance(null);
	}

	public static ReserveFragment newInstance(Bundle args) {
		ReserveFragment f = new ReserveFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mRoot = mAct.getLayoutInflater().inflate(R.layout.fragment_reserve, null);

		initFragment();
		return mRoot;
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
		mGroup = (RadioGroup) mRoot.findViewById(R.id.group);
		mRefreshLayout = (PullToRefreshListView) mRoot.findViewById(R.id.refresh_layout);

		mGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup groupd, int checkedId) {
				if (checkedId == R.id.butn_all) {
					mStatus = 0;
				} else if (checkedId == R.id.butn_done) {
					mStatus = 1;
				} else if (checkedId == R.id.butn_no_done) {
					mStatus = 2;
				}
				refresh();
			}
		});

		mRefreshLayout.useDefaultFooterView();
		mRefreshLayout.setAdapter(new ReserveAdapter(mAct));

		mRefreshLayout.setDataController(new IDataController() {
			@Override
			public void onLoadmore(int page) {
				GetReservesTask task = new GetReservesTask(mAct, new OnTaskExecuteListener() {
					@Override
					public void onSucceed(Object result) {
						super.onSucceed(result);
						ArrayList<Reserve> list = (ArrayList<Reserve>) result;
						mRefreshLayout.onLoadMoreFinish(list);
					}

					@Override
					public void onFailed(Throwable thr) {
						super.onFailed(thr);
					}
				});
				TaskUtil.execute(task, mStatus, 10 * mStatus + page);
			}

			@Override
			public void onRefresh() {
				GetReservesTask task = new GetReservesTask(mAct, new OnTaskExecuteListener() {
					@Override
					public void onSucceed(Object result) {
						super.onSucceed(result);
						ArrayList<Reserve> list = (ArrayList<Reserve>) result;
						mRefreshLayout.onRefreshFinish(list);
					}

					@Override
					public void onFailed(Throwable thr) {
						super.onFailed(thr);
					}
				});
				TaskUtil.execute(task, mStatus, 10 * mStatus);
			}
		});
		
		refresh();
	}

	private void refresh() {
		mRefreshLayout.setRefreshing(true);
		mRefreshLayout.refresh();
	}
}
package com.example.remotetreatment.fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.model.ViewHolder.DoctorHolder;
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
	private ViewGroup mLayoutContent;

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
		registerDoctorReceiver();
		return mRoot;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unregisterDoctorReceiver();
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
		mLayoutContent = (ViewGroup) mRoot.findViewById(R.id.layout_content);
		mRefreshLayout = (PullToRefreshListView) mRoot.findViewById(R.id.refresh_layout);
		mRefreshLayout.setAdapter(new DoctorAdapter(mAct));
		mRefreshLayout.useDefaultFooterView();

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

	DoctorReceiver mDoctorReceiver;

	private void registerDoctorReceiver() {
		try {
			unregisterDoctorReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction(Base.ACTION_SHOW_DOCTOR_DETAIL);
			filter.addAction(Base.ACTION_SHOW_DOCTOR_RESERVE);
			filter.addAction(Base.ACTION_SHOW_DOCTOR_CONFIRM);
			mDoctorReceiver = new DoctorReceiver();
			mAct.registerReceiver(mDoctorReceiver, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void unregisterDoctorReceiver() {
		try {
			mAct.unregisterReceiver(mDoctorReceiver);
			mDoctorReceiver = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class DoctorReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context ctx, Intent intent) {
			try {
				if (intent.getAction().equals(Base.ACTION_SHOW_DOCTOR_LIST)) {
					mLayoutContent.removeAllViews();
					mLayoutContent.setVisibility(View.GONE);
					mLayoutContent.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
						}
					});
				} else {
					View convertView = ((LayoutInflater) mAct.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_doctor, null);

					final Doctor d = (Doctor) intent.getSerializableExtra(Base.EXTRA_DOCTOR);
					final DoctorHolder h = new DoctorHolder(convertView);

					h.base.setText(d.getName() + " " + d.getTitle() + " " + d.getEducation());
					h.hospital.setText(d.getHospital() + " " + d.getDept());
					h.fee.setText(mAct.getString(R.string.regist_fee, d.getRegistFee()));

					h.star.setRating(d.getStar());
					h.expert.setText(d.getExpert());
					h.partTime.setText(d.getPartTime());
					h.intro.setText(d.getIntro());

					h.butnReserve.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							showReserve(d);
						}
					});
					h.butnVisit.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							visit(d);
						}
					});

					if (intent.getAction().equals(Base.ACTION_SHOW_DOCTOR_DETAIL)) {
						h.layoutDetail.setVisibility(View.VISIBLE);
					} else if (intent.getAction().equals(Base.ACTION_SHOW_DOCTOR_RESERVE)) {
						h.layoutReserve.setVisibility(View.VISIBLE);
					} else if (intent.getAction().equals(Base.ACTION_SHOW_DOCTOR_CONFIRM)) {
						h.layoutConfirm.setVisibility(View.VISIBLE);
					}

					mLayoutContent.removeAllViews();
					mLayoutContent.setVisibility(View.VISIBLE);
					mLayoutContent.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
						}
					});
					mLayoutContent.addView(convertView);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void showReserve(Doctor doctor) {
		Intent intent = new Intent(Base.ACTION_SHOW_DOCTOR_RESERVE);
		intent.putExtra(Base.EXTRA_DOCTOR, doctor);
		mAct.sendBroadcast(intent);
	}

	private void visit(Doctor doctor) {
	}

	public boolean onBackPressed() {
		if (mLayoutContent.getVisibility() == View.VISIBLE) {
			mLayoutContent.removeAllViews();
			mLayoutContent.setVisibility(View.GONE);
			mLayoutContent.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				}
			});
			return false;
		}
		return true;
	}
}
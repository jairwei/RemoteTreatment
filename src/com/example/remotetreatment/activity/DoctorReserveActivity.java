package com.example.remotetreatment.activity;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.model.Reserve;
import com.example.remotetreatment.util.AccountUtil;
import com.example.remotetreatment.util.DateUtil;
import com.example.remotetreatment.util.IntentUtil;
import com.example.remotetreatment.util.Toaster;
import com.example.remotetreatment.util.Util;
import com.example.remotetreatment.view.CalendarView;
import com.example.remotetreatment.view.CalendarView.OnCheckedChangedListener;

public class DoctorReserveActivity extends Activity {
	private Doctor mDoctor;
	private CalendarView mCalendarView;
	private TextView mDoctorName;
	private TextView mUser;
	private TextView mTime;
	private TextView mFee;
	private View mButnCommit;
	private View mLayoutTimesDialog;
	private View mLayoutDialog;
	private ViewGroup mLayoutTimes;
	private View mButnConfirm;
	private View mButnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_doctor_reserve);

		mDoctor = (Doctor) getIntent().getSerializableExtra(Base.EXTRA_DOCTOR);
		if (mDoctor == null) {
			Toaster.show("doctor is null.");
			finish();
			return;
		}

		initHeader();
		initView();

		registerOrderConfirmReceiver();
	}

	private void initView() {
		final Doctor d = mDoctor;

		mCalendarView = (CalendarView) findViewById(R.id.calendar_view);
		mDoctorName = (TextView) findViewById(R.id.name);
		mUser = (TextView) findViewById(R.id.user);
		mTime = (TextView) findViewById(R.id.time);
		mFee = (TextView) findViewById(R.id.fee);
		mButnCommit = findViewById(R.id.butn_commit);

		mLayoutTimesDialog = findViewById(R.id.layout_times_dialog);
		mLayoutDialog = findViewById(R.id.layout_dialog);
		mLayoutTimes = (ViewGroup) findViewById(R.id.layout_times);
		mButnConfirm = findViewById(R.id.butn_confirm);
		mButnCancel = findViewById(R.id.butn_cancel);

		mCalendarView.setDates(d.getDateString());
		mDoctorName.setText(d.getName());
		mUser.setText(AccountUtil.getUserAccount().getName() + " " + AccountUtil.getUserAccount().getPhone());
		mFee.setText(getString(R.string.regist_fee1, d.getRegistFee()));
		mButnCommit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Reserve res = new Reserve();
				res.setDoctor(d);
				res.setTime(mTime.getText().toString());
				IntentUtil.showOrderConfirm(DoctorReserveActivity.this, res);
			}
		});
		mCalendarView.setOnCheckedChangedListener(new OnCheckedChangedListener() {
			@Override
			public void onCheckedChanged(String date) {
				buildTimes(date);
				openTimeSelector();
			}
		});
	}

	private void buildTimes(final String date) {
		final Doctor d = mDoctor;
		List<String> times = d.getDates().get(date);

		if (times == null || times.isEmpty()) {
			return;
		}

		mLayoutTimes.removeAllViews();

		int week = DateUtil.getWeek(DateUtil.parseDate(date));
		TextView reserveDate = (TextView) findViewById(R.id.reserve_date);
		reserveDate.setText(DateUtil.getChineseDate(DateUtil.parseDate(date)) + " " + Base.WEEKS[week]);

		for (int i = 0; i < times.size(); i++) {
			final String time = times.get(i);
			View v = getLayoutInflater().inflate(R.layout.item_time, null);
			View layoutItem = v.findViewById(R.id.layout_item);
			TextView timeView = (TextView) v.findViewById(R.id.time);
			timeView.setText(time);

			if (i == times.size() - 1) {
				layoutItem.setBackgroundResource(R.drawable.item_bg_bottom);
				layoutItem.setPadding(Util.dip2pxInt(10), Util.dip2pxInt(10), Util.dip2pxInt(10), Util.dip2pxInt(10));
			}

			layoutItem.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onCheckTime(date + " " + time);
					closeTimeSelector();
				}
			});

			mLayoutTimes.addView(v);
		}
	}

	protected void onCheckTime(String time) {
		mTime.setText(time);
	}

	private void openTimeSelector() {
		try {
			mLayoutTimesDialog.setVisibility(View.VISIBLE);
			mLayoutTimesDialog.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					closeTimeSelector();
				}
			});

			mButnConfirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					closeTimeSelector();
				}
			});
			mButnCancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					closeTimeSelector();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeTimeSelector() {
		mLayoutTimesDialog.setVisibility(View.GONE);
	}

	TextView mHeaderButnLeft;
	TextView mHeaderTitle;

	private void initHeader() {
		mHeaderButnLeft = (TextView) findViewById(R.id.header_butn_left);
		mHeaderButnLeft.setText(R.string.butn_back);
		mHeaderButnLeft.setVisibility(View.VISIBLE);
		mHeaderButnLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		mHeaderTitle = (TextView) findViewById(R.id.header_title);
		mHeaderTitle.setText(R.string.header_doctor_reserve);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterOrderConfirmReceiver();
	}

	private void registerOrderConfirmReceiver() {
		unregisterOrderConfirmReceiver();

		IntentFilter filter = new IntentFilter(Base.ACTION_ORDER_CONFIRMED);
		mOrderConfirmReceiver = new OrderConfirmReceiver();
		registerReceiver(mOrderConfirmReceiver, filter);
	}

	private void unregisterOrderConfirmReceiver() {
		if (mOrderConfirmReceiver != null) {
			unregisterReceiver(mOrderConfirmReceiver);
			mOrderConfirmReceiver = null;
		}
	}

	private OrderConfirmReceiver mOrderConfirmReceiver;

	private class OrderConfirmReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			try {
				finish();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

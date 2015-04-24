package com.example.remotetreatment.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.util.IntentUtil;
import com.example.remotetreatment.util.Toaster;

public class DoctorDetailActivity extends Activity {
	private Doctor mDoctor;

	private ImageView mAvatar;
	private TextView mName;
	private TextView mEducation;
	private TextView mTitle;
	private TextView mHospital;
	private TextView mDept;
	private TextView mFee;
	private View mButnReserve;
	private RatingBar mStar;
	private TextView mScore;
	private TextView mComment;
	private TextView mExpert;
	private TextView mPartTime;
	private TextView mIntro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_doctor_detail);

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

		mAvatar = (ImageView) findViewById(R.id.avatar);
		mName = (TextView) findViewById(R.id.name);
		mEducation = (TextView) findViewById(R.id.education);
		mTitle = (TextView) findViewById(R.id.title);
		mHospital = (TextView) findViewById(R.id.hospital);
		mDept = (TextView) findViewById(R.id.dept);
		mFee = (TextView) findViewById(R.id.fee);
		mButnReserve = findViewById(R.id.butn_reserve);
		mStar = (RatingBar) findViewById(R.id.star);
		mScore = (TextView) findViewById(R.id.score);
		mComment = (TextView) findViewById(R.id.comment);
		mExpert = (TextView) findViewById(R.id.expert);
		mPartTime = (TextView) findViewById(R.id.part_time);
		mIntro = (TextView) findViewById(R.id.intro);

		// ImageLoaderUtil.display(d.getAvatar(), mAvatar);
		mName.setText(d.getName());
		mEducation.setText(d.getEducation());
		mTitle.setText(d.getTitle());
		mHospital.setText(d.getHospital());
		mDept.setText(d.getDept());
		mFee.setText(getString(R.string.regist_fee1, d.getRegistFee()));
		mStar.setRating(d.getStar());
		mScore.setText(getString(R.string.score, d.getScore()));
		mComment.setText(getString(R.string.comment, d.getComment()));
		mExpert.setText(d.getExpert());
		mPartTime.setText(d.getPartTime());
		mIntro.setText(d.getIntro());

		mButnReserve.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtil.showDoctorReserve(DoctorDetailActivity.this, d);
			}
		});
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
		mHeaderTitle.setText(R.string.header_doctor_detail);
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

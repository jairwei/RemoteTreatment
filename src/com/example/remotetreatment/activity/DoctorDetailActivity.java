package com.example.remotetreatment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.util.ImageLoaderUtil;
import com.example.remotetreatment.util.IntentUtil;
import com.example.remotetreatment.util.Toaster;

public class DoctorDetailActivity extends Activity {
	private Doctor mDoctor;

	private ImageView mAvatar;
	private TextView mBase;
	private TextView mHospital;
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
	}

	private void initView() {
		final Doctor d = mDoctor;

		mAvatar = (ImageView) findViewById(R.id.avatar);
		mBase = (TextView) findViewById(R.id.base);
		mHospital = (TextView) findViewById(R.id.hospital);
		mFee = (TextView) findViewById(R.id.fee);
		mButnReserve = findViewById(R.id.butn_reserve);
		mStar = (RatingBar) findViewById(R.id.star);
		mScore = (TextView) findViewById(R.id.score);
		mComment = (TextView) findViewById(R.id.comment);
		mExpert = (TextView) findViewById(R.id.expert);
		mPartTime = (TextView) findViewById(R.id.part_time);
		mIntro = (TextView) findViewById(R.id.intro);

		ImageLoaderUtil.display(d.getAvatar(), mAvatar);
		mBase.setText(d.getName() + " " + d.getTitle() + " " + d.getEducation());
		mHospital.setText(d.getHospital() + " " + d.getDept());
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
}

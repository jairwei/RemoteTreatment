package com.example.remotetreatment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.model.Reserve;
import com.example.remotetreatment.util.Toaster;

public class OrderConfirmActivity extends Activity {
	private Reserve mReserve;

	private TextView mReserveDoctor;
	private TextView mReserveTime;
	private TextView mReserveFee;
	private View mButnConfirmPay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_order_confirm);

		mReserve = (Reserve) getIntent().getSerializableExtra(Base.EXTRA_RESERVE);
		if (mReserve == null) {
			Toaster.show("reserve is null.");
			finish();
			return;
		}

		initHeader();
		initView();
	}

	private void initView() {
		final Reserve r = mReserve;
		final Doctor d = r.getDoctor();

		mReserveDoctor = (TextView) findViewById(R.id.name);
		mReserveTime = (TextView) findViewById(R.id.time);
		mReserveFee = (TextView) findViewById(R.id.fee);
		mButnConfirmPay = (TextView) findViewById(R.id.butn_confirm_pay);

		mReserveDoctor.setText(d.getName());
		mReserveTime.setText(r.getTime());
		mReserveFee.setText(String.valueOf(d.getRegistFee()));

		mButnConfirmPay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				confirmPay();
			}
		});
	}

	private void confirmPay() {
		Intent intent = new Intent(Base.ACTION_ORDER_CONFIRMED);
		sendBroadcast(intent);

		finish();
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
		mHeaderTitle.setText(R.string.header_order_confirm);
	}
}

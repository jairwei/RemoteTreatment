package com.example.remotetreatment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.remotetreatment.R;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_login);

		initHeader();
		initView();
	}

	private void initView() {
	}

	// TextView mHeaderButnLeft;
	TextView mHeaderTitle;

	private void initHeader() {
		// mHeaderButnLeft = (TextView) findViewById(R.id.header_butn_left);
		// mHeaderButnLeft.setText(R.string.butn_back);
		// mHeaderButnLeft.setVisibility(View.VISIBLE);
		// mHeaderButnLeft.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// finish();
		// }
		// });

		mHeaderTitle = (TextView) findViewById(R.id.header_title);
		mHeaderTitle.setText(R.string.header_login);
	}
}

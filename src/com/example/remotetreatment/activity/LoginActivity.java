package com.example.remotetreatment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.remotetreatment.R;
import com.example.remotetreatment.util.IntentUtil;

public class LoginActivity extends Activity {
	private EditText mPhone;
	private EditText mPassword;
	private TextView mButnLogin;
	private TextView mButnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_login);

		initHeader();
		initView();
	}

	private void initView() {
		mPhone = (EditText) findViewById(R.id.phone);
		mPassword = (EditText) findViewById(R.id.password);
		mButnLogin = (TextView) findViewById(R.id.butn_login);
		mButnRegister = (TextView) findViewById(R.id.butn_register);

		mButnRegister.setVisibility(View.GONE);
		mButnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				login();
			}
		});
	}

	private void login() {
		// IntentUtil.showLogin(this);
		// finish();
	}

	TextView mHeaderTitle;
	TextView mHeaderButnRight;

	private void initHeader() {
		mHeaderTitle = (TextView) findViewById(R.id.header_title);
		mHeaderTitle.setText(R.string.header_login);

		mHeaderButnRight = (TextView) findViewById(R.id.header_butn_right);
		mHeaderButnRight.setText(R.string.butn_register);
		mHeaderButnRight.setVisibility(View.VISIBLE);
		mHeaderButnRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				IntentUtil.showRegister(LoginActivity.this);
				finish();
			}
		});
	}
}

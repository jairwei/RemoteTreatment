package com.example.remotetreatment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.remotetreatment.R;
import com.example.remotetreatment.util.IntentUtil;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_splash);

		initView();
	}

	private void initView() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				try {
					// UserAccount user = AccountUtil.getUserAccount();
					// if (user == null || user.getId() == 0) {
					// IntentUtil.showRegister(SplashActivity.this);
					// } else {
					IntentUtil.showHome(SplashActivity.this);
					// }
					finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 1000 * 2);
	}
}

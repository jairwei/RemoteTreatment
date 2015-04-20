package com.example.remotetreatment.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.remotetreatment.R;
import com.example.remotetreatment.fragment.BaseFragment;
import com.example.remotetreatment.fragment.DoctorFragment;
import com.example.remotetreatment.fragment.RecordFragment;
import com.example.remotetreatment.fragment.ReserveFragment;
import com.example.remotetreatment.fragment.SettingFragment;
import com.example.remotetreatment.util.Toaster;

public class HomeActivity extends FragmentActivity {

	private ViewPager mPager;
	private GroupAdapter mAdapter;
	private RadioGroup mGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_home);

		initHeader();
		initView();
	}

	private void initView() {
		mPager = (ViewPager) findViewById(R.id.pager);
		mGroup = (RadioGroup) findViewById(R.id.group);

		mAdapter = new GroupAdapter(getSupportFragmentManager());
		mPager.setOffscreenPageLimit(4);
		mPager.setAdapter(mAdapter);

		mGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup groupd, int checkedId) {
				if (checkedId == R.id.butn_doctor) {
					mPager.setCurrentItem(0);
				} else if (checkedId == R.id.butn_reserve) {
					mPager.setCurrentItem(1);
				} else if (checkedId == R.id.butn_record) {
					mPager.setCurrentItem(2);
				} else if (checkedId == R.id.butn_setting) {
					mPager.setCurrentItem(3);
				}
			}
		});
	}

	public class GroupAdapter extends FragmentPagerAdapter {
		public GroupAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			BaseFragment fragment = null;
			switch (position) {
			case 0:
				fragment = DoctorFragment.newInstance();
				break;
			case 1:
				fragment = ReserveFragment.newInstance();
				break;
			case 2:
				fragment = RecordFragment.newInstance();
				break;
			case 3:
				fragment = SettingFragment.newInstance();
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return 4;
		}
	}

	private boolean mBackKeyPressed = false;

	@Override
	public void onBackPressed() {
		// if (mPager.getCurrentItem() == 0) {
		// DoctorFragment doctorFragment = (DoctorFragment) ViewUtil.getActiveFragment(this, mPager, 0);
		// if (!doctorFragment.onBackPressed()) {
		// return;
		// }
		// }

		if (mBackKeyPressed) {
			super.onBackPressed();
			return;
		}

		mBackKeyPressed = true;
		Toaster.show(R.string.press_again_for_exit);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mBackKeyPressed = false;
			}
		}, 1000 * 2);
	}

	TextView mHeaderTitle;

	private void initHeader() {
		mHeaderTitle = (TextView) findViewById(R.id.header_title);
		mHeaderTitle.setText(R.string.app_name);
	}
}

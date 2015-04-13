package com.example.remotetreatment.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.remotetreatment.R;

@SuppressLint("ValidFragment")
public class SettingFragment extends BaseFragment {

	final static String TAG = SettingFragment.class.getSimpleName();
	private View mRoot;

	public static SettingFragment newInstance() {
		return newInstance(null);
	}

	public static SettingFragment newInstance(Bundle args) {
		SettingFragment f = new SettingFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mRoot = mAct.getLayoutInflater().inflate(R.layout.fragment_setting, null);

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

	}
}
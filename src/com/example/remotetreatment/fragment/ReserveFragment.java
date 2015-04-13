package com.example.remotetreatment.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.remotetreatment.R;

@SuppressLint("ValidFragment")
public class ReserveFragment extends BaseFragment {

	final static String TAG = ReserveFragment.class.getSimpleName();
	private View mRoot;

	public static ReserveFragment newInstance() {
		return newInstance(null);
	}

	public static ReserveFragment newInstance(Bundle args) {
		ReserveFragment f = new ReserveFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mRoot = mAct.getLayoutInflater().inflate(R.layout.fragment_reserve, null);

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
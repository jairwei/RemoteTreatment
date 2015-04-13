package com.example.remotetreatment.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.remotetreatment.R;

@SuppressLint("ValidFragment")
public class RecordFragment extends BaseFragment {

	final static String TAG = RecordFragment.class.getSimpleName();
	private View mRoot;

	public static RecordFragment newInstance() {
		return newInstance(null);
	}

	public static RecordFragment newInstance(Bundle args) {
		RecordFragment f = new RecordFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mRoot = mAct.getLayoutInflater().inflate(R.layout.fragment_record, null);

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
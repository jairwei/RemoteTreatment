package com.example.remotetreatment.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.remotetreatment.util.Util;

public class BaseFragment extends Fragment {
	public BaseFragment() {
	}

	public boolean inited = false;

	public boolean isInited() {
		return inited;
	}

	public void initFragment() {
		Util.log(getClass().getName() + "-> initFragment");
		inited = true;
	}

	public Activity mAct;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mAct = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Util.log(getClass().getName() + "-> onCreate");
		// if (savedInstanceState != null) {
		// inited = savedInstanceState.getBoolean("inited");
		// }
		setRetainInstance(false);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Util.log(getClass().getName() + "-> onCreateView");
		// if (savedInstanceState != null) {
		// inited = savedInstanceState.getBoolean("inited");
		// }
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
		Util.log(getClass().getName() + "-> onInflate");
		// if (savedInstanceState != null) {
		// inited = savedInstanceState.getBoolean("inited");
		// }
		super.onInflate(activity, attrs, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		Util.log(getClass().getName() + "-> onViewCreated");
		// if (savedInstanceState != null) {
		// inited = savedInstanceState.getBoolean("inited");
		// }
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Util.log(getClass().getName() + "-> onSaveInstanceState");
		// outState.putBoolean("inited", inited);
		super.onSaveInstanceState(outState);
	}

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		Util.log(getClass().getName() + "-> onCreateAnimation");
		return super.onCreateAnimation(transit, enter, nextAnim);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		Util.log(getClass().getName() + "-> onCreateContextMenu");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		Util.log(getClass().getName() + "-> onCreateOptionsMenu");
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onDestroy() {
		Util.log(getClass().getName() + "-> onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDestroyOptionsMenu() {
		Util.log(getClass().getName() + "-> onDestroyOptionsMenu");
		super.onDestroyOptionsMenu();
	}

	@Override
	public void onDestroyView() {
		Util.log(getClass().getName() + "-> onDestroyView");
		// inited = false;
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		Util.log(getClass().getName() + "-> onDetach");
		super.onDetach();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		Util.log(getClass().getName() + "-> onHiddenChanged");
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onLowMemory() {
		Util.log(getClass().getName() + "-> onLowMemory");
		super.onLowMemory();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Util.log(getClass().getName() + "-> onOptionsItemSelected");
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		Util.log(getClass().getName() + "-> onOptionsMenuClosed");
		super.onOptionsMenuClosed(menu);
	}

	@Override
	public void onPause() {
		Util.log(getClass().getName() + "-> onPause");
		super.onPause();
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		Util.log(getClass().getName() + "-> onPrepareOptionsMenu");
		super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void onResume() {
		Util.log(getClass().getName() + "-> onResume");
		super.onResume();
	}

	@Override
	public void onStart() {
		Util.log(getClass().getName() + "-> onStart");
		super.onStart();
	}

	@Override
	public void onStop() {
		Util.log(getClass().getName() + "-> onStop");
		super.onStop();
	}
}
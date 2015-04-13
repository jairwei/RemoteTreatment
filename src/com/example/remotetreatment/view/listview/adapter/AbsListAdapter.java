package com.example.remotetreatment.view.listview.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public abstract class AbsListAdapter<T> extends SetAdapter<T> implements android.widget.AbsListView.RecyclerListener {

	protected ListView mListView;
	// private int mDelayMillis;

	private volatile boolean mIsFling;

	private Handler mNotifyHandler;

	public AbsListAdapter(Context context) {
		super(context, new ArrayList<T>());
		mIsFling = false;
		// mDelayMillis = 500;
		mNotifyHandler = new Handler() {
			public void handleMessage(Message message) {
				if (message.what == 1000) {
					callNotifyDataSetChanged();
					return;
				} else {
					super.handleMessage(message);
					return;
				}
			}
		};
	}

	private void callNotifyDataSetChanged() {
		if (mIsFling) {
			notifyDataSetChanged();
			return;
		} else {
			mNotifyHandler.removeMessages(1000);
			super.notifyDataSetChanged();
			return;
		}
	}

	public View getDropDownView(int i, View view, ViewGroup viewgroup) {
		return getView(i, view, viewgroup);
	}

	@Override
	public void onMovedToScrapHeap(View arg0) {

	}

	public abstract View getView(int i, View view, ViewGroup viewgroup);
}

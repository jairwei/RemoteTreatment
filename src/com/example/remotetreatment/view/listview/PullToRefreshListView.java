package com.example.remotetreatment.view.listview;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.example.remotetreatment.R;
import com.example.remotetreatment.util.Util;
import com.example.remotetreatment.view.listview.adapter.AbsListAdapter;

public class PullToRefreshListView extends SwipeRefreshLayout {

	public static String TAG = PullToRefreshListView.class.getSimpleName();

	private ListView listView;
	private AbsListAdapter adapter;
	private IDataController dataController;
	private int page;

	public PullToRefreshListView(Context context) {
		super(context);
		init(context, null);
	}

	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public void init(Context context, AttributeSet attrs) {

	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);

		setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				Util.log(TAG, "onRefresh");
				refresh();
			}
		});
		// listView.setOnScrollListener(new CustListView.OnScrollListener() {
		// @Override
		// public void onScrollEnd() {
		// Util.log(TAG, "onLoadmore");
		// if (dataController != null) {
		// page++;
		// dataController.onLoadmore(page);
		// }
		// }
		// });

		listView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				// ��������ʱ
				case OnScrollListener.SCROLL_STATE_IDLE:
					// �жϹ������ײ�
					if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
						Util.log(TAG, "onLoadmore");
						loadmore();
					}
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			}
		});

	}

	public IDataController getDataController() {
		return dataController;
	}

	public void setDataController(IDataController dataController) {
		this.dataController = dataController;
	}

	public AbsListAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(AbsListAdapter adapter) {
		this.adapter = adapter;
		if (listView != null)
			listView.setAdapter(adapter);
	}

	public ListView getListView() {
		return listView;
	}

	public void setListView(ListView listView) {
		this.listView = listView;
	}

	public static interface IDataController {
		public abstract void onLoadmore(int page);

		public abstract void onRefresh();
	}

	public void onRefreshFinish(ArrayList list) {
		adapter.clear();
		adapter.addAll(list);
		setRefreshing(false);
	}

	public void onLoadMoreFinish(ArrayList list) {
		adapter.addAll(list);
		// adapter.notifyDataSetChanged();
		setLoadingMore(false);

		if (footerView != null) {
			footerView.setVisibility(View.GONE);
			// listView.removeFooterView(footerView);
		}
	}

	public void refresh() {
		if (loadingMore) {
			setRefreshing(false);
			return;
		}
		// if (isRefreshing() || loadingMore) {
		// setRefreshing(false);
		// return;
		// }
		if (dataController != null) {
			page = 0;
			dataController.onRefresh();
		}
	}

	public void loadmore() {
		if (isRefreshing() || loadingMore || !hasMore) {
			setLoadingMore(false);
			return;
		}

		if (dataController != null) {
			page++;
			dataController.onLoadmore(page);
			if (footerView != null) {
				footerView.setVisibility(View.VISIBLE);
				// listView.removeFooterView(footerView);
				// listView.addFooterView(footerView);
			}
		}
	}

	private boolean loadingMore = false;
	private boolean hasMore = true;
	private View footerView;

	public void setLoadingMore(boolean b) {
		loadingMore = b;
	}

	public void useDefaultFooterView() {
		Context ctx = getContext();
		View footer = inflate(ctx, R.layout.com_def_footer_view, null);
		View loadingView = footer.findViewById(R.id.loading_anim);
		startLoadingAnimation(loadingView, true);
		setFooterView(footer);
	}

	public void setFooterView(View footer) {
		footerView = footer;

		if (footerView != null) {
			listView.removeFooterView(footerView);
			listView.addFooterView(footerView);
			footerView.setVisibility(View.GONE);
		}
	}

	public static void startLoadingAnimation(View v, boolean clockwise) {
		if (v == null) {
			return;
		}
		final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
		final int ROTATION_ANIMATION_DURATION = 1200;
		RotateAnimation rotate = null;
		if (clockwise) {
			rotate = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		} else {
			rotate = new RotateAnimation(720, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		}
		rotate.setInterpolator(ANIMATION_INTERPOLATOR);
		rotate.setDuration(ROTATION_ANIMATION_DURATION);
		rotate.setRepeatMode(Animation.RESTART);
		rotate.setRepeatCount(Animation.INFINITE);

		v.startAnimation(rotate);
	}
}

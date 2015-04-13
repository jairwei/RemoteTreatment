package com.example.remotetreatment.view.listview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;

// Referenced classes of package android.support.v4.widget:
//            SwipeProgressBar

public class SwipeRefreshLayout extends ViewGroup {
	private class BaseAnimationListener implements android.view.animation.Animation.AnimationListener {

		public void onAnimationStart(Animation animation1) {
		}

		public void onAnimationEnd(Animation animation1) {
		}

		public void onAnimationRepeat(Animation animation1) {
		}

		final SwipeRefreshLayout this$0;

		private BaseAnimationListener() {
			this$0 = SwipeRefreshLayout.this;
		}
	}

	//
	// public static interface OnRefreshListener {
	//
	// public abstract void onRefresh();
	// }

	public SwipeRefreshLayout(Context context) {
		this(context, null);
	}

	public SwipeRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mRefreshing = false;
		mDistanceToTriggerSync = -1F;
		mFromPercentage = 0.0F;
		mCurrPercentage = 0.0F;
		mAnimateToStartPosition = new Animation() {

			public void applyTransformation(float interpolatedTime, Transformation t) {
				int targetTop = 0;
				if (mFrom != mOriginalOffsetTop)
					targetTop = mFrom + (int) ((float) (mOriginalOffsetTop - mFrom) * interpolatedTime);
				int offset = targetTop - mTarget.getTop();
				int currentTop = mTarget.getTop();
				if (offset + currentTop < 0)
					offset = 0 - currentTop;
				setTargetOffsetTopAndBottom(offset);
			}

			final SwipeRefreshLayout this$0;
			{
				this$0 = SwipeRefreshLayout.this;
			}
		};
		mReturnToStartPositionListener = new BaseAnimationListener() {

			public void onAnimationEnd(Animation animation) {
				mCurrentTargetOffsetTop = 0;
			}

			final SwipeRefreshLayout this$0;
			{
				this$0 = SwipeRefreshLayout.this;
			}
		};
		mShrinkAnimationListener = new BaseAnimationListener() {

			public void onAnimationEnd(Animation animation) {
				mCurrPercentage = 0.0F;
			}

			final SwipeRefreshLayout this$0;

			{
				this$0 = SwipeRefreshLayout.this;
			}
		};
		mReturnToStartPosition = new Runnable() {

			public void run() {
				mReturningToStart = true;
				animateOffsetToStartPosition(mCurrentTargetOffsetTop + getPaddingTop(), mReturnToStartPositionListener);
			}

			final SwipeRefreshLayout this$0;
			{
				this$0 = SwipeRefreshLayout.this;
			}
		};
		mCancel = new Runnable() {

			public void run() {
				mReturningToStart = true;
				if (mProgressBar != null) {
					mFromPercentage = mCurrPercentage;
					mShrinkTrigger.setDuration(mMediumAnimationDuration);
					mShrinkTrigger.setAnimationListener(mShrinkAnimationListener);
					mShrinkTrigger.reset();
					mShrinkTrigger.setInterpolator(mDecelerateInterpolator);
					startAnimation(mShrinkTrigger);
				}
				animateOffsetToStartPosition(mCurrentTargetOffsetTop + getPaddingTop(), mReturnToStartPositionListener);
			}

			final SwipeRefreshLayout this$0;

			{
				this$0 = SwipeRefreshLayout.this;
			}
		};
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mMediumAnimationDuration = getResources().getInteger(17694721);
		setWillNotDraw(false);
		mProgressBar = new SwipeProgressBar(this);
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		mProgressBarHeight = (int) (metrics.density * 4F);
		mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
		mAccelerateInterpolator = new AccelerateInterpolator(1.5F);
		TypedArray a = context.obtainStyledAttributes(attrs, LAYOUT_ATTRS);
		setEnabled(a.getBoolean(0, true));
		a.recycle();
	}

	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		removeCallbacks(mCancel);
		removeCallbacks(mReturnToStartPosition);
	}

	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		removeCallbacks(mReturnToStartPosition);
		removeCallbacks(mCancel);
	}

	private void animateOffsetToStartPosition(int from, android.view.animation.Animation.AnimationListener listener) {
		mFrom = from;
		mAnimateToStartPosition.reset();
		mAnimateToStartPosition.setDuration(mMediumAnimationDuration);
		mAnimateToStartPosition.setAnimationListener(listener);
		mAnimateToStartPosition.setInterpolator(mDecelerateInterpolator);
		mTarget.startAnimation(mAnimateToStartPosition);
	}

	public void setOnRefreshListener(OnRefreshListener listener) {
		mListener = listener;
	}

	private void setTriggerPercentage(float percent) {
		if (percent == 0.0F) {
			mCurrPercentage = 0.0F;
			return;
		} else {
			mCurrPercentage = percent;
			mProgressBar.setTriggerPercentage(percent);
			return;
		}
	}

	public void setRefreshing(boolean refreshing) {
		if (mRefreshing != refreshing) {
			ensureTarget();
			mCurrPercentage = 0.0F;
			mRefreshing = refreshing;
			if (mRefreshing)
				mProgressBar.start();
			else
				mProgressBar.stop();
		}
	}

	public void setColorScheme(int colorRes1, int colorRes2, int colorRes3, int colorRes4) {
		ensureTarget();
		Resources res = getResources();
		int color1 = res.getColor(colorRes1);
		int color2 = res.getColor(colorRes2);
		int color3 = res.getColor(colorRes3);
		int color4 = res.getColor(colorRes4);
		mProgressBar.setColorScheme(color1, color2, color3, color4);
	}

	public boolean isRefreshing() {
		return mRefreshing;
	}

	public void ensureTarget() {
		if (mTarget == null) {
			if (getChildCount() > 1 && !isInEditMode())
				throw new IllegalStateException("SwipeRefreshLayout can host only one direct child");
			mTarget = getChildAt(0);
			mOriginalOffsetTop = mTarget.getTop() + getPaddingTop();
		}
		if (mDistanceToTriggerSync == -1F && getParent() != null && ((View) getParent()).getHeight() > 0) {
			DisplayMetrics metrics = getResources().getDisplayMetrics();
			mDistanceToTriggerSync = (int) Math.min((float) ((View) getParent()).getHeight() * 0.6F, 120F * metrics.density);
		}
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		mProgressBar.draw(canvas);
	}

	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		mProgressBar.setBounds(0, 0, width, mProgressBarHeight);
		if (getChildCount() == 0) {
			return;
		} else {
			View child = getChildAt(0);
			int childLeft = getPaddingLeft();
			int childTop = mCurrentTargetOffsetTop + getPaddingTop();
			int childWidth = width - getPaddingLeft() - getPaddingRight();
			int childHeight = height - getPaddingTop() - getPaddingBottom();
			child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
			return;
		}
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (getChildCount() > 1 && !isInEditMode())
			throw new IllegalStateException("SwipeRefreshLayout can host only one direct child");
		if (getChildCount() > 0)
			getChildAt(0).measure(android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), 1073741824), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), 1073741824));
	}

	public boolean canChildScrollUp() {
		if (android.os.Build.VERSION.SDK_INT < 14) {
			if (mTarget instanceof AbsListView) {
				AbsListView absListView = (AbsListView) mTarget;
				return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
			} else {
				return mTarget.getScrollY() > 0;
			}
		} else {
			return ViewCompat.canScrollVertically(mTarget, -1);
		}
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		ensureTarget();
		boolean handled = false;
		if (mReturningToStart && ev.getAction() == 0)
			mReturningToStart = false;
		if (isEnabled() && !mReturningToStart && !canChildScrollUp())
			handled = onTouchEvent(ev);
		return handled ? handled : super.onInterceptTouchEvent(ev);
	}

	public void requestDisallowInterceptTouchEvent(boolean flag) {
	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		boolean handled = false;
		switch (action) {
		default:
			break;

		case 0: // '\0'
			mCurrPercentage = 0.0F;
			mDownEvent = MotionEvent.obtain(event);
			mPrevY = mDownEvent.getY();
			break;

		case 2: // '\002'
			if (mDownEvent == null || mReturningToStart)
				break;
			float eventY = event.getY();
			float yDiff = eventY - mDownEvent.getY();
			if (yDiff <= (float) mTouchSlop)
				break;
			if (yDiff > mDistanceToTriggerSync) {
				startRefresh();
				handled = true;
				break;
			}
			setTriggerPercentage(mAccelerateInterpolator.getInterpolation(yDiff / mDistanceToTriggerSync));
			float offsetTop = yDiff;
			if (mPrevY > eventY)
				offsetTop = yDiff - (float) mTouchSlop;
			updateContentOffsetTop((int) offsetTop);
			if (mPrevY > eventY && mTarget.getTop() < mTouchSlop)
				removeCallbacks(mCancel);
			else
				updatePositionTimeout();
			mPrevY = event.getY();
			handled = true;
			break;

		case 1: // '\001'
		case 3: // '\003'
			if (mDownEvent != null) {
				mDownEvent.recycle();
				mDownEvent = null;
			}
			break;
		}
		return handled;
	}

	public void startRefresh() {
		removeCallbacks(mCancel);
		mReturnToStartPosition.run();
		setRefreshing(true);
		mListener.onRefresh();
	}

	private void updateContentOffsetTop(int targetTop) {
		int currentTop = mTarget.getTop();
		if ((float) targetTop > mDistanceToTriggerSync)
			targetTop = (int) mDistanceToTriggerSync;
		else if (targetTop < 0)
			targetTop = 0;
		setTargetOffsetTopAndBottom(targetTop - currentTop);
	}

	private void setTargetOffsetTopAndBottom(int offset) {
		mTarget.offsetTopAndBottom(offset);
		mCurrentTargetOffsetTop = mTarget.getTop();
	}

	private void updatePositionTimeout() {
		removeCallbacks(mCancel);
		postDelayed(mCancel, 300L);
	}

	private static final long RETURN_TO_ORIGINAL_POSITION_TIMEOUT = 300L;
	private static final float ACCELERATE_INTERPOLATION_FACTOR = 1.5F;
	private static final float DECELERATE_INTERPOLATION_FACTOR = 2F;
	private static final float PROGRESS_BAR_HEIGHT = 4F;
	private static final float MAX_SWIPE_DISTANCE_FACTOR = 0.6F;
	private static final int REFRESH_TRIGGER_DISTANCE = 120;
	private SwipeProgressBar mProgressBar;
	private View mTarget;
	private int mOriginalOffsetTop;
	private OnRefreshListener mListener;
	private MotionEvent mDownEvent;
	private int mFrom;
	private boolean mRefreshing;
	private int mTouchSlop;
	private float mDistanceToTriggerSync;
	private float mPrevY;
	private int mMediumAnimationDuration;
	private float mFromPercentage;
	private float mCurrPercentage;
	private int mProgressBarHeight;
	private int mCurrentTargetOffsetTop;
	private boolean mReturningToStart;
	private final DecelerateInterpolator mDecelerateInterpolator;
	private final AccelerateInterpolator mAccelerateInterpolator;
	private static final int LAYOUT_ATTRS[] = { 16842766 };
	private final Animation mAnimateToStartPosition;
	private Animation mShrinkTrigger = new Animation() {

		public void applyTransformation(float interpolatedTime, Transformation t) {
			float percent = mFromPercentage + (0.0F - mFromPercentage) * interpolatedTime;
			mProgressBar.setTriggerPercentage(percent);
		}

		final SwipeRefreshLayout this$0;
		{
			this$0 = SwipeRefreshLayout.this;
		}
	};
	private final android.view.animation.Animation.AnimationListener mReturnToStartPositionListener;
	private final android.view.animation.Animation.AnimationListener mShrinkAnimationListener;
	private final Runnable mReturnToStartPosition;
	private final Runnable mCancel;
}

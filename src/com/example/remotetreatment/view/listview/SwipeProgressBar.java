package com.example.remotetreatment.view.listview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

final class SwipeProgressBar {

	public SwipeProgressBar(View parent) {
		mBounds = new Rect();
		mParent = parent;
		mColor1 = -1291845632;
		mColor2 = -2147483648;
		mColor3 = 1291845632;
		mColor4 = 436207616;
	}

	void setColorScheme(int color1, int color2, int color3, int color4) {
		mColor1 = color1;
		mColor2 = color2;
		mColor3 = color3;
		mColor4 = color4;
	}

	void setTriggerPercentage(float triggerPercentage) {
		mTriggerPercentage = triggerPercentage;
		mStartTime = 0L;
		ViewCompat.postInvalidateOnAnimation(mParent);
	}

	void start() {
		if (!mRunning) {
			mTriggerPercentage = 0.0F;
			mStartTime = AnimationUtils.currentAnimationTimeMillis();
			mRunning = true;
			mParent.postInvalidate();
		}
	}

	void stop() {
		if (mRunning) {
			mTriggerPercentage = 0.0F;
			mFinishTime = AnimationUtils.currentAnimationTimeMillis();
			mRunning = false;
			mParent.postInvalidate();
		}
	}

	boolean isRunning() {
		return mRunning || mFinishTime > 0L;
	}

	void draw(Canvas canvas) {
		int width = mBounds.width();
		int height = mBounds.height();
		int cx = width / 2;
		int cy = height / 2;
		boolean drawTriggerWhileFinishing = false;
		int restoreCount = canvas.save();
		canvas.clipRect(mBounds);
		if (mRunning || mFinishTime > 0L) {
			long now = AnimationUtils.currentAnimationTimeMillis();
			long elapsed = (now - mStartTime) % 2000L;
			long iterations = (now - mStartTime) / 2000L;
			float rawProgress = (float) elapsed / 20F;
			if (!mRunning) {
				if (now - mFinishTime >= 1000L) {
					mFinishTime = 0L;
					return;
				}
				long finishElapsed = (now - mFinishTime) % 1000L;
				float finishProgress = (float) finishElapsed / 10F;
				float pct = finishProgress / 100F;
				float clearRadius = (float) (width / 2) * INTERPOLATOR.getInterpolation(pct);
				mClipRect.set((float) cx - clearRadius, 0.0F, (float) cx + clearRadius, height);
				canvas.saveLayerAlpha(mClipRect, 0, 0);
				drawTriggerWhileFinishing = true;
			}
			if (iterations == 0L)
				canvas.drawColor(mColor1);
			else if (rawProgress >= 0.0F && rawProgress < 25F)
				canvas.drawColor(mColor4);
			else if (rawProgress >= 25F && rawProgress < 50F)
				canvas.drawColor(mColor1);
			else if (rawProgress >= 50F && rawProgress < 75F)
				canvas.drawColor(mColor2);
			else
				canvas.drawColor(mColor3);
			if (rawProgress >= 0.0F && rawProgress <= 25F) {
				float pct = ((rawProgress + 25F) * 2.0F) / 100F;
				drawCircle(canvas, cx, cy, mColor1, pct);
			}
			if (rawProgress >= 0.0F && rawProgress <= 50F) {
				float pct = (rawProgress * 2.0F) / 100F;
				drawCircle(canvas, cx, cy, mColor2, pct);
			}
			if (rawProgress >= 25F && rawProgress <= 75F) {
				float pct = ((rawProgress - 25F) * 2.0F) / 100F;
				drawCircle(canvas, cx, cy, mColor3, pct);
			}
			if (rawProgress >= 50F && rawProgress <= 100F) {
				float pct = ((rawProgress - 50F) * 2.0F) / 100F;
				drawCircle(canvas, cx, cy, mColor4, pct);
			}
			if (rawProgress >= 75F && rawProgress <= 100F) {
				float pct = ((rawProgress - 75F) * 2.0F) / 100F;
				drawCircle(canvas, cx, cy, mColor1, pct);
			}
			if (mTriggerPercentage > 0.0F && drawTriggerWhileFinishing) {
				canvas.restoreToCount(restoreCount);
				restoreCount = canvas.save();
				canvas.clipRect(mBounds);
				drawTrigger(canvas, cx, cy);
			}
			ViewCompat.postInvalidateOnAnimation(mParent);
		} else if (mTriggerPercentage > 0.0F && (double) mTriggerPercentage <= 1.0D)
			drawTrigger(canvas, cx, cy);
		canvas.restoreToCount(restoreCount);
	}

	private void drawTrigger(Canvas canvas, int cx, int cy) {
		mPaint.setColor(mColor1);
		canvas.drawCircle(cx, cy, (float) cx * mTriggerPercentage, mPaint);
	}

	private void drawCircle(Canvas canvas, float cx, float cy, int color, float pct) {
		mPaint.setColor(color);
		canvas.save();
		canvas.translate(cx, cy);
		float radiusScale = INTERPOLATOR.getInterpolation(pct);
		canvas.scale(radiusScale, radiusScale);
		canvas.drawCircle(0.0F, 0.0F, cx, mPaint);
		canvas.restore();
	}

	void setBounds(int left, int top, int right, int bottom) {
		mBounds.left = left;
		mBounds.top = top;
		mBounds.right = right;
		mBounds.bottom = bottom;
	}

	// private static final int COLOR1 = -1291845632;
	// private static final int COLOR2 = -2147483648;
	// private static final int COLOR3 = 1291845632;
	// private static final int COLOR4 = 436207616;
	// private static final int ANIMATION_DURATION_MS = 2000;
	// private static final int FINISH_ANIMATION_DURATION_MS = 1000;
	private static final Interpolator INTERPOLATOR = BakedBezierInterpolator.getInstance();
	private final Paint mPaint = new Paint();
	private final RectF mClipRect = new RectF();
	private float mTriggerPercentage;
	private long mStartTime;
	private long mFinishTime;
	private boolean mRunning;
	private int mColor1;
	private int mColor2;
	private int mColor3;
	private int mColor4;
	private View mParent;
	private Rect mBounds;
}
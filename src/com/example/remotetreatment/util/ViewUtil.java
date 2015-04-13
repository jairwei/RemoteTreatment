package com.example.remotetreatment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.remotetreatment.Base;

public class ViewUtil {

	public static Fragment getActiveFragment(FragmentActivity f, ViewPager container, int position) {
		try {
			String name = makeFragmentName(container.getId(), position);
			return f.getSupportFragmentManager().findFragmentByTag(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String makeFragmentName(int viewId, int index) {
		return "android:switcher:" + viewId + ":" + index;
	}

	public static void initSize(Activity a) {
		DisplayMetrics dm = a.getResources().getDisplayMetrics();
		Base.WINDOW_WIDTH = dm.widthPixels;
		Base.WINDOW_HEIGHT = dm.heightPixels;
	}

	public static int getStatusBarHeight(View v) {
		Rect outRect = new Rect();
		v.getWindowVisibleDisplayFrame(outRect);
		return outRect.top;
	}

	public static Rect getWindowRect(View v) {
		Rect outRect = new Rect();
		v.getWindowVisibleDisplayFrame(outRect);
		return outRect;
	}

	public static void setColor(TextView view, String keyword, int color) {
		List<String> list = new ArrayList<String>();
		list.add(keyword);
		setColor(view, list, color, null, null);
	}

	public static void setColor(TextView view, List<String> keyword, int color, Drawable drawable, String holder) {
		if (view == null || keyword == null || keyword.isEmpty()) {
			return;
		}
		String temp = view.getText().toString();
		Spannable spannable = new SpannableString(temp);

		for (String key : keyword) {
			Pattern p = getAtPattern(key);
			Matcher m = p.matcher(temp);

			while (m.find()) {
				ForegroundColorSpan span = new ForegroundColorSpan(color);
				spannable.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}

		if (drawable != null && !TextUtils.isEmpty(holder)) {
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
			int start = temp.indexOf(holder);
			if (start >= 0) {
				spannable.setSpan(new ImageSpan(drawable, DynamicDrawableSpan.ALIGN_BASELINE), start, start + holder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}

		view.setText(spannable);
	}

	private static Pattern getAtPattern(String keyword) {
		Pattern atpattern = Pattern.compile(Pattern.quote(keyword));
		return atpattern;
	}
}

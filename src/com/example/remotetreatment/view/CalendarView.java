package com.example.remotetreatment.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.GlobalApplication;
import com.example.remotetreatment.R;
import com.example.remotetreatment.model.ViewHolder.CalendarHolder;
import com.example.remotetreatment.model.Week;
import com.example.remotetreatment.util.DateUtil;
import com.example.remotetreatment.util.Util;

@SuppressLint("NewApi")
public class CalendarView extends LinearLayout {
	List<Week> mDayList = new ArrayList<Week>();
	List<View> mViewList = new ArrayList<View>();

	// private String checkDayString = "";
	private List<String> dates;

	private int LINE_BACKGROUND_COLOR = GlobalApplication.mApp.getResources().getColor(R.color.gray_d9);
	private int HEADER_BACKGROUND_COLOR = GlobalApplication.mApp.getResources().getColor(R.color.gray_aa);
	private int VIEW_BACKGROUND_COLOR = GlobalApplication.mApp.getResources().getColor(R.color.white);
	private int IN_AREA_TEXT_COLOR = GlobalApplication.mApp.getResources().getColor(R.color.gray_33);
	private int OUT_AREA_TEXT_COLOR = GlobalApplication.mApp.getResources().getColor(R.color.gray_cc);
	private int HEADER_TEXT_COLOR = GlobalApplication.mApp.getResources().getColor(R.color.gray_66);

	public CalendarView(Context context) {
		super(context, null, 0);
		init();
	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		init();
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		try {
			removeAllViews();
			mDayList.clear();
			mViewList.clear();

			setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
				}
			});
			setOrientation(VERTICAL);
			setBackgroundColor(VIEW_BACKGROUND_COLOR);

			addHeader();
			addDays();

			addViews();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addViews() {
		LinearLayout line = null;
		for (int i = 0; i < mDayList.size(); i++) {
			if (i % 7 == 0) {
				line = new LinearLayout(getContext());
				addView(line);

				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
				ImageView lineView = new ImageView(GlobalApplication.mApp);
				lineView.setBackgroundColor(LINE_BACKGROUND_COLOR);
				addView(lineView, lp);
			}
			View v = getView(i);
			line.addView(v);
		}
	}

	private void addHeader() {
		mDayList.add(Week.getHeader(0));
		mDayList.add(Week.getHeader(1));
		mDayList.add(Week.getHeader(2));
		mDayList.add(Week.getHeader(3));
		mDayList.add(Week.getHeader(4));
		mDayList.add(Week.getHeader(5));
		mDayList.add(Week.getHeader(6));
	}

	private void addDays() {
		try {
			Date today = new Date();
			int currentDay = DateUtil.getWeek(new Date());
			for (int i = -(currentDay - 1); i < 0; i++) {
				Date date = DateUtil.add(today, Calendar.DATE, i);
				addWeek(date);
			}
			for (int i = 0; i <= 35 - currentDay; i++) {
				Date date = DateUtil.add(today, Calendar.DATE, i);
				addWeek(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addWeek(Date date) {
		int weekday = DateUtil.getWeek(date);
		int dateDay = DateUtil.getDay(date);
		Week week = new Week();
		week.setDateString(DateUtil.getDate(date));
		week.setDate(dateDay);
		week.setWeekday(weekday);
		mDayList.add(week);
	}

	public View getView(int pos) {
		final View view = LayoutInflater.from(GlobalApplication.mApp).inflate(R.layout.item_day, null);
		CalendarHolder h = new CalendarHolder(view);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.weight = 1;
		final Week week = mDayList.get(pos);
		if (dates != null && dates.contains(week.getDateString())) {
			h.text.setTextColor(IN_AREA_TEXT_COLOR);
		} else {
			h.text.setTextColor(OUT_AREA_TEXT_COLOR);
		}
		if (week.isHeader()) {
			h.text.setText(Base.WEEKS_HEADER[week.getWeekday()]);
			h.text.setTextColor(HEADER_TEXT_COLOR);
			h.text.setTextSize(14);
			lp.bottomMargin = 1;
			lp.height = Util.dip2pxInt(32);
			view.setBackgroundColor(HEADER_BACKGROUND_COLOR);
		} else {
			h.text.setText(String.valueOf(week.getDate()));
			h.text.setTextSize(14);
			lp.height = Util.dip2pxInt(38);
		}
		view.setTag(week);
		mViewList.add(view);

		view.setLayoutParams(lp);

		if (dates != null && dates.contains(week.getDateString()) && !week.isHeader()) {
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// clearSelection(CalendarView.this);
					// view.setSelected(true);
					// String oldStr = checkDayString;
					String checkDayString = week.getDateString();
					// close();
					// try {
					// if (!oldStr.equals(checkDayString)) {
					// // refreshView();
					if (onCheckedChangedListener != null) {
						onCheckedChangedListener.onCheckedChanged(checkDayString);
					}
					// }
					// // if (!oldStr.equals(checkDayString) && onCheckedChangedListener != null) {
					// // }
					// } catch (Exception e) {
					// e.printStackTrace();
					// }
				}
			});
		} else {
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
				}
			});
		}

		return view;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
		init();
		// for (View v : mViewList) {
		// if (v == null) {
		// continue;
		// }
		// Week week = (Week) v.getTag();
		// if (week == null) {
		// continue;
		// }
		// if (week.isHeader()) {
		// continue;
		// }
		//
		// View dataFlag = v.findViewById(R.id.data_flag);
		// if (dataSet != null && dataSet.contains(week.getDateString())) {
		// dataFlag.setVisibility(View.VISIBLE);
		// } else {
		// dataFlag.setVisibility(View.INVISIBLE);
		// }
		// }
	}

	OnCheckedChangedListener onCheckedChangedListener;

	public OnCheckedChangedListener getOnCheckedChangedListener() {
		return onCheckedChangedListener;
	}

	public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
		this.onCheckedChangedListener = onCheckedChangedListener;
	}

	public static interface OnCheckedChangedListener {
		public void onCheckedChanged(String date);
	}
}

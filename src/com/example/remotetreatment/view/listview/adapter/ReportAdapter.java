package com.example.remotetreatment.view.listview.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Report;
import com.example.remotetreatment.model.ViewHolder.ReportHolder;

public class ReportAdapter extends AbsListAdapter<Report> {

	private Context mContext;

	public ReportAdapter(Context context) {
		super(context);
		this.mContext = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			convertView = ((LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_report, null);

			Report r1 = getReport(position * 2);
			Report r2 = getReport(position * 2 + 1);

			// final Presc r = getItem(position);
			final ReportHolder h = new ReportHolder(convertView);

			if (r1 != null) {
				h.layoutReport1.setVisibility(View.VISIBLE);
				h.title1.setText(r1.getTitle());
				h.time1.setText(r1.getTime());

				h.layoutReport1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});
			} else {
				h.layoutReport1.setVisibility(View.INVISIBLE);
			}
			if (r2 != null) {
				h.layoutReport2.setVisibility(View.VISIBLE);
				h.title2.setText(r2.getTitle());
				h.time2.setText(r2.getTime());

				h.layoutReport2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});
			} else {
				h.layoutReport2.setVisibility(View.INVISIBLE);
			}
			h.layoutItem.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}

	private Report getReport(int pos) {
		Report report = null;
		try {
			return getItem(pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return report;
	}

	@Override
	public int getCount() {
		if (getList() == null) {
			return 0;
		}
		return getList().size() / 2;
	}
}
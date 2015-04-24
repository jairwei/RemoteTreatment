package com.example.remotetreatment.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.remotetreatment.R;

public class ViewHolder {
	public static class CalendarHolder {
		public Week week;
		public TextView text;
		public ImageView dataFlag;

		public CalendarHolder(View view) {
			text = (TextView) view.findViewById(R.id.text);
			dataFlag = (ImageView) view.findViewById(R.id.data_flag);
		}

		public boolean isError() {
			if (text == null) {
				return true;
			}
			return false;
		}
	}

	public static class DoctorHolder {
		public View layoutItem;
		public View layoutAbbr;

		public ImageView avatar;
		public TextView name;
		public TextView title;
		public TextView dept;
		public TextView hospital;
		public TextView education;
		public TextView fee;
		public RatingBar star;
		public TextView butnReserve;
		public TextView butnVisit;

		public DoctorHolder(View v) {
			layoutItem = v.findViewById(R.id.layout_item);
			layoutAbbr = v.findViewById(R.id.layout_abbr);

			avatar = (ImageView) v.findViewById(R.id.avatar);
			name = (TextView) v.findViewById(R.id.name);
			title = (TextView) v.findViewById(R.id.title);
			dept = (TextView) v.findViewById(R.id.dept);
			hospital = (TextView) v.findViewById(R.id.hospital);
			education = (TextView) v.findViewById(R.id.education);
			fee = (TextView) v.findViewById(R.id.fee);
			star = (RatingBar) v.findViewById(R.id.star);
			butnReserve = (TextView) v.findViewById(R.id.butn_reserve);
			butnVisit = (TextView) v.findViewById(R.id.butn_visit);
		}
	}

	public static class ReserveHolder {
		public View layoutItem;

		public ImageView avatar;
		public TextView doctorName;
		public TextView time;
		public TextView status;
		public TextView butnAction;

		public ReserveHolder(View v) {
			layoutItem = v.findViewById(R.id.layout_item);
			avatar = (ImageView) v.findViewById(R.id.avatar);
			doctorName = (TextView) v.findViewById(R.id.name);
			time = (TextView) v.findViewById(R.id.time);
			status = (TextView) v.findViewById(R.id.status);
			butnAction = (TextView) v.findViewById(R.id.butn_action);
		}
	}

	public static class PrescHolder {
		public View layoutItem;

		public ImageView avatar;
		public TextView doctorName;
		public TextView time;
		public TextView result;
		public TextView butnShowPresc;

		public PrescHolder(View v) {
			layoutItem = v.findViewById(R.id.layout_item);
			avatar = (ImageView) v.findViewById(R.id.avatar);
			doctorName = (TextView) v.findViewById(R.id.name);
			time = (TextView) v.findViewById(R.id.time);
			result = (TextView) v.findViewById(R.id.result);
			butnShowPresc = (TextView) v.findViewById(R.id.butn_show_presc);
		}
	}

	public static class ReportHolder {
		public View layoutItem;

		public View layoutReport1;
		public View layoutReport2;

		public ImageView image1;
		public TextView title1;
		public TextView time1;

		public ImageView image2;
		public TextView title2;
		public TextView time2;

		public ReportHolder(View v) {
			layoutItem = v.findViewById(R.id.layout_item);
			layoutReport1 = v.findViewById(R.id.layout_report1);
			layoutReport2 = v.findViewById(R.id.layout_report2);

			image1 = (ImageView) layoutReport1.findViewById(R.id.image);
			time1 = (TextView) layoutReport1.findViewById(R.id.time);
			title1 = (TextView) layoutReport1.findViewById(R.id.title);

			image2 = (ImageView) layoutReport2.findViewById(R.id.image);
			time2 = (TextView) layoutReport2.findViewById(R.id.time);
			title2 = (TextView) layoutReport2.findViewById(R.id.title);
		}
	}
}

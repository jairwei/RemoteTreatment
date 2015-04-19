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
		public TextView base;
		public TextView hospital;
		public TextView fee;
		public RatingBar star;
		public TextView butnReserve;
		public TextView butnVisit;

		public DoctorHolder(View v) {
			layoutItem = v.findViewById(R.id.layout_item);
			layoutAbbr = v.findViewById(R.id.layout_abbr);

			avatar = (ImageView) v.findViewById(R.id.avatar);
			base = (TextView) v.findViewById(R.id.base);
			hospital = (TextView) v.findViewById(R.id.hospital);
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
			doctorName = (TextView) v.findViewById(R.id.doctor_name);
			time = (TextView) v.findViewById(R.id.time);
			status = (TextView) v.findViewById(R.id.status);
			butnAction = (TextView) v.findViewById(R.id.butn_action);
		}
	}
}

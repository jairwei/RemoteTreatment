package com.example.remotetreatment.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.remotetreatment.R;

public class ViewHolder {
	public static class DoctorHolder {
		public View layoutItem;
		public View layoutAbbr;
		public View layoutDetail;
		public View layoutReserve;
		public View layoutConfirm;

		public ImageView avatar;
		public TextView base;
		public TextView hospital;
		public TextView fee;
		public RatingBar star;
		public TextView butnReserve;
		public TextView butnVisit;

		public TextView expert;
		public TextView partTime;
		public TextView intro;

		public DoctorHolder(View v) {
			layoutItem = v.findViewById(R.id.layout_item);
			layoutAbbr = v.findViewById(R.id.layout_abbr);
			layoutDetail = v.findViewById(R.id.layout_detail);
			layoutReserve = v.findViewById(R.id.layout_reserve);
			layoutConfirm = v.findViewById(R.id.layout_confirm);

			avatar = (ImageView) v.findViewById(R.id.avatar);
			base = (TextView) v.findViewById(R.id.base);
			hospital = (TextView) v.findViewById(R.id.hospital);
			fee = (TextView) v.findViewById(R.id.fee);
			star = (RatingBar) v.findViewById(R.id.star);
			butnReserve = (TextView) v.findViewById(R.id.butn_reserve);
			butnVisit = (TextView) v.findViewById(R.id.butn_visit);

			expert = (TextView) v.findViewById(R.id.expert);
			partTime = (TextView) v.findViewById(R.id.part_time);
			intro = (TextView) v.findViewById(R.id.intro);
		}
	}

	public static class ReserveHolder {
		public View layoutItem;
		public View layoutButtons;

		public TextView doctorName;
		public TextView time;
		public TextView status;

		public ReserveHolder(View v) {
			layoutItem = v.findViewById(R.id.layout_item);
			layoutButtons = v.findViewById(R.id.layout_abbr);
			doctorName = (TextView) v.findViewById(R.id.doctor_name);
			time = (TextView) v.findViewById(R.id.time);
			status = (TextView) v.findViewById(R.id.status);
		}
	}
}

package com.example.remotetreatment.view.listview.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.model.ViewHolder.DoctorHolder;
import com.example.remotetreatment.util.IntentUtil;

public class DoctorAdapter extends AbsListAdapter<Doctor> {

	private Context mContext;

	public DoctorAdapter(Context context) {
		super(context);
		this.mContext = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			convertView = ((LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_doctor, null);

			final Doctor d = getItem(position);
			final DoctorHolder h = new DoctorHolder(convertView);

			h.base.setText(d.getName() + " " + d.getTitle() + " " + d.getEducation());
			h.hospital.setText(d.getHospital() + " " + d.getDept());
			h.fee.setText(mContext.getString(R.string.regist_fee, d.getRegistFee()));

			h.star.setRating(d.getStar());
			// h.expert.setText(d.getExpert());
			// h.partTime.setText(d.getPartTime());
			// h.intro.setText(d.getIntro());

			h.butnReserve.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showReserve(d);
				}
			});
			h.butnVisit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					visit(d);
				}
			});
			h.layoutItem.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showDetail(d);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}

	private void showDetail(Doctor doctor) {
		IntentUtil.showDoctorDetail(mContext, doctor);
		// Intent intent = new Intent(Base.ACTION_SHOW_DOCTOR_DETAIL);
		// intent.putExtra(Base.EXTRA_DOCTOR, doctor);
		// mContext.sendBroadcast(intent);
	}

	private void showReserve(Doctor doctor) {
		IntentUtil.showDoctorReserve(mContext, doctor);
		// Intent intent = new Intent(Base.ACTION_SHOW_DOCTOR_RESERVE);
		// intent.putExtra(Base.EXTRA_DOCTOR, doctor);
		// mContext.sendBroadcast(intent);
	}

	private void visit(Doctor doctor) {
	}
}
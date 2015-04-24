package com.example.remotetreatment.view.listview.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Presc;
import com.example.remotetreatment.model.ViewHolder.PrescHolder;

public class PrescAdapter extends AbsListAdapter<Presc> {

	private Context mContext;

	public PrescAdapter(Context context) {
		super(context);
		this.mContext = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			convertView = ((LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_presc, null);

			final Presc r = getItem(position);
			final PrescHolder h = new PrescHolder(convertView);

			// ImageLoaderUtil.display(r.getDoctor().getAvatar(), h.avatar);
			h.doctorName.setText(r.getDoctor().getName());
			h.time.setText(r.getTime());
			h.result.setText(r.getResult());

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
}
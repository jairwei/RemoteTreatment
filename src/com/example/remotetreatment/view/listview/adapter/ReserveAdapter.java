package com.example.remotetreatment.view.listview.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Reserve;
import com.example.remotetreatment.model.ViewHolder.ReserveHolder;
import com.example.remotetreatment.util.IntentUtil;

public class ReserveAdapter extends AbsListAdapter<Reserve> {

	private Context mContext;

	public ReserveAdapter(Context context) {
		super(context);
		this.mContext = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			convertView = ((LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_reserve, null);

			final Reserve r = getItem(position);
			final ReserveHolder h = new ReserveHolder(convertView);

			// ImageLoaderUtil.display(r.getDoctor().getAvatar(), h.avatar);
			h.doctorName.setText(r.getDoctor().getName());
			h.time.setText(r.getTime());

			if (r.getStatus() == Reserve.STATUS_RESERVE) {
				h.butnAction.setText(R.string.butn_visit);
				h.status.setText(R.string.status_reserve);
				h.butnAction.setVisibility(View.VISIBLE);
			} else if (r.getStatus() == Reserve.STATUS_NO_PAY) {
				h.butnAction.setText(R.string.butn_pay);
				h.status.setText(R.string.status_no_pay);
				h.butnAction.setVisibility(View.VISIBLE);
			} else if (r.getStatus() == Reserve.STATUS_COMPLATE) {
				h.butnAction.setText(R.string.butn_comment);
				h.status.setText(R.string.status_complate);
				h.butnAction.setVisibility(View.VISIBLE);
			} else if (r.getStatus() == Reserve.STATUS_CANCEL) {
				h.butnAction.setText("");
				h.status.setText(R.string.status_cancel);
				h.butnAction.setVisibility(View.GONE);
			}

			h.layoutItem.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					IntentUtil.showWriteComment(mContext, r);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}
}
package com.example.remotetreatment.view.listview.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Test;

public class TestAdapter extends AbsListAdapter<Test> {

	private Context mContext;

	public TestAdapter(Context context) {
		super(context);
		this.mContext = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Test t = getItem(position);

		convertView = ((LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_test, null);
		TextView id = (TextView) convertView.findViewById(R.id.id);
		TextView name = (TextView) convertView.findViewById(R.id.name);

		id.setText(t.id);
		name.setText(t.name);

		return convertView;
	}
}
package com.example.remotetreatment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.R;
import com.example.remotetreatment.model.Doctor;
import com.example.remotetreatment.model.Reserve;
import com.example.remotetreatment.util.Toaster;

public class WriteCommentActivity extends Activity {
	private Reserve mReserve;

	private ImageView mAvatar;
	private TextView mName;
	private TextView mTime;
	private TextView mHospital;
	private TextView mDept;
	private RatingBar mStarYishu;
	private RatingBar mStarTaidu;
	private EditText mContent;
	private View mButnWriteComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_write_comment);

		mReserve = (Reserve) getIntent().getSerializableExtra(Base.EXTRA_RESERVE);
		if (mReserve == null) {
			Toaster.show("reserve is null.");
			finish();
			return;
		}

		initHeader();
		initView();
	}

	private void initView() {
		final Reserve r = mReserve;
		final Doctor d = r.getDoctor();

		mAvatar = (ImageView) findViewById(R.id.avatar);
		mName = (TextView) findViewById(R.id.name);
		mTime = (TextView) findViewById(R.id.time);
		mHospital = (TextView) findViewById(R.id.hospital);
		mDept = (TextView) findViewById(R.id.dept);
		mStarYishu = (RatingBar) findViewById(R.id.star_yishu);
		mStarTaidu = (RatingBar) findViewById(R.id.star_taidu);
		mContent = (EditText) findViewById(R.id.content);
		mButnWriteComment = findViewById(R.id.butn_write_comment);

		mName.setText(d.getName());
		mTime.setText(r.getTime());
		mHospital.setText(d.getHospital());
		mDept.setText(d.getDept());

		mButnWriteComment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	TextView mHeaderButnLeft;
	TextView mHeaderTitle;

	private void initHeader() {
		mHeaderButnLeft = (TextView) findViewById(R.id.header_butn_left);
		mHeaderButnLeft.setText(R.string.butn_back);
		mHeaderButnLeft.setVisibility(View.VISIBLE);
		mHeaderButnLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		mHeaderTitle = (TextView) findViewById(R.id.header_title);
		mHeaderTitle.setText(R.string.header_write_comment);
	}

}

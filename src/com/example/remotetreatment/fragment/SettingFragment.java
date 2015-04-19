package com.example.remotetreatment.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.remotetreatment.R;
import com.example.remotetreatment.model.UserAccount;
import com.example.remotetreatment.util.AccountUtil;
import com.example.remotetreatment.util.ImageLoaderUtil;

@SuppressLint("ValidFragment")
public class SettingFragment extends BaseFragment {

	final static String TAG = SettingFragment.class.getSimpleName();
	private View mRoot;

	private ImageView mAvatar;
	private TextView mName;
	private TextView mGender;
	private TextView mIdType;
	private TextView mIdNumber;
	private TextView mPhone;
	private TextView mEmail;
	private View mButnLogout;

	public static SettingFragment newInstance() {
		return newInstance(null);
	}

	public static SettingFragment newInstance(Bundle args) {
		SettingFragment f = new SettingFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mRoot = mAct.getLayoutInflater().inflate(R.layout.fragment_setting, null);

		initFragment();
		return mRoot;
	}

	@Override
	public void initFragment() {
		if (inited) {
			return;
		}
		super.initFragment();

		initView();
	}

	private void initView() {

		mAvatar = (ImageView) mRoot.findViewById(R.id.avatar);
		mName = (TextView) mRoot.findViewById(R.id.name);
		mGender = (TextView) mRoot.findViewById(R.id.gender);
		mIdType = (TextView) mRoot.findViewById(R.id.id_type);
		mIdNumber = (TextView) mRoot.findViewById(R.id.id_number);
		mPhone = (TextView) mRoot.findViewById(R.id.phone);
		mEmail = (TextView) mRoot.findViewById(R.id.email);
		mButnLogout = mRoot.findViewById(R.id.butn_logout);

		UserAccount user = AccountUtil.getUserAccount();
		if (user == null) {
			return;
		}

		ImageLoaderUtil.display(user.getAvatar(), mAvatar);
		mName.setText(user.getName());
		mGender.setText(user.getGender());
		mIdType.setText(mAct.getResources().getStringArray(R.array.id_type)[user.getIdType()]);
		mIdNumber.setText(user.getIdNumber());
		mPhone.setText(user.getPhone());
		mEmail.setText(user.getEmail());

		mButnLogout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				logout();
			}
		});
	}

	protected void logout() {

	}
}
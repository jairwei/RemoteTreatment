package com.example.remotetreatment.util;

import android.content.Context;
import android.content.Intent;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.activity.DoctorDetailActivity;
import com.example.remotetreatment.activity.DoctorReserveActivity;
import com.example.remotetreatment.model.Doctor;

public class IntentUtil {
	public static void showDoctorDetail(Context c, Doctor doctor) {
		Intent intent = new Intent(c, DoctorDetailActivity.class);
		intent.putExtra(Base.EXTRA_DOCTOR, doctor);
		c.startActivity(intent);
	}

	public static void showDoctorReserve(Context c, Doctor doctor) {
		Intent intent = new Intent(c, DoctorReserveActivity.class);
		intent.putExtra(Base.EXTRA_DOCTOR, doctor);
		c.startActivity(intent);
	}
}

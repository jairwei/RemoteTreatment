package com.example.remotetreatment;

public class Base {
	public static final String TAG = "RemoteTreatment";

	public static boolean DEBUGGABLE = true;

	public static final int TRUE = 1;
	public static final int FALSE = 0;

	public static final int PAGE_SIZE = 10;
	public static int WINDOW_WIDTH = 480;
	public static int WINDOW_HEIGHT = 800;

	public static final String URL_HOST = "http://api.my445.com/";

	/** http建立连接超时时间 */
	public static final int HTTP_TIMEOUT_CONNECTION = 1000 * 30;
	/** http传输数据超时时间 */
	public static final int HTTP_TIMEOUT_SOCKET = 1000 * 60;
	/** http建立连接超时时间 */
	public static final int HTTP_TIMEOUT_CONNECTION_LONG_TIME = 1000 * 60;
	/** http传输数据超时时间 */
	public static final int HTTP_TIMEOUT_SOCKET_LONG_TIME = 1000 * 60 * 3;

	public static final String ACTION_SHOW_DOCTOR_DETAIL = "com.remotetreatment.actions.show_doctor_detail";
	public static final String ACTION_SHOW_DOCTOR_RESERVE = "com.remotetreatment.actions.show_doctor_reserve";
	public static final String ACTION_SHOW_DOCTOR_CONFIRM = "com.remotetreatment.actions.show_doctor_confirm";
	public static final String ACTION_SHOW_DOCTOR_LIST = "com.remotetreatment.actions.show_doctor_list";

	public static final String EXTRA_DOCTOR = "EXTRA_DOCTOR";
}

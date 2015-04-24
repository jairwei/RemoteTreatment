package com.example.remotetreatment;

public class Base {
	public static final String TAG = "RemoteTreatment";

	public static boolean DEBUGGABLE = true;

	public static final int TRUE = 1;
	public static final int FALSE = 0;

	public static final int PAGE_SIZE = 10;
	public static int WINDOW_WIDTH = 480;
	public static int WINDOW_HEIGHT = 800;

	public static final int ID_TYPE = 0;// 身份证
	public static final int ID_SOLDIER = 1;// 军官证
	public static final int ID_PASSPORT = 2;// 护照

	public static final String URL_HOST = "http://api.my445.com/";

	/** http建立连接超时时间 */
	public static final int HTTP_TIMEOUT_CONNECTION = 1000 * 30;
	/** http传输数据超时时间 */
	public static final int HTTP_TIMEOUT_SOCKET = 1000 * 60;
	/** http建立连接超时时间 */
	public static final int HTTP_TIMEOUT_CONNECTION_LONG_TIME = 1000 * 60;
	/** http传输数据超时时间 */
	public static final int HTTP_TIMEOUT_SOCKET_LONG_TIME = 1000 * 60 * 3;

	// public static final String ACTION_SHOW_DOCTOR_DETAIL = "com.remotetreatment.actions.show_doctor_detail";
	// public static final String ACTION_SHOW_DOCTOR_RESERVE = "com.remotetreatment.actions.show_doctor_reserve";
	// public static final String ACTION_SHOW_DOCTOR_CONFIRM = "com.remotetreatment.actions.show_doctor_confirm";
	public static final String ACTION_ORDER_CONFIRMED = "com.remotetreatment.actions.order_confirmed";

	public static final String EXTRA_RESERVE = "EXTRA_RESERVE";
	public static final String EXTRA_DOCTOR = "EXTRA_DOCTOR";

	public static final String ACCOUNT_FILE = "user_account.ser";

	/** 文件存储目录 */
	public static final String PATH = "/remotetreatment/";
	public static final String PATH_SERIAL = PATH + "serial/";

	public static String[] WEEKS_HEADER = new String[] { "日", "一", "二", "三", "四", "五", "六" };
	public static String[] WEEKS = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	public static final int STATUS_CLOSED = 1;
	public static final int STATUS_CLOSING = 2;
	public static final int STATUS_OPENED = 3;
	public static final int STATUS_OPENING = 4;

}

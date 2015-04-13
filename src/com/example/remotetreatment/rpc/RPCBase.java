package com.example.remotetreatment.rpc;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.exception.ApiException;
import com.example.remotetreatment.exception.RpcException;
import com.example.remotetreatment.util.Util;

public class RPCBase {

	protected static String TAG = "HTTP";

	protected Context mContext;
	protected byte sBuffer[] = new byte[512];

	protected static final String BOUNDARY = "---------7d4a6d158c9";
	protected static final String MULTIPART_FORM_DATA = "multipart/form-data";

	/**
	 * 判断当前网络模式是否为CMWAP
	 * 
	 * @param context
	 * @return
	 */
	protected boolean isCmwapNet(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWrokInfo = manager.getActiveNetworkInfo();
		if (netWrokInfo == null || !netWrokInfo.isAvailable()) {
			return false;
		} else if (netWrokInfo.getTypeName().equals("mobile") && netWrokInfo.getExtraInfo() != null && netWrokInfo.getExtraInfo().equals("cmwap")) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 * @throws RpcException
	 */
	protected HttpClient getHttpClient(int soTimeout, int connectTimeout) throws RpcException {
		DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
		HttpParams httpparams = defaulthttpclient.getParams();
		try {
			if (isCmwapNet(mContext)) {
				HttpHost proxy = new HttpHost("10.0.0.172", 80, "http");
				httpparams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			}

			HttpConnectionParams.setConnectionTimeout(httpparams, connectTimeout);
			HttpConnectionParams.setSoTimeout(httpparams, soTimeout);
			HttpConnectionParams.setTcpNoDelay(httpparams, true);

			httpparams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			httpparams.setParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return defaulthttpclient;
	}

	protected HttpUriRequest getHttpUriRequest(String urlPath, String method) throws RpcException {
		if (method.equalsIgnoreCase(HttpGet.METHOD_NAME)) {
			// String param = URLEncodedUtils.format(listPair, "UTF-8");
			// URI uri;
			// try {
			// uri = URIUtils.createURI("http", Base.URL_HOST, 80, urlPath, null, null);
			// } catch (URISyntaxException e) {
			// throw new RpcException(e);
			// }
			HttpGet httpget = new HttpGet(Base.URL_HOST + urlPath);

			// Util.logRequst(httpget);
			return httpget;
		} else {
			HttpPost httppost = new HttpPost(Base.URL_HOST + urlPath);
			// try {
			// if (listPair != null) {
			// UrlEncodedFormEntity urlencodedformentity = new UrlEncodedFormEntity(listPair, "UTF-8");
			// httppost.setEntity(urlencodedformentity);
			// Util.logRequst(httppost);
			// }
			// } catch (UnsupportedEncodingException e) {
			// throw new RpcException(e);
			// }
			// Util.logRequst(httppost);
			return httppost;
		}
	}

	public String execute(String urlPath, HttpEntity entity) throws ApiException, ConnectTimeoutException, RpcException {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = "";
		try {
			httpClient = getHttpClient(Base.HTTP_TIMEOUT_CONNECTION, Base.HTTP_TIMEOUT_SOCKET);
			httpPost = (HttpPost) getHttpUriRequest(urlPath, HttpPost.METHOD_NAME);

			httpPost.setEntity(entity);
			Util.log(TAG, httpPost.getURI().toString());
			if (entity instanceof StringEntity) {
				Util.log(TAG, EntityUtils.toString(entity));
			}

			HttpResponse response = httpClient.execute(httpPost);
			result = JSONTokener(EntityUtils.toString(response.getEntity()));
			Util.log(TAG, result);

			if (!TextUtils.isEmpty(result)) {
				result = result.substring(result.indexOf("{"));
			}
			// JuanRspData rsp = JsonUtil.getObject(result, JuanRspData.class);
			// if (rsp == null) {
			// throw new RpcException("parse JuanRspData is null.");
			// }
			// if (isError(rsp)) {
			// ApiException apie = new ApiException();
			// apie.setErrorCode(rsp.getResult());
			// apie.setErrorDesc(rsp.getMsg());
			// throw apie;
			// }
			return result;
		} catch (IOException io) {
			io.printStackTrace();
			throw new RpcException("MultiPartEntity execute!!", io);
		} finally {
			if (httpClient != null)
				httpClient.getConnectionManager().shutdown();
		}
	}

	public String executeLongTime(String urlPath, HttpEntity entity) throws ApiException, ConnectTimeoutException, RpcException {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = "";
		try {
			httpClient = getHttpClient(Base.HTTP_TIMEOUT_CONNECTION_LONG_TIME, Base.HTTP_TIMEOUT_SOCKET_LONG_TIME);
			httpPost = (HttpPost) getHttpUriRequest(urlPath, HttpPost.METHOD_NAME);

			httpPost.setEntity(entity);
			Util.log(TAG, httpPost.getURI().toString());
			if (entity instanceof StringEntity) {
				Util.log(TAG, EntityUtils.toString(entity));
			}

			HttpResponse response = httpClient.execute(httpPost);
			result = JSONTokener(EntityUtils.toString(response.getEntity()));
			Util.log(TAG, result);

			if (!TextUtils.isEmpty(result)) {
				result = result.substring(result.indexOf("{"));
			}
			// JuanRspData rsp = JsonUtil.getObject(result, JuanRspData.class);
			// if (rsp == null) {
			// throw new RpcException("parse JuanRspData is null.");
			// }
			// if (isError(rsp)) {
			// ApiException apie = new ApiException();
			// apie.setErrorCode(rsp.getResult());
			// apie.setErrorDesc(rsp.getMsg());
			// throw apie;
			// }
			return result;
		} catch (IOException io) {
			io.printStackTrace();
			throw new RpcException("MultiPartEntity execute!!", io);
		} finally {
			if (httpClient != null)
				httpClient.getConnectionManager().shutdown();
		}
	}

	// private boolean isError(JuanRspData rsp) {
	// if (rsp == null || rsp.getResult() != 0) {
	// return true;
	// }
	// return false;
	// }

	protected static String JSONTokener(String in) {
		// consume an optional byte order mark (BOM) if it exists
		if (in != null && in.startsWith("\ufeff")) {
			in = in.substring(1);
		}
		return in;
	}
}
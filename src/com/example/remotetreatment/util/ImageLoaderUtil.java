package com.example.remotetreatment.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;

public class ImageLoaderUtil {

	public static void display(String uri, ImageView imageView) {
		ImageLoader.getInstance().displayImage(uri, imageView, Options_Memory);
	}

	public static void displayAvatar(String uri, ImageView imageView, ImageLoadingListener imageLoadingListener) {
		// if (imageView == null) {
		// return;
		// }
		// if (TextUtils.isEmpty(uri)) {
		// imageView.setImageResource(R.drawable.morentouxiang);
		// return;
		// }
		// try {
		// if (uri.startsWith(AppConfig.DEFAULT_AVATAR_PREFIX)) {
		// int i = Integer.parseInt(uri.replaceFirst(AppConfig.DEFAULT_AVATAR_PREFIX, ""));
		// int resId = Base.Avatars.get(i);
		// imageView.setImageResource(resId);
		// if (imageLoadingListener != null) {
		// imageLoadingListener.onLoadingComplete(uri, imageView, null);
		// }
		// return;
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// if (!uri.toLowerCase().startsWith("http")) {
		// uri = Uri.fromFile(new File(uri)).toString();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// uri = Util.toSmall(uri);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		ImageLoader.getInstance().displayImage(uri, imageView, Options_Memory, imageLoadingListener);
	}

	public static void displayAvatar(String uri, ImageView imageView) {
		displayAvatar(uri, imageView, null);
	}

	//
	// public static void displayRound(String uri, ImageView imageView) {
	// ImageLoader.getInstance().displayImage(uri, imageView, Options_Round_Memory);
	// }
	//
	// public static void displayMonitorRound(String uri, ImageView imageView, ImageLoadingListener imageLoadingListener) {
	// ImageLoader.getInstance().displayImage(uri, imageView, Options_Monitor_Round_Memory, imageLoadingListener);
	// }

	// public static DisplayImageOptions getDefaultImageOptions(int resid) {
	// return new DisplayImageOptions.Builder().showStubImage(resid).cacheOnDisc(true).build();
	// }

	public static ImageSize Size_Guild_Grid = new ImageSize(60, 60);

//	// @formatter:off
//	public static DisplayImageOptions Options_Circle_Avatar = new DisplayImageOptions
//			.Builder()
//			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//			.bitmapConfig(Bitmap.Config.RGB_565)
//			.resetViewBeforeLoading(true)
//			.showStubImage(R.drawable.morentouxiang)
////			.showStubImage(R.drawable.morentouxiang)
//			.showImageOnFail(R.drawable.morentouxiang)
//			.showImageForEmptyUri(R.drawable.morentouxiang)
//			.cacheInMemory(true)
//			.cacheOnDisc(true)
//			.displayer(new CircleBitmapDisplayer())
//			.build();
//	public static DisplayImageOptions Options_Round_Memory = new DisplayImageOptions
//			.Builder()
//			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//			.bitmapConfig(Bitmap.Config.RGB_565)
//			.resetViewBeforeLoading(true)
//			.showStubImage(R.drawable.morentouxiang)
//			.showImageOnFail(R.drawable.morentouxiang)
//			.showImageForEmptyUri(R.drawable.morentouxiang)
//			.cacheInMemory(true)
//			.cacheOnDisc(true)
//			.displayer(new RoundedBitmapDisplayer(Util.dip2pxInt(5)))
//			.build();
//	public static DisplayImageOptions Options_Monitor_Round_Memory = new DisplayImageOptions
//			.Builder()
//			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//			.bitmapConfig(Bitmap.Config.RGB_565)
//			.resetViewBeforeLoading(true)
//			.postProcessor(null)
//			.showStubImage(R.drawable.p1)
//			.showImageOnFail(R.drawable.p1)
//			.showImageForEmptyUri(R.drawable.p1)
//			.cacheInMemory(true)
//			.cacheOnDisc(true)
////			.displayer(new RoundedBitmapDisplayer(Util.dip2pxInt(5)))
//			.build();
	public static DisplayImageOptions Options_Memory = new DisplayImageOptions
			.Builder()
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)
//			.showStubImage(R.drawable.morentouxiang)
//			.showImageOnFail(R.drawable.morentouxiang)
//			.showImageForEmptyUri(R.drawable.morentouxiang)
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.build();
	// @formatter:on

	public static String toLarge(String url) {
		if (TextUtils.isEmpty(url)) {
			return null;
		}
		return url.replaceFirst("/small/", "/large/");
	}

	public static String toSmall(String url) {
		if (TextUtils.isEmpty(url)) {
			return null;
		}
		return url.replaceFirst("/large/", "/small/");
	}
}

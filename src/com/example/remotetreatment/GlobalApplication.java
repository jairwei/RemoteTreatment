package com.example.remotetreatment;

import java.io.File;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class GlobalApplication extends Application {
	public static GlobalApplication mApp;

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			mApp = this;
			try {
				Class.forName("android.os.AsyncTask");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			initImageLoader();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UnlimitedDiscCache DiscCache = null;

	public void initImageLoader() {
		File cacheDir = StorageUtils.getCacheDirectory(mApp);
		DiscCache = new UnlimitedDiscCache(cacheDir);
		// File cacheDir = getImagePath();
		// @formatter:off
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mApp)
		// .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
		// .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
        .threadPoolSize(3) // default
        // .discCacheExtraOptions(800, 800, CompressFormat.PNG, 100,null)
        .threadPriority(Thread.NORM_PRIORITY - 1) // default
        .tasksProcessingOrder(QueueProcessingType.FIFO) // default
        .denyCacheImageMultipleSizesInMemory()
        .memoryCache(new WeakMemoryCache())
        .memoryCacheSizePercentage(13) // default
        .discCache(new UnlimitedDiscCache(cacheDir)) // default
        .discCacheSize(50 * 1024 * 1024)
        .discCacheFileCount(100)
        .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
        .imageDownloader(new BaseImageDownloader(mApp)) // default
        .imageDecoder(new BaseImageDecoder(true)) // default
        .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
        .writeDebugLogs()
        .build();
		// @formatter:on

		ImageLoader.getInstance().init(config);
	}
}
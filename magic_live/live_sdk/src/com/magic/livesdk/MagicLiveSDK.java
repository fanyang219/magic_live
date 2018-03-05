package com.magic.livesdk;

import com.magic.livesdk.core.config.AppConfig;

import android.content.Context;

/**
 * 直播SDK接口类
 * @author xufan
 */
public class MagicLiveSDK {
	 private static final String TAG = MagicLiveSDK.class.getSimpleName();
	 
	 private AppConfig mAppConfig = null;
	 
	 private Context mContext = null;
	 private String mVersion = "0.1.0";
	 
	 private MagicLiveSDK() {}
	 
	 public static MagicLiveSDK getInstance() {
		 return SDKHolder.instance;
	 }
	 
	 public void initSDK(Context context, String appId, String accountType) {
		 this.mContext = context;
		 
		 getAppConfig().setAppId(appId);
		 getAppConfig().setAccountType(accountType);
	 }
	 
	 private static class SDKHolder {
		 private static MagicLiveSDK instance = new MagicLiveSDK();
	 }
	 
	 public Context getAppContext() {
		 return mContext;
	 }
	 
	 public AppConfig getAppConfig() {
		 if(mAppConfig==null) {
			 mAppConfig = new AppConfig();
		 }
		 return mAppConfig;
	 }
	 
	 public String getVersion() {
		 return mVersion;
	 }
}

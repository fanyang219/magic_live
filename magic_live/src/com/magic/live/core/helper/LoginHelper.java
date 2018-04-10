package com.magic.live.core.helper;

import com.magic.live.core.helper.listener.EmptyLoginListener;
import com.magic.live.core.helper.listener.ILoginListener;

public class LoginHelper {
	private static final String TAG = LoginHelper.class.getSimpleName();
	private static final String DEFAULT_NUMBER = "18503087942";
	private static final String DEFAULT_PASSWORD = "123456";
	public static final String SCENE_LOGIN_SUCCESS = "scene_login_success";
	
	private ILoginListener mLoginListener = null;
	
	public void login(String mobile, String password) {
		// TODO µ÷ÓÃHTTP¿ò¼Ü
		if(DEFAULT_NUMBER.equals(mobile)
				&& DEFAULT_PASSWORD.equals(password)) {
			getLoginListener().switchPage(SCENE_LOGIN_SUCCESS);
		}
	}
	
	public void setLoginListener(ILoginListener listener) {
		mLoginListener = listener;
	}

	public ILoginListener getLoginListener() {
		if(mLoginListener==null) {
			mLoginListener = new EmptyLoginListener();
		}
		return mLoginListener;
	}
}

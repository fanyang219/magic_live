package com.magic.live.activity;

import com.magic.live.R;
import com.magic.live.core.helper.LoginHelper;
import com.magic.live.core.helper.listener.ILoginListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements ILoginListener {
	private static final String TAG = LoginActivity.class.getSimpleName();
	
	private EditText mMobileEdit = null;
	private EditText mPasswordEdit = null;
	private Button mLoginButton = null;
	
	private LoginHelper mLoginHelper = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		initView();
		initData();
		attachListener();
	}
	
	protected void onResume() {
		super.onResume();
	}
	
	private void initView() {
		mMobileEdit = (EditText) findViewById(R.id.mobile_edit);
		mPasswordEdit = (EditText) findViewById(R.id.password_edit);
		mLoginButton = (Button) findViewById(R.id.button_login);
	}
	
	private void initData() {
		
	}
	
	private void attachListener() {
		mLoginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				login();	
			}
		});
	}
	
	private void login() {
		getLoginHelper().login(getMobile(), getPassword());
	}
	
	/*******************辅助方法********************/
	private String getMobile() {
		return mMobileEdit.getText().toString().trim();
	}
	
	private String getPassword() {
		return mPasswordEdit.getText().toString().trim();
	}
	
	private LoginHelper getLoginHelper() {
		if(mLoginHelper==null) {
			mLoginHelper = new LoginHelper();
			mLoginHelper.setLoginListener(this);
		}
		return mLoginHelper;
	}

	@Override
	public void switchPage(String scene) {
		if(LoginHelper.SCENE_LOGIN_SUCCESS.equals(scene)) {
			// 跳转到直播间页面
			Intent intent = new Intent(this, LiveActivity.class);
			startActivity(intent);
		}
	}
}

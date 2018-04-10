package com.magic.live.activity;

import android.app.Activity;
import android.widget.Toast;

public class BaseActivity extends Activity {
	public void toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT);
	}
}

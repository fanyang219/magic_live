package com.magic.live.activity;

import com.magic.live.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LiveActivity extends BaseActivity {
	private static final String TAG = LiveActivity.class.getSimpleName();
	private static final int REQUEST_CODE_PREVIEW = 1001;
	
	private LinearLayout mCloseButton = null;
	private ImageView mStartButton = null;
	private ImageView mBackgroundView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_live);
		
		initView();
		initData();
		attachListener();
	}
	

	private void initView() {
		mCloseButton = (LinearLayout) findViewById(R.id.live_close_button);
		mStartButton = (ImageView) findViewById(R.id.live_start_button);
		mBackgroundView = (ImageView) findViewById(R.id.live_background);
	}
	
	private void initData() {
		
	}
	
	private void attachListener() {
		mCloseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 后续优化
				finish();
			}
		});
		
		mStartButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				gotoAudition();
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mBackgroundView.setVisibility(View.GONE);
        if(REQUEST_CODE_PREVIEW == requestCode) {
            Log.d(TAG, "Preview finished, start live now...");
            if(RESULT_OK == resultCode) {
                mStartButton.setVisibility(View.GONE);
                startLive();
            }
        }
    }
	
	private void gotoAudition() {
        Intent auditionIntent = new Intent(LiveActivity.this, AuditionActivity.class);
        startActivityForResult(auditionIntent, REQUEST_CODE_PREVIEW);
        
        mBackgroundView.setVisibility(View.VISIBLE);
    }
	
	private void startLive() {
		
	}
}

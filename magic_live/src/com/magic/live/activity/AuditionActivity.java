package com.magic.live.activity;

import com.magic.live.R;
import com.magic.live.activity.widget.CameraView;
import com.magic.live.core.helper.CameraHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 *  ‘æµ“≥√Ê
 */
public class AuditionActivity extends Activity {
    private static final String TAG = AuditionActivity.class.getSimpleName();

    private CameraView mCameraView = null;
    private LinearLayout mSwitchButton = null;
    private LinearLayout mCloseButton = null;
    private LinearLayout mStartButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audition);

        initView();
        initData();
        attachListener();
    }

    private void initView() {
        mCameraView = (CameraView) findViewById(R.id.audition_camera);
        mSwitchButton = (LinearLayout) findViewById(R.id.switch_layout);
        mCloseButton = (LinearLayout) findViewById(R.id.close_layout);
        mStartButton = (LinearLayout) findViewById(R.id.start_layout);
    }
    
    private void initData() {
    	// ≥ı ºªØcamera
    	CameraHelper.getInstance().init(this);
    	CameraHelper.getInstance().display(mCameraView.getHolder());
    }

    private void attachListener() {
        mSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CameraHelper.getInstance().switchCamera(AuditionActivity.this);
            }
        });

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();

        asyncStartPreview();
    }

	private void asyncStartPreview() {
		Thread openThread = new Thread(new PreviewTask());
        openThread.start();
	}
	
	class PreviewTask implements Runnable {
		@Override
		public void run() {
			try {
                Thread.sleep(200);
                
                CameraHelper.getInstance().startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
}
package com.magic.live.core.helper;

import java.util.List;

import com.magic.live.util.CameraUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Camera辅助类
 * @author xufan
 */
@SuppressLint("NewApi")
public class CameraHelper {
	private static final CameraHelper instance = new CameraHelper();
	private static final int CAMERA_FRONT = 0;
	private static final int CAMERA_BACK = 1;
	
	private int previewWidth = 0;
	private int previewHeight = 0;
	private Context context = null;
	private SurfaceHolder holder = null;
	private int cameraId = CAMERA_FRONT; // 默认前置Camera
	private Camera camera = null;
	private boolean isPreviewing = false;
	
	private CameraHelper() {}

    public static CameraHelper getInstance() {
        return instance;
    }
    
    public void init(Context context) {
    	this.context = context;
    }
    
    public void display(SurfaceHolder holder) {
    	this.holder = holder;
    }
    
    // 试镜接口
    public void startPreview() {
    	// 1.参数校验
    	if(context==null) {
    		Toast.makeText(getContext(),
    				"CameraHelper isn't inited.", Toast.LENGTH_SHORT);
    		return;
    	}
    	if(holder==null) {
    		Toast.makeText(getContext(),
    				"CameraHelper has no holder to display.", Toast.LENGTH_SHORT);
    		return;
    	}
    	
    	// 2.打开Camera
    	openCamera(cameraId);
    	if(camera==null) { // 保护处理
    		return;
    	}
    	
    	// 3.配置Camera
    	configCamera();
    	
    	// 4.预览
    	if(isPreviewing) {
			stopPreview();
		}
		camera.startPreview();
        isPreviewing = true;
    }
    
    private void stopPreview() {
    	if(camera == null) { // 保护处理
    		return;
        }
    	
    	camera.stopPreview();
        isPreviewing = false;
    }
    
    private void openCamera(int cameraId) {
    	try {
    		camera = Camera.open(cameraId);
        } catch(Exception e) {
        	camera = null;
            e.printStackTrace();
        }
    }
    
    private void configCamera() {
        if(camera==null) { // 保护处理
            return;
        }

        try {
        	camera.setPreviewCallback(null);
        	camera.setDisplayOrientation(90);
            Camera.Parameters params = camera.getParameters();
            List<Camera.Size> cameraSizes = params.getSupportedPreviewSizes();
            Camera.Size properSize = CameraUtils.getPropPreviewSize(
            		cameraSizes, getPreviewWidth(), getPreviewHeight());

            params.setPreviewFormat(ImageFormat.NV21);
            params.setPreviewSize(properSize.width, properSize.height);
            camera.setParameters(params);
            camera.setPreviewDisplay(holder);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setPreviewSize(int previewWidth, int previewHeight) {
    	this.previewWidth = previewWidth;
    	this.previewHeight = previewHeight;
    }
    
	public void release() {
        if(camera==null) { // 保护处理
            return;
        }
        try {
        	camera.setPreviewCallback(null);
        	camera.stopPreview();
        	camera.release();
        	camera = null;
        } catch(Exception e) {
        	camera = null;
            e.printStackTrace();
        }
    }
    
    /********************辅助接口********************/
    private int getPreviewWidth() {
    	if(previewWidth<=0) {
    		previewWidth = getScreenWidth();
    	}
    	return previewWidth;
    }
    
    private int getPreviewHeight() {
    	if(previewHeight<=0) {
    		previewHeight = getScreenHeight();
    	}
    	return previewHeight;
    }
    
    @SuppressWarnings("deprecation")
    private int getScreenWidth() {
        Display display = ((WindowManager) getContext()
        		.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return display.getWidth();
    }

    @SuppressWarnings("deprecation")
    private int getScreenHeight() {
        Display display = ((WindowManager) getContext()
        		.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return display.getHeight();
    }
    
    private Context getContext() {
    	return context;
    }
}

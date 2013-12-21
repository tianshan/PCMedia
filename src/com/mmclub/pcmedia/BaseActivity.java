package com.mmclub.pcmedia;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * 基类
 * @author tianshan
 *
 */
public class BaseActivity extends Activity{
	
	private Toast toast;
	private UDPSocket udpcon;
	public String TAG;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	public void SendMsg(char c) {
    	if (udpcon != null) {
    		if ( !udpcon.SendByte((byte)(c & 0xff)) )
    			Log.i(TAG, "error");
    	}
    	else
    		showToast("还没有连接");
    }
	
	public void SendCoord(float offsetX, float offsetY) {
		if (udpcon != null) {
    		if ( !udpcon.SendCoord(offsetX, offsetY))
    			Log.i(TAG, "error");
    	}
    	else
    		showToast("还没有连接");
	}
    
	public void setTag(String tag) {
		TAG = tag;
	}
	
	public void CreateUDPSocket(String str_ip) {
		// if (udpcon != null)
			// udpcon.closeSocket();
		
		try {
			udpcon = new UDPSocket(str_ip);
			showToast("连接成功");
		} catch (IOException e) {
			e.printStackTrace();
			showToast("已连接");
		}
    }
	
    @Override
    public void onPause(){
    	super.onPause();
    	Log.i(TAG, "pause");
    	if (udpcon != null) {
    		udpcon.closeSocket();
    	}
    }
	
	/**
     * @param msg The msg to display
     * @param flag whether to show immediately
     */
    public void newToast(String msg, boolean flag) {
    	toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    	if (flag) toast.show();
    }
    public void showToast(String msg) {
    	toast.setText(msg);
    	toast.show();
    }
	
}

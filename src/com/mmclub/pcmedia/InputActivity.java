package com.mmclub.pcmedia;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;

/**
 * 第二屏
 * 全键盘和手势
 * @author tianshan
 *
 */
public class InputActivity extends BaseActivity implements OnGestureListener {

	private final static String TAG = "InputActivity";
	Button m_button1;
	Button m_button_switch;
	Button bt_connect;
	boolean CAPSLOCK = false;
	private String str_ip;
	
	GestureDetector detector;
	
	final int SCREENTYPE_VERTICAL = 0;
	final int SCREENTYPE_HORIZONTAL = 1;

	int screen_type;
	
	// 5-17 add
	private boolean FLAG_MOUSE = false;
	private CheckBox cb_mouse;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.input);
        
        m_button1 = (Button)findViewById(R.id.button1);
        m_button1.setOnClickListener(onClickListener);
        m_button_switch = (Button)findViewById(R.id.button_switch);
        m_button_switch.setOnClickListener(onClickListener);
        bt_connect = (Button)findViewById(R.id.button_con);
        bt_connect.setOnClickListener(onClickListener);
        
        cb_mouse = (CheckBox)findViewById(R.id.checkBox_mouse);
        cb_mouse.setChecked(false);
        cb_mouse.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
            	FLAG_MOUSE = arg1;
            	Log.i(TAG, String.valueOf(FLAG_MOUSE));
            }
        });
        
        newToast("", false);
        str_ip = getIntent().getStringExtra("ip");
        CreateUDPSocket(str_ip);
        
        detector = new GestureDetector(this);
        detector.setIsLongpressEnabled(true);
        
        // 设置默认屏幕方向，用于触控板模式下的坐标换算
        screen_type = SCREENTYPE_VERTICAL;
        
        
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		public void onClick(View v) {
			if (v == m_button1) {
				 InputMethodManager m = (InputMethodManager)
				 	InputActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                 m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			}else if (v == m_button_switch) {
				if (screen_type == SCREENTYPE_VERTICAL)
					screen_type = SCREENTYPE_HORIZONTAL;
				else if (screen_type == SCREENTYPE_HORIZONTAL)
					screen_type = SCREENTYPE_VERTICAL;
			}else if ( v == bt_connect) {
				CreateUDPSocket(str_ip);
			}
		}
	};
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.v(TAG, String.valueOf(keyCode));
		if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
			char c = '0';
			c += keyCode - KeyEvent.KEYCODE_0;
			SendMsg(c);
		}
		else if (keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z) {
			char c;
			if (CAPSLOCK) {
				c = 'A';
				CAPSLOCK = false;
			}else {
				c = 'a';
			}
			c += keyCode - KeyEvent.KEYCODE_A;
			SendMsg(c);
		}
		else if (keyCode == 59) {	// 大小写
			CAPSLOCK = true;
		}else {
			char en = KeyCode.enKeycode(keyCode);
			if(en != ' ') {
				SendMsg(en);
			}
		}
		return super.onKeyDown(keyCode, event);
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Log.i(TAG, "onTouchEvent");
		detector.onTouchEvent(event);
		return false;
	}
	
    // 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发  
	@Override
	public boolean onDown(MotionEvent e) {
		// Log.d(TAG, "onDown");
		return false;
	}

	
    // 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发 
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// Log.d(TAG, "onFling");
		
		// 参数解释：  
        // e1：第1个ACTION_DOWN MotionEvent  
        // e2：最后一个ACTION_MOVE MotionEvent  
        // velocityX：X轴上的移动速度，像素/秒  
        // velocityY：Y轴上的移动速度，像素/秒  
      
        // 触发条件 ：  
        // X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒  
		if (FLAG_MOUSE) return false;
		
		
        final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;
        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {  
            // Fling left
        	SendMsg(KeyCode.left);
            Log.i(TAG, "Fling left");  
        } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {  
            // Fling right
        	SendMsg(KeyCode.right);
            Log.i(TAG, "Fling right");  
        } else if(e2.getY()-e1.getY()>FLING_MIN_DISTANCE && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
            // Fling down
        	SendMsg(KeyCode.down);
            Log.i(TAG, "Fling down");  
        } else if(e1.getY()-e2.getY()>FLING_MIN_DISTANCE && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
            // Fling up
        	SendMsg(KeyCode.up);
            Log.i(TAG, "Fling up");  
        }  
		return false;
	}

    // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发  
	@Override
	public void onLongPress(MotionEvent e) {
		Log.d(TAG, "onLongPress");
	}
	
    // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发 
	// e1是起始点，不改变，e2是本次调用onScroll的终点，后两个是距上一次调用onScroll时的X和Y方向位移
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		//Log.d(TAG, "onScroll");
		
		// 根据屏幕方向换算坐标
		if (FLAG_MOUSE == false) return false; 
		
		if (screen_type == SCREENTYPE_VERTICAL) {
			SendCoord(-1*distanceX, -1*distanceY);
		}
		else if (screen_type == SCREENTYPE_HORIZONTAL) {
			SendCoord(-1*distanceY, distanceX);
		}
		
		
		
		
		Log.i("onScroll", String.valueOf(e1.getX()) +":"+ String.valueOf(e1.getY())+" "+
				String.valueOf(e2.getX()) +":"+ String.valueOf(e2.getY())+" "+
				String.valueOf(distanceX)+":"+String.valueOf(distanceY));
				
		return false;
	}

	 /* 
     * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发 

     * 注意和onDown()的区别，强调的是没有松开或者拖动的状态 
     */  
	@Override
	public void onShowPress(MotionEvent e) {
		Log.d(TAG, "onShowPress");
	}
	
    // 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发  
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		Log.d(TAG, "onSingleTapUp");
		
		// 定义为鼠标左键单击
		
		SendMsg(KeyCode.l_button);
		return false;
	}
}

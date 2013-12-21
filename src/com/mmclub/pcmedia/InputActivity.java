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
 * �ڶ���
 * ȫ���̺�����
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
        
        // ����Ĭ����Ļ�������ڴ��ذ�ģʽ�µ����껻��
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
		else if (keyCode == 59) {	// ��Сд
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
	
    // �û��ᴥ����������1��MotionEvent ACTION_DOWN����  
	@Override
	public boolean onDown(MotionEvent e) {
		// Log.d(TAG, "onDown");
		return false;
	}

	
    // �û����´������������ƶ����ɿ�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE, 1��ACTION_UP���� 
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// Log.d(TAG, "onFling");
		
		// �������ͣ�  
        // e1����1��ACTION_DOWN MotionEvent  
        // e2�����һ��ACTION_MOVE MotionEvent  
        // velocityX��X���ϵ��ƶ��ٶȣ�����/��  
        // velocityY��Y���ϵ��ƶ��ٶȣ�����/��  
      
        // �������� ��  
        // X�������λ�ƴ���FLING_MIN_DISTANCE�����ƶ��ٶȴ���FLING_MIN_VELOCITY������/��  
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

    // �û��������������ɶ��MotionEvent ACTION_DOWN����  
	@Override
	public void onLongPress(MotionEvent e) {
		Log.d(TAG, "onLongPress");
	}
	
    // �û����´����������϶�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE���� 
	// e1����ʼ�㣬���ı䣬e2�Ǳ��ε���onScroll���յ㣬�������Ǿ���һ�ε���onScrollʱ��X��Y����λ��
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		//Log.d(TAG, "onScroll");
		
		// ������Ļ����������
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
     * �û��ᴥ����������δ�ɿ����϶�����һ��1��MotionEvent ACTION_DOWN���� 

     * ע���onDown()������ǿ������û���ɿ������϶���״̬ 
     */  
	@Override
	public void onShowPress(MotionEvent e) {
		Log.d(TAG, "onShowPress");
	}
	
    // �û����ᴥ���������ɿ�����һ��1��MotionEvent ACTION_UP����  
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		Log.d(TAG, "onSingleTapUp");
		
		// ����Ϊ����������
		
		SendMsg(KeyCode.l_button);
		return false;
	}
}
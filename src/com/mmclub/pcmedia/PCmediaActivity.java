package com.mmclub.pcmedia;

import com.mmclub.util.Preferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 第一屏
 * 方向键等
 * @author tianshan
 *
 */
public class PCmediaActivity extends BaseActivity {
    /** Called when the activity is first created. */
	private Button m_button;
	private Button m_button_space;
	private Button m_button_enter;
	private Button m_button_up;
	private Button m_button_down;
	private Button m_button_left;
	private Button m_button_right;
	private Button m_button_connect;
	
	private ImageButton m_button_volumeUp;
	private ImageButton m_button_volumeDown;
	private ImageButton m_button_volumeMute;
	
	private EditText m_edittext;
	private TextView m_textview;
	
	private Button button_input;
	private String str_ip;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_button = (Button)findViewById(R.id.button1);
        m_button_space = (Button)findViewById(R.id.button_space);
        m_button_enter = (Button)findViewById(R.id.button_enter);
        
        m_edittext = (EditText)findViewById(R.id.editText1);
        m_textview = (TextView)findViewById(R.id.textview);
        
        
        m_button_up = (Button)findViewById(R.id.button_up);
        m_button_down = (Button)findViewById(R.id.button_down);
        m_button_left = (Button)findViewById(R.id.button_left);
        m_button_right = (Button)findViewById(R.id.button_right);
        
        m_button_connect = (Button)findViewById(R.id.button_connect);
        
        m_button_volumeUp = (ImageButton)findViewById(R.id.imageButton_up);
        m_button_volumeDown = (ImageButton)findViewById(R.id.imageButton_down);
        m_button_volumeMute = (ImageButton)findViewById(R.id.imageButton_mute);
        
        newToast("", false);
        setTag("PCmediaActivity");
        
        button_input = (Button)findViewById(R.id.button_input);
        button_input.setOnClickListener(onClickListener);
        
        // 获得默认ip
        getDefaultIP();
        
        m_button_connect.setOnClickListener(onClickListener);
        m_button.setOnClickListener(onClickListener);
        m_button_space.setOnClickListener(onClickListener);
        m_button_up.setOnClickListener(onClickListener);
        m_button_down.setOnClickListener(onClickListener);
        m_button_left.setOnClickListener(onClickListener);
        m_button_right.setOnClickListener(onClickListener);
        m_button_enter.setOnClickListener(onClickListener);
        m_button_volumeUp.setOnClickListener(onClickListener);
        m_button_volumeDown.setOnClickListener(onClickListener);
        m_button_volumeMute.setOnClickListener(onClickListener);
		
    }
    
    private OnClickListener onClickListener = new OnClickListener() {
    	public void onClick(View v){
    		if (v == m_button_volumeMute)
    			SendMsg(KeyCode.volume_mute);
    		else if (v == m_button_volumeDown)
    			SendMsg(KeyCode.volume_down);
    		else if (v == m_button_volumeUp)
        		SendMsg(KeyCode.volume_up);
    		else if (v == m_button_enter)
        		SendMsg(KeyCode.enter);
    		else if (v == m_button_right)
        		SendMsg(KeyCode.right);
    		else if (v == m_button_left)
    			SendMsg(KeyCode.left);
    		else if (v == m_button_down)
        		SendMsg(KeyCode.down);
    		else if (v == m_button_up)
    			SendMsg(KeyCode.up);
    		else if (v == m_button_space)
    			SendMsg(KeyCode.space);
    		else if (v == m_button){
    			char test = 0x41;
        		SendMsg(test);
    		}
    		else if (v == m_button_connect)
        		createUDPSocket();
    		else if (v == button_input) {
    			Intent intent = new Intent();
    			intent.putExtra("ip", str_ip);
    			intent.setClass(PCmediaActivity.this, InputActivity.class);
    			startActivity(intent);
    		}
    	}
    };
    
    
    private void getDefaultIP() {
    	String ip = Preferences.getPreferencesString(getApplicationContext(), "ip");
    	if (ip == null) ip = "0.0.0.0";
    	m_edittext.setText(ip);
    }
    private void setIP(String ip) {
    	Preferences.setPreferencesString(getApplicationContext(), "ip", ip);
    }
    
    public void createUDPSocket() {
    	str_ip = m_edittext.getText().toString();
    	// 保存当前ip，供下次使用
    	setIP(str_ip);
		m_textview.setText("当前连接："+str_ip);
		CreateUDPSocket(str_ip);
    }
    
    
}


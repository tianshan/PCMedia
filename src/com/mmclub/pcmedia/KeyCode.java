package com.mmclub.pcmedia;

import android.view.KeyEvent;

public final class KeyCode {
	final static char enter 		= 0x80;
	final static char up 			= 0x81;
	final static char down 			= 0x82;
	final static char left 			= 0x83;
	final static char right			= 0x84;
	final static char space			= 0x85;
	final static char backspace		= 0x86;
	
	
	final static char volume_up		= 0x91;
	final static char volume_down	= 0x92;
	final static char volume_mute	= 0x93;
	
	final static char alt_left = 0xa1;
	final static char alt_right = 0xa2;
	final static char shift_left = 0xa3;
	final static char shift_right = 0xa4;
	final static char C_TAB = 0xa5;
	
	final static char plus = 0xb1;
	final static char minus = 0xb2;
	final static char star = 0xb3;
	final static char slash = 0xb4;
	final static char equals = 0xb5;
	
	final static char s_at = 0xc1;
	final static char s_left_bracket = 0xc2;
	final static char s_right_bracket = 0xc3;
	final static char s_backslash = 0xc4;
	final static char s_apostarophe = 0xc5;
	final static char S_COMMA = 0xc6;
	final static char S_GRAVE = 0xc7;
	final static char S_PERIOD = 0xc8;
	final static char S_POUND = 0xc9;
	final static char S_SEMICOLON = 0xca;
	
	final static char l_button		= 0xd1;
	final static char r_button		= 0xd2;

	public static char enKeycode(int keycode) {
		if (keycode == KeyEvent.KEYCODE_ALT_LEFT) {
			return alt_left;
		}else if (keycode == KeyEvent.KEYCODE_ALT_RIGHT) {
			return alt_right;
		}else if (keycode == KeyEvent.KEYCODE_SHIFT_LEFT) {
			return shift_left;
		}else if (keycode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
			return shift_right;
		}else if (keycode == KeyEvent.KEYCODE_TAB) {
			return C_TAB;
		}
		
		else if (keycode == KeyEvent.KEYCODE_ENTER) {
			return enter;
		}else if (keycode == KeyEvent.KEYCODE_DPAD_DOWN) {
			return down;
		}else if (keycode == KeyEvent.KEYCODE_DPAD_LEFT) {
			return left;
		}else if (keycode == KeyEvent.KEYCODE_DPAD_RIGHT) {
			return right;
		}else if (keycode == KeyEvent.KEYCODE_DPAD_UP) {
			return up;
		}else if (keycode == KeyEvent.KEYCODE_DEL) {
			return backspace;
		}else if (keycode == KeyEvent.KEYCODE_SPACE) {
			return space;
		}
		
		else if (keycode == KeyEvent.KEYCODE_PLUS) {
			return plus;
		}else if (keycode == KeyEvent.KEYCODE_MINUS) {
			return minus;
		}else if (keycode == KeyEvent.KEYCODE_STAR) {
			return star;
		}else if (keycode == KeyEvent.KEYCODE_SLASH) {
			return slash;
		}else if (keycode == KeyEvent.KEYCODE_EQUALS) {
			return equals;
		}
		
		else if (keycode == KeyEvent.KEYCODE_AT) {
			return s_at;
		}else if (keycode == KeyEvent.KEYCODE_LEFT_BRACKET) {
			return s_left_bracket;
		}else if (keycode == KeyEvent.KEYCODE_RIGHT_BRACKET) {
			return s_right_bracket;
		}else if (keycode == KeyEvent.KEYCODE_BACKSLASH) {
			return s_backslash;
		}else if (keycode == KeyEvent.KEYCODE_APOSTROPHE) {
			return s_apostarophe;
		}else if (keycode == KeyEvent.KEYCODE_COMMA) {
			return S_COMMA;
		}else if (keycode == KeyEvent.KEYCODE_GRAVE) {
			return S_GRAVE;
		}else if (keycode == KeyEvent.KEYCODE_PERIOD) {
			return S_PERIOD;
		}else if (keycode == KeyEvent.KEYCODE_POUND) {
			return S_POUND;
		}else if (keycode == KeyEvent.KEYCODE_SEMICOLON) {
			return S_SEMICOLON;
		}
		
		else if (keycode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			return volume_up;
		}else if (keycode == KeyEvent.KEYCODE_VOLUME_UP) {
			return volume_down;
		}
		
		return ' ';
	}
}

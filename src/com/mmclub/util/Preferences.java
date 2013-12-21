package com.mmclub.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
	// get Preferences
	public static String getPreferencesString(Context context, String key) {
		SharedPreferences sPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
		return sPreferences.getString(key, null);
	}
	
	public static int getPreferencesInt(Context context, String key) {
		SharedPreferences sPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
		return sPreferences.getInt(key, 0);
	}
	
	public static float getPreferencesFloat(Context context, String key) {
		SharedPreferences sPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
		return sPreferences.getFloat(key, 0);
	}
	
	public static boolean getPreferenceBoolean(Context context, String key) {
		SharedPreferences sPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
		return sPreferences.getBoolean(key, false);
	}
	
	// set Preferences
	public static void setPreferencesString(Context context, String key, String info) {
		SharedPreferences sPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
		sPreferences.edit().putString(key, info).commit();
	}
	
	public static void setPreferencesInt(Context context, String key, int info) {
		SharedPreferences sPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
		sPreferences.edit().putInt(key, info).commit();
	}
	
	public static void setPreferencesFloat(Context context, String key, float info) {
		SharedPreferences sPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
		sPreferences.edit().putFloat(key, info).commit();
	}
	
	public static void setPreferencesBoolean(Context context, String key, Boolean info) {
		SharedPreferences sPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
		sPreferences.edit().putBoolean(key, info).commit();
	}
}

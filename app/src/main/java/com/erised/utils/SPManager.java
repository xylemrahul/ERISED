package com.erised.utils;

import android.content.SharedPreferences;

import com.erised.app.App;

import java.util.HashMap;

/**
 * Utility class used to save values on {@link SharedPreferences}  <br/>
 * Created by rahul on 26/9/15.
 *
 */

public class SPManager {

	public static final String KEY_USERNAME             = "GOOGLE_USERNAME";
	public static final String KEY_PASSWORD             = "PASSWORD";
	public static final String KEY_REMEMBER_ME          = "REMEMBER_ME";
	public static final String KEY_USERID 	            = "GOOGLE_USERID";
	public static final String KEY_LOGGED_IN 	        = "IS_LOGGED_IN";
	public static final String KEY_EMAIL 	            = "GOOGLE_EMAIL_ID";
	//Key for FaceBook
	public static final String KEY_PROFILE_PIC   		= "GOOGLE_PROFILE_PIC";
	public static final String KEY_GENDER 				= "GOOGLE_GENDER";


	//keys for user seen movie details
	
	public static final String KEY_IS_FIRST_TIME = "IS_FIRST_TIME";
	public static final String KEY_LASTMOVIE_ID = "LASTMOVIE_ID";
	
	public static final String KEY_GCM_REG_ID = "gcm_reg_id";
	
	/** 
	 * This method is to save string value to the shared preferences.
	 * @param key unique identifier for value
	 * @param data The value you wants to save to the preferences.
	 * */
	public static void save(String key, String data)
	{
		SharedPreferences.Editor editor = App.getS().edit();
		editor.putString(key, data);
		editor.commit();
	}

	/** 
	 * This method is to save integer value to the shared preferences.
	 * @param key unique identifier for value
	 * @param data The value you wants to save to the preferences.
	 * */
	public static void saveInt(String key, int data) {

		SharedPreferences.Editor editor = App.getS().edit();
		editor.putInt(key, data);
		editor.commit();
	}
	
	/** 
	 * This method is to save {@link java.util.HashMap} value to the shared preferences.
	 * */
	public static void saveMap(HashMap<String, String> hashMap){
		
		SharedPreferences.Editor editor = App.getS().edit();
		for (String key : hashMap.keySet()) {
			editor.putString(key, hashMap.get(key));
		}
		editor.commit();
	}

	/** 
	 * This method is to save boolean value to the shared preferences.
	 * @param key unique identifier for value
	 * @param data The value you wants to save to the preferences.
	 * */
	public static void saveBoolean(String key, boolean data) {

		SharedPreferences.Editor editor = App.getS().edit();

		editor.putBoolean(key, data);
		editor.commit();

	}

	/** 
	 * This method is to delete the value from the shared preferences.
	 * @param key unique identifier for value
	 * */
	public static void delete(String key) {
		SharedPreferences.Editor editor = App.getS().edit();

		editor.putString(key, null);
		editor.commit();
	}

	/** 
	 * This method is to retrieve string value from the shared preferences.
	 * @param key unique identifier for value
	 * */
	public static String retrive(String key)
	{
		return App.getS().getString(key, null);
	}

	/** 
	 * This method is to retrieve integer value from the shared preferences.
	 * @param key unique identifier for value
	 * Returns the preference value if it exists, or -1. Throws ClassCastException if there is a preference with this name that is not an int.
	 * */
	public static int retriveInt(String key) {
		return App.getS().getInt(key, -1);
	}

	/** 
	 * This method is to retrieve boolean value from the shared preferences.
	 * @param key unique identifier for value
	 * @return 
	 * Returns boolean the preference value if it exists, or <b>false</b>.
	 * */
	public static boolean retriveBoolean(String key) {
		return App.getS().getBoolean(key, false);
	}

	/** 
	 * This method is to check value for given key in the shared preferences.
	 * @param key unique identifier for value
	 * Returns true if it exists, otherwise false.
	 * */
	public static boolean exists(String key) {
		
		return App.getS().contains(key);
	}
}
package portal.bachtiar.com.portalv12.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import portal.bachtiar.com.portalv12.activity.Activity_Login;

/**
 * Created by bachtiar on 03/06/16.
 */

@SuppressLint("CommitPrefEdits")
public class SessionManager {
    SharedPreferences pref;
    Editor editor1, editor2;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Sesi";
    private static final String IS_LOGIN = "status";
    public static final String KEY_NIK = "nik";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PLANT = "plant";
    public static final String KEY_DEPT = "dept";
    public static final String KEY_PWD = "pwd";
    public static final String KEY_IMGUSER = "imgUser";
    public static final String KEY_TAG = "tag";
    public static final String KEY_REGDATE = "regDate";

    public static final String KEY_FEEDROOMID = "roomId";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor1 = pref.edit();
    }

    public void createLoginSession(String nik) {
        editor1.putBoolean(IS_LOGIN, true);
        editor1.putString(KEY_NIK, nik);
        editor1.commit();

    }

    public void UpdateLoginSession(String imgUser) {
        editor1.putBoolean(IS_LOGIN, true);
        editor1.putString(KEY_IMGUSER, imgUser);
        editor1.commit();

    }

    /**
     * Create login session
     */
    public void createLoginSession(String nik, String name, String phone, String email, String plant, String dept, String pwd,
                                   String imgUser, String tag, String regDate) {
        editor1.putBoolean(IS_LOGIN, true);
        editor1.putString(KEY_NIK, nik);
        editor1.putString(KEY_NAME, name);
        editor1.putString(KEY_PHONE, phone);
        editor1.putString(KEY_EMAIL, email);
        editor1.putString(KEY_PLANT, plant);
        editor1.putString(KEY_DEPT, dept);
        editor1.putString(KEY_PWD, pwd);
        editor1.putString(KEY_IMGUSER, imgUser);
        editor1.putString(KEY_TAG, tag);
        editor1.putString(KEY_REGDATE, regDate);
        editor1.commit();
    }

    public void feedSession(String roomId) {
        editor2.putString(KEY_FEEDROOMID, roomId);
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        if (!this.isLoggedIn()) {
            editor1.putBoolean(IS_LOGIN, false);
            Intent i = new Intent(_context, Activity_Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NIK, pref.getString(KEY_NIK, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_PLANT, pref.getString(KEY_PLANT, null));
        user.put(KEY_DEPT, pref.getString(KEY_DEPT, null));
        user.put(KEY_PWD, pref.getString(KEY_PWD, null));
        user.put(KEY_IMGUSER, pref.getString(KEY_IMGUSER, null));
        user.put(KEY_TAG, pref.getString(KEY_TAG, null));
        user.put(KEY_REGDATE, pref.getString(KEY_REGDATE, null));

        return user;
    }

    /**
     * Clear session details
     */
    public void backtoNews() {
        editor2.clear();
        editor2.commit();
    }

    public void logoutUser() {
        editor1.clear();
        editor1.commit();
        Intent i = new Intent(_context, Activity_Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}

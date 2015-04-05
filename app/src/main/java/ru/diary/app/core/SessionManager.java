package ru.diary.app.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import ru.diary.app.models.User;

/**
 * Created by Daria Schetinina on 4/5/15.
 */
public class SessionManager {

	static SharedPreferences sPref;
	static SharedPreferences.Editor editor;
	private static String TAG = SessionManager.class.getSimpleName();
	public static final String KEY_CACHE_USER = TAG + ".key_CACHE_USER";
	Context _context;

	public SessionManager(Context context) {
		this._context = context;
		sPref = _context.getSharedPreferences(KEY_CACHE_USER, Context.MODE_PRIVATE);
		editor = sPref.edit();
	}

	public static User getUser() {
		Gson gson = new Gson();
		String json = sPref.getString("user", "");
		return gson.fromJson(json, User.class);
	}

	public static void setUser(User user) {
		Gson gson = new Gson();
		String json = gson.toJson(user);
		editor.putString("user", json);
		editor.apply();
	}

}

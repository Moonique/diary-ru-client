package ru.diary.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by Daria Schetinina on 4/2/15.
 */
public class FavoritesActivity extends Activity{

	private TextView usernameTextView;
	private TextView passwordTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		findViews();
		SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		Gson gson = new Gson();
		String json = sPref.getString("user", "");
		User user = gson.fromJson(json, User.class);
		String username = user.getUsername();
		String password = user.getPassword();
		usernameTextView.setText(username);
		passwordTextView.setText(password);
	}

	private void findViews() {
		usernameTextView = (TextView) findViewById(R.id.textView);
		passwordTextView = (TextView) findViewById(R.id.textView2);
	}
}

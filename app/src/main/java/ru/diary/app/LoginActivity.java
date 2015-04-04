package ru.diary.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import static ru.diary.app.Utils.md5;


/**
 * Created by Daria Schetinina on 4/1/15.
 */
public class LoginActivity extends Activity {

	private EditText loginEditText;
	private EditText passwordEditText;
	private Button loginButton;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().setTitle(R.string.app_name);
		findViews();
		setListeners();
	}

	private void setListeners() {
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				User user = new User();
				user.setUsername(loginEditText.getText().toString());
				String passwordEncode = md5(R.string.private_key+passwordEditText.getText().toString());
				user.setPassword(passwordEncode);
				SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				SharedPreferences.Editor editor = sPref.edit();
				Gson gson = new Gson();
				String json = gson.toJson(user);
				editor.putString("user", json);
				editor.commit();
				Intent intent = new Intent(getBaseContext(), FavoritesActivity.class);
				startActivity(intent);
			}
		});
	}

	private void findViews() {
		loginEditText = (EditText) findViewById(R.id.activity_login_login_edit_text);
		passwordEditText = (EditText) findViewById(R.id.activity_login_password_edit_text);
		loginButton = (Button) findViewById(R.id.activity_login_login_button);
	}

}

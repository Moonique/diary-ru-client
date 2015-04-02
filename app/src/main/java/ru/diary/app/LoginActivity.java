package ru.diary.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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

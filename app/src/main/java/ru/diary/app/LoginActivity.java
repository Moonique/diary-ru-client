package ru.diary.app;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by Daria Schetinina on 4/1/15.
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().setTitle(R.string.app_name);
	}

}

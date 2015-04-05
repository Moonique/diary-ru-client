package ru.diary.app.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import ru.diary.app.R;
import ru.diary.app.core.SessionManager;
import ru.diary.app.models.User;

/**
 * Created by Daria Schetinina on 4/2/15.
 */
public class FavoritesActivity extends Activity{

	private TextView usernameTextView;
	private TextView passwordTextView;
	private TextView sidTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		findViews();
		User user = SessionManager.getUser();
		String username = user.getUsername();
		String password = user.getPassword();
		String sid = user.getSid();
		usernameTextView.setText(username);
		passwordTextView.setText(password);
		sidTextView.setText(sid);
	}

	private void findViews() {
		usernameTextView = (TextView) findViewById(R.id.textView);
		passwordTextView = (TextView) findViewById(R.id.textView2);
		sidTextView = (TextView) findViewById(R.id.textView3);
	}
}

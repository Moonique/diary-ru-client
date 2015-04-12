package ru.diary.app.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import ru.diary.app.R;
import ru.diary.app.core.DiaryApplication;
import ru.diary.app.core.SessionManager;
import ru.diary.app.models.User;

import static ru.diary.app.Utils.getMD5;


public class LoginActivity extends ActionBarActivity {

	private static final String TAG = LoginActivity.class.getSimpleName();

	private EditText loginEditText;
	private EditText passwordEditText;
	private Button loginButton;
	private ProgressDialog progressDialog;
	private Toolbar toolbar;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		findViews();
		setListeners();

		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);

		//SessionManager session = new SessionManager(getApplicationContext());

		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(R.string.app_name);

	}

	private void setListeners() {
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				User user = new User();
				user.setUsername(loginEditText.getText().toString());
				String passwordEncode = getMD5(getString(R.string.private_key) + passwordEditText.getText().toString());
				user.setPassword(passwordEncode);
				SessionManager.setUser(user);
				signInUser(user);
			}
		});
	}

	private void signInUser(final User user) {
		final String tag_string_req = "req_login";
		progressDialog.setMessage("Авторизация...");
		progressDialog.show();
		String url = getString(R.string.webservice_auth) + "&username=" + user.getUsername() + "&password=" +
				user.getPassword() + "&appkey=" + getString(R.string.app_key);
		final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.d(TAG, "Login Response: " + response);
				progressDialog.dismiss();

				try {
					JSONObject jsonObject = new JSONObject(response);
					int result = jsonObject.getInt("result");

					if (result == 0) {
						String sid = jsonObject.getString("sid");
						user.setSid(sid);
						SessionManager.setUser(user);
						Intent intent = new Intent(getBaseContext(), FavoritesActivity.class);
						startActivity(intent);
						finish();
					} else {
						String errorMsg = jsonObject.getString("error");
						Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "Login Error: " + error.getMessage());
				Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
				progressDialog.hide();
			}
		});
		DiaryApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);
	}

	private void findViews() {
		loginEditText = (EditText) findViewById(R.id.activity_login_login_edit_text);
		passwordEditText = (EditText) findViewById(R.id.activity_login_password_edit_text);
		loginButton = (Button) findViewById(R.id.activity_login_login_button);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
	}

}

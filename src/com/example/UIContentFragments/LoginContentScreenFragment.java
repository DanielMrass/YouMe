package com.example.UIContentFragments;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.UILayoutFragments.ProfileSoulmatesLayoutFragment;
import com.example.youapp.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.content.Intent;
import android.content.SharedPreferences;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import android.support.v4.app.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.preference.PreferenceManager;

public class LoginContentScreenFragment extends Fragment implements
		OnClickListener {

	private LoginButton loginButton;
	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		loginButton = (LoginButton) view.findViewById(R.id.login_button);
		loginButton.setFragment(this);
		// loginButton.setReadPermissions(Arrays.asList("user_location",
		// "user_birthday", "user_likes"));

		return view;
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {

			// Request user data and show the results
			Request.newMeRequest(session, new Request.GraphUserCallback() {

				@Override
				public void onCompleted(final GraphUser user,
						final Response response) {
					Log.i("ISREGISTERED", "" + isRegistered(user.getId()));
					if (user != null && isRegistered(user.getId())) {
						SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
						SharedPreferences.Editor editor = settings.edit();
						Log.i("PERSONID", getPersonId(user.getId()));
						editor.putString("personId", getPersonId(user.getId())).commit();
						getFragmentManager()
								.beginTransaction()
								.replace(R.id.container,
										new ProfileSoulmatesLayoutFragment())
								.commit();
					} else {
						getFragmentManager()
								.beginTransaction()
								.replace(
										R.id.container,
										new RegistrationContentScreenFragment(),
										"registrationFragment").commit();
					}
				}
			}).executeAsync();

			Log.i(TAG, "Logged in...");
		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
		}
	}

	private boolean isRegistered(String facebookId) {

		GetRequestTask task = (GetRequestTask) new GetRequestTask()
				.execute("https://app.dev.galaxyadvisors.com/YouApp/rest/persons/fbidexists.html?fbId=" + facebookId);

		try {
			if (task.get().equals("true")) {
				return true;
			} else {
				return false;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private String getPersonId(String facebookId){
		GetRequestTask task = (GetRequestTask) new GetRequestTask()
		.execute("https://app.dev.galaxyadvisors.com/YouApp/rest/persons/getid.html?fbId=" + facebookId);
		try {
			return task.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		Session session = Session.getActiveSession();
		if (session != null && (session.isOpened() || session.isClosed())) {
			onSessionStateChange(session, session.getState(), null);
		}

		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType) {
			// No need to implement.
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType) {
			// No need to implement.
		}
	} };

	// The async task to make the HTTP GET requests.
	class GetRequestTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String result = "";
			try {

				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc
						.getSocketFactory());

				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(params[0]);

				HttpResponse execute = client.execute(httpGet);
				InputStream content = execute.getEntity().getContent();

				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(content));
				String s = "";
				String response = "";
				while ((s = buffer.readLine()) != null) {
					response += s;
				}

				return response;

			} catch (Exception e) {
				Log.i("EXCEPTOION", e.toString());
				return null;
			}
		}

	}
}

package com.example.youapp;

import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import com.example.AsyncTasks.GetRequestTask;
import com.example.JSONParser.JSONParser;
import com.example.UIContentFragments.LoginContentScreenFragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//LoginScreenFragment
		if (savedInstanceState == null) {
			LoginContentScreenFragment frag = new LoginContentScreenFragment();
			//TODO noch aus Profiles auslesen
			Bundle args = new Bundle();
			args.putBoolean("isRegistered", false);
			frag.setArguments(args);
			
			getFragmentManager().beginTransaction()
					.replace(R.id.container, frag).addToBackStack("login").commit();
		}
		
		GetRequestTask task = (GetRequestTask) new GetRequestTask().execute("https://app.dev.galaxyadvisors.com/YouApp/rest/persons/index.html?personId=2");
		JSONObject result;
		try {
			result = task.get();
			Log.i("RESULT", result.toString());
			JSONParser parser = new JSONParser();
			parser.parseJSON(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		//RegistrationScreenFragment
//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new RegistrationScreenFragment()).commit();
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

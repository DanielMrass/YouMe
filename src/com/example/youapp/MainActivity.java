package com.example.youapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import com.example.AsyncTasks.GetRequestTask;
import com.example.JSONParser.JSONParser;
import com.example.UIContentFragments.LoginContentScreenFragment;
import com.facebook.Session;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

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
			
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, frag).addToBackStack("login").commit();
		}
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
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Session session = Session.getActiveSession();
		session.close();
	}
	
}

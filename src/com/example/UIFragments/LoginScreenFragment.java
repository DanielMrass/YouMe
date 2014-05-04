package com.example.UIFragments;

import com.example.youapp.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenFragment extends Fragment implements OnClickListener {
	
	private Button loginButton;
	private EditText mailaddy;
	private EditText password;
	private Button loginregButton;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);
		mailaddy = (EditText) rootView.findViewById(R.id.login_mail);
		password = (EditText) rootView.findViewById(R.id.login_password);
		loginButton = (Button) rootView.findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
		
		loginregButton = (Button) rootView.findViewById(R.id.login_regbutton);
		loginregButton.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.login_button:
			Toast.makeText(getActivity(), "Login", Toast.LENGTH_LONG).show();
			break;
		case R.id.login_regbutton:
			getFragmentManager().beginTransaction().replace(R.id.container, new RegistrationScreenFragment()).addToBackStack(null).commit();
			break;
		}
	}
}

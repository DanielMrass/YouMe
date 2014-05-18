package com.example.UIContentFragments;

import com.example.UILayoutFragments.ConfirmationLayoutFragment;
import com.example.UILayoutFragments.ProfileSoulmatesLayoutFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginContentScreenFragment extends Fragment implements OnClickListener {
	
	private Button loginButton;
	private EditText mailaddy;
	private EditText password;
	private Button loginregButton;
	private Button profileButton;
	private Button confButton;
	
	private boolean isRegistered = false;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Bundle args = getArguments();
		if(args != null){
			Toast.makeText(getActivity(), "Got Arguments!", Toast.LENGTH_SHORT).show();
			if(args.containsKey("isRegistered")){
				this.isRegistered = args.getBoolean("isRegistered");
			}
		}
		
		getActivity().getActionBar().setTitle("YouMeIBD");
		
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);
//		mailaddy = (EditText) rootView.findViewById(R.id.login_mail);
//		password = (EditText) rootView.findViewById(R.id.login_password);
		loginButton = (Button) rootView.findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
		
		
		//Buttons zu Testzwecken eingefügt 
//		loginregButton = (Button) rootView.findViewById(R.id.login_regbutton);
//		loginregButton.setOnClickListener(this);
		
//		profileButton = (Button) rootView.findViewById(R.id.reg_button_profile);
//		profileButton.setOnClickListener(this);
//		
//		confButton = (Button) rootView.findViewById(R.id.conf_button_confirmation);
//		confButton.setOnClickListener(this);
		
		
		return rootView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.login_button:
			if(isRegistered){
				getFragmentManager().popBackStack();
				getFragmentManager().beginTransaction().replace(R.id.container, new ProfileSoulmatesLayoutFragment()).commit();
			}
			else {
				getFragmentManager().beginTransaction().replace(R.id.container, new RegistrationContentScreenFragment(), "registrationFragment").commit();
			}
			break;
//		case R.id.login_regbutton:
//			getFragmentManager().beginTransaction().replace(R.id.container, new RegistrationContentScreenFragment()).addToBackStack("registration").commit();
//			break;
//		case R.id.reg_button_profile:
//			getFragmentManager().beginTransaction().replace(R.id.container, new ProfileSoulmatesLayoutFragment()).addToBackStack("profile").commit();
//			break;
//		case R.id.conf_button_confirmation:
//			getFragmentManager().beginTransaction().replace(R.id.container, new ConfirmationLayoutFragment()).addToBackStack("confirmation").commit();
//			break;
		}
	}
}

package com.example.UIContentFragments;

import com.example.UILayoutFragments.ProfileSoulmatesLayoutFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LoginContentScreenFragment extends Fragment implements OnClickListener {
	
	private Button loginButton;
	
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
		loginButton = (Button) rootView.findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);	
		
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
		}
	}
}

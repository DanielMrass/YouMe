package com.example.UIMenuFragments;

import com.example.UIContentFragments.ProfileContentScreenFragment;
import com.example.UIContentFragments.SoulmateContentScreenFragment;
import com.example.youapp.R;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ProfileSoulmateScreensMenuFragment extends Fragment implements OnCheckedChangeListener {
	
	private ToggleButton profile;
	private ToggleButton soulmates;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.profile_soulmate_menu_fragment, container,
				false);
		profile = (ToggleButton) rootView.findViewById(R.id.profile_toggle_profile);
		profile.setChecked(true);
		profile.setOnCheckedChangeListener(this);
		profile.setClickable(false);
		soulmates = (ToggleButton) rootView.findViewById(R.id.profile_toggle_soulmate);
		soulmates.setOnCheckedChangeListener(this);
		return rootView;
		
	}

	@Override
	public void onCheckedChanged(CompoundButton button, boolean state) {
		switch(button.getId()){
		case R.id.profile_toggle_profile:
			Toast.makeText(getActivity(), "Profile: " + state, Toast.LENGTH_SHORT).show();
			if(state){
				soulmates.setChecked(false);
				profile.setClickable(false);
				soulmates.setClickable(true);
				getFragmentManager().beginTransaction().replace(R.id.content_placeholder, new ProfileContentScreenFragment()).commit();
			}
			break;
		case R.id.profile_toggle_soulmate:
			Toast.makeText(getActivity(), "Soulmate: " + state, Toast.LENGTH_SHORT).show();
			if(state){
				profile.setChecked(false);
				profile.setClickable(true);
				soulmates.setClickable(false);
				getFragmentManager().beginTransaction().replace(R.id.content_placeholder, new SoulmateContentScreenFragment()).commit();
			}
			break;
		}
	}
}

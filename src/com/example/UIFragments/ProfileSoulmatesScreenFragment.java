package com.example.UIFragments;

import com.example.UIMenuFragments.ProfileSoulmateScreensMenuFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileSoulmatesScreenFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.f_profile_soulmate, container,
				false);
		getFragmentManager().beginTransaction().replace(R.id.menu_placeholder, new ProfileSoulmateScreensMenuFragment()).commit();
		return rootView;
	}
}

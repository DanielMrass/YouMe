package com.example.UIFragments;

import com.example.UIMenuFragments.FooterFragment;
import com.example.UIMenuFragments.ProfileSoulmateScreensMenuFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileSoulmatesScreenFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.f_profile_soulmate, container,
				false);
		getFragmentManager().beginTransaction().replace(R.id.menu_placeholder, new ProfileSoulmateScreensMenuFragment()).commit();
		getFragmentManager().beginTransaction().replace(R.id.content_placeholder, new ProfileContentScreenFragment()).commit();
		getFragmentManager().beginTransaction().replace(R.id.footer_placeholder, new FooterFragment()).commit();
		
		getActivity().getActionBar().setTitle("Your Profile");
		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		//super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu, menu);
	}
}

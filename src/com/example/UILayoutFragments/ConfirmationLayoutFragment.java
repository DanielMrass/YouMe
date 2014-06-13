package com.example.UILayoutFragments;

import com.example.UIContentFragments.ConfirmationContentScreenFragment;
import com.example.UIMenuFragments.FooterFragment;
import com.example.youapp.R;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConfirmationLayoutFragment extends Fragment{
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.f_standard_layout, container, false);
			
//			getFragmentManager().beginTransaction().replace(R.id.menu_placeholder, new ProfileSoulmateScreensMenuFragment()).commit();
			getFragmentManager().beginTransaction().replace(R.id.content_placeholder, new ConfirmationContentScreenFragment()).commit();
			getFragmentManager().beginTransaction().replace(R.id.footer_placeholder, new FooterFragment()).commit();
			
			getActivity().getActionBar().setTitle("Confirmation");
			return rootView;
		}
}

package com.example.UIFragments;

import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileContentScreenFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.f_profile_content, container, false);
		
		return rootView;
	}
}

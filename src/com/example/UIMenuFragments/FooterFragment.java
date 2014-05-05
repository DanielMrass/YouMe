package com.example.UIMenuFragments;

import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FooterFragment extends Fragment {
	
	//TODO Links zu entsprechenden Seiten hinzufügen
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.f_footer_menu, container,
				false);
		TextView pp_labelText = (TextView) rootView.findViewById(R.id.footer_privacy_policy);
		pp_labelText.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView about_labelText = (TextView) rootView.findViewById(R.id.footer_about);
		about_labelText.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView help_labelText = (TextView) rootView.findViewById(R.id.footer_help);
		help_labelText.setMovementMethod(LinkMovementMethod.getInstance());
		
		return rootView;
	}
}

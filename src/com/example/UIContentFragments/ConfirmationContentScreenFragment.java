package com.example.UIContentFragments;

import com.example.youapp.R;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class ConfirmationContentScreenFragment extends Fragment implements OnClickListener{

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
//		getActivity().getActionBar().setTitle("Confirmation");
		View rootView = inflater.inflate(R.layout.f_confirmation_content, container,
				false);
		
		TextView label_b2login = (TextView) rootView.findViewById(R.id.confirmation_b2login);
		label_b2login.setClickable(true);
		
		//TODO der Link muss noch dafür sorgen das man wieder auf den loginscreen kommt
		label_b2login.setOnClickListener(this);
		
		return rootView;
	}
	
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.confirmation_b2login:
			LoginContentScreenFragment frag = new LoginContentScreenFragment();
			//TODO noch aus Profiles auslesen
			Bundle args = new Bundle();
			args.putBoolean("isRegistered", true);
			frag.setArguments(args);
			
			getFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
//			getFragmentManager().beginTransaction().detach(this).commit();
//			getFragmentManager().popBackStack("login", 0);
			break;
		}
	}
}

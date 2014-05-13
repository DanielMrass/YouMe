package com.example.UIFragments;

import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Confirmation");
		View rootView = inflater.inflate(R.layout.f_confirmation, container,
				false);
		
		TextView label_b2login = (TextView) rootView.findViewById(R.id.confirmation_b2login);
		
		//TODO der Link muss noch dafür sorgen das man wieder auf den loginscreen kommt
//		label_b2login.setOnClickListener(this);
		
		return rootView;
	}
	
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.confirmation_b2login:
			Toast.makeText(getActivity(), "Back2Login", Toast.LENGTH_LONG).show();			
			getFragmentManager().beginTransaction().replace(R.id.container, new LoginScreenFragment()).addToBackStack("Lgoin").commit();
			break;
		}
	}
}

package com.example.UIFragments;

import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class ProfileContentScreenFragment extends Fragment implements OnClickListener {
	
	private TextView personalContent;
	private TextView symptomsContent;
	private TextView medicationContent;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Your Profile");
		
		View rootView = inflater.inflate(R.layout.f_profile_content, container, false);
		
		CheckBox profcontentInfoButton = (CheckBox) rootView.findViewById(R.id.profcontent_info_button);
		profcontentInfoButton.setOnClickListener(this);
		personalContent = (TextView) rootView.findViewById(R.id.profcontent_info_content);
		personalContent.setVisibility(View.GONE);
		
		CheckBox profContentSymptomsButton = (CheckBox) rootView.findViewById(R.id.profcontent_symptoms_button);
		profContentSymptomsButton.setOnClickListener(this);
		symptomsContent = (TextView) rootView.findViewById(R.id.profcontent_symptoms_content);
		symptomsContent.setVisibility(View.GONE);
		
		CheckBox profContentMedicationButton = (CheckBox) rootView.findViewById(R.id.profcontent_medication_button);
		profContentMedicationButton.setOnClickListener(this);
		medicationContent = (TextView) rootView.findViewById(R.id.profcontent_medication_content);
		medicationContent.setVisibility(View.GONE);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.profcontent_info_button:
			personalContent.setVisibility( personalContent.isShown()
                    ? View.GONE
                    : View.VISIBLE );
		break;
		case R.id.profcontent_symptoms_button:
			symptomsContent.setVisibility( symptomsContent.isShown()
                    ? View.GONE
                    : View.VISIBLE );
			break;
		case R.id.profcontent_medication_button:
			medicationContent.setVisibility( medicationContent.isShown()
                    ? View.GONE
                    : View.VISIBLE );
			break;
		}
	}
}

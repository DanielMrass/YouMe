package com.example.UIFragments;

import java.util.Vector;

import com.example.LayoutFactories.ProfileSoulmateLayoutFactory;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileContentScreenFragment extends Fragment implements OnClickListener {
	
	private LinearLayout personalContent;
	private TextView symptomsContent;
	private TextView medicationContent;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Your Profile");
		
		ProfileSoulmateLayoutFactory plf = new ProfileSoulmateLayoutFactory(getActivity()); 
		View rootView = inflater.inflate(R.layout.f_profile_content, container, false);
		
		CheckBox profcontentInfoButton = (CheckBox) rootView.findViewById(R.id.profcontent_info_button);
		profcontentInfoButton.setOnClickListener(this);
		
		personalContent = plf.getProfileContent(createDummyData());
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.profcontent_info_layout);
		layout.addView(personalContent);
//		personalContent = rootView.findViewById(R.id.profcontent_info_content);
//		personalContent.setVisibility(View.GONE);
		
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

	private Vector<String> createDummyData() {
		Vector<String> data = new Vector<String>();
		data.add("Daniel Mraﬂ");
		data.add("Alter: 25");
		data.add("Ernststr. 91");
		data.add("53757 Sankt Augustin");
		data.add("Germany");
		return data;
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

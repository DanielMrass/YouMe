package com.example.UIContentFragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.example.Adapters.ProfileListAdapter;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileContentScreenFragment extends Fragment  {
	
	private List<String> categories = new ArrayList<String>();
	private HashMap<String, List<Object>> childData = new HashMap<String, List<Object>>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Your Profile");
		View rootView = inflater.inflate(R.layout.f_profile_content, container, false);
		
		ExpandableListView exlv = (ExpandableListView) rootView.findViewById(R.id.profile_exlist);
		createDummyData();
		ProfileListAdapter pla = new ProfileListAdapter(getActivity(), categories, childData);
		exlv.setAdapter(pla);
		return rootView;
	}

	private void createDummyData() {
		//add the Categories
		categories.add("My Profile");
		categories.add("Symptoms");
		categories.add("Medication");
		
		
		//create profile Information
		List<Object> profiles = new ArrayList<Object>();
		List<Object> profile = new ArrayList<Object>();
		profile.add("Daniel Mraﬂ, Age: 25");
		profile.add("Sankt Augustin, Germany");
		profile.add("daniel.mrass@gmail.com (not visible)");
		profiles.add(profile);
		
		//create symptoms iInformation
		List<Object> symptom = new ArrayList<Object>();
		symptom.add("smartness");
		symptom.add("good look");
		symptom.add("clear speech");
		
		
		//create medication information
		List<Object> medication = new ArrayList<Object>();
		medication.add("Coffee");
		medication.add("More Coffee");
		medication.add("...");
		
		//add the categories and data to the map
		childData.put(categories.get(0), profiles);
		childData.put(categories.get(1), symptom);
		childData.put(categories.get(2), medication);
	}
}

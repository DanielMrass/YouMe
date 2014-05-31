package com.example.UIContentFragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.Adapters.ProfileListAdapter;
import com.example.Adapters.ProfileMedicationAdapter;
import com.example.Adapters.ProfileSymptomsAdapter;
import com.example.CallbackInterfaces.MedicineCallBack;
import com.example.CallbackInterfaces.ProfileCallBack;
import com.example.CallbackInterfaces.SymptomCallBack;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class ProfileContentScreenFragment extends Fragment implements MedicineCallBack, SymptomCallBack, ProfileCallBack  {
	
	private List<String> categories = new ArrayList<String>();
	private HashMap<String, List<Object>> childData = new HashMap<String, List<Object>>();
	
	private ArrayList<String> medication = new ArrayList<String>();
	private ProfileMedicationAdapter medicationAdapter;
	
	private ArrayList<String> symptoms = new ArrayList<String>();
	private ProfileSymptomsAdapter symptomsAdapter;
	
	private ArrayList<String> profileData = new ArrayList<String>();
	private ProfileListAdapter profileAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Your Profile");
		View rootView = inflater.inflate(R.layout.f_profile_content, container, false);
		
		ExpandableListView profile = (ExpandableListView) rootView.findViewById(R.id.profile_exlist);
		createDummyProfile();
		profileAdapter = new ProfileListAdapter(getActivity(), "My Profile", profileData);
		profileAdapter.setProfCallback(this);
		profile.setAdapter(profileAdapter);
		
		ExpandableListView medicationList = (ExpandableListView) rootView.findViewById(R.id.profile_exlist_medication);
		createDummyMedication();
		medicationAdapter = new ProfileMedicationAdapter(getActivity(), "Medication", medication);
		medicationAdapter.setProfMedCallBack(this);
		medicationList.setAdapter(medicationAdapter);
		
		ExpandableListView symptomsList = (ExpandableListView) rootView.findViewById(R.id.profile_exlist_symptoms);
		createDummySymptoms();
		symptomsAdapter = new ProfileSymptomsAdapter(getActivity(), "Symptoms", symptoms);
		symptomsAdapter.setProfSympCallBack(this);
		symptomsList.setAdapter(symptomsAdapter);
		
		return rootView;
	}

	private void createDummyProfile() {
		profileData.add("Mr. X");
		profileData.add("Unknown");
		profileData.add("London");
	}

	private void createDummySymptoms() {
		symptoms.add("cleverness");
		symptoms.add("clear speech");
		symptoms.add("good look");
	}

		// Dummy methods for data-initialization
		private void createDummyMedication() {
			medication.add("Coffee");
			medication.add("More Coffee");
			medication.add("...");
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

	@Override
	public void deleteMedicineFromList(int position) {
		medication.remove(position);
		medicationAdapter.updateDataList(medication);
	}

	@Override
	public void addMedicine(String medicine) {
		medication.add(medicine);
		medicationAdapter.updateDataList(medication);
	}

	@Override
	public void deleteSymptomFromList(int position) {
		symptoms.remove(position);
		symptomsAdapter.updateDataList(symptoms);
	}

	@Override
	public void addSymptom(String symptom) {
		symptoms.add(symptom);
		symptomsAdapter.updateDataList(symptoms);
	}

	@Override
	public void updateProfile(ArrayList<String> data) {
		this.profileData = data;
		profileAdapter.updateProfileData(profileData);
	}
}

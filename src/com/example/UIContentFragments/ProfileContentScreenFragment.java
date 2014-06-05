package com.example.UIContentFragments;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Adapters.ProfileListAdapter;
import com.example.Adapters.ProfileMedicationAdapter;
import com.example.Adapters.ProfileSymptomsAdapter;
import com.example.CallbackInterfaces.MedicineCallBack;
import com.example.CallbackInterfaces.ProfileCallBack;
import com.example.CallbackInterfaces.SymptomCallBack;
import com.example.youapp.R;

import UIDialogFragments.EditProfileFragment;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class ProfileContentScreenFragment extends Fragment implements MedicineCallBack, SymptomCallBack, ProfileCallBack  {
	
	
	private ArrayList<String> medication = new ArrayList<String>();
	private ProfileMedicationAdapter medicationAdapter;
	
	private ArrayList<String> symptoms = new ArrayList<String>();
	private ProfileSymptomsAdapter symptomsAdapter;
	
	private HashMap<String, String> profileDataSorted = new HashMap<String, String>();
	private ArrayList<String> profileDataForAdapter = new ArrayList<String>();
	private ProfileListAdapter profileAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Your Profile");
		View rootView = inflater.inflate(R.layout.f_profile_content, container, false);
		
		ExpandableListView profile = (ExpandableListView) rootView.findViewById(R.id.profile_exlist);
		//TODO get HashMap with data from Parser
		createDummyProfile();
		profileAdapter = new ProfileListAdapter(getActivity(), "My Profile", profileDataForAdapter);
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
		profileDataSorted.put("name", "Mr.X");
		profileDataSorted.put("origin", "London/England");
		profileDataSorted.put("gender", "male");
		profileDataSorted.put("birthday", "11-11-1995");
		profileDataSorted.put("mail", "mrx@gangsters-london.uk");
		profileDataSorted.put("mailVisible", "false");
		updateArrayList();		
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
	public void updateProfile(HashMap<String, String> data) {
		this.profileDataSorted = data;
		updateArrayList();
		profileAdapter.updateProfileData(profileDataForAdapter);
	}

	private void updateArrayList() {
		profileDataForAdapter.clear();
		profileDataForAdapter.add(profileDataSorted.get("name") + ", " + profileDataSorted.get("origin"));
		profileDataForAdapter.add(profileDataSorted.get("gender") + ", " + profileDataSorted.get("birthday"));
		if(profileDataSorted.get("mailVisible").equalsIgnoreCase("false")){
			profileDataForAdapter.add(profileDataSorted.get("mail")+ "(not visible)");
		}
		else {
			profileDataForAdapter.add(profileDataSorted.get("mail")+ "(visible)");
		}
	}

	@Override
	public void initiateEditDialog() {
		// TODO createTheDialog
		Bundle bundle = new Bundle();
		Log.i("PROFILECONTENT", profileDataSorted.toString());
		bundle.putSerializable("profileMap", profileDataSorted);
		DialogFragment frag = new EditProfileFragment();
		frag.setArguments(bundle);
		frag.show(getFragmentManager(), "editProfile");
		((EditProfileFragment)frag).setProfCall(this);
	}
}

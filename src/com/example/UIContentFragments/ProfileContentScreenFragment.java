package com.example.UIContentFragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Adapters.ProfileListAdapter;
import com.example.Adapters.ProfileMedicationAdapter;
import com.example.Adapters.ProfileSymptomsAdapter;
import com.example.AsyncTasks.GetRequestTask;
import com.example.AsyncTasks.PutRequestTask;
import com.example.CallbackInterfaces.MedicineCallBack;
import com.example.CallbackInterfaces.ProfileCallBack;
import com.example.CallbackInterfaces.SymptomCallBack;
import com.example.youapp.R;

import UIDialogFragments.EditProfileFragment;
//import android.app.DialogFragment;
import android.support.v4.app.*;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
	private JSONObject object;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Your Profile");
		View rootView = inflater.inflate(R.layout.f_profile_content, container, false);
		
		ExpandableListView profile = (ExpandableListView) rootView.findViewById(R.id.profile_exlist);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		GetRequestTask grt = (GetRequestTask) new GetRequestTask().execute("https://app.dev.galaxyadvisors.com/YouApp/rest/persons/index.html?personId="+settings.getString("personId", null));
		try {
		object = grt.get();
		if(object != null){
			generateProfileData(object);
			generateMedicationSymptomsData(object);
		}
		else{
			//TODO
		}
		
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		profileAdapter = new ProfileListAdapter(getActivity(), "My Profile", profileDataForAdapter);
		profileAdapter.setProfCallback(this);
		profile.setAdapter(profileAdapter);
		
		ExpandableListView medicationList = (ExpandableListView) rootView.findViewById(R.id.profile_exlist_medication);
		medicationAdapter = new ProfileMedicationAdapter(getActivity(), "Medication", medication);
		medicationAdapter.setProfMedCallBack(this);
		medicationList.setAdapter(medicationAdapter);
		
		ExpandableListView symptomsList = (ExpandableListView) rootView.findViewById(R.id.profile_exlist_symptoms);
		symptomsAdapter = new ProfileSymptomsAdapter(getActivity(), "Symptoms", symptoms);
		symptomsAdapter.setProfSympCallBack(this);
		symptomsList.setAdapter(symptomsAdapter);
		
		return rootView;
	}

	private void generateProfileData(JSONObject object) {
		try {
			profileDataSorted.put("firstName", object.getString("firstName"));
			profileDataSorted.put("lastName", object.getString("lastName"));
			
			JSONObject obj = object.getJSONObject("location");
			profileDataSorted.put("origin", obj.getString("name"));
			
			profileDataSorted.put("gender", object.getString("gender"));
			profileDataSorted.put("birthday", object.getString("birthday"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		updateArrayList();		
	}

	// Dummy methods for data-initialization
	private void generateMedicationSymptomsData(JSONObject medis) {
		try {
			JSONArray array = medis.getJSONArray("tags");
			JSONObject obj = new JSONObject();
			for(int i = 0; i< array.length(); i++) {
				obj = array.getJSONObject(i);
				if(obj.getString("category").equalsIgnoreCase("Medication")){
					medication.add(obj.getString("name"));
				}
				else if(obj.getString("category").equalsIgnoreCase("Symptom")){
					symptoms.add(obj.getString("name"));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
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
		//TODO generate an ID for the new Symptom and put it into the JSON Object before calling the API
		
//		try {
//			JSONArray array = object.getJSONArray("tags");
//			JSONObject obj = new JSONObject("{\"category\":\"Medication\", \"name\":\""+ medicine + "\"}");
//			array.put(obj);
//			object.put("tags", array);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
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
		//TODO generate an ID for the new Symptom and put it into the JSON Object before calling the API
		
//		try {
//			JSONArray array = object.getJSONArray("tags");
//			JSONObject obj = new JSONObject("{\"category\":\"Symptom\", \"name\":\""+ symptom + "\"}");
//			array.put(obj);
//			object.put("tags", array);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void updateProfile(HashMap<String, String> data) {
		this.profileDataSorted = data;
		updateArrayList();
		profileAdapter.updateProfileData(profileDataForAdapter);
		
		try {
			object.put("lastName", profileDataSorted.get("lastName"));
			object.put("firstName", profileDataSorted.get("firstName"));
			object.put("gender", profileDataSorted.get("gender"));
			object.put("birthday", profileDataSorted.get("birthday"));
			
			//TODO start the task
			PutRequestTask prt = (PutRequestTask) new PutRequestTask().execute("https://app.dev.galaxyadvisors.com/YouApp/rest/persons/index.html", object.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	private void updateArrayList() {
		profileDataForAdapter.clear();
		profileDataForAdapter.add(profileDataSorted.get("firstName") +" "+ profileDataSorted.get("lastName"));
		profileDataForAdapter.add(profileDataSorted.get("origin"));
		if(profileDataSorted.get("gender").equalsIgnoreCase("M")){
			profileDataForAdapter.add("Male");
		}else {
			profileDataForAdapter.add("Female");
		}
		
		profileDataForAdapter.add(profileDataSorted.get("birthday"));
//		if(profileDataSorted.get("mailVisible").equalsIgnoreCase("false")){
//			profileDataForAdapter.add(profileDataSorted.get("mail")+ "(not visible)");
//		}
//		else {
//			profileDataForAdapter.add(profileDataSorted.get("mail")+ "(visible)");
//		}
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

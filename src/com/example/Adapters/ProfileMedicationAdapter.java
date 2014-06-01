package com.example.Adapters;

import java.util.ArrayList;

import com.example.CallbackInterfaces.MedicineCallBack;
import com.example.youapp.R;

import UIDialogFragments.AddMedicinesFragment;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileMedicationAdapter extends BaseExpandableListAdapter {

	//TODO Fehlerüberprüfung beim erstellen der Views
	
	
	//Class Variables
	//contains Profile Information, Medicine und Symptoms!
	private String category; 
	//contains Data regarding to the childs -> Profile Information,
	private ArrayList<String> medication;
	private MedicineCallBack medCall;
	private Activity activity;
	
	
	public ProfileMedicationAdapter(Activity act, String cat, ArrayList<String> data){
		this.activity = act;
		this.category = cat;
		this.medication = data;
	}
	
	@Override
	public String getChild(int groupPosition, int childPosition) {
		return this.medication.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		String medication = getChild(groupPosition, childPosition);
		
		LayoutInflater infalInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.profile_exlist_medication_child, null);
		
		TextView medicationView = (TextView) convertView.findViewById(R.id.medication_exlist_name);
		medicationView.setText(medication);
		
		ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.medication_exlist_button);
		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				medCall.deleteMedicineFromList(childPosition);
			}
		});
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return medication.size();
	}

	@Override
	public String getGroup(int groupPosition) {
		return category;
	}

	@Override
	public int getGroupCount() {
		return 1;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExPanded, View convertView, ViewGroup parent) {
		String categoryTitle = (String) getGroup(groupPosition);
		
		if(convertView == null){
			LayoutInflater infalInflater = (LayoutInflater) 
                    activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.profile_exlist_profinfo_category, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.profcontent_category_text);
		tv.setText(categoryTitle);
		
		ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.profcontent_category_button);
		imageButton.setFocusableInTouchMode(false);
		imageButton.setFocusable(false);
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogFragment df = new AddMedicinesFragment();
				df.show(activity.getFragmentManager(), "addMedicine");
				((AddMedicinesFragment) df).setMedCall(medCall);
			}
		});
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}
	
	public void updateDataList(ArrayList<String> medication){
		this.medication = medication;
		notifyDataSetChanged();
	}
	
	public void setProfMedCallBack(MedicineCallBack medCall){
		this.medCall = medCall;
	}
}

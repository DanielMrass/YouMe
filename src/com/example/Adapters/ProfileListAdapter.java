package com.example.Adapters;

import java.util.HashMap;
import java.util.List;

import com.example.youapp.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileListAdapter extends BaseExpandableListAdapter {

	//TODO Fehlerüberprüfung beim erstellen der Views
	
	
	//Class Variables
	//contains Profile Information, Medicine und Symptoms!
	private List<String> categories; 
	//contains Data regarding to the childs -> Profile Information, 
	private HashMap<String, List<Object>> childData;
	private Activity activity;
	
	
	public ProfileListAdapter(Activity act, List<String> cats, HashMap<String, List<Object>> data){
		this.activity = act;
		this.categories = cats;
		this.childData = data;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.childData.get(this.categories.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	//TODO auf Profile umbauen
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		Log.i("GROUP", ""+ groupPosition);
		Log.i("CHILD", ""+childPosition);
		switch(groupPosition){
		//ProfileInfo
		case 0:
			List<Object> obj = (List<Object>) getChild(groupPosition, childPosition);
			return createProfileChild(convertView, obj);
		//Symptoms 
		case 1:
			String symptom = (String) getChild(groupPosition, childPosition);
			Log.i("Symptom", symptom);
			return createSymptomsChild(convertView, symptom);
		//Medication
		case 2:
			String medication = (String) getChild(groupPosition, childPosition);
			return createMedicationChild(convertView, medication);
		}
		return convertView;
	}
	
	private View createMedicationChild(View convertView, String medication2) {
		//if(convertView == null){
			LayoutInflater infalInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	convertView = infalInflater.inflate(R.layout.profile_exlist_medication_child, null);
		//}
		
		TextView medication = (TextView) convertView.findViewById(R.id.medication_exlist_name);
		medication.setText(medication2);
		return convertView;
	}

	private View createSymptomsChild(View convertView, String symptom2) {
		//if(convertView == null){
			LayoutInflater infalInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	convertView = infalInflater.inflate(R.layout.profile_exlist_symptoms_child, null);
		//}
		
		TextView symptom = (TextView) convertView.findViewById(R.id.symptoms_exlist_name);
		symptom.setText(symptom2);
		
		return convertView;
	}

	private View createProfileChild(View convertView, List<Object> obj) {
		//if(convertView == null){
			LayoutInflater infalInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	convertView = infalInflater.inflate(R.layout.profile_exlist_info_child, null);
		//}
		
		TextView name = (TextView) convertView.findViewById(R.id.profile_exlist_info_child_name);
		name.setText((String)obj.get(0));
		
		TextView land = (TextView) convertView.findViewById(R.id.profile_exlist_info_child_land);
		land.setText((String)obj.get(1));
		
		TextView mail = (TextView) convertView.findViewById(R.id.profile_exlist_info_child_mail);
		mail.setText((String)obj.get(2));
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childData.get(categories.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return categories.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return categories.size();
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

}

package com.example.Adapters;

import java.util.ArrayList;

import com.example.CallbackInterfaces.ProfileCallBack;
import com.example.youapp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileListAdapter extends BaseExpandableListAdapter {

	//TODO Fehlerüberprüfung beim erstellen der Views
	
	
	//Class Variables
	//contains Profile Information, Medicine und Symptoms!
	private String category; 
	//contains Data regarding to the childs -> Profile Information, 
	private ArrayList<String> profileInformation;
	private Activity activity;
	
	private ProfileCallBack profCall;
	
	
	public ProfileListAdapter(Activity act, String cat, ArrayList<String> data){
		this.activity = act;
		this.category = cat;
		this.profileInformation = data;
	}
	
	@Override
	public String getChild(int groupPosition, int childPosition) {
		return profileInformation.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	//TODO auf Profile umbauen
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		
			if(convertView == null){
				LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.profile_exlist_info_child, null);
			}
			
		TextView name = (TextView) convertView.findViewById(R.id.profile_exlist_info_child);
		name.setText(profileInformation.get(childPosition));
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return profileInformation.size();
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
		imageButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.edit_pen));
		imageButton.setFocusable(false);
		imageButton.setFocusableInTouchMode(false);
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO Edit-DialogFragment for Profile
				
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
	
	public void updateProfileData(ArrayList<String> profileInformation){
		this.profileInformation = profileInformation;
		notifyDataSetChanged();
	}

	public void setProfCallback(ProfileCallBack profCall){
		this.profCall = profCall;
	}
}

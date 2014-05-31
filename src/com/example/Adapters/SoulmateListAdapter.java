package com.example.Adapters;

import java.util.HashMap;
import java.util.List;

import com.example.CallbackInterfaces.SoulmateCallback;
import com.example.Models.Soulmates;
import com.example.UIContentFragments.ChatScreenContentFragment;
import com.example.UILayoutFragments.ProfileSoulmatesLayoutFragment;
import com.example.youapp.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SoulmateListAdapter extends BaseExpandableListAdapter {

	//TODO Fehlerüberprüfung beim erstellen der Views
	//TODO möglicherweise keinen ExpandableListAdapter nehmen 
	
	private final int PROPOSEDSOULMATES = 0;
	private final int MYSOULMATES = 1;
	//Class Variables
	//hier stehen Proposed Soulmates und MySoulmates drin!
	private List<String> categories; 
	//Hier stehen zu den Kategorien zugeordnet die Daten drin, also Nutzer?
	private HashMap<String, List<Soulmates>> childData;
	private Activity activity;
	
	private SoulmateCallback soulCall;
	
	public SoulmateListAdapter(Activity act, List<String> cats, HashMap<String, List<Soulmates>> data){
		this.activity = act;
		this.categories = cats;
		this.childData = data;
	}
	
	@Override
	public Soulmates getChild(int groupPosition, int childPosition) {
		return this.childData.get(this.categories.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	//erstmal getestet mit einer Child View, die den Text befüllt -> später mit anderen Daten machen
	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		
		Soulmates mate = getChild(groupPosition, childPosition);
		Log.i("EXLIST", "Group: "+ groupPosition + "; Child: " + childPosition);
//		if(convertView == null){
			if(groupPosition == MYSOULMATES){
				LayoutInflater infalInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.soulcontent_exlist_child_myown, null);
			}
			else {
				LayoutInflater infalInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.soulcontent_exlist_child_proposed, null);
			}
//		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.soulcontent_exlist_child_profinformation);
		tv.setText(mate.toString());
		//TODO Soulmate needs to reference an Image for the profile
		Drawable icon = activity.getResources().getDrawable(R.drawable.logo_zeichen);
		tv.setCompoundDrawablesRelativeWithIntrinsicBounds(icon, null, null, null);
		
		if(groupPosition == MYSOULMATES){
			ImageButton removeButton = (ImageButton) convertView.findViewById(R.id.soulcontent_exlist_child_removesoulmate);
			removeButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					soulCall.removeSoulmate(groupPosition, childPosition);
				}
			});
		}
		
		Button visit = (Button) convertView.findViewById(R.id.soulcontent_exlist_child_visitpage);
		visit.setText("Visit " + mate.getName() + "s page");
		visit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO Load other persons profile fragment -> new fragment?
				activity.getFragmentManager().beginTransaction().replace(R.id.container, new ProfileSoulmatesLayoutFragment()).commit();
				Toast.makeText(activity, "This should be " + ((Button)v).getText() + "s Profile", Toast.LENGTH_SHORT).show();
			}
		});
		
		Button write = (Button) convertView.findViewById(R.id.soulcontent_exlist_child_writeMessage);
		write.setText("Write " + mate.getName() + " a message.");
		write.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Set the field for the addressat from here
				activity.getFragmentManager().beginTransaction().replace(R.id.container, new ChatScreenContentFragment()).commit();
				Toast.makeText(activity, "Came here through " + ((Button)v).getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childData.get(categories.get(groupPosition)).size();
	}

	@Override
	public String getGroup(int groupPosition) {
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
		String categoryTitle = getGroup(groupPosition);
		
		if(convertView == null){
			LayoutInflater infalInflater = (LayoutInflater) 
                    activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.soulcontent_exlist_categories, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.soulcontent_category_text);
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
	
	public void updateListData(HashMap<String, List<Soulmates>> data){
		this.childData = data;
		notifyDataSetChanged();
	}
	
	public void setSoulCall(SoulmateCallback soulCall){
		this.soulCall = soulCall;
	}
}

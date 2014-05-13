package com.example.Adapters;

import java.util.HashMap;
import java.util.List;

import com.example.youapp.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class SoulmateListAdapter extends BaseExpandableListAdapter {

	//TODO Fehlerüberprüfung beim erstellen der Views
	
	
	//Class Variables
	//hier stehen Proposed Soulmates und MySoulamtes drin!
	private List<String> categories; 
	//Hier stehen zu den Kategorien zugeordnet die Daten drin, also Nutzer?
	private HashMap<String, List<Object>> childData;
	
	private Activity activity;
	
	
	public SoulmateListAdapter(Activity act, List<String> cats, HashMap<String, List<Object>> data){
		this.activity = act;
		this.categories = cats;
		this.childData = data;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return this.childData.get(this.categories.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	//erstmal getestet mit einer Child View, die den Text befüllt -> später mit anderen Daten machen
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// int id, String profinfo, String visit, String write
		List<Object> obj = (List<Object>) getChild(groupPosition, childPosition);
		
		if(convertView == null){
			LayoutInflater infalInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.soulcontent_exlist_child, null);
		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.soulcontent_exlist_child_profinformation);
		tv.setText((String)obj.get(1));
		Drawable icon = activity.getResources().getDrawable((Integer) obj.get(0));
		tv.setCompoundDrawablesRelativeWithIntrinsicBounds(icon, null, null, null);
		
		TextView visit = (TextView) convertView.findViewById(R.id.soulcontent_exlist_child_visitpage);
		visit.setText((String)obj.get(2));
		
		TextView write = (TextView) convertView.findViewById(R.id.soulcontent_exlist_child_writeMessage);
		write.setText((String) obj.get(3));
		
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

}

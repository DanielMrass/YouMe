package com.example.UIContentFragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.Adapters.SoulmateListAdapter;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class SoulmateContentScreenFragment extends Fragment {
	
	private List<String> categories = new ArrayList<String>();
	private HashMap<String, List<Object>> data = new HashMap<String, List<Object>>();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Soulmates");
		
		View rootView = inflater.inflate(R.layout.f_soulmate_content, container,
				false);
		
		ExpandableListView expv = (ExpandableListView) rootView.findViewById(R.id.soulcontent_exlist);
		expv.setClickable(false);
		//TODO die Daten noch über JSON einholen
		prepareTestData();
		SoulmateListAdapter slma = new SoulmateListAdapter(getActivity(), categories, data);
		expv.setAdapter(slma);
		return rootView;
	}

	private void prepareTestData() {
		//Add Categories
		categories.add("Proposed Soulmates");
		categories.add("My Soulmates");
		
		//create child Data for Categories
		List<Object> proposed = new ArrayList<Object>();
		for(int i = 0; i<2; i++){
			List<Object> temp = new ArrayList<Object>();
			temp.add(R.drawable.logo_zeichen);
			temp.add("Soulmate" + i);
			temp.add("Visit Soulmate "+i + " Page");
			temp.add("Write Soulmate " +i+" a message");
			proposed.add(temp);
		}
		
		List<Object> mysoulmates = new ArrayList<Object>();
		for(int j = 0; j<4; j++){
			List<Object> temp = new ArrayList<Object>();
			temp.add(R.drawable.logo_zeichen);
			temp.add("Soulmate" + j);
			temp.add("Visit Soulmate "+j + " Page");
			temp.add("Write Soulmate " +j+" a message");
			mysoulmates.add(temp);
		}
		
		//add the child data to the categories
		data.put(categories.get(0), proposed);
		data.put(categories.get(1), mysoulmates);
	}
}

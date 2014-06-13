package com.example.UIContentFragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.Adapters.SoulmateListAdapter;
import com.example.CallbackInterfaces.SoulmateCallback;
import com.example.Models.Soulmates;
import com.example.youapp.R;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class SoulmateContentScreenFragment extends Fragment implements SoulmateCallback{
	
	private List<String> categories = new ArrayList<String>();
	private HashMap<String, List<Soulmates>> data = new HashMap<String, List<Soulmates>>();
	private SoulmateListAdapter slma;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Soulmates");
		
		View rootView = inflater.inflate(R.layout.f_soulmate_content, container,
				false);
		
		ExpandableListView expv = (ExpandableListView) rootView.findViewById(R.id.soulcontent_exlist);
		expv.setClickable(false);
		//TODO die Daten noch über JSON einholen
		prepareTestData();
		slma = new SoulmateListAdapter(getActivity(), categories, data);
		slma.setSoulCall(this);
		expv.setAdapter(slma);
		return rootView;
	}

	private void prepareTestData() {
		//Add Categories
		categories.add("Proposed Soulmates");
		categories.add("My Soulmates");
		
		//create child Data for Categories
		List<Soulmates> proposed = new ArrayList<Soulmates>();
		for(int i = 0; i<2; i++){
			Soulmates temp = new Soulmates();
			temp.setName("Soulmate" + i);
			temp.setAge("" + (i+20));
			temp.setOrigin("Utopia");
			proposed.add(temp);
		}
		
		List<Soulmates> mysoulmates = new ArrayList<Soulmates>();
		for(int j = 0; j<4; j++){
			Soulmates temp= new Soulmates();
			temp.setName("Soulmate" + j);
			temp.setAge("" + (j+20));
			temp.setOrigin("Utopia");
			mysoulmates.add(temp);
		}
		
		//add the child data to the categories
		data.put(categories.get(0), proposed);
		data.put(categories.get(1), mysoulmates);
	}

	@Override
	public void removeSoulmate(int groupPosition, int childPosition) {
		data.get(categories.get(groupPosition)).remove(childPosition);
		slma.updateListData(data);
	}
}

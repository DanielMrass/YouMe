package com.example.UIContentFragments;

import java.util.ArrayList;

import com.example.Adapters.PostQuestionsOptionsAdapter;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class PostQuestionsContentScreenFragment extends Fragment implements OnClickListener {
		
		private ArrayList<String> optionsListData = new ArrayList<String>();
		private ListView optionsListView;
	
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			getActivity().getActionBar().setTitle("Post Questions");
			
			View rootView = inflater.inflate(R.layout.postquestion_content, container,
					false);
			
			optionsListView = (ListView) rootView.findViewById(R.id.postquestions_options_listview);
			createDummyData();
			PostQuestionsOptionsAdapter pqa = new PostQuestionsOptionsAdapter(getActivity(), optionsListData);
			optionsListView.setAdapter(pqa);
			
			Button addOptionButton = (Button) rootView.findViewById(R.id.postquestions_moreoptions_button);
			addOptionButton.setOnClickListener(this);
			return rootView;
		}

		private ArrayList<String> createDummyData() {
			optionsListData.add("Option 1");
			optionsListData.add("Option 2");
			return optionsListData;
		}

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.postquestions_moreoptions_button:
				PostQuestionsOptionsAdapter pqa = (PostQuestionsOptionsAdapter) optionsListView.getAdapter();
				pqa.notifyDataSetChanged();
				int count = pqa.getCount()+1;
				//TODO Daten aus den Views auslesen -> Child View ist ein Linear Layout, da die View per ID finden -> umständlcih! 
				break;
			}
		}
}

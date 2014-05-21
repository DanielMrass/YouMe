package com.example.UIContentFragments;

import java.util.ArrayList;

import com.example.Adapters.PostQuestionsOptionsAdapter;
import com.example.CallbackInterfaces.PostQuestionCallback;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class PostQuestionsContentScreenFragment extends Fragment implements OnClickListener, PostQuestionCallback {
		
		private ArrayList<String> optionsListData = new ArrayList<String>();
		private ListView optionsListView;
		private PostQuestionsOptionsAdapter cpqa;
	
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			getActivity().getActionBar().setTitle("Post Questions");
			
			View rootView = inflater.inflate(R.layout.postquestion_content, container,
					false);
			
			optionsListView = (ListView) rootView.findViewById(R.id.postquestions_options_listview);
			createDummyData();
//			PostQuestionsOptionsAdapter pqa = new PostQuestionsOptionsAdapter(getActivity(), optionsListData);
//			pqa.registerDataSetObserver(new DataSetObserver() {
//			});
			cpqa = new PostQuestionsOptionsAdapter(getActivity());
			cpqa.addAll(optionsListData);
			cpqa.setCallbackInterface(this);
			optionsListView.setAdapter(cpqa);
			
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
				int count = cpqa.getCount()+1;
				optionsListData.add("Option " + count);
				cpqa.clear();
				cpqa.addAll(optionsListData);
				break;
			}
		}

		@Override
		public void updateListData(int position, Editable s) {
			optionsListData.set(position, s.toString());
		}
}

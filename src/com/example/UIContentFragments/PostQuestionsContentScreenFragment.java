package com.example.UIContentFragments;

import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PostQuestionsContentScreenFragment extends Fragment {
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			getActivity().getActionBar().setTitle("Post Questions");
			
			View rootView = inflater.inflate(R.layout.postquestion_content, container,
					false);
			
			return rootView;
		}
}

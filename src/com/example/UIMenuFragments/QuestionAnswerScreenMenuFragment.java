package com.example.UIMenuFragments;

import com.example.UIContentFragments.AnswerQuestionsContentScreenFragment;
import com.example.UIContentFragments.PostQuestionsContentScreenFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class QuestionAnswerScreenMenuFragment extends Fragment implements OnCheckedChangeListener{
	
	ToggleButton postquestion;
	ToggleButton answerquestion;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.question_answer_menu_fragment, container,
				false);
		answerquestion = (ToggleButton) rootView.findViewById(R.id.qa_toggle_answerquestion);
		answerquestion.setChecked(true);
		answerquestion.setOnCheckedChangeListener(this);
		answerquestion.setClickable(false);
		postquestion = (ToggleButton) rootView.findViewById(R.id.qa_toggle_postquestion);
		postquestion.setOnCheckedChangeListener(this);
		
		return rootView;
	}

	@Override
	public void onCheckedChanged(CompoundButton button, boolean state) {
		switch(button.getId()){
		case R.id.qa_toggle_answerquestion:
			if(state){
				postquestion.setChecked(false);
				answerquestion.setClickable(false);
				postquestion.setClickable(true);
				//TODO Change to answer question fragment
				getFragmentManager().beginTransaction().replace(R.id.content_placeholder, new AnswerQuestionsContentScreenFragment()).commit();
			}
			break;
		case R.id.qa_toggle_postquestion:
			if(state){
				answerquestion.setChecked(false);
				answerquestion.setClickable(true);
				postquestion.setClickable(false);
				//TODO Change to post question fragment
				getFragmentManager().beginTransaction().replace(R.id.content_placeholder, new PostQuestionsContentScreenFragment()).commit();
			}
			break;
		}
	}
}

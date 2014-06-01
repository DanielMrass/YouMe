package com.example.UIContentFragments;

import java.util.ArrayList;

import com.example.Adapters.MyPostQuestionsAdapter;
import com.example.Adapters.PostQuestionsOptionsAdapter;
import com.example.CallbackInterfaces.MyPostQuestionsCallback;
import com.example.CallbackInterfaces.PostQuestionCallback;
import com.example.Models.PostQuestion;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class PostQuestionsContentScreenFragment extends Fragment implements OnClickListener, PostQuestionCallback, MyPostQuestionsCallback {
		
		private ArrayList<String> optionsListData = new ArrayList<String>();
		private PostQuestionsOptionsAdapter cpqa;
		private MyPostQuestionsAdapter myPQAdapter; 
		private ArrayList<PostQuestion> myPQs = new ArrayList<PostQuestion>();
		private EditText addQuestion;
	
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			getActivity().getActionBar().setTitle("Post Questions");
			
			View rootView = inflater.inflate(R.layout.postquestion_content, container,
					false);
			
			addQuestion = (EditText) rootView.findViewById(R.id.postquestions_etext_question);
			
			ListView optionsListView = (ListView) rootView.findViewById(R.id.postquestions_options_listview);
			createDummyData();
			cpqa = new PostQuestionsOptionsAdapter(getActivity());
			cpqa.addAll(optionsListData);
			cpqa.setCallbackInterface(this);
			optionsListView.setAdapter(cpqa);
			
			Button addOptionButton = (Button) rootView.findViewById(R.id.postquestions_moreoptions_button);
			addOptionButton.setOnClickListener(this);
			
			Button addQuestionButton = (Button) rootView.findViewById(R.id.postquestions_ok_button);
			addQuestionButton.setOnClickListener(this);
			
			Button skipQuestionButton = (Button) rootView.findViewById(R.id.postquestions_reset_button);
			skipQuestionButton.setOnClickListener(this);
			
			ExpandableListView myPostQuestionsListView = (ExpandableListView) rootView.findViewById(R.id.postquestions_my_questions_list);
			createDummyMyPostQuestions();
			myPQAdapter = new MyPostQuestionsAdapter(getActivity(), "My Post Questions", myPQs);
			myPQAdapter.setMyPostCall(this);
			myPostQuestionsListView.setAdapter(myPQAdapter);
			
			return rootView;
		}

		private void createDummyMyPostQuestions() {
			String question = "Why is the rum gone?";
			ArrayList<String> answers = new ArrayList<String>();
			answers.add("Thiefs");
			answers.add("Pirates");
			answers.add("Used for a big fire");
			PostQuestion pq = new PostQuestion(question, answers);
			myPQs.add(pq);
			
			String question2 = "What is love?";
			ArrayList<String> answers2 = new ArrayList<String>();
			answers2.clear();
			answers2.add("Baby dont hurt me");
			answers2.add("dont hurt me");
			answers2.add("no more");
			PostQuestion pq2 = new PostQuestion(question2, answers2);
			myPQs.add(pq2);
		}
		
		private void resetAddPostQuestion(){
			optionsListData.clear();
			createDummyData();
			cpqa.clear();
			cpqa.addAll(optionsListData);
			addQuestion.setText("");
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
			case R.id.postquestions_reset_button:
				resetAddPostQuestion();
				break;
			case R.id.postquestions_ok_button:
				addQuestion.requestFocus();
				Log.i("OPTIONS", optionsListData.toString());
				PostQuestion pq = new PostQuestion();
				pq.setQuestion(addQuestion.getText().toString());
				ArrayList<String> answers = new ArrayList<String>();
				answers.addAll(optionsListData);
				pq.setAnswer(answers);
				Log.i("OPTIONS", pq.getAnswers().toString());
				addPostQuestion(pq);
				break;
			}
		}

		@Override
		public void updateListData(int position, Editable s) {
			this.optionsListData.set(position, s.toString());
			Log.i("TEXTCHANGE", "Position " + position +" changed into " + s.toString());
		}

		@Override
		public void deletePostQuestion(int position) {
			myPQs.remove(position);
			myPQAdapter.updateListData(myPQs);
		}

		@Override
		public void addPostQuestion(PostQuestion pq) {
			myPQs.add(pq);
			myPQAdapter.updateListData(myPQs);
			resetAddPostQuestion();
		}
}

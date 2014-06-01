package com.example.Adapters;

import java.util.ArrayList;

import com.example.CallbackInterfaces.MyPostQuestionsCallback;
import com.example.Models.PostQuestion;
import com.example.youapp.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyPostQuestionsAdapter extends BaseExpandableListAdapter {

	private LayoutInflater inflater;
	private MyPostQuestionsCallback myPostCallback;
	
	private String category;
	private ArrayList<PostQuestion> postQuestions;
	
	public MyPostQuestionsAdapter(Context context, String cat, ArrayList<PostQuestion> questions) {
		this.category = cat;
		this.postQuestions = questions;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public PostQuestion getChild(int group, int child) {
		return postQuestions.get(child);
	}

	@Override
	public long getChildId(int group, int child) {
		return child;
	}

	@Override
	public View getChildView(int group, final int child, boolean isLastChild, View convertView,
			ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.my_post_answers_list_item, null);
		}
		
		PostQuestion pq = getChild(group, child);
		
		TextView question = (TextView) convertView.findViewById(R.id.postAnswersListItem_question);
		question.setText("Q: " + pq.getQuestion());
		
		TextView answers = (TextView) convertView.findViewById(R.id.postAnswersListItem_answer);
		String answersString = "";
		ArrayList<String> answerStrings = pq.getAnswers();
		Log.i("ANSWERS", answerStrings.toString());
		for(int i = 0;i<answerStrings.size(); i++){
			if(i==0){
				answersString = answerStrings.get(i);
			}
			else{
				answersString = answersString + ", " + answerStrings.get(i);
			}
		}
		answers.setText("A: "+answersString);
		
		ImageButton delete = (ImageButton) convertView.findViewById(R.id.postAnswersListItem_delete_answer);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myPostCallback.deletePostQuestion(child);
			}
		});
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int group) {
		return postQuestions.size();
	}

	@Override
	public String getGroup(int group) {
		return category;
	}

	@Override
	public int getGroupCount() {
		return 1;
	}

	@Override
	public long getGroupId(int group) {
		return group;
	}

	@Override
	public View getGroupView(int group, boolean isExpanded, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.my_post_answers_list_category, null);
		}
		String categoryTitle = getGroup(group);
		TextView tv = (TextView) convertView.findViewById(R.id.my_post_answers_list_category_label);
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
	
	public void setMyPostCall(MyPostQuestionsCallback myPostCall){
		this.myPostCallback = myPostCall;
	}
	
	public void updateListData(ArrayList<PostQuestion> postQuestions){
		this.postQuestions = postQuestions;
		notifyDataSetChanged();
	}
}

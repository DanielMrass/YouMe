package com.example.Adapters;

import java.util.ArrayList;

import com.example.CallbackInterfaces.MyAnswerCallback;
import com.example.Models.AnsweredQuestion;
import com.example.youapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyAnswersListAdapter extends BaseExpandableListAdapter {

	private LayoutInflater inflater;
	private MyAnswerCallback myAnswCall;
	
	private String category;
	private ArrayList<AnsweredQuestion> answeredQuestions;
	
	public MyAnswersListAdapter(Context context, String cat, ArrayList<AnsweredQuestion> answeredQs) {
		this.category = cat;
		this.answeredQuestions = answeredQs;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getGroupView(int group, boolean isExpanded, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.my_answers_list_category, null);
		}
		String categoryTitle = getGroup(group);
		TextView tv = (TextView) convertView.findViewById(R.id.my_answers_list_category_label);
		tv.setText(categoryTitle);
		
		return convertView;
	}
	

	@Override
	public View getChildView(int group, final int child, boolean isLastChild, View convertView,
			ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.my_answers_list_item, null);
		}
		
		AnsweredQuestion aq = getChild(group, child);
		
		TextView question = (TextView) convertView.findViewById(R.id.answersListItem_question);
		question.setText(aq.getQuestion());
		
		TextView answer = (TextView) convertView.findViewById(R.id.answersListItem_answer);
		answer.setText(aq.getAnswer());
		
		ImageButton delete = (ImageButton) convertView.findViewById(R.id.answersListItem_delete_answer);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myAnswCall.deleteAnsweredQuestionFromList(child);
			}
		});
		return convertView;
	}
	
	public void setMyAnswerCallback(MyAnswerCallback myAnswerCall){
		this.myAnswCall = myAnswerCall;
	}
	
	public void updateListData(ArrayList<AnsweredQuestion> answeredQuestions){
		this.answeredQuestions = answeredQuestions;
		notifyDataSetChanged();
	}

	@Override
	public AnsweredQuestion getChild(int group, int child) {
		return answeredQuestions.get(child);
	}

	@Override
	public long getChildId(int group, int child) {
		return child;
	}

	@Override
	public int getChildrenCount(int group) {
		return answeredQuestions.size()	;
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
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int group, int child) {
		return true;
	}
}

package com.example.Adapters;

import com.example.CallbackInterfaces.MyAnswerCallback;
import com.example.Models.AnsweredQuestion;
import com.example.youapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyAnswersListAdapter extends ArrayAdapter<AnsweredQuestion> {

	private LayoutInflater inflater;
	private MyAnswerCallback myAnswCall;
	
	public MyAnswersListAdapter(Context context) {
		super(context, 1);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.my_answers_list_item, null);
		}
		
		TextView question = (TextView) convertView.findViewById(R.id.answersListItem_question);
		question.setText(getItem(position).getQuestion());
		
		TextView answer = (TextView) convertView.findViewById(R.id.answersListItem_answer);
		answer.setText(getItem(position).getAnswer());
		
		ImageButton delete = (ImageButton) convertView.findViewById(R.id.answersListItem_delete_answer);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myAnswCall.deleteAnsweredQuestionFromList(position);
			}
		});
		return convertView;
	}
	
	public void setMyAnswerCallback(MyAnswerCallback myAnswerCall){
		this.myAnswCall = myAnswerCall;
	}
}

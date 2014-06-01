package com.example.Adapters;


import com.example.CallbackInterfaces.PostQuestionCallback;
import com.example.youapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class PostQuestionsOptionsAdapter extends ArrayAdapter<String> {
	
	private PostQuestionCallback pqc;
	private LayoutInflater inflater;
	
	public PostQuestionsOptionsAdapter(Context context) {
		super(context, 1);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.postquestions_adapter_item, null);
		}
		
		final EditText et = (EditText) convertView.findViewById(R.id.postquestion_option_text);
		et.setText(getItem(position));
		et.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				pqc.updateListData(position, et.getText());
			}
		});
		return convertView;
	}
	
	public void setCallbackInterface(PostQuestionCallback pqc){
		this.pqc = pqc;
	}
}

package com.example.Adapters;

import java.util.ArrayList;

import com.example.youapp.R;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class PostQuestionsOptionsAdapter extends BaseAdapter {

	private ArrayList<String> optionsList = new ArrayList<String>();
	

	private Activity activity;
	
	public PostQuestionsOptionsAdapter(Activity act, ArrayList<String> data){
		this.activity=act;
		this.optionsList = data;
		notifyDataSetChanged();
	}
	
	
	@Override
	public int getCount() {
		return optionsList.size();
	}

	@Override
	public Object getItem(int position) {
		return optionsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.postquestions_adapter_item, null);
//		}
		
		EditText et = (EditText) convertView.findViewById(R.id.postquestion_option_text);
		et.setText(optionsList.get(position));
		return convertView;
	}

	public void updateResults(ArrayList<String> newData){
		this.optionsList = newData;
		notifyDataSetChanged();
	}
	
	public ArrayList<String> getOptionsList() {
		return optionsList;
	}
}

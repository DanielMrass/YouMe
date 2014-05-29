package com.example.Adapters;


import com.example.CallbackInterfaces.SymptomCallBack;
import com.example.youapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class SymptomsAdapter extends ArrayAdapter<String> {
	
	private SymptomCallBack sympCall;
	private LayoutInflater inflater;
	
	public SymptomsAdapter(Context context){
		super(context, 1);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.symptom_medicine_list_item, null);
		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.registration_list_child_text);
		tv.setText(getItem(position));
		
		ImageButton imgButton = (ImageButton) convertView.findViewById(R.id.registration_list_child_image);
		imgButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sympCall.deleteSymptomFromList(position);
			}
		});
		
		return convertView;
	}

	public void setSympCallback(SymptomCallBack symCall){
		this.sympCall = symCall;
	}
}

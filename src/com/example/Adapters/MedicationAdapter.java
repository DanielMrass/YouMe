package com.example.Adapters;


import com.example.CallbackInterfaces.MedicineCallBack;
import com.example.youapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class MedicationAdapter extends ArrayAdapter<String> {
	
	private MedicineCallBack medCall;
	private LayoutInflater inflater;
	
	
	public MedicationAdapter(Context context) {
		super(context, 1);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
				medCall.deleteMedicineFromList(position);
			}
		});
		
		return convertView;
	}

	public void setMedCallBack(MedicineCallBack medCall){
		this.medCall = medCall;
	}
}

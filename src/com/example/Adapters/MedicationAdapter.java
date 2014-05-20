package com.example.Adapters;

import java.util.ArrayList;
import java.util.Collection;

import com.example.youapp.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicationAdapter extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<String> medicationCollection;


	static class ViewHolder{
		public TextView text;
		public ImageButton image;
	}
	
	public MedicationAdapter(Activity act, ArrayList<String> symptomCollection){
		this.activity = act;
		this.medicationCollection = symptomCollection;
	}
	
	@Override
	public int getCount() {
		return medicationCollection.size();
	}

	@Override
	public Object getItem(int position) {
		return medicationCollection.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null){
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.symptom_medicine_list_item, null);
			ViewHolder holder = new ViewHolder();
			holder.text = (TextView) rowView.findViewById(R.id.registration_list_child_text);
			holder.image = (ImageButton) rowView.findViewById(R.id.registration_list_child_image);
			rowView.setTag(holder);
		}
		
		ViewHolder hold = (ViewHolder) rowView.getTag();
		hold.text.setText(medicationCollection.get(position));
		Drawable icon = activity.getResources().getDrawable(R.drawable.delete_icon_21);
		hold.image.setImageDrawable(icon);
		hold.image.setTag(position);
		Log.i("IMAGEBUTTON", ""+(Integer) hold.image.getTag());
		hold.image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("IMAGEBUTTON v", ""+(Integer) v.getTag());
				int index = (Integer) v.getTag(); 
				medicationCollection.remove(index);
				notifyDataSetChanged();
				Log.i("Medication", medicationCollection.toString());
			}
		});
		
		return rowView;
	}

}

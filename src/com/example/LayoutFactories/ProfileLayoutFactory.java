package com.example.LayoutFactories;

import java.util.Vector;

import com.example.youapp.R;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileLayoutFactory {
	private Activity activity;
	
	public ProfileLayoutFactory(Activity activity){
		this.activity = activity;
	}
	public LinearLayout getProfileContent(Vector<String> values){
		LinearLayout layout = new LinearLayout(activity);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVisibility(View.GONE);
		layout.setId(R.id.profcontent_expandable_info);
		int counter=0; 
		for(String s : values){
			TextView tv_name = new TextView(activity);
			tv_name.setText(values.get(counter));
			tv_name.setLayoutParams(params);
			layout.addView(tv_name);
			counter ++;
		}
		return layout;
	}
}

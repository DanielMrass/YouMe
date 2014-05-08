package com.example.LayoutFactories;

import java.util.Vector;

import com.example.youapp.R;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ProfileSoulmateLayoutFactory {
	private Activity activity;
	
	public ProfileSoulmateLayoutFactory(Activity activity){
		this.activity = activity;
	}
	public LinearLayout getProfileContent(Vector<String> values){
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		//Layout for the Profile Information
		LinearLayout layout = new LinearLayout(activity);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setId(R.id.profcontent_expandable_info);
		layout.setVisibility(View.GONE);
		for(String s : values){
			TextView tv_name = new TextView(activity);
			tv_name.setText(s);
			tv_name.setLayoutParams(params);
			layout.addView(tv_name);
		}
		
		//EditButton for profile
		ImageButton edit_button = new ImageButton(activity);
		edit_button.setId(R.id.profcontent_profile_edit_button);
		layout.addView(edit_button);
		LinearLayout.LayoutParams button_params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		button_params.gravity = 5;
		button_params.width = LayoutParams.WRAP_CONTENT;
		button_params.height = LayoutParams.MATCH_PARENT;
		edit_button.setLayoutParams(button_params);
		edit_button.setImageResource(R.drawable.edit_pen);
		return layout;
	}
	
	
}

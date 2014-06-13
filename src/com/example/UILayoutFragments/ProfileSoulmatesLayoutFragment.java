package com.example.UILayoutFragments;

import com.example.UIContentFragments.ChatScreenContentFragment;
import com.example.UIContentFragments.ProfileContentScreenFragment;
import com.example.UIMenuFragments.FooterFragment;
import com.example.UIMenuFragments.ProfileSoulmateScreensMenuFragment;
import com.example.youapp.R;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ProfileSoulmatesLayoutFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.f_standard_layout, container,
				false);
		getFragmentManager().beginTransaction().replace(R.id.menu_placeholder, new ProfileSoulmateScreensMenuFragment()).commit();
		getFragmentManager().beginTransaction().replace(R.id.content_placeholder, new ProfileContentScreenFragment()).commit();
		getFragmentManager().beginTransaction().replace(R.id.footer_placeholder, new FooterFragment()).commit();
		
		getActivity().getActionBar().setTitle("Your Profile");
		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		//super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.chat_menu_icon:
			getFragmentManager().beginTransaction().replace(R.id.container, new ChatScreenContentFragment()).commit();
			return true;
		case R.id.question_answer_menu_icon:
			//TODO Question Fragment erstellen und aufrufen
			getFragmentManager().beginTransaction().replace(R.id.container, new QuestionAnswerLayoutFragment()).commit();
			return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}

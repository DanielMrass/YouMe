package com.example.UIContentFragments;

import com.example.UILayoutFragments.ProfileSoulmatesLayoutFragment;
import com.example.UILayoutFragments.QuestionAnswerLayoutFragment;
import com.example.UIMenuFragments.FooterFragment;
import com.example.youapp.R;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ChatScreenContentFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		getActivity().getActionBar().setTitle("Messages");
		
		View rootView = inflater.inflate(R.layout.chat_screen_content, container, false);
		
		getFragmentManager().beginTransaction().replace(R.id.footer_content_placeholder, new FooterFragment()).commit();
		
		return rootView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.profile_soulmate_menu_icon:
			getFragmentManager().beginTransaction().replace(R.id.container, new ProfileSoulmatesLayoutFragment()).commit();
			return true;
		case R.id.question_answer_menu_icon:
			getFragmentManager().beginTransaction().replace(R.id.container, new QuestionAnswerLayoutFragment()).commit();
			return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}

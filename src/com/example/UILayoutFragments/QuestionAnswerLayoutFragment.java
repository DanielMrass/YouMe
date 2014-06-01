package com.example.UILayoutFragments;

import com.example.UIContentFragments.AnswerQuestionsContentScreenFragment;
import com.example.UIContentFragments.ChatScreenContentFragment;
import com.example.UIMenuFragments.FooterFragment;
import com.example.UIMenuFragments.QuestionAnswerScreenMenuFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class QuestionAnswerLayoutFragment extends Fragment{
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.f_standard_layout, container,
				false);
		getFragmentManager().beginTransaction().replace(R.id.menu_placeholder, new QuestionAnswerScreenMenuFragment()).commit();
		getFragmentManager().beginTransaction().replace(R.id.content_placeholder, new AnswerQuestionsContentScreenFragment()).commit();
		getFragmentManager().beginTransaction().replace(R.id.footer_placeholder, new FooterFragment()).commit();
		
		getActivity().getActionBar().setTitle("Answer Questions");
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
		case R.id.profile_soulmate_menu_icon:
			getFragmentManager().beginTransaction().replace(R.id.container, new ProfileSoulmatesLayoutFragment()).commit();
			return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
}

package com.example.UIContentFragments;

import com.example.UILayoutFragments.ProfileSoulmatesLayoutFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class AnswerQuestionsContentScreenFragment extends Fragment{
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Answer Questions");
		
		View rootView = inflater.inflate(R.layout.answerquestion_content, container,
				false);
		
		
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

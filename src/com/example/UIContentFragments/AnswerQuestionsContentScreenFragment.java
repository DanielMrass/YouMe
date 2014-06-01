package com.example.UIContentFragments;

import java.util.ArrayList;

import com.example.Adapters.MyAnswersListAdapter;
import com.example.CallbackInterfaces.MyAnswerCallback;
import com.example.Models.AnsweredQuestion;
import com.example.UILayoutFragments.ProfileSoulmatesLayoutFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class AnswerQuestionsContentScreenFragment extends Fragment implements OnClickListener, OnItemSelectedListener, OnSeekBarChangeListener, MyAnswerCallback{
	
	private Spinner questionSpinner;
	private RadioGroup answersGroup;
	private Button okButton;
	private Button skipButton;
	private SeekBar importanceRating;
	private ArrayList<AnsweredQuestion> myAnsweredQuestionsList = new ArrayList<AnsweredQuestion>();
	private MyAnswersListAdapter myAnswersAdapter;
	
	//Positions for the dummy values
	private final int COLORQUESTION = 0;
	private final int FOODQUESTION = 1;
	private final int CARQUESTION = 2;
	private final int SPORTSQUESTION = 3;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Answer Questions");
		
		View rootView = inflater.inflate(R.layout.answerquestion_content, container,
				false);
		
		questionSpinner = (Spinner) rootView.findViewById(R.id.answerquestion_question_spinner);
		ArrayList<String> data = createDummyQuestions();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		questionSpinner.setAdapter(adapter);
		questionSpinner.setOnItemSelectedListener(this);
		
		answersGroup = (RadioGroup) rootView.findViewById(R.id.answerquestion_radio_button_group);
		fillRadioGroup(createDummyAnswers(COLORQUESTION));
		
		importanceRating = (SeekBar) rootView.findViewById(R.id.answerquestion_rating_bar);
		importanceRating.setOnSeekBarChangeListener(this);
		
		okButton = (Button) rootView.findViewById(R.id.answerquestion_ok_button);
		okButton.setOnClickListener(this);
		
		skipButton = (Button) rootView.findViewById(R.id.answerquestion_skip_button);
		skipButton.setOnClickListener(this);
		
		ExpandableListView myAnswersList = (ExpandableListView) rootView.findViewById(R.id.answerquestions_my_answers_list);
		createDummyAnsweredQuestions();
		myAnswersAdapter = new MyAnswersListAdapter(getActivity(), "My Answers", myAnsweredQuestionsList);
		myAnswersAdapter.setMyAnswerCallback(this);
		myAnswersList.setAdapter(myAnswersAdapter);
		
		return rootView;
	}

	private void createDummyAnsweredQuestions() {
		for(int i = 0; i<5; i++){
			AnsweredQuestion aq = new AnsweredQuestion("Question "+i, "Answer " +i);
			myAnsweredQuestionsList.add(aq);
		}
	}

	private ArrayList<String> createDummyAnswers(int position) {
		ArrayList<String> answers = new ArrayList<String>(); 
		switch(position){
		case COLORQUESTION:
			answers.add("green");
			answers.add("yellow");
			answers.add("black");
			answers.add("blue");
			answers.add("red");
			break;
		case FOODQUESTION:
			answers.add("Spaghetti");
			answers.add("Hamburgers");
			answers.add("French Fries");
			answers.add("Cookies");
			answers.add("Lasagne");
			answers.add("Soup");
			answers.add("Pizza");
			break;
		case CARQUESTION:
			answers.add("Mercedes");
			answers.add("Porsche");
			answers.add("BMW");
			answers.add("Ferrari");
			break;
		case SPORTSQUESTION:
			answers.add("Soccer");
			answers.add("Basketball");
			answers.add("Ice Hockey");
			answers.add("Baseball");
			answers.add("American Football");
			break;
		}
		return answers;
	}

	private void fillRadioGroup(ArrayList<String> data) {
		Log.i("ANSWERS", data.toString());
		answersGroup.removeAllViews();
		for(String s : data){
			RadioButton rb = new RadioButton(getActivity());
			rb.setText(s);
			rb.setTag(s);
			answersGroup.addView(rb);
		}
	}

	private ArrayList<String> createDummyQuestions() {
		ArrayList<String> questions = new ArrayList<String>();
		questions.add("What is your favourite color?");
		questions.add("What is your favourite food?");
		questions.add("What is your favourite car?");
		questions.add("What is your favourite sports?");
		return questions;
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


	@Override
	public void onClick(View v) {
//		switch(v.getId()){
//		}
	}

	//OnItemSelectedListener
	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		Toast.makeText(getActivity(), (String)parent.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
		switch(pos){
		case COLORQUESTION:
			fillRadioGroup(createDummyAnswers(COLORQUESTION));
			break;
		case FOODQUESTION:
			fillRadioGroup(createDummyAnswers(FOODQUESTION));
			break;
		case SPORTSQUESTION:
			fillRadioGroup(createDummyAnswers(SPORTSQUESTION));
			break;
		case CARQUESTION:
			fillRadioGroup(createDummyAnswers(CARQUESTION));
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
	
	//Methods for SeekBarListener
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		Toast.makeText(getActivity(), "Importance now is: " + seekBar.getProgress()  , Toast.LENGTH_SHORT).show();
	}

	@Override
	public void deleteAnsweredQuestionFromList(int position) {
		myAnsweredQuestionsList.remove(position);
		myAnswersAdapter.updateListData(myAnsweredQuestionsList);
	}
}

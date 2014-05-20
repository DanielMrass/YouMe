package com.example.UIContentFragments;

import java.util.ArrayList;
import java.util.List;

import com.example.Adapters.MedicationAdapter;
import com.example.Adapters.SymptomsAdapter;
import com.example.UILayoutFragments.ConfirmationLayoutFragment;
import com.example.youapp.R;

import UIDialogFragments.DatePickerFragment;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationContentScreenFragment extends Fragment implements OnItemSelectedListener, OnClickListener, OnFocusChangeListener {
	private Spinner spinner;
	private View rootView;
	private Button reg_button;
	private CheckBox reg_cbox;
	private EditText name_text_box;
	private EditText email_text_box;
	private EditText nickname_text_box;
	private EditText birthday_text_box;
	private EditText city_text_box;
	private EditText plz_text_box;
	private ListView symptomListView;
	private ImageButton add_symptom_button;
	
	private ArrayList<String> symptoms = new ArrayList<String>();
	private ArrayList<String> medicines = new ArrayList<String>();
	private ListView medicationListView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle("Registration");
		
		rootView = inflater.inflate(R.layout.f_registration, container,
				false);
	
		name_text_box = (EditText) rootView.findViewById(R.id.reg_box_name); 
		email_text_box = (EditText) rootView.findViewById(R.id.reg_box_email);
		nickname_text_box = (EditText) rootView.findViewById(R.id.reg_box_nickname);
		birthday_text_box = (EditText) rootView.findViewById(R.id.reg_box_birthday);
		birthday_text_box.setOnFocusChangeListener(this);
		city_text_box = (EditText) rootView.findViewById(R.id.reg_box_city);
		plz_text_box = (EditText) rootView.findViewById(R.id.reg_box_plz);
		
		spinner = (Spinner) rootView.findViewById(R.id.reg_box_country);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.reg_countries_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
		reg_button = (Button) rootView.findViewById(R.id.reg_button_reg);
		reg_button.setEnabled(false);
		reg_button.setOnClickListener(this);
		
		reg_cbox = (CheckBox) rootView.findViewById(R.id.reg_cbox_toc);
		reg_cbox.setOnClickListener(this);
		
		TextView toc_labelText = (TextView) rootView.findViewById(R.id.reg_label_cbox);
		toc_labelText.setMovementMethod(LinkMovementMethod.getInstance());
		
		symptomListView = (ListView) rootView.findViewById(R.id.reg_list_symptoms);
		createDummySymptoms();
		SymptomsAdapter sma = new SymptomsAdapter(getActivity(), symptoms);
		symptomListView.setAdapter(sma);
		
		ImageButton imgButton = (ImageButton) rootView.findViewById(R.id.reg_add_symptoms_button);
		imgButton.setOnClickListener(this);
		
		medicationListView = (ListView)rootView.findViewById(R.id.reg_list_medicines);
		createDummyMedication();
		MedicationAdapter ma = new MedicationAdapter(getActivity(), medicines);
		medicationListView.setAdapter(ma);
		
		ImageButton addMedicine = (ImageButton) rootView.findViewById(R.id.reg_add_medicine_button);
		addMedicine.setOnClickListener(this);
		
		return rootView;
	}
	
	private void createDummyMedication() {
		medicines.add("Coffee");
		medicines.add("More Coffee");
		medicines.add("...");
	}

	private void createDummySymptoms() {
		symptoms.add("cleverness");
		symptoms.add("clear speech");
		symptoms.add("good look");
	}

	public void showDatePicker(){
		DialogFragment df  = new DatePickerFragment();
		df.show(getFragmentManager(), "datePicker");
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos,
			long id) {
		Toast.makeText(getActivity(), (String) parent.getItemAtPosition(pos), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.reg_button_reg:
//			Toast.makeText(getActivity(), "trololololololololol isengard! oink!", Toast.LENGTH_LONG).show();
			getFragmentManager().beginTransaction().replace(R.id.container, new ConfirmationLayoutFragment()).commit();
			break;
		case R.id.reg_cbox_toc:
			if (reg_cbox.isChecked()){
            	reg_button = (Button) rootView.findViewById(R.id.reg_button_reg);
            	reg_button.setEnabled(true);
            }
            else{
            	reg_button = (Button) rootView.findViewById(R.id.reg_button_reg);
            	reg_button.setEnabled(false);
            }
			break;
		case R.id.reg_add_symptoms_button:
			//TODO Symptom hinzufügen
			break;
		case R.id.reg_add_medicine_button:
			//TODO Medizin hinzufügen
			break;
		}
	}
	
	public void giveBackMyBirthday(String date){
		birthday_text_box.setText(date);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch(v.getId()){
			case R.id.reg_box_birthday:
				if(hasFocus){
					showDatePicker();
				}
				break;
		}
	}
}

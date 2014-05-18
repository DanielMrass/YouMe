package com.example.UIContentFragments;

import java.util.List;

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
	
	private List<String> symptoms;
	private List<String> medicines;

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
		
		//TODO Symptome + Medizin noch in XML richtig definieren
		//einfach an eine ViewGroup(LinearLayout) vertical ne View als Child dranpacken für jedes Symptom
		//einfach Werte als Liste nebenan tracken
		
		spinner = (Spinner) rootView.findViewById(R.id.reg_box_country);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.reg_countries_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
		reg_button = (Button) rootView.findViewById(R.id.reg_button_reg);
		reg_button.setEnabled(false);
		reg_button.setOnClickListener(this);
		
		reg_cbox = (CheckBox) rootView.findViewById(R.id.reg_cbox_toc);
		reg_cbox.setOnClickListener(this);
		
		TextView toc_labelText = (TextView) rootView.findViewById(R.id.reg_label_cbox);
		toc_labelText.setMovementMethod(LinkMovementMethod.getInstance());
		
		return rootView;
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
			Toast.makeText(getActivity(), "trololololololololol isengard! oink!", Toast.LENGTH_LONG).show();
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

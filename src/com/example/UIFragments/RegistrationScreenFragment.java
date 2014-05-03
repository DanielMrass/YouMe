package com.example.UIFragments;

import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationScreenFragment extends Fragment implements OnItemSelectedListener, OnClickListener {
	private Spinner spinner;
	private View rootView;
	private Button reg_button;
	private CheckBox reg_cbox;
	private View name_text_box;
	private View email_text_box;
	private View nickname_text_box;
	private View birthday_text_box;
	private View country_text_box;
	private View city_text_box;
	private View plz_text_box;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.f_registration, container,
				false);
	
		name_text_box = rootView.findViewById(R.id.reg_box_name); 
		email_text_box = rootView.findViewById(R.id.reg_box_email);
		nickname_text_box = rootView.findViewById(R.id.reg_box_nickname);
		birthday_text_box = rootView.findViewById(R.id.reg_box_birthday);
		country_text_box = rootView.findViewById(R.id.reg_box_country);
		city_text_box = rootView.findViewById(R.id.reg_box_city);
		plz_text_box = rootView.findViewById(R.id.reg_box_plz);
		
		//TODO Symptome + Medizin noch in XML richtig definieren
		
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
		
		return rootView;
	}
	
	public String showDatePicker(){
		
		return "";
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
}

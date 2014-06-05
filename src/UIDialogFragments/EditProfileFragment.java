package UIDialogFragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.example.CallbackInterfaces.ProfileCallBack;
import com.example.youapp.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class EditProfileFragment extends DialogFragment {
	
	private ProfileCallBack profCall;
	private EditText name;
	private Spinner origin;
	private RadioGroup gender;
	private DatePicker birthday;
	private EditText mailAddy;
	private RadioGroup mailVisible;
	
	private Date birthdayDate;
	
	private HashMap<String, String> data = new HashMap<String, String>();
	private RadioButton male;
	private RadioButton female;
	private SimpleDateFormat sdf;
	private RadioButton visible;
	private RadioButton invisible;
	
	@SuppressWarnings("unchecked")
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View rootView = inflater.inflate(R.layout.edit_profile, null);
		
		Bundle bundle = getArguments();
		
		if(bundle != null){
			data = (HashMap<String, String>) bundle.getSerializable("profileMap");
			Log.i("EDITPROFILE", data.toString());
		}else {
			EditProfileFragment.this.getDialog().cancel();
		}
		//TODO Views instantiieren.
		
		name = (EditText) rootView.findViewById(R.id.editprofile_name_etext);
		name.setText(data.get("name"));
		
		origin = (Spinner) rootView.findViewById(R.id.editprofile_origin_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.reg_countries_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		origin.setAdapter(adapter);
		origin.setSelection(adapter.getPosition(data.get("origin")));
		
		gender = (RadioGroup) rootView.findViewById(R.id.editprofile_gender_radio);
		male = new RadioButton(getActivity());
		male.setText("male");
		female = new RadioButton(getActivity());
		female.setText("female");
		gender.addView(male);
		gender.addView(female);
		if(data.get("gender").equalsIgnoreCase("male")){
			gender.check(male.getId());
		}else{
			gender.check(female.getId());
		}
		
		birthday = (DatePicker) rootView.findViewById(R.id.editprofile_birthday_picker);
		sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			birthdayDate = sdf.parse(data.get("birthday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(birthdayDate);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        birthday.init(year, month, day, new OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				birthdayDate = new Date(view.getCalendarView().getDate());
			}
		});
        birthday.setCalendarViewShown(false);
		
		mailAddy = (EditText) rootView.findViewById(R.id.editprofile_mail_extext);
		mailAddy.setText(data.get("mail"));
		
		mailVisible = (RadioGroup) rootView.findViewById(R.id.editprofile_mailVisible_radio);
		visible = new RadioButton(getActivity());
		visible.setText("Yes");
		invisible = new RadioButton(getActivity());
		invisible.setText("No");
		mailVisible.addView(visible);
		mailVisible.addView(invisible);
		if(data.get("mailVisible").equalsIgnoreCase("true")){
			mailVisible.check(visible.getId());
		}else{
			mailVisible.check(invisible.getId());
		}
		
		builder.setView(rootView);
		builder.setTitle("Add Symptom:");
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				updateData();
				profCall.updateProfile(data);
			}
		});
		builder.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditProfileFragment.this.getDialog().cancel();
			}
		});
		return builder.create();
	}
	
	protected void updateData() {
		data.clear();
		data.put("name", name.getText().toString());
		data.put("origin", (String)origin.getSelectedItem());
		if(gender.getCheckedRadioButtonId() == male.getId()){
			data.put("gender", "male");
		}
		else {
			data.put("gender", "female");
		}
		data.put("birthday", sdf.format(birthdayDate));
		data.put("mail", mailAddy.getText().toString());
		if(mailVisible.getCheckedRadioButtonId() == visible.getId()){
			data.put("mailVisible", "true");
		}
		else {
			data.put("mailVisible", "false");
		}
		Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_SHORT).show();
	}

	public void setProfCall(ProfileCallBack profCall){
		this.profCall = profCall;
	}
}

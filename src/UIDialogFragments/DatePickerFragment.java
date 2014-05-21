package UIDialogFragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.CallbackInterfaces.BirthdayCallBack;
import com.example.UIContentFragments.RegistrationContentScreenFragment;
import com.example.youapp.R;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {
	
	private BirthdayCallBack birthCall;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker picker, int year, int month, int day) {
		final Date c = new Date(picker.getCalendarView().getDate());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(c);
		Log.i("DIALOG", date);
//		RegistrationContentScreenFragment rcsf = (RegistrationContentScreenFragment) getFragmentManager().findFragmentByTag("registrationFragment");
//		rcsf.giveBackMyBirthday(date);
		birthCall.giveBackMyBirthday(date);
	}
	
	public void setBirthdayCallback(BirthdayCallBack birthCall){
		this.birthCall = birthCall;
	}
	
}

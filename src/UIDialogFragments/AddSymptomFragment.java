package UIDialogFragments;

import com.example.CallbackInterfaces.SymptomCallBack;
import com.example.youapp.R;

import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.*;

public class AddSymptomFragment extends DialogFragment {
	
	private SymptomCallBack sympCall;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View rootView = inflater.inflate(R.layout.add_symptom, null);
		final EditText et = (EditText) rootView.findViewById(R.id.add_symptom_text);
		
		builder.setView(rootView);
		builder.setTitle("Add Symptom:");
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				sympCall.addSymptom(et.getText().toString());
			}
		});
		builder.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AddSymptomFragment.this.getDialog().cancel();
			}
		});
		return builder.create();
	}
	
	public void setSympCall(SymptomCallBack sympCall){
		this.sympCall = sympCall;
	}
}

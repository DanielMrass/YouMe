package UIDialogFragments;


import com.example.CallbackInterfaces.MedicineCallBack;
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

public class AddMedicinesFragment extends DialogFragment {
	
	private MedicineCallBack medCall;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View rootView = inflater.inflate(R.layout.add_medicine, null);
		final EditText et = (EditText) rootView.findViewById(R.id.add_medicine_text);
		
		builder.setView(rootView);
		builder.setTitle("Add Medicine:");
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				medCall.addMedicine(et.getText().toString());
			}
		});
		builder.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AddMedicinesFragment.this.getDialog().cancel();
			}
		});
		return builder.create();
	}
	
	public void setMedCall(MedicineCallBack medCall){
		this.medCall = medCall;
	}
}

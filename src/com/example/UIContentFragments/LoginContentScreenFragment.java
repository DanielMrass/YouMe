package com.example.UIContentFragments;

import com.example.UILayoutFragments.ProfileSoulmatesLayoutFragment;
import com.example.youapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.widget.LoginButton;


public class LoginContentScreenFragment extends Fragment implements OnClickListener {
	
	private LoginButton loginButton;
	
	private boolean isRegistered = false;
	
	
	private Session.StatusCallback statusCallback = new SessionStatusCallback();


    @Override
    public void onStart() {
        super.onStart();
        Session.getActiveSession().addCallback(statusCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        Session.getActiveSession().removeCallback(statusCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);
    }

   /** @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Session session = Session.getActiveSession();
        Session.saveSession(session, outState);
    }*/

   private void updateView() {
        Session session = Session.getActiveSession();
        if (session.isOpened()) {
            loginButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) { onClickLogout(); }
            });
        } else {
            loginButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) { onClickLogin(); }
            });
        }
    }

    private void onClickLogin() {
        Session session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(getActivity()).setCallback(statusCallback));
        } else {
            Session.openActiveSession(getActivity(), true, statusCallback);
        }
    }

    private void onClickLogout() {
        Session session = Session.getActiveSession();
        if (!session.isClosed()) {
            session.closeAndClearTokenInformation();
        }
    }

    public class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            updateView();
        }
    }

    
    //--------------------------------------------
	
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		Bundle args = getArguments();
		if(args != null){
			Toast.makeText(getActivity(), "Got Arguments!", Toast.LENGTH_SHORT).show();
			if(args.containsKey("isRegistered")){
				this.isRegistered = args.getBoolean("isRegistered");
			}
		}
		
		getActivity().getActionBar().setTitle("YouMeIBD");
		
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);
		loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);

        Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);

        Session session = Session.getActiveSession();
        if (session == null) {
            if (savedInstanceState != null) {
                session = Session.restoreSession(getActivity(), null, statusCallback, savedInstanceState);
            }
            if (session == null) {
                session = new Session(getActivity());
            }
            Session.setActiveSession(session);
            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                session.openForRead(new Session.OpenRequest(getActivity()).setCallback(statusCallback));
            }
        }

        updateView();
		
		return rootView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.login_button:
			if(isRegistered){
				getFragmentManager().popBackStack();
				getFragmentManager().beginTransaction().replace(R.id.container, new ProfileSoulmatesLayoutFragment()).commit();
			}
			else {
				getFragmentManager().beginTransaction().replace(R.id.container, new RegistrationContentScreenFragment(), "registrationFragment").commit();
			}
			break;
		}
	}
}

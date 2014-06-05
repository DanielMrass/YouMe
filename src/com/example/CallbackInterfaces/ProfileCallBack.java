package com.example.CallbackInterfaces;

import java.util.HashMap;

public interface ProfileCallBack {
	void updateProfile(HashMap<String, String> data);
	void initiateEditDialog();
}

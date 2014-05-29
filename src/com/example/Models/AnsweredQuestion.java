package com.example.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class AnsweredQuestion implements Parcelable{

	private String question;
	private String answer;
	
	public AnsweredQuestion(String question, String answer){
		this.question = question;
		this.answer = answer;
	}
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if(question != null){
			dest.writeString(question);
		}
		if(answer!= null){
			dest.writeString(answer);
		}
	}

	
	public static final Parcelable.Creator<AnsweredQuestion> CREATOR = new Parcelable.Creator<AnsweredQuestion>() {
		public AnsweredQuestion createFromParcel(Parcel in) {
			return new AnsweredQuestion(in);
		}

		public AnsweredQuestion[] newArray(int size) {
			return new AnsweredQuestion[size];
		}
	};

	private AnsweredQuestion(Parcel in) {
		question = in.readString();
		answer = in.readString();
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}


}

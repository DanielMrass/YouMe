package com.example.Models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PostQuestion implements Parcelable{

	private String question;
	private ArrayList<String> answers;
	
	public PostQuestion(){
	}
	
	public PostQuestion(String question, ArrayList<String> answers){
		this.question = question;
		this.answers = answers;
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
		if(answers!= null){
			dest.writeStringList((answers));
		}
	}

	
	public static final Parcelable.Creator<PostQuestion> CREATOR = new Parcelable.Creator<PostQuestion>() {
		public PostQuestion createFromParcel(Parcel in) {
			return new PostQuestion(in);
		}

		public PostQuestion[] newArray(int size) {
			return new PostQuestion[size];
		}
	};

	private PostQuestion(Parcel in) {
		question = in.readString();
		in.readStringList((ArrayList<String>)answers);
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswer(ArrayList<String> answer) {
		this.answers = answer;
	}


}

package com.example.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Soulmates implements Parcelable {

	private String name;
	private String age;
	private String origin;

	// TODO damit den Soulmateadapter füllen
	public Soulmates(){
		
	}
	
	public Soulmates(String name, String age, String origin){
		this.name = name;
		this.age = age;
		this.origin = origin;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (name != null) {
			dest.writeString(name);
		}
		if (age != null) {
			dest.writeString(age);
		}
		if (origin != null) {
			dest.writeString(origin);
		}
	}

	@Override
	public String toString() {
		return name + ", " + age + ", " +origin;
	}

	public static final Parcelable.Creator<Soulmates> CREATOR = new Parcelable.Creator<Soulmates>() {
		public Soulmates createFromParcel(Parcel in) {
			return new Soulmates(in);
		}

		public Soulmates[] newArray(int size) {
			return new Soulmates[size];
		}
	};

	private Soulmates(Parcel in) {
		name = in.readString();
		age = in.readString();
		origin = in.readString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
}

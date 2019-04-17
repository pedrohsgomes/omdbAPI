package br.com.phsg.omdbapi.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Rating implements Parcelable, Serializable {

    public String Source;
    public String Value;

    /* Boilerplate code to make the object parcelable */

    public Rating(Parcel in) {
        Source = in.readString();
        Value = in.readString();
    }

    @Override
    public String toString() {
        return "Rating{" +
                "Source='" + Source + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Source);
        dest.writeString(Value);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}

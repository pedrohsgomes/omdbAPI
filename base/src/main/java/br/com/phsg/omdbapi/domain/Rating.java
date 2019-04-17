package br.com.phsg.omdbapi.domain;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcel;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.ManyToOne;
import io.requery.Persistable;

@Entity
public interface Rating extends Observable, Parcelable, Persistable {

    @Key
    @Generated
    @Bindable
    Integer getId();

    @Bindable
    String getSource();
    @Bindable
    String getValue();

    @ManyToOne
    Movie getMovie();
}
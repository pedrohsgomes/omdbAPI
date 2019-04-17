package br.com.phsg.omdbapi.domain;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.ForeignKey;
import io.requery.Key;
import io.requery.OneToOne;
import io.requery.Persistable;

@Entity
public interface Detail extends Observable, Parcelable, Persistable {

    @Key
    @Bindable
    String getImdbID();

    @OneToOne(mappedBy = "detail")
    Movie getMovie();

    @Bindable
    public String getRated();
    @Bindable
    public String getReleased();
    @Bindable
    public String getRuntime();
    @Bindable
    public String getGenre();
}

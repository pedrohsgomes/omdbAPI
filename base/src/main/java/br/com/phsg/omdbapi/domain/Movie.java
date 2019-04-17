package br.com.phsg.omdbapi.domain;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import java.util.List;

import io.requery.CascadeAction;
import io.requery.Entity;
import io.requery.ForeignKey;
import io.requery.Key;
import io.requery.OneToMany;
import io.requery.OneToOne;
import io.requery.Persistable;
import io.requery.query.Result;

@Entity
public interface Movie extends Observable, Parcelable, Persistable {

    @Key
    @Bindable
    String getImdbID();

    @Bindable
    String getTitle();
    @Bindable
    String getYear();
    @Bindable
    String getType();
    @Bindable
    String getPoster();

    @Bindable
    @OneToOne(cascade = {CascadeAction.SAVE, CascadeAction.DELETE})
    @ForeignKey(referencedColumn = "imdbID")
    Detail getDetail();

    @OneToMany(mappedBy = "movie")
    @Bindable
    List<Rating> getRatings();
}

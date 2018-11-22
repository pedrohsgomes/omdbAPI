package br.com.phsg.omdbapi.service;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;

import br.com.phsg.omdbapi.dto.Movie;
import br.com.phsg.omdbapi.dto.Result;
import br.com.phsg.omdbapi.dto.ResultWithDetail;

public class RetrofitLoader extends AsyncTaskLoader<ResultWithDetail> {

    private static final String LOG_TAG = "RetrofitLoader";

    private final String mTitle;

    private ResultWithDetail mData;

    public RetrofitLoader(Context context, String title) {
        super(context);
        mTitle = title;
    }

    @Override
    public ResultWithDetail loadInBackground() {
        // Recupera o resultado da chamada da api
        try {
            Result result =  PesquisaServiceOMDb.performSearch(mTitle);
            ResultWithDetail resultWithDetail = new ResultWithDetail(result);
            if(result.Search != null) {
                for(Movie movie: result.Search) {
                    resultWithDetail.addToList(PesquisaServiceOMDb.getDetail(movie.imdbID));
                }
            }
            return  resultWithDetail;
        } catch(final IOException e) {
            Log.e(LOG_TAG, "Erro de acesso à api", e);
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        } else {
            forceLoad();
        }
    }


    @Override
    protected void onReset() {
        Log.d(LOG_TAG, "onReset");
        super.onReset();
        mData = null;
    }

    @Override
    public void deliverResult(ResultWithDetail data) {
        if (isReset()) {
            return;
        }

        ResultWithDetail oldData = mData;
        mData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }
}
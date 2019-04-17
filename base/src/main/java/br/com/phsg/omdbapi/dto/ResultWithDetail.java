package br.com.phsg.omdbapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultWithDetail implements Serializable {
    private List<Detail> movieDetailList;
    private String totalResults;
    private String Response;

    public ResultWithDetail(Result result) {
        this.totalResults = result.totalResults;
        this.Response = result.Response;
        movieDetailList = new ArrayList<>();
    }

    public void addToList(Detail detail) {
        movieDetailList.add(detail);
    }

    public List<Detail> getMovieDetailList() {
        return movieDetailList;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return Response;
    }
}

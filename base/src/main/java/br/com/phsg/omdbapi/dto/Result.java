package br.com.phsg.omdbapi.dto;

import java.util.List;

import br.com.phsg.omdbapi.service.PesquisaServiceOMDb;

public class Result {
    public List<Movie> Search;
    public String totalResults;
    public String Response;

    @Override
    public String toString() {
        return "Result{" +
                "Search=" + Search +
                ", totalResults='" + totalResults + '\'' +
                ", Response='" + Response + '\'' +
                '}';
    }

}

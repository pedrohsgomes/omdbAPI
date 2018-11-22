package br.com.phsg.omdbapi.service;

import br.com.phsg.omdbapi.dto.Detail;
import br.com.phsg.omdbapi.dto.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PesquisaServiceOMDbAPI {
    @GET("?type=movie")
    Call<Result> Result(
            @Query("s") String Title);

    @GET("?plot=full")
    Call<Detail> Detail(
            @Query("i") String ImdbId);
}

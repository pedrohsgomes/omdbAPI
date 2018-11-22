package br.com.phsg.omdbapi.service;

import java.io.IOException;

import br.com.phsg.omdbapi.BuildConfig;
import br.com.phsg.omdbapi.dto.Detail;
import br.com.phsg.omdbapi.dto.Result;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PesquisaServiceOMDb {
    private static final String API_URL = "http://www.omdbapi.com";
    private static PesquisaServiceOMDbAPI service;

    private static void setService() {
        if (service == null) {
            OkHttpClient.Builder httpClient =
                    new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("apikey", BuildConfig.API_KEY)
                            .build();
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            //Instanciando um novo servico REST da api
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(PesquisaServiceOMDbAPI.class);
        }
    }

    public static Result performSearch(String title) throws IOException {
        setService();
        Call<Result> call = service.Result(title);

        return call.execute().body();
    }

    public static Detail getDetail(String imdbId) throws IOException {
        setService();

        Call<Detail> call = service.Detail(imdbId);

        return call.execute().body();
    }
}

package com.ida.mvvmtvshows.network;

import com.ida.mvvmtvshows.response.TVShowDetailsResponse;
import com.ida.mvvmtvshows.response.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<TVShowResponse> getMostPopularTvShow(@Query("page") int page);

    @GET("show-details")
    Call<TVShowDetailsResponse> getTVShowDetails(@Query("q") String tvShowId);
}

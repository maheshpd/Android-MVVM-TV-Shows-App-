package com.ida.mvvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ida.mvvmtvshows.repositories.TVShowDetailsRepository;
import com.ida.mvvmtvshows.response.TVShowDetailsResponse;

public class TVShowDetailsViewModel extends ViewModel {
    private TVShowDetailsRepository tvShowDetailsRepository;

    public TVShowDetailsViewModel() {
        tvShowDetailsRepository = new TVShowDetailsRepository();
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }

}

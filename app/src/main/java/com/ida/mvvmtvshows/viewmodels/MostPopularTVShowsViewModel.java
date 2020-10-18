package com.ida.mvvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ida.mvvmtvshows.repositories.MostPopularTvShowsRepository;
import com.ida.mvvmtvshows.response.TVShowResponse;

public class MostPopularTVShowsViewModel extends ViewModel {
    private MostPopularTvShowsRepository mostPopularTvShowsRepository;

    public MostPopularTVShowsViewModel() {
        mostPopularTvShowsRepository = new MostPopularTvShowsRepository();
    }

    public LiveData<TVShowResponse> getMostPopularTvShows(int page) {
        return mostPopularTvShowsRepository.getMostPopularTvShows(page);
    }

}

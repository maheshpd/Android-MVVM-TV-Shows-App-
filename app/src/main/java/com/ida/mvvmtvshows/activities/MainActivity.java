package com.ida.mvvmtvshows.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ida.mvvmtvshows.R;
import com.ida.mvvmtvshows.adapter.TvShowsAdapter;
import com.ida.mvvmtvshows.databinding.ActivityMainBinding;
import com.ida.mvvmtvshows.models.TvShow;
import com.ida.mvvmtvshows.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TvShow> tvShows = new ArrayList<>();
    private TvShowsAdapter tvShowsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TvShowsAdapter(tvShows);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowsAdapter);
        getMostPopularTvShows();
    }

    private void getMostPopularTvShows() {
        activityMainBinding.setIsLoading(true);
        viewModel.getMostPopularTvShows(0).observe(this, mostPopulatTvShowsResponse -> {
            activityMainBinding.setIsLoading(false);
            if (mostPopulatTvShowsResponse != null) {
                if (mostPopulatTvShowsResponse.getTvShows() != null) {
                    tvShows.addAll(mostPopulatTvShowsResponse.getTvShows());
                    tvShowsAdapter.notifyDataSetChanged();
                }
            }

        });
    }

}
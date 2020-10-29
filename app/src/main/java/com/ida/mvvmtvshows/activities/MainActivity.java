package com.ida.mvvmtvshows.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.ida.mvvmtvshows.R;
import com.ida.mvvmtvshows.adapter.TvShowsAdapter;
import com.ida.mvvmtvshows.databinding.ActivityMainBinding;
import com.ida.mvvmtvshows.listeners.TVShowsListener;
import com.ida.mvvmtvshows.models.TvShow;
import com.ida.mvvmtvshows.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private final List<TvShow> tvShows = new ArrayList<>();
    private TvShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TvShowsAdapter(tvShows, this);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)) {
                    currentPage += 1;
                    getMostPopularTvShows();
                }
            }
        });
        getMostPopularTvShows();
    }

    private void getMostPopularTvShows() {
        toggleLoading();
        viewModel.getMostPopularTvShows(currentPage).observe(this, mostPopulatTvShowsResponse -> {
            toggleLoading();
            if (mostPopulatTvShowsResponse != null) {
                totalAvailablePages = mostPopulatTvShowsResponse.getTotalPages();
                if (mostPopulatTvShowsResponse.getTvShows() != null) {
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopulatTvShowsResponse.getTvShows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }

        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            activityMainBinding.setIsLoading(activityMainBinding.getIsLoading() == null || !activityMainBinding.getIsLoading());
        } else {
            activityMainBinding.setIsLoadingMore(activityMainBinding.getIsLoadingMore() == null || !activityMainBinding.getIsLoadingMore());
        }
    }


    @Override
    public void onTVShowClicked(TvShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("id", tvShow.getId());
        intent.putExtra("name", tvShow.getName());
        intent.putExtra("startDate", tvShow.getStartDate());
        intent.putExtra("country", tvShow.getCountry());
        intent.putExtra("network", tvShow.getNetwork());
        intent.putExtra("status", tvShow.getStatus());
        startActivity(intent);
    }
}
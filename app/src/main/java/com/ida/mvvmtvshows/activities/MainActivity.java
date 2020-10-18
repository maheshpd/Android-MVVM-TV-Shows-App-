package com.ida.mvvmtvshows.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ida.mvvmtvshows.R;
import com.ida.mvvmtvshows.viewmodels.MostPopularTVShowsViewModel;

public class MainActivity extends AppCompatActivity {

    private MostPopularTVShowsViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        getMostPopularTvShows();
    }

    private void getMostPopularTvShows() {
        viewModel.getMostPopularTvShows(0).observe(this, mostPopulatTvShowsResponse ->
                Toast.makeText(this, "Toal Pages: " + mostPopulatTvShowsResponse.getTotalPages(), Toast.LENGTH_SHORT).show()
        );
    }

}
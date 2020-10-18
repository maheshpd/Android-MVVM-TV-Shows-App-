package com.ida.mvvmtvshows.response;

import com.google.gson.annotations.SerializedName;
import com.ida.mvvmtvshows.models.TVShowDetails;

public class TVShowDetailsResponse {
    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}

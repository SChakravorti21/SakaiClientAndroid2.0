package com.example.development.sakaiclient20.networking.services;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Shoumyo Chakravorti on 8/5/18.
 */

public interface SitesService {
    @GET("site.json")
    Single<ResponseBody> getAllSites();

    @GET("site/{site_id}.json")
    Single<ResponseBody> getSingleSite(@Path(value = "site_id", encoded = true) String siteId);
}

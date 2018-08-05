package com.example.development.sakaiclient20.networking.services;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Shoumyo Chakravorti on 8/5/18.
 */

public interface SitesService {
    @GET("site.json")
    Single<ResponseBody> getAllSites();
}

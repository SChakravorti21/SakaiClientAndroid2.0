package com.example.development.sakaiclient20.networking.sources;

import io.reactivex.Single;

/**
 * Created by Shoumyo Chakravorti on 8/5/18.
 */

public interface DataSource<CompleteEntity, SiteEntity> {
    Single<CompleteEntity> getAll();
    Single<SiteEntity> getForSite(String siteId);
}

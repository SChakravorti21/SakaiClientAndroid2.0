package com.example.development.sakaiclient20.networking.datasources;

import io.reactivex.Observable;

/**
 * Created by Shoumyo Chakravorti on 8/5/18.
 */

public interface DataSource<CompleteEntity, SiteEntity> {
    Observable<CompleteEntity> getAll();
    Observable<SiteEntity> getForSite(String siteId);
}

package com.example.development.sakaiclient20.networking.sources;

import android.content.Context;

import com.example.development.sakaiclient20.models.sakai.announcements.Announcement;
import com.example.development.sakaiclient20.models.sakai.announcements.AnnouncementsResponse;
import com.example.development.sakaiclient20.networking.services.AnnouncementsService;
import com.example.development.sakaiclient20.networking.services.ServiceFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Development on 8/5/18.
 */

public class AnnouncementsApiDataSource implements DataSource<List<Announcement>, List<Announcement>> {

    private static final int MAX_DAYS = 1000000000;
    private static final int MAX_ANNOUNCEMENTS = 1000000000;

    private static AnnouncementsApiDataSource mInstance;
    private AnnouncementsService announcementsService;

    public static AnnouncementsApiDataSource getInstance(Context context) {
        if(mInstance == null) {
            mInstance = createInstance(context);
        }
        return mInstance;
    }

    private static AnnouncementsApiDataSource createInstance(Context context) {
        AnnouncementsService announcementsService =
                ServiceFactory.getService(context, AnnouncementsService.class);
        return new AnnouncementsApiDataSource(announcementsService);
    }

    // Sakai always shows the most recent 10 announcements
    @Override
    public Single<List<Announcement>> getAll() {
        return getAll(10);
    }

    // Show all announcements for the class
    @Override
    public Single<List<Announcement>> getForSite(String siteId) {
        return getForSite(siteId, MAX_DAYS);
    }

    private AnnouncementsApiDataSource(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    private Single<List<Announcement>> getAll(int days) {
        return announcementsService
                .getAllAnnouncements(days, MAX_ANNOUNCEMENTS)
                .map(AnnouncementsResponse::getAnnouncements);
    }

    private Single<List<Announcement>> getForSite(String siteId, int days) {
        return announcementsService
                .getAnnouncementsForSite(siteId, days, MAX_ANNOUNCEMENTS)
                .map(AnnouncementsResponse::getAnnouncements);
    }
}

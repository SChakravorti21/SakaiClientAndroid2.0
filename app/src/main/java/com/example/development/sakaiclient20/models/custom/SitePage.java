package com.example.development.sakaiclient20.models.custom;

public class SitePage {

    private String url;
    private String title;
    private String siteId;

    public SitePage(String siteId, String title, String url) {
        this.siteId = siteId;
        this.title = title;
        this.url = url;
    }

    public String getId() {
        return siteId;
    }

    public String getTitle() {
        return title;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return (this.title + ", " + this.siteId);
    }
}

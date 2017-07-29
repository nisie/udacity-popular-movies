package com.nisie.popularmovies.movielist.domain.model;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieReviewItemDomain {

    private String id;
    private String author;
    private String content;
    private String url;

    public MovieReviewItemDomain(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}

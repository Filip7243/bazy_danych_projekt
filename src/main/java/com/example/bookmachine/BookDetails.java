package com.example.bookmachine;

import java.util.Date;

public class BookDetails {

    private Long id;
    private String title;
    private Date date;
    private String authorName;
    private String publisherName;
    private String genre;
    private String cover;

    public BookDetails(Long id, String title, Date date, String authorName,
                       String publisherName, String genre, String cover) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.genre = genre;
        this.cover = cover;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}

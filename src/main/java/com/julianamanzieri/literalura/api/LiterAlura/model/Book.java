package com.julianamanzieri.literalura.api.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String languages;
    private Integer numberOfDownloads;

    public  Book() {}


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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return languages;
    }

    public void setLanguage(String language) {
        this.languages = language;
    }

    public Integer getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(Integer numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    @Override
    public String toString() {
        return "Title: '" + title + '\'' +
                ", Author: " + author +
                ", Language: '" + languages + '\'' +
                ", Number of downloads: " + numberOfDownloads;
    }
}

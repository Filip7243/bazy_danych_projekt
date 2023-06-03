package com.example.bookmachine;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "publication_date")
    private Date publicationDate;
    private String cover;
    @ManyToOne
    private Publisher publisher;
    @ManyToOne
    private Genre genre;
    @ManyToMany
    @JoinTable
            (
                    name = "books_authors",
                    joinColumns = { @JoinColumn(name = "book_id", referencedColumnName = "id") },
                    inverseJoinColumns = { @JoinColumn(name = "author_id", referencedColumnName = "id") }
            )
    private List<Author> authors;

    public Book() {
    }

    public Book(Long id, String title, Date publicationDate, String cover, Publisher publisher, Genre genre) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.cover = cover;
        this.publisher = publisher;
        this.genre = genre;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", cover='" + cover + '\'' +
                ", publisher=" + publisher +
                ", genre=" + genre +
                ", authors=" + authors +
                '}';
    }
}

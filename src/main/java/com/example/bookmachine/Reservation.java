package com.example.bookmachine;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reservation_date")
    private Date reservationDate;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Person person;

    public Reservation() {
    }

    public Reservation(Long id, Date reservationDate, Book book, Person person) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.book = book;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", book=" + book +
                ", person=" + person +
                '}';
    }
}

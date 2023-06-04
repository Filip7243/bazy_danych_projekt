package com.example.bookmachine;

import jakarta.persistence.Column;

import java.util.Date;

public class ReservationDetails {

    private Long reservationId;
    private String title;
    private Date reservationDate;
    private Long bookId;
    private Long personId;

    public ReservationDetails(Long reservationId, String title, Date reservationDate, Long bookId, Long personId) {
        this.reservationId = reservationId;
        this.title = title;
        this.reservationDate = reservationDate;
        this.bookId = bookId;
        this.personId = personId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}

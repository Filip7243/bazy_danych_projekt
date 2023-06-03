package com.example.bookmachine;

import jakarta.persistence.Column;

import java.util.Date;

public class ReservationDetails {

    private String title;
    @Column(name = "reservation_date")
    private Date reservationDate;

    public ReservationDetails(String title, Date reservationDate) {
        this.title = title;
        this.reservationDate = reservationDate;
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
}

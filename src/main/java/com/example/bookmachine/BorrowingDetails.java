package com.example.bookmachine;

import java.util.Date;

public class BorrowingDetails {

    private String title;
    private Date borrowDate;
    private Date returnDate;

    public BorrowingDetails() {
    }

    public BorrowingDetails(String title, Date borrowDate, Date returnDate) {
        this.title = title;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

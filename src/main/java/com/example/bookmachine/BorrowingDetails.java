package com.example.bookmachine;

import java.util.Date;

public class BorrowingDetails {

    private Long bookId;
    private Long borrowId;
    private String title;
    private Date borrowDate;
    private Date returnDate;

    public BorrowingDetails() {
    }

    public BorrowingDetails(Long id, Long borrowId, String title, Date borrowDate, Date returnDate) {
        this.bookId = id;
        this.borrowId = borrowId;
        this.title = title;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
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

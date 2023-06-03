package com.example.bookmachine;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "borrow_date")
    private Date borrowDate;
    @Column(name = "return_date")
    private Date returnDate;
    private BigDecimal penalty;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Person person;

    public Borrowing() {
    }

    public Borrowing(Long id, Date borrowDate, Date returnDate, BigDecimal penalty, Book book, Person person) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.penalty = penalty;
        this.book = book;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
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
}

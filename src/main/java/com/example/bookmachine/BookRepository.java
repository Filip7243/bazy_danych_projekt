package com.example.bookmachine;

import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;

import static com.example.bookmachine.LoginController.em;

public class BookRepository {

    public static List<Book> getAllNotBorrowedBooks() {
        StoredProcedureQuery notBorrowedBooksQuery = em
                .createStoredProcedureQuery("get_all_not_borrowed_books", Book.class);
        notBorrowedBooksQuery.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        notBorrowedBooksQuery.execute();

        List<Book> resultList = notBorrowedBooksQuery.getResultList();

        return resultList;
    }

    public static List<Book> getAllNotReservedBooks() {
        StoredProcedureQuery notReservedBooksQuery = em
                .createStoredProcedureQuery("get_all_not_reserved_books", Book.class);
        notReservedBooksQuery.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        notReservedBooksQuery.execute();

        List<Book> resultList = notReservedBooksQuery.getResultList();
        System.out.println(resultList);
        return resultList;
    }
}

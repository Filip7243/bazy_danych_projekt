package com.example.bookmachine;

import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;

import static com.example.bookmachine.HelloApplication.loggedInUser;
import static com.example.bookmachine.LoginController.em;

public class BorrowingRepository {

    public static List<Borrowing> getUsersBorrowings() {
        StoredProcedureQuery personBorrowings = em
                .createStoredProcedureQuery("get_person_borrowings", Borrowing.class);
        personBorrowings.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        personBorrowings.registerStoredProcedureParameter(2, void.class, ParameterMode.REF_CURSOR);
        personBorrowings.setParameter(1, loggedInUser.getId());
        personBorrowings.execute();

        return personBorrowings.getResultList();
    }

    public static List<Reservation> getUsersReservation() {
        StoredProcedureQuery personReservations = em
                .createStoredProcedureQuery("get_person_reservations", Reservation.class);
        personReservations.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        personReservations.registerStoredProcedureParameter(2, void.class, ParameterMode.REF_CURSOR);
        personReservations.setParameter(1, loggedInUser.getId());
        personReservations.execute();

        return personReservations.getResultList();
    }

    public static void borrowBook(Long bookId, Long personId) {
        StoredProcedureQuery borrowBook = em
                .createStoredProcedureQuery("borrow_book");
        borrowBook.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        borrowBook.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        borrowBook.setParameter(1, bookId);
        borrowBook.setParameter(2, personId);
        borrowBook.execute();
    }

    public static void reserveBook(Long bookId, Long personId) {
        StoredProcedureQuery reserveBook = em
                .createStoredProcedureQuery("reserve_book");
        reserveBook.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        reserveBook.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        reserveBook.setParameter(1, bookId);
        reserveBook.setParameter(2, personId);
        reserveBook.execute();
    }

    public static void returnBook(Long borrowingId) {
        StoredProcedureQuery returnBook = em
                .createStoredProcedureQuery("return_book");
        returnBook.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        returnBook.setParameter(1, borrowingId);
        returnBook.execute();
    }

    public static void borrowReservedBook(Long bookId, Long personId, Long reservationId) {
        StoredProcedureQuery returnBook = em
                .createStoredProcedureQuery("borrow_reserved_book");
        returnBook.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        returnBook.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        returnBook.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        returnBook.setParameter(1, bookId);
        returnBook.setParameter(2, personId);
        returnBook.setParameter(3, reservationId);
        returnBook.execute();
    }
}

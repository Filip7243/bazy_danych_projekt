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

        List resultList = personBorrowings.getResultList();
        System.out.println(resultList);
        System.out.println(loggedInUser.getId());
        return resultList;
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
}

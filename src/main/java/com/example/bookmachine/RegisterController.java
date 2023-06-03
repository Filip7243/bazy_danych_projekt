package com.example.bookmachine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button registerBtn;

    public void initialize() {

        registerBtn.setOnAction(event -> {
            String firstName = this.firstName.getText();
            String lastName = this.lastName.getText();
            String phoneNumber = this.phoneNumber.getText();
            String email = this.email.getText();
            String password = this.password.getText();


            EntityManager entityManager = Config.getEntityManager();

            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("REGISTER_USER", Person.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(6, void.class, ParameterMode.REF_CURSOR);

            query.setParameter(1, firstName.trim());
            query.setParameter(2, lastName);
            query.setParameter(3, phoneNumber);
            query.setParameter(4, email);
            query.setParameter(5, password);

            try {
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();
                query.execute();
                Person person = (Person) query.getSingleResult();
                transaction.commit();
                System.out.println(person);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}

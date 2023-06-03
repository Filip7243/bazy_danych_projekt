package com.example.bookmachine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOError;
import java.io.IOException;

import static com.example.bookmachine.HelloApplication.loggedInUser;
import static com.example.bookmachine.HibernateConnect.openSession;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorInfo;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;

    public static final EntityManager em = HibernateConnect.openSession();

    public void initialize() {
        errorInfo.setVisible(false);

        loginBtn.setOnAction(event -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            StoredProcedureQuery query = em
                    .createStoredProcedureQuery("LOGIN", Person.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, void.class, ParameterMode.REF_CURSOR);

            query.setParameter(1, email);
            query.setParameter(2, password);

            query.execute();

            try {
                Person person = (Person) query.getSingleResult();
                loggedInUser = person;
                errorInfo.setVisible(false);
                loadFXML(event, "home-view.fxml");
            } catch (NullPointerException e) {
                errorInfo.setVisible(true);
            }

        });

        registerBtn.setOnAction(event -> {
            loadFXML(event, "register-view.fxml");
        });
    }

    public static void loadFXML(ActionEvent event, String fxmlName) {
        Parent root;
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlName));
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 1150, 650));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

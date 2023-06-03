module com.example.bookmachine {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.example.bookmachine to javafx.fxml, org.hibernate.orm.core;
    exports com.example.bookmachine;
}
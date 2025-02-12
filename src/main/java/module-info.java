module com.portfolio.fxexpensetrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    exports com.portfolio.fxexpensetrack.controllers;
    opens com.portfolio.fxexpensetrack.controllers to javafx.fxml;

}
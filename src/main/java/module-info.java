module fx.expense.track.main {
    requires jakarta.persistence;
    requires java.logging;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.hibernate.orm.core;
    opens com.portfolio.fxexpensetrack;
}
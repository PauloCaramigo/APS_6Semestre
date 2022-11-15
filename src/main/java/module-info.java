module com.Interface {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens com.Interface to javafx.fxml;
    exports com.Interface;
}

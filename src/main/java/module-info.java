module com.example.exercise1imageeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires java.logging;

    opens com.example.exercise1imageeditor to javafx.fxml;
    exports com.example.exercise1imageeditor;
    exports controllers;
    opens controllers to javafx.fxml;
}
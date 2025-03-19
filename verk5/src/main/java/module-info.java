module vidmot.eventmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens vidmot to javafx.fxml;
    exports vidmot;
    exports vinnsla;
    opens vinnsla to javafx.fxml;
}
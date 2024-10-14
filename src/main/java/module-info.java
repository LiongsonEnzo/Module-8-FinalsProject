module com.example.finalsprojectwithjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.finalsprojectwithjavafx to javafx.fxml;
    exports com.example.finalsprojectwithjavafx;
}
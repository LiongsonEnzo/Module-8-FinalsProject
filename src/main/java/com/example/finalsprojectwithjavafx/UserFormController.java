package com.example.finalsprojectwithjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserFormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField genderField;

    @FXML
    private ComboBox<String> roleComboBox;

    // Other fields and methods...

    @FXML
    public void handleSubmit() {
        // Handle submit logic
    }

    @FXML
    public void handleClear() {
        // Clear fields
    }
}

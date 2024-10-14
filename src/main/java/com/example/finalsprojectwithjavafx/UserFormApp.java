package com.example.finalsprojectwithjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UserFormApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Form");

        // Labels and text fields for User details
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        Label genderLabel = new Label("Gender:");
        TextField genderField = new TextField();

        // Role selection (Student or Teacher)
        Label roleLabel = new Label("Role:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Student", "Teacher");

        // Specific fields for Student
        Label studentIDLabel = new Label("Student ID:");
        TextField studentIDField = new TextField();
        Label creditHoursLabel = new Label("Credit Hours:");
        TextField creditHoursField = new TextField();
        Label gpaLabel = new Label("GPA:");
        TextField gpaField = new TextField();

        // Specific fields for Teacher
        Label teacherIDLabel = new Label("Teacher ID:");
        TextField teacherIDField = new TextField();
        Label subjectLabel = new Label("Subject:");
        TextField subjectField = new TextField();

        // Buttons for actions
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(ageLabel, 0, 1);
        gridPane.add(ageField, 1, 1);
        gridPane.add(genderLabel, 0, 2);
        gridPane.add(genderField, 1, 2);
        gridPane.add(roleLabel, 0, 3);
        gridPane.add(roleComboBox, 1, 3);

        // Adding fields dynamically based on role
        roleComboBox.setOnAction(e -> {
            // Clear previous role-specific fields but keep the buttons
            gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) >= 4 && node != submitButton && node != clearButton);

            if (roleComboBox.getValue().equals("Student")) {
                gridPane.add(studentIDLabel, 0, 4);
                gridPane.add(studentIDField, 1, 4);
                gridPane.add(creditHoursLabel, 0, 5);
                gridPane.add(creditHoursField, 1, 5);
                gridPane.add(gpaLabel, 0, 6);
                gridPane.add(gpaField, 1, 6);
            } else if (roleComboBox.getValue().equals("Teacher")) {
                gridPane.add(teacherIDLabel, 0, 4);
                gridPane.add(teacherIDField, 1, 4);
                gridPane.add(subjectLabel, 0, 5);
                gridPane.add(subjectField, 1, 5);
            }
        });

        // Add buttons at the bottom
        gridPane.add(submitButton, 1, 7);
        gridPane.add(clearButton, 1, 8);

        submitButton.setOnAction(e -> handleSubmit(roleComboBox, nameField, ageField, genderField, studentIDField, creditHoursField, gpaField, teacherIDField, subjectField));

        clearButton.setOnAction(e -> clearFields(nameField, ageField, genderField, studentIDField, creditHoursField, gpaField, teacherIDField, subjectField));

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSubmit(ComboBox<String> role, TextField name, TextField age, TextField gender, TextField studentID, TextField creditHours, TextField gpa, TextField teacherID, TextField subject) {
        String selectedRole = role.getValue();

        if (selectedRole != null) {
            try {
                if (selectedRole.equals("Student")) {
                    int parsedAge = Integer.parseInt(age.getText());
                    double parsedCreditHours = Double.parseDouble(creditHours.getText());
                    double parsedGpa = Double.parseDouble(gpa.getText());

                    Student student = new Student(name.getText(), parsedAge, gender.getText(), studentID.getText(), parsedCreditHours, parsedGpa);
                    System.out.println("Student: " + student.getName() + " | ID: " + student.getStudentID() + " | GPA: " + student.getGPA());

                } else if (selectedRole.equals("Teacher")) {
                    int parsedAge = Integer.parseInt(age.getText());

                    Teacher teacher = new Teacher(name.getText(), parsedAge, gender.getText(), teacherID.getText(), subject.getText());
                    System.out.println("Teacher: " + teacher.getName() + " | Subject: " + teacher.getSubject());
                }

            } catch (NumberFormatException e) {
                System.err.println("Invalid input: Age must be an integer, and Credit Hours and GPA must be numbers.");
            }
        } else {
            System.err.println("Role must be selected.");
        }
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class Student extends User {
        private String studentID;
        private double creditHours;
        private double gpa;

        public Student(String name, int age, String gender, String studentID, double creditHours, double gpa) {
            super(name, age, gender);
            this.studentID = studentID;
            this.creditHours = creditHours;
            this.gpa = gpa;
        }

        // Getters and Setters
        public String getStudentID() { return studentID; }
        public void setStudentID(String studentID) { this.studentID = studentID; }

        public double getCreditHours() { return creditHours; }
        public void setCreditHours(double creditHours) { this.creditHours = creditHours; }

        public double getGPA() { return gpa; }
        public void setGPA(double gpa) { this.gpa = gpa; }
    }

    public static class Teacher extends User {
        private String teacherID;
        private String subject;
        private List<String> classesHandled;
        private List<String> gradesSubmitted;

        public Teacher(String name, int age, String gender, String teacherID, String subject) {
            super(name, age, gender);
            this.teacherID = teacherID;
            this.subject = subject;
            this.classesHandled = new ArrayList<>();
            this.gradesSubmitted = new ArrayList<>();
        }

        // Getters and Setters
        public String getTeacherID() { return teacherID; }
        public void setTeacherID(String teacherID) { this.teacherID = teacherID; }

        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }

        public List<String> getClassesHandled() { return classesHandled; }
        public void addClassHandled(String classHandled) { this.classesHandled.add(classHandled); }

        public List<String> getGradesSubmitted() { return gradesSubmitted; }
        public void addGradeSubmitted(String grade) { this.gradesSubmitted.add(grade); }
    }

    public static class User {
        private String name;
        private int age;
        private String gender;

        public User(String name, int age, String gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }

        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }
    }
}

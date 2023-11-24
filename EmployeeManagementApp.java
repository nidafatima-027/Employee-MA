package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementApp extends Application {

    private List<Employee> employees = new ArrayList<>();
    private ListView<String> employeeListView;
    private TextField nameField;
    private TextField ageField;
    private TextField cnicField;
    private TextField dobField;
    private TextField addressField;
    private Scene secondWindowScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Management Application");


        employeeListView = new ListView<>();
        Button addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setOnAction(e -> openAddEmployeeWindow(primaryStage));

        VBox firstWindowLayout = new VBox(10);
        firstWindowLayout.getChildren().addAll(employeeListView, addEmployeeButton);


        nameField = new TextField();
        ageField = new TextField();
        cnicField = new TextField();
        dobField = new TextField();
        addressField = new TextField();
        Button saveEmployeeButton = new Button("Save Employee");
        saveEmployeeButton.setOnAction(e -> saveEmployee());
        Button viewListButton = new Button("View Employee List");

        VBox secondWindowLayout = new VBox(10);
        secondWindowLayout.getChildren().addAll(
                new Label("Employee Name:"),
                nameField,
                new Label("Employee Age:"),
                ageField,
                new Label("Employee CNIC (Numbers only):"),
                cnicField,
                new Label("Employee DOB:"),
                dobField,
                new Label("Employee Address:"),
                addressField,
                saveEmployeeButton,
                viewListButton
        );

        secondWindowScene = new Scene(secondWindowLayout, 600, 400);

        Scene firstWindowScene = new Scene(firstWindowLayout, 600, 400);

        primaryStage.setScene(firstWindowScene);
        primaryStage.show();

        viewListButton.setOnAction(e -> primaryStage.setScene(firstWindowScene));
    }

    private void openAddEmployeeWindow(Stage primaryStage) {
        primaryStage.setScene(secondWindowScene);
    }

    private void saveEmployee() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        int cnic = Integer.parseInt(cnicField.getText());
        String dob = dobField.getText();
        String address = addressField.getText();

        Employee employee = new Employee(name, age, cnic, dob, address);
        employees.add(employee);

        // Add employee details to the ListView in the first window
        employeeListView.getItems().add(employee.toString());

        // Clear input fields
        nameField.clear();
        ageField.clear();
        cnicField.clear();
        dobField.clear();
        addressField.clear();
    }

    public class Employee {
        private String name;
        private int age;
        private int cnic;
        private String dob;
        private String address;

        public Employee(String name, int age, int cnic, String dob, String address) {
            this.name = name;
            this.age = age;
            this.cnic = cnic;
            this.dob = dob;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Age: " + age + ", CNIC: " + cnic + ", DOB: " + dob + ", Address: " + address;
        }
    }
}

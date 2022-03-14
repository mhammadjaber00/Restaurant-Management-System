package Models;

import javafx.scene.control.Alert;

public class Manager implements Employee{
    private final String JOB; //intrinsic property
    private String name;

    public Manager() {
        JOB = "Manage the stock";
    }

    @Override
    public void assignName(String name) {
        this.name = name;

    }

    @Override
    public void createEmployee() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Using Flyweight pattern");
        alert.setContentText("Manager: " + name + "with job " + JOB);
        alert.showAndWait();

    }
}
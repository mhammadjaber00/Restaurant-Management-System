package Models;

import javafx.scene.control.Alert;

public class Staff_flyweight implements Employee{
    private final String JOB; //intrinsic property
    private String name; //extrinsic property, will change according to the staff name

    public Staff_flyweight() {
        JOB = "Serve the customer";
    }
    @Override
    public void assignName(String name) {
        this.name=name;
    }

    @Override
    public void createEmployee() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Using Flyweight pattern");
        alert.setContentText("staff: " + name + " \n with job " + JOB);
        alert.showAndWait();

    }
}
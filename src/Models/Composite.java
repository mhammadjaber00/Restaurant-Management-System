package Models;

import javafx.scene.control.Alert;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{
    String name;
    List<Component> components = new ArrayList<>();



    public Composite(String name) {
        this.name = name;
    }

    public void addComponent(Component comp){
        components.add(comp);
    }

    @Override
    public void showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Using Composite Pattern!");
        alert.setHeaderText("Plate is added!");
        alert.setContentText("Composite is : " + name);
        alert.showAndWait();
        for(Component c : components) {
            c.showInfo();
        }

    }
}
package Models;


import javafx.scene.control.Alert;

import java.awt.*;

public class Leaf implements Component{
    int price;
    String name;

    public Leaf(int price, String name) {
        this.price = price;
        this.name = name;
    }
    public String getName(){
        return this.name;

    }

    @Override
    public void showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Using Composite Pattern!");
        alert.setHeaderText("Leaf is added!");
        alert.setContentText("Leaf is : " + name + "\n" + "price : " + price);
        alert.showAndWait();
    }
}




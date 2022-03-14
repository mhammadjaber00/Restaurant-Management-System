package Models;

import javafx.scene.control.Alert;

public class Vegtable implements Pizza {
    @Override
    public void makepizza(String p) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Using Factory Pattern!");
        alert.setHeaderText("Pizza is added!");
        alert.setContentText("Type: " + p);
        alert.showAndWait();

    }
}


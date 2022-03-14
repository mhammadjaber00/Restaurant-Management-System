package Models;

import javafx.scene.control.Alert;

public class PizzaFactory {
    Pizza obj = null;

    public Pizza getType(String type){
        if(type.toLowerCase().equals("papperoni")){
            //interface = new subclass
            obj = new Papperoni();
        }else if(type.toLowerCase().equals("chicken")){
            obj = new Chicken();
        }else if(type.toLowerCase().equals("vegtable")){
            obj = new Vegtable();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Problem");
            alert.setContentText("Sorry we don't have " + type);
            alert.showAndWait();
        }
        return obj;
    }
}


package Models;

import javafx.scene.control.Alert;

import java.util.HashMap;

public class EmployeeFactory {
    private static HashMap<String,Employee> hashMap = new HashMap<String,Employee>();

    public static Employee getEmployee(String position){
        Employee e =null;
        if(hashMap.containsKey(position)){
            e=hashMap.get(position);
        }
        else{
            switch(position){
                case "Manager":
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("Using Flyweight pattern");
                    alert.setContentText("Manager is created");
                    alert.showAndWait();
                    e = new Manager();
                    break;
                case "Staff_flyweight":
                    e = new Staff_flyweight();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("Using Flyweight pattern");
                    alert.setContentText("Object staff is created with hashcode: \n "+ e.hashCode());
                    alert.showAndWait();
                    break;
                default:
                    throw new IllegalArgumentException("doesn't exist");

            }
            hashMap.put(position,e);
        }
        return e;
    }


}
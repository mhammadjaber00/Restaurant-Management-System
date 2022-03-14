package Controllers;

import javafx.scene.control.Alert;
import java.sql.*;

//using singelton pattern
public class databaseconnect {

    private databaseconnect(){}
    static Connection con = null;

    public static Connection ConnectDB()
    {
        try {
            if(con==null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/POS", "root", "");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Failed!");
            alert.setContentText("Connection Is Bad");
            alert.showAndWait();
        }
        return con;
    }
}

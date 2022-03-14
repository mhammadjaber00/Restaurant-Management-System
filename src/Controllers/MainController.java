package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainController {

    String user,pass;
    @FXML
    TextField userfield;
    @FXML
    PasswordField passfield;

    public void openScene(Parent root, ActionEvent event){
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //login for the user
    public void Login (ActionEvent event) throws SQLException {
        Connection con =databaseconnect.ConnectDB();
        String pos = "staff";
        String sql = "Select * From User where username= '" + userfield.getText() + "' and password = '" + passfield.getText() + "' and position ='" + pos + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        Alert alert;
        if(rs.next()) {
            try {
                user=userfield.getText();
                FXMLLoader loader =new FXMLLoader(getClass().getResource("../views/system.fxml"));
                Parent root=loader.load();
                SystemController sys=loader.getController();
                sys.settext(user);
                openScene(root, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Login Successful!");
            alert.setContentText("Staff: " + userfield.getText());
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("staff Doesn't Exists");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
    }
    //go to manager
    public void Manager(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/managerlogin.fxml"));
            openScene(root,event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

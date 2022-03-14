package Controllers;

import Models.Employee;
import Models.EmployeeFactory;
import Models.staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class StaffController {
    @FXML
    private TableView<staff> staff_table;

    @FXML
    private TableColumn<staff, Integer> col_staffid;

    @FXML
    private TableColumn<staff, String> col_stafusnm;

    @FXML
    private TableColumn<staff, String> col_staffpas;

    @FXML
    TextField staff_username;
    @FXML
    PasswordField staff_password;

    @FXML
    PasswordField staff_password2;

    @FXML
    TextField deleteid;


    ObservableList<staff> listM;

    public ObservableList<staff> getStaff() {
        Connection con = databaseconnect.ConnectDB();
        ObservableList<staff> list = FXCollections.observableArrayList();
        String position = "staff";

        try {
            PreparedStatement ps = con.prepareStatement("select * from User where position = '"+position+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new staff(Integer.parseInt(rs.getString("id")), rs.getString("password"), rs.getString("username")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    public void updateTable(){
        col_staffid.setCellValueFactory(new PropertyValueFactory<staff,Integer>("staffid"));
        col_stafusnm.setCellValueFactory(new PropertyValueFactory<staff,String>("staffusername"));
        col_staffpas.setCellValueFactory(new PropertyValueFactory<staff,String>("staffpassword"));
        listM = getStaff();
        staff_table.setItems(listM);

    }

    public void openScene(Parent root, ActionEvent event){
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void backtomanager(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/manager.fxml"));
            openScene(root,event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addStaff() throws SQLException {
        Employee staff = EmployeeFactory.getEmployee("Staff_flyweight");
        String position = "staff";
        Connection con=databaseconnect.ConnectDB();
        PreparedStatement pst = null;
        Alert alert;
        if(!staff_username.getText().isEmpty() && checkstaff(staff_username.getText()).equals(false) && !staff_password.getText().isEmpty() && !staff_password2.getText().isEmpty() && staff_password.getText().equals(staff_password2.getText())){
            try {
                pst= con.prepareStatement("insert into user (username,password,position)values(?,?,?)");
                pst.setString(1,staff_username.getText());
                pst.setString(2,staff_password.getText());
                pst.setString(3,position);
                pst.execute();
                staff.assignName(staff_username.getText());
                staff.createEmployee();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            updateTable();
        }
        else if(staff_username.getText().isEmpty() || staff_password.getText().isEmpty() || staff_password2.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Please Fill all the Information ");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
        else if(checkstaff(staff_username.getText()).equals(true)){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Username exists ");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }else if (!staff_password.getText().equals(staff_password2.getText())){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Wrong Password confirmation");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
    }
    public Boolean checkstaff(String username) throws SQLException {
        Connection con = databaseconnect.ConnectDB();
        String sql="Select * from user where username='"+username+"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if(!rs.next()){
            return false;
        }
        return true;
    }
    public void deleteStaff() {
        Connection con = databaseconnect.ConnectDB();
        Alert alert;
        int staffid;
        try {
            staffid=Integer.parseInt(deleteid.getText());
            PreparedStatement pst = con.prepareStatement("delete from user where id =? ");
            pst.setInt(1, staffid);
            pst.executeUpdate();
            updateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Enter id as a number");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
    }
}

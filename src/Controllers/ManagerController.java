package Controllers;

import Models.*;
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

public class ManagerController {
    @FXML
    TextField muserfield,usermanager,deleteitemid,itemname,itemquant,itemprice;
    @FXML
    PasswordField password,passmanager,confirmpassword,oldpassword;
    @FXML
    ComboBox categorymenu;
    @FXML
    private TableView<items> managertable;
    @FXML
    private TableColumn<items, Integer> col_itemid;
    @FXML
    private TableColumn<items, String> col_itemname;
    @FXML
    private TableColumn<items, Integer> col_itemquantity;
    @FXML
    private TableColumn<items, Integer> col_itemprice;

    ObservableList<items> listM;

    public void openScene(Parent root,ActionEvent event){
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    //-------------------------------------------------------------------------------------------------------
    //managerlogin

    //go to editmanager
    public void gotoeditmanager(ActionEvent event ){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/addmanager.fxml"));
            openScene(root,event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //back to main
    public void back (ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
            openScene(root,event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //login to manager
    public void login (ActionEvent event) throws SQLException{
        Alert alert;
        Connection con = databaseconnect.ConnectDB();
        String sql = "Select * From user where id = 1 and username= '" + usermanager.getText() + "' and password = '" + passmanager.getText() + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../views/manager.fxml"));
                openScene(root, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Login Successful!");
            alert.setContentText("Manager: " + usermanager.getText());
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Manager Doesn't Exists");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
    }

    //---------------------------------------------------------------------------------------------------------
    //editmanager

    //back to managerlogin
    public void back1(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/managerlogin.fxml"));
            openScene(root,event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editmanager() throws SQLException{
        String muser = muserfield.getText();
        String pass = password.getText();
        String confpass = confirmpassword.getText();
        String oldpass=oldpassword.getText();
        Alert alert;
        if (pass.equals(confpass) && muser.length() != 0 && pass.length() != 0 && checkman(oldpass).equals(true)) {
            Connection con1 = databaseconnect.ConnectDB();
            PreparedStatement pst = con1.prepareStatement("UPDATE user set username = ? , password = ? WHERE id = 1");
            pst.setString(1, muser);
            pst.setString(2, pass);
            pst.executeUpdate();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Manager is updated!");
            alert.setContentText("Username: " + muser);
            alert.showAndWait();
        } else if ( pass.isEmpty() || confpass.isEmpty() || muser.isEmpty() || oldpass.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Please Fill all the Information ");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }else if(checkman(oldpass).equals(false)){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Wrong Password ");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }else if(!pass.equals(confpass)){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Wrong Password Confirmation ");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
    }
    public Boolean checkman(String oldpass) throws SQLException {
        Connection con = databaseconnect.ConnectDB();
        String sql="Select * from user where id = 1 and password='"+oldpass+"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if(!rs.next()){
            return false;
        }
        return true;
    }
    //-----------------------------------------------------------------------------------------------------------
    //manager

    //back to main
    public void managerlogout(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
            openScene(root,event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //go to staff
    public void gotostaff(ActionEvent event){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../views/staff.fxml"));
            Parent root=loader.load();
            StaffController sc =loader.getController();
            sc.updateTable();
            openScene(root,event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void select(){
        categorymenu.setPromptText(categorymenu.getValue().toString());
        updateTable();
    }
    public ObservableList<items> getItems(String category) {
        Connection con = databaseconnect.ConnectDB();
        ObservableList<items> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = con.prepareStatement("select * from products where category = ?");
            ps.setString(1,category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new items(Integer.parseInt(rs.getString("quantity")), Integer.parseInt(rs.getString("id")), rs.getString("name"),Integer.parseInt(rs.getString("price"))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    public void updateTable(){
        col_itemid.setCellValueFactory(new PropertyValueFactory<items,Integer>("itemid"));
        col_itemname.setCellValueFactory(new PropertyValueFactory<items,String>("itemname"));
        col_itemquantity.setCellValueFactory(new PropertyValueFactory<items,Integer>("itemquantity"));
        col_itemprice.setCellValueFactory(new PropertyValueFactory<items,Integer>("itemprice"));
        listM = getItems(categorymenu.getPromptText());
        managertable.setItems(listM);

    }
    public void deleteItem() {
        Alert alert;
        Connection con = databaseconnect.ConnectDB();
        int itemid;
        try {
            itemid= Integer.parseInt(deleteitemid.getText());
            PreparedStatement pst = con.prepareStatement("delete from products where id =? ");
            pst.setInt(1,itemid);
            pst.executeUpdate();
            updateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Enter id as a number");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
    }
    public void additem() throws SQLException {
        PizzaFactory factory =new PizzaFactory();
        //interface = new subclass
        Pizza obj = null;
        Connection con=null;
        PreparedStatement pst = null;
        con = databaseconnect.ConnectDB();
        Alert alert;
        int price,quantity;
        if(categorymenu.getPromptText().equals("Plates")){
            Component x = new Leaf(Integer.parseInt(itemprice.getText()),itemname.getText());
            Composite Plate  = new Composite("Plates");
            Plate.addComponent(x);
            Plate.showInfo();
        }else if(categorymenu.getPromptText().equals("Pizza")){
            obj = factory.getType(itemname.getText());
            if(obj==null){
                return;
            }
            else{
                obj.makepizza(itemname.getText());
            }
        }
        if(!categorymenu.getPromptText().equals("Choose") && checkitem(itemname.getText()).equals(false) && !itemname.getText().isEmpty() && !itemquant.getText().isEmpty() && !itemprice.getText().isEmpty()) {
            try {
                price=Integer.parseInt(itemprice.getText());
                quantity=Integer.parseInt(itemquant.getText());
                pst = con.prepareStatement("insert into products (name,category,price,quantity)values(?,?,?,?)");
                pst.setString(1, itemname.getText());
                pst.setString(2, categorymenu.getPromptText());
                pst.setInt(3, price);
                pst.setInt(4, quantity);
                pst.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }catch (NumberFormatException e){
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Enter price and quantity as a number");
                alert.setContentText("Oops, there was an error!");
                alert.showAndWait();
            }
            updateTable();
        }else if(itemname.getText().isEmpty() || categorymenu.getPromptText().equals("Choose") || (itemprice.getText().isEmpty() && itemquant.getText().isEmpty())){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Please Fill the required information and choose category ");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }else if(checkitem(itemname.getText()).equals(true) && !itemprice.getText().isEmpty() && itemquant.getText().isEmpty()){
            try {
                price=Integer.parseInt(itemprice.getText());
                pst = con.prepareStatement("UPDATE products set price = ? WHERE name = ?");
                pst.setInt(1, price);
                pst.setString(2, itemname.getText());
                pst.executeUpdate();
                updateTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }catch (NumberFormatException e){
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Enter price as a number");
                alert.setContentText("Oops, there was an error!");
                alert.showAndWait();
            }
        }else if(checkitem(itemname.getText()).equals(true) && itemprice.getText().isEmpty() && !itemquant.getText().isEmpty()){
            try {
                quantity=Integer.parseInt(itemquant.getText());
                pst = con.prepareStatement("UPDATE products set quantity = quantity + ? WHERE name = ?");
                pst.setInt(1, quantity);
                pst.setString(2, itemname.getText());
                pst.executeUpdate();
                updateTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }catch (NumberFormatException e){
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Enter quantity as a number");
                alert.setContentText("Oops, there was an error!");
                alert.showAndWait();
            }
        }
        else if(checkitem(itemname.getText()).equals(true) && !itemprice.getText().isEmpty() && !itemquant.getText().isEmpty()){
            try {
                price=Integer.parseInt(itemprice.getText());
                quantity=Integer.parseInt(itemquant.getText());
                pst = con.prepareStatement("UPDATE products set quantity = quantity + ? , price = ? WHERE name = ?");
                pst.setInt(1, quantity);
                pst.setInt(2, price);
                pst.setString(3, itemname.getText());
                pst.executeUpdate();
                updateTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }catch (NumberFormatException e){
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Enter price and quantity as a number");
                alert.setContentText("Oops, there was an error!");
                alert.showAndWait();
            }
        }
    }
    public Boolean checkitem(String itemname){
        Connection con=null;
        PreparedStatement pst = null;
        con = databaseconnect.ConnectDB();
        try {
            pst = con.prepareStatement("SELECT name FROM products WHERE name = ? ");
            pst.setString(1,itemname);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}

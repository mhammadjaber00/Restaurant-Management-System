package Controllers;

import Models.items;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SystemController {
    @FXML
    Label user,finalprice;
    @FXML
    Button sandwichesid,platesid,burgersid,pastaid,pizzaid,dessertsid,drinksid,hotoffersid;
    @FXML
    ComboBox menu;
    @FXML
    Spinner<Integer> quantspin;
    @FXML
    TableView<items> productstable;
    @FXML
    TableColumn<items,String> colname;
    @FXML
    TableColumn<items,Integer> colprice,colquant;
    @FXML
    Pane pane;
    @FXML
    TextArea textarea;
    @FXML
    Button confirmbutton;

    ObservableList<String> listM;
    ArrayList<String> array= new ArrayList<String>();
    public void openScene(Parent root, ActionEvent event){
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
            openScene(root,event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void settext(String text){
        user.setText(text);
    }
    public void plates(){
        fillmenu(platesid.getText());
        platesid.setStyle("-fx-background-color: #5af74f; ");
        sandwichesid.setStyle("-fx-background-color: #ffbf00; ");
        burgersid.setStyle("-fx-background-color: #ffbf00; ");
        pastaid.setStyle("-fx-background-color: #ffbf00; ");
        pizzaid.setStyle("-fx-background-color: #ffbf00; ");
        dessertsid.setStyle("-fx-background-color: #ffbf00; ");
        drinksid.setStyle("-fx-background-color: #ffbf00; ");
        hotoffersid.setStyle("-fx-background-color: #ffbf00; ");
    }
    public void sandwiches(){
        fillmenu(sandwichesid.getText());
        platesid.setStyle("-fx-background-color: #ffbf00; ");
        sandwichesid.setStyle("-fx-background-color: #5af74f; ");
        burgersid.setStyle("-fx-background-color: #ffbf00; ");
        pastaid.setStyle("-fx-background-color: #ffbf00; ");
        pizzaid.setStyle("-fx-background-color: #ffbf00; ");
        dessertsid.setStyle("-fx-background-color: #ffbf00; ");
        drinksid.setStyle("-fx-background-color: #ffbf00; ");
        hotoffersid.setStyle("-fx-background-color: #ffbf00; ");
    }
    public void burgers(){
        fillmenu(burgersid.getText());
        platesid.setStyle("-fx-background-color: #ffbf00; ");
        sandwichesid.setStyle("-fx-background-color: #ffbf00; ");
        burgersid.setStyle("-fx-background-color: #5af74f; ");
        pastaid.setStyle("-fx-background-color: #ffbf00; ");
        pizzaid.setStyle("-fx-background-color: #ffbf00; ");
        dessertsid.setStyle("-fx-background-color: #ffbf00; ");
        drinksid.setStyle("-fx-background-color: #ffbf00; ");
        hotoffersid.setStyle("-fx-background-color: #ffbf00; ");
    }
    public void pasta(){
        fillmenu(pastaid.getText());
        platesid.setStyle("-fx-background-color: #ffbf00; ");
        sandwichesid.setStyle("-fx-background-color: #ffbf00; ");
        burgersid.setStyle("-fx-background-color: #ffbf00; ");
        pastaid.setStyle("-fx-background-color: #5af74f; ");
        pizzaid.setStyle("-fx-background-color: #ffbf00; ");
        dessertsid.setStyle("-fx-background-color: #ffbf00; ");
        drinksid.setStyle("-fx-background-color: #ffbf00; ");
        hotoffersid.setStyle("-fx-background-color: #ffbf00; ");
    }
    public void pizza(){
        fillmenu(pizzaid.getText());
        platesid.setStyle("-fx-background-color: #ffbf00; ");
        sandwichesid.setStyle("-fx-background-color: #ffbf00; ");
        burgersid.setStyle("-fx-background-color: #ffbf00; ");
        pastaid.setStyle("-fx-background-color: #ffbf00; ");
        pizzaid.setStyle("-fx-background-color: #5af74f; ");
        dessertsid.setStyle("-fx-background-color: #ffbf00; ");
        drinksid.setStyle("-fx-background-color: #ffbf00; ");
        hotoffersid.setStyle("-fx-background-color: #ffbf00; ");
    }
    public void desserts(){
        fillmenu(dessertsid.getText());
        platesid.setStyle("-fx-background-color: #ffbf00; ");
        sandwichesid.setStyle("-fx-background-color: #ffbf00; ");
        burgersid.setStyle("-fx-background-color: #ffbf00; ");
        pastaid.setStyle("-fx-background-color: #ffbf00; ");
        pizzaid.setStyle("-fx-background-color: #ffbf00; ");
        dessertsid.setStyle("-fx-background-color: #5af74f; ");
        drinksid.setStyle("-fx-background-color: #ffbf00; ");
        hotoffersid.setStyle("-fx-background-color: #ffbf00; ");
    }
    public void drinks(){
        fillmenu(drinksid.getText());
        platesid.setStyle("-fx-background-color: #ffbf00; ");
        sandwichesid.setStyle("-fx-background-color: #ffbf00; ");
        burgersid.setStyle("-fx-background-color: #ffbf00; ");
        pastaid.setStyle("-fx-background-color: #ffbf00; ");
        pizzaid.setStyle("-fx-background-color: #ffbf00; ");
        dessertsid.setStyle("-fx-background-color: #ffbf00; ");
        drinksid.setStyle("-fx-background-color:#5af74f; ");
        hotoffersid.setStyle("-fx-background-color: #ffbf00; ");
    }
    public void hot_offers(){
        fillmenu(hotoffersid.getText());
        platesid.setStyle("-fx-background-color: #ffbf00; ");
        sandwichesid.setStyle("-fx-background-color: #ffbf00; ");
        burgersid.setStyle("-fx-background-color: #ffbf00; ");
        pastaid.setStyle("-fx-background-color: #ffbf00; ");
        pizzaid.setStyle("-fx-background-color: #ffbf00; ");
        dessertsid.setStyle("-fx-background-color: #ffbf00; ");
        drinksid.setStyle("-fx-background-color: #ffbf00; ");
        hotoffersid.setStyle("-fx-background-color: #5af74f; ");
    }
    public void fillmenu(String name){
        listM= FXCollections.observableArrayList();
        array=itemsdb(name);
        listM.addAll(array);
        menu.setItems(listM);
    }
    public void select(){
        try {
            menu.setPromptText(menu.getValue().toString());
        }catch(NullPointerException e){
            menu.setPromptText("Select");
        }
    }
    public ArrayList<String> itemsdb(String name){
        String sql;
        ArrayList<String> array=new ArrayList<String>();
        try {
            Connection con = databaseconnect.ConnectDB();
            sql = "SELECT name from products where category = '"+name+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                array.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return array;
    }
    public void addtobill(){
        String item,finalpriceee;
        int quant,price,finalpricee;
        Alert alert;
        item=menu.getPromptText();
        quant=quantspin.getValue();
        if(!item.equals("Select")){
            price=itemprice(item);
            colname.setCellValueFactory(new PropertyValueFactory<items,String>("itemname"));
            colquant.setCellValueFactory(new PropertyValueFactory<items,Integer>("itemquantity"));
            colprice.setCellValueFactory(new PropertyValueFactory<items,Integer>("itemprice"));
            items item1=new items(item,price,quant);
            productstable.getItems().add(item1);
            finalpricee=Integer.parseInt(finalprice.getText());
            finalpricee+=(price*quant);
            finalpriceee=Integer.toString(finalpricee);
            finalprice.setText(finalpriceee);
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Choose item");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
    }
    public int itemprice(String item){
        String sql;
        try {
            Connection con = databaseconnect.ConnectDB();
            sql = "SELECT price from products where name = '"+item+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public int itemquantity(String name){
        String sql;
        try {
            Connection con = databaseconnect.ConnectDB();
            sql = "SELECT quantity from products where name = '"+name+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public void cancelorder(){
        productstable.getItems().clear();
        finalprice.setText("0");
    }
    public void deleteitem(){
        items item=productstable.getSelectionModel().getSelectedItem();
        int price,quant,total,finalpricee;
        price=item.getItemprice();
        quant=item.getItemquantity();
        total=price*quant;
        finalpricee=Integer.parseInt(finalprice.getText());
        finalpricee-=total;
        finalprice.setText(Integer.toString(finalpricee));
        productstable.getItems().removeAll(productstable.getSelectionModel().getSelectedItem());
    }
    public void paybill(){
        String item;
        int quant,avquant;
        Alert alert;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if( productstable.getItems().size()>0&&checkquant().equals(true) ){
            for( int i=0;i<productstable.getItems().size();i++){
                item=productstable.getItems().get(i).getItemname();
                quant=productstable.getItems().get(i).getItemquantity();
                avquant=itemquantity(item);
                try {
                    Connection con = databaseconnect.ConnectDB();
                    PreparedStatement pst = null;
                    pst = con.prepareStatement("UPDATE products set quantity = ? WHERE name = ?");
                    pst.setInt(1, avquant-quant);
                    pst.setString(2, item);
                    pst.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            pane.setVisible(true);
            confirmbutton.setVisible(true);
            textarea.setText("----------------------------------------------------------------------------------------\n" +
                    "                                                  GUI RESTAURANT\n" +
                    "----------------------------------------------------------------------------------------\n" +
                    " --------------------------------------- INVOICE -------------------------------------\n" +
                    "       Served By:  "+user.getText()+"\n" +
                    "       Time:  "+formatter.format(date)+"\n" +
                    "----------------------------------------------------------------------------------------\n" +
                    "       Name                                                                    Quantity               Price\n" +
                    "-----------------------------------------------------------------------------------------\n");
            for(int i=0;i<productstable.getItems().size();i++){
                textarea.setText(textarea.getText()+"       "+(i+1)+"-"+productstable.getItems().get(i).getItemname()+"\n");
                textarea.setText(textarea.getText()+"                                                                                           "+productstable.getItems().get(i).getItemquantity()+"                       "+productstable.getItems().get(i).getItemprice()*productstable.getItems().get(i).getItemquantity()+" $\n");
            }
            textarea.setText(textarea.getText()+"----------------------------------------------------------------------------------------\n");
            textarea.setText(textarea.getText()+"       Total price = "+finalprice.getText()+" $\n");
            textarea.setText(textarea.getText()+"----------------------------------------------------------------------------------------\n");
            textarea.setText(textarea.getText()+"                                                  FOR DELIVERY\n");
            textarea.setText(textarea.getText()+"                                                    01/999999\n");
            textarea.setText(textarea.getText()+"******************************************************************************\n");
            textarea.setText(textarea.getText()+"                            THANKS FOR VISITING OUR RESTAURANT\n");
            textarea.setText(textarea.getText()+"******************************************************************************\n");
            productstable.getItems().clear();
            finalprice.setText("0");

        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("There is no items selected");
            alert.setContentText("Oops, there was an error!");
            alert.showAndWait();
        }
    }
    public void confirmaction(){
        pane.setVisible(false);
        confirmbutton.setVisible(false);
    }
    public Boolean checkquant() {
        String item;
        Alert alert;
        int quant, avquant;
        for (int i = 0; i < productstable.getItems().size(); i++) {
            item = productstable.getItems().get(i).getItemname();
            quant = productstable.getItems().get(i).getItemquantity();
            avquant = itemquantity(item);
            if (quant > avquant) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("No available quantity for " + item);
                alert.setContentText("Oops, there was an error!");
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
}
package controller;

import TM.ListTM;
import db.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


/**
 * author : W.P.A.M.Nonis <ameshnonis8@gmail.com>
 * contact : 0717730167
 * date : 1/3/2023
 **/
public class TableFormController {

    public TableView<ListTM> tblList;
    public AnchorPane root;



    public void initialize(){



        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("price"));

        LoadData();

        System.out.println(autoGenarateID());
    }


    public void btnBackOnAction(ActionEvent actionEvent) {

        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/SearchForm.fxml"));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home Page");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnAddCardOnAction(ActionEvent actionEvent) {


        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/ShoppingCardForm.fxml"));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Shopping Cart");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void LoadData(){

        ObservableList<ListTM> data = tblList.getItems();

        data.clear();

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from wishlist where id = ?");
            preparedStatement.setObject(1,LoginFormController.enteredID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                String wish_id = resultSet.getString(1);
                String brand = resultSet.getString(2);
                String price = resultSet.getString(3);
                String id = resultSet.getString(4);

                ListTM object = new ListTM(wish_id,brand,price,id);

                data.add(object);
            }

            tblList.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String autoGenarateID(){

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select shopping_id from shopping order by shopping_id desc limit 1");
            boolean isExits = resultSet.next();

            if(isExits){

                String oldId = resultSet.getString(1);

                int length = oldId.length();
                String Id = oldId.substring(1, length);
                int intId = Integer.parseInt(Id);

                intId = intId +1;

                if(intId <10){
                    return "S00"+ intId;

                } else if (intId < 100) {
                    return "S0"+ intId;
                }else {
                    return "S"+ intId;
                }
            }else{
                return "S001";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }



}

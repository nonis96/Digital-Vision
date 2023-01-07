package controller;
import TM.ListTM;
import db.DBConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

/**
 * author : W.P.A.M.Nonis <ameshnonis8@gmail.com>
 * contact : 0717730167
 * date : 1/2/2023
 **/
public class SearchFormController  {
    public AnchorPane root;
    public Label lblBrand1;
    public Label lblPrice1;
    public Label lblBrand2;
    public Label lblPrice2;
    public Label lblBrand3;
    public Label lblPrice3;
    public Label lblBrand4;
    public Label lblPrice4;
    public Label lblID;

    public void initialize(){

        lblID.setText(LoginFormController.enteredID);

        LoadTable();
    }

    public void btnClickToBy1OnAction(ActionEvent actionEvent) {

        click1();

        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/TableForm.fxml"));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Wish List");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void btnClickToBy2OnAction(ActionEvent actionEvent) {

        click2();

        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/TableForm.fxml"));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Vish List");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnClickToBy3OnAction(ActionEvent actionEvent) {

        click3();

        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/TableForm.fxml"));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Vish List");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void btnClickToBy4OnAction(ActionEvent actionEvent) {

        click4();

        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/TableForm.fxml"));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Wish List");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    public void click1(){
        String id = autoGenarateid();
        String brand1Text = lblBrand1.getText();
        String price1Text = lblPrice1.getText();
        String user_id = lblID.getText();

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into wishlist values (?,?,?,?)");
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,brand1Text);
            preparedStatement.setObject(3,price1Text);
            preparedStatement.setObject(4,user_id);

            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void click2(){
        String id = autoGenarateid();
        String brand1Text = lblBrand2.getText();
        String price1Text = lblPrice2.getText();
        String user_id = lblID.getText();

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into wishlist values (?,?,?,?)");
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,brand1Text);
            preparedStatement.setObject(3,price1Text);
            preparedStatement.setObject(4,user_id);

            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void click3(){
        String id = autoGenarateid();
        String brand1Text = lblBrand3.getText();
        String price1Text = lblPrice3.getText();
        String user_id = lblID.getText();

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into wishlist values (?,?,?,?)");
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,brand1Text);
            preparedStatement.setObject(3,price1Text);
            preparedStatement.setObject(4,user_id);

            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void click4(){
        String id = autoGenarateid();
        String brand1Text = lblBrand4.getText();
        String price1Text = lblPrice4.getText();
        String user_id = lblID.getText();

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into wishlist values (?,?,?,?)");
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,brand1Text);
            preparedStatement.setObject(3,price1Text);
            preparedStatement.setObject(4,user_id);

            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String autoGenarateid(){

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select wish_id from wishlist order by wish_id desc limit 1");
            boolean isExits = resultSet.next();

            if(isExits){

                String oldId = resultSet.getString(1);

                int length = oldId.length();
                String Id = oldId.substring(1, length);
                int intId = Integer.parseInt(Id);

                intId = intId +1;

                if(intId <10){
                    return "W00"+ intId;

                } else if (intId < 100) {
                    return "W0"+ intId;
                }else {
                    return "W"+ intId;
                }
            }else{
                return "W001";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void LoadTable(){

    }
}

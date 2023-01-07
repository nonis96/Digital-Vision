package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

/**
 * author : W.P.A.M.Nonis <ameshnonis8@gmail.com>
 * contact : 0717730167
 * date : 1/2/2023
 **/
public class RegisterFormController {
    public Label lblID;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtBill;
    public PasswordField txtPassword;
    public PasswordField txtConformPassword;
    public Button btnRegister;
    public AnchorPane root;

    public void initialize(){

    }

    public void btnClickBeforeRegisterOnAction(ActionEvent actionEvent) {



    }

    public String autoGenarateId(){
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select id from customer order by id desc limit 1");
            boolean isExits = resultSet.next();

            if(isExits){

                String oldId = resultSet.getString(1);

                int length = oldId.length();
                String Id = oldId.substring(1, length);
                int intId = Integer.parseInt(Id);

                intId = intId +1;

                if(intId <10){
                    return "U00"+ intId;
                } else if (intId < 100) {
                    return "U0"+ intId;
                }else {
                    return "U"+ intId;
                }
            }else{
                return "U001";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {

        String password = txtPassword.getText();
        String confampassword = txtConformPassword.getText();

        if(password.equals(confampassword)){
            txtPassword.setStyle("-fx-border-color: transparent");
            txtConformPassword.setStyle("-fx-border-color: transparent");

            register();

        }else {
            txtPassword.setStyle("-fx-border-color: red");
            txtConformPassword.setStyle("-fx-border-color: red");

            txtPassword.requestFocus();
        }
    }

    public void register(){

        String id = autoGenarateId();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String bill = txtBill.getText();
        String password = txtPassword.getText();

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into customer values(?,?,?,?,?) ");
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,email);
            preparedStatement.setObject(4,bill);
            preparedStatement.setObject(5,password);

            int i = preparedStatement.executeUpdate();

            if(i != 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Success...........").showAndWait();

                Parent parent = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
                Scene scene = new Scene(parent);

                Stage primaryStage = (Stage) root.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Login Form");
                primaryStage.centerOnScreen();


            }else {
                new Alert(Alert.AlertType.ERROR,"Error..............").showAndWait();
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }
}

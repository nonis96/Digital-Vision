package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author : W.P.A.M.Nonis <ameshnonis8@gmail.com>
 * contact : 0717730167
 * date : 1/2/2023
 **/
public class LoginFormController {
    public AnchorPane root;
    public PasswordField txtPassword;
    public TextField txtUserName;

    public static String enteredID;

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/RegisterForm.fxml"));
        Scene scene = new Scene(parent);

        Stage primaryStage =(Stage) root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Register Form");
        primaryStage.centerOnScreen();


    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

        String username = txtUserName.getText();
        String password = txtPassword.getText();

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer where name = ? and  passward = ?");
            preparedStatement.setObject(1,username);
            preparedStatement.setObject(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            PreparedStatement preparedStatement1 = connection.prepareStatement("select * from admin where name = ? and  passward = ?");
            preparedStatement1.setObject(1,username);
            preparedStatement1.setObject(2,password);


            ResultSet resultSet1 = preparedStatement1.executeQuery();

            if(resultSet.next() || resultSet1.next()){

                enteredID = resultSet.getString(1);

                Parent parent = FXMLLoader.load(this.getClass().getResource("../view/SearchForm.fxml"));
                Scene scene = new Scene(parent);

                Stage primaryStage =(Stage) root.getScene().getWindow();

                primaryStage.setScene(scene);
                primaryStage.setTitle("Search Form");
                primaryStage.centerOnScreen();



            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid user name or password").showAndWait();
                txtUserName.clear();
                txtPassword.clear();

                txtUserName.requestFocus();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

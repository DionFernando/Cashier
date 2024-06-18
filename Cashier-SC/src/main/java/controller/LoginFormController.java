package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class LoginFormController {
    public TextField txtUsername;
    public TextField txtPassword;

    public void loginBtnOnAction(ActionEvent actionEvent) {
        boolean check = checkUsernameAndPassword();

        if (check){
            navigateToDashboard();
            System.out.println("Login Success");
        } else {
            System.out.println("Login Failed");
        }
    }

    private void navigateToDashboard() {

    }

    private boolean checkUsernameAndPassword() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.equals("cashier") && password.equals("sc123")) {
            return true;
        } else {
            return false;
        }
    }
}

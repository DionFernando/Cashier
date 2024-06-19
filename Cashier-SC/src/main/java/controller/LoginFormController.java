package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUsername;
    public TextField txtPassword;
    public AnchorPane root;

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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
            Parent rootNode=fxmlLoader.load();
            Scene scene = new Scene(rootNode);

            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Dashboard Form");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

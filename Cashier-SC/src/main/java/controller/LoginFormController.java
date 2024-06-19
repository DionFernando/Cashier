package controller;

import animatefx.animation.Pulse;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public TextField txtUsername;
    public TextField txtPassword;
    public AnchorPane root;
    public ImageView img;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pulse pulse = new Pulse(img);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();
    }

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
            System.out.println("Login Success");
            return true;

        } else {
            return false;
        }
    }
}

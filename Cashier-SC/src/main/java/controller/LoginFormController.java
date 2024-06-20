package controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    public Label lblInfo;
    public JFXButton btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pulse pulse = new Pulse(img);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();

        lblInfo.setText("");

        keyEvent();
    }

    private void keyEvent() {
        txtUsername.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPassword.requestFocus();
            }
        });

        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Assuming loginBtn is the fx:id of your login button
                btnLogin.fire();
            }
        });

    }

    public void loginBtnOnAction(ActionEvent actionEvent) {
        boolean check = checkUsernameAndPassword();

        if (check){
            lblInfo.setText("Login Success");
            navigateToDashboard();
        } else {
            lblInfo.setText("Login Failed");
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

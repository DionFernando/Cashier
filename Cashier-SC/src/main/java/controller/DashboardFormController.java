package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.tm.ItemTm;

public class DashboardFormController {
    public Label lblTotIncome;
    public TextField txtTot;
    public TableView tblCart;
    public TableColumn colItem;
    public TableColumn colQty;
    public TableColumn colPrice;

    public void initialize() {
        setCellValueFactories();
    }

    private void setCellValueFactories() {
        colItem.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void popcornBtnOnAction(ActionEvent actionEvent) {
        int qty = 0;
//        ItemTm itemTm = new ItemTm("Popcorn",qty+1, 200.00);
//        tblCart.getItems().add(itemTm);

        // if there is popcorn is inside the cart already
        if
    }

    public void chipsBtnOnAction(ActionEvent actionEvent) {
    }

    public void colaOnAction(ActionEvent actionEvent) {
    }

    public void spriteOnAction(ActionEvent actionEvent) {
    }

    public void btnSave(ActionEvent actionEvent) {
    }

    public void DecrementItemCartOnAction(ActionEvent actionEvent) {
    }

    public void logoutBtnOnAction(ActionEvent actionEvent) {
    }

    public void IncrementItemCartOnAction(ActionEvent actionEvent) {
    }
}

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
        boolean isPopCornExists = false;
        for (Object item : tblCart.getItems()) {
            ItemTm itemTm = (ItemTm) item;
            if (itemTm.getDescription().equals("Popcorn")){
                itemTm.setQty(itemTm.getQty()+1);
                itemTm.setPrice(itemTm.getPrice() + 200);
                isPopCornExists = true;
                break;
            }
        }

        if (!isPopCornExists) {
            ItemTm itemTm = new ItemTm("Popcorn", 1, 200.00);
            tblCart.getItems().add(itemTm);
        }

        tblCart.refresh();
    }

    public void chipsBtnOnAction(ActionEvent actionEvent) {
        boolean isChipsExists = false;
        for (Object item : tblCart.getItems()) {
            ItemTm itemTm = (ItemTm) item;
            if (itemTm.getDescription().equals("Chips")){
                itemTm.setQty(itemTm.getQty()+1);
                itemTm.setPrice(itemTm.getPrice() + 200);
                isChipsExists = true;
                break;
            }
        }

        if (!isChipsExists) {
            ItemTm itemTm = new ItemTm("Chips", 1, 100.00);
            tblCart.getItems().add(itemTm);
        }

        tblCart.refresh();
    }

    public void colaOnAction(ActionEvent actionEvent) {
        boolean isCocacolaExists = false;
        for (Object item : tblCart.getItems()) {
            ItemTm itemTm = (ItemTm) item;
            if (itemTm.getDescription().equals("Cocacola")){
                itemTm.setQty(itemTm.getQty()+1);
                itemTm.setPrice(itemTm.getPrice() + 300);
                isCocacolaExists = true;
                break;
            }
        }

        if (!isCocacolaExists) {
            ItemTm itemTm = new ItemTm("Cocacola", 1, 300.00);
            tblCart.getItems().add(itemTm);
        }

        tblCart.refresh();
    }

    public void spriteOnAction(ActionEvent actionEvent) {
        boolean isSpiteExists = false;
        for (Object item : tblCart.getItems()) {
            ItemTm itemTm = (ItemTm) item;
            if (itemTm.getDescription().equals("Sprite")){
                itemTm.setQty(itemTm.getQty()+1);
                itemTm.setPrice(itemTm.getPrice() + 300);
                isSpiteExists = true;
                break;
            }
        }

        if (!isSpiteExists) {
            ItemTm itemTm = new ItemTm("Sprite", 1, 300.00);
            tblCart.getItems().add(itemTm);
        }

        tblCart.refresh();
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

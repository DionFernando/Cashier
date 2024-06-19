package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
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

        addRowSwipeActionToTable(tblCart);
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
                itemTm.setPrice(itemTm.getPrice() + 100);
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
    private void addRowSwipeActionToTable(TableView<ItemTm> table) {
        table.setRowFactory(tv -> {
            TableRow<ItemTm> row = new TableRow<>();

            row.setOnMousePressed(event -> {
                if (!row.isEmpty()) {
                    row.setUserData(new double[] { event.getSceneX(), event.getSceneY() });
                }
            });

            row.setOnMouseReleased(event -> {
                if (!row.isEmpty()) {
                    double[] startCoords = (double[]) row.getUserData();
                    double endX = event.getSceneX();
                    double endY = event.getSceneY();

                    double deltaX = endX - startCoords[0];
                    double deltaY = endY - startCoords[1];

                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        if (deltaX > 0) {
                            System.out.println("Swiped right on " + row.getItem().getDescription());
                            incrementItemQty(row.getItem());
                        } else {
                            System.out.println("Swiped left on " + row.getItem().getDescription());
                            decrementItemQty(row.getItem());
                        }
                    }
                }
            });

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    System.out.println("Double clicked on " + row.getItem().getDescription());
                    tblCart.getItems().remove(row.getItem());
                    tblCart.refresh();
                }
            });

            return row;
        });
    }

    private void incrementItemQty(ItemTm item) {
        item.setQty(item.getQty() + 1);
        item.setPrice(item.getPrice() + getPriceIncrement(item.getDescription()));
        tblCart.refresh();
    }

    private void decrementItemQty(ItemTm item) {
        if (item.getQty() > 1) {
            item.setQty(item.getQty() - 1);
            item.setPrice(item.getPrice() - getPriceIncrement(item.getDescription()));
        } else {
            tblCart.getItems().remove(item);
        }
        tblCart.refresh();
    }

    private double getPriceIncrement(String description) {
        switch (description) {
            case "Popcorn": return 200;
            case "Chips": return 100;
            case "Cocacola": return 300;
            case "Sprite": return 300;
            default: return 0;
        }
    }
}

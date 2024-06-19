package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.tm.ItemTm;

public class DashboardFormController {
    public Label lblTotIncome;
    public TextField txtTot;
    public TableView<ItemTm> tblCart;
    public TableColumn<ItemTm, String> colItem;
    public TableColumn<ItemTm, Integer> colQty;
    public TableColumn<ItemTm, Double> colPrice;

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
        addItemToCart("Popcorn", 200);
    }

    public void chipsBtnOnAction(ActionEvent actionEvent) {
        addItemToCart("Chips", 100);
    }

    public void colaOnAction(ActionEvent actionEvent) {
        addItemToCart("Cocacola", 300);
    }

    public void spriteOnAction(ActionEvent actionEvent) {
        addItemToCart("Sprite", 300);
    }

    public void btnSave(ActionEvent actionEvent) {
    }

    public void DecrementItemCartOnAction(ActionEvent actionEvent) {
    }

    public void logoutBtnOnAction(ActionEvent actionEvent) {
    }

    public void IncrementItemCartOnAction(ActionEvent actionEvent) {
    }

    private void addItemToCart(String description, double price) {
        boolean isItemExists = false;
        for (ItemTm itemTm : tblCart.getItems()) {
            if (itemTm.getDescription().equals(description)) {
                itemTm.setQty(itemTm.getQty() + 1);
                itemTm.setPrice(itemTm.getPrice() + price);
                isItemExists = true;
                break;
            }
        }

        if (!isItemExists) {
            ItemTm itemTm = new ItemTm(description, 1, price);
            tblCart.getItems().add(itemTm);
        }
        setTotal();
        tblCart.refresh();
    }

    private void addRowSwipeActionToTable(TableView<ItemTm> table) {
        table.setRowFactory(tv -> {
            TableRow<ItemTm> row = new TableRow<>();

            row.setOnMousePressed(event -> {
                if (!row.isEmpty()) {
                    row.setUserData(new double[] { event.getSceneX(), event.getSceneY() });
                }
            });

            row.setOnMouseDragged(event -> {
                if (!row.isEmpty()) {
                    double[] startCoords = (double[]) row.getUserData();
                    double deltaX = event.getSceneX() - startCoords[0];
                    row.setTranslateX(deltaX);

                    // Update row color based on swipe direction
                    if (deltaX > 0) {
                        row.setStyle("-fx-background-color: lightgreen;");
                    } else {
                        row.setStyle("-fx-background-color: lightcoral;");
                    }
                }
            });

            row.setOnMouseReleased(event -> {
                if (!row.isEmpty()) {
                    double[] startCoords = (double[]) row.getUserData();
                    double endX = event.getSceneX();
                    double deltaX = endX - startCoords[0];

                    TranslateTransition transition = new TranslateTransition(Duration.millis(300), row);

                    if (Math.abs(deltaX) > 50) { // Detect swipe if moved more than 50 pixels
                        if (deltaX > 0) {
                            // Swipe right
                            incrementItemQty(row.getItem());
                        } else {
                            // Swipe left
                            decrementItemQty(row.getItem());
                        }
                        transition.setToX(0); // Reset position after swipe action
                    } else {
                        transition.setToX(0); // Reset position if not a swipe
                    }
                    transition.play();

                    // Reset row style after the transition
                    transition.setOnFinished(e -> row.setStyle(""));
                }
            });

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    tblCart.getItems().remove(row.getItem());
                    tblCart.refresh();
                    setTotal();
                }
            });

            return row;
        });
    }

    private void incrementItemQty(ItemTm item) {
        item.setQty(item.getQty() + 1);
        item.setPrice(item.getPrice() + getPriceIncrement(item.getDescription()));
        setTotal();
        tblCart.refresh();
    }

    private void decrementItemQty(ItemTm item) {
        if (item.getQty() > 1) {
            item.setQty(item.getQty() - 1);
            item.setPrice(item.getPrice() - getPriceIncrement(item.getDescription()));
        } else {
            tblCart.getItems().remove(item);
        }
        setTotal();
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

    private void setTotal() {
        double total = 0;
        for (ItemTm item : tblCart.getItems()) {
            total += item.getPrice();
        }
        txtTot.setText(String.valueOf(total));
    }
}

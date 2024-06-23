package controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.Item;
import model.Total;
import model.tm.ItemTm;
import repository.ExpenseRepo;
import repository.ItemRepo;
import repository.SpendRepo;
import repository.TotalRepo;

import java.sql.SQLException;

public class DashboardFormController {
    public Label lblTotIncome;
    public TextField txtTot;
    public TableView<ItemTm> tblCart;
    public TableColumn<ItemTm, String> colItem;
    public TableColumn<ItemTm, Integer> colQty;
    public TableColumn<ItemTm, Double> colPrice;
    public ImageView imgChips;
    public ImageView imgPopcorn;
    public ImageView imgSprite;
    public ImageView imgCoke;
    public Label lblPopcornIncome;
    public Label lblChipsIncome;
    public Label lblCokeIncome;
    public Label lblSpriteIncome;
    public Label lblTotSaleCount;
    public TextField txtBalance;
    public TextField txtCash;
    public JFXButton btnPopcorn;
    public TextField txtExpense;
    public JFXButton btnDe;
    public Label lblOriginalSales;
    public Label lblTotExpenses;
    TotalRepo totalRepo = new TotalRepo();
    ItemRepo itemRepo = new ItemRepo();
    ExpenseRepo expenseRepo = new ExpenseRepo();
    SpendRepo spendRepo = new SpendRepo();

    public void initialize() {
        setCellValueFactories();

        Pulse pulse = new Pulse(imgCoke);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();

        pulse = new Pulse(imgSprite);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();

        pulse = new Pulse(imgPopcorn);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();

        pulse = new Pulse(imgChips);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();

        tblCart.setFixedCellSize(60);
        tblCart.prefHeightProperty().bind(tblCart.fixedCellSizeProperty().multiply(5));
        tblCart.setStyle("-fx-font-size: 23px; -fx-font-family: 'URW Gothic L';");

        Platform.runLater(() -> {

                    try {
                        itemRepo.checkEmptyAndInsert();
                        lblTotSaleCount.setText(String.format("%d", totalRepo.getSaleCount()));
                        lblTotIncome.setText(String.format("Rs. %.2f", totalRepo.getSaleTotal()));
                        lblPopcornIncome.setText(String.format("Rs. %.2f", itemRepo.getItemIncome("Popcorn")));
                        lblChipsIncome.setText(String.format("Rs. %.2f", itemRepo.getItemIncome("Chips")));
                        lblCokeIncome.setText(String.format("Rs. %.2f", itemRepo.getItemIncome("Coke")));
                        lblSpriteIncome.setText(String.format("Rs. %.2f", itemRepo.getItemIncome("Sprite")));
                        lblTotExpenses.setText(String.format("Rs. %.2f", expenseRepo.getExpenseTotal()));
                        lblOriginalSales.setText(String.format("Rs. %.2f", (totalRepo.getSaleTotal() + expenseRepo.getExpenseTotal())));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
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

        try {
            double totalIncome = 0;
            double popcornIncome = 0;
            double chipsIncome = 0;
            double cokeIncome = 0;
            double spriteIncome = 0;
            int totalSaleCount = totalRepo.getSaleCount();

            try {
                for (ItemTm item : tblCart.getItems()) {
                    totalIncome += item.getPrice();
                    switch (item.getDescription()) {
                        case "Popcorn":
                            popcornIncome += item.getPrice();
                            itemRepo.updateItemIncome(new Item("Popcorn", popcornIncome));
                            break;
                        case "Chips":
                            chipsIncome += item.getPrice();
                            itemRepo.updateItemIncome(new Item("Chips", chipsIncome));
                            break;
                        case "Cocacola":
                            cokeIncome += item.getPrice();
                            itemRepo.updateItemIncome(new Item("Coke", cokeIncome));
                            break;
                        case "Sprite":
                            spriteIncome += item.getPrice();
                            itemRepo.updateItemIncome(new Item("Sprite", spriteIncome));
                            break;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (!tblCart.getItems().isEmpty()) {
                totalSaleCount =totalSaleCount+1;
            }

            Total total = new Total(totalSaleCount, totalIncome);
            totalRepo.saveOrUpdateTotalSales(total);

            Double income = totalRepo.getSaleTotal();
            int salesCount = totalRepo.getSaleCount();
            lblTotIncome.setText(String.format("Rs. %.2f", income));
            lblTotSaleCount.setText(String.format("%d", salesCount));

            Double popcorn = null;
            Double chips = null;
            Double coke = null;
            Double sprite = null;

            try {
                popcorn = itemRepo.getItemIncome("Popcorn");
                chips = itemRepo.getItemIncome("Chips");
                coke = itemRepo.getItemIncome("Coke");
                sprite = itemRepo.getItemIncome("Sprite");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            lblPopcornIncome.setText(String.format("Rs. %.2f", popcorn));
            lblChipsIncome.setText(String.format("Rs. %.2f", chips));
            lblCokeIncome.setText(String.format("Rs. %.2f", coke));
            lblSpriteIncome.setText(String.format("Rs. %.2f", sprite));


            tblCart.getItems().clear();
            txtTot.clear();
            txtCash.clear();
            txtBalance.clear();
            lblOriginalSales.setText(String.format("Rs. %.2f", (totalRepo.getSaleTotal()+expenseRepo.getExpenseTotal())));
            btnPopcorn.requestFocus();

            new Alert(Alert.AlertType.INFORMATION, "Sale saved successfully").show();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save sale").show();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void logoutBtnOnAction(ActionEvent actionEvent) {
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

                    if (Math.abs(deltaX) > 50) {
                        if (deltaX > 0) {
                            incrementItemQty(row.getItem());
                        } else {
                            decrementItemQty(row.getItem());
                        }
                        transition.setToX(0);
                    } else {
                        transition.setToX(0);
                    }
                    transition.play();


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

    public void onCashReleased(KeyEvent keyEvent) {
        double cash = 0.0;
        if (!txtCash.getText().isEmpty()) {
            cash = Double.parseDouble(txtCash.getText());
        }

        double total = 0.0;
        if (!txtTot.getText().isEmpty()) {
            total = Double.parseDouble(txtTot.getText());
        }

        double balance = cash - total;
        if (balance >= 0){
            txtBalance.setText(String.valueOf(balance));
        } else {
            txtBalance.setText("Insufficient Cash");
        }

        if (keyEvent.getCode().toString().equals("ENTER")) {
            btnSave(new ActionEvent());
        }
    }

    public void onFReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("F1")) {
            txtCash.requestFocus();
        }
        if (keyEvent.getCode().toString().equals("F2")) {
            txtExpense.requestFocus();
        }
    }

    public void onTxtExpense(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            btnDeduct(new ActionEvent());
        }
    }

    public void btnDeduct(ActionEvent actionEvent) {
        double expense = 0.0;
        if (!txtExpense.getText().isEmpty()) {
            expense = Double.parseDouble(txtExpense.getText());
        }

        try {
            spendRepo.expenseDeductAndTotalSaleUpdate(expense);
            lblTotIncome.setText(String.format("Rs. %.2f", totalRepo.getSaleTotal()));
            txtExpense.clear();

            lblTotExpenses.setText(String.format("Rs. %.2f", expenseRepo.getExpenseTotal()));

            lblOriginalSales.setText(String.format("Rs. %.2f", (totalRepo.getSaleTotal()+expenseRepo.getExpenseTotal())));
            new Alert(Alert.AlertType.INFORMATION, "Expense deducted successfully").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to deduct expense").show();
            throw new RuntimeException(e);
        }
    }

    public void onExpenseReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            btnDeduct(new ActionEvent());
        }
    }
}

package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Item {
    private int itemCode;
    private String description;
    private double totalPrice;

    public Item(String description, double Income) {
        this.description = description;
        this.totalPrice = Income;
    }
}

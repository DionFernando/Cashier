package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Item {
    private String itemCode;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}

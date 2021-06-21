package app.core.rest.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductsRegisterOrder {
    private long storeId;
    private long userId;
    private int commonPrice;
    private int stockPrice;
    private String token;
    private List<ProductOrderItem> products;
}
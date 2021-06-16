package app.data.modeles;

import lombok.Data;

@Data
public class OrderProduct {
    private long orderId;
    private long productId;
    private int countOfProducts;
    private Product product;
}
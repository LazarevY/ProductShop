package app.data.modeles;

import lombok.Data;

@Data
public class OrderProduct {
    private Long orderId;
    private Long productId;
    private int countOfProducts;
}
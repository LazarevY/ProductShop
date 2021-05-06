package app.data.modeles;

import lombok.Data;

@Data
public class ProductInStore {
    private Product product;
    private long storeId;
    private int price;
    private int count;
}

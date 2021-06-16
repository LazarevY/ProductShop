package app.data.modeles;

import lombok.Data;

@Data
public class ProductInStore {
    private Product product;
    private Stock stock = null;
    private long storeId;
    private double price;
    private int count;
}

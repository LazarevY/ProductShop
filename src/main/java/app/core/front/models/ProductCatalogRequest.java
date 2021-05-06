package app.core.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCatalogRequest {

    private long storeId;
    private int priceLow;
    private int priceHigh;
    private String namePattern;

}

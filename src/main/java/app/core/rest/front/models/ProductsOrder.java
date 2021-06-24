package app.core.rest.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductsOrder {
    private long storeId;
    private List<ProductOrderItem> products;
}

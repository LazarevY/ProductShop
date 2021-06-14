package app.core.rest.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductOrderItem {
    private long productId;
    private int count;
}

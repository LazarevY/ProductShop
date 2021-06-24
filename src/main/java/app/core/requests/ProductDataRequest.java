package app.core.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDataRequest {
    private long productId;
    private long storeId;
}

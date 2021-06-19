package app.core.rest.front.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularForProductRequest {
    private long storeId;
    private long productId;
}

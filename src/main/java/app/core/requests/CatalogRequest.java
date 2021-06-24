package app.core.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CatalogRequest {
    private String token;
    private long storeId;
    private int priceLow;
    private int priceHigh;
    private String namePattern;
    private List<Integer> categories;
    private Long userId;
}

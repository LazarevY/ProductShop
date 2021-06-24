package app.core.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MultipleProductRequest {
    private long storeId;
    private List<Long> productIds;
}

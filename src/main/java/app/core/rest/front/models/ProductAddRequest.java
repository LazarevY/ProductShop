package app.core.rest.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductAddRequest {
    private String name;
    private String description;
    private int weight;
    private int calories;
    private ProductMetadataAddRequest metadata;
}

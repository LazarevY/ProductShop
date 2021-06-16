package app.data.modeles;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Product {
    private long id;
    private String name;
    private String description;
    private long weight;
    private long calories;
    private long metadataId;
    private ProductMetadata metadata;
    private List<ProductCategory> categories = new ArrayList<>();
}

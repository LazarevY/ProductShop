package app.data.modeles;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Long weight;
    private Long calories;
    private Long metadataId;

    private List<Order> productsOrders= new ArrayList<>();
    private List<ProductCategory> categories = new ArrayList<>();
}

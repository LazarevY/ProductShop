package app.data.modeles;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductCategory {
    private Long id;
    private String name;

    private List<Product> products = new ArrayList<>();
}

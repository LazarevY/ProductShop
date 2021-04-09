package app.data.modeles;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private Long weight;
    private Long price;
    private Long energyPrice;
    private String image;
}

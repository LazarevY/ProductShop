package app.data.modeles;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Long orderStatusId;
    private Long userId;
    private Long storeId;
    private Long commonPrice;
    private Long stockPrice;
    private String date;

    private List<Product> orderProducts= new ArrayList<>();
}
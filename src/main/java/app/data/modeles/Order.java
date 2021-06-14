package app.data.modeles;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Long orderStatusId;
    private Long userId;
    private Long storeId;
    private double commonPrice;
    private double stockPrice;
    private Date date;

    private List<Product> orderProducts = new ArrayList<>();
}
package app.data.modeles;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private long id;
    private long orderStatusId;
    private long userId;
    private long storeId;
    private double commonPrice;
    private double stockPrice;
    private Date date;

    private List<OrderProduct> orderProducts = new ArrayList<>();
}
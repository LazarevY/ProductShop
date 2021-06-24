package app.data.modeles;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Stock {
    private long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<StockClause> stockClauses = new ArrayList<>();
}

package app.data.modeles;

import lombok.Data;

import java.util.Date;

@Data
public class Stock {
    private long id;
    private String name;
    private Date startDate;
    private Date endDate;
}

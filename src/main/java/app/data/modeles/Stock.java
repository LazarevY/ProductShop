package app.data.modeles;

import lombok.Data;

@Data
public class Stock {
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
}

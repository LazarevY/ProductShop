package app.data.modeles;

import lombok.Data;

@Data
public class StockClause {
    private long id;
    private StockClauseItem stockClauseItem;
    private String clauseValue;
}

package app.data.mappers;

        import app.data.modeles.StockClauseItems;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Param;
        import org.apache.ibatis.annotations.Select;

        import java.util.List;

@Mapper
public interface StockClauseItemsMapper {

    @Select("SELECT * FROM Stock_Clause_Items WHERE stock_clause_item_id = #{id}")
    StockClauseItems getStockClauseItems(@Param("id") Long id);

    @Select("SELECT * FROM Stock_Clause_Items")
    List<StockClauseItems> findAll();

    @Insert("INSERT INTO Stock_Clause_Items (item_name) VALUES (#{name})")
    void addStockClauseItems(String name);

}
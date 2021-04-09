package app.data.mappers;

        import app.data.modeles.Store;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Param;
        import org.apache.ibatis.annotations.Select;

        import java.util.List;

@Mapper
public interface StoreMapper {

    @Select("SELECT * FROM Store WHERE store_id = #{id}")
    Store getStore(@Param("id") Long id);

    @Select("SELECT * FROM Store")
    List<Store> findAll();

    @Insert("INSERT INTO Store (address, name) VALUES (#{address}, #{name})")
    void addStore(String address, String name);

}
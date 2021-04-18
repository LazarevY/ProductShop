package app.data.mappers;

import app.data.modeles.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {

    void addStore(@Param("address") String address);

    void updateStore(@Param("id") long id, @Param("address") String address);

    Store getById(@Param("id") long id);

    List<Store> findAll();

    void  deleteById(@Param("id") long id);

}

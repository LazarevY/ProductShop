package app.data.mappers;

import app.data.modeles.Store;
import app.data.modeles.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAddressMapper {

    void addUserAddress(@Param("userId") long userId, @Param("address") String address);

    void updateAddress(@Param("id") long id, @Param("userId") long userId, @Param("address") String address);

    UserAddress getById(@Param("id") long id);

    void deleteById(@Param("id") long id);

    List<UserAddress> getAllByUserId(@Param("userId") long userId);

    List<UserAddress> findAll();

}

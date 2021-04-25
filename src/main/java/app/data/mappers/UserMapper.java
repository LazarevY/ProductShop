package app.data.mappers;

import app.data.modeles.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lazarev Yaroslav
 */

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE user_id = #{id}")
    @ResultMap("UserResultMap")
    User getUser(@Param("id") Long id);

    User getByEmail(@Param("email") String email);

    @Select("SELECT * FROM users")
    @ResultMap("UserResultMap")
    List<User> findAll();

    void addUser(@Param("firstName") String firstName,
                 @Param("lastName") String lastName,
                 @Param("phone") String phone,
                 @Param("email") String email,
                 @Param("passwordHash") String passwordHash);

    void deleteById(@Param("id") Long id);

}

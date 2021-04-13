package app.data.mappers;

import app.data.modeles.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Lazarev Yaroslav
 */

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE user_id = #{id}")
    @ResultMap("UserResultMap")
    User getUser(@Param("id") Long id);

    @Select("SELECT * FROM users")
    @ResultMap("UserResultMap")
    List<User> findAll();

    @Insert("INSERT INTO users (user_first_name, user_last_name, user_phone, user_email, user_password_hash) " +
            "VALUES (#{firstName}, #{lastName}, #{phone}, #{email}, #{passwordHash})")
    void addUser(String firstName, String lastName, String phone, String email, String passwordHash);

}

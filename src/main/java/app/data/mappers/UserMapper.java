package app.data.mappers;

import app.data.modeles.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lazarev Yaroslav
 */

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM User WHERE user_id = #{id}")
    User getUser(@Param("id") Long id);

    @Select("SELECT * FROM User")
    List<User> findAll();

    @Insert("INSERT INTO User (first_name, last_name, phone_number) VALUES (#{firstName}, #{lastName}, #{phone})")
    void addUser(String firstName, String lastName, String phone);

}

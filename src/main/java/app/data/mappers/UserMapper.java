package app.data.mappers;

import app.data.modeles.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lazarev Yaroslav
 */

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE user_id = #{id}")
    User getUser(@Param("id") Long id);

    @Select("select * from users")
    List<User> findAll();

}

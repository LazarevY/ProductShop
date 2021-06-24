package app.data.mappers;

import app.data.modeles.Role;
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

    void addUserRole(@Param("user_id") long user_id, @Param("role_id") long role_id);

    List<Role> findRolesByUserId(@Param("user_id") long user_id);

    void updateUser(@Param("id") long id,
                 @Param("firstName") String firstName,
                 @Param("lastName") String lastName,
                 @Param("phone") String phone,
                 @Param("email") String email,
                 @Param("passwordHash") String passwordHash);

    void updateUserMain(@Param("id") long id,
                    @Param("firstName") String firstName,
                    @Param("lastName") String lastName,
                    @Param("phone") String phone);

}

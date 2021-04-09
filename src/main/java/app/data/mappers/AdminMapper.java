package app.data.mappers;

import app.data.modeles.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("SELECT * Admin_Data Stock WHERE admin_id = #{id}")
    Admin getAdmin(@Param("id") Long id);

    @Select("SELECT * FROM Admin_Data")
    List<Admin> findAll();

    @Insert("INSERT INTO Admin_Data (name) VALUES (#{name})")
    void addAdmin(String name);

}

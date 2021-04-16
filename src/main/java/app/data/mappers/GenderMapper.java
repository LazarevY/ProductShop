package app.data.mappers;

import app.data.modeles.Gender;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GenderMapper {

    @Select("SELECT * FROM gender WHERE gender_id = #{id}")
    @ResultMap("GenderResultMap")
    Gender getGender(@Param("id") Long id);

    @Select("SELECT * FROM gender")
    @ResultMap("GenderResultMap")
    List<Gender> getAll();

    @Insert("INSERT INTO gender (gender_name) " +
            "VALUES (#{name})")
    void addGender(String name);

}

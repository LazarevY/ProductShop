package app.data.mappers;

import app.data.modeles.Gender;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GenderMapper {

    Gender getGender(@Param("id") Long id);

    List<Gender> getAll();

    void addGender(@Param("name") String name);

    void deleteById(@Param("id") long id);

}

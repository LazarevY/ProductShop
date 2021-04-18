package app.data.mappers;

import app.data.modeles.UserCalorieData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserCalorieDataMapper {

    void addCalorieData(@Param("userId") long userId, @Param("weight") int weight, @Param("growth") int growth, @Param("genderId") long genderId, @Param("currentNorm") int currentNorm);

    void updateCalorieData(@Param("userId") long userId, @Param("weight") int weight, @Param("growth") int growth, @Param("genderId") long genderId, @Param("currentNorm") int currentNorm);

    UserCalorieData getByUserId(@Param("userId") long userId);

    void deleteByUserId(@Param("userId") long userId);

}

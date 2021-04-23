package app.data.mappers;

import app.data.modeles.PayMethod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PayMethodMapper {
    void addPayMethod(@Param("userId") long userId,
                      @Param("cardNumber") String cardNumber,
                      @Param("dateEnd") String dateEnd);

    List<PayMethod> getAllByUserId(@Param("userId") long userId);

    PayMethod getById(@Param("id") long id);

    List<PayMethod> findAll();

    void deleteById(@Param("id") long id);
}

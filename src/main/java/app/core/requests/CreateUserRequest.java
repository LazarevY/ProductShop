package app.core.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

@Data
@AllArgsConstructor
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
}

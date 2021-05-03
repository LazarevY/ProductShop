package app.core.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseMessage {
    private Boolean success;
    private String message;
}

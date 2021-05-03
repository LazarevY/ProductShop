package app.core.security.dto;

import lombok.Data;

@Data
public class ApiResponseMessage {
    private Boolean success;
    private String message;
}

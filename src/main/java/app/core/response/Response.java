package app.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Response {

    public Response() {
        code = ResponseCode.OK;
        message = "";
        parameters = new HashMap<>();
    }
    public Response(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
        parameters = new HashMap<>();
    }


    private ResponseCode code;
    private String message;
    private Map<String, Object> parameters;


    public Object addParameter(String name, Object value) {
        var old = parameters.getOrDefault(name, null);
        parameters.put(name, value);
        return old;
    }

    public Object getParameter(String name){
        return parameters.getOrDefault(name, null);
    }

    public void removeParameter(String name){
        parameters.remove(name);
    }
}

package app.data.modeles;

import lombok.Data;

import java.util.Date;

@Data
public class PayMethod {
    private long id;
    private long methodId;
    private String cardNumber;
    private Date dateEnd;
}

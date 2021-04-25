package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface OrdersService {
    Response getOrders(String email);
}

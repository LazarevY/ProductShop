package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface UserOrdersService {
    Response getOrders(long userId);
}

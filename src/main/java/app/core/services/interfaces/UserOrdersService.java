package app.core.services.interfaces;

import app.core.response.Response;
import app.core.rest.front.models.ProductOrderItem;
import app.core.rest.front.models.ProductsOrder;
import app.core.rest.front.models.ProductsRegisterOrder;
import org.springframework.stereotype.Service;

@Service
public interface UserOrdersService {
    Response getOrders(long userId);
    Response validateOrder(ProductsOrder order);
    Response registerOrder(ProductsRegisterOrder order);
    Response getOrderPrice(ProductsOrder order);
}

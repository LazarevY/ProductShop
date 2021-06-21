package app.core.services.impl;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.rest.front.models.ProductsOrder;
import app.core.rest.front.models.ProductsRegisterOrder;
import app.core.services.interfaces.StockService;
import app.core.services.interfaces.UserOrdersService;
import app.data.mappers.OrderMapper;
import app.data.mappers.OrderProductMapper;
import app.data.mappers.ProductsStoresMapper;
import app.data.mappers.UserMapper;
import app.data.modeles.ProductInStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class UserOrdersServiceImpl implements UserOrdersService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private ProductsStoresMapper productsStoresMapper;

    @Autowired
    private StockService stockService;

    @Override
    public Response getOrders(long userId) {
        var order = orderMapper.getByUserId(userId);
        Response r = new Response();
        r.setCode(ResponseCode.OK);
        r.addParameter("userOrders", order);
        return r;
    }

    @Override
    public Response validateOrder(ProductsOrder order) {
        var storeId = order.getStoreId();
        Response r = new Response();
        var map = new HashMap<Long, Integer>();
        for (var p: order.getProducts()) {
            ProductInStore productData = productsStoresMapper.getProductData(storeId, p.getProductId());
            if (p.getCount() > productData.getCount())
                map.put(p.getProductId(), productData.getCount());
        }
        r.addParameter("missing", map);
        r.addParameter("productsValidated", map.isEmpty());
        return r;
    }

    @Override
    public Response registerOrder(ProductsRegisterOrder order) {
        var storeId = order.getStoreId();
        Response r = new Response();
//        for (var p: order.getProducts()){
//            ProductInStore productData = productsStoresMapper.getProductData(storeId, p.getProductId());
//            productsStoresMapper.updateProductCount(storeId, p.getProductId(),
//                    productData.getCount() - p.getCount());
//        }

        var date = new Date();
        int orderId = orderMapper.addOrder(0, order.getUserId(),
                storeId,
                order.getCommonPrice(),
                order.getStockPrice(),
                date);

        for (var p: order.getProducts())
            orderProductMapper.addOrderProduct(orderId, p.getProductId(), p.getCount());

        r.setCode(ResponseCode.OK);
        return r;
    }

    @Override
    public Response getOrderPrice(ProductsOrder order) {
        Response r = new Response();
        double price = 0;
        double stock = 0;
        for (var p: order.getProducts()){
            double v = stockService.calculateStock(p.getProductId(), order.getStoreId(), p.getCount());
            ProductInStore productData = productsStoresMapper.getProductData(order.getStoreId(), p.getProductId());
            stock += v;
            price += p.getCount() * productData.getPrice();
        }
        r.addParameter("price", price);
        r.addParameter("stock", stock);
        return r;
    }
}

package app.core.services.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface StockService {
    double calculateStock(long productId, long storeId, int productCount);
}

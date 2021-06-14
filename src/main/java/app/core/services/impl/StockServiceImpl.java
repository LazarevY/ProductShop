package app.core.services.impl;

import app.core.services.interfaces.StockService;
import app.data.mappers.ProductsStoresMapper;
import app.data.modeles.ProductInStore;
import app.data.modeles.StockClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private ProductsStoresMapper productsStoresMapper;

    @Override
    public double calculateStock(long productId, long storeId, int productCount) {
        ProductInStore productData = productsStoresMapper.getProductData(storeId, productId);
        if (productData.getStock() == null)
            return 0;
        if (productData.getStock().getEndDate().after(new Date()))
            return 0;

        StockClause stockClause = productData.getStock().getStockClauses().get(0);

        if (stockClause.getStockClauseItem().getName().equals("percent")){
            int percent = Integer.parseInt(stockClause.getClauseValue());
            return calculateByPercent(productCount, productData.getPrice(), percent);
        }
        else if (stockClause.getStockClauseItem().getName().equals("free_for_count")){
            String[] ts = stockClause.getClauseValue().split("t");
            int need = Integer.parseInt(ts[0]);
            int free = Integer.parseInt(ts[1]);
            return calculateByForFree(productCount, productData.getPrice(), need, free);
        }

        return 0;

    }

    private double calculateByPercent(int count, double price, int percent){
        double factor = (100.0 - percent) / 100;
        return count * (price * factor);
    }

    private double calculateByForFree(int count, double price, int needForFree, int freeCount){
        int free = count / (needForFree + freeCount);
        return free * freeCount * price;
    }
}
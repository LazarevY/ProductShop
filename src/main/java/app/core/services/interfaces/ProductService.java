package app.core.services.interfaces;

import app.core.requests.CatalogRequest;
import app.core.requests.MultipleProductRequest;
import app.core.requests.ProductDataRequest;
import app.core.rest.front.models.ProductAddRequest;
import app.data.modeles.ProductCategory;
import app.data.modeles.ProductInStore;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ProductService {
    List<ProductInStore> getUserOrientedCatalog(CatalogRequest request);
    List<ProductInStore> getGuestCatalog(CatalogRequest request);
    ProductInStore getProductData(ProductDataRequest request);
    List<ProductInStore> getProductsData(MultipleProductRequest request);
    void addProduct(ProductAddRequest request);
    List<ProductCategory> getAllProductCategories();
    List<ProductInStore> getProductsWithStocks(long storeId, Date date);
    List<ProductInStore> getMostPopular(long storeId, int max);
    List<ProductInStore> getMostPopularForProduct(long storeId, long productId, int max);
}

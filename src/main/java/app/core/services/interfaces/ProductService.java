package app.core.services.interfaces;

import app.core.requests.CatalogRequest;
import app.core.requests.MultipleProductRequest;
import app.core.requests.ProductDataRequest;
import app.data.modeles.ProductInStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductInStore> getUserOrientedCatalog(CatalogRequest request);
    List<ProductInStore> getGuestCatalog(CatalogRequest request);
    ProductInStore getProductData(ProductDataRequest request);
    List<ProductInStore> getProductsData(MultipleProductRequest request);
}

package app.core.services.interfaces;

import app.core.requests.CatalogRequest;
import app.data.modeles.Product;
import app.data.modeles.ProductInStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductInStore> getUserOrientedCatalog(CatalogRequest request);
    List<ProductInStore> getGuestCatalog(CatalogRequest request);
}

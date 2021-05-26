package app.core.services.impl;

import app.core.requests.CatalogRequest;
import app.core.requests.MultipleProductRequest;
import app.core.requests.ProductDataRequest;
import app.core.services.interfaces.ProductService;
import app.data.mappers.ProductsStoresMapper;
import app.data.modeles.ProductInStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsStoresMapper productMapper;

    @Override
    public List<ProductInStore> getUserOrientedCatalog(CatalogRequest request) {
        return productMapper.getUserOrientedList(request.getStoreId(),
                request.getUserId(),
                request.getPriceLow(),
                request.getPriceHigh(),
                request.getNamePattern(),
                request.getCategories().stream().mapToInt(i -> i).toArray());
    }

    @Override
    public List<ProductInStore> getGuestCatalog(CatalogRequest request) {
        return productMapper.getAllInStoreFilteredByPriceAndNameAndCategory(
                request.getStoreId(),
                request.getPriceLow(),
                request.getPriceHigh(),
                request.getNamePattern(),
                request.getCategories().stream().mapToInt(i -> i).toArray());
    }

    @Override
    public ProductInStore getProductData(ProductDataRequest request) {
        return productMapper.getProductData(request.getStoreId(), request.getProductId());
    }

    @Override
    public List<ProductInStore> getProductsData(MultipleProductRequest request) {
        return productMapper.getProductsData(request.getStoreId(), request.getProductIds());
    }
}

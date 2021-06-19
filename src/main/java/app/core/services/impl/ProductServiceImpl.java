package app.core.services.impl;

import app.core.requests.CatalogRequest;
import app.core.requests.MultipleProductRequest;
import app.core.requests.ProductDataRequest;
import app.core.rest.front.models.ProductAddRequest;
import app.core.services.interfaces.ProductService;
import app.data.mappers.ProductCategoryMapper;
import app.data.mappers.ProductMapper;
import app.data.mappers.ProductMetadataMapper;
import app.data.mappers.ProductsStoresMapper;
import app.data.modeles.ProductCategory;
import app.data.modeles.ProductInStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsStoresMapper productsStoresMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductMetadataMapper productMetadataMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductInStore> getUserOrientedCatalog(CatalogRequest request) {
        return productsStoresMapper.getUserOrientedList(request.getStoreId(),
                request.getUserId(),
                request.getPriceLow(),
                request.getPriceHigh(),
                request.getNamePattern(),
                request.getCategories().stream().mapToInt(i -> i).toArray());
    }

    @Override
    public List<ProductInStore> getGuestCatalog(CatalogRequest request) {
        return productsStoresMapper.getAllInStoreFilteredByPriceAndNameAndCategory(
                request.getStoreId(),
                request.getPriceLow(),
                request.getPriceHigh(),
                request.getNamePattern(),
                request.getCategories().stream().mapToInt(i -> i).toArray());
    }

    @Override
    public ProductInStore getProductData(ProductDataRequest request) {
        return productsStoresMapper.getProductData(request.getStoreId(), request.getProductId());
    }

    @Override
    public List<ProductInStore> getProductsData(MultipleProductRequest request) {
        return productsStoresMapper.getProductsData(request.getStoreId(), request.getProductIds());
    }

    @Override
    public void addProduct(ProductAddRequest request) {
        int mId = productMetadataMapper.addProductMetadata(request.getMetadata().getFilename());
        productMapper.addProduct(request.getName(),
                request.getDescription(), request.getWeight(),
                request.getCalories(), mId);
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryMapper.getAll();
    }

    @Override
    public List<ProductInStore> getProductsWithStocks(long storeId, Date date) {
        return productsStoresMapper.getAllStocks(storeId, date);
    }

    @Override
    public List<ProductInStore> getMostPopular(long storeId, int max) {
        return productsStoresMapper.getMostPopular(storeId, max);
    }

    @Override
    public List<ProductInStore> getMostPopularForProduct(long storeId, long productId, int max) {
        return productsStoresMapper.getMostPopularForProduct(storeId, productId, max);
    }
}

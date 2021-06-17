package app.core.rest.controllers;


import app.core.requests.CatalogRequest;
import app.core.requests.MultipleProductRequest;
import app.core.requests.ProductDataRequest;
import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.security.JwtProvider;
import app.core.services.interfaces.ProductService;
import app.core.services.interfaces.UserService;
import app.data.modeles.ProductInStore;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class ProductCatalogController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/catalog/main")
    public Response getCatalog(@RequestBody CatalogRequest request){
        Response r = new Response();
        if (request.getToken() != null  && jwtProvider.validateToken(request.getToken())){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(request.getToken()));
            request.setUserId(u.getId());
            var c = getUserOrientedCatalog(request);
            validateStocks(c);
            r.addParameter("catalog", c);
            r.addParameter("tokenValid", true);
        }
        else {
            var c = getGuestCatalog(request);
            validateStocks(c);
            r.addParameter("catalog", c);
            r.addParameter("tokenValid", false);
        }

        r.setCode(ResponseCode.OK);
        return r;

    }

    @PostMapping("/api/catalog/product/info")
    public Response getProductData(@RequestBody ProductDataRequest request){
        Response r = new Response();
        r.addParameter("product",
                productService.getProductData(request));
        r.setCode(ResponseCode.OK);
        return r;

    }
    @PostMapping("/api/catalog/products/info")
    public Response getProductsData(@RequestBody MultipleProductRequest request){
        Response r = new Response();
        r.addParameter("products",
                productService.getProductsData(request));
        r.setCode(ResponseCode.OK);
        return r;

    }


    private List<ProductInStore> getUserOrientedCatalog(CatalogRequest request){
        return productService.getUserOrientedCatalog(request);
    }
    private List<ProductInStore> getGuestCatalog(CatalogRequest request){
        return productService.getGuestCatalog(request);
    }

    private void validateStocks(List<ProductInStore> lst){
        Date now = new Date();
        for (var p :
                lst) {
            if (p.getStock() != null && (p.getStock().getStartDate().after(now) || p.getStock().getEndDate().before(now)))
                p.setStock(null);
        }
    }

}

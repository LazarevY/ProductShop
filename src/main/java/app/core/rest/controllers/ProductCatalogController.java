package app.core.rest.controllers;


import app.core.requests.CatalogRequest;
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
            r.addParameter("catalog", getUserOrientedCatalog(request));
            r.addParameter("tokenValid", true);
        }
        else {
            r.addParameter("catalog", getGuestCatalog(request));
            r.addParameter("tokenValid", false);
        }

        r.setCode(ResponseCode.OK);
        return r;

    }

    private List<ProductInStore> getUserOrientedCatalog(CatalogRequest request){
        return productService.getUserOrientedCatalog(request);
    }
    private List<ProductInStore> getGuestCatalog(CatalogRequest request){
        return productService.getGuestCatalog(request);
    }

}

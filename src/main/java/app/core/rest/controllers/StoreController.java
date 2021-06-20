package app.core.rest.controllers;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.services.interfaces.StoreService;
import app.data.modeles.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/api/store/get")
    public Response getAllStores(){
        List<Store> allStores = storeService.getAllStores();
        return new Response(ResponseCode.OK,"", Map.of("stores", allStores));
    }

}

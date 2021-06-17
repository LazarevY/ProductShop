package app.core.rest.controllers;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.rest.front.models.*;
import app.core.security.JwtProvider;
import app.core.services.impl.UserServiceImpl;
import app.core.services.interfaces.UserOrdersService;
import app.core.services.interfaces.UserService;
import app.data.mappers.GenderMapper;
import app.data.modeles.Gender;
import app.data.modeles.User;
import app.data.modeles.UserAddress;
import app.data.modeles.UserCalorieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserOrdersService userOrdersService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private GenderMapper genderMapper;


    @PostMapping("/api/user/orders")
    public Response getUserOrders(@RequestBody String token){
        if (token!= null  && jwtProvider.validateToken(token)){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(token));
            return userOrdersService.getOrders(u.getId());
        }

        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }

    @PostMapping("/api/user/data")
    public Response getUserData(@RequestBody String token){
        if (token!= null  && jwtProvider.validateToken(token)){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(token));
            return new Response(ResponseCode.OK, "", Map.of("data", u));
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }

    @PostMapping("/api/user/data/update")
    public Response getUserData(@RequestBody UpdateUserData req){
        if (req.getToken()!= null  && jwtProvider.validateToken(req.getToken())){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(req.getToken()));
            req.setId(u.getId());
            userService.updateUserData(req);
            return new Response(ResponseCode.OK, "");
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }

    @PostMapping("/api/user/address/get")
    public Response getUserAddressList(@RequestBody UserAddressRequest req){
        if (req.getToken()!= null  && jwtProvider.validateToken(req.getToken())){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(req.getToken()));
            req.setId(u.getId());
            List<UserAddress> userAddressList = userService.getUserAddressList(req);
            return new Response(ResponseCode.OK, "", Map.of("addresses", userAddressList));
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }

    @PostMapping("/api/user/address/update")
    public Response updateUserAddress(@RequestBody UserAddAddressRequest req){
        if (req.getToken()!= null  && jwtProvider.validateToken(req.getToken())){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(req.getToken()));
            req.setUserId(u.getId());
            userService.updateUserAddress(req);
            return new Response(ResponseCode.OK, "");
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }

    @PostMapping("/api/user/address/add")
    public Response addUserAddress(@RequestBody UserAddAddressRequest req){
        if (req.getToken()!= null  && jwtProvider.validateToken(req.getToken())){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(req.getToken()));
            req.setUserId(u.getId());
            userService.addUserAddress(req);
            return new Response(ResponseCode.OK, "");
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }

    @PostMapping("/api/user/address/delete")
    public Response deleteUserAddress(@RequestBody DeleteUserAddress req){
        if (req.getToken()!= null  && jwtProvider.validateToken(req.getToken())){
            userService.deleteUserAddress(req);
            return new Response(ResponseCode.OK, "");
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }

    @GetMapping("/api/data/genders")
    public Response getGendersList(){
        List<Gender> all = genderMapper.getAll();
        return new Response(ResponseCode.OK, "", Map.of("genders", all));
    }

    @PostMapping("/api/user/calories-data/update")
    public Response updateCaloriesData(@RequestBody UserAddCalorieDataRequest request){
        if (request.getToken() != null  && jwtProvider.validateToken(request.getToken())){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(request.getToken()));
            request.setUserId(u.getId());
            userService.updateUserCalorieData(request);
            return new Response(ResponseCode.OK, "");
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }

    @PostMapping("/api/user/calories-data/get")
    public Response getCaloriesData(@RequestBody String token){
        if (token!= null  && jwtProvider.validateToken(token)){
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(token));
            UserCalorieData calorieData = userService.getCalorieData(u.getId());
            if (calorieData == null)
                return new Response(ResponseCode.WARN, "", Map.of("calorie", new UserCalorieData()));
            return new Response(ResponseCode.OK, "", Map.of("calorie", calorieData));
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }


}

package app.core.rest.controllers;

import app.core.front.models.LoginUser;
import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.security.dto.ApiResponseMessage;
import app.core.security.util.SecurityCipher;
import app.core.services.interfaces.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AuthController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private AuthorizationService userService;
//    @Autowired
//    private FindByIndexNameSessionRepository sessionRepository;
//
//
//    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> login(
//            @CookieValue(name = "accessToken", required = false) String accessToken,
//            @CookieValue(name = "refreshToken", required = false) String refreshToken,
//            @RequestBody LoginUser loginRequest
//    ) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        if(isAlreadyLoggedIn(loginRequest.getEmail())){
//            Response loginResponse = new Response(ResponseCode.ERROR, "User already loggined");
//            return ResponseEntity.ok(loginResponse);
//        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
//        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
//        return userService.login(loginRequest, decryptedAccessToken, decryptedRefreshToken);
//    }
//
//    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> refreshToken(@CookieValue(name = "accessToken", required = false) String accessToken,
//                                                      @CookieValue(name = "refreshToken", required = false) String refreshToken) {
//        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
//        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
//        return userService.refresh(decryptedAccessToken, decryptedRefreshToken);
//    }
//    @GetMapping("/logout")
//    public ResponseEntity<Response> logOut(HttpServletRequest request, HttpServletResponse response){
//        Response r = new Response(ResponseCode.OK, "OK");
//        r.addParameter("APIResponse", new ApiResponseMessage(true, userService.logout(request, response)));
//        return ResponseEntity.ok(r);
//
//    }
//    private Boolean isAlreadyLoggedIn(String pricipalName) {
//
//        Map user = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,pricipalName);
//        return user.size()>0;
//    }
}

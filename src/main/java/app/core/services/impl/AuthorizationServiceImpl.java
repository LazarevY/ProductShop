package app.core.services.impl;

import app.core.front.models.LoginUser;
import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.security.JwtTokenProvider;
import app.core.security.dto.Token;
import app.core.security.util.CookieUtil;
import app.core.services.interfaces.AuthorizationService;
import app.data.mappers.UserMapper;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CookieUtil cookieUtil;

    @Override
    public ResponseEntity<Response> login(LoginUser loginRequest, String accessToken, String refreshToken) {
        String email = loginRequest.getEmail();
        User user = userMapper.getByEmail(email);
        if (user == null)
            throw new IllegalArgumentException(String.format("Not funded be email %s", email));

        boolean accessTokenValid = tokenProvider.validateToken(accessToken);
        boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);

        HttpHeaders responseHeaders = new HttpHeaders();
        Token newAccessToken;
        Token newRefreshToken;
        if (!accessTokenValid && !refreshTokenValid) {
            newAccessToken = tokenProvider.generateToken(user);
            newRefreshToken = tokenProvider.generateRefreshToken(user);
            addAccessTokenCookie(responseHeaders, newAccessToken);
            addRefreshTokenCookie(responseHeaders, newRefreshToken);
        }

        if (!accessTokenValid && refreshTokenValid) {
            newAccessToken = tokenProvider.generateToken(user);
            addAccessTokenCookie(responseHeaders, newAccessToken);
        }

        if (accessTokenValid && refreshTokenValid) {
            newAccessToken = tokenProvider.generateToken(user);
            newRefreshToken = tokenProvider.generateRefreshToken(user);
            addAccessTokenCookie(responseHeaders, newAccessToken);
            addRefreshTokenCookie(responseHeaders, newRefreshToken);
        }

        Response response = new Response(ResponseCode.OK, "Auth successful");
        return ResponseEntity.ok().headers(responseHeaders).body(response);

    }

    @Override
    public ResponseEntity<Response> refresh(String accessToken, String refreshToken) {
        boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);
        if (!refreshTokenValid) {
            Response loginResponse = new Response(ResponseCode.ERROR, "Invalid refresh token !");
            return ResponseEntity.ok().body(loginResponse);
        }

        String email = tokenProvider.getUsername(refreshToken);
        User user = userMapper.getByEmail(email);
        if (user == null)
            throw new IllegalArgumentException(String.format("Not funded be email %s", email));

        Token newAccessToken = tokenProvider.generateToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration()).toString());

        Response loginResponse = new Response(ResponseCode.OK, "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
    }

    @Override
    public User getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userMapper.getByEmail(authentication.getName());
        if (user == null)
            throw new IllegalArgumentException(String.format("Not funded be email %s", authentication.getName()));
        return user;
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        if (request.getCookies() != null) {
            System.out.println("i found some cookies");
            for (Cookie cookie : request.getCookies()) {
                cookie.setMaxAge(0);
                cookie.setValue("");
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        return "logout successfully";
    }

    private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    private void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }
}

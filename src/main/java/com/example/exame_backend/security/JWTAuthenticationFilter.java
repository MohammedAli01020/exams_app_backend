package com.example.exame_backend.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.exame_backend.model.persisitece.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDetails userCredentials = new ObjectMapper().readValue(request.getInputStream(), UserDetails.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword()
                            ));

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
//        Date expirationDate = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);


        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        c.add(Calendar.YEAR, SecurityConstants.EXPIRATION_TIME);

        // Convert calendar back to Date
        Date expirationDate = c.getTime();

        String jwt = JWT.create()
                .withSubject(getSubject(authResult))
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));



        Map<String, Object> tokens = new HashMap<>();

        tokens.put("token", SecurityConstants.TOKEN_PREFIX + jwt);
        tokens.put("role",((CustomUserDetails) authResult.getPrincipal()).getRole());
        tokens.put("username", getSubject(authResult));
        tokens.put("userId",((CustomUserDetails) authResult.getPrincipal()).getUserId());




        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwt);

        response.setContentType(APPLICATION_JSON_VALUE);


        new ObjectMapper().writeValue(response.getOutputStream(), tokens );
    }

    private static String getSubject(Authentication authResult) {
        CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();

        return user.getUsername();

    }
}
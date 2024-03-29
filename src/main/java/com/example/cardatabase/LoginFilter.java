package com.example.cardatabase;

import com.example.cardatabase.domain.AccountCredentials;
import com.example.cardatabase.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {


    public LoginFilter(String url, AuthenticationManager auth) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(auth);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        AccountCredentials credentials = new ObjectMapper()
                .readValue(request.getInputStream(), AccountCredentials.class);
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(credentials
                        .getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthenticationService.addToken(response, authResult.getName());
    }

}

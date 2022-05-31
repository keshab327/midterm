package com.example.demo.configuration;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.enittiy.CustomUserDetail;
 
@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
    	CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
         
        if (userDetails.hasRole("ROLE_ADMIN")) {
            redirectURL = "/admin";
        } else if (userDetails.hasRole("ROLE_USER")) {
            redirectURL = "/";
        } else if (userDetails.hasRole("ROLE_OWNER")) {
            redirectURL = "/image/show";
        }
         
        response.sendRedirect(redirectURL);
         
    }
 
}

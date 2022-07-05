package com.example.demo.configuration;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.example.demo.enittiy.CustomUserDetail;
 
@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
    	CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
    	userDetails = (CustomUserDetail) authentication.getPrincipal();
		String shopowner_email= userDetails.getUsername();
		
		Model model = null;
         
        String redirectURL = request.getContextPath();
        
        
        if (userDetails.hasRole("ROLE_ADMIN")) {
            redirectURL = "/admin";
        } else if (userDetails.hasRole("ROLE_USER")) {
            redirectURL = "/";
        } else if (userDetails.hasRole("ROLE_OWNER")) {
        	//model.addAttribute("email",shopowner_email);
        	request.getSession().setAttribute("email",shopowner_email);
        	System.out.println("session in login"+shopowner_email);
        	redirectURL = "/shopwoner_home";
            
        }
         
        response.sendRedirect(redirectURL);
         
    }
 
}

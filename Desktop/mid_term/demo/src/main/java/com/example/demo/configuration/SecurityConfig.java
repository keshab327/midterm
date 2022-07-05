package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.services.CustomUserDetailService;

//import com.example.demo.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	@Autowired 
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	CustomLoginSuccessHandler customsuccesshandler;
	
	protected void configure(HttpSecurity http) throws Exception{
	http
	
	   .authorizeRequests()
	   .antMatchers("/","/customer_order/**","/shopowner_home_return/**","/shopwoner_seeorder/**","/vieworderdetails/**","/display/**","/buy/**","/shopwoner_home/**","/shop/**","/register/**","/shopRegister2/**","/image/saveImageDetails/**","/image/display/**","/image/imageDetails/**","/image/show/**","/admin/**","/image/display_tax/**","/confirm_add_user/**","/reject_shop_request/**","/delete_shop_request/**","/admin/products/**").permitAll()
	  .antMatchers("/admin/**").hasRole("ADMIN")//no need for ROLE_ADMIN this is equal to ADMIN
	   	.anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .failureUrl("/login?error=true")
        .successHandler( customsuccesshandler)
        .usernameParameter("email")
        .passwordParameter("password")
        .and()
        .oauth2Login()
        .loginPage("/login")
        .successHandler(googleOAuth2SuccessHandler)
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .and()
        .exceptionHandling()
        .and()
        .csrf()
        .disable();
	http.headers().frameOptions().disable();
	
}
@Bean
public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
}
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	auth.userDetailsService(customUserDetailService);
}
public void configure(WebSecurity web) throws Exception{
	web.ignoring().antMatchers("/resources/**","/static/**","/images/**","/productimage/**","/css/**","/js/**");
}
}
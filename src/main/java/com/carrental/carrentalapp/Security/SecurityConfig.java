package com.carrental.carrentalapp.Security;

import com.carrental.carrentalapp.Model.AppUser;
import com.carrental.carrentalapp.Model.Role;
import com.carrental.carrentalapp.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Spring Security configurations
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserService userService;
    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
//      Add your own localhost addresses
//      The 4200 is for Angular and the 3000 is the Proxy server i use to not get CORS by the Google Api when testing
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200","http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("PUT","POST","DELETE", "OPTION","GET"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler(){
        return ((request, response, authentication) -> {
            if(authentication != null){
                AppUser appUser = (AppUser) authentication.getPrincipal();
                Map<String, String> userInfomap = new HashMap<>();
                userInfomap.put("username", appUser.getUsername());
                userInfomap.put("id", appUser.getId().toString());
                userInfomap.put( "role",appUser.getRole().stream().map(Role::getRole).toList().get(0));
                response.getWriter().write(new ObjectMapper().writeValueAsString(userInfomap));
                response.setStatus(200);
            }
        });
    }
    @Bean
    LogoutSuccessHandler  logoutSuccessHandler (){
        return ((request, response, authentication) ->{
            Map<String,Boolean> logOutMap = new HashMap<>();
            logOutMap.put("logged_out",true);
            response.getWriter().write(new ObjectMapper().writeValueAsString(logOutMap));
            response.setStatus(200);
        } );
    }
//    Configure the endpoints as per your needs
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        HttpSecurity http = httpSecurity.cors((cors) -> cors.configurationSource(corsConfigurationSource()));
//      Do not disable csrf in a real app
        http.csrf((csrf) -> csrf.disable());
//      These are the endpoints open to everyone using the app
        http.authorizeHttpRequests((authz) -> authz.requestMatchers("/carrental/v1/login",
                        "/carrental/v1/logout","/carrental/v1/register","/carrental/v1/data/**","/car/**","api/**","/api/confirm-payment1")
                .permitAll()
//               Handel request by user with the Role ADMIN
                .requestMatchers("/carrental/v1/admin/**")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated());
        http.userDetailsService(userService);
        http.formLogin((form) -> form.loginPage("/carrental/v1/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler()));
        http.logout((logout)->logout.logoutUrl("/carrental/v1/logout")
                .logoutSuccessHandler(logoutSuccessHandler()));
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}

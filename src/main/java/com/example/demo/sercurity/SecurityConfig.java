package com.example.demo.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Có thể chỉ định rõ: https://techmaster.vn
        config.addAllowedHeader("*"); // Có thể chỉ định rõ: Arrays.asList("authorization", "content-type", "x-auth-token")
        config.addAllowedMethod("*"); // Có thể chỉ định rõ: Arrays.asList("GET", "POST", "PUT", "DELETE")
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()

                //product
                .antMatchers(HttpMethod.GET,"/product").permitAll()
                .antMatchers(HttpMethod.POST,"/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/product/mobile").permitAll()
                .antMatchers(HttpMethod.DELETE,"/product/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/product/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/product/{id}").hasRole("ADMIN")
                //user
                .antMatchers(HttpMethod.POST,"/user").permitAll()
                .antMatchers(HttpMethod.POST,"/user/login").permitAll()
                .antMatchers(HttpMethod.GET,"/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/user/get-info-user").hasAnyRole("ADMIN","CUSTOMER","STORE")
                .antMatchers(HttpMethod.PUT,"/user/{id}").hasAnyRole("ADMIN","CUSTOMER")
                .antMatchers(HttpMethod.GET,"/user/get-user-register").hasRole("ADMIN")
        //
                //store
                .antMatchers(HttpMethod.GET,"/store").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/store").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/store/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/store/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/store/{id}").hasRole("ADMIN")
                //store-have-product
                .antMatchers("/storehave").hasRole("STORE")

                //cartitem
                .antMatchers("/cartitem").hasRole("CUSTOMER")
                .antMatchers("/cartitem/test").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .addFilter(new ApiJWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Vô hiệu hóa toàn bộ bảo mật đối với các request vào các đường dẫn sau
        // Không phải trải qua filter
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }
}


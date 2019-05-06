package com.company.context.applications.tomcat;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {

  private String corsAllowOrigin = "localhost:9100";

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.headers().frameOptions().disable();

    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("**")
        .permitAll();
  }

  // Enable H2 Console for local development.
  //  @Profile("default")
  @Bean
  ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    registrationBean.addInitParameter("webAllowOthers", "true");
    return registrationBean;
  }

//  @Bean
//  CorsConfigurationSource corsConfigurationSource() {
//    CorsConfiguration configuration = new CorsConfiguration();
//    if (!StringUtils.isEmpty(corsAllowOrigin)) {
//      String[] allowOriginsArray = corsAllowOrigin.split(",");
//      if (allowOriginsArray != null) {
//        List<String> allowOrigins = Arrays.asList(allowOriginsArray);
//        configuration.setAllowedOrigins(allowOrigins);
//      }
//    }
//    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//    configuration.setAllowedHeaders(Arrays.asList("*"));
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", configuration);
//    return source;
//  }
}

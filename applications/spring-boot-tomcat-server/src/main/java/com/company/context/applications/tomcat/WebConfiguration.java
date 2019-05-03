package com.company.context.applications.tomcat;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
        .and()
        .authorizeRequests()
        .antMatchers("/context/v1/order/**")
        .permitAll()
        .antMatchers("/console/**")
        .permitAll()
        .antMatchers("/actuator/**")
        .permitAll()
        .anyRequest()
        .authenticated();
  }

  // Enable H2 Console for local development.
  @Bean
  @Profile("default")
  ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    registrationBean.addInitParameter("webAllowOthers", "true");
    return registrationBean;
  }
}

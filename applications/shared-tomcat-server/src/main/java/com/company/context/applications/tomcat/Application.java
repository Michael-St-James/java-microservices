package com.company.context.applications.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Sample spring-boot application.
 */
@EntityScan({"com.company.context.orderservice",
    "com.company.context.paymentservice"})
@EnableJpaRepositories({"com.company.context.orderservice",
    "com.company.context.paymentservice"})
@SpringBootApplication(
    scanBasePackages = {
        "com.company.context.applications.tomcat",
        "com.company.context.orderservice",
        "com.company.context.paymentservice"
    }
)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

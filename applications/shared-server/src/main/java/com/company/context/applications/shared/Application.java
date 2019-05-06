package com.company.context.applications.shared;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Sample spring-boot application.
 */
@EntityScan("com.company.context.orderservice")
@EnableJpaRepositories("com.company.context.orderservice")
@SpringBootApplication(
    scanBasePackages = {
        "com.company.context.applications.shared",
        "com.company.context.orderservice"
    }
)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

package com.company.context.orderservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Order-service isolated application container.
 */
@EntityScan("com.company.context.orderservice")
@EnableJpaRepositories("com.company.context.orderservice")
@SpringBootApplication(
    scanBasePackages = {
        "com.company.context.orderservice"
    }
)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

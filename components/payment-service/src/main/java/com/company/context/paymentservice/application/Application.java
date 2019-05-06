package com.company.context.paymentservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Order-service isolated application container.
 */
@EntityScan("com.company.context.paymentservice")
@EnableJpaRepositories("com.company.context.paymentservice")
@SpringBootApplication(
    scanBasePackages = {
        "com.company.context.paymentservice"
    }
)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

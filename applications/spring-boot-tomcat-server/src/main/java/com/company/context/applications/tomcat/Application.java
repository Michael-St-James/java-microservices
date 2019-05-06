package com.company.context.applications.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Sample spring-boot application.
 */
@EntityScan("com.company.context")
@EnableJpaRepositories("com.company.context")
@SpringBootApplication(
    scanBasePackages = {
        "com.company.context.applications.tomcat",
        "com.company.context"
    }
)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

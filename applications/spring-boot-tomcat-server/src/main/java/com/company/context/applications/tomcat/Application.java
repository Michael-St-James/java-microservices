package com.company.context.applications.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sample spring-boot application.
 */
@SpringBootApplication(
    scanBasePackages = {
        "com.company.context"
    }
)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

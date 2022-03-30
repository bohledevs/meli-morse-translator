package com.example.morsetranslator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableConfigurationProperties
@EnableCaching
@EnableRetry
public class MorseTranslatorApplication {

  public static void main(String[] args) {
    SpringApplication.run(MorseTranslatorApplication.class, args);
  }
}

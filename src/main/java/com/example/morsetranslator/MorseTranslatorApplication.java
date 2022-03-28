package com.example.morsetranslator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MorseTranslatorApplication {

  public static void main(String[] args) {
    SpringApplication.run(MorseTranslatorApplication.class, args);
  }
}

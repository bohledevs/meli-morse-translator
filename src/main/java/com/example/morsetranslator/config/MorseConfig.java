package com.example.morsetranslator.config;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** This class loads the morse alphabet from the application properties */
@Data
@Configuration
@ConfigurationProperties(prefix = "morse")
public class MorseConfig {
  private Map<String, String> alphabet;
}

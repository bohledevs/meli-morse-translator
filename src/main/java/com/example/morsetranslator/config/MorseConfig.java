package com.example.morsetranslator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/** This class loads */
@Data
@Configuration("morseConfig")
public class MorseConfig {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private static final String MORSE_ALPHABET_FILEPATH = "src/main/resources/morseAlphabet.json";

  private Map<String, String> morseAlphabet;

  @PostConstruct
  private void initializeAlphabet() throws IOException {
    String jsonAlphabet = new String(Files.readAllBytes(Paths.get(MORSE_ALPHABET_FILEPATH)));
    this.morseAlphabet = objectMapper.readValue(jsonAlphabet, HashMap.class);
  }
}

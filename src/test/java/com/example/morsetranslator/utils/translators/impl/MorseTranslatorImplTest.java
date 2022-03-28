package com.example.morsetranslator.utils.translators.impl;

import static com.example.morsetranslator.utils.TestConstants.randomHumanString;
import static com.example.morsetranslator.utils.TestConstants.randomMorseString;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.example.morsetranslator.config.MorseConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MorseTranslatorImplTest {

  private static HashMap<String, String> alphabet;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private MorseConfig morseConfig;

  @InjectMocks private MorseTranslatorImpl morseTranslator;

  @BeforeAll
  static void getAlphabet() throws IOException {
    alphabet =
        new ObjectMapper()
            .readValue(new File("src/test/resources/morseAlphabet.json"), HashMap.class);
  }

  @Test
  void shouldTranslateFromHumanToMorse() {
    when(morseConfig.getAlphabet().entrySet()).thenReturn(alphabet.entrySet());
    String response = this.morseTranslator.humanToMorse(randomHumanString);
    assertTrue("Should not be blank.", StringUtils.isNotBlank(response));
    assertEquals("Should be translated to Morse.", randomMorseString, response);
  }

  @Test
  void shouldTranslateFromMorseToHuman() {
    when(morseConfig.getAlphabet().entrySet()).thenReturn(alphabet.entrySet());
    String response = this.morseTranslator.morseToHuman(randomMorseString);
    assertTrue("Should not be blank.", StringUtils.isNotBlank(response));
    assertEquals("Should be translated to Human.", randomHumanString, response);
  }
}

package com.example.morsetranslator.service.impl;

import static com.example.morsetranslator.utils.TestConstants.randomBitsString;
import static com.example.morsetranslator.utils.TestConstants.randomHumanString;
import static com.example.morsetranslator.utils.TestConstants.randomMorseString;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

import com.example.morsetranslator.domain.dto.MorseDto;
import com.example.morsetranslator.utils.translators.BitsDecoder;
import com.example.morsetranslator.utils.translators.MorseTranslator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MorseServiceImplTest {
  @InjectMocks private MorseServiceImpl morseService;
  @Mock private MorseTranslator morseTranslator;
  @Mock private BitsDecoder bitsDecoder;

  @Test
  void shouldDecodeBitsToMorse() {
    when(this.bitsDecoder.decodeBitString(randomBitsString)).thenReturn(randomMorseString);
    MorseDto response = this.morseService.decodeBits2Morse(randomBitsString);
    assertNotNull("Response should not be null.", response);
    assertEquals("Response should be morse translation", randomMorseString, response.getText());
  }

  @Test
  void shouldDecodeFromMorseToHuman() {
    when(this.morseTranslator.morseToHuman(randomMorseString)).thenReturn(randomHumanString);
    MorseDto response = this.morseService.translate2Human(randomMorseString);
    assertNotNull("Response should not be null.", response);
    assertEquals("Response should be human translation", randomHumanString, response.getText());
  }

  @Test
  void shouldDecodeFromHumanToMorse() {
    when(this.morseTranslator.humanToMorse(randomHumanString)).thenReturn(randomMorseString);
    MorseDto response = this.morseService.translate2Morse(randomHumanString);
    assertNotNull("Response should not be null.", response);
    assertEquals("Response should be morse translation", randomMorseString, response.getText());
  }
}

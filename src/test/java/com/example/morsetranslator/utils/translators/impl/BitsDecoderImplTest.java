package com.example.morsetranslator.utils.translators.impl;

import static com.example.morsetranslator.utils.TestConstants.randomBitsString;
import static com.example.morsetranslator.utils.TestConstants.randomMorseString;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.example.morsetranslator.domain.PulseTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BitsDecoderImplTest {

  private BitsDecoderImpl bitsDecoder;

  @BeforeEach
  void setUp() {
    this.bitsDecoder = new BitsDecoderImpl();
  }

  @Test
  void shouldDecodeBitsString() {
    String decodedMorseString = this.bitsDecoder.decodeBitString(randomBitsString);
    assertNotBlank(decodedMorseString);
    assertEquals("Should be decoded.", randomMorseString, decodedMorseString);
  }

  @Test
  void shouldDecodeSinglePulseBitsString() {
    String decodeMorseString = this.bitsDecoder.decodeBitString("1");
    assertNotBlank(decodeMorseString);
    assertEquals("Should be a single dot", PulseTypes.DOT.getValue(), decodeMorseString);
  }

  private void assertNotBlank(String decodedString) {
    assertTrue("Should not be blank.", StringUtils.isNotBlank(decodedString));
  }
}

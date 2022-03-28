package com.example.morsetranslator.facade;

import static com.example.morsetranslator.utils.TestConstants.randomBitsString;
import static com.example.morsetranslator.utils.TestConstants.randomHumanString;
import static com.example.morsetranslator.utils.TestConstants.randomMorseString;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

import com.example.morsetranslator.domain.dto.MorseDto;
import com.example.morsetranslator.service.MorseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MorseControllerTest {

  @Mock private MorseDto morseDto;
  @Mock private MorseService morseService;
  @InjectMocks private MorseController morseController;

  @Test
  void shouldParseBitsToMorse() {
    when(this.morseService.decodeBits2Morse(randomBitsString)).thenReturn(this.morseDto);
    facadeAssertion(
        this.morseController.bitsToMorse(MorseDto.builder().text(randomBitsString).build()));
  }

  @Test
  void shouldParseMorseToHuman() {
    when(this.morseService.translate2Human(randomMorseString)).thenReturn(this.morseDto);
    facadeAssertion(
        this.morseController.morseToHuman(MorseDto.builder().text(randomMorseString).build()));
  }

  @Test
  void shouldParseHumanToMorse() {
    when(this.morseService.translate2Morse(randomHumanString)).thenReturn(this.morseDto);
    facadeAssertion(
        this.morseController.humanToMorse(MorseDto.builder().text(randomHumanString).build()));
  }

  private void facadeAssertion(ResponseEntity<MorseDto> response) {
    assertNotNull("Response can't be null.", response);
    assertEquals("Should be 200 OK.", HttpStatus.OK, response.getStatusCode());
    assertEquals("Should return correct body.", this.morseDto, response.getBody());
  }
}

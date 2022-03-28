package com.example.morsetranslator.facade;

import com.example.morsetranslator.domain.dto.MorseDto;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Application Programming Interface for Morse translation services.
 *
 * @author bohledevs
 */
@RequestMapping("/morse")
public interface MorseApi {

  /**
   * Given a bit char sequence, performs a MORSE translation.
   *
   * @param morseDto {@link MorseDto} text in bits.
   * @return {@link MorseDto} text in morse code.
   */
  @PostMapping("/bits2morse")
  ResponseEntity<MorseDto> bitsToMorse(@RequestBody @Valid @NotNull MorseDto morseDto);

  /**
   * Given a morse char sequence, performs a natural language translation.
   *
   * @param morseDto {@link MorseDto} text in morse code.
   * @return {@link MorseDto} text in natural language.
   */
  @PostMapping("/2text")
  ResponseEntity<MorseDto> morseToHuman(@RequestBody @Valid @NotNull MorseDto morseDto);

  /**
   * Given a human char sequence, performs a morse translation.
   *
   * @param morseDto {@link MorseDto} text in natural language.
   * @return {@link MorseDto} text in morse.
   */
  @PostMapping("/2morse")
  ResponseEntity<MorseDto> humanToMorse(@RequestBody @Valid @NotNull MorseDto morseDto);
}

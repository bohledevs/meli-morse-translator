package com.example.morsetranslator.facade;

import com.example.morsetranslator.domain.dto.MorseDto;
import com.example.morsetranslator.service.MorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "morseController")
@RequiredArgsConstructor
public class MorseController implements MorseApi {

  private final MorseService morseService;

  @Override
  public ResponseEntity<MorseDto> bitsToMorse(MorseDto morseDto) {
    return ResponseEntity.ok(morseService.decodeBits2Morse(morseDto.getText()));
  }

  @Override
  public ResponseEntity<MorseDto> morseToHuman(MorseDto morseDto) {
    return ResponseEntity.ok(morseService.translate2Human(morseDto.getText()));
  }

  @Override
  public ResponseEntity<MorseDto> humanToMorse(MorseDto morseDto) {
    return ResponseEntity.ok(this.morseService.translate2Morse(morseDto.getText()));
  }
}

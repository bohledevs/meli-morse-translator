package com.example.morsetranslator.service.impl;

import com.example.morsetranslator.domain.dto.MorseDto;
import com.example.morsetranslator.service.MorseService;
import com.example.morsetranslator.utils.translators.BitsDecoder;
import com.example.morsetranslator.utils.translators.MorseTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "morseService")
@RequiredArgsConstructor
public class MorseServiceImpl implements MorseService {

  private final MorseTranslator morseTranslator;

  private final BitsDecoder bitsDecoder;

  @Override
  public MorseDto decodeBits2Morse(String bits) {
    return new MorseDto(bitsDecoder.decodeBitString(bits));
  }

  @Override
  public MorseDto translate2Human(String morse) {
    return new MorseDto(morseTranslator.morseToHuman(morse));
  }

  @Override
  public MorseDto translate2Morse(String human) {
    return new MorseDto(morseTranslator.humanToMorse(human));
  }

}

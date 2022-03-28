package com.example.morsetranslator.service;

import com.example.morsetranslator.domain.dto.MorseDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

/**
 * Service Layer to orchestrate the Morse/Bits/Human translators.
 */
@Validated
public interface MorseService {
    MorseDto decodeBits2Morse(@Pattern(regexp = "[0-1]+" , message = "Only binaries") String bits);

    MorseDto translate2Human(@Pattern(regexp = "[\\.\\-\\s]+" , message = "Only dots, dashes and whitespaces") String morse);

    MorseDto translate2Morse(@Pattern(regexp = "[A-Z0-9\\s]+" , message = "Only alphanumerics and whitespaces") String human);
}

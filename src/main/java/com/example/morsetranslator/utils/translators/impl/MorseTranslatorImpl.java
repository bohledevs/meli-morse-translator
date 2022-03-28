package com.example.morsetranslator.utils.translators.impl;

import com.example.morsetranslator.config.MorseConfig;
import com.example.morsetranslator.utils.translators.MorseTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.example.morsetranslator.utils.Constants.EMPTY_STRING;
import static com.example.morsetranslator.utils.Constants.WHITESPACE;

@Service("morseTranslator")
@RequiredArgsConstructor
public class MorseTranslatorImpl implements MorseTranslator  {

    private final MorseConfig morseConfig;

    @Override
    public String morseToHuman(String morse) {
        return Arrays.stream(morse.split("[ ]{2,}")) // split by words (2 whitespaces)
                .map(word ->
                      Arrays.stream(word.split("[ ]")) // split by letters (1 whitespace)
                            .map(letter ->
                                      this.morseConfig.getAlphabet().entrySet()
                                                .stream().filter(entry -> letter.equals(entry.getValue()))
                                                .findFirst()
                                                .orElse(new AbstractMap.SimpleEntry<String, String>(EMPTY_STRING,EMPTY_STRING))
                                                .getKey()
                            ).collect(Collectors.joining())
               ).collect(Collectors.joining(WHITESPACE));
    }

    @Override
    public String humanToMorse(String natural) {
        return Arrays.stream(natural.split("[ ]{1,}")) // split by words (1+ whitespaces)
                .map(word ->
                        Arrays.stream(word.split(EMPTY_STRING)) // split by letters (empty string)
                                .map(letter ->
                                        this.morseConfig.getAlphabet().entrySet()
                                                .stream().filter(entry -> letter.equals(entry.getKey()))
                                                .findFirst()
                                                .orElse(new AbstractMap.SimpleEntry<String, String>(EMPTY_STRING,EMPTY_STRING))
                                                .getValue()
                                ).collect(Collectors.joining(WHITESPACE))
                ).collect(Collectors.joining("  "));
    }
}

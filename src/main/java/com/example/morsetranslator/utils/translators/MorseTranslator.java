package com.example.morsetranslator.utils.translators;

/** Utility to perform two-way Morse/Human translations. */
public interface MorseTranslator {

  /**
   * Translates a morse string to a natural language string.
   *
   * @param morse {@link String} morse string.
   * @return {@link String} natural language string.
   */
  String morseToHuman(String morse);

  /**
   * Translates a natural language string to a morse string.
   *
   * @param natural {@link String} natural language string.
   * @return {@link String} morse string.
   */
  String humanToMorse(String natural);
}

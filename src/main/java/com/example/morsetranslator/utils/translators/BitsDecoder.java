package com.example.morsetranslator.utils.translators;

/** Utility layer to perform bits string to Morse decoding. */
public interface BitsDecoder {

  /**
   * Decodes a bits string representing human pulses, and translates to Morse code.
   *
   * @param bits {@link String} bits string
   * @return {@link String} morse equivalence
   */
  String decodeBitString(String bits);
}

package com.example.morsetranslator.domain;

import lombok.Getter;

/** Enum representing the options that each human inserted pulse or pause can represent in Morse. */
@Getter
public enum PulseTypes {
  DOT("."),
  DASH("-"),
  EMPTY(""),
  LETTER_SEPARATOR(" "),
  WORD_SEPARATOR("  ");

  private final String value;

  PulseTypes(String s) {
    this.value = s;
  }
}

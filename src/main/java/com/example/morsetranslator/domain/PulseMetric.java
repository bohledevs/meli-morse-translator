package com.example.morsetranslator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Metrics about human pulses that will help us sort out the Morse translation. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PulseMetric {
  private int maxDot; // Bits length of the max inserted dot
  private int maxDash; // Bits length of the max inserted dash
}

package com.example.morsetranslator.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/** DTO both for requests and responses. */
@Data
@Builder
@AllArgsConstructor
public class MorseDto {
  @NotNull(message = "text can't be null")
  @Size(min = 1, max = 1000) // shortest is one pulse / letter / char
  public String text;
}

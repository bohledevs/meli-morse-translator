package com.example.morsetranslator.utils.translators.impl;

import com.example.morsetranslator.domain.PulseMetric;
import com.example.morsetranslator.domain.PulseTypes;
import com.example.morsetranslator.utils.translators.BitsDecoder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component(value = "bitsDecoder")
public class BitsDecoderImpl implements BitsDecoder {

    @Override
    public String decodeBitString(String bits) {

        String sanitizedBits = sanitize(bits);
        return Pattern.compile("1+|0+").matcher(sanitizedBits).results().map(MatchResult::group)
                .map(pulse -> getPulseType(pulse, buildPulseMetric(sanitizedBits)))
                .map(PulseTypes::getValue)
                .reduce("", String::concat);
    }

    private PulseTypes getPulseType(String pulse, PulseMetric pulseMetric) {
        int length = pulse.length();

        // Case Pause
        if (pulse.contains("0")) {
            if (length <= pulseMetric.getMaxDot()) {
                return PulseTypes.EMPTY;
            } else if (length <= pulseMetric.getMaxDash()) {
                return PulseTypes.LETTER_SEPARATOR;
            }
            return PulseTypes.WORD_SEPARATOR;
        }

        // Case Human Pulse
        if (length <= pulseMetric.getMaxDot()) {
            return PulseTypes.DOT;
        }
        return PulseTypes.DASH;
    }

    /**
     * Analyzes minuses and maxes of pulses and pauses, and builds a string-specific metric.
     *
     * @param bits {@link String} bit input string.
     * @return {@link PulseMetric} bit string metrics.
     */
    private PulseMetric buildPulseMetric(String bits) {

        Supplier<Stream<String>> pulseSupplier = () ->
                Pattern.compile("[1]+").matcher(bits).results().map(MatchResult::group);

        int oneMin = getIntStream(pulseSupplier).min().orElseGet(() -> getIntStream(pulseSupplier).findAny().getAsInt());
        int oneMax = getIntStream(pulseSupplier).max().orElseGet(() -> getIntStream(pulseSupplier).findAny().getAsInt());

        return PulseMetric
                    .builder()
                    .maxDot(getIntStream(pulseSupplier)
                            .filter(pulse ->
                                    Math.abs(pulse - oneMin) < Math.abs(oneMax - pulse))
                            .max()
                            .orElse(oneMin))
                    .maxDash(getIntStream(pulseSupplier)
                            .filter(pulse -> Math.abs(pulse - oneMin) > Math.abs(oneMax - pulse))
                            .max()
                            .orElse(oneMax))
                    .build();
    }


    private IntStream getIntStream(Supplier<Stream<String>> supplier) {
        return supplier.get().mapToInt(String::length);
    }

    /**
     * Removes first and latter long pauses.
     * @param bits {@link String} full input.
     * @return {@link String} sanitized input (no long pauses)
     */
    private String sanitize(String bits) {
        return bits.substring(bits.indexOf("1"), bits.lastIndexOf("1")+1);
    }

}

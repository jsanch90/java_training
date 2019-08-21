package com.talos.javatraining.lesson7;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.BaseStream;
import java.util.Collection;

import org.apache.commons.lang3.mutable.MutableObject;


public class MainImpl implements Main {

    @Override
    public BigDecimal sum(Stream<String> stream) {
        return stream.parallel()
                .map(BigDecimal::new)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal sumIf(Stream<String> stream, Predicate<BigDecimal> predicate) {
        return stream.parallel()
                .map(BigDecimal::new)
                .filter(predicate)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @Override
    public Map<Long, BigDecimal> sumsByRange(Stream<String> stream) {

        return stream.parallel()
                .map(BigDecimal::new)
                .collect(Collectors.toMap(this::getRange, num -> num, BigDecimal::add));
    }

    private Long getRange(BigDecimal value) {
        return value.movePointLeft(3).longValue();
    }
}

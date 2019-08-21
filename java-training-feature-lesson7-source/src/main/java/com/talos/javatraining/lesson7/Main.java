package com.talos.javatraining.lesson7;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;


public interface Main
{

	BigDecimal sum(Stream<String> stream);

	BigDecimal sumIf(Stream<String> stream, Predicate<BigDecimal> predicate);

	Map<Long, BigDecimal> sumsByRange(Stream<String> stream);
}

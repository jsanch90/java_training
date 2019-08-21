package com.talos.javatraining.lesson7;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.lang3.mutable.MutableObject;


public class MainImpl implements Main
{
	@Override
	public BigDecimal sum(Stream<String> stream)
	{
		MutableObject<BigDecimal> mutableAccumulator = new MutableObject<>(BigDecimal.ZERO);

		stream.map(BigDecimal::new).forEach(v -> mutableAccumulator.setValue(mutableAccumulator.getValue().add(v)));

		return mutableAccumulator.getValue();
	}

	@Override
	public BigDecimal sumIf(Stream<String> stream, Predicate<BigDecimal> predicate)
	{
		MutableObject<BigDecimal> mutableAccumulator = new MutableObject<>(BigDecimal.ZERO);
		stream.map(BigDecimal::new).filter(predicate)
				.forEach(v -> mutableAccumulator.setValue(mutableAccumulator.getValue().add(v)));

		return mutableAccumulator.getValue();
	}

	@Override
	public Map<Long, BigDecimal> sumsByRange(Stream<String> stream)
	{
		Map<Long, BigDecimal> result = new HashMap<>();
		stream.map(BigDecimal::new).forEach(e -> result.compute(getRange(e), (k, v) -> {
			if (v == null)
			{
				v = BigDecimal.ZERO;
			}
			return v.add(e);
		}));
		return result;
	}

	private Long getRange(BigDecimal value)
	{
		return value.movePointLeft(3).longValue();
	}
}

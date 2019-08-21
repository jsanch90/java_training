package com.talos.javatraining.lesson7;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MainTest
{
	private static final BigDecimal FIFTY = BigDecimal.valueOf(50);
	private static final BigDecimal TEN_THOUSAND = BigDecimal.valueOf(10000);
	private Stream<String> stream;
	private Main main = new MainImpl();

	@Before
	public void setup()
	{
		Generator generator = new Generator();
		Reader reader = generator.generateIfNecessary();
		BufferedReader bufferedReader = new BufferedReader(reader);
		stream = bufferedReader.lines().onClose(asUncheckedRunnable(bufferedReader));
	}

	@Test(timeout = 5000)
	public void testMutableSum()
	{
		BigDecimal value = main.sum(stream);

		assertEquals(new BigDecimal("85714567228571.43"), value);
	}

	@Test(timeout = 5000)
	public void testMutableSumIfDecimalGreaterThan50()
	{
		BigDecimal value = main.sumIf(stream, this::isDecimalGreaterThan50);

		assertEquals(new BigDecimal("36734810865298.02"), value);
	}

	@Test(timeout = 5000)
	public void testMutableSumIfValueGreaterThan10000()
	{
		BigDecimal value = main.sumIf(stream, b -> b.compareTo(TEN_THOUSAND) > 0);

		assertEquals(new BigDecimal("85714450557035.85"), value);
	}

	@Test(timeout = 7000)
	public void testSumsByRange()
	{
		Map<Long, BigDecimal> values = main.sumsByRange(stream);

		assertNotNull(values);
		assertEquals(8572, values.size());
		assertEquals(new BigDecimal("3500843.28"), values.get(1L));
		assertEquals(new BigDecimal("2308503510.00"), values.get(989L));
		assertEquals(new BigDecimal("4520187510.00"), values.get(1937L));
		assertEquals(new BigDecimal("6962838510.00"), values.get(2984L));
		assertEquals(new BigDecimal("11227562176.72"), values.get(4812L));
		assertEquals(new BigDecimal("13023972510.00"), values.get(5582L));
		assertEquals(new BigDecimal("14329592843.28"), values.get(6139L));
		assertEquals(new BigDecimal("18152684843.28"), values.get(7777L));
		assertEquals(new BigDecimal("18175236510.00"), values.get(7790L));
		assertEquals(new BigDecimal("19367399176.72"), values.get(8301L));

	}

	/**
	 * Convert a Closeable to a Runnable by converting checked IOException
	 * to UncheckedIOException
	 */
	private static Runnable asUncheckedRunnable(Closeable c)
	{
		return () -> {
			try
			{
				c.close();
			}
			catch (IOException e)
			{
				throw new UncheckedIOException(e);
			}
		};
	}

	private boolean isDecimalGreaterThan50(BigDecimal value)
	{
		BigDecimal real = value.movePointRight(2);
		BigDecimal base = BigDecimal.valueOf(value.longValue()).movePointRight(2);
		return real.subtract(base).compareTo(FIFTY) > 0;
	}
}

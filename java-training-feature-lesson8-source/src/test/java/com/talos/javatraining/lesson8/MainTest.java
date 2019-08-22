package com.talos.javatraining.lesson8;

import com.talos.javatraining.lesson8.util.Loader;
import com.talos.javatraining.lesson8.util.TestScenario;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.time.format.DateTimeFormatter.*;
import static org.apache.commons.lang3.reflect.MethodUtils.invokeMethod;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MainTest
{

	private static Map<Integer, List<TestScenario>> testScenarios;

	// the class to test
	private Main main = new MainImpl();

	@BeforeClass
	public static void loadData() throws Exception
	{
		testScenarios = Loader.loadTestScenarios();
	}

	@Test
	public void testGetInstant() throws Exception
	{
		check("getInstant", str -> new Object[] { str }, (Instant i) -> ISO_INSTANT.format(i), 1);
	}

	@Test
	public void testGetDuration() throws Exception
	{
		Function<String, Object[]> generator = str -> {
			String[] parts = str.split(" ");
			return new Object[] { Instant.parse(parts[0]), Instant.parse(parts[1]) };
		};
		check("getDuration", generator, (Duration d) -> String.valueOf(d.get(ChronoUnit.SECONDS)), 2);
	}

	@Test
	public void testGetHumanReadableDate() throws Exception
	{
		check("getHumanReadableDate", str -> new Object[] { LocalDateTime.parse(str) }, (String str) -> str, 3);
	}

	@Test
	public void testGetLocalDateTime() throws Exception
	{
		check("getLocalDateTime", str -> new Object[] { str }, (LocalDateTime ldt) -> ISO_LOCAL_DATE_TIME.format(ldt), 4);
	}

	@Test
	public void testCalculateNewPeriod() throws Exception
	{
		LocalDate localDate = LocalDate.of(2012, 01, 01);
		Function<String, Object[]> generator = str -> {
			String[] parts = str.split(" ");
			return new Object[] { Period.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])) };
		};
		check("calculateNewPeriod", generator, (Period p) -> ISO_LOCAL_DATE.format(p.addTo(localDate)), 5);
	}

	@Test
	public void testToLocalDate() throws Exception
	{
		Function<String, Object[]> generator = str -> {
			String[] parts = str.split(" ");
			return new Object[] { Year.of(Integer.parseInt(parts[0])),
					MonthDay.of(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])) };
		};
		check("toLocalDate", generator, (LocalDate ld) -> ISO_LOCAL_DATE.format(ld), 6);
	}

	@Test
	public void testToLocalDateTime() throws Exception
	{
		Function<String, Object[]> generator = str -> {
			String[] parts = str.split(" ");
			return new Object[] { YearMonth.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])), Integer.parseInt(parts[2]),
					LocalTime.parse(parts[3]) };
		};
		check("toLocalDateTime", generator, (LocalDateTime ldt) -> ISO_LOCAL_DATE_TIME.format(ldt), 7);
	}

	@Test
	public void testCreateTemporalAdjusterNextMonday() throws Exception
	{
		Holder holder = new Holder();
		check("createTemporalAdjusterNextMonday", holder::generate, holder::convert, 8);
	}

	@Test
	public void testCreateTemporalAdjusterNextFebruaryFirst() throws Exception
	{
		Holder holder = new Holder();
		check("createTemporalAdjusterNextFebruaryFirst", holder::generate, holder::convert, 9);
	}

	@Test
	public void testAdjustDateTime() throws Exception
	{
		TemporalAdjuster adjuster = TemporalAdjusters.firstDayOfNextMonth();
		check("adjustDateTime", str -> new Object[] { str, adjuster}, (String str) -> str, 10);
	}

	@Test
	public void testProcessZonedDateTime() throws Exception
	{
		check("processZonedDateTime", str -> new Object[] { str }, (String str) -> str, 11);
	}

	/**
	 * Method that checks test class method
	 *
	 * @param method            the method to test
	 * @param argumentGenerator function that generates the method's arguments
	 * @param resultConverter   function that converts the method's result into an string
	 * @param testId            the test id
	 * @param <T>               the result type
	 * @throws Exception
	 */
	private <T> void check(String method, Function<String, Object[]> argumentGenerator, Function<T, String> resultConverter,
			int testId) throws Exception
	{
		List<TestScenario> scenarios = testScenarios.get(testId);
		for (TestScenario ts : scenarios)
		{
			Object[] arguments = argumentGenerator.apply(ts.getArgument());

			T result = (T) (arguments == null ? invokeMethod(main, method) : invokeMethod(main, method, arguments));

			String strArguments = StringUtils.removeAll(Arrays.toString(arguments), "[\\[\\]]");
			System.out.print(method + "(" + strArguments + ") returned: " + result);

			assertNotNull("Method '" + method + "' returned null with these arguments: " + strArguments, result);
			String actual = resultConverter.apply(result);
			assertEquals("Method '" + method + "' returned a no expected response with these arguments: " + strArguments
					, ts.getExpected(), actual);

			System.out.println(" ok!");
		}
	}

	/**
	 * Helper class to test the adjusters.
	 */
	private class Holder
	{
		private LocalDate localDate;

		public Object[] generate(String localDate)
		{
			this.localDate = LocalDate.parse(localDate);
			return null;
		}

		public String convert(TemporalAdjuster adjuster)
		{
			return ISO_LOCAL_DATE.format(localDate.with(adjuster));
		}
	}
}

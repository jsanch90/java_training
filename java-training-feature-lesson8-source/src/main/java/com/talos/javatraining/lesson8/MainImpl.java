package com.talos.javatraining.lesson8;



import java.time.*;
import java.time.temporal.TemporalAdjuster;


public class MainImpl implements Main
{

	@Override
	public Instant getInstant(String dateTime)
	{
		return null;
	}

	@Override
	public Duration getDuration(Instant a, Instant b)
	{
		return null;
	}

	@Override
	public String getHumanReadableDate(LocalDateTime localDateTime)
	{
		return null;
	}

	@Override
	public LocalDateTime getLocalDateTime(String dateTime)
	{
		return null;
	}

	@Override
	public Period calculateNewPeriod(Period period)
	{
		return null;
	}

	@Override
	public LocalDate toLocalDate(Year year, MonthDay monthDay)
	{
		return null;
	}

	@Override
	public LocalDateTime toLocalDateTime(YearMonth yearMonth, int dayOfMonth, LocalTime time)
	{
		return null;
	}

	@Override
	public TemporalAdjuster createTemporalAdjusterNextMonday()
	{
		return null;
	}

	@Override
	public TemporalAdjuster createTemporalAdjusterNextFebruaryFirst()
	{
		return null;
	}

	@Override
	public String adjustDateTime(String localDateTime, TemporalAdjuster adjuster)
	{
		return null;
	}

	@Override
	public String processZonedDateTime(String zonedDateTime)
	{
		return null;
	}
}

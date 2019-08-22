package com.talos.javatraining.lesson8;


import java.time.*;
import java.time.temporal.TemporalAdjuster;


public interface Main
{

	/**
	 * Gets an instant from the given data time and does these changes:
	 * <ol>
	 * <li>Adds a second</li>
	 * <li>Substracts 10 minutes</li>
	 * </ol>
	 *
	 * @param dateTime a date time in the format ISO_LOCAL_DATE_TIME in GMT-5.
	 * @return the instant with the expected changes
	 */
	Instant getInstant(String dateTime);

	/**
	 * Calculates the duration between the given instants and does these changes:
	 * <ol>
	 * <li>Adds 1 day</li>
	 * <li>Substracts 4 hours</li>
	 * </ol>
	 *
	 * @param a the instant a
	 * @param b the instant b
	 * @return the duration between the given instants with the expected changes
	 */
	Duration getDuration(Instant a, Instant b);

	/**
	 * Gets the given localDateTime does the changes listed below and returns it in ISO_LOCAL_DATE format
	 * <ol>
	 * <li>Adds 3 hours</li>
	 * <li>Sets the July month</li>
	 * <li>After the previous changes. Round the year to the next odd. Eg: Given year = 2012, then the year is set to 2013</li>
	 * </ol>
	 *
	 * @param localDateTime the local date time
	 * @return The human readable date with the given calculations
	 */
	String getHumanReadableDate(LocalDateTime localDateTime);

	/**
	 * Gets the given dateTime in this format: ssmmHHddMMyyyy and does the changes listed below:
	 * <ol>
	 * <li>Rounds the month to the next even. Eg: Given month = 3, then the month is set to 4</li>
	 * <li>Multiplies the seconds by 2. Hint: you have to add the missing seconds because you only can set a number from 0 to 59</li>
	 * </ol>
	 *
	 * @param dateTime the date time
	 * @return the local date time with the expected changes
	 */
	LocalDateTime getLocalDateTime(String dateTime);

	/**
	 * Does the listed below changes to the given period:
	 * <ol>
	 * <li>Adds 5 months</li>
	 * <li>Adds 6 days</li>
	 * <li>Substracts 2 weeks</li>
	 * </ol>
	 *
	 * @param period the period
	 * @return the new period
	 */
	Period calculateNewPeriod(Period period);

	/**
	 * Converts a given year and month-day into a localDate. In addition does these changes:
	 * <ol>
	 * <li>Adds 3 years</li>
	 * <li>Sets the days to the floor 5 multiple. If after round it the result is zero, set it to 1.
	 * Eg: Given day = 14, then the day is set to 10. </li>
	 * </ol>
	 *
	 * @param year     the year
	 * @param monthDay the month-day
	 * @return the local date
	 */
	LocalDate toLocalDate(Year year, MonthDay monthDay);

	/**
	 * Converts a given year-month, day and time into a localDateTime. In addition does these changes:
	 * <ol>
	 * <li>Set seconds to zero</li>
	 * <li>Substracts 37 minutes</li>
	 * <li>Adds 3 days</li>
	 * </ol>
	 *
	 * @param yearMonth  the year-month
	 * @param dayOfMonth the day of month
	 * @param time       the time
	 * @return the local date time
	 */
	LocalDateTime toLocalDateTime(YearMonth yearMonth, int dayOfMonth, LocalTime time);

	/**
	 * Create a temporal adjuster that given a LocalDate object will set it to the next monday.
	 *
	 * @return the temporal adjuster
	 */
	TemporalAdjuster createTemporalAdjusterNextMonday();

	/**
	 * Create a temporal adjuster that given a LocalDate object will set it to the next February first.
	 *
	 * @return the temporal adjuster
	 */
	TemporalAdjuster createTemporalAdjusterNextFebruaryFirst();

	/**
	 * Adjust the given date with the format ISO_LOCAL_DATE_TIME and using the given adjuster. Returns the result in the same format.
	 *
	 * @param localDateTime the localDateTime string
	 * @param adjuster  the adjuster to use
	 * @return the result
	 */
	String adjustDateTime(String localDateTime, TemporalAdjuster adjuster);

	/**
	 * Adjust the given zoned date time with the format ISO_OFFSET_DATE_TIME and does the changes listed below.
	 * Finally, returns the result in the RFC_1123_DATE_TIME format.
	 * <ol>
	 * <li>Adds 1 hour</li>
	 * <li>Change the time zone to UTC</li>
	 * <li>Rounds the minutes to the floor 15 multiple. Eg: Given minute  = 29, then minute is set to 15</li>
	 * </ol>
	 *
	 * @param zonedDateTime the zoned date time with the format ISO_OFFSET_DATE_TIME
	 * @return the result
	 */
	String processZonedDateTime(String zonedDateTime);
}


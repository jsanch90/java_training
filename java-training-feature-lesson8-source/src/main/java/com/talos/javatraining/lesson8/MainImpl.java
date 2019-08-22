package com.talos.javatraining.lesson8;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;


public class MainImpl implements Main {

    @Override
    public Instant getInstant(String dateTime) {

        return LocalDateTime.parse(dateTime)
                .plusSeconds(1)
                .minusMinutes(10)
                .toInstant(ZoneOffset.of("-5"));
    }

    @Override
    public Duration getDuration(Instant a, Instant b) {

        return Duration.between(a, b)
                .plusDays(1)
                .minusHours(4);

    }

    @Override
    public String getHumanReadableDate(LocalDateTime localDateTime) {

        LocalDateTime res = localDateTime
                .plusHours(3)
                .withMonth(7);
        return (res.getYear() % 2 == 0) ?
                res.plusYears(1).format(DateTimeFormatter.ISO_LOCAL_DATE) :
                res.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public LocalDateTime getLocalDateTime(String dateTime) {
        LocalDateTime res = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("ssmmHHddMMyyyy"));
        if (res.getMonthValue() % 2 != 0) {
            res = res.plusMonths(1);
        }

        return res.getSecond() < 30 ?
                res.withSecond(res.getSecond() * 2) :
                res.withSecond((res.getSecond() * 2) - 60).plusMinutes(1);
    }

    @Override
    public Period calculateNewPeriod(Period period) {
        return period
                .plus(Period.ofMonths(5))
                .plus(Period.ofDays(6))
                .minus(Period.ofWeeks(2));
    }

    @Override
    public LocalDate toLocalDate(Year year, MonthDay monthDay) {
        LocalDate res = year.atMonthDay(monthDay);

        res = res.plusYears(3);
        return ((res.getDayOfMonth() / 5) * 5 == 0) ?
                res.withDayOfMonth(1) :
                res.withDayOfMonth((res.getDayOfMonth() / 5) * 5);

    }

    @Override
    public LocalDateTime toLocalDateTime(YearMonth yearMonth, int dayOfMonth, LocalTime time) {
        return yearMonth
                .atDay(dayOfMonth)
                .atTime(time)
                .withSecond(0)
                .minusMinutes(37)
                .plusDays(3);

    }

    @Override
    public TemporalAdjuster createTemporalAdjusterNextMonday() {
        return TemporalAdjusters.next(DayOfWeek.MONDAY);
    }

    @Override
    public TemporalAdjuster createTemporalAdjusterNextFebruaryFirst() {

        return TemporalAdjusters.ofDateAdjuster(date -> {
            return (date.getMonthValue() >= 2) ?
                    date.withMonth(2).withDayOfMonth(1).plusYears(1) :
                    date.withMonth(2).withDayOfMonth(1);
        });

    }

    @Override
    public String adjustDateTime(String localDateTime, TemporalAdjuster adjuster) {
        return LocalDateTime
                .parse(localDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .with(adjuster)
                .toString();

    }

    @Override
    public String processZonedDateTime(String zonedDateTime) {
        ZonedDateTime res = ZonedDateTime
                .parse(zonedDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .withZoneSameInstant(ZoneId.of("UTC"))
                .plusHours(1);
        return (res.getMinute() % 15 != 0) ?
                res.withMinute((res.getMinute() / 15) * 15).format(DateTimeFormatter.RFC_1123_DATE_TIME) :
                res.format(DateTimeFormatter.RFC_1123_DATE_TIME);

    }
}


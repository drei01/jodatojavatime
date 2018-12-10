package com.smallimprovements.jodatojava

import org.joda.time.DateTime
import spock.lang.Specification

import java.time.*
import java.time.temporal.ChronoUnit

class CycleTest extends Specification {
    def "IsMoreThanTwoDaysOld"() {
        expect: new Cycle(new DateTime(Date.from(Instant.now().minus(2, ChronoUnit.DAYS))), new DateTime())
                .isMoreThanTwoDaysOld()
    }

    def "HasStarted"() {
        expect: new Cycle(new DateTime(), new DateTime(Date.from(Instant.now().minus(1, ChronoUnit.DAYS))))
                .hasStarted()
    }

    def "DaysBetweenFirstJan2018AndCreated"() {
        def firstFeb2018 = Date.from(LocalDateTime.of(2018, 02, 01, 0, 0).atZone(ZoneId.of("CET")).toInstant())
        expect: new Cycle(new DateTime(firstFeb2018), new DateTime())
                .daysBetweenFirstJan2018AndCreated() == 31
    }

    def "GetStartDateBeginningOfDay"() {
        def firstJanTenAM = Date.from(LocalDateTime.of(2018, 01, 01, 10, 0).atZone(ZoneId.of("CET")).toInstant())
        def firstJanMidnightAM = Date.from(LocalDateTime.of(2018, 01, 01, 0, 0).atZone(ZoneId.of("CET")).toInstant())
        expect: new Cycle(new DateTime(), new DateTime(firstJanTenAM))
                .getStartDateBeginningOfDay() == firstJanMidnightAM
    }

    def "GetStartDateAtTimeZone"() {
        def fiveAm = Date.from(ZonedDateTime.of(LocalDateTime.of(2018, 01, 01, 05, 00, 00), ZoneId.ofOffset("UTC", ZoneOffset.ofHours(5))).toInstant())
        def midnight = Date.from(ZonedDateTime.of(LocalDateTime.of(2018, 01, 01, 00, 00, 00), ZoneId.of("UTC")).toInstant())
        expect: new Cycle(new DateTime(), new DateTime(fiveAm))
                .getStartDateAtTimeZone("UTC") == midnight
    }

    def "DaysBetweenCreateAndStartDates"() {
        expect: new Cycle(new DateTime(), new DateTime(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)))).daysBetweenCreateAndStartDates() == 2
    }

    def "LastDayOfPreviousMonth"() {
        def jan28 = Date.from(LocalDateTime.of(2018, 01, 28, 0,0).atZone(ZoneOffset.systemDefault()).toInstant())
        def dec31 = Date.from(LocalDateTime.of(2017, 12, 31, 0,0).atZone(ZoneOffset.systemDefault()).toInstant())
        expect: new Cycle(new DateTime(), new DateTime(jan28))
                .lastDayOfPreviousMonth() == dec31
    }
}

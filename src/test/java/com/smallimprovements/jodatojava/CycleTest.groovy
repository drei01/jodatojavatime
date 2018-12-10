package com.smallimprovements.jodatojava

import spock.lang.Specification

import java.time.*
import java.time.temporal.ChronoUnit

class CycleTest extends Specification {
    def "IsMoreThanTwoDaysOld"() {
        expect: new Cycle(Instant.now().minus(2, ChronoUnit.DAYS), Instant.now())
                .isMoreThanTwoDaysOld()
    }

    def "HasStarted"() {
        expect: new Cycle(Instant.now(), Instant.now().minus(1, ChronoUnit.DAYS))
                .hasStarted()
    }

    def "DaysBetweenFirstJan2018AndCreated"() {
        def firstFeb2018 = LocalDateTime.of(2018, 02, 01, 0, 0).atZone(ZoneId.of("UCT")).toInstant()
        expect: new Cycle(firstFeb2018, Instant.now())
                .daysBetweenFirstJan2018AndCreated() == 31
    }

    def "GetStartDateBeginningOfDay"() {
        def firstJanTenAM = ZonedDateTime.of(2018, 01, 01, 10, 0, 0, 0, ZoneId.of("UTC")).toInstant()
        def firstJanMidnightAM = ZonedDateTime.of(2018, 01, 01, 0, 0, 0,0, ZoneId.of("UTC")).toInstant()
        expect: new Cycle(Instant.now(), firstJanTenAM)
                .getStartDateBeginningOfDay() == Date.from(firstJanMidnightAM)
    }

    def "GetStartDateAtTimeZone"() {
        def fiveAm = ZonedDateTime.of(LocalDateTime.of(2018, 01, 01, 05, 00, 00), ZoneId.ofOffset("UTC", ZoneOffset.ofHours(5))).toInstant()
        def midnight = ZonedDateTime.of(LocalDateTime.of(2018, 01, 01, 00, 00, 00), ZoneId.of("UTC")).toInstant()
        expect: new Cycle(Instant.now(), fiveAm)
                .getStartDateAtTimeZone("UTC") == Date.from(midnight)
    }

    def "DaysBetweenCreateAndStartDates"() {
        expect: new Cycle(Instant.now(), Instant.now().plus(2, ChronoUnit.DAYS)).daysBetweenCreateAndStartDates() == 2
    }

    def "LastDayOfPreviousMonth"() {
        def jan28 = LocalDateTime.of(2018, 01, 28, 0,0).atZone(ZoneOffset.systemDefault()).toInstant()
        def dec31 = LocalDateTime.of(2017, 12, 31, 0,0).atZone(ZoneOffset.systemDefault()).toInstant()
        expect: new Cycle(Instant.now(), jan28)
                .lastDayOfPreviousMonth() == Date.from(dec31)
    }
}

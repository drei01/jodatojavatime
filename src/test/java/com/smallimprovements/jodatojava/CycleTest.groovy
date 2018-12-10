package com.smallimprovements.jodatojava

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import spock.lang.Specification

class CycleTest extends Specification {
    def "IsMoreThanTwoDaysOld"() {
        expect: new Cycle(new DateTime().minusDays(2), new DateTime())
                .isMoreThanTwoDaysOld()
    }

    def "HasStarted"() {
        expect: new Cycle(new DateTime(), new DateTime().minusDays(1))
                .hasStarted()
    }

    def "DaysBetweenFirstJan2018AndCreated"() {
        expect: new Cycle(new DateTime(2018, 02, 01, 0, 0), new DateTime())
                .daysBetweenFirstJan2018AndCreated() == 31
    }

    def "GetStartDateBeginningOfDay"() {
        expect: new Cycle(new DateTime(), new DateTime(2018, 01, 01, 10, 50))
                .getStartDateBeginningOfDay() == new DateTime(2018, 01, 01, 0, 0).toDate()
    }

    def "GetStartDateAtTimeZone"() {
        expect: new Cycle(new DateTime(), new DateTime(2018,01,01,05,00,00, 00, DateTimeZone.forOffsetHours(5)))
                .getStartDateAtTimeZone("UTC") == new DateTime(2018,01,01,00,00,00, 00, DateTimeZone.UTC).toDate()
    }

    def "DaysBetweenCreateAndStartDates"() {
        expect: new Cycle(new DateTime(), new DateTime().plusDays(2)).daysBetweenCreateAndStartDates() == 2
    }

    def "LastDayOfPreviousMonth"() {
        expect: new Cycle(new DateTime(), new DateTime(2018, 01, 28, 0, 0))
                .lastDayOfPreviousMonth() == new DateTime(2017, 12, 31, 0, 0).toDate()
    }
}

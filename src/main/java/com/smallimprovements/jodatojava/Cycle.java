package com.smallimprovements.jodatojava;

import lombok.Value;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;

import java.util.Date;

@Value
public class Cycle {
    private DateTime createdAt;
    private DateTime startDate;

    public boolean isMoreThanTwoDaysOld() {
        return createdAt.isBefore(new DateTime().minusDays(2));
    }

    public boolean hasStarted() {
        return startDate.isBeforeNow();
    }

    public int daysBetweenFirstJan2018AndCreated() {
        return Days.daysBetween(new DateTime(2018, 01, 01, 0, 0, 0), createdAt).getDays();
    }

    public Date getStartDateBeginningOfDay() {
        return startDate.withTimeAtStartOfDay().toDate();
    }

    public Date getStartDateAtTimeZone(String timeZone) {
        return startDate.withZone(DateTimeZone.forID(timeZone)).toDate();
    }

    public long daysBetweenCreateAndStartDates() {
        return new Duration(createdAt, startDate).getStandardDays();
    }

    public Date beginningOfStartMonth() {
        return startDate.dayOfMonth().withMaximumValue().withTimeAtStartOfDay().minusMonths(1)
                .toDate();
    }


}

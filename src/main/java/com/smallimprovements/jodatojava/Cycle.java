package com.smallimprovements.jodatojava;

import lombok.Value;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Value
public class Cycle {
    private Instant createdAt;
    private Instant startDate;

    public boolean isMoreThanTwoDaysOld() {
        return createdAt.isBefore(Instant.now().minus(2, ChronoUnit.DAYS));
    }

    public boolean hasStarted() {
        return startDate.isBefore(Instant.now());
    }

    public long daysBetweenFirstJan2018AndCreated() {
        final Instant firstJan2018 = LocalDateTime.of(2018, 01, 01, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant();
        return Duration.between(firstJan2018, createdAt).toDays();
    }

    public Date getStartDateBeginningOfDay() {
        return Date.from(startDate.truncatedTo(ChronoUnit.DAYS));
    }

    public Date getStartDateAtTimeZone(String timeZone) {
        return Date.from(startDate.atZone(ZoneId.of(timeZone)).toInstant());
    }

    public long daysBetweenCreateAndStartDates() {
        return Duration.between(createdAt, startDate).toDays();
    }

    public Date lastDayOfPreviousMonth() {
        return Date.from(startDate
                .truncatedTo(ChronoUnit.DAYS)
                .atZone(ZoneId.systemDefault())
                .minus(1, ChronoUnit.MONTHS).toInstant());
    }


}

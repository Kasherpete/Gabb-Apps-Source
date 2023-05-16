package com.gabb.base.extensions;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\t\u001a\u00020\n\u001a\u0016\u0010\u000b\u001a\n \b*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\f\u001a\u00020\u0001\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u0004\u001a\u0012\u0010\u000f\u001a\n \b*\u0004\u0018\u00010\u00040\u0004*\u00020\u0004\u001a\u001a\u0010\u0010\u001a\n \b*\u0004\u0018\u00010\u00010\u0001*\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0001\u001a\u001a\u0010\u0010\u001a\n \b*\u0004\u0018\u00010\u00010\u0001*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0001\u001a\n\u0010\u0012\u001a\u00020\u0013*\u00020\u0004\u001a\n\u0010\u0014\u001a\u00020\u0013*\u00020\u0004\u001a\u0012\u0010\u0015\u001a\n \b*\u0004\u0018\u00010\u00010\u0001*\u00020\u0004\u001a\u0012\u0010\u0016\u001a\n \b*\u0004\u0018\u00010\u00010\u0001*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, mo20735d2 = {"MONTH_DAY_YEAR", "", "TIME_TWELVE_HOUR", "now", "Ljava/time/LocalDateTime;", "getNow", "()Ljava/time/LocalDateTime;", "fromEpochMillis", "kotlin.jvm.PlatformType", "epoch", "", "fromString", "dateString", "daysFromToday", "", "endOfDay", "format", "Ljava/time/LocalDate;", "isToday", "", "isYesterday", "toMonthDayYear", "toTwelveHourTime", "base"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: date.kt */
public final class DateKt {
    public static final String MONTH_DAY_YEAR = "MM/dd/yyyy";
    public static final String TIME_TWELVE_HOUR = "hh:mm a";

    public static final LocalDateTime getNow() {
        LocalDateTime now = LocalDateTime.now();
        Intrinsics.checkNotNullExpressionValue(now, "now()");
        return now;
    }

    public static final String format(LocalDate localDate, String str) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(str, "format");
        return localDate.format(DateTimeFormatter.ofPattern(str, Locale.getDefault()));
    }

    public static final String format(LocalDateTime localDateTime, String str) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        Intrinsics.checkNotNullParameter(str, "format");
        return localDateTime.format(DateTimeFormatter.ofPattern(str, Locale.getDefault()));
    }

    public static final LocalDateTime fromEpochMillis(long j) {
        return Instant.ofEpochMilli(j).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static final LocalDateTime fromString(String str) {
        Intrinsics.checkNotNullParameter(str, "dateString");
        return Instant.parse(str).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static final LocalDateTime endOfDay(LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        return localDateTime.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0).minusNanos(1);
    }

    public static final boolean isToday(LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        return daysFromToday(localDateTime) == 0;
    }

    public static final boolean isYesterday(LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        return daysFromToday(localDateTime) == 1;
    }

    public static final int daysFromToday(LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        return (int) ChronoUnit.DAYS.between(localDateTime, getNow());
    }

    public static final String toMonthDayYear(LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        return format(localDateTime, MONTH_DAY_YEAR);
    }

    public static final String toTwelveHourTime(LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        return format(localDateTime, TIME_TWELVE_HOUR);
    }
}

package com.datadog.android;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/DatadogSite;", "", "(Ljava/lang/String;I)V", "logsEndpoint", "", "rumEndpoint", "tracesEndpoint", "US1", "US3", "US5", "US1_FED", "EU1", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogSite.kt */
public enum DatadogSite {
    US1,
    US3,
    US5,
    US1_FED,
    EU1;

    @Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DatadogSite.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] iArr = new int[DatadogSite.values().length];
            iArr[DatadogSite.US1.ordinal()] = 1;
            iArr[DatadogSite.US3.ordinal()] = 2;
            iArr[DatadogSite.US5.ordinal()] = 3;
            iArr[DatadogSite.US1_FED.ordinal()] = 4;
            iArr[DatadogSite.EU1.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final String logsEndpoint() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "https://logs.browser-intake-datadoghq.com";
        }
        if (i == 2) {
            return DatadogEndpoint.LOGS_US3;
        }
        if (i == 3) {
            return DatadogEndpoint.LOGS_US5;
        }
        if (i == 4) {
            return "https://logs.browser-intake-ddog-gov.com";
        }
        if (i == 5) {
            return "https://mobile-http-intake.logs.datadoghq.eu";
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String tracesEndpoint() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "https://trace.browser-intake-datadoghq.com";
        }
        if (i == 2) {
            return DatadogEndpoint.TRACES_US3;
        }
        if (i == 3) {
            return DatadogEndpoint.TRACES_US5;
        }
        if (i == 4) {
            return "https://trace.browser-intake-ddog-gov.com";
        }
        if (i == 5) {
            return "https:/public-trace-http-intake.logs.datadoghq.eu";
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String rumEndpoint() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "https://rum.browser-intake-datadoghq.com";
        }
        if (i == 2) {
            return DatadogEndpoint.RUM_US3;
        }
        if (i == 3) {
            return DatadogEndpoint.RUM_US5;
        }
        if (i == 4) {
            return "https://rum.browser-intake-ddog-gov.com";
        }
        if (i == 5) {
            return "https://rum-http-intake.logs.datadoghq.eu";
        }
        throw new NoWhenBranchMatchedException();
    }
}

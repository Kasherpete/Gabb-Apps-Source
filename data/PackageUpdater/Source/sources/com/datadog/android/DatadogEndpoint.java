package com.datadog.android;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b%\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002R\u0016\u0010\t\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0002R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0002R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0002R\u0016\u0010\u0018\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u0019\u0010\u0002R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u001f\u0010\u0002R\u000e\u0010 \u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\"\u0010\u0002R\u0016\u0010#\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b$\u0010\u0002R\u000e\u0010%\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006)"}, mo20735d2 = {"Lcom/datadog/android/DatadogEndpoint;", "", "()V", "LOGS_EU", "", "getLOGS_EU$annotations", "LOGS_EU1", "LOGS_GOV", "getLOGS_GOV$annotations", "LOGS_US", "getLOGS_US$annotations", "LOGS_US1", "LOGS_US1_FED", "LOGS_US3", "LOGS_US5", "NTP_0", "NTP_1", "NTP_2", "NTP_3", "RUM_EU", "getRUM_EU$annotations", "RUM_EU1", "RUM_GOV", "getRUM_GOV$annotations", "RUM_US", "getRUM_US$annotations", "RUM_US1", "RUM_US1_FED", "RUM_US3", "RUM_US5", "TRACES_EU", "getTRACES_EU$annotations", "TRACES_EU1", "TRACES_GOV", "getTRACES_GOV$annotations", "TRACES_US", "getTRACES_US$annotations", "TRACES_US1", "TRACES_US1_FED", "TRACES_US3", "TRACES_US5", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogEndpoint.kt */
public final class DatadogEndpoint {
    public static final DatadogEndpoint INSTANCE = new DatadogEndpoint();
    public static final String LOGS_EU = "https://mobile-http-intake.logs.datadoghq.eu";
    public static final String LOGS_EU1 = "https://mobile-http-intake.logs.datadoghq.eu";
    public static final String LOGS_GOV = "https://logs.browser-intake-ddog-gov.com";
    public static final String LOGS_US = "https://logs.browser-intake-datadoghq.com";
    public static final String LOGS_US1 = "https://logs.browser-intake-datadoghq.com";
    public static final String LOGS_US1_FED = "https://logs.browser-intake-ddog-gov.com";
    public static final String LOGS_US3 = "https://logs.browser-intake-us3-datadoghq.com";
    public static final String LOGS_US5 = "https://logs.browser-intake-us5-datadoghq.com";
    public static final String NTP_0 = "0.datadog.pool.ntp.org";
    public static final String NTP_1 = "1.datadog.pool.ntp.org";
    public static final String NTP_2 = "2.datadog.pool.ntp.org";
    public static final String NTP_3 = "3.datadog.pool.ntp.org";
    public static final String RUM_EU = "https://rum-http-intake.logs.datadoghq.eu";
    public static final String RUM_EU1 = "https://rum-http-intake.logs.datadoghq.eu";
    public static final String RUM_GOV = "https://rum.browser-intake-ddog-gov.com";
    public static final String RUM_US = "https://rum.browser-intake-datadoghq.com";
    public static final String RUM_US1 = "https://rum.browser-intake-datadoghq.com";
    public static final String RUM_US1_FED = "https://rum.browser-intake-ddog-gov.com";
    public static final String RUM_US3 = "https://rum.browser-intake-us3-datadoghq.com";
    public static final String RUM_US5 = "https://rum.browser-intake-us5-datadoghq.com";
    public static final String TRACES_EU = "https:/public-trace-http-intake.logs.datadoghq.eu";
    public static final String TRACES_EU1 = "https:/public-trace-http-intake.logs.datadoghq.eu";
    public static final String TRACES_GOV = "https://trace.browser-intake-ddog-gov.com";
    public static final String TRACES_US = "https://trace.browser-intake-datadoghq.com";
    public static final String TRACES_US1 = "https://trace.browser-intake-datadoghq.com";
    public static final String TRACES_US1_FED = "https://trace.browser-intake-ddog-gov.com";
    public static final String TRACES_US3 = "https://trace.browser-intake-us3-datadoghq.com";
    public static final String TRACES_US5 = "https://trace.browser-intake-us5-datadoghq.com";

    @Deprecated(message = "Use LOGS_EU1 instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.LOGS_EU1", imports = {}))
    public static /* synthetic */ void getLOGS_EU$annotations() {
    }

    @Deprecated(message = "Use LOGS_US1_FED instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.LOGS_US1_FED", imports = {}))
    public static /* synthetic */ void getLOGS_GOV$annotations() {
    }

    @Deprecated(message = "Use LOGS_US1 instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.LOGS_US1", imports = {}))
    public static /* synthetic */ void getLOGS_US$annotations() {
    }

    @Deprecated(message = "Use RUM_EU1 instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.RUM_EU1", imports = {}))
    public static /* synthetic */ void getRUM_EU$annotations() {
    }

    @Deprecated(message = "Use RUM_US1_FED instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.RUM_US1_FED", imports = {}))
    public static /* synthetic */ void getRUM_GOV$annotations() {
    }

    @Deprecated(message = "Use RUM_US1 instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.RUM_US1", imports = {}))
    public static /* synthetic */ void getRUM_US$annotations() {
    }

    @Deprecated(message = "Use TRACES_EU1 instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.TRACES_EU1", imports = {}))
    public static /* synthetic */ void getTRACES_EU$annotations() {
    }

    @Deprecated(message = "Use TRACES_US1_FED instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.TRACES_US1_FED", imports = {}))
    public static /* synthetic */ void getTRACES_GOV$annotations() {
    }

    @Deprecated(message = "Use TRACES_US1 instead", replaceWith = @ReplaceWith(expression = "DatadogEndpoint.TRACES_US1", imports = {}))
    public static /* synthetic */ void getTRACES_US$annotations() {
    }

    private DatadogEndpoint() {
    }
}

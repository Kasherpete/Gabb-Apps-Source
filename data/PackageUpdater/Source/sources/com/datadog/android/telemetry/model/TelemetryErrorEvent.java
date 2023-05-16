package com.datadog.android.telemetry.model;

import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.webview.internal.rum.WebViewRumEventMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import p006io.opentracing.log.Fields;

@Metadata(mo20734d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\b\u0018\u0000 @2\u00020\u0001:\t>?@ABCDEFBe\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0014HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0007HÆ\u0003J\t\u0010/\u001a\u00020\tHÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0012HÆ\u0003Ju\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\u0006\u0010;\u001a\u00020<J\t\u0010=\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*¨\u0006G"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent;", "", "dd", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Dd;", "date", "", "service", "", "source", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source;", "version", "application", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Application;", "session", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Session;", "view", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$View;", "action", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Action;", "telemetry", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Telemetry;", "(Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Dd;JLjava/lang/String;Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source;Ljava/lang/String;Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Application;Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Session;Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$View;Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Action;Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Telemetry;)V", "getAction", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Action;", "getApplication", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Application;", "getDate", "()J", "getDd", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Dd;", "getService", "()Ljava/lang/String;", "getSession", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Session;", "getSource", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source;", "getTelemetry", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Telemetry;", "type", "getType", "getVersion", "getView", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$View;", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Action", "Application", "Companion", "Dd", "Error", "Session", "Source", "Telemetry", "View", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TelemetryErrorEvent.kt */
public final class TelemetryErrorEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Action action;
    private final Application application;
    private final long date;

    /* renamed from: dd */
    private final C0872Dd f144dd;
    private final String service;
    private final Session session;
    private final Source source;
    private final Telemetry telemetry;
    private final String type;
    private final String version;
    private final View view;

    public static /* synthetic */ TelemetryErrorEvent copy$default(TelemetryErrorEvent telemetryErrorEvent, C0872Dd dd, long j, String str, Source source2, String str2, Application application2, Session session2, View view2, Action action2, Telemetry telemetry2, int i, Object obj) {
        TelemetryErrorEvent telemetryErrorEvent2 = telemetryErrorEvent;
        int i2 = i;
        return telemetryErrorEvent.copy((i2 & 1) != 0 ? telemetryErrorEvent2.f144dd : dd, (i2 & 2) != 0 ? telemetryErrorEvent2.date : j, (i2 & 4) != 0 ? telemetryErrorEvent2.service : str, (i2 & 8) != 0 ? telemetryErrorEvent2.source : source2, (i2 & 16) != 0 ? telemetryErrorEvent2.version : str2, (i2 & 32) != 0 ? telemetryErrorEvent2.application : application2, (i2 & 64) != 0 ? telemetryErrorEvent2.session : session2, (i2 & 128) != 0 ? telemetryErrorEvent2.view : view2, (i2 & 256) != 0 ? telemetryErrorEvent2.action : action2, (i2 & 512) != 0 ? telemetryErrorEvent2.telemetry : telemetry2);
    }

    @JvmStatic
    public static final TelemetryErrorEvent fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final C0872Dd component1() {
        return this.f144dd;
    }

    public final Telemetry component10() {
        return this.telemetry;
    }

    public final long component2() {
        return this.date;
    }

    public final String component3() {
        return this.service;
    }

    public final Source component4() {
        return this.source;
    }

    public final String component5() {
        return this.version;
    }

    public final Application component6() {
        return this.application;
    }

    public final Session component7() {
        return this.session;
    }

    public final View component8() {
        return this.view;
    }

    public final Action component9() {
        return this.action;
    }

    public final TelemetryErrorEvent copy(C0872Dd dd, long j, String str, Source source2, String str2, Application application2, Session session2, View view2, Action action2, Telemetry telemetry2) {
        Intrinsics.checkNotNullParameter(dd, "dd");
        String str3 = str;
        Intrinsics.checkNotNullParameter(str3, "service");
        Source source3 = source2;
        Intrinsics.checkNotNullParameter(source3, "source");
        String str4 = str2;
        Intrinsics.checkNotNullParameter(str4, "version");
        Telemetry telemetry3 = telemetry2;
        Intrinsics.checkNotNullParameter(telemetry3, "telemetry");
        return new TelemetryErrorEvent(dd, j, str3, source3, str4, application2, session2, view2, action2, telemetry3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TelemetryErrorEvent)) {
            return false;
        }
        TelemetryErrorEvent telemetryErrorEvent = (TelemetryErrorEvent) obj;
        return Intrinsics.areEqual((Object) this.f144dd, (Object) telemetryErrorEvent.f144dd) && this.date == telemetryErrorEvent.date && Intrinsics.areEqual((Object) this.service, (Object) telemetryErrorEvent.service) && this.source == telemetryErrorEvent.source && Intrinsics.areEqual((Object) this.version, (Object) telemetryErrorEvent.version) && Intrinsics.areEqual((Object) this.application, (Object) telemetryErrorEvent.application) && Intrinsics.areEqual((Object) this.session, (Object) telemetryErrorEvent.session) && Intrinsics.areEqual((Object) this.view, (Object) telemetryErrorEvent.view) && Intrinsics.areEqual((Object) this.action, (Object) telemetryErrorEvent.action) && Intrinsics.areEqual((Object) this.telemetry, (Object) telemetryErrorEvent.telemetry);
    }

    public int hashCode() {
        int hashCode = ((((((((this.f144dd.hashCode() * 31) + Long.hashCode(this.date)) * 31) + this.service.hashCode()) * 31) + this.source.hashCode()) * 31) + this.version.hashCode()) * 31;
        Application application2 = this.application;
        int i = 0;
        int hashCode2 = (hashCode + (application2 == null ? 0 : application2.hashCode())) * 31;
        Session session2 = this.session;
        int hashCode3 = (hashCode2 + (session2 == null ? 0 : session2.hashCode())) * 31;
        View view2 = this.view;
        int hashCode4 = (hashCode3 + (view2 == null ? 0 : view2.hashCode())) * 31;
        Action action2 = this.action;
        if (action2 != null) {
            i = action2.hashCode();
        }
        return ((hashCode4 + i) * 31) + this.telemetry.hashCode();
    }

    public String toString() {
        C0872Dd dd = this.f144dd;
        long j = this.date;
        String str = this.service;
        Source source2 = this.source;
        String str2 = this.version;
        Application application2 = this.application;
        Session session2 = this.session;
        View view2 = this.view;
        Action action2 = this.action;
        return "TelemetryErrorEvent(dd=" + dd + ", date=" + j + ", service=" + str + ", source=" + source2 + ", version=" + str2 + ", application=" + application2 + ", session=" + session2 + ", view=" + view2 + ", action=" + action2 + ", telemetry=" + this.telemetry + ")";
    }

    public TelemetryErrorEvent(C0872Dd dd, long j, String str, Source source2, String str2, Application application2, Session session2, View view2, Action action2, Telemetry telemetry2) {
        Intrinsics.checkNotNullParameter(dd, "dd");
        Intrinsics.checkNotNullParameter(str, "service");
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(str2, "version");
        Intrinsics.checkNotNullParameter(telemetry2, "telemetry");
        this.f144dd = dd;
        this.date = j;
        this.service = str;
        this.source = source2;
        this.version = str2;
        this.application = application2;
        this.session = session2;
        this.view = view2;
        this.action = action2;
        this.telemetry = telemetry2;
        this.type = "telemetry";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ TelemetryErrorEvent(com.datadog.android.telemetry.model.TelemetryErrorEvent.C0872Dd r16, long r17, java.lang.String r19, com.datadog.android.telemetry.model.TelemetryErrorEvent.Source r20, java.lang.String r21, com.datadog.android.telemetry.model.TelemetryErrorEvent.Application r22, com.datadog.android.telemetry.model.TelemetryErrorEvent.Session r23, com.datadog.android.telemetry.model.TelemetryErrorEvent.View r24, com.datadog.android.telemetry.model.TelemetryErrorEvent.Action r25, com.datadog.android.telemetry.model.TelemetryErrorEvent.Telemetry r26, int r27, kotlin.jvm.internal.DefaultConstructorMarker r28) {
        /*
            r15 = this;
            r0 = r27
            r1 = r0 & 32
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r10 = r2
            goto L_0x000b
        L_0x0009:
            r10 = r22
        L_0x000b:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0011
            r11 = r2
            goto L_0x0013
        L_0x0011:
            r11 = r23
        L_0x0013:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0019
            r12 = r2
            goto L_0x001b
        L_0x0019:
            r12 = r24
        L_0x001b:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x0021
            r13 = r2
            goto L_0x0023
        L_0x0021:
            r13 = r25
        L_0x0023:
            r3 = r15
            r4 = r16
            r5 = r17
            r7 = r19
            r8 = r20
            r9 = r21
            r14 = r26
            r3.<init>(r4, r5, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.telemetry.model.TelemetryErrorEvent.<init>(com.datadog.android.telemetry.model.TelemetryErrorEvent$Dd, long, java.lang.String, com.datadog.android.telemetry.model.TelemetryErrorEvent$Source, java.lang.String, com.datadog.android.telemetry.model.TelemetryErrorEvent$Application, com.datadog.android.telemetry.model.TelemetryErrorEvent$Session, com.datadog.android.telemetry.model.TelemetryErrorEvent$View, com.datadog.android.telemetry.model.TelemetryErrorEvent$Action, com.datadog.android.telemetry.model.TelemetryErrorEvent$Telemetry, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final C0872Dd getDd() {
        return this.f144dd;
    }

    public final long getDate() {
        return this.date;
    }

    public final String getService() {
        return this.service;
    }

    public final Source getSource() {
        return this.source;
    }

    public final String getVersion() {
        return this.version;
    }

    public final Application getApplication() {
        return this.application;
    }

    public final Session getSession() {
        return this.session;
    }

    public final View getView() {
        return this.view;
    }

    public final Action getAction() {
        return this.action;
    }

    public final Telemetry getTelemetry() {
        return this.telemetry;
    }

    public final String getType() {
        return this.type;
    }

    public final JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(WebViewRumEventMapper.DD_KEY_NAME, this.f144dd.toJson());
        jsonObject.addProperty(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type);
        jsonObject.addProperty("date", (Number) Long.valueOf(this.date));
        jsonObject.addProperty("service", this.service);
        jsonObject.add("source", this.source.toJson());
        jsonObject.addProperty("version", this.version);
        Application application2 = this.application;
        if (application2 != null) {
            jsonObject.add(WebViewRumEventMapper.APPLICATION_KEY_NAME, application2.toJson());
        }
        Session session2 = this.session;
        if (session2 != null) {
            jsonObject.add("session", session2.toJson());
        }
        View view2 = this.view;
        if (view2 != null) {
            jsonObject.add("view", view2.toJson());
        }
        Action action2 = this.action;
        if (action2 != null) {
            jsonObject.add("action", action2.toJson());
        }
        jsonObject.add("telemetry", this.telemetry.toJson());
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryErrorEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0068 A[Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0080 A[Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0098 A[Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }] */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.datadog.android.telemetry.model.TelemetryErrorEvent fromJson(java.lang.String r15) throws com.google.gson.JsonParseException {
            /*
                r14 = this;
                java.lang.String r14 = "version"
                java.lang.String r0 = "it"
                java.lang.String r1 = "service"
                java.lang.String r2 = "serializedObject"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r2)
                com.google.gson.JsonElement r15 = com.google.gson.JsonParser.parseString(r15)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.google.gson.JsonObject r15 = r15.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Dd r3 = new com.datadog.android.telemetry.model.TelemetryErrorEvent$Dd     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r3.<init>()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r2 = "date"
                com.google.gson.JsonElement r2 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                long r4 = r2.getAsLong()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.google.gson.JsonElement r2 = r15.get(r1)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r6 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r2 = "source"
                com.google.gson.JsonElement r2 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Source$Companion r7 = com.datadog.android.telemetry.model.TelemetryErrorEvent.Source.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Source r7 = r7.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.google.gson.JsonElement r2 = r15.get(r14)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r8 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r2 = "application"
                com.google.gson.JsonElement r2 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r9 = 0
                if (r2 != 0) goto L_0x0050
            L_0x004e:
                r10 = r9
                goto L_0x005e
            L_0x0050:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                if (r2 != 0) goto L_0x0057
                goto L_0x004e
            L_0x0057:
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Application$Companion r10 = com.datadog.android.telemetry.model.TelemetryErrorEvent.Application.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Application r2 = r10.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r10 = r2
            L_0x005e:
                java.lang.String r2 = "session"
                com.google.gson.JsonElement r2 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                if (r2 != 0) goto L_0x0068
            L_0x0066:
                r11 = r9
                goto L_0x0076
            L_0x0068:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                if (r2 != 0) goto L_0x006f
                goto L_0x0066
            L_0x006f:
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Session$Companion r11 = com.datadog.android.telemetry.model.TelemetryErrorEvent.Session.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Session r2 = r11.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r11 = r2
            L_0x0076:
                java.lang.String r2 = "view"
                com.google.gson.JsonElement r2 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                if (r2 != 0) goto L_0x0080
            L_0x007e:
                r12 = r9
                goto L_0x008e
            L_0x0080:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                if (r2 != 0) goto L_0x0087
                goto L_0x007e
            L_0x0087:
                com.datadog.android.telemetry.model.TelemetryErrorEvent$View$Companion r12 = com.datadog.android.telemetry.model.TelemetryErrorEvent.View.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$View r2 = r12.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r12 = r2
            L_0x008e:
                java.lang.String r2 = "action"
                com.google.gson.JsonElement r2 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                if (r2 != 0) goto L_0x0098
            L_0x0096:
                r13 = r9
                goto L_0x00a6
            L_0x0098:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                if (r2 != 0) goto L_0x009f
                goto L_0x0096
            L_0x009f:
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Action$Companion r9 = com.datadog.android.telemetry.model.TelemetryErrorEvent.Action.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Action r2 = r9.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r13 = r2
            L_0x00a6:
                java.lang.String r2 = "telemetry"
                com.google.gson.JsonElement r15 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r15 = r15.toString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Telemetry$Companion r2 = com.datadog.android.telemetry.model.TelemetryErrorEvent.Telemetry.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r0)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent$Telemetry r15 = r2.fromJson(r15)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryErrorEvent r0 = new com.datadog.android.telemetry.model.TelemetryErrorEvent     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r14)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r2 = r0
                r9 = r10
                r10 = r11
                r11 = r12
                r12 = r13
                r13 = r15
                r2.<init>(r3, r4, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                return r0
            L_0x00cb:
                r14 = move-exception
                com.google.gson.JsonParseException r15 = new com.google.gson.JsonParseException
                java.lang.String r14 = r14.getMessage()
                r15.<init>((java.lang.String) r14)
                throw r15
            L_0x00d6:
                r14 = move-exception
                com.google.gson.JsonParseException r15 = new com.google.gson.JsonParseException
                java.lang.String r14 = r14.getMessage()
                r15.<init>((java.lang.String) r14)
                throw r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.telemetry.model.TelemetryErrorEvent.Companion.fromJson(java.lang.String):com.datadog.android.telemetry.model.TelemetryErrorEvent");
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Dd;", "", "()V", "formatVersion", "", "getFormatVersion", "()J", "toJson", "Lcom/google/gson/JsonElement;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* renamed from: com.datadog.android.telemetry.model.TelemetryErrorEvent$Dd */
    /* compiled from: TelemetryErrorEvent.kt */
    public static final class C0872Dd {
        private final long formatVersion = 2;

        public final long getFormatVersion() {
            return this.formatVersion;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("format_version", (Number) Long.valueOf(this.formatVersion));
            return jsonObject;
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Application;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryErrorEvent.kt */
    public static final class Application {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f146id;

        public static /* synthetic */ Application copy$default(Application application, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = application.f146id;
            }
            return application.copy(str);
        }

        @JvmStatic
        public static final Application fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f146id;
        }

        public final Application copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Application(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Application) && Intrinsics.areEqual((Object) this.f146id, (Object) ((Application) obj).f146id);
        }

        public int hashCode() {
            return this.f146id.hashCode();
        }

        public String toString() {
            return "Application(id=" + this.f146id + ")";
        }

        public Application(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f146id = str;
        }

        public final String getId() {
            return this.f146id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f146id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Application$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Application;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryErrorEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Application fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String asString = JsonParser.parseString(str).getAsJsonObject().get("id").getAsString();
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    return new Application(asString);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Session;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryErrorEvent.kt */
    public static final class Session {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f147id;

        public static /* synthetic */ Session copy$default(Session session, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = session.f147id;
            }
            return session.copy(str);
        }

        @JvmStatic
        public static final Session fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f147id;
        }

        public final Session copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Session(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Session) && Intrinsics.areEqual((Object) this.f147id, (Object) ((Session) obj).f147id);
        }

        public int hashCode() {
            return this.f147id.hashCode();
        }

        public String toString() {
            return "Session(id=" + this.f147id + ")";
        }

        public Session(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f147id = str;
        }

        public final String getId() {
            return this.f147id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f147id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Session$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Session;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryErrorEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Session fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String asString = JsonParser.parseString(str).getAsJsonObject().get("id").getAsString();
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    return new Session(asString);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$View;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryErrorEvent.kt */
    public static final class View {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f148id;

        public static /* synthetic */ View copy$default(View view, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = view.f148id;
            }
            return view.copy(str);
        }

        @JvmStatic
        public static final View fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f148id;
        }

        public final View copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new View(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof View) && Intrinsics.areEqual((Object) this.f148id, (Object) ((View) obj).f148id);
        }

        public int hashCode() {
            return this.f148id.hashCode();
        }

        public String toString() {
            return "View(id=" + this.f148id + ")";
        }

        public View(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f148id = str;
        }

        public final String getId() {
            return this.f148id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f148id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$View$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$View;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryErrorEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final View fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String asString = JsonParser.parseString(str).getAsJsonObject().get("id").getAsString();
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    return new View(asString);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Action;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryErrorEvent.kt */
    public static final class Action {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f145id;

        public static /* synthetic */ Action copy$default(Action action, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = action.f145id;
            }
            return action.copy(str);
        }

        @JvmStatic
        public static final Action fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f145id;
        }

        public final Action copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Action(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Action) && Intrinsics.areEqual((Object) this.f145id, (Object) ((Action) obj).f145id);
        }

        public int hashCode() {
            return this.f145id.hashCode();
        }

        public String toString() {
            return "Action(id=" + this.f145id + ")";
        }

        public Action(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f145id = str;
        }

        public final String getId() {
            return this.f145id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f145id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Action$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Action;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryErrorEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Action fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String asString = JsonParser.parseString(str).getAsJsonObject().get("id").getAsString();
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    return new Action(asString);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0006\u0010\u0015\u001a\u00020\u0016J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0003XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0019"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Telemetry;", "", "message", "", "error", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Error;", "(Ljava/lang/String;Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Error;)V", "getError", "()Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Error;", "getMessage", "()Ljava/lang/String;", "status", "getStatus", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryErrorEvent.kt */
    public static final class Telemetry {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Error error;
        private final String message;
        private final String status;

        public static /* synthetic */ Telemetry copy$default(Telemetry telemetry, String str, Error error2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = telemetry.message;
            }
            if ((i & 2) != 0) {
                error2 = telemetry.error;
            }
            return telemetry.copy(str, error2);
        }

        @JvmStatic
        public static final Telemetry fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.message;
        }

        public final Error component2() {
            return this.error;
        }

        public final Telemetry copy(String str, Error error2) {
            Intrinsics.checkNotNullParameter(str, "message");
            return new Telemetry(str, error2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Telemetry)) {
                return false;
            }
            Telemetry telemetry = (Telemetry) obj;
            return Intrinsics.areEqual((Object) this.message, (Object) telemetry.message) && Intrinsics.areEqual((Object) this.error, (Object) telemetry.error);
        }

        public int hashCode() {
            int hashCode = this.message.hashCode() * 31;
            Error error2 = this.error;
            return hashCode + (error2 == null ? 0 : error2.hashCode());
        }

        public String toString() {
            String str = this.message;
            return "Telemetry(message=" + str + ", error=" + this.error + ")";
        }

        public Telemetry(String str, Error error2) {
            Intrinsics.checkNotNullParameter(str, "message");
            this.message = str;
            this.error = error2;
            this.status = "error";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Telemetry(String str, Error error2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : error2);
        }

        public final String getMessage() {
            return this.message;
        }

        public final Error getError() {
            return this.error;
        }

        public final String getStatus() {
            return this.status;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", this.status);
            jsonObject.addProperty("message", this.message);
            Error error2 = this.error;
            if (error2 != null) {
                jsonObject.add("error", error2.toJson());
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Telemetry$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Telemetry;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryErrorEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Telemetry fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    String asString = asJsonObject.get("message").getAsString();
                    JsonElement jsonElement = asJsonObject.get("error");
                    Error error = null;
                    if (jsonElement != null) {
                        String jsonElement2 = jsonElement.toString();
                        if (jsonElement2 != null) {
                            error = Error.Companion.fromJson(jsonElement2);
                        }
                    }
                    Intrinsics.checkNotNullExpressionValue(asString, "message");
                    return new Telemetry(asString, error);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Error;", "", "stack", "", "kind", "(Ljava/lang/String;Ljava/lang/String;)V", "getKind", "()Ljava/lang/String;", "getStack", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryErrorEvent.kt */
    public static final class Error {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String kind;
        private final String stack;

        public Error() {
            this((String) null, (String) null, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Error copy$default(Error error, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = error.stack;
            }
            if ((i & 2) != 0) {
                str2 = error.kind;
            }
            return error.copy(str, str2);
        }

        @JvmStatic
        public static final Error fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.stack;
        }

        public final String component2() {
            return this.kind;
        }

        public final Error copy(String str, String str2) {
            return new Error(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Error)) {
                return false;
            }
            Error error = (Error) obj;
            return Intrinsics.areEqual((Object) this.stack, (Object) error.stack) && Intrinsics.areEqual((Object) this.kind, (Object) error.kind);
        }

        public int hashCode() {
            String str = this.stack;
            int i = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.kind;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            String str = this.stack;
            return "Error(stack=" + str + ", kind=" + this.kind + ")";
        }

        public Error(String str, String str2) {
            this.stack = str;
            this.kind = str2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Error(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        public final String getStack() {
            return this.stack;
        }

        public final String getKind() {
            return this.kind;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.stack;
            if (str != null) {
                jsonObject.addProperty(Fields.STACK, str);
            }
            String str2 = this.kind;
            if (str2 != null) {
                jsonObject.addProperty("kind", str2);
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Error$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Error;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryErrorEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Error fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    JsonElement jsonElement = asJsonObject.get(Fields.STACK);
                    String str2 = null;
                    String asString = jsonElement == null ? null : jsonElement.getAsString();
                    JsonElement jsonElement2 = asJsonObject.get("kind");
                    if (jsonElement2 != null) {
                        str2 = jsonElement2.getAsString();
                    }
                    return new Error(asString, str2);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "ANDROID", "IOS", "BROWSER", "FLUTTER", "REACT_NATIVE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryErrorEvent.kt */
    public enum Source {
        ANDROID(CoreFeature.DEFAULT_SOURCE_NAME),
        IOS("ios"),
        BROWSER("browser"),
        FLUTTER("flutter"),
        REACT_NATIVE("react-native");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final Source fromJson(String str) {
            return Companion.fromJson(str);
        }

        private Source(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryErrorEvent$Source;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryErrorEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Source fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                Source[] values = Source.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    Source source = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) source.jsonValue, (Object) str)) {
                        return source;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }
}

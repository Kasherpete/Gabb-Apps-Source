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

@Metadata(mo20734d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\b\u0018\u0000 @2\u00020\u0001:\b>?@ABCDEBe\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0014HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0007HÆ\u0003J\t\u0010/\u001a\u00020\tHÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0012HÆ\u0003Ju\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\u0006\u0010;\u001a\u00020<J\t\u0010=\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*¨\u0006F"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent;", "", "dd", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Dd;", "date", "", "service", "", "source", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Source;", "version", "application", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Application;", "session", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Session;", "view", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$View;", "action", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Action;", "telemetry", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Telemetry;", "(Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Dd;JLjava/lang/String;Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Source;Ljava/lang/String;Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Application;Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Session;Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$View;Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Action;Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Telemetry;)V", "getAction", "()Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Action;", "getApplication", "()Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Application;", "getDate", "()J", "getDd", "()Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Dd;", "getService", "()Ljava/lang/String;", "getSession", "()Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Session;", "getSource", "()Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Source;", "getTelemetry", "()Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Telemetry;", "type", "getType", "getVersion", "getView", "()Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$View;", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Action", "Application", "Companion", "Dd", "Session", "Source", "Telemetry", "View", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TelemetryDebugEvent.kt */
public final class TelemetryDebugEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Action action;
    private final Application application;
    private final long date;

    /* renamed from: dd */
    private final C0871Dd f139dd;
    private final String service;
    private final Session session;
    private final Source source;
    private final Telemetry telemetry;
    private final String type;
    private final String version;
    private final View view;

    public static /* synthetic */ TelemetryDebugEvent copy$default(TelemetryDebugEvent telemetryDebugEvent, C0871Dd dd, long j, String str, Source source2, String str2, Application application2, Session session2, View view2, Action action2, Telemetry telemetry2, int i, Object obj) {
        TelemetryDebugEvent telemetryDebugEvent2 = telemetryDebugEvent;
        int i2 = i;
        return telemetryDebugEvent.copy((i2 & 1) != 0 ? telemetryDebugEvent2.f139dd : dd, (i2 & 2) != 0 ? telemetryDebugEvent2.date : j, (i2 & 4) != 0 ? telemetryDebugEvent2.service : str, (i2 & 8) != 0 ? telemetryDebugEvent2.source : source2, (i2 & 16) != 0 ? telemetryDebugEvent2.version : str2, (i2 & 32) != 0 ? telemetryDebugEvent2.application : application2, (i2 & 64) != 0 ? telemetryDebugEvent2.session : session2, (i2 & 128) != 0 ? telemetryDebugEvent2.view : view2, (i2 & 256) != 0 ? telemetryDebugEvent2.action : action2, (i2 & 512) != 0 ? telemetryDebugEvent2.telemetry : telemetry2);
    }

    @JvmStatic
    public static final TelemetryDebugEvent fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final C0871Dd component1() {
        return this.f139dd;
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

    public final TelemetryDebugEvent copy(C0871Dd dd, long j, String str, Source source2, String str2, Application application2, Session session2, View view2, Action action2, Telemetry telemetry2) {
        Intrinsics.checkNotNullParameter(dd, "dd");
        String str3 = str;
        Intrinsics.checkNotNullParameter(str3, "service");
        Source source3 = source2;
        Intrinsics.checkNotNullParameter(source3, "source");
        String str4 = str2;
        Intrinsics.checkNotNullParameter(str4, "version");
        Telemetry telemetry3 = telemetry2;
        Intrinsics.checkNotNullParameter(telemetry3, "telemetry");
        return new TelemetryDebugEvent(dd, j, str3, source3, str4, application2, session2, view2, action2, telemetry3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TelemetryDebugEvent)) {
            return false;
        }
        TelemetryDebugEvent telemetryDebugEvent = (TelemetryDebugEvent) obj;
        return Intrinsics.areEqual((Object) this.f139dd, (Object) telemetryDebugEvent.f139dd) && this.date == telemetryDebugEvent.date && Intrinsics.areEqual((Object) this.service, (Object) telemetryDebugEvent.service) && this.source == telemetryDebugEvent.source && Intrinsics.areEqual((Object) this.version, (Object) telemetryDebugEvent.version) && Intrinsics.areEqual((Object) this.application, (Object) telemetryDebugEvent.application) && Intrinsics.areEqual((Object) this.session, (Object) telemetryDebugEvent.session) && Intrinsics.areEqual((Object) this.view, (Object) telemetryDebugEvent.view) && Intrinsics.areEqual((Object) this.action, (Object) telemetryDebugEvent.action) && Intrinsics.areEqual((Object) this.telemetry, (Object) telemetryDebugEvent.telemetry);
    }

    public int hashCode() {
        int hashCode = ((((((((this.f139dd.hashCode() * 31) + Long.hashCode(this.date)) * 31) + this.service.hashCode()) * 31) + this.source.hashCode()) * 31) + this.version.hashCode()) * 31;
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
        C0871Dd dd = this.f139dd;
        long j = this.date;
        String str = this.service;
        Source source2 = this.source;
        String str2 = this.version;
        Application application2 = this.application;
        Session session2 = this.session;
        View view2 = this.view;
        Action action2 = this.action;
        return "TelemetryDebugEvent(dd=" + dd + ", date=" + j + ", service=" + str + ", source=" + source2 + ", version=" + str2 + ", application=" + application2 + ", session=" + session2 + ", view=" + view2 + ", action=" + action2 + ", telemetry=" + this.telemetry + ")";
    }

    public TelemetryDebugEvent(C0871Dd dd, long j, String str, Source source2, String str2, Application application2, Session session2, View view2, Action action2, Telemetry telemetry2) {
        Intrinsics.checkNotNullParameter(dd, "dd");
        Intrinsics.checkNotNullParameter(str, "service");
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(str2, "version");
        Intrinsics.checkNotNullParameter(telemetry2, "telemetry");
        this.f139dd = dd;
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
    public /* synthetic */ TelemetryDebugEvent(com.datadog.android.telemetry.model.TelemetryDebugEvent.C0871Dd r16, long r17, java.lang.String r19, com.datadog.android.telemetry.model.TelemetryDebugEvent.Source r20, java.lang.String r21, com.datadog.android.telemetry.model.TelemetryDebugEvent.Application r22, com.datadog.android.telemetry.model.TelemetryDebugEvent.Session r23, com.datadog.android.telemetry.model.TelemetryDebugEvent.View r24, com.datadog.android.telemetry.model.TelemetryDebugEvent.Action r25, com.datadog.android.telemetry.model.TelemetryDebugEvent.Telemetry r26, int r27, kotlin.jvm.internal.DefaultConstructorMarker r28) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.telemetry.model.TelemetryDebugEvent.<init>(com.datadog.android.telemetry.model.TelemetryDebugEvent$Dd, long, java.lang.String, com.datadog.android.telemetry.model.TelemetryDebugEvent$Source, java.lang.String, com.datadog.android.telemetry.model.TelemetryDebugEvent$Application, com.datadog.android.telemetry.model.TelemetryDebugEvent$Session, com.datadog.android.telemetry.model.TelemetryDebugEvent$View, com.datadog.android.telemetry.model.TelemetryDebugEvent$Action, com.datadog.android.telemetry.model.TelemetryDebugEvent$Telemetry, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final C0871Dd getDd() {
        return this.f139dd;
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
        jsonObject.add(WebViewRumEventMapper.DD_KEY_NAME, this.f139dd.toJson());
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

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryDebugEvent.kt */
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
        public final com.datadog.android.telemetry.model.TelemetryDebugEvent fromJson(java.lang.String r15) throws com.google.gson.JsonParseException {
            /*
                r14 = this;
                java.lang.String r14 = "version"
                java.lang.String r0 = "it"
                java.lang.String r1 = "service"
                java.lang.String r2 = "serializedObject"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r2)
                com.google.gson.JsonElement r15 = com.google.gson.JsonParser.parseString(r15)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.google.gson.JsonObject r15 = r15.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Dd r3 = new com.datadog.android.telemetry.model.TelemetryDebugEvent$Dd     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r3.<init>()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r2 = "date"
                com.google.gson.JsonElement r2 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                long r4 = r2.getAsLong()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.google.gson.JsonElement r2 = r15.get(r1)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r6 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r2 = "source"
                com.google.gson.JsonElement r2 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Source$Companion r7 = com.datadog.android.telemetry.model.TelemetryDebugEvent.Source.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Source r7 = r7.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
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
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Application$Companion r10 = com.datadog.android.telemetry.model.TelemetryDebugEvent.Application.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Application r2 = r10.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
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
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Session$Companion r11 = com.datadog.android.telemetry.model.TelemetryDebugEvent.Session.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Session r2 = r11.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
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
                com.datadog.android.telemetry.model.TelemetryDebugEvent$View$Companion r12 = com.datadog.android.telemetry.model.TelemetryDebugEvent.View.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$View r2 = r12.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
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
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Action$Companion r9 = com.datadog.android.telemetry.model.TelemetryDebugEvent.Action.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Action r2 = r9.fromJson(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                r13 = r2
            L_0x00a6:
                java.lang.String r2 = "telemetry"
                com.google.gson.JsonElement r15 = r15.get(r2)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                java.lang.String r15 = r15.toString()     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Telemetry$Companion r2 = com.datadog.android.telemetry.model.TelemetryDebugEvent.Telemetry.Companion     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r0)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent$Telemetry r15 = r2.fromJson(r15)     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
                com.datadog.android.telemetry.model.TelemetryDebugEvent r0 = new com.datadog.android.telemetry.model.TelemetryDebugEvent     // Catch:{ IllegalStateException -> 0x00d6, NumberFormatException -> 0x00cb }
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
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.telemetry.model.TelemetryDebugEvent.Companion.fromJson(java.lang.String):com.datadog.android.telemetry.model.TelemetryDebugEvent");
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Dd;", "", "()V", "formatVersion", "", "getFormatVersion", "()J", "toJson", "Lcom/google/gson/JsonElement;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* renamed from: com.datadog.android.telemetry.model.TelemetryDebugEvent$Dd */
    /* compiled from: TelemetryDebugEvent.kt */
    public static final class C0871Dd {
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Application;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryDebugEvent.kt */
    public static final class Application {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f141id;

        public static /* synthetic */ Application copy$default(Application application, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = application.f141id;
            }
            return application.copy(str);
        }

        @JvmStatic
        public static final Application fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f141id;
        }

        public final Application copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Application(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Application) && Intrinsics.areEqual((Object) this.f141id, (Object) ((Application) obj).f141id);
        }

        public int hashCode() {
            return this.f141id.hashCode();
        }

        public String toString() {
            return "Application(id=" + this.f141id + ")";
        }

        public Application(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f141id = str;
        }

        public final String getId() {
            return this.f141id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f141id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Application$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Application;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryDebugEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Session;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryDebugEvent.kt */
    public static final class Session {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f142id;

        public static /* synthetic */ Session copy$default(Session session, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = session.f142id;
            }
            return session.copy(str);
        }

        @JvmStatic
        public static final Session fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f142id;
        }

        public final Session copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Session(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Session) && Intrinsics.areEqual((Object) this.f142id, (Object) ((Session) obj).f142id);
        }

        public int hashCode() {
            return this.f142id.hashCode();
        }

        public String toString() {
            return "Session(id=" + this.f142id + ")";
        }

        public Session(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f142id = str;
        }

        public final String getId() {
            return this.f142id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f142id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Session$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Session;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryDebugEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$View;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryDebugEvent.kt */
    public static final class View {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f143id;

        public static /* synthetic */ View copy$default(View view, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = view.f143id;
            }
            return view.copy(str);
        }

        @JvmStatic
        public static final View fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f143id;
        }

        public final View copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new View(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof View) && Intrinsics.areEqual((Object) this.f143id, (Object) ((View) obj).f143id);
        }

        public int hashCode() {
            return this.f143id.hashCode();
        }

        public String toString() {
            return "View(id=" + this.f143id + ")";
        }

        public View(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f143id = str;
        }

        public final String getId() {
            return this.f143id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f143id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$View$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$View;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryDebugEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Action;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryDebugEvent.kt */
    public static final class Action {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f140id;

        public static /* synthetic */ Action copy$default(Action action, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = action.f140id;
            }
            return action.copy(str);
        }

        @JvmStatic
        public static final Action fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f140id;
        }

        public final Action copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Action(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Action) && Intrinsics.areEqual((Object) this.f140id, (Object) ((Action) obj).f140id);
        }

        public int hashCode() {
            return this.f140id.hashCode();
        }

        public String toString() {
            return "Action(id=" + this.f140id + ")";
        }

        public Action(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f140id = str;
        }

        public final String getId() {
            return this.f140id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f140id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Action$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Action;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryDebugEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0006\u0010\u0010\u001a\u00020\u0011J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0003XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Telemetry;", "", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "status", "getStatus", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryDebugEvent.kt */
    public static final class Telemetry {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String message;
        private final String status = RumEventDeserializer.TELEMETRY_TYPE_DEBUG;

        public static /* synthetic */ Telemetry copy$default(Telemetry telemetry, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = telemetry.message;
            }
            return telemetry.copy(str);
        }

        @JvmStatic
        public static final Telemetry fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.message;
        }

        public final Telemetry copy(String str) {
            Intrinsics.checkNotNullParameter(str, "message");
            return new Telemetry(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Telemetry) && Intrinsics.areEqual((Object) this.message, (Object) ((Telemetry) obj).message);
        }

        public int hashCode() {
            return this.message.hashCode();
        }

        public String toString() {
            return "Telemetry(message=" + this.message + ")";
        }

        public Telemetry(String str) {
            Intrinsics.checkNotNullParameter(str, "message");
            this.message = str;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getStatus() {
            return this.status;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", this.status);
            jsonObject.addProperty("message", this.message);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Telemetry$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Telemetry;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryDebugEvent.kt */
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
                    String asString = JsonParser.parseString(str).getAsJsonObject().get("message").getAsString();
                    Intrinsics.checkNotNullExpressionValue(asString, "message");
                    return new Telemetry(asString);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Source;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "ANDROID", "IOS", "BROWSER", "FLUTTER", "REACT_NATIVE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: TelemetryDebugEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Source$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/telemetry/model/TelemetryDebugEvent$Source;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: TelemetryDebugEvent.kt */
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

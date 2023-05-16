package com.datadog.android.rum.model;

import androidx.core.app.NotificationCompat;
import androidx.core.p003os.EnvironmentCompat;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.utils.MiscUtilsKt;
import com.datadog.android.log.LogAttributes;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.webview.internal.rum.WebViewRumEventMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\b\u0018\u0000 X2\u00020\u0001:\u0013TUVWXYZ[\\]^_`abcdefB\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0002\u0010\u001eJ\t\u0010=\u001a\u00020\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\t\u0010?\u001a\u00020\u0017HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0019HÆ\u0003J\t\u0010A\u001a\u00020\u001bHÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u001dHÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010E\u001a\u00020\tHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010G\u001a\u00020\rHÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0013HÆ\u0003J¥\u0001\u0010K\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÆ\u0001J\u0013\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010O\u001a\u00020PHÖ\u0001J\u0006\u0010Q\u001a\u00020RJ\t\u0010S\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b8\u00100R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<¨\u0006g"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent;", "", "date", "", "application", "Lcom/datadog/android/rum/model/LongTaskEvent$Application;", "service", "", "session", "Lcom/datadog/android/rum/model/LongTaskEvent$LongTaskEventSession;", "source", "Lcom/datadog/android/rum/model/LongTaskEvent$Source;", "view", "Lcom/datadog/android/rum/model/LongTaskEvent$View;", "usr", "Lcom/datadog/android/rum/model/LongTaskEvent$Usr;", "connectivity", "Lcom/datadog/android/rum/model/LongTaskEvent$Connectivity;", "synthetics", "Lcom/datadog/android/rum/model/LongTaskEvent$Synthetics;", "ciTest", "Lcom/datadog/android/rum/model/LongTaskEvent$CiTest;", "dd", "Lcom/datadog/android/rum/model/LongTaskEvent$Dd;", "context", "Lcom/datadog/android/rum/model/LongTaskEvent$Context;", "longTask", "Lcom/datadog/android/rum/model/LongTaskEvent$LongTask;", "action", "Lcom/datadog/android/rum/model/LongTaskEvent$Action;", "(JLcom/datadog/android/rum/model/LongTaskEvent$Application;Ljava/lang/String;Lcom/datadog/android/rum/model/LongTaskEvent$LongTaskEventSession;Lcom/datadog/android/rum/model/LongTaskEvent$Source;Lcom/datadog/android/rum/model/LongTaskEvent$View;Lcom/datadog/android/rum/model/LongTaskEvent$Usr;Lcom/datadog/android/rum/model/LongTaskEvent$Connectivity;Lcom/datadog/android/rum/model/LongTaskEvent$Synthetics;Lcom/datadog/android/rum/model/LongTaskEvent$CiTest;Lcom/datadog/android/rum/model/LongTaskEvent$Dd;Lcom/datadog/android/rum/model/LongTaskEvent$Context;Lcom/datadog/android/rum/model/LongTaskEvent$LongTask;Lcom/datadog/android/rum/model/LongTaskEvent$Action;)V", "getAction", "()Lcom/datadog/android/rum/model/LongTaskEvent$Action;", "getApplication", "()Lcom/datadog/android/rum/model/LongTaskEvent$Application;", "getCiTest", "()Lcom/datadog/android/rum/model/LongTaskEvent$CiTest;", "getConnectivity", "()Lcom/datadog/android/rum/model/LongTaskEvent$Connectivity;", "getContext", "()Lcom/datadog/android/rum/model/LongTaskEvent$Context;", "getDate", "()J", "getDd", "()Lcom/datadog/android/rum/model/LongTaskEvent$Dd;", "getLongTask", "()Lcom/datadog/android/rum/model/LongTaskEvent$LongTask;", "getService", "()Ljava/lang/String;", "getSession", "()Lcom/datadog/android/rum/model/LongTaskEvent$LongTaskEventSession;", "getSource", "()Lcom/datadog/android/rum/model/LongTaskEvent$Source;", "getSynthetics", "()Lcom/datadog/android/rum/model/LongTaskEvent$Synthetics;", "type", "getType", "getUsr", "()Lcom/datadog/android/rum/model/LongTaskEvent$Usr;", "getView", "()Lcom/datadog/android/rum/model/LongTaskEvent$View;", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Action", "Application", "Cellular", "CiTest", "Companion", "Connectivity", "Context", "Dd", "DdSession", "Interface", "LongTask", "LongTaskEventSession", "Plan", "Source", "Status", "Synthetics", "Type", "Usr", "View", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LongTaskEvent.kt */
public final class LongTaskEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Action action;
    private final Application application;
    private final CiTest ciTest;
    private final Connectivity connectivity;
    private final Context context;
    private final long date;

    /* renamed from: dd */
    private final C0865Dd f115dd;
    private final LongTask longTask;
    private final String service;
    private final LongTaskEventSession session;
    private final Source source;
    private final Synthetics synthetics;
    private final String type;
    private final Usr usr;
    private final View view;

    public static /* synthetic */ LongTaskEvent copy$default(LongTaskEvent longTaskEvent, long j, Application application2, String str, LongTaskEventSession longTaskEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0865Dd dd, Context context2, LongTask longTask2, Action action2, int i, Object obj) {
        LongTaskEvent longTaskEvent2 = longTaskEvent;
        int i2 = i;
        return longTaskEvent.copy((i2 & 1) != 0 ? longTaskEvent2.date : j, (i2 & 2) != 0 ? longTaskEvent2.application : application2, (i2 & 4) != 0 ? longTaskEvent2.service : str, (i2 & 8) != 0 ? longTaskEvent2.session : longTaskEventSession, (i2 & 16) != 0 ? longTaskEvent2.source : source2, (i2 & 32) != 0 ? longTaskEvent2.view : view2, (i2 & 64) != 0 ? longTaskEvent2.usr : usr2, (i2 & 128) != 0 ? longTaskEvent2.connectivity : connectivity2, (i2 & 256) != 0 ? longTaskEvent2.synthetics : synthetics2, (i2 & 512) != 0 ? longTaskEvent2.ciTest : ciTest2, (i2 & 1024) != 0 ? longTaskEvent2.f115dd : dd, (i2 & 2048) != 0 ? longTaskEvent2.context : context2, (i2 & 4096) != 0 ? longTaskEvent2.longTask : longTask2, (i2 & 8192) != 0 ? longTaskEvent2.action : action2);
    }

    @JvmStatic
    public static final LongTaskEvent fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final long component1() {
        return this.date;
    }

    public final CiTest component10() {
        return this.ciTest;
    }

    public final C0865Dd component11() {
        return this.f115dd;
    }

    public final Context component12() {
        return this.context;
    }

    public final LongTask component13() {
        return this.longTask;
    }

    public final Action component14() {
        return this.action;
    }

    public final Application component2() {
        return this.application;
    }

    public final String component3() {
        return this.service;
    }

    public final LongTaskEventSession component4() {
        return this.session;
    }

    public final Source component5() {
        return this.source;
    }

    public final View component6() {
        return this.view;
    }

    public final Usr component7() {
        return this.usr;
    }

    public final Connectivity component8() {
        return this.connectivity;
    }

    public final Synthetics component9() {
        return this.synthetics;
    }

    public final LongTaskEvent copy(long j, Application application2, String str, LongTaskEventSession longTaskEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0865Dd dd, Context context2, LongTask longTask2, Action action2) {
        Application application3 = application2;
        Intrinsics.checkNotNullParameter(application3, WebViewRumEventMapper.APPLICATION_KEY_NAME);
        LongTaskEventSession longTaskEventSession2 = longTaskEventSession;
        Intrinsics.checkNotNullParameter(longTaskEventSession2, "session");
        View view3 = view2;
        Intrinsics.checkNotNullParameter(view3, "view");
        C0865Dd dd2 = dd;
        Intrinsics.checkNotNullParameter(dd2, "dd");
        LongTask longTask3 = longTask2;
        Intrinsics.checkNotNullParameter(longTask3, "longTask");
        return new LongTaskEvent(j, application3, str, longTaskEventSession2, source2, view3, usr2, connectivity2, synthetics2, ciTest2, dd2, context2, longTask3, action2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LongTaskEvent)) {
            return false;
        }
        LongTaskEvent longTaskEvent = (LongTaskEvent) obj;
        return this.date == longTaskEvent.date && Intrinsics.areEqual((Object) this.application, (Object) longTaskEvent.application) && Intrinsics.areEqual((Object) this.service, (Object) longTaskEvent.service) && Intrinsics.areEqual((Object) this.session, (Object) longTaskEvent.session) && this.source == longTaskEvent.source && Intrinsics.areEqual((Object) this.view, (Object) longTaskEvent.view) && Intrinsics.areEqual((Object) this.usr, (Object) longTaskEvent.usr) && Intrinsics.areEqual((Object) this.connectivity, (Object) longTaskEvent.connectivity) && Intrinsics.areEqual((Object) this.synthetics, (Object) longTaskEvent.synthetics) && Intrinsics.areEqual((Object) this.ciTest, (Object) longTaskEvent.ciTest) && Intrinsics.areEqual((Object) this.f115dd, (Object) longTaskEvent.f115dd) && Intrinsics.areEqual((Object) this.context, (Object) longTaskEvent.context) && Intrinsics.areEqual((Object) this.longTask, (Object) longTaskEvent.longTask) && Intrinsics.areEqual((Object) this.action, (Object) longTaskEvent.action);
    }

    public int hashCode() {
        int hashCode = ((Long.hashCode(this.date) * 31) + this.application.hashCode()) * 31;
        String str = this.service;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.session.hashCode()) * 31;
        Source source2 = this.source;
        int hashCode3 = (((hashCode2 + (source2 == null ? 0 : source2.hashCode())) * 31) + this.view.hashCode()) * 31;
        Usr usr2 = this.usr;
        int hashCode4 = (hashCode3 + (usr2 == null ? 0 : usr2.hashCode())) * 31;
        Connectivity connectivity2 = this.connectivity;
        int hashCode5 = (hashCode4 + (connectivity2 == null ? 0 : connectivity2.hashCode())) * 31;
        Synthetics synthetics2 = this.synthetics;
        int hashCode6 = (hashCode5 + (synthetics2 == null ? 0 : synthetics2.hashCode())) * 31;
        CiTest ciTest2 = this.ciTest;
        int hashCode7 = (((hashCode6 + (ciTest2 == null ? 0 : ciTest2.hashCode())) * 31) + this.f115dd.hashCode()) * 31;
        Context context2 = this.context;
        int hashCode8 = (((hashCode7 + (context2 == null ? 0 : context2.hashCode())) * 31) + this.longTask.hashCode()) * 31;
        Action action2 = this.action;
        if (action2 != null) {
            i = action2.hashCode();
        }
        return hashCode8 + i;
    }

    public String toString() {
        long j = this.date;
        Application application2 = this.application;
        String str = this.service;
        LongTaskEventSession longTaskEventSession = this.session;
        Source source2 = this.source;
        View view2 = this.view;
        Usr usr2 = this.usr;
        Connectivity connectivity2 = this.connectivity;
        Synthetics synthetics2 = this.synthetics;
        CiTest ciTest2 = this.ciTest;
        C0865Dd dd = this.f115dd;
        Context context2 = this.context;
        LongTask longTask2 = this.longTask;
        return "LongTaskEvent(date=" + j + ", application=" + application2 + ", service=" + str + ", session=" + longTaskEventSession + ", source=" + source2 + ", view=" + view2 + ", usr=" + usr2 + ", connectivity=" + connectivity2 + ", synthetics=" + synthetics2 + ", ciTest=" + ciTest2 + ", dd=" + dd + ", context=" + context2 + ", longTask=" + longTask2 + ", action=" + this.action + ")";
    }

    public LongTaskEvent(long j, Application application2, String str, LongTaskEventSession longTaskEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0865Dd dd, Context context2, LongTask longTask2, Action action2) {
        C0865Dd dd2 = dd;
        LongTask longTask3 = longTask2;
        Intrinsics.checkNotNullParameter(application2, WebViewRumEventMapper.APPLICATION_KEY_NAME);
        Intrinsics.checkNotNullParameter(longTaskEventSession, "session");
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(dd2, "dd");
        Intrinsics.checkNotNullParameter(longTask3, "longTask");
        this.date = j;
        this.application = application2;
        this.service = str;
        this.session = longTaskEventSession;
        this.source = source2;
        this.view = view2;
        this.usr = usr2;
        this.connectivity = connectivity2;
        this.synthetics = synthetics2;
        this.ciTest = ciTest2;
        this.f115dd = dd2;
        this.context = context2;
        this.longTask = longTask3;
        this.action = action2;
        this.type = "long_task";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ LongTaskEvent(long r20, com.datadog.android.rum.model.LongTaskEvent.Application r22, java.lang.String r23, com.datadog.android.rum.model.LongTaskEvent.LongTaskEventSession r24, com.datadog.android.rum.model.LongTaskEvent.Source r25, com.datadog.android.rum.model.LongTaskEvent.View r26, com.datadog.android.rum.model.LongTaskEvent.Usr r27, com.datadog.android.rum.model.LongTaskEvent.Connectivity r28, com.datadog.android.rum.model.LongTaskEvent.Synthetics r29, com.datadog.android.rum.model.LongTaskEvent.CiTest r30, com.datadog.android.rum.model.LongTaskEvent.C0865Dd r31, com.datadog.android.rum.model.LongTaskEvent.Context r32, com.datadog.android.rum.model.LongTaskEvent.LongTask r33, com.datadog.android.rum.model.LongTaskEvent.Action r34, int r35, kotlin.jvm.internal.DefaultConstructorMarker r36) {
        /*
            r19 = this;
            r0 = r35
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r7 = r2
            goto L_0x000b
        L_0x0009:
            r7 = r23
        L_0x000b:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0011
            r9 = r2
            goto L_0x0013
        L_0x0011:
            r9 = r25
        L_0x0013:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0019
            r11 = r2
            goto L_0x001b
        L_0x0019:
            r11 = r27
        L_0x001b:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0021
            r12 = r2
            goto L_0x0023
        L_0x0021:
            r12 = r28
        L_0x0023:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0029
            r13 = r2
            goto L_0x002b
        L_0x0029:
            r13 = r29
        L_0x002b:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0031
            r14 = r2
            goto L_0x0033
        L_0x0031:
            r14 = r30
        L_0x0033:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x003a
            r16 = r2
            goto L_0x003c
        L_0x003a:
            r16 = r32
        L_0x003c:
            r0 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r0 == 0) goto L_0x0043
            r18 = r2
            goto L_0x0045
        L_0x0043:
            r18 = r34
        L_0x0045:
            r3 = r19
            r4 = r20
            r6 = r22
            r8 = r24
            r10 = r26
            r15 = r31
            r17 = r33
            r3.<init>(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.LongTaskEvent.<init>(long, com.datadog.android.rum.model.LongTaskEvent$Application, java.lang.String, com.datadog.android.rum.model.LongTaskEvent$LongTaskEventSession, com.datadog.android.rum.model.LongTaskEvent$Source, com.datadog.android.rum.model.LongTaskEvent$View, com.datadog.android.rum.model.LongTaskEvent$Usr, com.datadog.android.rum.model.LongTaskEvent$Connectivity, com.datadog.android.rum.model.LongTaskEvent$Synthetics, com.datadog.android.rum.model.LongTaskEvent$CiTest, com.datadog.android.rum.model.LongTaskEvent$Dd, com.datadog.android.rum.model.LongTaskEvent$Context, com.datadog.android.rum.model.LongTaskEvent$LongTask, com.datadog.android.rum.model.LongTaskEvent$Action, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final long getDate() {
        return this.date;
    }

    public final Application getApplication() {
        return this.application;
    }

    public final String getService() {
        return this.service;
    }

    public final LongTaskEventSession getSession() {
        return this.session;
    }

    public final Source getSource() {
        return this.source;
    }

    public final View getView() {
        return this.view;
    }

    public final Usr getUsr() {
        return this.usr;
    }

    public final Connectivity getConnectivity() {
        return this.connectivity;
    }

    public final Synthetics getSynthetics() {
        return this.synthetics;
    }

    public final CiTest getCiTest() {
        return this.ciTest;
    }

    public final C0865Dd getDd() {
        return this.f115dd;
    }

    public final Context getContext() {
        return this.context;
    }

    public final LongTask getLongTask() {
        return this.longTask;
    }

    public final Action getAction() {
        return this.action;
    }

    public final String getType() {
        return this.type;
    }

    public final JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", (Number) Long.valueOf(this.date));
        jsonObject.add(WebViewRumEventMapper.APPLICATION_KEY_NAME, this.application.toJson());
        String str = this.service;
        if (str != null) {
            jsonObject.addProperty("service", str);
        }
        jsonObject.add("session", this.session.toJson());
        Source source2 = this.source;
        if (source2 != null) {
            jsonObject.add("source", source2.toJson());
        }
        jsonObject.add("view", this.view.toJson());
        Usr usr2 = this.usr;
        if (usr2 != null) {
            jsonObject.add("usr", usr2.toJson());
        }
        Connectivity connectivity2 = this.connectivity;
        if (connectivity2 != null) {
            jsonObject.add("connectivity", connectivity2.toJson());
        }
        Synthetics synthetics2 = this.synthetics;
        if (synthetics2 != null) {
            jsonObject.add("synthetics", synthetics2.toJson());
        }
        CiTest ciTest2 = this.ciTest;
        if (ciTest2 != null) {
            jsonObject.add("ci_test", ciTest2.toJson());
        }
        jsonObject.add(WebViewRumEventMapper.DD_KEY_NAME, this.f115dd.toJson());
        Context context2 = this.context;
        if (context2 != null) {
            jsonObject.add(RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX, context2.toJson());
        }
        jsonObject.addProperty(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type);
        jsonObject.add("long_task", this.longTask.toJson());
        Action action2 = this.action;
        if (action2 != null) {
            jsonObject.add("action", action2.toJson());
        }
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0085 A[Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x009d A[Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }] */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00b5 A[Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00cd A[Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x00f9 A[Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }] */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0126 A[Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }] */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.datadog.android.rum.model.LongTaskEvent fromJson(java.lang.String r20) throws com.google.gson.JsonParseException {
            /*
                r19 = this;
                java.lang.String r0 = "it"
                java.lang.String r1 = "serializedObject"
                r2 = r20
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                com.google.gson.JsonElement r1 = com.google.gson.JsonParser.parseString(r20)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.google.gson.JsonObject r1 = r1.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = "date"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                long r4 = r2.getAsLong()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = "application"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Application$Companion r3 = com.datadog.android.rum.model.LongTaskEvent.Application.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Application r6 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = "service"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x0038
                r7 = 0
                goto L_0x003d
            L_0x0038:
                java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r7 = r2
            L_0x003d:
                java.lang.String r2 = "session"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$LongTaskEventSession$Companion r8 = com.datadog.android.rum.model.LongTaskEvent.LongTaskEventSession.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$LongTaskEventSession r8 = r8.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = "source"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x005a
            L_0x0058:
                r9 = 0
                goto L_0x0068
            L_0x005a:
                java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x0061
                goto L_0x0058
            L_0x0061:
                com.datadog.android.rum.model.LongTaskEvent$Source$Companion r9 = com.datadog.android.rum.model.LongTaskEvent.Source.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Source r2 = r9.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r9 = r2
            L_0x0068:
                java.lang.String r2 = "view"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$View$Companion r10 = com.datadog.android.rum.model.LongTaskEvent.View.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$View r10 = r10.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = "usr"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x0085
            L_0x0083:
                r11 = 0
                goto L_0x0093
            L_0x0085:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x008c
                goto L_0x0083
            L_0x008c:
                com.datadog.android.rum.model.LongTaskEvent$Usr$Companion r11 = com.datadog.android.rum.model.LongTaskEvent.Usr.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Usr r2 = r11.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r11 = r2
            L_0x0093:
                java.lang.String r2 = "connectivity"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x009d
            L_0x009b:
                r12 = 0
                goto L_0x00ab
            L_0x009d:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x00a4
                goto L_0x009b
            L_0x00a4:
                com.datadog.android.rum.model.LongTaskEvent$Connectivity$Companion r12 = com.datadog.android.rum.model.LongTaskEvent.Connectivity.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Connectivity r2 = r12.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r12 = r2
            L_0x00ab:
                java.lang.String r2 = "synthetics"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x00b5
            L_0x00b3:
                r13 = 0
                goto L_0x00c3
            L_0x00b5:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x00bc
                goto L_0x00b3
            L_0x00bc:
                com.datadog.android.rum.model.LongTaskEvent$Synthetics$Companion r13 = com.datadog.android.rum.model.LongTaskEvent.Synthetics.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Synthetics r2 = r13.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r13 = r2
            L_0x00c3:
                java.lang.String r2 = "ci_test"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x00cd
            L_0x00cb:
                r14 = 0
                goto L_0x00db
            L_0x00cd:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x00d4
                goto L_0x00cb
            L_0x00d4:
                com.datadog.android.rum.model.LongTaskEvent$CiTest$Companion r14 = com.datadog.android.rum.model.LongTaskEvent.CiTest.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$CiTest r2 = r14.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r14 = r2
            L_0x00db:
                java.lang.String r2 = "_dd"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Dd$Companion r15 = com.datadog.android.rum.model.LongTaskEvent.C0865Dd.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Dd r15 = r15.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = "context"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x00f9
            L_0x00f6:
                r16 = 0
                goto L_0x0108
            L_0x00f9:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r2 != 0) goto L_0x0100
                goto L_0x00f6
            L_0x0100:
                com.datadog.android.rum.model.LongTaskEvent$Context$Companion r3 = com.datadog.android.rum.model.LongTaskEvent.Context.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Context r2 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r16 = r2
            L_0x0108:
                java.lang.String r2 = "long_task"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$LongTask$Companion r3 = com.datadog.android.rum.model.LongTaskEvent.LongTask.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$LongTask r17 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r0 = "action"
                com.google.gson.JsonElement r0 = r1.get(r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r0 != 0) goto L_0x0126
            L_0x0123:
                r18 = 0
                goto L_0x0135
            L_0x0126:
                java.lang.String r0 = r0.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                if (r0 != 0) goto L_0x012d
                goto L_0x0123
            L_0x012d:
                com.datadog.android.rum.model.LongTaskEvent$Action$Companion r1 = com.datadog.android.rum.model.LongTaskEvent.Action.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.LongTaskEvent$Action r0 = r1.fromJson(r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r18 = r0
            L_0x0135:
                com.datadog.android.rum.model.LongTaskEvent r0 = new com.datadog.android.rum.model.LongTaskEvent     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r3 = r0
                r3.<init>(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                return r0
            L_0x013c:
                r0 = move-exception
                com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                java.lang.String r0 = r0.getMessage()
                r1.<init>((java.lang.String) r0)
                throw r1
            L_0x0147:
                r0 = move-exception
                com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                java.lang.String r0 = r0.getMessage()
                r1.<init>((java.lang.String) r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.LongTaskEvent.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.LongTaskEvent");
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Application;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class Application {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f117id;

        public static /* synthetic */ Application copy$default(Application application, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = application.f117id;
            }
            return application.copy(str);
        }

        @JvmStatic
        public static final Application fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f117id;
        }

        public final Application copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Application(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Application) && Intrinsics.areEqual((Object) this.f117id, (Object) ((Application) obj).f117id);
        }

        public int hashCode() {
            return this.f117id.hashCode();
        }

        public String toString() {
            return "Application(id=" + this.f117id + ")";
        }

        public Application(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f117id = str;
        }

        public final String getId() {
            return this.f117id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f117id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Application$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Application;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\nJ.\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$LongTaskEventSession;", "", "id", "", "type", "Lcom/datadog/android/rum/model/LongTaskEvent$Type;", "hasReplay", "", "(Ljava/lang/String;Lcom/datadog/android/rum/model/LongTaskEvent$Type;Ljava/lang/Boolean;)V", "getHasReplay", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getId", "()Ljava/lang/String;", "getType", "()Lcom/datadog/android/rum/model/LongTaskEvent$Type;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Lcom/datadog/android/rum/model/LongTaskEvent$Type;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/LongTaskEvent$LongTaskEventSession;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class LongTaskEventSession {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Boolean hasReplay;

        /* renamed from: id */
        private final String f119id;
        private final Type type;

        public static /* synthetic */ LongTaskEventSession copy$default(LongTaskEventSession longTaskEventSession, String str, Type type2, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = longTaskEventSession.f119id;
            }
            if ((i & 2) != 0) {
                type2 = longTaskEventSession.type;
            }
            if ((i & 4) != 0) {
                bool = longTaskEventSession.hasReplay;
            }
            return longTaskEventSession.copy(str, type2, bool);
        }

        @JvmStatic
        public static final LongTaskEventSession fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f119id;
        }

        public final Type component2() {
            return this.type;
        }

        public final Boolean component3() {
            return this.hasReplay;
        }

        public final LongTaskEventSession copy(String str, Type type2, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(type2, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            return new LongTaskEventSession(str, type2, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LongTaskEventSession)) {
                return false;
            }
            LongTaskEventSession longTaskEventSession = (LongTaskEventSession) obj;
            return Intrinsics.areEqual((Object) this.f119id, (Object) longTaskEventSession.f119id) && this.type == longTaskEventSession.type && Intrinsics.areEqual((Object) this.hasReplay, (Object) longTaskEventSession.hasReplay);
        }

        public int hashCode() {
            int hashCode = ((this.f119id.hashCode() * 31) + this.type.hashCode()) * 31;
            Boolean bool = this.hasReplay;
            return hashCode + (bool == null ? 0 : bool.hashCode());
        }

        public String toString() {
            String str = this.f119id;
            Type type2 = this.type;
            return "LongTaskEventSession(id=" + str + ", type=" + type2 + ", hasReplay=" + this.hasReplay + ")";
        }

        public LongTaskEventSession(String str, Type type2, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(type2, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            this.f119id = str;
            this.type = type2;
            this.hasReplay = bool;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LongTaskEventSession(String str, Type type2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, type2, (i & 4) != 0 ? null : bool);
        }

        public final String getId() {
            return this.f119id;
        }

        public final Type getType() {
            return this.type;
        }

        public final Boolean getHasReplay() {
            return this.hasReplay;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f119id);
            jsonObject.add(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type.toJson());
            Boolean bool = this.hasReplay;
            if (bool != null) {
                jsonObject.addProperty("has_replay", Boolean.valueOf(bool.booleanValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$LongTaskEventSession$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$LongTaskEventSession;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final LongTaskEventSession fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    String asString = asJsonObject.get("id").getAsString();
                    String asString2 = asJsonObject.get(RumEventDeserializer.EVENT_TYPE_KEY_NAME).getAsString();
                    Type.Companion companion = Type.Companion;
                    Intrinsics.checkNotNullExpressionValue(asString2, "it");
                    Type fromJson = companion.fromJson(asString2);
                    JsonElement jsonElement = asJsonObject.get("has_replay");
                    Boolean valueOf = jsonElement == null ? null : Boolean.valueOf(jsonElement.getAsBoolean());
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    return new LongTaskEventSession(asString, fromJson, valueOf);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\t\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\f¨\u0006\u001f"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$View;", "", "id", "", "referrer", "url", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "setName", "(Ljava/lang/String;)V", "getReferrer", "setReferrer", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class View {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f121id;
        private String name;
        private String referrer;
        private String url;

        public static /* synthetic */ View copy$default(View view, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = view.f121id;
            }
            if ((i & 2) != 0) {
                str2 = view.referrer;
            }
            if ((i & 4) != 0) {
                str3 = view.url;
            }
            if ((i & 8) != 0) {
                str4 = view.name;
            }
            return view.copy(str, str2, str3, str4);
        }

        @JvmStatic
        public static final View fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f121id;
        }

        public final String component2() {
            return this.referrer;
        }

        public final String component3() {
            return this.url;
        }

        public final String component4() {
            return this.name;
        }

        public final View copy(String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str3, "url");
            return new View(str, str2, str3, str4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof View)) {
                return false;
            }
            View view = (View) obj;
            return Intrinsics.areEqual((Object) this.f121id, (Object) view.f121id) && Intrinsics.areEqual((Object) this.referrer, (Object) view.referrer) && Intrinsics.areEqual((Object) this.url, (Object) view.url) && Intrinsics.areEqual((Object) this.name, (Object) view.name);
        }

        public int hashCode() {
            int hashCode = this.f121id.hashCode() * 31;
            String str = this.referrer;
            int i = 0;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.url.hashCode()) * 31;
            String str2 = this.name;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            String str = this.f121id;
            String str2 = this.referrer;
            String str3 = this.url;
            return "View(id=" + str + ", referrer=" + str2 + ", url=" + str3 + ", name=" + this.name + ")";
        }

        public View(String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str3, "url");
            this.f121id = str;
            this.referrer = str2;
            this.url = str3;
            this.name = str4;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ View(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2, str3, (i & 8) != 0 ? null : str4);
        }

        public final String getId() {
            return this.f121id;
        }

        public final String getReferrer() {
            return this.referrer;
        }

        public final void setReferrer(String str) {
            this.referrer = str;
        }

        public final String getUrl() {
            return this.url;
        }

        public final void setUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.url = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f121id);
            String str = this.referrer;
            if (str != null) {
                jsonObject.addProperty("referrer", str);
            }
            jsonObject.addProperty("url", this.url);
            String str2 = this.name;
            if (str2 != null) {
                jsonObject.addProperty("name", str2);
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$View$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$View;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
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
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    String asString = asJsonObject.get("id").getAsString();
                    JsonElement jsonElement = asJsonObject.get("referrer");
                    String str2 = null;
                    String asString2 = jsonElement == null ? null : jsonElement.getAsString();
                    String asString3 = asJsonObject.get("url").getAsString();
                    JsonElement jsonElement2 = asJsonObject.get("name");
                    if (jsonElement2 != null) {
                        str2 = jsonElement2.getAsString();
                    }
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    Intrinsics.checkNotNullExpressionValue(asString3, "url");
                    return new View(asString, asString2, asString3, str2);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0003JE\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Usr;", "", "id", "", "name", "email", "additionalProperties", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "getEmail", "()Ljava/lang/String;", "getId", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class Usr {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final String[] RESERVED_PROPERTIES = {"id", "name", NotificationCompat.CATEGORY_EMAIL};
        private final Map<String, Object> additionalProperties;
        private final String email;

        /* renamed from: id */
        private final String f120id;
        private final String name;

        public Usr() {
            this((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Usr copy$default(Usr usr, String str, String str2, String str3, Map<String, Object> map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = usr.f120id;
            }
            if ((i & 2) != 0) {
                str2 = usr.name;
            }
            if ((i & 4) != 0) {
                str3 = usr.email;
            }
            if ((i & 8) != 0) {
                map = usr.additionalProperties;
            }
            return usr.copy(str, str2, str3, map);
        }

        @JvmStatic
        public static final Usr fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f120id;
        }

        public final String component2() {
            return this.name;
        }

        public final String component3() {
            return this.email;
        }

        public final Map<String, Object> component4() {
            return this.additionalProperties;
        }

        public final Usr copy(String str, String str2, String str3, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            return new Usr(str, str2, str3, map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Usr)) {
                return false;
            }
            Usr usr = (Usr) obj;
            return Intrinsics.areEqual((Object) this.f120id, (Object) usr.f120id) && Intrinsics.areEqual((Object) this.name, (Object) usr.name) && Intrinsics.areEqual((Object) this.email, (Object) usr.email) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) usr.additionalProperties);
        }

        public int hashCode() {
            String str = this.f120id;
            int i = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.name;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.email;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return ((hashCode2 + i) * 31) + this.additionalProperties.hashCode();
        }

        public String toString() {
            String str = this.f120id;
            String str2 = this.name;
            String str3 = this.email;
            return "Usr(id=" + str + ", name=" + str2 + ", email=" + str3 + ", additionalProperties=" + this.additionalProperties + ")";
        }

        public Usr(String str, String str2, String str3, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.f120id = str;
            this.name = str2;
            this.email = str3;
            this.additionalProperties = map;
        }

        public final String getId() {
            return this.f120id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Usr(String str, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.f120id;
            if (str != null) {
                jsonObject.addProperty("id", str);
            }
            String str2 = this.name;
            if (str2 != null) {
                jsonObject.addProperty("name", str2);
            }
            String str3 = this.email;
            if (str3 != null) {
                jsonObject.addProperty(NotificationCompat.CATEGORY_EMAIL, str3);
            }
            for (Map.Entry next : this.additionalProperties.entrySet()) {
                String str4 = (String) next.getKey();
                Object value = next.getValue();
                if (!ArraysKt.contains((T[]) RESERVED_PROPERTIES, str4)) {
                    jsonObject.add(str4, MiscUtilsKt.toJsonElement(value));
                }
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Usr$Companion;", "", "()V", "RESERVED_PROPERTIES", "", "", "getRESERVED_PROPERTIES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Usr;", "serializedObject", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final String[] getRESERVED_PROPERTIES$dd_sdk_android_release() {
                return Usr.RESERVED_PROPERTIES;
            }

            @JvmStatic
            public final Usr fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    JsonElement jsonElement = asJsonObject.get("id");
                    String str2 = null;
                    String asString = jsonElement == null ? null : jsonElement.getAsString();
                    JsonElement jsonElement2 = asJsonObject.get("name");
                    String asString2 = jsonElement2 == null ? null : jsonElement2.getAsString();
                    JsonElement jsonElement3 = asJsonObject.get(NotificationCompat.CATEGORY_EMAIL);
                    if (jsonElement3 != null) {
                        str2 = jsonElement3.getAsString();
                    }
                    Map linkedHashMap = new LinkedHashMap();
                    for (Map.Entry next : asJsonObject.entrySet()) {
                        if (!ArraysKt.contains((T[]) getRESERVED_PROPERTIES$dd_sdk_android_release(), next.getKey())) {
                            Object key = next.getKey();
                            Intrinsics.checkNotNullExpressionValue(key, "entry.key");
                            linkedHashMap.put(key, next.getValue());
                        }
                    }
                    return new Usr(asString, asString2, str2, linkedHashMap);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Connectivity;", "", "status", "Lcom/datadog/android/rum/model/LongTaskEvent$Status;", "interfaces", "", "Lcom/datadog/android/rum/model/LongTaskEvent$Interface;", "cellular", "Lcom/datadog/android/rum/model/LongTaskEvent$Cellular;", "(Lcom/datadog/android/rum/model/LongTaskEvent$Status;Ljava/util/List;Lcom/datadog/android/rum/model/LongTaskEvent$Cellular;)V", "getCellular", "()Lcom/datadog/android/rum/model/LongTaskEvent$Cellular;", "getInterfaces", "()Ljava/util/List;", "getStatus", "()Lcom/datadog/android/rum/model/LongTaskEvent$Status;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class Connectivity {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Cellular cellular;
        private final List<Interface> interfaces;
        private final Status status;

        public static /* synthetic */ Connectivity copy$default(Connectivity connectivity, Status status2, List<Interface> list, Cellular cellular2, int i, Object obj) {
            if ((i & 1) != 0) {
                status2 = connectivity.status;
            }
            if ((i & 2) != 0) {
                list = connectivity.interfaces;
            }
            if ((i & 4) != 0) {
                cellular2 = connectivity.cellular;
            }
            return connectivity.copy(status2, list, cellular2);
        }

        @JvmStatic
        public static final Connectivity fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final Status component1() {
            return this.status;
        }

        public final List<Interface> component2() {
            return this.interfaces;
        }

        public final Cellular component3() {
            return this.cellular;
        }

        public final Connectivity copy(Status status2, List<? extends Interface> list, Cellular cellular2) {
            Intrinsics.checkNotNullParameter(status2, "status");
            Intrinsics.checkNotNullParameter(list, "interfaces");
            return new Connectivity(status2, list, cellular2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Connectivity)) {
                return false;
            }
            Connectivity connectivity = (Connectivity) obj;
            return this.status == connectivity.status && Intrinsics.areEqual((Object) this.interfaces, (Object) connectivity.interfaces) && Intrinsics.areEqual((Object) this.cellular, (Object) connectivity.cellular);
        }

        public int hashCode() {
            int hashCode = ((this.status.hashCode() * 31) + this.interfaces.hashCode()) * 31;
            Cellular cellular2 = this.cellular;
            return hashCode + (cellular2 == null ? 0 : cellular2.hashCode());
        }

        public String toString() {
            Status status2 = this.status;
            List<Interface> list = this.interfaces;
            return "Connectivity(status=" + status2 + ", interfaces=" + list + ", cellular=" + this.cellular + ")";
        }

        public Connectivity(Status status2, List<? extends Interface> list, Cellular cellular2) {
            Intrinsics.checkNotNullParameter(status2, "status");
            Intrinsics.checkNotNullParameter(list, "interfaces");
            this.status = status2;
            this.interfaces = list;
            this.cellular = cellular2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Connectivity(Status status2, List list, Cellular cellular2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(status2, list, (i & 4) != 0 ? null : cellular2);
        }

        public final Status getStatus() {
            return this.status;
        }

        public final List<Interface> getInterfaces() {
            return this.interfaces;
        }

        public final Cellular getCellular() {
            return this.cellular;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("status", this.status.toJson());
            JsonArray jsonArray = new JsonArray(this.interfaces.size());
            for (Interface json : this.interfaces) {
                jsonArray.add(json.toJson());
            }
            jsonObject.add("interfaces", jsonArray);
            Cellular cellular2 = this.cellular;
            if (cellular2 != null) {
                jsonObject.add("cellular", cellular2.toJson());
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Connectivity$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Connectivity;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Connectivity fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    String asString = asJsonObject.get("status").getAsString();
                    Status.Companion companion = Status.Companion;
                    Intrinsics.checkNotNullExpressionValue(asString, "it");
                    Status fromJson = companion.fromJson(asString);
                    JsonArray<JsonElement> asJsonArray = asJsonObject.get("interfaces").getAsJsonArray();
                    ArrayList arrayList = new ArrayList(asJsonArray.size());
                    Intrinsics.checkNotNullExpressionValue(asJsonArray, "jsonArray");
                    for (JsonElement asString2 : asJsonArray) {
                        Interface.Companion companion2 = Interface.Companion;
                        String asString3 = asString2.getAsString();
                        Intrinsics.checkNotNullExpressionValue(asString3, "it.asString");
                        arrayList.add(companion2.fromJson(asString3));
                    }
                    JsonElement jsonElement = asJsonObject.get("cellular");
                    Cellular cellular = null;
                    if (jsonElement != null) {
                        String jsonElement2 = jsonElement.toString();
                        if (jsonElement2 != null) {
                            cellular = Cellular.Companion.fromJson(jsonElement2);
                        }
                    }
                    return new Connectivity(fromJson, arrayList, cellular);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\tJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Synthetics;", "", "testId", "", "resultId", "injected", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getInjected", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getResultId", "()Ljava/lang/String;", "getTestId", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/LongTaskEvent$Synthetics;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class Synthetics {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Boolean injected;
        private final String resultId;
        private final String testId;

        public static /* synthetic */ Synthetics copy$default(Synthetics synthetics, String str, String str2, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = synthetics.testId;
            }
            if ((i & 2) != 0) {
                str2 = synthetics.resultId;
            }
            if ((i & 4) != 0) {
                bool = synthetics.injected;
            }
            return synthetics.copy(str, str2, bool);
        }

        @JvmStatic
        public static final Synthetics fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.testId;
        }

        public final String component2() {
            return this.resultId;
        }

        public final Boolean component3() {
            return this.injected;
        }

        public final Synthetics copy(String str, String str2, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "testId");
            Intrinsics.checkNotNullParameter(str2, "resultId");
            return new Synthetics(str, str2, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Synthetics)) {
                return false;
            }
            Synthetics synthetics = (Synthetics) obj;
            return Intrinsics.areEqual((Object) this.testId, (Object) synthetics.testId) && Intrinsics.areEqual((Object) this.resultId, (Object) synthetics.resultId) && Intrinsics.areEqual((Object) this.injected, (Object) synthetics.injected);
        }

        public int hashCode() {
            int hashCode = ((this.testId.hashCode() * 31) + this.resultId.hashCode()) * 31;
            Boolean bool = this.injected;
            return hashCode + (bool == null ? 0 : bool.hashCode());
        }

        public String toString() {
            String str = this.testId;
            String str2 = this.resultId;
            return "Synthetics(testId=" + str + ", resultId=" + str2 + ", injected=" + this.injected + ")";
        }

        public Synthetics(String str, String str2, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "testId");
            Intrinsics.checkNotNullParameter(str2, "resultId");
            this.testId = str;
            this.resultId = str2;
            this.injected = bool;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Synthetics(String str, String str2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? null : bool);
        }

        public final String getTestId() {
            return this.testId;
        }

        public final String getResultId() {
            return this.resultId;
        }

        public final Boolean getInjected() {
            return this.injected;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("test_id", this.testId);
            jsonObject.addProperty("result_id", this.resultId);
            Boolean bool = this.injected;
            if (bool != null) {
                jsonObject.addProperty("injected", Boolean.valueOf(bool.booleanValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Synthetics$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Synthetics;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Synthetics fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    String asString = asJsonObject.get("test_id").getAsString();
                    String asString2 = asJsonObject.get("result_id").getAsString();
                    JsonElement jsonElement = asJsonObject.get("injected");
                    Boolean valueOf = jsonElement == null ? null : Boolean.valueOf(jsonElement.getAsBoolean());
                    Intrinsics.checkNotNullExpressionValue(asString, "testId");
                    Intrinsics.checkNotNullExpressionValue(asString2, "resultId");
                    return new Synthetics(asString, asString2, valueOf);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$CiTest;", "", "testExecutionId", "", "(Ljava/lang/String;)V", "getTestExecutionId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class CiTest {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String testExecutionId;

        public static /* synthetic */ CiTest copy$default(CiTest ciTest, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = ciTest.testExecutionId;
            }
            return ciTest.copy(str);
        }

        @JvmStatic
        public static final CiTest fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.testExecutionId;
        }

        public final CiTest copy(String str) {
            Intrinsics.checkNotNullParameter(str, "testExecutionId");
            return new CiTest(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CiTest) && Intrinsics.areEqual((Object) this.testExecutionId, (Object) ((CiTest) obj).testExecutionId);
        }

        public int hashCode() {
            return this.testExecutionId.hashCode();
        }

        public String toString() {
            return "CiTest(testExecutionId=" + this.testExecutionId + ")";
        }

        public CiTest(String str) {
            Intrinsics.checkNotNullParameter(str, "testExecutionId");
            this.testExecutionId = str;
        }

        public final String getTestExecutionId() {
            return this.testExecutionId;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("test_execution_id", this.testExecutionId);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$CiTest$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$CiTest;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final CiTest fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String asString = JsonParser.parseString(str).getAsJsonObject().get("test_execution_id").getAsString();
                    Intrinsics.checkNotNullExpressionValue(asString, "testExecutionId");
                    return new CiTest(asString);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Dd;", "", "session", "Lcom/datadog/android/rum/model/LongTaskEvent$DdSession;", "browserSdkVersion", "", "(Lcom/datadog/android/rum/model/LongTaskEvent$DdSession;Ljava/lang/String;)V", "getBrowserSdkVersion", "()Ljava/lang/String;", "formatVersion", "", "getFormatVersion", "()J", "getSession", "()Lcom/datadog/android/rum/model/LongTaskEvent$DdSession;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* renamed from: com.datadog.android.rum.model.LongTaskEvent$Dd */
    /* compiled from: LongTaskEvent.kt */
    public static final class C0865Dd {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String browserSdkVersion;
        private final long formatVersion;
        private final DdSession session;

        public C0865Dd() {
            this((DdSession) null, (String) null, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ C0865Dd copy$default(C0865Dd dd, DdSession ddSession, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                ddSession = dd.session;
            }
            if ((i & 2) != 0) {
                str = dd.browserSdkVersion;
            }
            return dd.copy(ddSession, str);
        }

        @JvmStatic
        public static final C0865Dd fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final DdSession component1() {
            return this.session;
        }

        public final String component2() {
            return this.browserSdkVersion;
        }

        public final C0865Dd copy(DdSession ddSession, String str) {
            return new C0865Dd(ddSession, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0865Dd)) {
                return false;
            }
            C0865Dd dd = (C0865Dd) obj;
            return Intrinsics.areEqual((Object) this.session, (Object) dd.session) && Intrinsics.areEqual((Object) this.browserSdkVersion, (Object) dd.browserSdkVersion);
        }

        public int hashCode() {
            DdSession ddSession = this.session;
            int i = 0;
            int hashCode = (ddSession == null ? 0 : ddSession.hashCode()) * 31;
            String str = this.browserSdkVersion;
            if (str != null) {
                i = str.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            DdSession ddSession = this.session;
            return "Dd(session=" + ddSession + ", browserSdkVersion=" + this.browserSdkVersion + ")";
        }

        public C0865Dd(DdSession ddSession, String str) {
            this.session = ddSession;
            this.browserSdkVersion = str;
            this.formatVersion = 2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ C0865Dd(DdSession ddSession, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : ddSession, (i & 2) != 0 ? null : str);
        }

        public final DdSession getSession() {
            return this.session;
        }

        public final String getBrowserSdkVersion() {
            return this.browserSdkVersion;
        }

        public final long getFormatVersion() {
            return this.formatVersion;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("format_version", (Number) Long.valueOf(this.formatVersion));
            DdSession ddSession = this.session;
            if (ddSession != null) {
                jsonObject.add("session", ddSession.toJson());
            }
            String str = this.browserSdkVersion;
            if (str != null) {
                jsonObject.addProperty("browser_sdk_version", str);
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Dd$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Dd;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* renamed from: com.datadog.android.rum.model.LongTaskEvent$Dd$Companion */
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARNING: Removed duplicated region for block: B:11:0x002d A[Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }] */
            /* JADX WARNING: Removed duplicated region for block: B:12:0x002e A[Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }] */
            @kotlin.jvm.JvmStatic
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.datadog.android.rum.model.LongTaskEvent.C0865Dd fromJson(java.lang.String r3) throws com.google.gson.JsonParseException {
                /*
                    r2 = this;
                    java.lang.String r2 = "serializedObject"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
                    com.google.gson.JsonElement r2 = com.google.gson.JsonParser.parseString(r3)     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    com.google.gson.JsonObject r2 = r2.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    java.lang.String r3 = "session"
                    com.google.gson.JsonElement r3 = r2.get(r3)     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    r0 = 0
                    if (r3 != 0) goto L_0x0018
                L_0x0016:
                    r3 = r0
                    goto L_0x0025
                L_0x0018:
                    java.lang.String r3 = r3.toString()     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    if (r3 != 0) goto L_0x001f
                    goto L_0x0016
                L_0x001f:
                    com.datadog.android.rum.model.LongTaskEvent$DdSession$Companion r1 = com.datadog.android.rum.model.LongTaskEvent.DdSession.Companion     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    com.datadog.android.rum.model.LongTaskEvent$DdSession r3 = r1.fromJson(r3)     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                L_0x0025:
                    java.lang.String r1 = "browser_sdk_version"
                    com.google.gson.JsonElement r2 = r2.get(r1)     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    if (r2 != 0) goto L_0x002e
                    goto L_0x0032
                L_0x002e:
                    java.lang.String r0 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                L_0x0032:
                    com.datadog.android.rum.model.LongTaskEvent$Dd r2 = new com.datadog.android.rum.model.LongTaskEvent$Dd     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    r2.<init>(r3, r0)     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    return r2
                L_0x0038:
                    r2 = move-exception
                    com.google.gson.JsonParseException r3 = new com.google.gson.JsonParseException
                    java.lang.String r2 = r2.getMessage()
                    r3.<init>((java.lang.String) r2)
                    throw r3
                L_0x0043:
                    r2 = move-exception
                    com.google.gson.JsonParseException r3 = new com.google.gson.JsonParseException
                    java.lang.String r2 = r2.getMessage()
                    r3.<init>((java.lang.String) r2)
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.LongTaskEvent.C0865Dd.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.LongTaskEvent$Dd");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003HÆ\u0003J!\u0010\t\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Context;", "", "additionalProperties", "", "", "(Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class Context {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Map<String, Object> additionalProperties;

        public Context() {
            this((Map) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Context copy$default(Context context, Map<String, Object> map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = context.additionalProperties;
            }
            return context.copy(map);
        }

        @JvmStatic
        public static final Context fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final Map<String, Object> component1() {
            return this.additionalProperties;
        }

        public final Context copy(Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            return new Context(map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Context) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) ((Context) obj).additionalProperties);
        }

        public int hashCode() {
            return this.additionalProperties.hashCode();
        }

        public String toString() {
            return "Context(additionalProperties=" + this.additionalProperties + ")";
        }

        public Context(Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.additionalProperties = map;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Context(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry next : this.additionalProperties.entrySet()) {
                jsonObject.add((String) next.getKey(), MiscUtilsKt.toJsonElement(next.getValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Context$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Context;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Context fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    Map linkedHashMap = new LinkedHashMap();
                    for (Map.Entry next : asJsonObject.entrySet()) {
                        Object key = next.getKey();
                        Intrinsics.checkNotNullExpressionValue(key, "entry.key");
                        linkedHashMap.put(key, next.getValue());
                    }
                    return new Context(linkedHashMap);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\rJ0\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0006\u0010\r¨\u0006\u001c"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$LongTask;", "", "id", "", "duration", "", "isFrozenFrame", "", "(Ljava/lang/String;JLjava/lang/Boolean;)V", "getDuration", "()J", "getId", "()Ljava/lang/String;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;JLjava/lang/Boolean;)Lcom/datadog/android/rum/model/LongTaskEvent$LongTask;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class LongTask {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long duration;

        /* renamed from: id */
        private final String f118id;
        private final Boolean isFrozenFrame;

        public static /* synthetic */ LongTask copy$default(LongTask longTask, String str, long j, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = longTask.f118id;
            }
            if ((i & 2) != 0) {
                j = longTask.duration;
            }
            if ((i & 4) != 0) {
                bool = longTask.isFrozenFrame;
            }
            return longTask.copy(str, j, bool);
        }

        @JvmStatic
        public static final LongTask fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f118id;
        }

        public final long component2() {
            return this.duration;
        }

        public final Boolean component3() {
            return this.isFrozenFrame;
        }

        public final LongTask copy(String str, long j, Boolean bool) {
            return new LongTask(str, j, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LongTask)) {
                return false;
            }
            LongTask longTask = (LongTask) obj;
            return Intrinsics.areEqual((Object) this.f118id, (Object) longTask.f118id) && this.duration == longTask.duration && Intrinsics.areEqual((Object) this.isFrozenFrame, (Object) longTask.isFrozenFrame);
        }

        public int hashCode() {
            String str = this.f118id;
            int i = 0;
            int hashCode = (((str == null ? 0 : str.hashCode()) * 31) + Long.hashCode(this.duration)) * 31;
            Boolean bool = this.isFrozenFrame;
            if (bool != null) {
                i = bool.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            String str = this.f118id;
            long j = this.duration;
            return "LongTask(id=" + str + ", duration=" + j + ", isFrozenFrame=" + this.isFrozenFrame + ")";
        }

        public LongTask(String str, long j, Boolean bool) {
            this.f118id = str;
            this.duration = j;
            this.isFrozenFrame = bool;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LongTask(String str, long j, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, j, (i & 4) != 0 ? null : bool);
        }

        public final String getId() {
            return this.f118id;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final Boolean isFrozenFrame() {
            return this.isFrozenFrame;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.f118id;
            if (str != null) {
                jsonObject.addProperty("id", str);
            }
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            Boolean bool = this.isFrozenFrame;
            if (bool != null) {
                jsonObject.addProperty("is_frozen_frame", Boolean.valueOf(bool.booleanValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$LongTask$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$LongTask;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final LongTask fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    JsonElement jsonElement = asJsonObject.get("id");
                    Boolean bool = null;
                    String asString = jsonElement == null ? null : jsonElement.getAsString();
                    long asLong = asJsonObject.get(LogAttributes.DURATION).getAsLong();
                    JsonElement jsonElement2 = asJsonObject.get("is_frozen_frame");
                    if (jsonElement2 != null) {
                        bool = Boolean.valueOf(jsonElement2.getAsBoolean());
                    }
                    return new LongTask(asString, asLong, bool);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Action;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class Action {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f116id;

        public static /* synthetic */ Action copy$default(Action action, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = action.f116id;
            }
            return action.copy(str);
        }

        @JvmStatic
        public static final Action fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f116id;
        }

        public final Action copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Action(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Action) && Intrinsics.areEqual((Object) this.f116id, (Object) ((Action) obj).f116id);
        }

        public int hashCode() {
            return this.f116id.hashCode();
        }

        public String toString() {
            return "Action(id=" + this.f116id + ")";
        }

        public Action(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f116id = str;
        }

        public final String getId() {
            return this.f116id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f116id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Action$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Action;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Cellular;", "", "technology", "", "carrierName", "(Ljava/lang/String;Ljava/lang/String;)V", "getCarrierName", "()Ljava/lang/String;", "getTechnology", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class Cellular {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String carrierName;
        private final String technology;

        public Cellular() {
            this((String) null, (String) null, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Cellular copy$default(Cellular cellular, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = cellular.technology;
            }
            if ((i & 2) != 0) {
                str2 = cellular.carrierName;
            }
            return cellular.copy(str, str2);
        }

        @JvmStatic
        public static final Cellular fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.technology;
        }

        public final String component2() {
            return this.carrierName;
        }

        public final Cellular copy(String str, String str2) {
            return new Cellular(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Cellular)) {
                return false;
            }
            Cellular cellular = (Cellular) obj;
            return Intrinsics.areEqual((Object) this.technology, (Object) cellular.technology) && Intrinsics.areEqual((Object) this.carrierName, (Object) cellular.carrierName);
        }

        public int hashCode() {
            String str = this.technology;
            int i = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.carrierName;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            String str = this.technology;
            return "Cellular(technology=" + str + ", carrierName=" + this.carrierName + ")";
        }

        public Cellular(String str, String str2) {
            this.technology = str;
            this.carrierName = str2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Cellular(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        public final String getTechnology() {
            return this.technology;
        }

        public final String getCarrierName() {
            return this.carrierName;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.technology;
            if (str != null) {
                jsonObject.addProperty("technology", str);
            }
            String str2 = this.carrierName;
            if (str2 != null) {
                jsonObject.addProperty("carrier_name", str2);
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Cellular$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Cellular;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Cellular fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    JsonElement jsonElement = asJsonObject.get("technology");
                    String str2 = null;
                    String asString = jsonElement == null ? null : jsonElement.getAsString();
                    JsonElement jsonElement2 = asJsonObject.get("carrier_name");
                    if (jsonElement2 != null) {
                        str2 = jsonElement2.getAsString();
                    }
                    return new Cellular(asString, str2);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$DdSession;", "", "plan", "Lcom/datadog/android/rum/model/LongTaskEvent$Plan;", "(Lcom/datadog/android/rum/model/LongTaskEvent$Plan;)V", "getPlan", "()Lcom/datadog/android/rum/model/LongTaskEvent$Plan;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public static final class DdSession {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Plan plan;

        public static /* synthetic */ DdSession copy$default(DdSession ddSession, Plan plan2, int i, Object obj) {
            if ((i & 1) != 0) {
                plan2 = ddSession.plan;
            }
            return ddSession.copy(plan2);
        }

        @JvmStatic
        public static final DdSession fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final Plan component1() {
            return this.plan;
        }

        public final DdSession copy(Plan plan2) {
            Intrinsics.checkNotNullParameter(plan2, WebViewRumEventMapper.SESSION_PLAN_KEY_NAME);
            return new DdSession(plan2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DdSession) && this.plan == ((DdSession) obj).plan;
        }

        public int hashCode() {
            return this.plan.hashCode();
        }

        public String toString() {
            return "DdSession(plan=" + this.plan + ")";
        }

        public DdSession(Plan plan2) {
            Intrinsics.checkNotNullParameter(plan2, WebViewRumEventMapper.SESSION_PLAN_KEY_NAME);
            this.plan = plan2;
        }

        public final Plan getPlan() {
            return this.plan;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(WebViewRumEventMapper.SESSION_PLAN_KEY_NAME, this.plan.toJson());
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$DdSession$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$DdSession;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final DdSession fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String asString = JsonParser.parseString(str).getAsJsonObject().get(WebViewRumEventMapper.SESSION_PLAN_KEY_NAME).getAsString();
                    Plan.Companion companion = Plan.Companion;
                    Intrinsics.checkNotNullExpressionValue(asString, "it");
                    return new DdSession(companion.fromJson(asString));
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Source;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "ANDROID", "IOS", "BROWSER", "FLUTTER", "REACT_NATIVE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Source$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Source;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Type;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "USER", "SYNTHETICS", "CI_TEST", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public enum Type {
        USER("user"),
        SYNTHETICS("synthetics"),
        CI_TEST("ci_test");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final Type fromJson(String str) {
            return Companion.fromJson(str);
        }

        private Type(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Type$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Type;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Type fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                Type[] values = Type.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    Type type = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) type.jsonValue, (Object) str)) {
                        return type;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Status;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "CONNECTED", "NOT_CONNECTED", "MAYBE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public enum Status {
        CONNECTED("connected"),
        NOT_CONNECTED("not_connected"),
        MAYBE("maybe");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final Status fromJson(String str) {
            return Companion.fromJson(str);
        }

        private Status(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Status$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Status;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Status fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                Status[] values = Status.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    Status status = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) status.jsonValue, (Object) str)) {
                        return status;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Interface;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "BLUETOOTH", "CELLULAR", "ETHERNET", "WIFI", "WIMAX", "MIXED", "OTHER", "UNKNOWN", "NONE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public enum Interface {
        BLUETOOTH("bluetooth"),
        CELLULAR("cellular"),
        ETHERNET("ethernet"),
        WIFI("wifi"),
        WIMAX("wimax"),
        MIXED("mixed"),
        OTHER("other"),
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
        NONE("none");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final Interface fromJson(String str) {
            return Companion.fromJson(str);
        }

        private Interface(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Interface$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Interface;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Interface fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                Interface[] values = Interface.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    Interface interfaceR = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) interfaceR.jsonValue, (Object) str)) {
                        return interfaceR;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Plan;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/Number;)V", "toJson", "Lcom/google/gson/JsonElement;", "PLAN_1", "PLAN_2", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LongTaskEvent.kt */
    public enum Plan {
        PLAN_1((Number) 1),
        PLAN_2((Number) 2);
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final Number jsonValue;

        @JvmStatic
        public static final Plan fromJson(String str) {
            return Companion.fromJson(str);
        }

        private Plan(Number number) {
            this.jsonValue = number;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/LongTaskEvent$Plan$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/LongTaskEvent$Plan;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: LongTaskEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Plan fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                Plan[] values = Plan.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    Plan plan = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) plan.jsonValue.toString(), (Object) str)) {
                        return plan;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }
}

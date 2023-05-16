package com.datadog.android.rum.model;

import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
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

@Metadata(mo20734d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\b\b\u0018\u0000 N2\u00020\u0001:\u001aJKLMNOPQRSTUVWXYZ[\\]^_`abcB\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010\u001aJ\t\u00105\u001a\u00020\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\t\u00107\u001a\u00020\u0017HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0019HÆ\u0003J\t\u00109\u001a\u00020\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010;\u001a\u00020\tHÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010=\u001a\u00020\rHÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u0001\u0010A\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÆ\u0001J\u0013\u0010B\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010E\u001a\u00020FHÖ\u0001J\u0006\u0010G\u001a\u00020HJ\t\u0010I\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b0\u0010(R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b3\u00104¨\u0006d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent;", "", "date", "", "application", "Lcom/datadog/android/rum/model/ViewEvent$Application;", "service", "", "session", "Lcom/datadog/android/rum/model/ViewEvent$ViewEventSession;", "source", "Lcom/datadog/android/rum/model/ViewEvent$Source;", "view", "Lcom/datadog/android/rum/model/ViewEvent$View;", "usr", "Lcom/datadog/android/rum/model/ViewEvent$Usr;", "connectivity", "Lcom/datadog/android/rum/model/ViewEvent$Connectivity;", "synthetics", "Lcom/datadog/android/rum/model/ViewEvent$Synthetics;", "ciTest", "Lcom/datadog/android/rum/model/ViewEvent$CiTest;", "dd", "Lcom/datadog/android/rum/model/ViewEvent$Dd;", "context", "Lcom/datadog/android/rum/model/ViewEvent$Context;", "(JLcom/datadog/android/rum/model/ViewEvent$Application;Ljava/lang/String;Lcom/datadog/android/rum/model/ViewEvent$ViewEventSession;Lcom/datadog/android/rum/model/ViewEvent$Source;Lcom/datadog/android/rum/model/ViewEvent$View;Lcom/datadog/android/rum/model/ViewEvent$Usr;Lcom/datadog/android/rum/model/ViewEvent$Connectivity;Lcom/datadog/android/rum/model/ViewEvent$Synthetics;Lcom/datadog/android/rum/model/ViewEvent$CiTest;Lcom/datadog/android/rum/model/ViewEvent$Dd;Lcom/datadog/android/rum/model/ViewEvent$Context;)V", "getApplication", "()Lcom/datadog/android/rum/model/ViewEvent$Application;", "getCiTest", "()Lcom/datadog/android/rum/model/ViewEvent$CiTest;", "getConnectivity", "()Lcom/datadog/android/rum/model/ViewEvent$Connectivity;", "getContext", "()Lcom/datadog/android/rum/model/ViewEvent$Context;", "getDate", "()J", "getDd", "()Lcom/datadog/android/rum/model/ViewEvent$Dd;", "getService", "()Ljava/lang/String;", "getSession", "()Lcom/datadog/android/rum/model/ViewEvent$ViewEventSession;", "getSource", "()Lcom/datadog/android/rum/model/ViewEvent$Source;", "getSynthetics", "()Lcom/datadog/android/rum/model/ViewEvent$Synthetics;", "type", "getType", "getUsr", "()Lcom/datadog/android/rum/model/ViewEvent$Usr;", "getView", "()Lcom/datadog/android/rum/model/ViewEvent$View;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Action", "Application", "Cellular", "CiTest", "Companion", "Connectivity", "Context", "Crash", "CustomTimings", "Dd", "DdSession", "Error", "FrozenFrame", "InForegroundPeriod", "Interface", "LoadingType", "LongTask", "Plan", "Resource", "Source", "Status", "Synthetics", "Type", "Usr", "View", "ViewEventSession", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ViewEvent.kt */
public final class ViewEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Application application;
    private final CiTest ciTest;
    private final Connectivity connectivity;
    private final Context context;
    private final long date;

    /* renamed from: dd */
    private final C0867Dd f131dd;
    private final String service;
    private final ViewEventSession session;
    private final Source source;
    private final Synthetics synthetics;
    private final String type;
    private final Usr usr;
    private final View view;

    public static /* synthetic */ ViewEvent copy$default(ViewEvent viewEvent, long j, Application application2, String str, ViewEventSession viewEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0867Dd dd, Context context2, int i, Object obj) {
        ViewEvent viewEvent2 = viewEvent;
        int i2 = i;
        return viewEvent.copy((i2 & 1) != 0 ? viewEvent2.date : j, (i2 & 2) != 0 ? viewEvent2.application : application2, (i2 & 4) != 0 ? viewEvent2.service : str, (i2 & 8) != 0 ? viewEvent2.session : viewEventSession, (i2 & 16) != 0 ? viewEvent2.source : source2, (i2 & 32) != 0 ? viewEvent2.view : view2, (i2 & 64) != 0 ? viewEvent2.usr : usr2, (i2 & 128) != 0 ? viewEvent2.connectivity : connectivity2, (i2 & 256) != 0 ? viewEvent2.synthetics : synthetics2, (i2 & 512) != 0 ? viewEvent2.ciTest : ciTest2, (i2 & 1024) != 0 ? viewEvent2.f131dd : dd, (i2 & 2048) != 0 ? viewEvent2.context : context2);
    }

    @JvmStatic
    public static final ViewEvent fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final long component1() {
        return this.date;
    }

    public final CiTest component10() {
        return this.ciTest;
    }

    public final C0867Dd component11() {
        return this.f131dd;
    }

    public final Context component12() {
        return this.context;
    }

    public final Application component2() {
        return this.application;
    }

    public final String component3() {
        return this.service;
    }

    public final ViewEventSession component4() {
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

    public final ViewEvent copy(long j, Application application2, String str, ViewEventSession viewEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0867Dd dd, Context context2) {
        Application application3 = application2;
        Intrinsics.checkNotNullParameter(application3, WebViewRumEventMapper.APPLICATION_KEY_NAME);
        ViewEventSession viewEventSession2 = viewEventSession;
        Intrinsics.checkNotNullParameter(viewEventSession2, "session");
        View view3 = view2;
        Intrinsics.checkNotNullParameter(view3, "view");
        C0867Dd dd2 = dd;
        Intrinsics.checkNotNullParameter(dd2, "dd");
        return new ViewEvent(j, application3, str, viewEventSession2, source2, view3, usr2, connectivity2, synthetics2, ciTest2, dd2, context2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewEvent)) {
            return false;
        }
        ViewEvent viewEvent = (ViewEvent) obj;
        return this.date == viewEvent.date && Intrinsics.areEqual((Object) this.application, (Object) viewEvent.application) && Intrinsics.areEqual((Object) this.service, (Object) viewEvent.service) && Intrinsics.areEqual((Object) this.session, (Object) viewEvent.session) && this.source == viewEvent.source && Intrinsics.areEqual((Object) this.view, (Object) viewEvent.view) && Intrinsics.areEqual((Object) this.usr, (Object) viewEvent.usr) && Intrinsics.areEqual((Object) this.connectivity, (Object) viewEvent.connectivity) && Intrinsics.areEqual((Object) this.synthetics, (Object) viewEvent.synthetics) && Intrinsics.areEqual((Object) this.ciTest, (Object) viewEvent.ciTest) && Intrinsics.areEqual((Object) this.f131dd, (Object) viewEvent.f131dd) && Intrinsics.areEqual((Object) this.context, (Object) viewEvent.context);
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
        int hashCode7 = (((hashCode6 + (ciTest2 == null ? 0 : ciTest2.hashCode())) * 31) + this.f131dd.hashCode()) * 31;
        Context context2 = this.context;
        if (context2 != null) {
            i = context2.hashCode();
        }
        return hashCode7 + i;
    }

    public String toString() {
        long j = this.date;
        Application application2 = this.application;
        String str = this.service;
        ViewEventSession viewEventSession = this.session;
        Source source2 = this.source;
        View view2 = this.view;
        Usr usr2 = this.usr;
        Connectivity connectivity2 = this.connectivity;
        Synthetics synthetics2 = this.synthetics;
        CiTest ciTest2 = this.ciTest;
        C0867Dd dd = this.f131dd;
        return "ViewEvent(date=" + j + ", application=" + application2 + ", service=" + str + ", session=" + viewEventSession + ", source=" + source2 + ", view=" + view2 + ", usr=" + usr2 + ", connectivity=" + connectivity2 + ", synthetics=" + synthetics2 + ", ciTest=" + ciTest2 + ", dd=" + dd + ", context=" + this.context + ")";
    }

    public ViewEvent(long j, Application application2, String str, ViewEventSession viewEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0867Dd dd, Context context2) {
        Intrinsics.checkNotNullParameter(application2, WebViewRumEventMapper.APPLICATION_KEY_NAME);
        Intrinsics.checkNotNullParameter(viewEventSession, "session");
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(dd, "dd");
        this.date = j;
        this.application = application2;
        this.service = str;
        this.session = viewEventSession;
        this.source = source2;
        this.view = view2;
        this.usr = usr2;
        this.connectivity = connectivity2;
        this.synthetics = synthetics2;
        this.ciTest = ciTest2;
        this.f131dd = dd;
        this.context = context2;
        this.type = "view";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ViewEvent(long r18, com.datadog.android.rum.model.ViewEvent.Application r20, java.lang.String r21, com.datadog.android.rum.model.ViewEvent.ViewEventSession r22, com.datadog.android.rum.model.ViewEvent.Source r23, com.datadog.android.rum.model.ViewEvent.View r24, com.datadog.android.rum.model.ViewEvent.Usr r25, com.datadog.android.rum.model.ViewEvent.Connectivity r26, com.datadog.android.rum.model.ViewEvent.Synthetics r27, com.datadog.android.rum.model.ViewEvent.CiTest r28, com.datadog.android.rum.model.ViewEvent.C0867Dd r29, com.datadog.android.rum.model.ViewEvent.Context r30, int r31, kotlin.jvm.internal.DefaultConstructorMarker r32) {
        /*
            r17 = this;
            r0 = r31
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r7 = r2
            goto L_0x000b
        L_0x0009:
            r7 = r21
        L_0x000b:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0011
            r9 = r2
            goto L_0x0013
        L_0x0011:
            r9 = r23
        L_0x0013:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0019
            r11 = r2
            goto L_0x001b
        L_0x0019:
            r11 = r25
        L_0x001b:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0021
            r12 = r2
            goto L_0x0023
        L_0x0021:
            r12 = r26
        L_0x0023:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0029
            r13 = r2
            goto L_0x002b
        L_0x0029:
            r13 = r27
        L_0x002b:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0031
            r14 = r2
            goto L_0x0033
        L_0x0031:
            r14 = r28
        L_0x0033:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x003a
            r16 = r2
            goto L_0x003c
        L_0x003a:
            r16 = r30
        L_0x003c:
            r3 = r17
            r4 = r18
            r6 = r20
            r8 = r22
            r10 = r24
            r15 = r29
            r3.<init>(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ViewEvent.<init>(long, com.datadog.android.rum.model.ViewEvent$Application, java.lang.String, com.datadog.android.rum.model.ViewEvent$ViewEventSession, com.datadog.android.rum.model.ViewEvent$Source, com.datadog.android.rum.model.ViewEvent$View, com.datadog.android.rum.model.ViewEvent$Usr, com.datadog.android.rum.model.ViewEvent$Connectivity, com.datadog.android.rum.model.ViewEvent$Synthetics, com.datadog.android.rum.model.ViewEvent$CiTest, com.datadog.android.rum.model.ViewEvent$Dd, com.datadog.android.rum.model.ViewEvent$Context, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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

    public final ViewEventSession getSession() {
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

    public final C0867Dd getDd() {
        return this.f131dd;
    }

    public final Context getContext() {
        return this.context;
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
        jsonObject.add(WebViewRumEventMapper.DD_KEY_NAME, this.f131dd.toJson());
        Context context2 = this.context;
        if (context2 != null) {
            jsonObject.add(RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX, context2.toJson());
        }
        jsonObject.addProperty(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type);
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0086 A[Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x009e A[Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }] */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00b6 A[Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00ce A[Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x00fa A[Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }] */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.datadog.android.rum.model.ViewEvent fromJson(java.lang.String r18) throws com.google.gson.JsonParseException {
            /*
                r17 = this;
                java.lang.String r0 = "it"
                java.lang.String r1 = "serializedObject"
                r2 = r18
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                com.google.gson.JsonElement r1 = com.google.gson.JsonParser.parseString(r18)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.google.gson.JsonObject r1 = r1.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = "date"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                long r4 = r2.getAsLong()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = "application"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Application$Companion r3 = com.datadog.android.rum.model.ViewEvent.Application.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Application r6 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = "service"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r3 = 0
                if (r2 != 0) goto L_0x0039
                r7 = r3
                goto L_0x003e
            L_0x0039:
                java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r7 = r2
            L_0x003e:
                java.lang.String r2 = "session"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$ViewEventSession$Companion r8 = com.datadog.android.rum.model.ViewEvent.ViewEventSession.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$ViewEventSession r8 = r8.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = "source"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x005b
            L_0x0059:
                r9 = r3
                goto L_0x0069
            L_0x005b:
                java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x0062
                goto L_0x0059
            L_0x0062:
                com.datadog.android.rum.model.ViewEvent$Source$Companion r9 = com.datadog.android.rum.model.ViewEvent.Source.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Source r2 = r9.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r9 = r2
            L_0x0069:
                java.lang.String r2 = "view"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$View$Companion r10 = com.datadog.android.rum.model.ViewEvent.View.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$View r10 = r10.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = "usr"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x0086
            L_0x0084:
                r11 = r3
                goto L_0x0094
            L_0x0086:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x008d
                goto L_0x0084
            L_0x008d:
                com.datadog.android.rum.model.ViewEvent$Usr$Companion r11 = com.datadog.android.rum.model.ViewEvent.Usr.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Usr r2 = r11.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r11 = r2
            L_0x0094:
                java.lang.String r2 = "connectivity"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x009e
            L_0x009c:
                r12 = r3
                goto L_0x00ac
            L_0x009e:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x00a5
                goto L_0x009c
            L_0x00a5:
                com.datadog.android.rum.model.ViewEvent$Connectivity$Companion r12 = com.datadog.android.rum.model.ViewEvent.Connectivity.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Connectivity r2 = r12.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r12 = r2
            L_0x00ac:
                java.lang.String r2 = "synthetics"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x00b6
            L_0x00b4:
                r13 = r3
                goto L_0x00c4
            L_0x00b6:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x00bd
                goto L_0x00b4
            L_0x00bd:
                com.datadog.android.rum.model.ViewEvent$Synthetics$Companion r13 = com.datadog.android.rum.model.ViewEvent.Synthetics.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Synthetics r2 = r13.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r13 = r2
            L_0x00c4:
                java.lang.String r2 = "ci_test"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x00ce
            L_0x00cc:
                r14 = r3
                goto L_0x00dc
            L_0x00ce:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r2 != 0) goto L_0x00d5
                goto L_0x00cc
            L_0x00d5:
                com.datadog.android.rum.model.ViewEvent$CiTest$Companion r14 = com.datadog.android.rum.model.ViewEvent.CiTest.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$CiTest r2 = r14.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r14 = r2
            L_0x00dc:
                java.lang.String r2 = "_dd"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Dd$Companion r15 = com.datadog.android.rum.model.ViewEvent.C0867Dd.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Dd r15 = r15.fromJson(r2)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                java.lang.String r0 = "context"
                com.google.gson.JsonElement r0 = r1.get(r0)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r0 != 0) goto L_0x00fa
            L_0x00f7:
                r16 = r3
                goto L_0x0109
            L_0x00fa:
                java.lang.String r0 = r0.toString()     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                if (r0 != 0) goto L_0x0101
                goto L_0x00f7
            L_0x0101:
                com.datadog.android.rum.model.ViewEvent$Context$Companion r1 = com.datadog.android.rum.model.ViewEvent.Context.Companion     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                com.datadog.android.rum.model.ViewEvent$Context r0 = r1.fromJson(r0)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r16 = r0
            L_0x0109:
                com.datadog.android.rum.model.ViewEvent r0 = new com.datadog.android.rum.model.ViewEvent     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                r3 = r0
                r3.<init>(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ IllegalStateException -> 0x011b, NumberFormatException -> 0x0110 }
                return r0
            L_0x0110:
                r0 = move-exception
                com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                java.lang.String r0 = r0.getMessage()
                r1.<init>((java.lang.String) r0)
                throw r1
            L_0x011b:
                r0 = move-exception
                com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                java.lang.String r0 = r0.getMessage()
                r1.<init>((java.lang.String) r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ViewEvent.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ViewEvent");
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Application;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class Application {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f132id;

        public static /* synthetic */ Application copy$default(Application application, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = application.f132id;
            }
            return application.copy(str);
        }

        @JvmStatic
        public static final Application fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f132id;
        }

        public final Application copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Application(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Application) && Intrinsics.areEqual((Object) this.f132id, (Object) ((Application) obj).f132id);
        }

        public int hashCode() {
            return this.f132id.hashCode();
        }

        public String toString() {
            return "Application(id=" + this.f132id + ")";
        }

        public Application(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f132id = str;
        }

        public final String getId() {
            return this.f132id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f132id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Application$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Application;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\nJ.\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$ViewEventSession;", "", "id", "", "type", "Lcom/datadog/android/rum/model/ViewEvent$Type;", "hasReplay", "", "(Ljava/lang/String;Lcom/datadog/android/rum/model/ViewEvent$Type;Ljava/lang/Boolean;)V", "getHasReplay", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getId", "()Ljava/lang/String;", "getType", "()Lcom/datadog/android/rum/model/ViewEvent$Type;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Lcom/datadog/android/rum/model/ViewEvent$Type;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/ViewEvent$ViewEventSession;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class ViewEventSession {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Boolean hasReplay;

        /* renamed from: id */
        private final String f135id;
        private final Type type;

        public static /* synthetic */ ViewEventSession copy$default(ViewEventSession viewEventSession, String str, Type type2, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = viewEventSession.f135id;
            }
            if ((i & 2) != 0) {
                type2 = viewEventSession.type;
            }
            if ((i & 4) != 0) {
                bool = viewEventSession.hasReplay;
            }
            return viewEventSession.copy(str, type2, bool);
        }

        @JvmStatic
        public static final ViewEventSession fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f135id;
        }

        public final Type component2() {
            return this.type;
        }

        public final Boolean component3() {
            return this.hasReplay;
        }

        public final ViewEventSession copy(String str, Type type2, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(type2, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            return new ViewEventSession(str, type2, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ViewEventSession)) {
                return false;
            }
            ViewEventSession viewEventSession = (ViewEventSession) obj;
            return Intrinsics.areEqual((Object) this.f135id, (Object) viewEventSession.f135id) && this.type == viewEventSession.type && Intrinsics.areEqual((Object) this.hasReplay, (Object) viewEventSession.hasReplay);
        }

        public int hashCode() {
            int hashCode = ((this.f135id.hashCode() * 31) + this.type.hashCode()) * 31;
            Boolean bool = this.hasReplay;
            return hashCode + (bool == null ? 0 : bool.hashCode());
        }

        public String toString() {
            String str = this.f135id;
            Type type2 = this.type;
            return "ViewEventSession(id=" + str + ", type=" + type2 + ", hasReplay=" + this.hasReplay + ")";
        }

        public ViewEventSession(String str, Type type2, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(type2, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            this.f135id = str;
            this.type = type2;
            this.hasReplay = bool;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ViewEventSession(String str, Type type2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, type2, (i & 4) != 0 ? null : bool);
        }

        public final String getId() {
            return this.f135id;
        }

        public final Type getType() {
            return this.type;
        }

        public final Boolean getHasReplay() {
            return this.hasReplay;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f135id);
            jsonObject.add(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type.toJson());
            Boolean bool = this.hasReplay;
            if (bool != null) {
                jsonObject.addProperty("has_replay", Boolean.valueOf(bool.booleanValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$ViewEventSession$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$ViewEventSession;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final ViewEventSession fromJson(String str) throws JsonParseException {
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
                    return new ViewEventSession(asString, fromJson, valueOf);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b^\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00012\u00020\u0001:\u0002\u0001Bó\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$\u0012\u0006\u0010%\u001a\u00020&\u0012\u0010\b\u0002\u0010'\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u00100J\t\u0010c\u001a\u00020\u0003HÆ\u0003J\u0010\u0010d\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010e\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u000b\u0010f\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0010\u0010g\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010h\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010i\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010j\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u000b\u0010k\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u0010\u0010l\u001a\u0004\u0018\u00010\u0019HÆ\u0003¢\u0006\u0002\u0010KJ\u0010\u0010m\u001a\u0004\u0018\u00010\u0019HÆ\u0003¢\u0006\u0002\u0010KJ\u000b\u0010n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010o\u001a\u00020\u001cHÆ\u0003J\t\u0010p\u001a\u00020\u001eHÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010 HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\"HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010$HÆ\u0003J\t\u0010t\u001a\u00020&HÆ\u0003J\u0011\u0010u\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(HÆ\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\t\u0010y\u001a\u00020\u0003HÆ\u0003J\u000b\u0010z\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010{\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010|\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010~\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u000b\u0010\u001a\u0004\u0018\u00010\nHÆ\u0003J\n\u0010\u0001\u001a\u00020\bHÆ\u0003J\u0011\u0010\u0001\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u0011\u0010\u0001\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010<J\u0003\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020&2\u0010\b\u0002\u0010'\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0003\u0010\u0001J\u0015\u0010\u0001\u001a\u00020\u00192\t\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000b\u0010\u0001\u001a\u00030\u0001HÖ\u0001J\b\u0010\u0001\u001a\u00030\u0001J\n\u0010\u0001\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010,\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0013\u0010-\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b5\u00104R\u0013\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b8\u00104R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\b;\u0010<R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\b>\u0010<R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\b?\u0010<R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0015\u0010\f\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\bB\u0010<R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\bC\u0010<R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\bD\u0010<R\u0013\u0010#\u001a\u0004\u0018\u00010$¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0019\u0010'\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\n\n\u0002\u0010L\u001a\u0004\b\u0018\u0010KR\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u0019¢\u0006\n\n\u0002\u0010L\u001a\u0004\b\u001a\u0010KR\u0015\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\bM\u0010<R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\bN\u0010<R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010=\u001a\u0004\bO\u0010<R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u0013\u0010!\u001a\u0004\u0018\u00010\"¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR\u0013\u0010*\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bT\u00104R\u0013\u0010+\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bU\u00104R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010H\"\u0004\bW\u0010XR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010H\"\u0004\bZ\u0010XR\u0013\u0010.\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b[\u00104R\u0013\u0010/\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\\\u00104R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b_\u0010`R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010H\"\u0004\bb\u0010X¨\u0006\u0001"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$View;", "", "id", "", "referrer", "url", "name", "loadingTime", "", "loadingType", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "timeSpent", "firstContentfulPaint", "largestContentfulPaint", "firstInputDelay", "firstInputTime", "cumulativeLayoutShift", "", "domComplete", "domContentLoaded", "domInteractive", "loadEvent", "customTimings", "Lcom/datadog/android/rum/model/ViewEvent$CustomTimings;", "isActive", "", "isSlowRendered", "action", "Lcom/datadog/android/rum/model/ViewEvent$Action;", "error", "Lcom/datadog/android/rum/model/ViewEvent$Error;", "crash", "Lcom/datadog/android/rum/model/ViewEvent$Crash;", "longTask", "Lcom/datadog/android/rum/model/ViewEvent$LongTask;", "frozenFrame", "Lcom/datadog/android/rum/model/ViewEvent$FrozenFrame;", "resource", "Lcom/datadog/android/rum/model/ViewEvent$Resource;", "inForegroundPeriods", "", "Lcom/datadog/android/rum/model/ViewEvent$InForegroundPeriod;", "memoryAverage", "memoryMax", "cpuTicksCount", "cpuTicksPerSecond", "refreshRateAverage", "refreshRateMin", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/datadog/android/rum/model/ViewEvent$LoadingType;JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Number;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lcom/datadog/android/rum/model/ViewEvent$CustomTimings;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/datadog/android/rum/model/ViewEvent$Action;Lcom/datadog/android/rum/model/ViewEvent$Error;Lcom/datadog/android/rum/model/ViewEvent$Crash;Lcom/datadog/android/rum/model/ViewEvent$LongTask;Lcom/datadog/android/rum/model/ViewEvent$FrozenFrame;Lcom/datadog/android/rum/model/ViewEvent$Resource;Ljava/util/List;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;)V", "getAction", "()Lcom/datadog/android/rum/model/ViewEvent$Action;", "getCpuTicksCount", "()Ljava/lang/Number;", "getCpuTicksPerSecond", "getCrash", "()Lcom/datadog/android/rum/model/ViewEvent$Crash;", "getCumulativeLayoutShift", "getCustomTimings", "()Lcom/datadog/android/rum/model/ViewEvent$CustomTimings;", "getDomComplete", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDomContentLoaded", "getDomInteractive", "getError", "()Lcom/datadog/android/rum/model/ViewEvent$Error;", "getFirstContentfulPaint", "getFirstInputDelay", "getFirstInputTime", "getFrozenFrame", "()Lcom/datadog/android/rum/model/ViewEvent$FrozenFrame;", "getId", "()Ljava/lang/String;", "getInForegroundPeriods", "()Ljava/util/List;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLargestContentfulPaint", "getLoadEvent", "getLoadingTime", "getLoadingType", "()Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "getLongTask", "()Lcom/datadog/android/rum/model/ViewEvent$LongTask;", "getMemoryAverage", "getMemoryMax", "getName", "setName", "(Ljava/lang/String;)V", "getReferrer", "setReferrer", "getRefreshRateAverage", "getRefreshRateMin", "getResource", "()Lcom/datadog/android/rum/model/ViewEvent$Resource;", "getTimeSpent", "()J", "getUrl", "setUrl", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/datadog/android/rum/model/ViewEvent$LoadingType;JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Number;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lcom/datadog/android/rum/model/ViewEvent$CustomTimings;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/datadog/android/rum/model/ViewEvent$Action;Lcom/datadog/android/rum/model/ViewEvent$Error;Lcom/datadog/android/rum/model/ViewEvent$Crash;Lcom/datadog/android/rum/model/ViewEvent$LongTask;Lcom/datadog/android/rum/model/ViewEvent$FrozenFrame;Lcom/datadog/android/rum/model/ViewEvent$Resource;Ljava/util/List;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;)Lcom/datadog/android/rum/model/ViewEvent$View;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class View {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Action action;
        private final Number cpuTicksCount;
        private final Number cpuTicksPerSecond;
        private final Crash crash;
        private final Number cumulativeLayoutShift;
        private final CustomTimings customTimings;
        private final Long domComplete;
        private final Long domContentLoaded;
        private final Long domInteractive;
        private final Error error;
        private final Long firstContentfulPaint;
        private final Long firstInputDelay;
        private final Long firstInputTime;
        private final FrozenFrame frozenFrame;

        /* renamed from: id */
        private final String f134id;
        private final List<InForegroundPeriod> inForegroundPeriods;
        private final Boolean isActive;
        private final Boolean isSlowRendered;
        private final Long largestContentfulPaint;
        private final Long loadEvent;
        private final Long loadingTime;
        private final LoadingType loadingType;
        private final LongTask longTask;
        private final Number memoryAverage;
        private final Number memoryMax;
        private String name;
        private String referrer;
        private final Number refreshRateAverage;
        private final Number refreshRateMin;
        private final Resource resource;
        private final long timeSpent;
        private String url;

        public static /* synthetic */ View copy$default(View view, String str, String str2, String str3, String str4, Long l, LoadingType loadingType2, long j, Long l2, Long l3, Long l4, Long l5, Number number, Long l6, Long l7, Long l8, Long l9, CustomTimings customTimings2, Boolean bool, Boolean bool2, Action action2, Error error2, Crash crash2, LongTask longTask2, FrozenFrame frozenFrame2, Resource resource2, List list, Number number2, Number number3, Number number4, Number number5, Number number6, Number number7, int i, Object obj) {
            View view2 = view;
            int i2 = i;
            return view.copy((i2 & 1) != 0 ? view2.f134id : str, (i2 & 2) != 0 ? view2.referrer : str2, (i2 & 4) != 0 ? view2.url : str3, (i2 & 8) != 0 ? view2.name : str4, (i2 & 16) != 0 ? view2.loadingTime : l, (i2 & 32) != 0 ? view2.loadingType : loadingType2, (i2 & 64) != 0 ? view2.timeSpent : j, (i2 & 128) != 0 ? view2.firstContentfulPaint : l2, (i2 & 256) != 0 ? view2.largestContentfulPaint : l3, (i2 & 512) != 0 ? view2.firstInputDelay : l4, (i2 & 1024) != 0 ? view2.firstInputTime : l5, (i2 & 2048) != 0 ? view2.cumulativeLayoutShift : number, (i2 & 4096) != 0 ? view2.domComplete : l6, (i2 & 8192) != 0 ? view2.domContentLoaded : l7, (i2 & 16384) != 0 ? view2.domInteractive : l8, (i2 & 32768) != 0 ? view2.loadEvent : l9, (i2 & 65536) != 0 ? view2.customTimings : customTimings2, (i2 & 131072) != 0 ? view2.isActive : bool, (i2 & 262144) != 0 ? view2.isSlowRendered : bool2, (i2 & 524288) != 0 ? view2.action : action2, (i2 & 1048576) != 0 ? view2.error : error2, (i2 & 2097152) != 0 ? view2.crash : crash2, (i2 & 4194304) != 0 ? view2.longTask : longTask2, (i2 & 8388608) != 0 ? view2.frozenFrame : frozenFrame2, (i2 & 16777216) != 0 ? view2.resource : resource2, (i2 & 33554432) != 0 ? view2.inForegroundPeriods : list, (i2 & 67108864) != 0 ? view2.memoryAverage : number2, (i2 & 134217728) != 0 ? view2.memoryMax : number3, (i2 & 268435456) != 0 ? view2.cpuTicksCount : number4, (i2 & 536870912) != 0 ? view2.cpuTicksPerSecond : number5, (i2 & BasicMeasure.EXACTLY) != 0 ? view2.refreshRateAverage : number6, (i2 & Integer.MIN_VALUE) != 0 ? view2.refreshRateMin : number7);
        }

        @JvmStatic
        public static final View fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f134id;
        }

        public final Long component10() {
            return this.firstInputDelay;
        }

        public final Long component11() {
            return this.firstInputTime;
        }

        public final Number component12() {
            return this.cumulativeLayoutShift;
        }

        public final Long component13() {
            return this.domComplete;
        }

        public final Long component14() {
            return this.domContentLoaded;
        }

        public final Long component15() {
            return this.domInteractive;
        }

        public final Long component16() {
            return this.loadEvent;
        }

        public final CustomTimings component17() {
            return this.customTimings;
        }

        public final Boolean component18() {
            return this.isActive;
        }

        public final Boolean component19() {
            return this.isSlowRendered;
        }

        public final String component2() {
            return this.referrer;
        }

        public final Action component20() {
            return this.action;
        }

        public final Error component21() {
            return this.error;
        }

        public final Crash component22() {
            return this.crash;
        }

        public final LongTask component23() {
            return this.longTask;
        }

        public final FrozenFrame component24() {
            return this.frozenFrame;
        }

        public final Resource component25() {
            return this.resource;
        }

        public final List<InForegroundPeriod> component26() {
            return this.inForegroundPeriods;
        }

        public final Number component27() {
            return this.memoryAverage;
        }

        public final Number component28() {
            return this.memoryMax;
        }

        public final Number component29() {
            return this.cpuTicksCount;
        }

        public final String component3() {
            return this.url;
        }

        public final Number component30() {
            return this.cpuTicksPerSecond;
        }

        public final Number component31() {
            return this.refreshRateAverage;
        }

        public final Number component32() {
            return this.refreshRateMin;
        }

        public final String component4() {
            return this.name;
        }

        public final Long component5() {
            return this.loadingTime;
        }

        public final LoadingType component6() {
            return this.loadingType;
        }

        public final long component7() {
            return this.timeSpent;
        }

        public final Long component8() {
            return this.firstContentfulPaint;
        }

        public final Long component9() {
            return this.largestContentfulPaint;
        }

        public final View copy(String str, String str2, String str3, String str4, Long l, LoadingType loadingType2, long j, Long l2, Long l3, Long l4, Long l5, Number number, Long l6, Long l7, Long l8, Long l9, CustomTimings customTimings2, Boolean bool, Boolean bool2, Action action2, Error error2, Crash crash2, LongTask longTask2, FrozenFrame frozenFrame2, Resource resource2, List<InForegroundPeriod> list, Number number2, Number number3, Number number4, Number number5, Number number6, Number number7) {
            String str5 = str;
            Intrinsics.checkNotNullParameter(str5, "id");
            Intrinsics.checkNotNullParameter(str3, "url");
            Intrinsics.checkNotNullParameter(action2, "action");
            Intrinsics.checkNotNullParameter(error2, "error");
            Intrinsics.checkNotNullParameter(resource2, "resource");
            return new View(str5, str2, str3, str4, l, loadingType2, j, l2, l3, l4, l5, number, l6, l7, l8, l9, customTimings2, bool, bool2, action2, error2, crash2, longTask2, frozenFrame2, resource2, list, number2, number3, number4, number5, number6, number7);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof View)) {
                return false;
            }
            View view = (View) obj;
            return Intrinsics.areEqual((Object) this.f134id, (Object) view.f134id) && Intrinsics.areEqual((Object) this.referrer, (Object) view.referrer) && Intrinsics.areEqual((Object) this.url, (Object) view.url) && Intrinsics.areEqual((Object) this.name, (Object) view.name) && Intrinsics.areEqual((Object) this.loadingTime, (Object) view.loadingTime) && this.loadingType == view.loadingType && this.timeSpent == view.timeSpent && Intrinsics.areEqual((Object) this.firstContentfulPaint, (Object) view.firstContentfulPaint) && Intrinsics.areEqual((Object) this.largestContentfulPaint, (Object) view.largestContentfulPaint) && Intrinsics.areEqual((Object) this.firstInputDelay, (Object) view.firstInputDelay) && Intrinsics.areEqual((Object) this.firstInputTime, (Object) view.firstInputTime) && Intrinsics.areEqual((Object) this.cumulativeLayoutShift, (Object) view.cumulativeLayoutShift) && Intrinsics.areEqual((Object) this.domComplete, (Object) view.domComplete) && Intrinsics.areEqual((Object) this.domContentLoaded, (Object) view.domContentLoaded) && Intrinsics.areEqual((Object) this.domInteractive, (Object) view.domInteractive) && Intrinsics.areEqual((Object) this.loadEvent, (Object) view.loadEvent) && Intrinsics.areEqual((Object) this.customTimings, (Object) view.customTimings) && Intrinsics.areEqual((Object) this.isActive, (Object) view.isActive) && Intrinsics.areEqual((Object) this.isSlowRendered, (Object) view.isSlowRendered) && Intrinsics.areEqual((Object) this.action, (Object) view.action) && Intrinsics.areEqual((Object) this.error, (Object) view.error) && Intrinsics.areEqual((Object) this.crash, (Object) view.crash) && Intrinsics.areEqual((Object) this.longTask, (Object) view.longTask) && Intrinsics.areEqual((Object) this.frozenFrame, (Object) view.frozenFrame) && Intrinsics.areEqual((Object) this.resource, (Object) view.resource) && Intrinsics.areEqual((Object) this.inForegroundPeriods, (Object) view.inForegroundPeriods) && Intrinsics.areEqual((Object) this.memoryAverage, (Object) view.memoryAverage) && Intrinsics.areEqual((Object) this.memoryMax, (Object) view.memoryMax) && Intrinsics.areEqual((Object) this.cpuTicksCount, (Object) view.cpuTicksCount) && Intrinsics.areEqual((Object) this.cpuTicksPerSecond, (Object) view.cpuTicksPerSecond) && Intrinsics.areEqual((Object) this.refreshRateAverage, (Object) view.refreshRateAverage) && Intrinsics.areEqual((Object) this.refreshRateMin, (Object) view.refreshRateMin);
        }

        public int hashCode() {
            int hashCode = this.f134id.hashCode() * 31;
            String str = this.referrer;
            int i = 0;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.url.hashCode()) * 31;
            String str2 = this.name;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Long l = this.loadingTime;
            int hashCode4 = (hashCode3 + (l == null ? 0 : l.hashCode())) * 31;
            LoadingType loadingType2 = this.loadingType;
            int hashCode5 = (((hashCode4 + (loadingType2 == null ? 0 : loadingType2.hashCode())) * 31) + Long.hashCode(this.timeSpent)) * 31;
            Long l2 = this.firstContentfulPaint;
            int hashCode6 = (hashCode5 + (l2 == null ? 0 : l2.hashCode())) * 31;
            Long l3 = this.largestContentfulPaint;
            int hashCode7 = (hashCode6 + (l3 == null ? 0 : l3.hashCode())) * 31;
            Long l4 = this.firstInputDelay;
            int hashCode8 = (hashCode7 + (l4 == null ? 0 : l4.hashCode())) * 31;
            Long l5 = this.firstInputTime;
            int hashCode9 = (hashCode8 + (l5 == null ? 0 : l5.hashCode())) * 31;
            Number number = this.cumulativeLayoutShift;
            int hashCode10 = (hashCode9 + (number == null ? 0 : number.hashCode())) * 31;
            Long l6 = this.domComplete;
            int hashCode11 = (hashCode10 + (l6 == null ? 0 : l6.hashCode())) * 31;
            Long l7 = this.domContentLoaded;
            int hashCode12 = (hashCode11 + (l7 == null ? 0 : l7.hashCode())) * 31;
            Long l8 = this.domInteractive;
            int hashCode13 = (hashCode12 + (l8 == null ? 0 : l8.hashCode())) * 31;
            Long l9 = this.loadEvent;
            int hashCode14 = (hashCode13 + (l9 == null ? 0 : l9.hashCode())) * 31;
            CustomTimings customTimings2 = this.customTimings;
            int hashCode15 = (hashCode14 + (customTimings2 == null ? 0 : customTimings2.hashCode())) * 31;
            Boolean bool = this.isActive;
            int hashCode16 = (hashCode15 + (bool == null ? 0 : bool.hashCode())) * 31;
            Boolean bool2 = this.isSlowRendered;
            int hashCode17 = (((((hashCode16 + (bool2 == null ? 0 : bool2.hashCode())) * 31) + this.action.hashCode()) * 31) + this.error.hashCode()) * 31;
            Crash crash2 = this.crash;
            int hashCode18 = (hashCode17 + (crash2 == null ? 0 : crash2.hashCode())) * 31;
            LongTask longTask2 = this.longTask;
            int hashCode19 = (hashCode18 + (longTask2 == null ? 0 : longTask2.hashCode())) * 31;
            FrozenFrame frozenFrame2 = this.frozenFrame;
            int hashCode20 = (((hashCode19 + (frozenFrame2 == null ? 0 : frozenFrame2.hashCode())) * 31) + this.resource.hashCode()) * 31;
            List<InForegroundPeriod> list = this.inForegroundPeriods;
            int hashCode21 = (hashCode20 + (list == null ? 0 : list.hashCode())) * 31;
            Number number2 = this.memoryAverage;
            int hashCode22 = (hashCode21 + (number2 == null ? 0 : number2.hashCode())) * 31;
            Number number3 = this.memoryMax;
            int hashCode23 = (hashCode22 + (number3 == null ? 0 : number3.hashCode())) * 31;
            Number number4 = this.cpuTicksCount;
            int hashCode24 = (hashCode23 + (number4 == null ? 0 : number4.hashCode())) * 31;
            Number number5 = this.cpuTicksPerSecond;
            int hashCode25 = (hashCode24 + (number5 == null ? 0 : number5.hashCode())) * 31;
            Number number6 = this.refreshRateAverage;
            int hashCode26 = (hashCode25 + (number6 == null ? 0 : number6.hashCode())) * 31;
            Number number7 = this.refreshRateMin;
            if (number7 != null) {
                i = number7.hashCode();
            }
            return hashCode26 + i;
        }

        public String toString() {
            String str = this.f134id;
            String str2 = this.referrer;
            String str3 = this.url;
            String str4 = this.name;
            Long l = this.loadingTime;
            LoadingType loadingType2 = this.loadingType;
            long j = this.timeSpent;
            Long l2 = this.firstContentfulPaint;
            Long l3 = this.largestContentfulPaint;
            Long l4 = this.firstInputDelay;
            Long l5 = this.firstInputTime;
            Number number = this.cumulativeLayoutShift;
            Long l6 = this.domComplete;
            Long l7 = this.domContentLoaded;
            Long l8 = this.domInteractive;
            Long l9 = this.loadEvent;
            CustomTimings customTimings2 = this.customTimings;
            Boolean bool = this.isActive;
            Boolean bool2 = this.isSlowRendered;
            Action action2 = this.action;
            Error error2 = this.error;
            Crash crash2 = this.crash;
            LongTask longTask2 = this.longTask;
            FrozenFrame frozenFrame2 = this.frozenFrame;
            Resource resource2 = this.resource;
            List<InForegroundPeriod> list = this.inForegroundPeriods;
            Number number2 = this.memoryAverage;
            Number number3 = this.memoryMax;
            Number number4 = this.cpuTicksCount;
            Number number5 = this.cpuTicksPerSecond;
            Number number6 = this.refreshRateAverage;
            return "View(id=" + str + ", referrer=" + str2 + ", url=" + str3 + ", name=" + str4 + ", loadingTime=" + l + ", loadingType=" + loadingType2 + ", timeSpent=" + j + ", firstContentfulPaint=" + l2 + ", largestContentfulPaint=" + l3 + ", firstInputDelay=" + l4 + ", firstInputTime=" + l5 + ", cumulativeLayoutShift=" + number + ", domComplete=" + l6 + ", domContentLoaded=" + l7 + ", domInteractive=" + l8 + ", loadEvent=" + l9 + ", customTimings=" + customTimings2 + ", isActive=" + bool + ", isSlowRendered=" + bool2 + ", action=" + action2 + ", error=" + error2 + ", crash=" + crash2 + ", longTask=" + longTask2 + ", frozenFrame=" + frozenFrame2 + ", resource=" + resource2 + ", inForegroundPeriods=" + list + ", memoryAverage=" + number2 + ", memoryMax=" + number3 + ", cpuTicksCount=" + number4 + ", cpuTicksPerSecond=" + number5 + ", refreshRateAverage=" + number6 + ", refreshRateMin=" + this.refreshRateMin + ")";
        }

        public View(String str, String str2, String str3, String str4, Long l, LoadingType loadingType2, long j, Long l2, Long l3, Long l4, Long l5, Number number, Long l6, Long l7, Long l8, Long l9, CustomTimings customTimings2, Boolean bool, Boolean bool2, Action action2, Error error2, Crash crash2, LongTask longTask2, FrozenFrame frozenFrame2, Resource resource2, List<InForegroundPeriod> list, Number number2, Number number3, Number number4, Number number5, Number number6, Number number7) {
            Action action3 = action2;
            Error error3 = error2;
            Resource resource3 = resource2;
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str3, "url");
            Intrinsics.checkNotNullParameter(action3, "action");
            Intrinsics.checkNotNullParameter(error3, "error");
            Intrinsics.checkNotNullParameter(resource3, "resource");
            this.f134id = str;
            this.referrer = str2;
            this.url = str3;
            this.name = str4;
            this.loadingTime = l;
            this.loadingType = loadingType2;
            this.timeSpent = j;
            this.firstContentfulPaint = l2;
            this.largestContentfulPaint = l3;
            this.firstInputDelay = l4;
            this.firstInputTime = l5;
            this.cumulativeLayoutShift = number;
            this.domComplete = l6;
            this.domContentLoaded = l7;
            this.domInteractive = l8;
            this.loadEvent = l9;
            this.customTimings = customTimings2;
            this.isActive = bool;
            this.isSlowRendered = bool2;
            this.action = action3;
            this.error = error3;
            this.crash = crash2;
            this.longTask = longTask2;
            this.frozenFrame = frozenFrame2;
            this.resource = resource3;
            this.inForegroundPeriods = list;
            this.memoryAverage = number2;
            this.memoryMax = number3;
            this.cpuTicksCount = number4;
            this.cpuTicksPerSecond = number5;
            this.refreshRateAverage = number6;
            this.refreshRateMin = number7;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ View(java.lang.String r38, java.lang.String r39, java.lang.String r40, java.lang.String r41, java.lang.Long r42, com.datadog.android.rum.model.ViewEvent.LoadingType r43, long r44, java.lang.Long r46, java.lang.Long r47, java.lang.Long r48, java.lang.Long r49, java.lang.Number r50, java.lang.Long r51, java.lang.Long r52, java.lang.Long r53, java.lang.Long r54, com.datadog.android.rum.model.ViewEvent.CustomTimings r55, java.lang.Boolean r56, java.lang.Boolean r57, com.datadog.android.rum.model.ViewEvent.Action r58, com.datadog.android.rum.model.ViewEvent.Error r59, com.datadog.android.rum.model.ViewEvent.Crash r60, com.datadog.android.rum.model.ViewEvent.LongTask r61, com.datadog.android.rum.model.ViewEvent.FrozenFrame r62, com.datadog.android.rum.model.ViewEvent.Resource r63, java.util.List r64, java.lang.Number r65, java.lang.Number r66, java.lang.Number r67, java.lang.Number r68, java.lang.Number r69, java.lang.Number r70, int r71, kotlin.jvm.internal.DefaultConstructorMarker r72) {
            /*
                r37 = this;
                r0 = r71
                r1 = r0 & 2
                r2 = 0
                if (r1 == 0) goto L_0x0009
                r5 = r2
                goto L_0x000b
            L_0x0009:
                r5 = r39
            L_0x000b:
                r1 = r0 & 8
                if (r1 == 0) goto L_0x0011
                r7 = r2
                goto L_0x0013
            L_0x0011:
                r7 = r41
            L_0x0013:
                r1 = r0 & 16
                if (r1 == 0) goto L_0x0019
                r8 = r2
                goto L_0x001b
            L_0x0019:
                r8 = r42
            L_0x001b:
                r1 = r0 & 32
                if (r1 == 0) goto L_0x0021
                r9 = r2
                goto L_0x0023
            L_0x0021:
                r9 = r43
            L_0x0023:
                r1 = r0 & 128(0x80, float:1.794E-43)
                if (r1 == 0) goto L_0x0029
                r12 = r2
                goto L_0x002b
            L_0x0029:
                r12 = r46
            L_0x002b:
                r1 = r0 & 256(0x100, float:3.59E-43)
                if (r1 == 0) goto L_0x0031
                r13 = r2
                goto L_0x0033
            L_0x0031:
                r13 = r47
            L_0x0033:
                r1 = r0 & 512(0x200, float:7.175E-43)
                if (r1 == 0) goto L_0x0039
                r14 = r2
                goto L_0x003b
            L_0x0039:
                r14 = r48
            L_0x003b:
                r1 = r0 & 1024(0x400, float:1.435E-42)
                if (r1 == 0) goto L_0x0041
                r15 = r2
                goto L_0x0043
            L_0x0041:
                r15 = r49
            L_0x0043:
                r1 = r0 & 2048(0x800, float:2.87E-42)
                if (r1 == 0) goto L_0x004a
                r16 = r2
                goto L_0x004c
            L_0x004a:
                r16 = r50
            L_0x004c:
                r1 = r0 & 4096(0x1000, float:5.74E-42)
                if (r1 == 0) goto L_0x0053
                r17 = r2
                goto L_0x0055
            L_0x0053:
                r17 = r51
            L_0x0055:
                r1 = r0 & 8192(0x2000, float:1.14794E-41)
                if (r1 == 0) goto L_0x005c
                r18 = r2
                goto L_0x005e
            L_0x005c:
                r18 = r52
            L_0x005e:
                r1 = r0 & 16384(0x4000, float:2.2959E-41)
                if (r1 == 0) goto L_0x0065
                r19 = r2
                goto L_0x0067
            L_0x0065:
                r19 = r53
            L_0x0067:
                r1 = 32768(0x8000, float:4.5918E-41)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x0070
                r20 = r2
                goto L_0x0072
            L_0x0070:
                r20 = r54
            L_0x0072:
                r1 = 65536(0x10000, float:9.18355E-41)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x007a
                r21 = r2
                goto L_0x007c
            L_0x007a:
                r21 = r55
            L_0x007c:
                r1 = 131072(0x20000, float:1.83671E-40)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x0084
                r22 = r2
                goto L_0x0086
            L_0x0084:
                r22 = r56
            L_0x0086:
                r1 = 262144(0x40000, float:3.67342E-40)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x008e
                r23 = r2
                goto L_0x0090
            L_0x008e:
                r23 = r57
            L_0x0090:
                r1 = 2097152(0x200000, float:2.938736E-39)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x0098
                r26 = r2
                goto L_0x009a
            L_0x0098:
                r26 = r60
            L_0x009a:
                r1 = 4194304(0x400000, float:5.877472E-39)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x00a2
                r27 = r2
                goto L_0x00a4
            L_0x00a2:
                r27 = r61
            L_0x00a4:
                r1 = 8388608(0x800000, float:1.17549435E-38)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x00ac
                r28 = r2
                goto L_0x00ae
            L_0x00ac:
                r28 = r62
            L_0x00ae:
                r1 = 33554432(0x2000000, float:9.403955E-38)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x00b6
                r30 = r2
                goto L_0x00b8
            L_0x00b6:
                r30 = r64
            L_0x00b8:
                r1 = 67108864(0x4000000, float:1.5046328E-36)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x00c0
                r31 = r2
                goto L_0x00c2
            L_0x00c0:
                r31 = r65
            L_0x00c2:
                r1 = 134217728(0x8000000, float:3.85186E-34)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x00ca
                r32 = r2
                goto L_0x00cc
            L_0x00ca:
                r32 = r66
            L_0x00cc:
                r1 = 268435456(0x10000000, float:2.5243549E-29)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x00d4
                r33 = r2
                goto L_0x00d6
            L_0x00d4:
                r33 = r67
            L_0x00d6:
                r1 = 536870912(0x20000000, float:1.0842022E-19)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x00de
                r34 = r2
                goto L_0x00e0
            L_0x00de:
                r34 = r68
            L_0x00e0:
                r1 = 1073741824(0x40000000, float:2.0)
                r1 = r1 & r0
                if (r1 == 0) goto L_0x00e8
                r35 = r2
                goto L_0x00ea
            L_0x00e8:
                r35 = r69
            L_0x00ea:
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                r0 = r0 & r1
                if (r0 == 0) goto L_0x00f2
                r36 = r2
                goto L_0x00f4
            L_0x00f2:
                r36 = r70
            L_0x00f4:
                r3 = r37
                r4 = r38
                r6 = r40
                r10 = r44
                r24 = r58
                r25 = r59
                r29 = r63
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ViewEvent.View.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, com.datadog.android.rum.model.ViewEvent$LoadingType, long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Number, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, com.datadog.android.rum.model.ViewEvent$CustomTimings, java.lang.Boolean, java.lang.Boolean, com.datadog.android.rum.model.ViewEvent$Action, com.datadog.android.rum.model.ViewEvent$Error, com.datadog.android.rum.model.ViewEvent$Crash, com.datadog.android.rum.model.ViewEvent$LongTask, com.datadog.android.rum.model.ViewEvent$FrozenFrame, com.datadog.android.rum.model.ViewEvent$Resource, java.util.List, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.Number, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final String getId() {
            return this.f134id;
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

        public final Long getLoadingTime() {
            return this.loadingTime;
        }

        public final LoadingType getLoadingType() {
            return this.loadingType;
        }

        public final long getTimeSpent() {
            return this.timeSpent;
        }

        public final Long getFirstContentfulPaint() {
            return this.firstContentfulPaint;
        }

        public final Long getLargestContentfulPaint() {
            return this.largestContentfulPaint;
        }

        public final Long getFirstInputDelay() {
            return this.firstInputDelay;
        }

        public final Long getFirstInputTime() {
            return this.firstInputTime;
        }

        public final Number getCumulativeLayoutShift() {
            return this.cumulativeLayoutShift;
        }

        public final Long getDomComplete() {
            return this.domComplete;
        }

        public final Long getDomContentLoaded() {
            return this.domContentLoaded;
        }

        public final Long getDomInteractive() {
            return this.domInteractive;
        }

        public final Long getLoadEvent() {
            return this.loadEvent;
        }

        public final CustomTimings getCustomTimings() {
            return this.customTimings;
        }

        public final Boolean isActive() {
            return this.isActive;
        }

        public final Boolean isSlowRendered() {
            return this.isSlowRendered;
        }

        public final Action getAction() {
            return this.action;
        }

        public final Error getError() {
            return this.error;
        }

        public final Crash getCrash() {
            return this.crash;
        }

        public final LongTask getLongTask() {
            return this.longTask;
        }

        public final FrozenFrame getFrozenFrame() {
            return this.frozenFrame;
        }

        public final Resource getResource() {
            return this.resource;
        }

        public final List<InForegroundPeriod> getInForegroundPeriods() {
            return this.inForegroundPeriods;
        }

        public final Number getMemoryAverage() {
            return this.memoryAverage;
        }

        public final Number getMemoryMax() {
            return this.memoryMax;
        }

        public final Number getCpuTicksCount() {
            return this.cpuTicksCount;
        }

        public final Number getCpuTicksPerSecond() {
            return this.cpuTicksPerSecond;
        }

        public final Number getRefreshRateAverage() {
            return this.refreshRateAverage;
        }

        public final Number getRefreshRateMin() {
            return this.refreshRateMin;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f134id);
            String str = this.referrer;
            if (str != null) {
                jsonObject.addProperty("referrer", str);
            }
            jsonObject.addProperty("url", this.url);
            String str2 = this.name;
            if (str2 != null) {
                jsonObject.addProperty("name", str2);
            }
            Long l = this.loadingTime;
            if (l != null) {
                jsonObject.addProperty("loading_time", (Number) Long.valueOf(l.longValue()));
            }
            LoadingType loadingType2 = this.loadingType;
            if (loadingType2 != null) {
                jsonObject.add("loading_type", loadingType2.toJson());
            }
            jsonObject.addProperty("time_spent", (Number) Long.valueOf(this.timeSpent));
            Long l2 = this.firstContentfulPaint;
            if (l2 != null) {
                jsonObject.addProperty("first_contentful_paint", (Number) Long.valueOf(l2.longValue()));
            }
            Long l3 = this.largestContentfulPaint;
            if (l3 != null) {
                jsonObject.addProperty("largest_contentful_paint", (Number) Long.valueOf(l3.longValue()));
            }
            Long l4 = this.firstInputDelay;
            if (l4 != null) {
                jsonObject.addProperty("first_input_delay", (Number) Long.valueOf(l4.longValue()));
            }
            Long l5 = this.firstInputTime;
            if (l5 != null) {
                jsonObject.addProperty("first_input_time", (Number) Long.valueOf(l5.longValue()));
            }
            Number number = this.cumulativeLayoutShift;
            if (number != null) {
                jsonObject.addProperty("cumulative_layout_shift", number);
            }
            Long l6 = this.domComplete;
            if (l6 != null) {
                jsonObject.addProperty("dom_complete", (Number) Long.valueOf(l6.longValue()));
            }
            Long l7 = this.domContentLoaded;
            if (l7 != null) {
                jsonObject.addProperty("dom_content_loaded", (Number) Long.valueOf(l7.longValue()));
            }
            Long l8 = this.domInteractive;
            if (l8 != null) {
                jsonObject.addProperty("dom_interactive", (Number) Long.valueOf(l8.longValue()));
            }
            Long l9 = this.loadEvent;
            if (l9 != null) {
                jsonObject.addProperty("load_event", (Number) Long.valueOf(l9.longValue()));
            }
            CustomTimings customTimings2 = this.customTimings;
            if (customTimings2 != null) {
                jsonObject.add("custom_timings", customTimings2.toJson());
            }
            Boolean bool = this.isActive;
            if (bool != null) {
                jsonObject.addProperty("is_active", Boolean.valueOf(bool.booleanValue()));
            }
            Boolean bool2 = this.isSlowRendered;
            if (bool2 != null) {
                jsonObject.addProperty("is_slow_rendered", Boolean.valueOf(bool2.booleanValue()));
            }
            jsonObject.add("action", this.action.toJson());
            jsonObject.add("error", this.error.toJson());
            Crash crash2 = this.crash;
            if (crash2 != null) {
                jsonObject.add("crash", crash2.toJson());
            }
            LongTask longTask2 = this.longTask;
            if (longTask2 != null) {
                jsonObject.add("long_task", longTask2.toJson());
            }
            FrozenFrame frozenFrame2 = this.frozenFrame;
            if (frozenFrame2 != null) {
                jsonObject.add("frozen_frame", frozenFrame2.toJson());
            }
            jsonObject.add("resource", this.resource.toJson());
            List<InForegroundPeriod> list = this.inForegroundPeriods;
            if (list != null) {
                JsonArray jsonArray = new JsonArray(list.size());
                for (InForegroundPeriod json : list) {
                    jsonArray.add(json.toJson());
                }
                jsonObject.add("in_foreground_periods", jsonArray);
            }
            Number number2 = this.memoryAverage;
            if (number2 != null) {
                jsonObject.addProperty("memory_average", number2);
            }
            Number number3 = this.memoryMax;
            if (number3 != null) {
                jsonObject.addProperty("memory_max", number3);
            }
            Number number4 = this.cpuTicksCount;
            if (number4 != null) {
                jsonObject.addProperty("cpu_ticks_count", number4);
            }
            Number number5 = this.cpuTicksPerSecond;
            if (number5 != null) {
                jsonObject.addProperty("cpu_ticks_per_second", number5);
            }
            Number number6 = this.refreshRateAverage;
            if (number6 != null) {
                jsonObject.addProperty("refresh_rate_average", number6);
            }
            Number number7 = this.refreshRateMin;
            if (number7 != null) {
                jsonObject.addProperty("refresh_rate_min", number7);
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$View$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$View;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARNING: Removed duplicated region for block: B:106:0x024b A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:107:0x024e A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:110:0x025c A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:111:0x025f A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:114:0x026d A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:115:0x0270 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:118:0x027e A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:119:0x0281 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:122:0x028f A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:123:0x0292 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:126:0x02a0 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:127:0x02a3 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x0080 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:24:0x0082 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:27:0x0093 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:28:0x0095 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:31:0x00a6 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:32:0x00a9 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:35:0x00bb A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:36:0x00be A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:39:0x00d0 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:40:0x00d3 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:43:0x00e1 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:44:0x00e4 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:47:0x00f6 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:48:0x00f9 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:51:0x010b A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:52:0x010e A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:55:0x0120 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:56:0x0123 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:60:0x0138 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:66:0x014f A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:67:0x0152 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:70:0x0164 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:71:0x0167 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:75:0x01a2 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:82:0x01bc A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:89:0x01d6 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:96:0x0204 A[Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }] */
            @kotlin.jvm.JvmStatic
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.datadog.android.rum.model.ViewEvent.View fromJson(java.lang.String r40) throws com.google.gson.JsonParseException {
                /*
                    r39 = this;
                    java.lang.String r0 = "url"
                    java.lang.String r1 = "id"
                    java.lang.String r2 = "it"
                    java.lang.String r3 = "serializedObject"
                    r4 = r40
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
                    com.google.gson.JsonElement r3 = com.google.gson.JsonParser.parseString(r40)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.google.gson.JsonObject r3 = r3.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.google.gson.JsonElement r4 = r3.get(r1)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r6 = r4.getAsString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r4 = "referrer"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0027
                    r7 = 0
                    goto L_0x002c
                L_0x0027:
                    java.lang.String r4 = r4.getAsString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r7 = r4
                L_0x002c:
                    com.google.gson.JsonElement r4 = r3.get(r0)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r8 = r4.getAsString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r4 = "name"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x003e
                    r9 = 0
                    goto L_0x0043
                L_0x003e:
                    java.lang.String r4 = r4.getAsString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r9 = r4
                L_0x0043:
                    java.lang.String r4 = "loading_time"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x004d
                    r10 = 0
                    goto L_0x0056
                L_0x004d:
                    long r10 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r10)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r10 = r4
                L_0x0056:
                    java.lang.String r4 = "loading_type"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0060
                L_0x005e:
                    r11 = 0
                    goto L_0x006e
                L_0x0060:
                    java.lang.String r4 = r4.getAsString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0067
                    goto L_0x005e
                L_0x0067:
                    com.datadog.android.rum.model.ViewEvent$LoadingType$Companion r11 = com.datadog.android.rum.model.ViewEvent.LoadingType.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$LoadingType r4 = r11.fromJson(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r11 = r4
                L_0x006e:
                    java.lang.String r4 = "time_spent"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    long r12 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r4 = "first_contentful_paint"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0082
                    r14 = 0
                    goto L_0x008b
                L_0x0082:
                    long r14 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r14)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r14 = r4
                L_0x008b:
                    java.lang.String r4 = "largest_contentful_paint"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0095
                    r15 = 0
                    goto L_0x009e
                L_0x0095:
                    long r15 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r15)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r15 = r4
                L_0x009e:
                    java.lang.String r4 = "first_input_delay"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x00a9
                    r16 = 0
                    goto L_0x00b3
                L_0x00a9:
                    long r16 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r16)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r16 = r4
                L_0x00b3:
                    java.lang.String r4 = "first_input_time"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x00be
                    r17 = 0
                    goto L_0x00c8
                L_0x00be:
                    long r17 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r17)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r17 = r4
                L_0x00c8:
                    java.lang.String r4 = "cumulative_layout_shift"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x00d3
                    r18 = 0
                    goto L_0x00d9
                L_0x00d3:
                    java.lang.Number r4 = r4.getAsNumber()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r18 = r4
                L_0x00d9:
                    java.lang.String r4 = "dom_complete"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x00e4
                    r19 = 0
                    goto L_0x00ee
                L_0x00e4:
                    long r19 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r19)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r19 = r4
                L_0x00ee:
                    java.lang.String r4 = "dom_content_loaded"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x00f9
                    r20 = 0
                    goto L_0x0103
                L_0x00f9:
                    long r20 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r20)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r20 = r4
                L_0x0103:
                    java.lang.String r4 = "dom_interactive"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x010e
                    r21 = 0
                    goto L_0x0118
                L_0x010e:
                    long r21 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r21)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r21 = r4
                L_0x0118:
                    java.lang.String r4 = "load_event"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0123
                    r22 = 0
                    goto L_0x012d
                L_0x0123:
                    long r22 = r4.getAsLong()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Long r4 = java.lang.Long.valueOf(r22)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r22 = r4
                L_0x012d:
                    java.lang.String r4 = "custom_timings"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0138
                L_0x0135:
                    r23 = 0
                    goto L_0x0147
                L_0x0138:
                    java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x013f
                    goto L_0x0135
                L_0x013f:
                    com.datadog.android.rum.model.ViewEvent$CustomTimings$Companion r5 = com.datadog.android.rum.model.ViewEvent.CustomTimings.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$CustomTimings r4 = r5.fromJson(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r23 = r4
                L_0x0147:
                    java.lang.String r4 = "is_active"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0152
                    r24 = 0
                    goto L_0x015c
                L_0x0152:
                    boolean r4 = r4.getAsBoolean()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r24 = r4
                L_0x015c:
                    java.lang.String r4 = "is_slow_rendered"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x0167
                    r25 = 0
                    goto L_0x0171
                L_0x0167:
                    boolean r4 = r4.getAsBoolean()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r25 = r4
                L_0x0171:
                    java.lang.String r4 = "action"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$Action$Companion r5 = com.datadog.android.rum.model.ViewEvent.Action.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$Action r26 = r5.fromJson(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r4 = "error"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$Error$Companion r5 = com.datadog.android.rum.model.ViewEvent.Error.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$Error r27 = r5.fromJson(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r4 = "crash"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x01a2
                L_0x019f:
                    r28 = 0
                    goto L_0x01b1
                L_0x01a2:
                    java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x01a9
                    goto L_0x019f
                L_0x01a9:
                    com.datadog.android.rum.model.ViewEvent$Crash$Companion r5 = com.datadog.android.rum.model.ViewEvent.Crash.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$Crash r4 = r5.fromJson(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r28 = r4
                L_0x01b1:
                    java.lang.String r4 = "long_task"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x01bc
                L_0x01b9:
                    r29 = 0
                    goto L_0x01cb
                L_0x01bc:
                    java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x01c3
                    goto L_0x01b9
                L_0x01c3:
                    com.datadog.android.rum.model.ViewEvent$LongTask$Companion r5 = com.datadog.android.rum.model.ViewEvent.LongTask.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$LongTask r4 = r5.fromJson(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r29 = r4
                L_0x01cb:
                    java.lang.String r4 = "frozen_frame"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x01d6
                L_0x01d3:
                    r30 = 0
                    goto L_0x01e5
                L_0x01d6:
                    java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r4 != 0) goto L_0x01dd
                    goto L_0x01d3
                L_0x01dd:
                    com.datadog.android.rum.model.ViewEvent$FrozenFrame$Companion r5 = com.datadog.android.rum.model.ViewEvent.FrozenFrame.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$FrozenFrame r4 = r5.fromJson(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r30 = r4
                L_0x01e5:
                    java.lang.String r4 = "resource"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$Resource$Companion r5 = com.datadog.android.rum.model.ViewEvent.Resource.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$Resource r31 = r5.fromJson(r4)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r2 = "in_foreground_periods"
                    com.google.gson.JsonElement r2 = r3.get(r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r2 != 0) goto L_0x0204
                L_0x0200:
                    r32 = r15
                    r4 = 0
                    goto L_0x0243
                L_0x0204:
                    com.google.gson.JsonArray r2 = r2.getAsJsonArray()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r2 != 0) goto L_0x020b
                    goto L_0x0200
                L_0x020b:
                    java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    int r5 = r2.size()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r4.<init>(r5)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.util.Iterator r2 = r2.iterator()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                L_0x021a:
                    boolean r5 = r2.hasNext()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r5 == 0) goto L_0x0241
                    java.lang.Object r5 = r2.next()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.google.gson.JsonElement r5 = (com.google.gson.JsonElement) r5     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r40 = r2
                    com.datadog.android.rum.model.ViewEvent$InForegroundPeriod$Companion r2 = com.datadog.android.rum.model.ViewEvent.InForegroundPeriod.Companion     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    java.lang.String r5 = r5.toString()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r32 = r15
                    java.lang.String r15 = "it.toString()"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r15)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    com.datadog.android.rum.model.ViewEvent$InForegroundPeriod r2 = r2.fromJson(r5)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r4.add(r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r2 = r40
                    r15 = r32
                    goto L_0x021a
                L_0x0241:
                    r32 = r15
                L_0x0243:
                    java.lang.String r2 = "memory_average"
                    com.google.gson.JsonElement r2 = r3.get(r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r2 != 0) goto L_0x024e
                    r33 = 0
                    goto L_0x0254
                L_0x024e:
                    java.lang.Number r2 = r2.getAsNumber()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r33 = r2
                L_0x0254:
                    java.lang.String r2 = "memory_max"
                    com.google.gson.JsonElement r2 = r3.get(r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r2 != 0) goto L_0x025f
                    r34 = 0
                    goto L_0x0265
                L_0x025f:
                    java.lang.Number r2 = r2.getAsNumber()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r34 = r2
                L_0x0265:
                    java.lang.String r2 = "cpu_ticks_count"
                    com.google.gson.JsonElement r2 = r3.get(r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r2 != 0) goto L_0x0270
                    r35 = 0
                    goto L_0x0276
                L_0x0270:
                    java.lang.Number r2 = r2.getAsNumber()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r35 = r2
                L_0x0276:
                    java.lang.String r2 = "cpu_ticks_per_second"
                    com.google.gson.JsonElement r2 = r3.get(r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r2 != 0) goto L_0x0281
                    r36 = 0
                    goto L_0x0287
                L_0x0281:
                    java.lang.Number r2 = r2.getAsNumber()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r36 = r2
                L_0x0287:
                    java.lang.String r2 = "refresh_rate_average"
                    com.google.gson.JsonElement r2 = r3.get(r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r2 != 0) goto L_0x0292
                    r37 = 0
                    goto L_0x0298
                L_0x0292:
                    java.lang.Number r2 = r2.getAsNumber()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r37 = r2
                L_0x0298:
                    java.lang.String r2 = "refresh_rate_min"
                    com.google.gson.JsonElement r2 = r3.get(r2)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    if (r2 != 0) goto L_0x02a3
                    r38 = 0
                    goto L_0x02a9
                L_0x02a3:
                    java.lang.Number r2 = r2.getAsNumber()     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r38 = r2
                L_0x02a9:
                    com.datadog.android.rum.model.ViewEvent$View r2 = new com.datadog.android.rum.model.ViewEvent$View     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r0 = r4
                    java.util.List r0 = (java.util.List) r0     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    r5 = r2
                    r15 = r32
                    r32 = r0
                    r5.<init>(r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38)     // Catch:{ IllegalStateException -> 0x02c8, NumberFormatException -> 0x02bd }
                    return r2
                L_0x02bd:
                    r0 = move-exception
                    com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                    java.lang.String r0 = r0.getMessage()
                    r1.<init>((java.lang.String) r0)
                    throw r1
                L_0x02c8:
                    r0 = move-exception
                    com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                    java.lang.String r0 = r0.getMessage()
                    r1.<init>((java.lang.String) r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ViewEvent.View.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ViewEvent$View");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0003JE\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Usr;", "", "id", "", "name", "email", "additionalProperties", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "getEmail", "()Ljava/lang/String;", "getId", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class Usr {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final String[] RESERVED_PROPERTIES = {"id", "name", NotificationCompat.CATEGORY_EMAIL};
        private final Map<String, Object> additionalProperties;
        private final String email;

        /* renamed from: id */
        private final String f133id;
        private final String name;

        public Usr() {
            this((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Usr copy$default(Usr usr, String str, String str2, String str3, Map<String, Object> map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = usr.f133id;
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
            return this.f133id;
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
            return Intrinsics.areEqual((Object) this.f133id, (Object) usr.f133id) && Intrinsics.areEqual((Object) this.name, (Object) usr.name) && Intrinsics.areEqual((Object) this.email, (Object) usr.email) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) usr.additionalProperties);
        }

        public int hashCode() {
            String str = this.f133id;
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
            String str = this.f133id;
            String str2 = this.name;
            String str3 = this.email;
            return "Usr(id=" + str + ", name=" + str2 + ", email=" + str3 + ", additionalProperties=" + this.additionalProperties + ")";
        }

        public Usr(String str, String str2, String str3, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.f133id = str;
            this.name = str2;
            this.email = str3;
            this.additionalProperties = map;
        }

        public final String getId() {
            return this.f133id;
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
            String str = this.f133id;
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

        @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Usr$Companion;", "", "()V", "RESERVED_PROPERTIES", "", "", "getRESERVED_PROPERTIES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Usr;", "serializedObject", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Connectivity;", "", "status", "Lcom/datadog/android/rum/model/ViewEvent$Status;", "interfaces", "", "Lcom/datadog/android/rum/model/ViewEvent$Interface;", "cellular", "Lcom/datadog/android/rum/model/ViewEvent$Cellular;", "(Lcom/datadog/android/rum/model/ViewEvent$Status;Ljava/util/List;Lcom/datadog/android/rum/model/ViewEvent$Cellular;)V", "getCellular", "()Lcom/datadog/android/rum/model/ViewEvent$Cellular;", "getInterfaces", "()Ljava/util/List;", "getStatus", "()Lcom/datadog/android/rum/model/ViewEvent$Status;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Connectivity$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Connectivity;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\tJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Synthetics;", "", "testId", "", "resultId", "injected", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getInjected", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getResultId", "()Ljava/lang/String;", "getTestId", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/ViewEvent$Synthetics;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Synthetics$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Synthetics;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$CiTest;", "", "testExecutionId", "", "(Ljava/lang/String;)V", "getTestExecutionId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$CiTest$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$CiTest;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J+\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0006\u0010\u001a\u001a\u00020\u001bJ\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001e"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Dd;", "", "session", "Lcom/datadog/android/rum/model/ViewEvent$DdSession;", "browserSdkVersion", "", "documentVersion", "", "(Lcom/datadog/android/rum/model/ViewEvent$DdSession;Ljava/lang/String;J)V", "getBrowserSdkVersion", "()Ljava/lang/String;", "getDocumentVersion", "()J", "formatVersion", "getFormatVersion", "getSession", "()Lcom/datadog/android/rum/model/ViewEvent$DdSession;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* renamed from: com.datadog.android.rum.model.ViewEvent$Dd */
    /* compiled from: ViewEvent.kt */
    public static final class C0867Dd {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String browserSdkVersion;
        private final long documentVersion;
        private final long formatVersion;
        private final DdSession session;

        public static /* synthetic */ C0867Dd copy$default(C0867Dd dd, DdSession ddSession, String str, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                ddSession = dd.session;
            }
            if ((i & 2) != 0) {
                str = dd.browserSdkVersion;
            }
            if ((i & 4) != 0) {
                j = dd.documentVersion;
            }
            return dd.copy(ddSession, str, j);
        }

        @JvmStatic
        public static final C0867Dd fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final DdSession component1() {
            return this.session;
        }

        public final String component2() {
            return this.browserSdkVersion;
        }

        public final long component3() {
            return this.documentVersion;
        }

        public final C0867Dd copy(DdSession ddSession, String str, long j) {
            return new C0867Dd(ddSession, str, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0867Dd)) {
                return false;
            }
            C0867Dd dd = (C0867Dd) obj;
            return Intrinsics.areEqual((Object) this.session, (Object) dd.session) && Intrinsics.areEqual((Object) this.browserSdkVersion, (Object) dd.browserSdkVersion) && this.documentVersion == dd.documentVersion;
        }

        public int hashCode() {
            DdSession ddSession = this.session;
            int i = 0;
            int hashCode = (ddSession == null ? 0 : ddSession.hashCode()) * 31;
            String str = this.browserSdkVersion;
            if (str != null) {
                i = str.hashCode();
            }
            return ((hashCode + i) * 31) + Long.hashCode(this.documentVersion);
        }

        public String toString() {
            DdSession ddSession = this.session;
            String str = this.browserSdkVersion;
            return "Dd(session=" + ddSession + ", browserSdkVersion=" + str + ", documentVersion=" + this.documentVersion + ")";
        }

        public C0867Dd(DdSession ddSession, String str, long j) {
            this.session = ddSession;
            this.browserSdkVersion = str;
            this.documentVersion = j;
            this.formatVersion = 2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ C0867Dd(DdSession ddSession, String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : ddSession, (i & 2) != 0 ? null : str, j);
        }

        public final DdSession getSession() {
            return this.session;
        }

        public final String getBrowserSdkVersion() {
            return this.browserSdkVersion;
        }

        public final long getDocumentVersion() {
            return this.documentVersion;
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
            jsonObject.addProperty("document_version", (Number) Long.valueOf(this.documentVersion));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Dd$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Dd;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* renamed from: com.datadog.android.rum.model.ViewEvent$Dd$Companion */
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARNING: Removed duplicated region for block: B:11:0x002d A[Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }] */
            /* JADX WARNING: Removed duplicated region for block: B:12:0x002e A[Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }] */
            @kotlin.jvm.JvmStatic
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.datadog.android.rum.model.ViewEvent.C0867Dd fromJson(java.lang.String r4) throws com.google.gson.JsonParseException {
                /*
                    r3 = this;
                    java.lang.String r3 = "serializedObject"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
                    com.google.gson.JsonElement r3 = com.google.gson.JsonParser.parseString(r4)     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    com.google.gson.JsonObject r3 = r3.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    java.lang.String r4 = "session"
                    com.google.gson.JsonElement r4 = r3.get(r4)     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    r0 = 0
                    if (r4 != 0) goto L_0x0018
                L_0x0016:
                    r4 = r0
                    goto L_0x0025
                L_0x0018:
                    java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    if (r4 != 0) goto L_0x001f
                    goto L_0x0016
                L_0x001f:
                    com.datadog.android.rum.model.ViewEvent$DdSession$Companion r1 = com.datadog.android.rum.model.ViewEvent.DdSession.Companion     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    com.datadog.android.rum.model.ViewEvent$DdSession r4 = r1.fromJson(r4)     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                L_0x0025:
                    java.lang.String r1 = "browser_sdk_version"
                    com.google.gson.JsonElement r1 = r3.get(r1)     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    if (r1 != 0) goto L_0x002e
                    goto L_0x0032
                L_0x002e:
                    java.lang.String r0 = r1.getAsString()     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                L_0x0032:
                    java.lang.String r1 = "document_version"
                    com.google.gson.JsonElement r3 = r3.get(r1)     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    long r1 = r3.getAsLong()     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    com.datadog.android.rum.model.ViewEvent$Dd r3 = new com.datadog.android.rum.model.ViewEvent$Dd     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    r3.<init>(r4, r0, r1)     // Catch:{ IllegalStateException -> 0x004d, NumberFormatException -> 0x0042 }
                    return r3
                L_0x0042:
                    r3 = move-exception
                    com.google.gson.JsonParseException r4 = new com.google.gson.JsonParseException
                    java.lang.String r3 = r3.getMessage()
                    r4.<init>((java.lang.String) r3)
                    throw r4
                L_0x004d:
                    r3 = move-exception
                    com.google.gson.JsonParseException r4 = new com.google.gson.JsonParseException
                    java.lang.String r3 = r3.getMessage()
                    r4.<init>((java.lang.String) r3)
                    throw r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ViewEvent.C0867Dd.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ViewEvent$Dd");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003HÆ\u0003J!\u0010\t\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Context;", "", "additionalProperties", "", "", "(Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Context$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Context;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u001b\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0015\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u001f\u0010\n\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0006\u0010\u0010\u001a\u00020\u0011J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$CustomTimings;", "", "additionalProperties", "", "", "", "(Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class CustomTimings {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Map<String, Long> additionalProperties;

        public CustomTimings() {
            this((Map) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ CustomTimings copy$default(CustomTimings customTimings, Map<String, Long> map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = customTimings.additionalProperties;
            }
            return customTimings.copy(map);
        }

        @JvmStatic
        public static final CustomTimings fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final Map<String, Long> component1() {
            return this.additionalProperties;
        }

        public final CustomTimings copy(Map<String, Long> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            return new CustomTimings(map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CustomTimings) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) ((CustomTimings) obj).additionalProperties);
        }

        public int hashCode() {
            return this.additionalProperties.hashCode();
        }

        public String toString() {
            return "CustomTimings(additionalProperties=" + this.additionalProperties + ")";
        }

        public CustomTimings(Map<String, Long> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.additionalProperties = map;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ CustomTimings(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, Long> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry next : this.additionalProperties.entrySet()) {
                jsonObject.addProperty((String) next.getKey(), (Number) Long.valueOf(((Number) next.getValue()).longValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$CustomTimings$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$CustomTimings;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final CustomTimings fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    Map linkedHashMap = new LinkedHashMap();
                    for (Map.Entry next : asJsonObject.entrySet()) {
                        Object key = next.getKey();
                        Intrinsics.checkNotNullExpressionValue(key, "entry.key");
                        linkedHashMap.put(key, Long.valueOf(((JsonElement) next.getValue()).getAsLong()));
                    }
                    return new CustomTimings(linkedHashMap);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Action;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class Action {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long count;

        public static /* synthetic */ Action copy$default(Action action, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = action.count;
            }
            return action.copy(j);
        }

        @JvmStatic
        public static final Action fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.count;
        }

        public final Action copy(long j) {
            return new Action(j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Action) && this.count == ((Action) obj).count;
        }

        public int hashCode() {
            return Long.hashCode(this.count);
        }

        public String toString() {
            return "Action(count=" + this.count + ")";
        }

        public Action(long j) {
            this.count = j;
        }

        public final long getCount() {
            return this.count;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("count", (Number) Long.valueOf(this.count));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Action$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Action;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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
                    return new Action(JsonParser.parseString(str).getAsJsonObject().get("count").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Error;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class Error {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long count;

        public static /* synthetic */ Error copy$default(Error error, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = error.count;
            }
            return error.copy(j);
        }

        @JvmStatic
        public static final Error fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.count;
        }

        public final Error copy(long j) {
            return new Error(j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Error) && this.count == ((Error) obj).count;
        }

        public int hashCode() {
            return Long.hashCode(this.count);
        }

        public String toString() {
            return "Error(count=" + this.count + ")";
        }

        public Error(long j) {
            this.count = j;
        }

        public final long getCount() {
            return this.count;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("count", (Number) Long.valueOf(this.count));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Error$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Error;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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
                    return new Error(JsonParser.parseString(str).getAsJsonObject().get("count").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Crash;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class Crash {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long count;

        public static /* synthetic */ Crash copy$default(Crash crash, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = crash.count;
            }
            return crash.copy(j);
        }

        @JvmStatic
        public static final Crash fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.count;
        }

        public final Crash copy(long j) {
            return new Crash(j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Crash) && this.count == ((Crash) obj).count;
        }

        public int hashCode() {
            return Long.hashCode(this.count);
        }

        public String toString() {
            return "Crash(count=" + this.count + ")";
        }

        public Crash(long j) {
            this.count = j;
        }

        public final long getCount() {
            return this.count;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("count", (Number) Long.valueOf(this.count));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Crash$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Crash;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Crash fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    return new Crash(JsonParser.parseString(str).getAsJsonObject().get("count").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$LongTask;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class LongTask {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long count;

        public static /* synthetic */ LongTask copy$default(LongTask longTask, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = longTask.count;
            }
            return longTask.copy(j);
        }

        @JvmStatic
        public static final LongTask fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.count;
        }

        public final LongTask copy(long j) {
            return new LongTask(j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof LongTask) && this.count == ((LongTask) obj).count;
        }

        public int hashCode() {
            return Long.hashCode(this.count);
        }

        public String toString() {
            return "LongTask(count=" + this.count + ")";
        }

        public LongTask(long j) {
            this.count = j;
        }

        public final long getCount() {
            return this.count;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("count", (Number) Long.valueOf(this.count));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$LongTask$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$LongTask;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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
                    return new LongTask(JsonParser.parseString(str).getAsJsonObject().get("count").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$FrozenFrame;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class FrozenFrame {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long count;

        public static /* synthetic */ FrozenFrame copy$default(FrozenFrame frozenFrame, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = frozenFrame.count;
            }
            return frozenFrame.copy(j);
        }

        @JvmStatic
        public static final FrozenFrame fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.count;
        }

        public final FrozenFrame copy(long j) {
            return new FrozenFrame(j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof FrozenFrame) && this.count == ((FrozenFrame) obj).count;
        }

        public int hashCode() {
            return Long.hashCode(this.count);
        }

        public String toString() {
            return "FrozenFrame(count=" + this.count + ")";
        }

        public FrozenFrame(long j) {
            this.count = j;
        }

        public final long getCount() {
            return this.count;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("count", (Number) Long.valueOf(this.count));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$FrozenFrame$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$FrozenFrame;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final FrozenFrame fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    return new FrozenFrame(JsonParser.parseString(str).getAsJsonObject().get("count").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Resource;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class Resource {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long count;

        public static /* synthetic */ Resource copy$default(Resource resource, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = resource.count;
            }
            return resource.copy(j);
        }

        @JvmStatic
        public static final Resource fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.count;
        }

        public final Resource copy(long j) {
            return new Resource(j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Resource) && this.count == ((Resource) obj).count;
        }

        public int hashCode() {
            return Long.hashCode(this.count);
        }

        public String toString() {
            return "Resource(count=" + this.count + ")";
        }

        public Resource(long j) {
            this.count = j;
        }

        public final long getCount() {
            return this.count;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("count", (Number) Long.valueOf(this.count));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Resource$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Resource;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Resource fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    return new Resource(JsonParser.parseString(str).getAsJsonObject().get("count").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$InForegroundPeriod;", "", "start", "", "duration", "(JJ)V", "getDuration", "()J", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public static final class InForegroundPeriod {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long duration;
        private final long start;

        public static /* synthetic */ InForegroundPeriod copy$default(InForegroundPeriod inForegroundPeriod, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = inForegroundPeriod.start;
            }
            if ((i & 2) != 0) {
                j2 = inForegroundPeriod.duration;
            }
            return inForegroundPeriod.copy(j, j2);
        }

        @JvmStatic
        public static final InForegroundPeriod fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.start;
        }

        public final long component2() {
            return this.duration;
        }

        public final InForegroundPeriod copy(long j, long j2) {
            return new InForegroundPeriod(j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InForegroundPeriod)) {
                return false;
            }
            InForegroundPeriod inForegroundPeriod = (InForegroundPeriod) obj;
            return this.start == inForegroundPeriod.start && this.duration == inForegroundPeriod.duration;
        }

        public int hashCode() {
            return (Long.hashCode(this.start) * 31) + Long.hashCode(this.duration);
        }

        public String toString() {
            long j = this.start;
            return "InForegroundPeriod(start=" + j + ", duration=" + this.duration + ")";
        }

        public InForegroundPeriod(long j, long j2) {
            this.start = j;
            this.duration = j2;
        }

        public final long getStart() {
            return this.start;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("start", (Number) Long.valueOf(this.start));
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$InForegroundPeriod$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$InForegroundPeriod;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final InForegroundPeriod fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    return new InForegroundPeriod(asJsonObject.get("start").getAsLong(), asJsonObject.get(LogAttributes.DURATION).getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Cellular;", "", "technology", "", "carrierName", "(Ljava/lang/String;Ljava/lang/String;)V", "getCarrierName", "()Ljava/lang/String;", "getTechnology", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Cellular$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Cellular;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$DdSession;", "", "plan", "Lcom/datadog/android/rum/model/ViewEvent$Plan;", "(Lcom/datadog/android/rum/model/ViewEvent$Plan;)V", "getPlan", "()Lcom/datadog/android/rum/model/ViewEvent$Plan;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$DdSession$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$DdSession;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Source;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "ANDROID", "IOS", "BROWSER", "FLUTTER", "REACT_NATIVE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Source$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Source;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Type;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "USER", "SYNTHETICS", "CI_TEST", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Type$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Type;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "INITIAL_LOAD", "ROUTE_CHANGE", "ACTIVITY_DISPLAY", "ACTIVITY_REDISPLAY", "FRAGMENT_DISPLAY", "FRAGMENT_REDISPLAY", "VIEW_CONTROLLER_DISPLAY", "VIEW_CONTROLLER_REDISPLAY", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
    public enum LoadingType {
        INITIAL_LOAD("initial_load"),
        ROUTE_CHANGE("route_change"),
        ACTIVITY_DISPLAY("activity_display"),
        ACTIVITY_REDISPLAY("activity_redisplay"),
        FRAGMENT_DISPLAY("fragment_display"),
        FRAGMENT_REDISPLAY("fragment_redisplay"),
        VIEW_CONTROLLER_DISPLAY("view_controller_display"),
        VIEW_CONTROLLER_REDISPLAY("view_controller_redisplay");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final LoadingType fromJson(String str) {
            return Companion.fromJson(str);
        }

        private LoadingType(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$LoadingType$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final LoadingType fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                LoadingType[] values = LoadingType.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    LoadingType loadingType = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) loadingType.jsonValue, (Object) str)) {
                        return loadingType;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Status;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "CONNECTED", "NOT_CONNECTED", "MAYBE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Status$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Status;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Interface;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "BLUETOOTH", "CELLULAR", "ETHERNET", "WIFI", "WIMAX", "MIXED", "OTHER", "UNKNOWN", "NONE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Interface$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Interface;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Plan;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/Number;)V", "toJson", "Lcom/google/gson/JsonElement;", "PLAN_1", "PLAN_2", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ViewEvent$Plan$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ViewEvent$Plan;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ViewEvent.kt */
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

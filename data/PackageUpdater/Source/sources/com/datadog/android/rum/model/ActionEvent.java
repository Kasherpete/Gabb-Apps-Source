package com.datadog.android.rum.model;

import androidx.core.app.NotificationCompat;
import androidx.core.p003os.EnvironmentCompat;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.utils.MiscUtilsKt;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.rum.internal.instrumentation.gestures.WindowCallbackWrapper;
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

@Metadata(mo20734d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\b\b\u0018\u0000 V2\u00020\u0001:\u0018OPQRSTUVWXYZ[\\]^_`abcdefB\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cJ\t\u00109\u001a\u00020\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\t\u0010;\u001a\u00020\u0017HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0019HÆ\u0003J\t\u0010=\u001a\u00020\u001bHÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010@\u001a\u00020\tHÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010B\u001a\u00020\rHÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bHÆ\u0001J\u0013\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010J\u001a\u00020KHÖ\u0001J\u0006\u0010L\u001a\u00020MJ\t\u0010N\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b4\u0010,R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b7\u00108¨\u0006g"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent;", "", "date", "", "application", "Lcom/datadog/android/rum/model/ActionEvent$Application;", "service", "", "session", "Lcom/datadog/android/rum/model/ActionEvent$ActionEventSession;", "source", "Lcom/datadog/android/rum/model/ActionEvent$Source;", "view", "Lcom/datadog/android/rum/model/ActionEvent$View;", "usr", "Lcom/datadog/android/rum/model/ActionEvent$Usr;", "connectivity", "Lcom/datadog/android/rum/model/ActionEvent$Connectivity;", "synthetics", "Lcom/datadog/android/rum/model/ActionEvent$Synthetics;", "ciTest", "Lcom/datadog/android/rum/model/ActionEvent$CiTest;", "dd", "Lcom/datadog/android/rum/model/ActionEvent$Dd;", "context", "Lcom/datadog/android/rum/model/ActionEvent$Context;", "action", "Lcom/datadog/android/rum/model/ActionEvent$Action;", "(JLcom/datadog/android/rum/model/ActionEvent$Application;Ljava/lang/String;Lcom/datadog/android/rum/model/ActionEvent$ActionEventSession;Lcom/datadog/android/rum/model/ActionEvent$Source;Lcom/datadog/android/rum/model/ActionEvent$View;Lcom/datadog/android/rum/model/ActionEvent$Usr;Lcom/datadog/android/rum/model/ActionEvent$Connectivity;Lcom/datadog/android/rum/model/ActionEvent$Synthetics;Lcom/datadog/android/rum/model/ActionEvent$CiTest;Lcom/datadog/android/rum/model/ActionEvent$Dd;Lcom/datadog/android/rum/model/ActionEvent$Context;Lcom/datadog/android/rum/model/ActionEvent$Action;)V", "getAction", "()Lcom/datadog/android/rum/model/ActionEvent$Action;", "getApplication", "()Lcom/datadog/android/rum/model/ActionEvent$Application;", "getCiTest", "()Lcom/datadog/android/rum/model/ActionEvent$CiTest;", "getConnectivity", "()Lcom/datadog/android/rum/model/ActionEvent$Connectivity;", "getContext", "()Lcom/datadog/android/rum/model/ActionEvent$Context;", "getDate", "()J", "getDd", "()Lcom/datadog/android/rum/model/ActionEvent$Dd;", "getService", "()Ljava/lang/String;", "getSession", "()Lcom/datadog/android/rum/model/ActionEvent$ActionEventSession;", "getSource", "()Lcom/datadog/android/rum/model/ActionEvent$Source;", "getSynthetics", "()Lcom/datadog/android/rum/model/ActionEvent$Synthetics;", "type", "getType", "getUsr", "()Lcom/datadog/android/rum/model/ActionEvent$Usr;", "getView", "()Lcom/datadog/android/rum/model/ActionEvent$View;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Action", "ActionEventSession", "ActionEventSessionType", "ActionType", "Application", "Cellular", "CiTest", "Companion", "Connectivity", "Context", "Crash", "Dd", "DdSession", "Error", "Interface", "LongTask", "Plan", "Resource", "Source", "Status", "Synthetics", "Target", "Usr", "View", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ActionEvent.kt */
public final class ActionEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Action action;
    private final Application application;
    private final CiTest ciTest;
    private final Connectivity connectivity;
    private final Context context;
    private final long date;

    /* renamed from: dd */
    private final C0863Dd f101dd;
    private final String service;
    private final ActionEventSession session;
    private final Source source;
    private final Synthetics synthetics;
    private final String type;
    private final Usr usr;
    private final View view;

    public static /* synthetic */ ActionEvent copy$default(ActionEvent actionEvent, long j, Application application2, String str, ActionEventSession actionEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0863Dd dd, Context context2, Action action2, int i, Object obj) {
        ActionEvent actionEvent2 = actionEvent;
        int i2 = i;
        return actionEvent.copy((i2 & 1) != 0 ? actionEvent2.date : j, (i2 & 2) != 0 ? actionEvent2.application : application2, (i2 & 4) != 0 ? actionEvent2.service : str, (i2 & 8) != 0 ? actionEvent2.session : actionEventSession, (i2 & 16) != 0 ? actionEvent2.source : source2, (i2 & 32) != 0 ? actionEvent2.view : view2, (i2 & 64) != 0 ? actionEvent2.usr : usr2, (i2 & 128) != 0 ? actionEvent2.connectivity : connectivity2, (i2 & 256) != 0 ? actionEvent2.synthetics : synthetics2, (i2 & 512) != 0 ? actionEvent2.ciTest : ciTest2, (i2 & 1024) != 0 ? actionEvent2.f101dd : dd, (i2 & 2048) != 0 ? actionEvent2.context : context2, (i2 & 4096) != 0 ? actionEvent2.action : action2);
    }

    @JvmStatic
    public static final ActionEvent fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final long component1() {
        return this.date;
    }

    public final CiTest component10() {
        return this.ciTest;
    }

    public final C0863Dd component11() {
        return this.f101dd;
    }

    public final Context component12() {
        return this.context;
    }

    public final Action component13() {
        return this.action;
    }

    public final Application component2() {
        return this.application;
    }

    public final String component3() {
        return this.service;
    }

    public final ActionEventSession component4() {
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

    public final ActionEvent copy(long j, Application application2, String str, ActionEventSession actionEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0863Dd dd, Context context2, Action action2) {
        Application application3 = application2;
        Intrinsics.checkNotNullParameter(application3, WebViewRumEventMapper.APPLICATION_KEY_NAME);
        ActionEventSession actionEventSession2 = actionEventSession;
        Intrinsics.checkNotNullParameter(actionEventSession2, "session");
        View view3 = view2;
        Intrinsics.checkNotNullParameter(view3, "view");
        C0863Dd dd2 = dd;
        Intrinsics.checkNotNullParameter(dd2, "dd");
        Action action3 = action2;
        Intrinsics.checkNotNullParameter(action3, "action");
        return new ActionEvent(j, application3, str, actionEventSession2, source2, view3, usr2, connectivity2, synthetics2, ciTest2, dd2, context2, action3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionEvent)) {
            return false;
        }
        ActionEvent actionEvent = (ActionEvent) obj;
        return this.date == actionEvent.date && Intrinsics.areEqual((Object) this.application, (Object) actionEvent.application) && Intrinsics.areEqual((Object) this.service, (Object) actionEvent.service) && Intrinsics.areEqual((Object) this.session, (Object) actionEvent.session) && this.source == actionEvent.source && Intrinsics.areEqual((Object) this.view, (Object) actionEvent.view) && Intrinsics.areEqual((Object) this.usr, (Object) actionEvent.usr) && Intrinsics.areEqual((Object) this.connectivity, (Object) actionEvent.connectivity) && Intrinsics.areEqual((Object) this.synthetics, (Object) actionEvent.synthetics) && Intrinsics.areEqual((Object) this.ciTest, (Object) actionEvent.ciTest) && Intrinsics.areEqual((Object) this.f101dd, (Object) actionEvent.f101dd) && Intrinsics.areEqual((Object) this.context, (Object) actionEvent.context) && Intrinsics.areEqual((Object) this.action, (Object) actionEvent.action);
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
        int hashCode7 = (((hashCode6 + (ciTest2 == null ? 0 : ciTest2.hashCode())) * 31) + this.f101dd.hashCode()) * 31;
        Context context2 = this.context;
        if (context2 != null) {
            i = context2.hashCode();
        }
        return ((hashCode7 + i) * 31) + this.action.hashCode();
    }

    public String toString() {
        long j = this.date;
        Application application2 = this.application;
        String str = this.service;
        ActionEventSession actionEventSession = this.session;
        Source source2 = this.source;
        View view2 = this.view;
        Usr usr2 = this.usr;
        Connectivity connectivity2 = this.connectivity;
        Synthetics synthetics2 = this.synthetics;
        CiTest ciTest2 = this.ciTest;
        C0863Dd dd = this.f101dd;
        Context context2 = this.context;
        return "ActionEvent(date=" + j + ", application=" + application2 + ", service=" + str + ", session=" + actionEventSession + ", source=" + source2 + ", view=" + view2 + ", usr=" + usr2 + ", connectivity=" + connectivity2 + ", synthetics=" + synthetics2 + ", ciTest=" + ciTest2 + ", dd=" + dd + ", context=" + context2 + ", action=" + this.action + ")";
    }

    public ActionEvent(long j, Application application2, String str, ActionEventSession actionEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0863Dd dd, Context context2, Action action2) {
        Intrinsics.checkNotNullParameter(application2, WebViewRumEventMapper.APPLICATION_KEY_NAME);
        Intrinsics.checkNotNullParameter(actionEventSession, "session");
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(dd, "dd");
        Intrinsics.checkNotNullParameter(action2, "action");
        this.date = j;
        this.application = application2;
        this.service = str;
        this.session = actionEventSession;
        this.source = source2;
        this.view = view2;
        this.usr = usr2;
        this.connectivity = connectivity2;
        this.synthetics = synthetics2;
        this.ciTest = ciTest2;
        this.f101dd = dd;
        this.context = context2;
        this.action = action2;
        this.type = "action";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ActionEvent(long r19, com.datadog.android.rum.model.ActionEvent.Application r21, java.lang.String r22, com.datadog.android.rum.model.ActionEvent.ActionEventSession r23, com.datadog.android.rum.model.ActionEvent.Source r24, com.datadog.android.rum.model.ActionEvent.View r25, com.datadog.android.rum.model.ActionEvent.Usr r26, com.datadog.android.rum.model.ActionEvent.Connectivity r27, com.datadog.android.rum.model.ActionEvent.Synthetics r28, com.datadog.android.rum.model.ActionEvent.CiTest r29, com.datadog.android.rum.model.ActionEvent.C0863Dd r30, com.datadog.android.rum.model.ActionEvent.Context r31, com.datadog.android.rum.model.ActionEvent.Action r32, int r33, kotlin.jvm.internal.DefaultConstructorMarker r34) {
        /*
            r18 = this;
            r0 = r33
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r7 = r2
            goto L_0x000b
        L_0x0009:
            r7 = r22
        L_0x000b:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0011
            r9 = r2
            goto L_0x0013
        L_0x0011:
            r9 = r24
        L_0x0013:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0019
            r11 = r2
            goto L_0x001b
        L_0x0019:
            r11 = r26
        L_0x001b:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0021
            r12 = r2
            goto L_0x0023
        L_0x0021:
            r12 = r27
        L_0x0023:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0029
            r13 = r2
            goto L_0x002b
        L_0x0029:
            r13 = r28
        L_0x002b:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0031
            r14 = r2
            goto L_0x0033
        L_0x0031:
            r14 = r29
        L_0x0033:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x003a
            r16 = r2
            goto L_0x003c
        L_0x003a:
            r16 = r31
        L_0x003c:
            r3 = r18
            r4 = r19
            r6 = r21
            r8 = r23
            r10 = r25
            r15 = r30
            r17 = r32
            r3.<init>(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ActionEvent.<init>(long, com.datadog.android.rum.model.ActionEvent$Application, java.lang.String, com.datadog.android.rum.model.ActionEvent$ActionEventSession, com.datadog.android.rum.model.ActionEvent$Source, com.datadog.android.rum.model.ActionEvent$View, com.datadog.android.rum.model.ActionEvent$Usr, com.datadog.android.rum.model.ActionEvent$Connectivity, com.datadog.android.rum.model.ActionEvent$Synthetics, com.datadog.android.rum.model.ActionEvent$CiTest, com.datadog.android.rum.model.ActionEvent$Dd, com.datadog.android.rum.model.ActionEvent$Context, com.datadog.android.rum.model.ActionEvent$Action, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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

    public final ActionEventSession getSession() {
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

    public final C0863Dd getDd() {
        return this.f101dd;
    }

    public final Context getContext() {
        return this.context;
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
        jsonObject.add(WebViewRumEventMapper.DD_KEY_NAME, this.f101dd.toJson());
        Context context2 = this.context;
        if (context2 != null) {
            jsonObject.add(RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX, context2.toJson());
        }
        jsonObject.addProperty(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type);
        jsonObject.add("action", this.action.toJson());
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0086 A[Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x009e A[Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }] */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00b6 A[Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00ce A[Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x00fa A[Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }] */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.datadog.android.rum.model.ActionEvent fromJson(java.lang.String r19) throws com.google.gson.JsonParseException {
            /*
                r18 = this;
                java.lang.String r0 = "it"
                java.lang.String r1 = "serializedObject"
                r2 = r19
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                com.google.gson.JsonElement r1 = com.google.gson.JsonParser.parseString(r19)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.google.gson.JsonObject r1 = r1.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = "date"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                long r4 = r2.getAsLong()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = "application"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Application$Companion r3 = com.datadog.android.rum.model.ActionEvent.Application.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Application r6 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = "service"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r3 = 0
                if (r2 != 0) goto L_0x0039
                r7 = r3
                goto L_0x003e
            L_0x0039:
                java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r7 = r2
            L_0x003e:
                java.lang.String r2 = "session"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$ActionEventSession$Companion r8 = com.datadog.android.rum.model.ActionEvent.ActionEventSession.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$ActionEventSession r8 = r8.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = "source"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x005b
            L_0x0059:
                r9 = r3
                goto L_0x0069
            L_0x005b:
                java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x0062
                goto L_0x0059
            L_0x0062:
                com.datadog.android.rum.model.ActionEvent$Source$Companion r9 = com.datadog.android.rum.model.ActionEvent.Source.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Source r2 = r9.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r9 = r2
            L_0x0069:
                java.lang.String r2 = "view"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$View$Companion r10 = com.datadog.android.rum.model.ActionEvent.View.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$View r10 = r10.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = "usr"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x0086
            L_0x0084:
                r11 = r3
                goto L_0x0094
            L_0x0086:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x008d
                goto L_0x0084
            L_0x008d:
                com.datadog.android.rum.model.ActionEvent$Usr$Companion r11 = com.datadog.android.rum.model.ActionEvent.Usr.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Usr r2 = r11.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r11 = r2
            L_0x0094:
                java.lang.String r2 = "connectivity"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x009e
            L_0x009c:
                r12 = r3
                goto L_0x00ac
            L_0x009e:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x00a5
                goto L_0x009c
            L_0x00a5:
                com.datadog.android.rum.model.ActionEvent$Connectivity$Companion r12 = com.datadog.android.rum.model.ActionEvent.Connectivity.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Connectivity r2 = r12.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r12 = r2
            L_0x00ac:
                java.lang.String r2 = "synthetics"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x00b6
            L_0x00b4:
                r13 = r3
                goto L_0x00c4
            L_0x00b6:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x00bd
                goto L_0x00b4
            L_0x00bd:
                com.datadog.android.rum.model.ActionEvent$Synthetics$Companion r13 = com.datadog.android.rum.model.ActionEvent.Synthetics.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Synthetics r2 = r13.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r13 = r2
            L_0x00c4:
                java.lang.String r2 = "ci_test"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x00ce
            L_0x00cc:
                r14 = r3
                goto L_0x00dc
            L_0x00ce:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x00d5
                goto L_0x00cc
            L_0x00d5:
                com.datadog.android.rum.model.ActionEvent$CiTest$Companion r14 = com.datadog.android.rum.model.ActionEvent.CiTest.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$CiTest r2 = r14.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r14 = r2
            L_0x00dc:
                java.lang.String r2 = "_dd"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Dd$Companion r15 = com.datadog.android.rum.model.ActionEvent.C0863Dd.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Dd r15 = r15.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r2 = "context"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x00fa
            L_0x00f7:
                r16 = r3
                goto L_0x0109
            L_0x00fa:
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                if (r2 != 0) goto L_0x0101
                goto L_0x00f7
            L_0x0101:
                com.datadog.android.rum.model.ActionEvent$Context$Companion r3 = com.datadog.android.rum.model.ActionEvent.Context.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Context r2 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r16 = r2
            L_0x0109:
                java.lang.String r2 = "action"
                com.google.gson.JsonElement r1 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                java.lang.String r1 = r1.toString()     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Action$Companion r2 = com.datadog.android.rum.model.ActionEvent.Action.Companion     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent$Action r17 = r2.fromJson(r1)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                com.datadog.android.rum.model.ActionEvent r0 = new com.datadog.android.rum.model.ActionEvent     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                r3 = r0
                r3.<init>(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ IllegalStateException -> 0x012e, NumberFormatException -> 0x0123 }
                return r0
            L_0x0123:
                r0 = move-exception
                com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                java.lang.String r0 = r0.getMessage()
                r1.<init>((java.lang.String) r0)
                throw r1
            L_0x012e:
                r0 = move-exception
                com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                java.lang.String r0 = r0.getMessage()
                r1.<init>((java.lang.String) r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ActionEvent.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ActionEvent");
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Application;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public static final class Application {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f104id;

        public static /* synthetic */ Application copy$default(Application application, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = application.f104id;
            }
            return application.copy(str);
        }

        @JvmStatic
        public static final Application fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f104id;
        }

        public final Application copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Application(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Application) && Intrinsics.areEqual((Object) this.f104id, (Object) ((Application) obj).f104id);
        }

        public int hashCode() {
            return this.f104id.hashCode();
        }

        public String toString() {
            return "Application(id=" + this.f104id + ")";
        }

        public Application(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f104id = str;
        }

        public final String getId() {
            return this.f104id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f104id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Application$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Application;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\nJ.\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$ActionEventSession;", "", "id", "", "type", "Lcom/datadog/android/rum/model/ActionEvent$ActionEventSessionType;", "hasReplay", "", "(Ljava/lang/String;Lcom/datadog/android/rum/model/ActionEvent$ActionEventSessionType;Ljava/lang/Boolean;)V", "getHasReplay", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getId", "()Ljava/lang/String;", "getType", "()Lcom/datadog/android/rum/model/ActionEvent$ActionEventSessionType;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Lcom/datadog/android/rum/model/ActionEvent$ActionEventSessionType;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/ActionEvent$ActionEventSession;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public static final class ActionEventSession {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Boolean hasReplay;

        /* renamed from: id */
        private final String f103id;
        private final ActionEventSessionType type;

        public static /* synthetic */ ActionEventSession copy$default(ActionEventSession actionEventSession, String str, ActionEventSessionType actionEventSessionType, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionEventSession.f103id;
            }
            if ((i & 2) != 0) {
                actionEventSessionType = actionEventSession.type;
            }
            if ((i & 4) != 0) {
                bool = actionEventSession.hasReplay;
            }
            return actionEventSession.copy(str, actionEventSessionType, bool);
        }

        @JvmStatic
        public static final ActionEventSession fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f103id;
        }

        public final ActionEventSessionType component2() {
            return this.type;
        }

        public final Boolean component3() {
            return this.hasReplay;
        }

        public final ActionEventSession copy(String str, ActionEventSessionType actionEventSessionType, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(actionEventSessionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            return new ActionEventSession(str, actionEventSessionType, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionEventSession)) {
                return false;
            }
            ActionEventSession actionEventSession = (ActionEventSession) obj;
            return Intrinsics.areEqual((Object) this.f103id, (Object) actionEventSession.f103id) && this.type == actionEventSession.type && Intrinsics.areEqual((Object) this.hasReplay, (Object) actionEventSession.hasReplay);
        }

        public int hashCode() {
            int hashCode = ((this.f103id.hashCode() * 31) + this.type.hashCode()) * 31;
            Boolean bool = this.hasReplay;
            return hashCode + (bool == null ? 0 : bool.hashCode());
        }

        public String toString() {
            String str = this.f103id;
            ActionEventSessionType actionEventSessionType = this.type;
            return "ActionEventSession(id=" + str + ", type=" + actionEventSessionType + ", hasReplay=" + this.hasReplay + ")";
        }

        public ActionEventSession(String str, ActionEventSessionType actionEventSessionType, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(actionEventSessionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            this.f103id = str;
            this.type = actionEventSessionType;
            this.hasReplay = bool;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ActionEventSession(String str, ActionEventSessionType actionEventSessionType, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, actionEventSessionType, (i & 4) != 0 ? null : bool);
        }

        public final String getId() {
            return this.f103id;
        }

        public final ActionEventSessionType getType() {
            return this.type;
        }

        public final Boolean getHasReplay() {
            return this.hasReplay;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f103id);
            jsonObject.add(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type.toJson());
            Boolean bool = this.hasReplay;
            if (bool != null) {
                jsonObject.addProperty("has_replay", Boolean.valueOf(bool.booleanValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$ActionEventSession$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$ActionEventSession;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final ActionEventSession fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    String asString = asJsonObject.get("id").getAsString();
                    String asString2 = asJsonObject.get(RumEventDeserializer.EVENT_TYPE_KEY_NAME).getAsString();
                    ActionEventSessionType.Companion companion = ActionEventSessionType.Companion;
                    Intrinsics.checkNotNullExpressionValue(asString2, "it");
                    ActionEventSessionType fromJson = companion.fromJson(asString2);
                    JsonElement jsonElement = asJsonObject.get("has_replay");
                    Boolean valueOf = jsonElement == null ? null : Boolean.valueOf(jsonElement.getAsBoolean());
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    return new ActionEventSession(asString, fromJson, valueOf);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 $2\u00020\u0001:\u0001$B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\rJF\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\u0006\u0010!\u001a\u00020\"J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\u0011¨\u0006%"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$View;", "", "id", "", "referrer", "url", "name", "inForeground", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()Ljava/lang/String;", "getInForeground", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getName", "setName", "(Ljava/lang/String;)V", "getReferrer", "setReferrer", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/ActionEvent$View;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public static final class View {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f106id;
        private final Boolean inForeground;
        private String name;
        private String referrer;
        private String url;

        public static /* synthetic */ View copy$default(View view, String str, String str2, String str3, String str4, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = view.f106id;
            }
            if ((i & 2) != 0) {
                str2 = view.referrer;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                str3 = view.url;
            }
            String str6 = str3;
            if ((i & 8) != 0) {
                str4 = view.name;
            }
            String str7 = str4;
            if ((i & 16) != 0) {
                bool = view.inForeground;
            }
            return view.copy(str, str5, str6, str7, bool);
        }

        @JvmStatic
        public static final View fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f106id;
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

        public final Boolean component5() {
            return this.inForeground;
        }

        public final View copy(String str, String str2, String str3, String str4, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str3, "url");
            return new View(str, str2, str3, str4, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof View)) {
                return false;
            }
            View view = (View) obj;
            return Intrinsics.areEqual((Object) this.f106id, (Object) view.f106id) && Intrinsics.areEqual((Object) this.referrer, (Object) view.referrer) && Intrinsics.areEqual((Object) this.url, (Object) view.url) && Intrinsics.areEqual((Object) this.name, (Object) view.name) && Intrinsics.areEqual((Object) this.inForeground, (Object) view.inForeground);
        }

        public int hashCode() {
            int hashCode = this.f106id.hashCode() * 31;
            String str = this.referrer;
            int i = 0;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.url.hashCode()) * 31;
            String str2 = this.name;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Boolean bool = this.inForeground;
            if (bool != null) {
                i = bool.hashCode();
            }
            return hashCode3 + i;
        }

        public String toString() {
            String str = this.f106id;
            String str2 = this.referrer;
            String str3 = this.url;
            String str4 = this.name;
            return "View(id=" + str + ", referrer=" + str2 + ", url=" + str3 + ", name=" + str4 + ", inForeground=" + this.inForeground + ")";
        }

        public View(String str, String str2, String str3, String str4, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str3, "url");
            this.f106id = str;
            this.referrer = str2;
            this.url = str3;
            this.name = str4;
            this.inForeground = bool;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ View(String str, String str2, String str3, String str4, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : bool);
        }

        public final String getId() {
            return this.f106id;
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

        public final Boolean getInForeground() {
            return this.inForeground;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f106id);
            String str = this.referrer;
            if (str != null) {
                jsonObject.addProperty("referrer", str);
            }
            jsonObject.addProperty("url", this.url);
            String str2 = this.name;
            if (str2 != null) {
                jsonObject.addProperty("name", str2);
            }
            Boolean bool = this.inForeground;
            if (bool != null) {
                jsonObject.addProperty("in_foreground", Boolean.valueOf(bool.booleanValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$View$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$View;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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
                    String asString2 = jsonElement == null ? null : jsonElement.getAsString();
                    String asString3 = asJsonObject.get("url").getAsString();
                    JsonElement jsonElement2 = asJsonObject.get("name");
                    String asString4 = jsonElement2 == null ? null : jsonElement2.getAsString();
                    JsonElement jsonElement3 = asJsonObject.get("in_foreground");
                    Boolean valueOf = jsonElement3 == null ? null : Boolean.valueOf(jsonElement3.getAsBoolean());
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    Intrinsics.checkNotNullExpressionValue(asString3, "url");
                    return new View(asString, asString2, asString3, asString4, valueOf);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0003JE\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Usr;", "", "id", "", "name", "email", "additionalProperties", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "getEmail", "()Ljava/lang/String;", "getId", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public static final class Usr {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final String[] RESERVED_PROPERTIES = {"id", "name", NotificationCompat.CATEGORY_EMAIL};
        private final Map<String, Object> additionalProperties;
        private final String email;

        /* renamed from: id */
        private final String f105id;
        private final String name;

        public Usr() {
            this((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Usr copy$default(Usr usr, String str, String str2, String str3, Map<String, Object> map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = usr.f105id;
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
            return this.f105id;
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
            return Intrinsics.areEqual((Object) this.f105id, (Object) usr.f105id) && Intrinsics.areEqual((Object) this.name, (Object) usr.name) && Intrinsics.areEqual((Object) this.email, (Object) usr.email) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) usr.additionalProperties);
        }

        public int hashCode() {
            String str = this.f105id;
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
            String str = this.f105id;
            String str2 = this.name;
            String str3 = this.email;
            return "Usr(id=" + str + ", name=" + str2 + ", email=" + str3 + ", additionalProperties=" + this.additionalProperties + ")";
        }

        public Usr(String str, String str2, String str3, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.f105id = str;
            this.name = str2;
            this.email = str3;
            this.additionalProperties = map;
        }

        public final String getId() {
            return this.f105id;
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
            String str = this.f105id;
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

        @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Usr$Companion;", "", "()V", "RESERVED_PROPERTIES", "", "", "getRESERVED_PROPERTIES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Usr;", "serializedObject", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Connectivity;", "", "status", "Lcom/datadog/android/rum/model/ActionEvent$Status;", "interfaces", "", "Lcom/datadog/android/rum/model/ActionEvent$Interface;", "cellular", "Lcom/datadog/android/rum/model/ActionEvent$Cellular;", "(Lcom/datadog/android/rum/model/ActionEvent$Status;Ljava/util/List;Lcom/datadog/android/rum/model/ActionEvent$Cellular;)V", "getCellular", "()Lcom/datadog/android/rum/model/ActionEvent$Cellular;", "getInterfaces", "()Ljava/util/List;", "getStatus", "()Lcom/datadog/android/rum/model/ActionEvent$Status;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Connectivity$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Connectivity;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\tJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Synthetics;", "", "testId", "", "resultId", "injected", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getInjected", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getResultId", "()Ljava/lang/String;", "getTestId", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/ActionEvent$Synthetics;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Synthetics$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Synthetics;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$CiTest;", "", "testExecutionId", "", "(Ljava/lang/String;)V", "getTestExecutionId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$CiTest$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$CiTest;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Dd;", "", "session", "Lcom/datadog/android/rum/model/ActionEvent$DdSession;", "browserSdkVersion", "", "(Lcom/datadog/android/rum/model/ActionEvent$DdSession;Ljava/lang/String;)V", "getBrowserSdkVersion", "()Ljava/lang/String;", "formatVersion", "", "getFormatVersion", "()J", "getSession", "()Lcom/datadog/android/rum/model/ActionEvent$DdSession;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* renamed from: com.datadog.android.rum.model.ActionEvent$Dd */
    /* compiled from: ActionEvent.kt */
    public static final class C0863Dd {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String browserSdkVersion;
        private final long formatVersion;
        private final DdSession session;

        public C0863Dd() {
            this((DdSession) null, (String) null, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ C0863Dd copy$default(C0863Dd dd, DdSession ddSession, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                ddSession = dd.session;
            }
            if ((i & 2) != 0) {
                str = dd.browserSdkVersion;
            }
            return dd.copy(ddSession, str);
        }

        @JvmStatic
        public static final C0863Dd fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final DdSession component1() {
            return this.session;
        }

        public final String component2() {
            return this.browserSdkVersion;
        }

        public final C0863Dd copy(DdSession ddSession, String str) {
            return new C0863Dd(ddSession, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0863Dd)) {
                return false;
            }
            C0863Dd dd = (C0863Dd) obj;
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

        public C0863Dd(DdSession ddSession, String str) {
            this.session = ddSession;
            this.browserSdkVersion = str;
            this.formatVersion = 2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ C0863Dd(DdSession ddSession, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Dd$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Dd;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* renamed from: com.datadog.android.rum.model.ActionEvent$Dd$Companion */
        /* compiled from: ActionEvent.kt */
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
            public final com.datadog.android.rum.model.ActionEvent.C0863Dd fromJson(java.lang.String r3) throws com.google.gson.JsonParseException {
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
                    com.datadog.android.rum.model.ActionEvent$DdSession$Companion r1 = com.datadog.android.rum.model.ActionEvent.DdSession.Companion     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    com.datadog.android.rum.model.ActionEvent$DdSession r3 = r1.fromJson(r3)     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                L_0x0025:
                    java.lang.String r1 = "browser_sdk_version"
                    com.google.gson.JsonElement r2 = r2.get(r1)     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                    if (r2 != 0) goto L_0x002e
                    goto L_0x0032
                L_0x002e:
                    java.lang.String r0 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
                L_0x0032:
                    com.datadog.android.rum.model.ActionEvent$Dd r2 = new com.datadog.android.rum.model.ActionEvent$Dd     // Catch:{ IllegalStateException -> 0x0043, NumberFormatException -> 0x0038 }
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
                throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ActionEvent.C0863Dd.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ActionEvent$Dd");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003HÆ\u0003J!\u0010\t\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Context;", "", "additionalProperties", "", "", "(Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Context$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Context;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 62\u00020\u0001:\u00016Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010'\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0011HÆ\u0003Jl\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u000202HÖ\u0001J\u0006\u00103\u001a\u000204J\t\u00105\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00067"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Action;", "", "type", "Lcom/datadog/android/rum/model/ActionEvent$ActionType;", "id", "", "loadingTime", "", "target", "Lcom/datadog/android/rum/model/ActionEvent$Target;", "error", "Lcom/datadog/android/rum/model/ActionEvent$Error;", "crash", "Lcom/datadog/android/rum/model/ActionEvent$Crash;", "longTask", "Lcom/datadog/android/rum/model/ActionEvent$LongTask;", "resource", "Lcom/datadog/android/rum/model/ActionEvent$Resource;", "(Lcom/datadog/android/rum/model/ActionEvent$ActionType;Ljava/lang/String;Ljava/lang/Long;Lcom/datadog/android/rum/model/ActionEvent$Target;Lcom/datadog/android/rum/model/ActionEvent$Error;Lcom/datadog/android/rum/model/ActionEvent$Crash;Lcom/datadog/android/rum/model/ActionEvent$LongTask;Lcom/datadog/android/rum/model/ActionEvent$Resource;)V", "getCrash", "()Lcom/datadog/android/rum/model/ActionEvent$Crash;", "getError", "()Lcom/datadog/android/rum/model/ActionEvent$Error;", "getId", "()Ljava/lang/String;", "getLoadingTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLongTask", "()Lcom/datadog/android/rum/model/ActionEvent$LongTask;", "getResource", "()Lcom/datadog/android/rum/model/ActionEvent$Resource;", "getTarget", "()Lcom/datadog/android/rum/model/ActionEvent$Target;", "getType", "()Lcom/datadog/android/rum/model/ActionEvent$ActionType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Lcom/datadog/android/rum/model/ActionEvent$ActionType;Ljava/lang/String;Ljava/lang/Long;Lcom/datadog/android/rum/model/ActionEvent$Target;Lcom/datadog/android/rum/model/ActionEvent$Error;Lcom/datadog/android/rum/model/ActionEvent$Crash;Lcom/datadog/android/rum/model/ActionEvent$LongTask;Lcom/datadog/android/rum/model/ActionEvent$Resource;)Lcom/datadog/android/rum/model/ActionEvent$Action;", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public static final class Action {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Crash crash;
        private final Error error;

        /* renamed from: id */
        private final String f102id;
        private final Long loadingTime;
        private final LongTask longTask;
        private final Resource resource;
        private final Target target;
        private final ActionType type;

        public static /* synthetic */ Action copy$default(Action action, ActionType actionType, String str, Long l, Target target2, Error error2, Crash crash2, LongTask longTask2, Resource resource2, int i, Object obj) {
            Action action2 = action;
            int i2 = i;
            return action.copy((i2 & 1) != 0 ? action2.type : actionType, (i2 & 2) != 0 ? action2.f102id : str, (i2 & 4) != 0 ? action2.loadingTime : l, (i2 & 8) != 0 ? action2.target : target2, (i2 & 16) != 0 ? action2.error : error2, (i2 & 32) != 0 ? action2.crash : crash2, (i2 & 64) != 0 ? action2.longTask : longTask2, (i2 & 128) != 0 ? action2.resource : resource2);
        }

        @JvmStatic
        public static final Action fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final ActionType component1() {
            return this.type;
        }

        public final String component2() {
            return this.f102id;
        }

        public final Long component3() {
            return this.loadingTime;
        }

        public final Target component4() {
            return this.target;
        }

        public final Error component5() {
            return this.error;
        }

        public final Crash component6() {
            return this.crash;
        }

        public final LongTask component7() {
            return this.longTask;
        }

        public final Resource component8() {
            return this.resource;
        }

        public final Action copy(ActionType actionType, String str, Long l, Target target2, Error error2, Crash crash2, LongTask longTask2, Resource resource2) {
            Intrinsics.checkNotNullParameter(actionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            return new Action(actionType, str, l, target2, error2, crash2, longTask2, resource2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Action)) {
                return false;
            }
            Action action = (Action) obj;
            return this.type == action.type && Intrinsics.areEqual((Object) this.f102id, (Object) action.f102id) && Intrinsics.areEqual((Object) this.loadingTime, (Object) action.loadingTime) && Intrinsics.areEqual((Object) this.target, (Object) action.target) && Intrinsics.areEqual((Object) this.error, (Object) action.error) && Intrinsics.areEqual((Object) this.crash, (Object) action.crash) && Intrinsics.areEqual((Object) this.longTask, (Object) action.longTask) && Intrinsics.areEqual((Object) this.resource, (Object) action.resource);
        }

        public int hashCode() {
            int hashCode = this.type.hashCode() * 31;
            String str = this.f102id;
            int i = 0;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            Long l = this.loadingTime;
            int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
            Target target2 = this.target;
            int hashCode4 = (hashCode3 + (target2 == null ? 0 : target2.hashCode())) * 31;
            Error error2 = this.error;
            int hashCode5 = (hashCode4 + (error2 == null ? 0 : error2.hashCode())) * 31;
            Crash crash2 = this.crash;
            int hashCode6 = (hashCode5 + (crash2 == null ? 0 : crash2.hashCode())) * 31;
            LongTask longTask2 = this.longTask;
            int hashCode7 = (hashCode6 + (longTask2 == null ? 0 : longTask2.hashCode())) * 31;
            Resource resource2 = this.resource;
            if (resource2 != null) {
                i = resource2.hashCode();
            }
            return hashCode7 + i;
        }

        public String toString() {
            ActionType actionType = this.type;
            String str = this.f102id;
            Long l = this.loadingTime;
            Target target2 = this.target;
            Error error2 = this.error;
            Crash crash2 = this.crash;
            LongTask longTask2 = this.longTask;
            return "Action(type=" + actionType + ", id=" + str + ", loadingTime=" + l + ", target=" + target2 + ", error=" + error2 + ", crash=" + crash2 + ", longTask=" + longTask2 + ", resource=" + this.resource + ")";
        }

        public Action(ActionType actionType, String str, Long l, Target target2, Error error2, Crash crash2, LongTask longTask2, Resource resource2) {
            Intrinsics.checkNotNullParameter(actionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            this.type = actionType;
            this.f102id = str;
            this.loadingTime = l;
            this.target = target2;
            this.error = error2;
            this.crash = crash2;
            this.longTask = longTask2;
            this.resource = resource2;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ Action(com.datadog.android.rum.model.ActionEvent.ActionType r9, java.lang.String r10, java.lang.Long r11, com.datadog.android.rum.model.ActionEvent.Target r12, com.datadog.android.rum.model.ActionEvent.Error r13, com.datadog.android.rum.model.ActionEvent.Crash r14, com.datadog.android.rum.model.ActionEvent.LongTask r15, com.datadog.android.rum.model.ActionEvent.Resource r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
            /*
                r8 = this;
                r0 = r17
                r1 = r0 & 2
                r2 = 0
                if (r1 == 0) goto L_0x0009
                r1 = r2
                goto L_0x000a
            L_0x0009:
                r1 = r10
            L_0x000a:
                r3 = r0 & 4
                if (r3 == 0) goto L_0x0010
                r3 = r2
                goto L_0x0011
            L_0x0010:
                r3 = r11
            L_0x0011:
                r4 = r0 & 8
                if (r4 == 0) goto L_0x0017
                r4 = r2
                goto L_0x0018
            L_0x0017:
                r4 = r12
            L_0x0018:
                r5 = r0 & 16
                if (r5 == 0) goto L_0x001e
                r5 = r2
                goto L_0x001f
            L_0x001e:
                r5 = r13
            L_0x001f:
                r6 = r0 & 32
                if (r6 == 0) goto L_0x0025
                r6 = r2
                goto L_0x0026
            L_0x0025:
                r6 = r14
            L_0x0026:
                r7 = r0 & 64
                if (r7 == 0) goto L_0x002c
                r7 = r2
                goto L_0x002d
            L_0x002c:
                r7 = r15
            L_0x002d:
                r0 = r0 & 128(0x80, float:1.794E-43)
                if (r0 == 0) goto L_0x0032
                goto L_0x0034
            L_0x0032:
                r2 = r16
            L_0x0034:
                r10 = r8
                r11 = r9
                r12 = r1
                r13 = r3
                r14 = r4
                r15 = r5
                r16 = r6
                r17 = r7
                r18 = r2
                r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ActionEvent.Action.<init>(com.datadog.android.rum.model.ActionEvent$ActionType, java.lang.String, java.lang.Long, com.datadog.android.rum.model.ActionEvent$Target, com.datadog.android.rum.model.ActionEvent$Error, com.datadog.android.rum.model.ActionEvent$Crash, com.datadog.android.rum.model.ActionEvent$LongTask, com.datadog.android.rum.model.ActionEvent$Resource, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final ActionType getType() {
            return this.type;
        }

        public final String getId() {
            return this.f102id;
        }

        public final Long getLoadingTime() {
            return this.loadingTime;
        }

        public final Target getTarget() {
            return this.target;
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

        public final Resource getResource() {
            return this.resource;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type.toJson());
            String str = this.f102id;
            if (str != null) {
                jsonObject.addProperty("id", str);
            }
            Long l = this.loadingTime;
            if (l != null) {
                jsonObject.addProperty("loading_time", (Number) Long.valueOf(l.longValue()));
            }
            Target target2 = this.target;
            if (target2 != null) {
                jsonObject.add("target", target2.toJson());
            }
            Error error2 = this.error;
            if (error2 != null) {
                jsonObject.add("error", error2.toJson());
            }
            Crash crash2 = this.crash;
            if (crash2 != null) {
                jsonObject.add("crash", crash2.toJson());
            }
            LongTask longTask2 = this.longTask;
            if (longTask2 != null) {
                jsonObject.add("long_task", longTask2.toJson());
            }
            Resource resource2 = this.resource;
            if (resource2 != null) {
                jsonObject.add("resource", resource2.toJson());
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Action$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Action;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARNING: Removed duplicated region for block: B:20:0x0067 A[Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }] */
            /* JADX WARNING: Removed duplicated region for block: B:27:0x007f A[Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }] */
            /* JADX WARNING: Removed duplicated region for block: B:34:0x0097 A[Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }] */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x00af A[Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }] */
            @kotlin.jvm.JvmStatic
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.datadog.android.rum.model.ActionEvent.Action fromJson(java.lang.String r12) throws com.google.gson.JsonParseException {
                /*
                    r11 = this;
                    java.lang.String r11 = "serializedObject"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r11)
                    com.google.gson.JsonElement r11 = com.google.gson.JsonParser.parseString(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    com.google.gson.JsonObject r11 = r11.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    java.lang.String r12 = "type"
                    com.google.gson.JsonElement r12 = r11.get(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    java.lang.String r12 = r12.getAsString()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    com.datadog.android.rum.model.ActionEvent$ActionType$Companion r0 = com.datadog.android.rum.model.ActionEvent.ActionType.Companion     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    java.lang.String r1 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    com.datadog.android.rum.model.ActionEvent$ActionType r3 = r0.fromJson(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    java.lang.String r12 = "id"
                    com.google.gson.JsonElement r12 = r11.get(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r0 = 0
                    if (r12 != 0) goto L_0x002d
                    r4 = r0
                    goto L_0x0032
                L_0x002d:
                    java.lang.String r12 = r12.getAsString()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r4 = r12
                L_0x0032:
                    java.lang.String r12 = "loading_time"
                    com.google.gson.JsonElement r12 = r11.get(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x003c
                    r5 = r0
                    goto L_0x0045
                L_0x003c:
                    long r1 = r12.getAsLong()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    java.lang.Long r12 = java.lang.Long.valueOf(r1)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r5 = r12
                L_0x0045:
                    java.lang.String r12 = "target"
                    com.google.gson.JsonElement r12 = r11.get(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x004f
                L_0x004d:
                    r6 = r0
                    goto L_0x005d
                L_0x004f:
                    java.lang.String r12 = r12.toString()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x0056
                    goto L_0x004d
                L_0x0056:
                    com.datadog.android.rum.model.ActionEvent$Target$Companion r1 = com.datadog.android.rum.model.ActionEvent.Target.Companion     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    com.datadog.android.rum.model.ActionEvent$Target r12 = r1.fromJson(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r6 = r12
                L_0x005d:
                    java.lang.String r12 = "error"
                    com.google.gson.JsonElement r12 = r11.get(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x0067
                L_0x0065:
                    r7 = r0
                    goto L_0x0075
                L_0x0067:
                    java.lang.String r12 = r12.toString()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x006e
                    goto L_0x0065
                L_0x006e:
                    com.datadog.android.rum.model.ActionEvent$Error$Companion r1 = com.datadog.android.rum.model.ActionEvent.Error.Companion     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    com.datadog.android.rum.model.ActionEvent$Error r12 = r1.fromJson(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r7 = r12
                L_0x0075:
                    java.lang.String r12 = "crash"
                    com.google.gson.JsonElement r12 = r11.get(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x007f
                L_0x007d:
                    r8 = r0
                    goto L_0x008d
                L_0x007f:
                    java.lang.String r12 = r12.toString()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x0086
                    goto L_0x007d
                L_0x0086:
                    com.datadog.android.rum.model.ActionEvent$Crash$Companion r1 = com.datadog.android.rum.model.ActionEvent.Crash.Companion     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    com.datadog.android.rum.model.ActionEvent$Crash r12 = r1.fromJson(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r8 = r12
                L_0x008d:
                    java.lang.String r12 = "long_task"
                    com.google.gson.JsonElement r12 = r11.get(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x0097
                L_0x0095:
                    r9 = r0
                    goto L_0x00a5
                L_0x0097:
                    java.lang.String r12 = r12.toString()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r12 != 0) goto L_0x009e
                    goto L_0x0095
                L_0x009e:
                    com.datadog.android.rum.model.ActionEvent$LongTask$Companion r1 = com.datadog.android.rum.model.ActionEvent.LongTask.Companion     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    com.datadog.android.rum.model.ActionEvent$LongTask r12 = r1.fromJson(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r9 = r12
                L_0x00a5:
                    java.lang.String r12 = "resource"
                    com.google.gson.JsonElement r11 = r11.get(r12)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r11 != 0) goto L_0x00af
                L_0x00ad:
                    r10 = r0
                    goto L_0x00bd
                L_0x00af:
                    java.lang.String r11 = r11.toString()     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    if (r11 != 0) goto L_0x00b6
                    goto L_0x00ad
                L_0x00b6:
                    com.datadog.android.rum.model.ActionEvent$Resource$Companion r12 = com.datadog.android.rum.model.ActionEvent.Resource.Companion     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    com.datadog.android.rum.model.ActionEvent$Resource r11 = r12.fromJson(r11)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r10 = r11
                L_0x00bd:
                    com.datadog.android.rum.model.ActionEvent$Action r11 = new com.datadog.android.rum.model.ActionEvent$Action     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    r2 = r11
                    r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ IllegalStateException -> 0x00cf, NumberFormatException -> 0x00c4 }
                    return r11
                L_0x00c4:
                    r11 = move-exception
                    com.google.gson.JsonParseException r12 = new com.google.gson.JsonParseException
                    java.lang.String r11 = r11.getMessage()
                    r12.<init>((java.lang.String) r11)
                    throw r12
                L_0x00cf:
                    r11 = move-exception
                    com.google.gson.JsonParseException r12 = new com.google.gson.JsonParseException
                    java.lang.String r11 = r11.getMessage()
                    r12.<init>((java.lang.String) r11)
                    throw r12
                */
                throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ActionEvent.Action.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ActionEvent$Action");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Cellular;", "", "technology", "", "carrierName", "(Ljava/lang/String;Ljava/lang/String;)V", "getCarrierName", "()Ljava/lang/String;", "getTechnology", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Cellular$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Cellular;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$DdSession;", "", "plan", "Lcom/datadog/android/rum/model/ActionEvent$Plan;", "(Lcom/datadog/android/rum/model/ActionEvent$Plan;)V", "getPlan", "()Lcom/datadog/android/rum/model/ActionEvent$Plan;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$DdSession$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$DdSession;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Target;", "", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public static final class Target {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private String name;

        public static /* synthetic */ Target copy$default(Target target, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = target.name;
            }
            return target.copy(str);
        }

        @JvmStatic
        public static final Target fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.name;
        }

        public final Target copy(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Target(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Target) && Intrinsics.areEqual((Object) this.name, (Object) ((Target) obj).name);
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return "Target(name=" + this.name + ")";
        }

        public Target(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.name = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.name = str;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", this.name);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Target$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Target;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Target fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    String asString = JsonParser.parseString(str).getAsJsonObject().get("name").getAsString();
                    Intrinsics.checkNotNullExpressionValue(asString, "name");
                    return new Target(asString);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Error;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Error$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Error;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Crash;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Crash$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Crash;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$LongTask;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$LongTask$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$LongTask;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Resource;", "", "count", "", "(J)V", "getCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Resource$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Resource;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Source;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "ANDROID", "IOS", "BROWSER", "FLUTTER", "REACT_NATIVE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Source$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Source;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$ActionEventSessionType;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "USER", "SYNTHETICS", "CI_TEST", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public enum ActionEventSessionType {
        USER("user"),
        SYNTHETICS("synthetics"),
        CI_TEST("ci_test");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final ActionEventSessionType fromJson(String str) {
            return Companion.fromJson(str);
        }

        private ActionEventSessionType(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$ActionEventSessionType$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$ActionEventSessionType;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final ActionEventSessionType fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                ActionEventSessionType[] values = ActionEventSessionType.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    ActionEventSessionType actionEventSessionType = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) actionEventSessionType.jsonValue, (Object) str)) {
                        return actionEventSessionType;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Status;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "CONNECTED", "NOT_CONNECTED", "MAYBE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Status$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Status;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Interface;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "BLUETOOTH", "CELLULAR", "ETHERNET", "WIFI", "WIMAX", "MIXED", "OTHER", "UNKNOWN", "NONE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Interface$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Interface;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$ActionType;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "CUSTOM", "CLICK", "TAP", "SCROLL", "SWIPE", "APPLICATION_START", "BACK", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
    public enum ActionType {
        CUSTOM("custom"),
        CLICK("click"),
        TAP("tap"),
        SCROLL("scroll"),
        SWIPE("swipe"),
        APPLICATION_START("application_start"),
        BACK(WindowCallbackWrapper.BACK_DEFAULT_TARGET_NAME);
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final ActionType fromJson(String str) {
            return Companion.fromJson(str);
        }

        private ActionType(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$ActionType$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$ActionType;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final ActionType fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                ActionType[] values = ActionType.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    ActionType actionType = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) actionType.jsonValue, (Object) str)) {
                        return actionType;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Plan;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/Number;)V", "toJson", "Lcom/google/gson/JsonElement;", "PLAN_1", "PLAN_2", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ActionEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ActionEvent$Plan$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ActionEvent$Plan;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ActionEvent.kt */
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

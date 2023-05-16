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

@Metadata(mo20734d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\b\b\u0018\u0000 X2\u00020\u0001:\u001dTUVWXYZ[\\]^_`abcdefghijklmnopB\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0002\u0010\u001eJ\t\u0010=\u001a\u00020\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\t\u0010?\u001a\u00020\u0017HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0019HÆ\u0003J\t\u0010A\u001a\u00020\u001bHÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u001dHÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010E\u001a\u00020\tHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010G\u001a\u00020\rHÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0013HÆ\u0003J¥\u0001\u0010K\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÆ\u0001J\u0013\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010O\u001a\u00020PHÖ\u0001J\u0006\u0010Q\u001a\u00020RJ\t\u0010S\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b8\u00100R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<¨\u0006q"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent;", "", "date", "", "application", "Lcom/datadog/android/rum/model/ResourceEvent$Application;", "service", "", "session", "Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSession;", "source", "Lcom/datadog/android/rum/model/ResourceEvent$Source;", "view", "Lcom/datadog/android/rum/model/ResourceEvent$View;", "usr", "Lcom/datadog/android/rum/model/ResourceEvent$Usr;", "connectivity", "Lcom/datadog/android/rum/model/ResourceEvent$Connectivity;", "synthetics", "Lcom/datadog/android/rum/model/ResourceEvent$Synthetics;", "ciTest", "Lcom/datadog/android/rum/model/ResourceEvent$CiTest;", "dd", "Lcom/datadog/android/rum/model/ResourceEvent$Dd;", "context", "Lcom/datadog/android/rum/model/ResourceEvent$Context;", "resource", "Lcom/datadog/android/rum/model/ResourceEvent$Resource;", "action", "Lcom/datadog/android/rum/model/ResourceEvent$Action;", "(JLcom/datadog/android/rum/model/ResourceEvent$Application;Ljava/lang/String;Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSession;Lcom/datadog/android/rum/model/ResourceEvent$Source;Lcom/datadog/android/rum/model/ResourceEvent$View;Lcom/datadog/android/rum/model/ResourceEvent$Usr;Lcom/datadog/android/rum/model/ResourceEvent$Connectivity;Lcom/datadog/android/rum/model/ResourceEvent$Synthetics;Lcom/datadog/android/rum/model/ResourceEvent$CiTest;Lcom/datadog/android/rum/model/ResourceEvent$Dd;Lcom/datadog/android/rum/model/ResourceEvent$Context;Lcom/datadog/android/rum/model/ResourceEvent$Resource;Lcom/datadog/android/rum/model/ResourceEvent$Action;)V", "getAction", "()Lcom/datadog/android/rum/model/ResourceEvent$Action;", "getApplication", "()Lcom/datadog/android/rum/model/ResourceEvent$Application;", "getCiTest", "()Lcom/datadog/android/rum/model/ResourceEvent$CiTest;", "getConnectivity", "()Lcom/datadog/android/rum/model/ResourceEvent$Connectivity;", "getContext", "()Lcom/datadog/android/rum/model/ResourceEvent$Context;", "getDate", "()J", "getDd", "()Lcom/datadog/android/rum/model/ResourceEvent$Dd;", "getResource", "()Lcom/datadog/android/rum/model/ResourceEvent$Resource;", "getService", "()Ljava/lang/String;", "getSession", "()Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSession;", "getSource", "()Lcom/datadog/android/rum/model/ResourceEvent$Source;", "getSynthetics", "()Lcom/datadog/android/rum/model/ResourceEvent$Synthetics;", "type", "getType", "getUsr", "()Lcom/datadog/android/rum/model/ResourceEvent$Usr;", "getView", "()Lcom/datadog/android/rum/model/ResourceEvent$View;", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Action", "Application", "Cellular", "CiTest", "Companion", "Connect", "Connectivity", "Context", "Dd", "DdSession", "Dns", "Download", "FirstByte", "Interface", "Method", "Plan", "Provider", "ProviderType", "Redirect", "Resource", "ResourceEventSession", "ResourceEventSessionType", "ResourceType", "Source", "Ssl", "Status", "Synthetics", "Usr", "View", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ResourceEvent.kt */
public final class ResourceEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Action action;
    private final Application application;
    private final CiTest ciTest;
    private final Connectivity connectivity;
    private final Context context;
    private final long date;

    /* renamed from: dd */
    private final C0866Dd f122dd;
    private final Resource resource;
    private final String service;
    private final ResourceEventSession session;
    private final Source source;
    private final Synthetics synthetics;
    private final String type;
    private final Usr usr;
    private final View view;

    public static /* synthetic */ ResourceEvent copy$default(ResourceEvent resourceEvent, long j, Application application2, String str, ResourceEventSession resourceEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0866Dd dd, Context context2, Resource resource2, Action action2, int i, Object obj) {
        ResourceEvent resourceEvent2 = resourceEvent;
        int i2 = i;
        return resourceEvent.copy((i2 & 1) != 0 ? resourceEvent2.date : j, (i2 & 2) != 0 ? resourceEvent2.application : application2, (i2 & 4) != 0 ? resourceEvent2.service : str, (i2 & 8) != 0 ? resourceEvent2.session : resourceEventSession, (i2 & 16) != 0 ? resourceEvent2.source : source2, (i2 & 32) != 0 ? resourceEvent2.view : view2, (i2 & 64) != 0 ? resourceEvent2.usr : usr2, (i2 & 128) != 0 ? resourceEvent2.connectivity : connectivity2, (i2 & 256) != 0 ? resourceEvent2.synthetics : synthetics2, (i2 & 512) != 0 ? resourceEvent2.ciTest : ciTest2, (i2 & 1024) != 0 ? resourceEvent2.f122dd : dd, (i2 & 2048) != 0 ? resourceEvent2.context : context2, (i2 & 4096) != 0 ? resourceEvent2.resource : resource2, (i2 & 8192) != 0 ? resourceEvent2.action : action2);
    }

    @JvmStatic
    public static final ResourceEvent fromJson(String str) throws JsonParseException {
        return Companion.fromJson(str);
    }

    public final long component1() {
        return this.date;
    }

    public final CiTest component10() {
        return this.ciTest;
    }

    public final C0866Dd component11() {
        return this.f122dd;
    }

    public final Context component12() {
        return this.context;
    }

    public final Resource component13() {
        return this.resource;
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

    public final ResourceEventSession component4() {
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

    public final ResourceEvent copy(long j, Application application2, String str, ResourceEventSession resourceEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0866Dd dd, Context context2, Resource resource2, Action action2) {
        Application application3 = application2;
        Intrinsics.checkNotNullParameter(application3, WebViewRumEventMapper.APPLICATION_KEY_NAME);
        ResourceEventSession resourceEventSession2 = resourceEventSession;
        Intrinsics.checkNotNullParameter(resourceEventSession2, "session");
        View view3 = view2;
        Intrinsics.checkNotNullParameter(view3, "view");
        C0866Dd dd2 = dd;
        Intrinsics.checkNotNullParameter(dd2, "dd");
        Resource resource3 = resource2;
        Intrinsics.checkNotNullParameter(resource3, "resource");
        return new ResourceEvent(j, application3, str, resourceEventSession2, source2, view3, usr2, connectivity2, synthetics2, ciTest2, dd2, context2, resource3, action2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourceEvent)) {
            return false;
        }
        ResourceEvent resourceEvent = (ResourceEvent) obj;
        return this.date == resourceEvent.date && Intrinsics.areEqual((Object) this.application, (Object) resourceEvent.application) && Intrinsics.areEqual((Object) this.service, (Object) resourceEvent.service) && Intrinsics.areEqual((Object) this.session, (Object) resourceEvent.session) && this.source == resourceEvent.source && Intrinsics.areEqual((Object) this.view, (Object) resourceEvent.view) && Intrinsics.areEqual((Object) this.usr, (Object) resourceEvent.usr) && Intrinsics.areEqual((Object) this.connectivity, (Object) resourceEvent.connectivity) && Intrinsics.areEqual((Object) this.synthetics, (Object) resourceEvent.synthetics) && Intrinsics.areEqual((Object) this.ciTest, (Object) resourceEvent.ciTest) && Intrinsics.areEqual((Object) this.f122dd, (Object) resourceEvent.f122dd) && Intrinsics.areEqual((Object) this.context, (Object) resourceEvent.context) && Intrinsics.areEqual((Object) this.resource, (Object) resourceEvent.resource) && Intrinsics.areEqual((Object) this.action, (Object) resourceEvent.action);
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
        int hashCode7 = (((hashCode6 + (ciTest2 == null ? 0 : ciTest2.hashCode())) * 31) + this.f122dd.hashCode()) * 31;
        Context context2 = this.context;
        int hashCode8 = (((hashCode7 + (context2 == null ? 0 : context2.hashCode())) * 31) + this.resource.hashCode()) * 31;
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
        ResourceEventSession resourceEventSession = this.session;
        Source source2 = this.source;
        View view2 = this.view;
        Usr usr2 = this.usr;
        Connectivity connectivity2 = this.connectivity;
        Synthetics synthetics2 = this.synthetics;
        CiTest ciTest2 = this.ciTest;
        C0866Dd dd = this.f122dd;
        Context context2 = this.context;
        Resource resource2 = this.resource;
        return "ResourceEvent(date=" + j + ", application=" + application2 + ", service=" + str + ", session=" + resourceEventSession + ", source=" + source2 + ", view=" + view2 + ", usr=" + usr2 + ", connectivity=" + connectivity2 + ", synthetics=" + synthetics2 + ", ciTest=" + ciTest2 + ", dd=" + dd + ", context=" + context2 + ", resource=" + resource2 + ", action=" + this.action + ")";
    }

    public ResourceEvent(long j, Application application2, String str, ResourceEventSession resourceEventSession, Source source2, View view2, Usr usr2, Connectivity connectivity2, Synthetics synthetics2, CiTest ciTest2, C0866Dd dd, Context context2, Resource resource2, Action action2) {
        View view3 = view2;
        C0866Dd dd2 = dd;
        Resource resource3 = resource2;
        Intrinsics.checkNotNullParameter(application2, WebViewRumEventMapper.APPLICATION_KEY_NAME);
        Intrinsics.checkNotNullParameter(resourceEventSession, "session");
        Intrinsics.checkNotNullParameter(view3, "view");
        Intrinsics.checkNotNullParameter(dd2, "dd");
        Intrinsics.checkNotNullParameter(resource3, "resource");
        this.date = j;
        this.application = application2;
        this.service = str;
        this.session = resourceEventSession;
        this.source = source2;
        this.view = view3;
        this.usr = usr2;
        this.connectivity = connectivity2;
        this.synthetics = synthetics2;
        this.ciTest = ciTest2;
        this.f122dd = dd2;
        this.context = context2;
        this.resource = resource3;
        this.action = action2;
        this.type = "resource";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ResourceEvent(long r20, com.datadog.android.rum.model.ResourceEvent.Application r22, java.lang.String r23, com.datadog.android.rum.model.ResourceEvent.ResourceEventSession r24, com.datadog.android.rum.model.ResourceEvent.Source r25, com.datadog.android.rum.model.ResourceEvent.View r26, com.datadog.android.rum.model.ResourceEvent.Usr r27, com.datadog.android.rum.model.ResourceEvent.Connectivity r28, com.datadog.android.rum.model.ResourceEvent.Synthetics r29, com.datadog.android.rum.model.ResourceEvent.CiTest r30, com.datadog.android.rum.model.ResourceEvent.C0866Dd r31, com.datadog.android.rum.model.ResourceEvent.Context r32, com.datadog.android.rum.model.ResourceEvent.Resource r33, com.datadog.android.rum.model.ResourceEvent.Action r34, int r35, kotlin.jvm.internal.DefaultConstructorMarker r36) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ResourceEvent.<init>(long, com.datadog.android.rum.model.ResourceEvent$Application, java.lang.String, com.datadog.android.rum.model.ResourceEvent$ResourceEventSession, com.datadog.android.rum.model.ResourceEvent$Source, com.datadog.android.rum.model.ResourceEvent$View, com.datadog.android.rum.model.ResourceEvent$Usr, com.datadog.android.rum.model.ResourceEvent$Connectivity, com.datadog.android.rum.model.ResourceEvent$Synthetics, com.datadog.android.rum.model.ResourceEvent$CiTest, com.datadog.android.rum.model.ResourceEvent$Dd, com.datadog.android.rum.model.ResourceEvent$Context, com.datadog.android.rum.model.ResourceEvent$Resource, com.datadog.android.rum.model.ResourceEvent$Action, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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

    public final ResourceEventSession getSession() {
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

    public final C0866Dd getDd() {
        return this.f122dd;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Resource getResource() {
        return this.resource;
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
        jsonObject.add(WebViewRumEventMapper.DD_KEY_NAME, this.f122dd.toJson());
        Context context2 = this.context;
        if (context2 != null) {
            jsonObject.add(RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX, context2.toJson());
        }
        jsonObject.addProperty(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type);
        jsonObject.add("resource", this.resource.toJson());
        Action action2 = this.action;
        if (action2 != null) {
            jsonObject.add("action", action2.toJson());
        }
        return jsonObject;
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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
        public final com.datadog.android.rum.model.ResourceEvent fromJson(java.lang.String r20) throws com.google.gson.JsonParseException {
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
                com.datadog.android.rum.model.ResourceEvent$Application$Companion r3 = com.datadog.android.rum.model.ResourceEvent.Application.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Application r6 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
                com.datadog.android.rum.model.ResourceEvent$ResourceEventSession$Companion r8 = com.datadog.android.rum.model.ResourceEvent.ResourceEventSession.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$ResourceEventSession r8 = r8.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
                com.datadog.android.rum.model.ResourceEvent$Source$Companion r9 = com.datadog.android.rum.model.ResourceEvent.Source.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Source r2 = r9.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r9 = r2
            L_0x0068:
                java.lang.String r2 = "view"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$View$Companion r10 = com.datadog.android.rum.model.ResourceEvent.View.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$View r10 = r10.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
                com.datadog.android.rum.model.ResourceEvent$Usr$Companion r11 = com.datadog.android.rum.model.ResourceEvent.Usr.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Usr r2 = r11.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
                com.datadog.android.rum.model.ResourceEvent$Connectivity$Companion r12 = com.datadog.android.rum.model.ResourceEvent.Connectivity.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Connectivity r2 = r12.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
                com.datadog.android.rum.model.ResourceEvent$Synthetics$Companion r13 = com.datadog.android.rum.model.ResourceEvent.Synthetics.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Synthetics r2 = r13.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
                com.datadog.android.rum.model.ResourceEvent$CiTest$Companion r14 = com.datadog.android.rum.model.ResourceEvent.CiTest.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$CiTest r2 = r14.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r14 = r2
            L_0x00db:
                java.lang.String r2 = "_dd"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Dd$Companion r15 = com.datadog.android.rum.model.ResourceEvent.C0866Dd.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Dd r15 = r15.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
                com.datadog.android.rum.model.ResourceEvent$Context$Companion r3 = com.datadog.android.rum.model.ResourceEvent.Context.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Context r2 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r16 = r2
            L_0x0108:
                java.lang.String r2 = "resource"
                com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Resource$Companion r3 = com.datadog.android.rum.model.ResourceEvent.Resource.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Resource r17 = r3.fromJson(r2)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
                com.datadog.android.rum.model.ResourceEvent$Action$Companion r1 = com.datadog.android.rum.model.ResourceEvent.Action.Companion     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                com.datadog.android.rum.model.ResourceEvent$Action r0 = r1.fromJson(r0)     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
                r18 = r0
            L_0x0135:
                com.datadog.android.rum.model.ResourceEvent r0 = new com.datadog.android.rum.model.ResourceEvent     // Catch:{ IllegalStateException -> 0x0147, NumberFormatException -> 0x013c }
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
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ResourceEvent.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ResourceEvent");
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Application;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Application {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f124id;

        public static /* synthetic */ Application copy$default(Application application, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = application.f124id;
            }
            return application.copy(str);
        }

        @JvmStatic
        public static final Application fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f124id;
        }

        public final Application copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Application(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Application) && Intrinsics.areEqual((Object) this.f124id, (Object) ((Application) obj).f124id);
        }

        public int hashCode() {
            return this.f124id.hashCode();
        }

        public String toString() {
            return "Application(id=" + this.f124id + ")";
        }

        public Application(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f124id = str;
        }

        public final String getId() {
            return this.f124id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f124id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Application$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Application;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\nJ.\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSession;", "", "id", "", "type", "Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSessionType;", "hasReplay", "", "(Ljava/lang/String;Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSessionType;Ljava/lang/Boolean;)V", "getHasReplay", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getId", "()Ljava/lang/String;", "getType", "()Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSessionType;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSessionType;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSession;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class ResourceEventSession {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Boolean hasReplay;

        /* renamed from: id */
        private final String f127id;
        private final ResourceEventSessionType type;

        public static /* synthetic */ ResourceEventSession copy$default(ResourceEventSession resourceEventSession, String str, ResourceEventSessionType resourceEventSessionType, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = resourceEventSession.f127id;
            }
            if ((i & 2) != 0) {
                resourceEventSessionType = resourceEventSession.type;
            }
            if ((i & 4) != 0) {
                bool = resourceEventSession.hasReplay;
            }
            return resourceEventSession.copy(str, resourceEventSessionType, bool);
        }

        @JvmStatic
        public static final ResourceEventSession fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f127id;
        }

        public final ResourceEventSessionType component2() {
            return this.type;
        }

        public final Boolean component3() {
            return this.hasReplay;
        }

        public final ResourceEventSession copy(String str, ResourceEventSessionType resourceEventSessionType, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(resourceEventSessionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            return new ResourceEventSession(str, resourceEventSessionType, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ResourceEventSession)) {
                return false;
            }
            ResourceEventSession resourceEventSession = (ResourceEventSession) obj;
            return Intrinsics.areEqual((Object) this.f127id, (Object) resourceEventSession.f127id) && this.type == resourceEventSession.type && Intrinsics.areEqual((Object) this.hasReplay, (Object) resourceEventSession.hasReplay);
        }

        public int hashCode() {
            int hashCode = ((this.f127id.hashCode() * 31) + this.type.hashCode()) * 31;
            Boolean bool = this.hasReplay;
            return hashCode + (bool == null ? 0 : bool.hashCode());
        }

        public String toString() {
            String str = this.f127id;
            ResourceEventSessionType resourceEventSessionType = this.type;
            return "ResourceEventSession(id=" + str + ", type=" + resourceEventSessionType + ", hasReplay=" + this.hasReplay + ")";
        }

        public ResourceEventSession(String str, ResourceEventSessionType resourceEventSessionType, Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(resourceEventSessionType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            this.f127id = str;
            this.type = resourceEventSessionType;
            this.hasReplay = bool;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ResourceEventSession(String str, ResourceEventSessionType resourceEventSessionType, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, resourceEventSessionType, (i & 4) != 0 ? null : bool);
        }

        public final String getId() {
            return this.f127id;
        }

        public final ResourceEventSessionType getType() {
            return this.type;
        }

        public final Boolean getHasReplay() {
            return this.hasReplay;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f127id);
            jsonObject.add(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type.toJson());
            Boolean bool = this.hasReplay;
            if (bool != null) {
                jsonObject.addProperty("has_replay", Boolean.valueOf(bool.booleanValue()));
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSession$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSession;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final ResourceEventSession fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    String asString = asJsonObject.get("id").getAsString();
                    String asString2 = asJsonObject.get(RumEventDeserializer.EVENT_TYPE_KEY_NAME).getAsString();
                    ResourceEventSessionType.Companion companion = ResourceEventSessionType.Companion;
                    Intrinsics.checkNotNullExpressionValue(asString2, "it");
                    ResourceEventSessionType fromJson = companion.fromJson(asString2);
                    JsonElement jsonElement = asJsonObject.get("has_replay");
                    Boolean valueOf = jsonElement == null ? null : Boolean.valueOf(jsonElement.getAsBoolean());
                    Intrinsics.checkNotNullExpressionValue(asString, "id");
                    return new ResourceEventSession(asString, fromJson, valueOf);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\t\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\f¨\u0006\u001f"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$View;", "", "id", "", "referrer", "url", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "setName", "(Ljava/lang/String;)V", "getReferrer", "setReferrer", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class View {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f130id;
        private String name;
        private String referrer;
        private String url;

        public static /* synthetic */ View copy$default(View view, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = view.f130id;
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
            return this.f130id;
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
            return Intrinsics.areEqual((Object) this.f130id, (Object) view.f130id) && Intrinsics.areEqual((Object) this.referrer, (Object) view.referrer) && Intrinsics.areEqual((Object) this.url, (Object) view.url) && Intrinsics.areEqual((Object) this.name, (Object) view.name);
        }

        public int hashCode() {
            int hashCode = this.f130id.hashCode() * 31;
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
            String str = this.f130id;
            String str2 = this.referrer;
            String str3 = this.url;
            return "View(id=" + str + ", referrer=" + str2 + ", url=" + str3 + ", name=" + this.name + ")";
        }

        public View(String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str3, "url");
            this.f130id = str;
            this.referrer = str2;
            this.url = str3;
            this.name = str4;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ View(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2, str3, (i & 8) != 0 ? null : str4);
        }

        public final String getId() {
            return this.f130id;
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
            jsonObject.addProperty("id", this.f130id);
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$View$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$View;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0003JE\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Usr;", "", "id", "", "name", "email", "additionalProperties", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "getEmail", "()Ljava/lang/String;", "getId", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Usr {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final String[] RESERVED_PROPERTIES = {"id", "name", NotificationCompat.CATEGORY_EMAIL};
        private final Map<String, Object> additionalProperties;
        private final String email;

        /* renamed from: id */
        private final String f129id;
        private final String name;

        public Usr() {
            this((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Usr copy$default(Usr usr, String str, String str2, String str3, Map<String, Object> map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = usr.f129id;
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
            return this.f129id;
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
            return Intrinsics.areEqual((Object) this.f129id, (Object) usr.f129id) && Intrinsics.areEqual((Object) this.name, (Object) usr.name) && Intrinsics.areEqual((Object) this.email, (Object) usr.email) && Intrinsics.areEqual((Object) this.additionalProperties, (Object) usr.additionalProperties);
        }

        public int hashCode() {
            String str = this.f129id;
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
            String str = this.f129id;
            String str2 = this.name;
            String str3 = this.email;
            return "Usr(id=" + str + ", name=" + str2 + ", email=" + str3 + ", additionalProperties=" + this.additionalProperties + ")";
        }

        public Usr(String str, String str2, String str3, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "additionalProperties");
            this.f129id = str;
            this.name = str2;
            this.email = str3;
            this.additionalProperties = map;
        }

        public final String getId() {
            return this.f129id;
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
            String str = this.f129id;
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

        @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Usr$Companion;", "", "()V", "RESERVED_PROPERTIES", "", "", "getRESERVED_PROPERTIES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Usr;", "serializedObject", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Connectivity;", "", "status", "Lcom/datadog/android/rum/model/ResourceEvent$Status;", "interfaces", "", "Lcom/datadog/android/rum/model/ResourceEvent$Interface;", "cellular", "Lcom/datadog/android/rum/model/ResourceEvent$Cellular;", "(Lcom/datadog/android/rum/model/ResourceEvent$Status;Ljava/util/List;Lcom/datadog/android/rum/model/ResourceEvent$Cellular;)V", "getCellular", "()Lcom/datadog/android/rum/model/ResourceEvent$Cellular;", "getInterfaces", "()Ljava/util/List;", "getStatus", "()Lcom/datadog/android/rum/model/ResourceEvent$Status;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Connectivity$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Connectivity;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\tJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u001b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Synthetics;", "", "testId", "", "resultId", "injected", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getInjected", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getResultId", "()Ljava/lang/String;", "getTestId", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/datadog/android/rum/model/ResourceEvent$Synthetics;", "equals", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Synthetics$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Synthetics;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$CiTest;", "", "testExecutionId", "", "(Ljava/lang/String;)V", "getTestExecutionId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$CiTest$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$CiTest;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000  2\u00020\u0001:\u0001 B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J9\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u0006\u0010\u001d\u001a\u00020\u001eJ\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fXD¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\n¨\u0006!"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Dd;", "", "session", "Lcom/datadog/android/rum/model/ResourceEvent$DdSession;", "browserSdkVersion", "", "spanId", "traceId", "(Lcom/datadog/android/rum/model/ResourceEvent$DdSession;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBrowserSdkVersion", "()Ljava/lang/String;", "formatVersion", "", "getFormatVersion", "()J", "getSession", "()Lcom/datadog/android/rum/model/ResourceEvent$DdSession;", "getSpanId", "getTraceId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* renamed from: com.datadog.android.rum.model.ResourceEvent$Dd */
    /* compiled from: ResourceEvent.kt */
    public static final class C0866Dd {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String browserSdkVersion;
        private final long formatVersion;
        private final DdSession session;
        private final String spanId;
        private final String traceId;

        public C0866Dd() {
            this((DdSession) null, (String) null, (String) null, (String) null, 15, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ C0866Dd copy$default(C0866Dd dd, DdSession ddSession, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                ddSession = dd.session;
            }
            if ((i & 2) != 0) {
                str = dd.browserSdkVersion;
            }
            if ((i & 4) != 0) {
                str2 = dd.spanId;
            }
            if ((i & 8) != 0) {
                str3 = dd.traceId;
            }
            return dd.copy(ddSession, str, str2, str3);
        }

        @JvmStatic
        public static final C0866Dd fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final DdSession component1() {
            return this.session;
        }

        public final String component2() {
            return this.browserSdkVersion;
        }

        public final String component3() {
            return this.spanId;
        }

        public final String component4() {
            return this.traceId;
        }

        public final C0866Dd copy(DdSession ddSession, String str, String str2, String str3) {
            return new C0866Dd(ddSession, str, str2, str3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0866Dd)) {
                return false;
            }
            C0866Dd dd = (C0866Dd) obj;
            return Intrinsics.areEqual((Object) this.session, (Object) dd.session) && Intrinsics.areEqual((Object) this.browserSdkVersion, (Object) dd.browserSdkVersion) && Intrinsics.areEqual((Object) this.spanId, (Object) dd.spanId) && Intrinsics.areEqual((Object) this.traceId, (Object) dd.traceId);
        }

        public int hashCode() {
            DdSession ddSession = this.session;
            int i = 0;
            int hashCode = (ddSession == null ? 0 : ddSession.hashCode()) * 31;
            String str = this.browserSdkVersion;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.spanId;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.traceId;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return hashCode3 + i;
        }

        public String toString() {
            DdSession ddSession = this.session;
            String str = this.browserSdkVersion;
            String str2 = this.spanId;
            return "Dd(session=" + ddSession + ", browserSdkVersion=" + str + ", spanId=" + str2 + ", traceId=" + this.traceId + ")";
        }

        public C0866Dd(DdSession ddSession, String str, String str2, String str3) {
            this.session = ddSession;
            this.browserSdkVersion = str;
            this.spanId = str2;
            this.traceId = str3;
            this.formatVersion = 2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ C0866Dd(DdSession ddSession, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : ddSession, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3);
        }

        public final DdSession getSession() {
            return this.session;
        }

        public final String getBrowserSdkVersion() {
            return this.browserSdkVersion;
        }

        public final String getSpanId() {
            return this.spanId;
        }

        public final String getTraceId() {
            return this.traceId;
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
            String str2 = this.spanId;
            if (str2 != null) {
                jsonObject.addProperty("span_id", str2);
            }
            String str3 = this.traceId;
            if (str3 != null) {
                jsonObject.addProperty("trace_id", str3);
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Dd$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Dd;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* renamed from: com.datadog.android.rum.model.ResourceEvent$Dd$Companion */
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARNING: Removed duplicated region for block: B:11:0x002d A[Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }] */
            /* JADX WARNING: Removed duplicated region for block: B:12:0x002f A[Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }] */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x003b A[Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }] */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x003d A[Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }] */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x0049 A[Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }] */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A[Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }] */
            @kotlin.jvm.JvmStatic
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.datadog.android.rum.model.ResourceEvent.C0866Dd fromJson(java.lang.String r5) throws com.google.gson.JsonParseException {
                /*
                    r4 = this;
                    java.lang.String r4 = "serializedObject"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    com.google.gson.JsonElement r4 = com.google.gson.JsonParser.parseString(r5)     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    com.google.gson.JsonObject r4 = r4.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    java.lang.String r5 = "session"
                    com.google.gson.JsonElement r5 = r4.get(r5)     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    r0 = 0
                    if (r5 != 0) goto L_0x0018
                L_0x0016:
                    r5 = r0
                    goto L_0x0025
                L_0x0018:
                    java.lang.String r5 = r5.toString()     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    if (r5 != 0) goto L_0x001f
                    goto L_0x0016
                L_0x001f:
                    com.datadog.android.rum.model.ResourceEvent$DdSession$Companion r1 = com.datadog.android.rum.model.ResourceEvent.DdSession.Companion     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    com.datadog.android.rum.model.ResourceEvent$DdSession r5 = r1.fromJson(r5)     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                L_0x0025:
                    java.lang.String r1 = "browser_sdk_version"
                    com.google.gson.JsonElement r1 = r4.get(r1)     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    if (r1 != 0) goto L_0x002f
                    r1 = r0
                    goto L_0x0033
                L_0x002f:
                    java.lang.String r1 = r1.getAsString()     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                L_0x0033:
                    java.lang.String r2 = "span_id"
                    com.google.gson.JsonElement r2 = r4.get(r2)     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    if (r2 != 0) goto L_0x003d
                    r2 = r0
                    goto L_0x0041
                L_0x003d:
                    java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                L_0x0041:
                    java.lang.String r3 = "trace_id"
                    com.google.gson.JsonElement r4 = r4.get(r3)     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    if (r4 != 0) goto L_0x004a
                    goto L_0x004e
                L_0x004a:
                    java.lang.String r0 = r4.getAsString()     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                L_0x004e:
                    com.datadog.android.rum.model.ResourceEvent$Dd r4 = new com.datadog.android.rum.model.ResourceEvent$Dd     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    r4.<init>(r5, r1, r2, r0)     // Catch:{ IllegalStateException -> 0x005f, NumberFormatException -> 0x0054 }
                    return r4
                L_0x0054:
                    r4 = move-exception
                    com.google.gson.JsonParseException r5 = new com.google.gson.JsonParseException
                    java.lang.String r4 = r4.getMessage()
                    r5.<init>((java.lang.String) r4)
                    throw r5
                L_0x005f:
                    r4 = move-exception
                    com.google.gson.JsonParseException r5 = new com.google.gson.JsonParseException
                    java.lang.String r4 = r4.getMessage()
                    r5.<init>((java.lang.String) r4)
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ResourceEvent.C0866Dd.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ResourceEvent$Dd");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003HÆ\u0003J!\u0010\t\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Context;", "", "additionalProperties", "", "", "(Ljava/util/Map;)V", "getAdditionalProperties", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Context$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Context;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 Q2\u00020\u0001:\u0001QB¡\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0002\u0010\u001bJ\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u001aHÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\t\u0010C\u001a\u00020\nHÆ\u0003J\u0010\u0010D\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\u000b\u0010E\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0010HÆ\u0003J°\u0001\u0010G\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÆ\u0001¢\u0006\u0002\u0010HJ\u0013\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010L\u001a\u00020MHÖ\u0001J\u0006\u0010N\u001a\u00020OJ\t\u0010P\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0015\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u00100\u001a\u0004\b.\u0010/R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u00100\u001a\u0004\b3\u0010/R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u001a\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010'\"\u0004\b7\u00108¨\u0006R"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Resource;", "", "id", "", "type", "Lcom/datadog/android/rum/model/ResourceEvent$ResourceType;", "method", "Lcom/datadog/android/rum/model/ResourceEvent$Method;", "url", "statusCode", "", "duration", "size", "redirect", "Lcom/datadog/android/rum/model/ResourceEvent$Redirect;", "dns", "Lcom/datadog/android/rum/model/ResourceEvent$Dns;", "connect", "Lcom/datadog/android/rum/model/ResourceEvent$Connect;", "ssl", "Lcom/datadog/android/rum/model/ResourceEvent$Ssl;", "firstByte", "Lcom/datadog/android/rum/model/ResourceEvent$FirstByte;", "download", "Lcom/datadog/android/rum/model/ResourceEvent$Download;", "provider", "Lcom/datadog/android/rum/model/ResourceEvent$Provider;", "(Ljava/lang/String;Lcom/datadog/android/rum/model/ResourceEvent$ResourceType;Lcom/datadog/android/rum/model/ResourceEvent$Method;Ljava/lang/String;Ljava/lang/Long;JLjava/lang/Long;Lcom/datadog/android/rum/model/ResourceEvent$Redirect;Lcom/datadog/android/rum/model/ResourceEvent$Dns;Lcom/datadog/android/rum/model/ResourceEvent$Connect;Lcom/datadog/android/rum/model/ResourceEvent$Ssl;Lcom/datadog/android/rum/model/ResourceEvent$FirstByte;Lcom/datadog/android/rum/model/ResourceEvent$Download;Lcom/datadog/android/rum/model/ResourceEvent$Provider;)V", "getConnect", "()Lcom/datadog/android/rum/model/ResourceEvent$Connect;", "getDns", "()Lcom/datadog/android/rum/model/ResourceEvent$Dns;", "getDownload", "()Lcom/datadog/android/rum/model/ResourceEvent$Download;", "getDuration", "()J", "getFirstByte", "()Lcom/datadog/android/rum/model/ResourceEvent$FirstByte;", "getId", "()Ljava/lang/String;", "getMethod", "()Lcom/datadog/android/rum/model/ResourceEvent$Method;", "getProvider", "()Lcom/datadog/android/rum/model/ResourceEvent$Provider;", "getRedirect", "()Lcom/datadog/android/rum/model/ResourceEvent$Redirect;", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSsl", "()Lcom/datadog/android/rum/model/ResourceEvent$Ssl;", "getStatusCode", "getType", "()Lcom/datadog/android/rum/model/ResourceEvent$ResourceType;", "getUrl", "setUrl", "(Ljava/lang/String;)V", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Lcom/datadog/android/rum/model/ResourceEvent$ResourceType;Lcom/datadog/android/rum/model/ResourceEvent$Method;Ljava/lang/String;Ljava/lang/Long;JLjava/lang/Long;Lcom/datadog/android/rum/model/ResourceEvent$Redirect;Lcom/datadog/android/rum/model/ResourceEvent$Dns;Lcom/datadog/android/rum/model/ResourceEvent$Connect;Lcom/datadog/android/rum/model/ResourceEvent$Ssl;Lcom/datadog/android/rum/model/ResourceEvent$FirstByte;Lcom/datadog/android/rum/model/ResourceEvent$Download;Lcom/datadog/android/rum/model/ResourceEvent$Provider;)Lcom/datadog/android/rum/model/ResourceEvent$Resource;", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Resource {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final Connect connect;
        private final Dns dns;
        private final Download download;
        private final long duration;
        private final FirstByte firstByte;

        /* renamed from: id */
        private final String f126id;
        private final Method method;
        private final Provider provider;
        private final Redirect redirect;
        private final Long size;
        private final Ssl ssl;
        private final Long statusCode;
        private final ResourceType type;
        private String url;

        public static /* synthetic */ Resource copy$default(Resource resource, String str, ResourceType resourceType, Method method2, String str2, Long l, long j, Long l2, Redirect redirect2, Dns dns2, Connect connect2, Ssl ssl2, FirstByte firstByte2, Download download2, Provider provider2, int i, Object obj) {
            Resource resource2 = resource;
            int i2 = i;
            return resource.copy((i2 & 1) != 0 ? resource2.f126id : str, (i2 & 2) != 0 ? resource2.type : resourceType, (i2 & 4) != 0 ? resource2.method : method2, (i2 & 8) != 0 ? resource2.url : str2, (i2 & 16) != 0 ? resource2.statusCode : l, (i2 & 32) != 0 ? resource2.duration : j, (i2 & 64) != 0 ? resource2.size : l2, (i2 & 128) != 0 ? resource2.redirect : redirect2, (i2 & 256) != 0 ? resource2.dns : dns2, (i2 & 512) != 0 ? resource2.connect : connect2, (i2 & 1024) != 0 ? resource2.ssl : ssl2, (i2 & 2048) != 0 ? resource2.firstByte : firstByte2, (i2 & 4096) != 0 ? resource2.download : download2, (i2 & 8192) != 0 ? resource2.provider : provider2);
        }

        @JvmStatic
        public static final Resource fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f126id;
        }

        public final Connect component10() {
            return this.connect;
        }

        public final Ssl component11() {
            return this.ssl;
        }

        public final FirstByte component12() {
            return this.firstByte;
        }

        public final Download component13() {
            return this.download;
        }

        public final Provider component14() {
            return this.provider;
        }

        public final ResourceType component2() {
            return this.type;
        }

        public final Method component3() {
            return this.method;
        }

        public final String component4() {
            return this.url;
        }

        public final Long component5() {
            return this.statusCode;
        }

        public final long component6() {
            return this.duration;
        }

        public final Long component7() {
            return this.size;
        }

        public final Redirect component8() {
            return this.redirect;
        }

        public final Dns component9() {
            return this.dns;
        }

        public final Resource copy(String str, ResourceType resourceType, Method method2, String str2, Long l, long j, Long l2, Redirect redirect2, Dns dns2, Connect connect2, Ssl ssl2, FirstByte firstByte2, Download download2, Provider provider2) {
            ResourceType resourceType2 = resourceType;
            Intrinsics.checkNotNullParameter(resourceType2, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            String str3 = str2;
            Intrinsics.checkNotNullParameter(str3, "url");
            return new Resource(str, resourceType2, method2, str3, l, j, l2, redirect2, dns2, connect2, ssl2, firstByte2, download2, provider2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Resource)) {
                return false;
            }
            Resource resource = (Resource) obj;
            return Intrinsics.areEqual((Object) this.f126id, (Object) resource.f126id) && this.type == resource.type && this.method == resource.method && Intrinsics.areEqual((Object) this.url, (Object) resource.url) && Intrinsics.areEqual((Object) this.statusCode, (Object) resource.statusCode) && this.duration == resource.duration && Intrinsics.areEqual((Object) this.size, (Object) resource.size) && Intrinsics.areEqual((Object) this.redirect, (Object) resource.redirect) && Intrinsics.areEqual((Object) this.dns, (Object) resource.dns) && Intrinsics.areEqual((Object) this.connect, (Object) resource.connect) && Intrinsics.areEqual((Object) this.ssl, (Object) resource.ssl) && Intrinsics.areEqual((Object) this.firstByte, (Object) resource.firstByte) && Intrinsics.areEqual((Object) this.download, (Object) resource.download) && Intrinsics.areEqual((Object) this.provider, (Object) resource.provider);
        }

        public int hashCode() {
            String str = this.f126id;
            int i = 0;
            int hashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.type.hashCode()) * 31;
            Method method2 = this.method;
            int hashCode2 = (((hashCode + (method2 == null ? 0 : method2.hashCode())) * 31) + this.url.hashCode()) * 31;
            Long l = this.statusCode;
            int hashCode3 = (((hashCode2 + (l == null ? 0 : l.hashCode())) * 31) + Long.hashCode(this.duration)) * 31;
            Long l2 = this.size;
            int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
            Redirect redirect2 = this.redirect;
            int hashCode5 = (hashCode4 + (redirect2 == null ? 0 : redirect2.hashCode())) * 31;
            Dns dns2 = this.dns;
            int hashCode6 = (hashCode5 + (dns2 == null ? 0 : dns2.hashCode())) * 31;
            Connect connect2 = this.connect;
            int hashCode7 = (hashCode6 + (connect2 == null ? 0 : connect2.hashCode())) * 31;
            Ssl ssl2 = this.ssl;
            int hashCode8 = (hashCode7 + (ssl2 == null ? 0 : ssl2.hashCode())) * 31;
            FirstByte firstByte2 = this.firstByte;
            int hashCode9 = (hashCode8 + (firstByte2 == null ? 0 : firstByte2.hashCode())) * 31;
            Download download2 = this.download;
            int hashCode10 = (hashCode9 + (download2 == null ? 0 : download2.hashCode())) * 31;
            Provider provider2 = this.provider;
            if (provider2 != null) {
                i = provider2.hashCode();
            }
            return hashCode10 + i;
        }

        public String toString() {
            String str = this.f126id;
            ResourceType resourceType = this.type;
            Method method2 = this.method;
            String str2 = this.url;
            Long l = this.statusCode;
            long j = this.duration;
            Long l2 = this.size;
            Redirect redirect2 = this.redirect;
            Dns dns2 = this.dns;
            Connect connect2 = this.connect;
            Ssl ssl2 = this.ssl;
            FirstByte firstByte2 = this.firstByte;
            Download download2 = this.download;
            return "Resource(id=" + str + ", type=" + resourceType + ", method=" + method2 + ", url=" + str2 + ", statusCode=" + l + ", duration=" + j + ", size=" + l2 + ", redirect=" + redirect2 + ", dns=" + dns2 + ", connect=" + connect2 + ", ssl=" + ssl2 + ", firstByte=" + firstByte2 + ", download=" + download2 + ", provider=" + this.provider + ")";
        }

        public Resource(String str, ResourceType resourceType, Method method2, String str2, Long l, long j, Long l2, Redirect redirect2, Dns dns2, Connect connect2, Ssl ssl2, FirstByte firstByte2, Download download2, Provider provider2) {
            Intrinsics.checkNotNullParameter(resourceType, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
            Intrinsics.checkNotNullParameter(str2, "url");
            this.f126id = str;
            this.type = resourceType;
            this.method = method2;
            this.url = str2;
            this.statusCode = l;
            this.duration = j;
            this.size = l2;
            this.redirect = redirect2;
            this.dns = dns2;
            this.connect = connect2;
            this.ssl = ssl2;
            this.firstByte = firstByte2;
            this.download = download2;
            this.provider = provider2;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ Resource(java.lang.String r20, com.datadog.android.rum.model.ResourceEvent.ResourceType r21, com.datadog.android.rum.model.ResourceEvent.Method r22, java.lang.String r23, java.lang.Long r24, long r25, java.lang.Long r27, com.datadog.android.rum.model.ResourceEvent.Redirect r28, com.datadog.android.rum.model.ResourceEvent.Dns r29, com.datadog.android.rum.model.ResourceEvent.Connect r30, com.datadog.android.rum.model.ResourceEvent.Ssl r31, com.datadog.android.rum.model.ResourceEvent.FirstByte r32, com.datadog.android.rum.model.ResourceEvent.Download r33, com.datadog.android.rum.model.ResourceEvent.Provider r34, int r35, kotlin.jvm.internal.DefaultConstructorMarker r36) {
            /*
                r19 = this;
                r0 = r35
                r1 = r0 & 1
                r2 = 0
                if (r1 == 0) goto L_0x0009
                r4 = r2
                goto L_0x000b
            L_0x0009:
                r4 = r20
            L_0x000b:
                r1 = r0 & 4
                if (r1 == 0) goto L_0x0011
                r6 = r2
                goto L_0x0013
            L_0x0011:
                r6 = r22
            L_0x0013:
                r1 = r0 & 16
                if (r1 == 0) goto L_0x0019
                r8 = r2
                goto L_0x001b
            L_0x0019:
                r8 = r24
            L_0x001b:
                r1 = r0 & 64
                if (r1 == 0) goto L_0x0021
                r11 = r2
                goto L_0x0023
            L_0x0021:
                r11 = r27
            L_0x0023:
                r1 = r0 & 128(0x80, float:1.794E-43)
                if (r1 == 0) goto L_0x0029
                r12 = r2
                goto L_0x002b
            L_0x0029:
                r12 = r28
            L_0x002b:
                r1 = r0 & 256(0x100, float:3.59E-43)
                if (r1 == 0) goto L_0x0031
                r13 = r2
                goto L_0x0033
            L_0x0031:
                r13 = r29
            L_0x0033:
                r1 = r0 & 512(0x200, float:7.175E-43)
                if (r1 == 0) goto L_0x0039
                r14 = r2
                goto L_0x003b
            L_0x0039:
                r14 = r30
            L_0x003b:
                r1 = r0 & 1024(0x400, float:1.435E-42)
                if (r1 == 0) goto L_0x0041
                r15 = r2
                goto L_0x0043
            L_0x0041:
                r15 = r31
            L_0x0043:
                r1 = r0 & 2048(0x800, float:2.87E-42)
                if (r1 == 0) goto L_0x004a
                r16 = r2
                goto L_0x004c
            L_0x004a:
                r16 = r32
            L_0x004c:
                r1 = r0 & 4096(0x1000, float:5.74E-42)
                if (r1 == 0) goto L_0x0053
                r17 = r2
                goto L_0x0055
            L_0x0053:
                r17 = r33
            L_0x0055:
                r0 = r0 & 8192(0x2000, float:1.14794E-41)
                if (r0 == 0) goto L_0x005c
                r18 = r2
                goto L_0x005e
            L_0x005c:
                r18 = r34
            L_0x005e:
                r3 = r19
                r5 = r21
                r7 = r23
                r9 = r25
                r3.<init>(r4, r5, r6, r7, r8, r9, r11, r12, r13, r14, r15, r16, r17, r18)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ResourceEvent.Resource.<init>(java.lang.String, com.datadog.android.rum.model.ResourceEvent$ResourceType, com.datadog.android.rum.model.ResourceEvent$Method, java.lang.String, java.lang.Long, long, java.lang.Long, com.datadog.android.rum.model.ResourceEvent$Redirect, com.datadog.android.rum.model.ResourceEvent$Dns, com.datadog.android.rum.model.ResourceEvent$Connect, com.datadog.android.rum.model.ResourceEvent$Ssl, com.datadog.android.rum.model.ResourceEvent$FirstByte, com.datadog.android.rum.model.ResourceEvent$Download, com.datadog.android.rum.model.ResourceEvent$Provider, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final String getId() {
            return this.f126id;
        }

        public final ResourceType getType() {
            return this.type;
        }

        public final Method getMethod() {
            return this.method;
        }

        public final String getUrl() {
            return this.url;
        }

        public final void setUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.url = str;
        }

        public final Long getStatusCode() {
            return this.statusCode;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final Long getSize() {
            return this.size;
        }

        public final Redirect getRedirect() {
            return this.redirect;
        }

        public final Dns getDns() {
            return this.dns;
        }

        public final Connect getConnect() {
            return this.connect;
        }

        public final Ssl getSsl() {
            return this.ssl;
        }

        public final FirstByte getFirstByte() {
            return this.firstByte;
        }

        public final Download getDownload() {
            return this.download;
        }

        public final Provider getProvider() {
            return this.provider;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.f126id;
            if (str != null) {
                jsonObject.addProperty("id", str);
            }
            jsonObject.add(RumEventDeserializer.EVENT_TYPE_KEY_NAME, this.type.toJson());
            Method method2 = this.method;
            if (method2 != null) {
                jsonObject.add("method", method2.toJson());
            }
            jsonObject.addProperty("url", this.url);
            Long l = this.statusCode;
            if (l != null) {
                jsonObject.addProperty("status_code", (Number) Long.valueOf(l.longValue()));
            }
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            Long l2 = this.size;
            if (l2 != null) {
                jsonObject.addProperty("size", (Number) Long.valueOf(l2.longValue()));
            }
            Redirect redirect2 = this.redirect;
            if (redirect2 != null) {
                jsonObject.add("redirect", redirect2.toJson());
            }
            Dns dns2 = this.dns;
            if (dns2 != null) {
                jsonObject.add("dns", dns2.toJson());
            }
            Connect connect2 = this.connect;
            if (connect2 != null) {
                jsonObject.add("connect", connect2.toJson());
            }
            Ssl ssl2 = this.ssl;
            if (ssl2 != null) {
                jsonObject.add("ssl", ssl2.toJson());
            }
            FirstByte firstByte2 = this.firstByte;
            if (firstByte2 != null) {
                jsonObject.add("first_byte", firstByte2.toJson());
            }
            Download download2 = this.download;
            if (download2 != null) {
                jsonObject.add("download", download2.toJson());
            }
            Provider provider2 = this.provider;
            if (provider2 != null) {
                jsonObject.add("provider", provider2.toJson());
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Resource$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Resource;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARNING: Removed duplicated region for block: B:15:0x005e A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x0060 A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x007b A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x007d A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:24:0x0090 A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:31:0x00a8 A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:38:0x00c0 A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:45:0x00d9 A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:52:0x00f3 A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:59:0x010d A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:66:0x0127 A[Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }] */
            @kotlin.jvm.JvmStatic
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.datadog.android.rum.model.ResourceEvent.Resource fromJson(java.lang.String r21) throws com.google.gson.JsonParseException {
                /*
                    r20 = this;
                    java.lang.String r0 = "url"
                    java.lang.String r1 = "serializedObject"
                    r2 = r21
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                    com.google.gson.JsonElement r1 = com.google.gson.JsonParser.parseString(r21)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.google.gson.JsonObject r1 = r1.getAsJsonObject()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.String r2 = "id"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r3 = 0
                    if (r2 != 0) goto L_0x001c
                    r5 = r3
                    goto L_0x0021
                L_0x001c:
                    java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r5 = r2
                L_0x0021:
                    java.lang.String r2 = "type"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$ResourceType$Companion r4 = com.datadog.android.rum.model.ResourceEvent.ResourceType.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.String r6 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$ResourceType r6 = r4.fromJson(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.String r2 = "method"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x0040
                L_0x003e:
                    r7 = r3
                    goto L_0x004e
                L_0x0040:
                    java.lang.String r2 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x0047
                    goto L_0x003e
                L_0x0047:
                    com.datadog.android.rum.model.ResourceEvent$Method$Companion r4 = com.datadog.android.rum.model.ResourceEvent.Method.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$Method r2 = r4.fromJson(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r7 = r2
                L_0x004e:
                    com.google.gson.JsonElement r2 = r1.get(r0)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.String r8 = r2.getAsString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.String r2 = "status_code"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x0060
                    r9 = r3
                    goto L_0x0069
                L_0x0060:
                    long r9 = r2.getAsLong()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.Long r2 = java.lang.Long.valueOf(r9)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r9 = r2
                L_0x0069:
                    java.lang.String r2 = "duration"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    long r10 = r2.getAsLong()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.String r2 = "size"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x007d
                    r12 = r3
                    goto L_0x0086
                L_0x007d:
                    long r12 = r2.getAsLong()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    java.lang.Long r2 = java.lang.Long.valueOf(r12)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r12 = r2
                L_0x0086:
                    java.lang.String r2 = "redirect"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x0090
                L_0x008e:
                    r13 = r3
                    goto L_0x009e
                L_0x0090:
                    java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x0097
                    goto L_0x008e
                L_0x0097:
                    com.datadog.android.rum.model.ResourceEvent$Redirect$Companion r4 = com.datadog.android.rum.model.ResourceEvent.Redirect.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$Redirect r2 = r4.fromJson(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r13 = r2
                L_0x009e:
                    java.lang.String r2 = "dns"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x00a8
                L_0x00a6:
                    r14 = r3
                    goto L_0x00b6
                L_0x00a8:
                    java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x00af
                    goto L_0x00a6
                L_0x00af:
                    com.datadog.android.rum.model.ResourceEvent$Dns$Companion r4 = com.datadog.android.rum.model.ResourceEvent.Dns.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$Dns r2 = r4.fromJson(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r14 = r2
                L_0x00b6:
                    java.lang.String r2 = "connect"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x00c0
                L_0x00be:
                    r15 = r3
                    goto L_0x00ce
                L_0x00c0:
                    java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x00c7
                    goto L_0x00be
                L_0x00c7:
                    com.datadog.android.rum.model.ResourceEvent$Connect$Companion r4 = com.datadog.android.rum.model.ResourceEvent.Connect.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$Connect r2 = r4.fromJson(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r15 = r2
                L_0x00ce:
                    java.lang.String r2 = "ssl"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x00d9
                L_0x00d6:
                    r16 = r3
                    goto L_0x00e8
                L_0x00d9:
                    java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x00e0
                    goto L_0x00d6
                L_0x00e0:
                    com.datadog.android.rum.model.ResourceEvent$Ssl$Companion r4 = com.datadog.android.rum.model.ResourceEvent.Ssl.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$Ssl r2 = r4.fromJson(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r16 = r2
                L_0x00e8:
                    java.lang.String r2 = "first_byte"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x00f3
                L_0x00f0:
                    r17 = r3
                    goto L_0x0102
                L_0x00f3:
                    java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x00fa
                    goto L_0x00f0
                L_0x00fa:
                    com.datadog.android.rum.model.ResourceEvent$FirstByte$Companion r4 = com.datadog.android.rum.model.ResourceEvent.FirstByte.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$FirstByte r2 = r4.fromJson(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r17 = r2
                L_0x0102:
                    java.lang.String r2 = "download"
                    com.google.gson.JsonElement r2 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x010d
                L_0x010a:
                    r18 = r3
                    goto L_0x011c
                L_0x010d:
                    java.lang.String r2 = r2.toString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r2 != 0) goto L_0x0114
                    goto L_0x010a
                L_0x0114:
                    com.datadog.android.rum.model.ResourceEvent$Download$Companion r4 = com.datadog.android.rum.model.ResourceEvent.Download.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$Download r2 = r4.fromJson(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r18 = r2
                L_0x011c:
                    java.lang.String r2 = "provider"
                    com.google.gson.JsonElement r1 = r1.get(r2)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r1 != 0) goto L_0x0127
                L_0x0124:
                    r19 = r3
                    goto L_0x0135
                L_0x0127:
                    java.lang.String r1 = r1.toString()     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    if (r1 != 0) goto L_0x012e
                    goto L_0x0124
                L_0x012e:
                    com.datadog.android.rum.model.ResourceEvent$Provider$Companion r2 = com.datadog.android.rum.model.ResourceEvent.Provider.Companion     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    com.datadog.android.rum.model.ResourceEvent$Provider r3 = r2.fromJson(r1)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    goto L_0x0124
                L_0x0135:
                    com.datadog.android.rum.model.ResourceEvent$Resource r1 = new com.datadog.android.rum.model.ResourceEvent$Resource     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    r4 = r1
                    r4.<init>(r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ IllegalStateException -> 0x014a, NumberFormatException -> 0x013f }
                    return r1
                L_0x013f:
                    r0 = move-exception
                    com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                    java.lang.String r0 = r0.getMessage()
                    r1.<init>((java.lang.String) r0)
                    throw r1
                L_0x014a:
                    r0 = move-exception
                    com.google.gson.JsonParseException r1 = new com.google.gson.JsonParseException
                    java.lang.String r0 = r0.getMessage()
                    r1.<init>((java.lang.String) r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.model.ResourceEvent.Resource.Companion.fromJson(java.lang.String):com.datadog.android.rum.model.ResourceEvent$Resource");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Action;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Action {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        /* renamed from: id */
        private final String f123id;

        public static /* synthetic */ Action copy$default(Action action, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = action.f123id;
            }
            return action.copy(str);
        }

        @JvmStatic
        public static final Action fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.f123id;
        }

        public final Action copy(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            return new Action(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Action) && Intrinsics.areEqual((Object) this.f123id, (Object) ((Action) obj).f123id);
        }

        public int hashCode() {
            return this.f123id.hashCode();
        }

        public String toString() {
            return "Action(id=" + this.f123id + ")";
        }

        public Action(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.f123id = str;
        }

        public final String getId() {
            return this.f123id;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", this.f123id);
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Action$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Action;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Cellular;", "", "technology", "", "carrierName", "(Ljava/lang/String;Ljava/lang/String;)V", "getCarrierName", "()Ljava/lang/String;", "getTechnology", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Cellular$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Cellular;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$DdSession;", "", "plan", "Lcom/datadog/android/rum/model/ResourceEvent$Plan;", "(Lcom/datadog/android/rum/model/ResourceEvent$Plan;)V", "getPlan", "()Lcom/datadog/android/rum/model/ResourceEvent$Plan;", "component1", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$DdSession$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$DdSession;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Redirect;", "", "duration", "", "start", "(JJ)V", "getDuration", "()J", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Redirect {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long duration;
        private final long start;

        public static /* synthetic */ Redirect copy$default(Redirect redirect, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = redirect.duration;
            }
            if ((i & 2) != 0) {
                j2 = redirect.start;
            }
            return redirect.copy(j, j2);
        }

        @JvmStatic
        public static final Redirect fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.duration;
        }

        public final long component2() {
            return this.start;
        }

        public final Redirect copy(long j, long j2) {
            return new Redirect(j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Redirect)) {
                return false;
            }
            Redirect redirect = (Redirect) obj;
            return this.duration == redirect.duration && this.start == redirect.start;
        }

        public int hashCode() {
            return (Long.hashCode(this.duration) * 31) + Long.hashCode(this.start);
        }

        public String toString() {
            long j = this.duration;
            return "Redirect(duration=" + j + ", start=" + this.start + ")";
        }

        public Redirect(long j, long j2) {
            this.duration = j;
            this.start = j2;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final long getStart() {
            return this.start;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            jsonObject.addProperty("start", (Number) Long.valueOf(this.start));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Redirect$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Redirect;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Redirect fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    return new Redirect(asJsonObject.get(LogAttributes.DURATION).getAsLong(), asJsonObject.get("start").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Dns;", "", "duration", "", "start", "(JJ)V", "getDuration", "()J", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Dns {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long duration;
        private final long start;

        public static /* synthetic */ Dns copy$default(Dns dns, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = dns.duration;
            }
            if ((i & 2) != 0) {
                j2 = dns.start;
            }
            return dns.copy(j, j2);
        }

        @JvmStatic
        public static final Dns fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.duration;
        }

        public final long component2() {
            return this.start;
        }

        public final Dns copy(long j, long j2) {
            return new Dns(j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Dns)) {
                return false;
            }
            Dns dns = (Dns) obj;
            return this.duration == dns.duration && this.start == dns.start;
        }

        public int hashCode() {
            return (Long.hashCode(this.duration) * 31) + Long.hashCode(this.start);
        }

        public String toString() {
            long j = this.duration;
            return "Dns(duration=" + j + ", start=" + this.start + ")";
        }

        public Dns(long j, long j2) {
            this.duration = j;
            this.start = j2;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final long getStart() {
            return this.start;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            jsonObject.addProperty("start", (Number) Long.valueOf(this.start));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Dns$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Dns;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Dns fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    return new Dns(asJsonObject.get(LogAttributes.DURATION).getAsLong(), asJsonObject.get("start").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Connect;", "", "duration", "", "start", "(JJ)V", "getDuration", "()J", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Connect {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long duration;
        private final long start;

        public static /* synthetic */ Connect copy$default(Connect connect, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = connect.duration;
            }
            if ((i & 2) != 0) {
                j2 = connect.start;
            }
            return connect.copy(j, j2);
        }

        @JvmStatic
        public static final Connect fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.duration;
        }

        public final long component2() {
            return this.start;
        }

        public final Connect copy(long j, long j2) {
            return new Connect(j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Connect)) {
                return false;
            }
            Connect connect = (Connect) obj;
            return this.duration == connect.duration && this.start == connect.start;
        }

        public int hashCode() {
            return (Long.hashCode(this.duration) * 31) + Long.hashCode(this.start);
        }

        public String toString() {
            long j = this.duration;
            return "Connect(duration=" + j + ", start=" + this.start + ")";
        }

        public Connect(long j, long j2) {
            this.duration = j;
            this.start = j2;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final long getStart() {
            return this.start;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            jsonObject.addProperty("start", (Number) Long.valueOf(this.start));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Connect$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Connect;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Connect fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    return new Connect(asJsonObject.get(LogAttributes.DURATION).getAsLong(), asJsonObject.get("start").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Ssl;", "", "duration", "", "start", "(JJ)V", "getDuration", "()J", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Ssl {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long duration;
        private final long start;

        public static /* synthetic */ Ssl copy$default(Ssl ssl, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = ssl.duration;
            }
            if ((i & 2) != 0) {
                j2 = ssl.start;
            }
            return ssl.copy(j, j2);
        }

        @JvmStatic
        public static final Ssl fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.duration;
        }

        public final long component2() {
            return this.start;
        }

        public final Ssl copy(long j, long j2) {
            return new Ssl(j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Ssl)) {
                return false;
            }
            Ssl ssl = (Ssl) obj;
            return this.duration == ssl.duration && this.start == ssl.start;
        }

        public int hashCode() {
            return (Long.hashCode(this.duration) * 31) + Long.hashCode(this.start);
        }

        public String toString() {
            long j = this.duration;
            return "Ssl(duration=" + j + ", start=" + this.start + ")";
        }

        public Ssl(long j, long j2) {
            this.duration = j;
            this.start = j2;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final long getStart() {
            return this.start;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            jsonObject.addProperty("start", (Number) Long.valueOf(this.start));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Ssl$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Ssl;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Ssl fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    return new Ssl(asJsonObject.get(LogAttributes.DURATION).getAsLong(), asJsonObject.get("start").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$FirstByte;", "", "duration", "", "start", "(JJ)V", "getDuration", "()J", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class FirstByte {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long duration;
        private final long start;

        public static /* synthetic */ FirstByte copy$default(FirstByte firstByte, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = firstByte.duration;
            }
            if ((i & 2) != 0) {
                j2 = firstByte.start;
            }
            return firstByte.copy(j, j2);
        }

        @JvmStatic
        public static final FirstByte fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.duration;
        }

        public final long component2() {
            return this.start;
        }

        public final FirstByte copy(long j, long j2) {
            return new FirstByte(j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FirstByte)) {
                return false;
            }
            FirstByte firstByte = (FirstByte) obj;
            return this.duration == firstByte.duration && this.start == firstByte.start;
        }

        public int hashCode() {
            return (Long.hashCode(this.duration) * 31) + Long.hashCode(this.start);
        }

        public String toString() {
            long j = this.duration;
            return "FirstByte(duration=" + j + ", start=" + this.start + ")";
        }

        public FirstByte(long j, long j2) {
            this.duration = j;
            this.start = j2;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final long getStart() {
            return this.start;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            jsonObject.addProperty("start", (Number) Long.valueOf(this.start));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$FirstByte$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$FirstByte;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final FirstByte fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    return new FirstByte(asJsonObject.get(LogAttributes.DURATION).getAsLong(), asJsonObject.get("start").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Download;", "", "duration", "", "start", "(JJ)V", "getDuration", "()J", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Download {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final long duration;
        private final long start;

        public static /* synthetic */ Download copy$default(Download download, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = download.duration;
            }
            if ((i & 2) != 0) {
                j2 = download.start;
            }
            return download.copy(j, j2);
        }

        @JvmStatic
        public static final Download fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final long component1() {
            return this.duration;
        }

        public final long component2() {
            return this.start;
        }

        public final Download copy(long j, long j2) {
            return new Download(j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Download)) {
                return false;
            }
            Download download = (Download) obj;
            return this.duration == download.duration && this.start == download.start;
        }

        public int hashCode() {
            return (Long.hashCode(this.duration) * 31) + Long.hashCode(this.start);
        }

        public String toString() {
            long j = this.duration;
            return "Download(duration=" + j + ", start=" + this.start + ")";
        }

        public Download(long j, long j2) {
            this.duration = j;
            this.start = j2;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final long getStart() {
            return this.start;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(LogAttributes.DURATION, (Number) Long.valueOf(this.duration));
            jsonObject.addProperty("start", (Number) Long.valueOf(this.start));
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Download$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Download;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Download fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    return new Download(asJsonObject.get(LogAttributes.DURATION).getAsLong(), asJsonObject.get("start").getAsLong());
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0006\u0010\u0016\u001a\u00020\u0017J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Provider;", "", "domain", "", "name", "type", "Lcom/datadog/android/rum/model/ResourceEvent$ProviderType;", "(Ljava/lang/String;Ljava/lang/String;Lcom/datadog/android/rum/model/ResourceEvent$ProviderType;)V", "getDomain", "()Ljava/lang/String;", "getName", "getType", "()Lcom/datadog/android/rum/model/ResourceEvent$ProviderType;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toJson", "Lcom/google/gson/JsonElement;", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public static final class Provider {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String domain;
        private final String name;
        private final ProviderType type;

        public Provider() {
            this((String) null, (String) null, (ProviderType) null, 7, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Provider copy$default(Provider provider, String str, String str2, ProviderType providerType, int i, Object obj) {
            if ((i & 1) != 0) {
                str = provider.domain;
            }
            if ((i & 2) != 0) {
                str2 = provider.name;
            }
            if ((i & 4) != 0) {
                providerType = provider.type;
            }
            return provider.copy(str, str2, providerType);
        }

        @JvmStatic
        public static final Provider fromJson(String str) throws JsonParseException {
            return Companion.fromJson(str);
        }

        public final String component1() {
            return this.domain;
        }

        public final String component2() {
            return this.name;
        }

        public final ProviderType component3() {
            return this.type;
        }

        public final Provider copy(String str, String str2, ProviderType providerType) {
            return new Provider(str, str2, providerType);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Provider)) {
                return false;
            }
            Provider provider = (Provider) obj;
            return Intrinsics.areEqual((Object) this.domain, (Object) provider.domain) && Intrinsics.areEqual((Object) this.name, (Object) provider.name) && this.type == provider.type;
        }

        public int hashCode() {
            String str = this.domain;
            int i = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.name;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            ProviderType providerType = this.type;
            if (providerType != null) {
                i = providerType.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            String str = this.domain;
            String str2 = this.name;
            return "Provider(domain=" + str + ", name=" + str2 + ", type=" + this.type + ")";
        }

        public Provider(String str, String str2, ProviderType providerType) {
            this.domain = str;
            this.name = str2;
            this.type = providerType;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Provider(String str, String str2, ProviderType providerType, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : providerType);
        }

        public final String getDomain() {
            return this.domain;
        }

        public final String getName() {
            return this.name;
        }

        public final ProviderType getType() {
            return this.type;
        }

        public final JsonElement toJson() {
            JsonObject jsonObject = new JsonObject();
            String str = this.domain;
            if (str != null) {
                jsonObject.addProperty("domain", str);
            }
            String str2 = this.name;
            if (str2 != null) {
                jsonObject.addProperty("name", str2);
            }
            ProviderType providerType = this.type;
            if (providerType != null) {
                jsonObject.add(RumEventDeserializer.EVENT_TYPE_KEY_NAME, providerType.toJson());
            }
            return jsonObject;
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Provider$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Provider;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Provider fromJson(String str) throws JsonParseException {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                try {
                    JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
                    JsonElement jsonElement = asJsonObject.get("domain");
                    ProviderType providerType = null;
                    String asString = jsonElement == null ? null : jsonElement.getAsString();
                    JsonElement jsonElement2 = asJsonObject.get("name");
                    String asString2 = jsonElement2 == null ? null : jsonElement2.getAsString();
                    JsonElement jsonElement3 = asJsonObject.get(RumEventDeserializer.EVENT_TYPE_KEY_NAME);
                    if (jsonElement3 != null) {
                        String asString3 = jsonElement3.getAsString();
                        if (asString3 != null) {
                            providerType = ProviderType.Companion.fromJson(asString3);
                        }
                    }
                    return new Provider(asString, asString2, providerType);
                } catch (IllegalStateException e) {
                    throw new JsonParseException(e.getMessage());
                } catch (NumberFormatException e2) {
                    throw new JsonParseException(e2.getMessage());
                }
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Source;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "ANDROID", "IOS", "BROWSER", "FLUTTER", "REACT_NATIVE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Source$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Source;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSessionType;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "USER", "SYNTHETICS", "CI_TEST", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public enum ResourceEventSessionType {
        USER("user"),
        SYNTHETICS("synthetics"),
        CI_TEST("ci_test");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final ResourceEventSessionType fromJson(String str) {
            return Companion.fromJson(str);
        }

        private ResourceEventSessionType(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSessionType$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$ResourceEventSessionType;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final ResourceEventSessionType fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                ResourceEventSessionType[] values = ResourceEventSessionType.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    ResourceEventSessionType resourceEventSessionType = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) resourceEventSessionType.jsonValue, (Object) str)) {
                        return resourceEventSessionType;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Status;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "CONNECTED", "NOT_CONNECTED", "MAYBE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Status$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Status;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Interface;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "BLUETOOTH", "CELLULAR", "ETHERNET", "WIFI", "WIMAX", "MIXED", "OTHER", "UNKNOWN", "NONE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Interface$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Interface;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$ResourceType;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "DOCUMENT", "XHR", "BEACON", "FETCH", "CSS", "JS", "IMAGE", "FONT", "MEDIA", "OTHER", "NATIVE", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public enum ResourceType {
        DOCUMENT("document"),
        XHR("xhr"),
        BEACON("beacon"),
        FETCH("fetch"),
        CSS("css"),
        JS("js"),
        IMAGE("image"),
        FONT("font"),
        MEDIA("media"),
        OTHER("other"),
        NATIVE("native");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final ResourceType fromJson(String str) {
            return Companion.fromJson(str);
        }

        private ResourceType(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$ResourceType$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$ResourceType;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final ResourceType fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                ResourceType[] values = ResourceType.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    ResourceType resourceType = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) resourceType.jsonValue, (Object) str)) {
                        return resourceType;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Method;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "POST", "GET", "HEAD", "PUT", "DELETE", "PATCH", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public enum Method {
        POST("POST"),
        GET("GET"),
        HEAD("HEAD"),
        PUT("PUT"),
        DELETE("DELETE"),
        PATCH("PATCH");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final Method fromJson(String str) {
            return Companion.fromJson(str);
        }

        private Method(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Method$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Method;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Method fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                Method[] values = Method.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    Method method = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) method.jsonValue, (Object) str)) {
                        return method;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Plan;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/Number;)V", "toJson", "Lcom/google/gson/JsonElement;", "PLAN_1", "PLAN_2", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
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

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$Plan$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$Plan;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
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

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0016"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$ProviderType;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toJson", "Lcom/google/gson/JsonElement;", "AD", "ADVERTISING", "ANALYTICS", "CDN", "CONTENT", "CUSTOMER_SUCCESS", "FIRST_PARTY", "HOSTING", "MARKETING", "OTHER", "SOCIAL", "TAG_MANAGER", "UTILITY", "VIDEO", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ResourceEvent.kt */
    public enum ProviderType {
        AD("ad"),
        ADVERTISING("advertising"),
        ANALYTICS("analytics"),
        CDN("cdn"),
        CONTENT("content"),
        CUSTOMER_SUCCESS("customer-success"),
        FIRST_PARTY("first party"),
        HOSTING("hosting"),
        MARKETING("marketing"),
        OTHER("other"),
        SOCIAL(NotificationCompat.CATEGORY_SOCIAL),
        TAG_MANAGER("tag-manager"),
        UTILITY("utility"),
        VIDEO("video");
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final String jsonValue;

        @JvmStatic
        public static final ProviderType fromJson(String str) {
            return Companion.fromJson(str);
        }

        private ProviderType(String str) {
            this.jsonValue = str;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public final JsonElement toJson() {
            return new JsonPrimitive(this.jsonValue);
        }

        @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/model/ResourceEvent$ProviderType$Companion;", "", "()V", "fromJson", "Lcom/datadog/android/rum/model/ResourceEvent$ProviderType;", "serializedObject", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
        /* compiled from: ResourceEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final ProviderType fromJson(String str) {
                Intrinsics.checkNotNullParameter(str, "serializedObject");
                ProviderType[] values = ProviderType.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    ProviderType providerType = values[i];
                    i++;
                    if (Intrinsics.areEqual((Object) providerType.jsonValue, (Object) str)) {
                        return providerType;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }
}

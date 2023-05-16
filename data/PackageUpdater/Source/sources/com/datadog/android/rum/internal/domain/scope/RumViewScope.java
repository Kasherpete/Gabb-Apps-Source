package com.datadog.android.rum.internal.domain.scope;

import android.app.Activity;
import android.view.Display;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.core.internal.net.FirstPartyHostDetector;
import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.core.internal.system.BuildSdkVersionProvider;
import com.datadog.android.core.internal.time.TimeProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.core.internal.utils.ViewUtilsKt;
import com.datadog.android.core.model.NetworkInfo;
import com.datadog.android.core.model.UserInfo;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.internal.domain.Time;
import com.datadog.android.rum.internal.domain.event.RumEventDeserializer;
import com.datadog.android.rum.internal.domain.event.RumEventSourceProvider;
import com.datadog.android.rum.internal.domain.scope.RumRawEvent;
import com.datadog.android.rum.internal.vitals.VitalInfo;
import com.datadog.android.rum.internal.vitals.VitalListener;
import com.datadog.android.rum.internal.vitals.VitalMonitor;
import com.datadog.android.rum.model.ActionEvent;
import com.datadog.android.rum.model.LongTaskEvent;
import com.datadog.android.rum.model.ViewEvent;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000¤\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0010\u0018\u0000 ®\u00012\u00020\u0001:\u0004®\u0001¯\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00040\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ,\u0010q\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00040$2\u0014\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00040\nH\u0002J\u001e\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020u2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\u001e\u0010x\u001a\u00020s2\u0006\u0010t\u001a\u00020u2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\u001e\u0010y\u001a\u00020s2\u0006\u0010t\u001a\u00020u2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\u0010\u0010z\u001a\u00020s2\u0006\u0010\u0003\u001a\u00020\u0004H\u0003J\b\u0010{\u001a\u00020|H\u0016J\u0010\u0010}\u001a\u00020\u001d2\u0006\u0010t\u001a\u00020~H\u0002J \u0010\u001a\u0004\u0018\u00010\u00012\u0006\u0010t\u001a\u00020u2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0016J\t\u0010\u0001\u001a\u00020bH\u0016J\t\u0010\u0001\u001a\u00020bH\u0002J\u0012\u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u0001H\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\u001f\u0010\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020~2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\u0012\u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u0001H\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\u0012\u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u0001H\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\u0012\u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u0001H\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030 \u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010¡\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030¢\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J \u0010£\u0001\u001a\u00020s2\u0007\u0010t\u001a\u00030¤\u00012\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\f\u0010¥\u0001\u001a\u0005\u0018\u00010¦\u0001H\u0002J\u001c\u0010§\u0001\u001a\u0004\u0018\u00010b2\t\u0010¨\u0001\u001a\u0004\u0018\u00010@H\u0002¢\u0006\u0003\u0010©\u0001J\u0011\u0010ª\u0001\u001a\u00020\u001d2\u0006\u0010t\u001a\u00020uH\u0002J\u001f\u0010«\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020u2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00040wH\u0002J\u0014\u0010¬\u0001\u001a\u00020s2\t\u0010­\u0001\u001a\u0004\u0018\u00010\u0001H\u0002R\u000e\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R \u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010$X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\"\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00040$X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u0004\n\u0002\u0010+R\u000e\u0010,\u001a\u00020-X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u000e\u00100\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001d0$X\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u0014\u00103\u001a\u00020\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u000e\u00108\u001a\u00020-X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010/R\u000e\u0010:\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00040<X\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010@X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010B\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0004\n\u0002\u0010CR\u0010\u0010D\u001a\u0004\u0018\u00010EX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020-X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010/R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010K\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u00105\"\u0004\bM\u0010NR\u001a\u0010O\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u00105\"\u0004\bQ\u0010NR\u001a\u0010R\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u00105\"\u0004\bT\u0010NR\u001a\u0010U\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u00105\"\u0004\bW\u0010NR\u001a\u0010X\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u00105\"\u0004\bZ\u0010NR\u000e\u0010[\u001a\u00020*X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010]\u001a\u00020\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u00105R\u000e\u0010_\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010a\u001a\u00020bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u0014\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\bg\u0010hR\u0014\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010jR\u0014\u0010k\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010JR\u000e\u0010m\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010o\u001a\u00020\u00062\u0006\u0010n\u001a\u00020\u0006@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\bp\u0010JR\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000¨\u0006°\u0001"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumViewScope;", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "parentScope", "key", "", "name", "", "eventTime", "Lcom/datadog/android/rum/internal/domain/Time;", "initialAttributes", "", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "cpuVitalMonitor", "Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "memoryVitalMonitor", "frameRateVitalMonitor", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "rumEventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "buildSdkVersionProvider", "Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;", "viewUpdatePredicate", "Lcom/datadog/android/rum/internal/domain/scope/ViewUpdatePredicate;", "type", "Lcom/datadog/android/rum/internal/domain/scope/RumViewScope$RumViewType;", "(Lcom/datadog/android/rum/internal/domain/scope/RumScope;Ljava/lang/Object;Ljava/lang/String;Lcom/datadog/android/rum/internal/domain/Time;Ljava/util/Map;Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/rum/internal/vitals/VitalMonitor;Lcom/datadog/android/core/internal/time/TimeProvider;Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;Lcom/datadog/android/core/internal/system/BuildSdkVersionProvider;Lcom/datadog/android/rum/internal/domain/scope/ViewUpdatePredicate;Lcom/datadog/android/rum/internal/domain/scope/RumViewScope$RumViewType;)V", "actionCount", "", "activeActionScope", "getActiveActionScope$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "setActiveActionScope$dd_sdk_android_release", "(Lcom/datadog/android/rum/internal/domain/scope/RumScope;)V", "activeResourceScopes", "", "getActiveResourceScopes$dd_sdk_android_release", "()Ljava/util/Map;", "attributes", "getAttributes$dd_sdk_android_release", "cpuTicks", "", "Ljava/lang/Double;", "cpuVitalListener", "Lcom/datadog/android/rum/internal/vitals/VitalListener;", "getCpuVitalMonitor$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "crashCount", "customTimings", "errorCount", "eventTimestamp", "getEventTimestamp$dd_sdk_android_release", "()J", "getFirstPartyHostDetector$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "frameRateVitalListener", "getFrameRateVitalMonitor$dd_sdk_android_release", "frozenFrameCount", "keyRef", "Ljava/lang/ref/Reference;", "getKeyRef$dd_sdk_android_release", "()Ljava/lang/ref/Reference;", "lastFrameRateInfo", "Lcom/datadog/android/rum/internal/vitals/VitalInfo;", "lastMemoryInfo", "loadingTime", "Ljava/lang/Long;", "loadingType", "Lcom/datadog/android/rum/model/ViewEvent$LoadingType;", "longTaskCount", "memoryVitalListener", "getMemoryVitalMonitor$dd_sdk_android_release", "getName$dd_sdk_android_release", "()Ljava/lang/String;", "pendingActionCount", "getPendingActionCount$dd_sdk_android_release", "setPendingActionCount$dd_sdk_android_release", "(J)V", "pendingErrorCount", "getPendingErrorCount$dd_sdk_android_release", "setPendingErrorCount$dd_sdk_android_release", "pendingFrozenFrameCount", "getPendingFrozenFrameCount$dd_sdk_android_release", "setPendingFrozenFrameCount$dd_sdk_android_release", "pendingLongTaskCount", "getPendingLongTaskCount$dd_sdk_android_release", "setPendingLongTaskCount$dd_sdk_android_release", "pendingResourceCount", "getPendingResourceCount$dd_sdk_android_release", "setPendingResourceCount$dd_sdk_android_release", "refreshRateScale", "resourceCount", "serverTimeOffsetInMs", "getServerTimeOffsetInMs$dd_sdk_android_release", "sessionId", "startedNanos", "stopped", "", "getStopped$dd_sdk_android_release", "()Z", "setStopped$dd_sdk_android_release", "(Z)V", "getTimeProvider$dd_sdk_android_release", "()Lcom/datadog/android/core/internal/time/TimeProvider;", "getType$dd_sdk_android_release", "()Lcom/datadog/android/rum/internal/domain/scope/RumViewScope$RumViewType;", "url", "getUrl$dd_sdk_android_release", "version", "<set-?>", "viewId", "getViewId$dd_sdk_android_release", "addExtraAttributes", "delegateEventToAction", "", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "delegateEventToChildren", "delegateEventToResources", "detectRefreshRateScale", "getRumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "getStartupTime", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ApplicationStarted;", "handleEvent", "isActive", "isViewComplete", "onActionDropped", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ActionDropped;", "onActionSent", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ActionSent;", "onAddCustomTiming", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddCustomTiming;", "onAddError", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddError;", "onAddLongTask", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$AddLongTask;", "onApplicationStarted", "onErrorDropped", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ErrorDropped;", "onErrorSent", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ErrorSent;", "onKeepAlive", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$KeepAlive;", "onLongTaskDropped", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$LongTaskDropped;", "onLongTaskSent", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$LongTaskSent;", "onResourceDropped", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ResourceDropped;", "onResourceSent", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$ResourceSent;", "onStartAction", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartAction;", "onStartResource", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartResource;", "onStartView", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartView;", "onStopView", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StopView;", "onUpdateViewLoadingTime", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$UpdateViewLoadingTime;", "resolveCustomTimings", "Lcom/datadog/android/rum/model/ViewEvent$CustomTimings;", "resolveRefreshRateInfo", "refreshRateInfo", "(Lcom/datadog/android/rum/internal/vitals/VitalInfo;)Ljava/lang/Boolean;", "resolveViewDuration", "sendViewUpdate", "updateActiveActionScope", "scope", "Companion", "RumViewType", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumViewScope.kt */
public class RumViewScope implements RumScope {
    public static final String ACTION_DROPPED_WARNING = "RUM Action (%s on %s) was dropped, because another action is still active for the same view";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final long FROZEN_FRAME_THRESHOLD_NS = TimeUnit.MILLISECONDS.toNanos(700);
    public static final String NEGATIVE_DURATION_WARNING_MESSAGE = "The computed duration for your view: %s was 0 or negative. In order to keep the view we forced it to 1ns.";
    /* access modifiers changed from: private */
    public static final long ONE_SECOND_NS = TimeUnit.SECONDS.toNanos(1);
    public static final String RUM_CONTEXT_UPDATE_IGNORED_AT_ACTION_UPDATE_MESSAGE = "Trying to update active action in the global RUM context, but the context doesn't reference this view.";
    public static final String RUM_CONTEXT_UPDATE_IGNORED_AT_STOP_VIEW_MESSAGE = "Trying to update global RUM context when StopView event arrived, but the context doesn't reference this view.";
    public static final int SLOW_RENDERED_THRESHOLD_FPS = 55;
    private long actionCount;
    private RumScope activeActionScope;
    private final Map<String, RumScope> activeResourceScopes;
    private final Map<String, Object> attributes;
    private final BuildSdkVersionProvider buildSdkVersionProvider;
    /* access modifiers changed from: private */
    public Double cpuTicks;
    private VitalListener cpuVitalListener;
    private final VitalMonitor cpuVitalMonitor;
    private long crashCount;
    private final Map<String, Long> customTimings;
    private long errorCount;
    private final long eventTimestamp;
    private final FirstPartyHostDetector firstPartyHostDetector;
    private VitalListener frameRateVitalListener;
    private final VitalMonitor frameRateVitalMonitor;
    private long frozenFrameCount;
    private final Reference<Object> keyRef;
    /* access modifiers changed from: private */
    public VitalInfo lastFrameRateInfo;
    /* access modifiers changed from: private */
    public VitalInfo lastMemoryInfo;
    private Long loadingTime;
    private ViewEvent.LoadingType loadingType;
    private long longTaskCount;
    private VitalListener memoryVitalListener;
    private final VitalMonitor memoryVitalMonitor;
    private final String name;
    private final RumScope parentScope;
    private long pendingActionCount;
    private long pendingErrorCount;
    private long pendingFrozenFrameCount;
    private long pendingLongTaskCount;
    private long pendingResourceCount;
    private double refreshRateScale;
    private long resourceCount;
    private final RumEventSourceProvider rumEventSourceProvider;
    private final long serverTimeOffsetInMs;
    /* access modifiers changed from: private */
    public String sessionId;
    private final long startedNanos;
    private boolean stopped;
    private final TimeProvider timeProvider;
    private final RumViewType type;
    private final String url;
    private long version;
    private String viewId;
    private final ViewUpdatePredicate viewUpdatePredicate;

    @Metadata(mo20734d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumViewScope$RumViewType;", "", "(Ljava/lang/String;I)V", "NONE", "FOREGROUND", "BACKGROUND", "APPLICATION_LAUNCH", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumViewScope.kt */
    public enum RumViewType {
        NONE,
        FOREGROUND,
        BACKGROUND,
        APPLICATION_LAUNCH
    }

    public RumViewScope(RumScope rumScope, Object obj, String str, Time time, Map<String, ? extends Object> map, FirstPartyHostDetector firstPartyHostDetector2, VitalMonitor vitalMonitor, VitalMonitor vitalMonitor2, VitalMonitor vitalMonitor3, TimeProvider timeProvider2, RumEventSourceProvider rumEventSourceProvider2, BuildSdkVersionProvider buildSdkVersionProvider2, ViewUpdatePredicate viewUpdatePredicate2, RumViewType rumViewType) {
        RumScope rumScope2 = rumScope;
        Object obj2 = obj;
        String str2 = str;
        FirstPartyHostDetector firstPartyHostDetector3 = firstPartyHostDetector2;
        VitalMonitor vitalMonitor4 = vitalMonitor;
        VitalMonitor vitalMonitor5 = vitalMonitor2;
        VitalMonitor vitalMonitor6 = vitalMonitor3;
        TimeProvider timeProvider3 = timeProvider2;
        RumEventSourceProvider rumEventSourceProvider3 = rumEventSourceProvider2;
        BuildSdkVersionProvider buildSdkVersionProvider3 = buildSdkVersionProvider2;
        ViewUpdatePredicate viewUpdatePredicate3 = viewUpdatePredicate2;
        RumViewType rumViewType2 = rumViewType;
        Intrinsics.checkNotNullParameter(rumScope2, "parentScope");
        Intrinsics.checkNotNullParameter(obj2, "key");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(time, "eventTime");
        Intrinsics.checkNotNullParameter(map, "initialAttributes");
        Intrinsics.checkNotNullParameter(firstPartyHostDetector3, "firstPartyHostDetector");
        Intrinsics.checkNotNullParameter(vitalMonitor4, "cpuVitalMonitor");
        Intrinsics.checkNotNullParameter(vitalMonitor5, "memoryVitalMonitor");
        Intrinsics.checkNotNullParameter(vitalMonitor6, "frameRateVitalMonitor");
        Intrinsics.checkNotNullParameter(timeProvider3, "timeProvider");
        Intrinsics.checkNotNullParameter(rumEventSourceProvider3, "rumEventSourceProvider");
        Intrinsics.checkNotNullParameter(buildSdkVersionProvider3, "buildSdkVersionProvider");
        Intrinsics.checkNotNullParameter(viewUpdatePredicate3, "viewUpdatePredicate");
        Intrinsics.checkNotNullParameter(rumViewType2, RumEventDeserializer.EVENT_TYPE_KEY_NAME);
        this.parentScope = rumScope2;
        this.name = str2;
        this.firstPartyHostDetector = firstPartyHostDetector3;
        this.cpuVitalMonitor = vitalMonitor4;
        this.memoryVitalMonitor = vitalMonitor5;
        this.frameRateVitalMonitor = vitalMonitor6;
        this.timeProvider = timeProvider3;
        this.rumEventSourceProvider = rumEventSourceProvider3;
        this.buildSdkVersionProvider = buildSdkVersionProvider3;
        this.viewUpdatePredicate = viewUpdatePredicate3;
        this.type = rumViewType2;
        this.url = StringsKt.replace$default(ViewUtilsKt.resolveViewUrl(obj), '.', '/', false, 4, (Object) null);
        this.keyRef = new WeakReference(obj2);
        Map<String, Object> mutableMap = MapsKt.toMutableMap(map);
        mutableMap.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
        this.attributes = mutableMap;
        this.sessionId = rumScope.getRumContext().getSessionId();
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        this.viewId = uuid;
        this.startedNanos = time.getNanoTime();
        long serverOffsetMillis = timeProvider2.getServerOffsetMillis();
        this.serverTimeOffsetInMs = serverOffsetMillis;
        this.eventTimestamp = time.getTimestamp() + serverOffsetMillis;
        this.activeResourceScopes = new LinkedHashMap();
        this.version = 1;
        this.customTimings = new LinkedHashMap();
        this.cpuVitalListener = new RumViewScope$cpuVitalListener$1(this);
        this.memoryVitalListener = new RumViewScope$memoryVitalListener$1(this);
        this.refreshRateScale = 1.0d;
        this.frameRateVitalListener = new RumViewScope$frameRateVitalListener$1(this);
        GlobalRum.updateRumContext$dd_sdk_android_release$default(GlobalRum.INSTANCE, getRumContext(), (Function1) null, 2, (Object) null);
        mutableMap.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
        vitalMonitor4.register(this.cpuVitalListener);
        vitalMonitor5.register(this.memoryVitalListener);
        vitalMonitor6.register(this.frameRateVitalListener);
        detectRefreshRateScale(obj2);
    }

    public final String getName$dd_sdk_android_release() {
        return this.name;
    }

    public final FirstPartyHostDetector getFirstPartyHostDetector$dd_sdk_android_release() {
        return this.firstPartyHostDetector;
    }

    public final VitalMonitor getCpuVitalMonitor$dd_sdk_android_release() {
        return this.cpuVitalMonitor;
    }

    public final VitalMonitor getMemoryVitalMonitor$dd_sdk_android_release() {
        return this.memoryVitalMonitor;
    }

    public final VitalMonitor getFrameRateVitalMonitor$dd_sdk_android_release() {
        return this.frameRateVitalMonitor;
    }

    public final TimeProvider getTimeProvider$dd_sdk_android_release() {
        return this.timeProvider;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RumViewScope(com.datadog.android.rum.internal.domain.scope.RumScope r18, java.lang.Object r19, java.lang.String r20, com.datadog.android.rum.internal.domain.Time r21, java.util.Map r22, com.datadog.android.core.internal.net.FirstPartyHostDetector r23, com.datadog.android.rum.internal.vitals.VitalMonitor r24, com.datadog.android.rum.internal.vitals.VitalMonitor r25, com.datadog.android.rum.internal.vitals.VitalMonitor r26, com.datadog.android.core.internal.time.TimeProvider r27, com.datadog.android.rum.internal.domain.event.RumEventSourceProvider r28, com.datadog.android.core.internal.system.BuildSdkVersionProvider r29, com.datadog.android.rum.internal.domain.scope.ViewUpdatePredicate r30, com.datadog.android.rum.internal.domain.scope.RumViewScope.RumViewType r31, int r32, kotlin.jvm.internal.DefaultConstructorMarker r33) {
        /*
            r17 = this;
            r0 = r32
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x000f
            com.datadog.android.core.internal.system.DefaultBuildSdkVersionProvider r1 = new com.datadog.android.core.internal.system.DefaultBuildSdkVersionProvider
            r1.<init>()
            com.datadog.android.core.internal.system.BuildSdkVersionProvider r1 = (com.datadog.android.core.internal.system.BuildSdkVersionProvider) r1
            r14 = r1
            goto L_0x0011
        L_0x000f:
            r14 = r29
        L_0x0011:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x0022
            com.datadog.android.rum.internal.domain.scope.DefaultViewUpdatePredicate r1 = new com.datadog.android.rum.internal.domain.scope.DefaultViewUpdatePredicate
            r2 = 0
            r4 = 1
            r5 = 0
            r1.<init>(r2, r4, r5)
            com.datadog.android.rum.internal.domain.scope.ViewUpdatePredicate r1 = (com.datadog.android.rum.internal.domain.scope.ViewUpdatePredicate) r1
            r15 = r1
            goto L_0x0024
        L_0x0022:
            r15 = r30
        L_0x0024:
            r0 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r0 == 0) goto L_0x002d
            com.datadog.android.rum.internal.domain.scope.RumViewScope$RumViewType r0 = com.datadog.android.rum.internal.domain.scope.RumViewScope.RumViewType.FOREGROUND
            r16 = r0
            goto L_0x002f
        L_0x002d:
            r16 = r31
        L_0x002f:
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            r8 = r23
            r9 = r24
            r10 = r25
            r11 = r26
            r12 = r27
            r13 = r28
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.domain.scope.RumViewScope.<init>(com.datadog.android.rum.internal.domain.scope.RumScope, java.lang.Object, java.lang.String, com.datadog.android.rum.internal.domain.Time, java.util.Map, com.datadog.android.core.internal.net.FirstPartyHostDetector, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.rum.internal.vitals.VitalMonitor, com.datadog.android.core.internal.time.TimeProvider, com.datadog.android.rum.internal.domain.event.RumEventSourceProvider, com.datadog.android.core.internal.system.BuildSdkVersionProvider, com.datadog.android.rum.internal.domain.scope.ViewUpdatePredicate, com.datadog.android.rum.internal.domain.scope.RumViewScope$RumViewType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final RumViewType getType$dd_sdk_android_release() {
        return this.type;
    }

    public final String getUrl$dd_sdk_android_release() {
        return this.url;
    }

    public final Reference<Object> getKeyRef$dd_sdk_android_release() {
        return this.keyRef;
    }

    public final Map<String, Object> getAttributes$dd_sdk_android_release() {
        return this.attributes;
    }

    public final String getViewId$dd_sdk_android_release() {
        return this.viewId;
    }

    public final long getServerTimeOffsetInMs$dd_sdk_android_release() {
        return this.serverTimeOffsetInMs;
    }

    public final long getEventTimestamp$dd_sdk_android_release() {
        return this.eventTimestamp;
    }

    public final RumScope getActiveActionScope$dd_sdk_android_release() {
        return this.activeActionScope;
    }

    public final void setActiveActionScope$dd_sdk_android_release(RumScope rumScope) {
        this.activeActionScope = rumScope;
    }

    public final Map<String, RumScope> getActiveResourceScopes$dd_sdk_android_release() {
        return this.activeResourceScopes;
    }

    public final long getPendingResourceCount$dd_sdk_android_release() {
        return this.pendingResourceCount;
    }

    public final void setPendingResourceCount$dd_sdk_android_release(long j) {
        this.pendingResourceCount = j;
    }

    public final long getPendingActionCount$dd_sdk_android_release() {
        return this.pendingActionCount;
    }

    public final void setPendingActionCount$dd_sdk_android_release(long j) {
        this.pendingActionCount = j;
    }

    public final long getPendingErrorCount$dd_sdk_android_release() {
        return this.pendingErrorCount;
    }

    public final void setPendingErrorCount$dd_sdk_android_release(long j) {
        this.pendingErrorCount = j;
    }

    public final long getPendingLongTaskCount$dd_sdk_android_release() {
        return this.pendingLongTaskCount;
    }

    public final void setPendingLongTaskCount$dd_sdk_android_release(long j) {
        this.pendingLongTaskCount = j;
    }

    public final long getPendingFrozenFrameCount$dd_sdk_android_release() {
        return this.pendingFrozenFrameCount;
    }

    public final void setPendingFrozenFrameCount$dd_sdk_android_release(long j) {
        this.pendingFrozenFrameCount = j;
    }

    public final boolean getStopped$dd_sdk_android_release() {
        return this.stopped;
    }

    public final void setStopped$dd_sdk_android_release(boolean z) {
        this.stopped = z;
    }

    public RumScope handleEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        if (rumRawEvent instanceof RumRawEvent.ResourceSent) {
            onResourceSent((RumRawEvent.ResourceSent) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.ActionSent) {
            onActionSent((RumRawEvent.ActionSent) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.ErrorSent) {
            onErrorSent((RumRawEvent.ErrorSent) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.LongTaskSent) {
            onLongTaskSent((RumRawEvent.LongTaskSent) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.ResourceDropped) {
            onResourceDropped((RumRawEvent.ResourceDropped) rumRawEvent);
        } else if (rumRawEvent instanceof RumRawEvent.ActionDropped) {
            onActionDropped((RumRawEvent.ActionDropped) rumRawEvent);
        } else if (rumRawEvent instanceof RumRawEvent.ErrorDropped) {
            onErrorDropped((RumRawEvent.ErrorDropped) rumRawEvent);
        } else if (rumRawEvent instanceof RumRawEvent.LongTaskDropped) {
            onLongTaskDropped((RumRawEvent.LongTaskDropped) rumRawEvent);
        } else if (rumRawEvent instanceof RumRawEvent.StartView) {
            onStartView((RumRawEvent.StartView) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StopView) {
            onStopView((RumRawEvent.StopView) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StartAction) {
            onStartAction((RumRawEvent.StartAction) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.StartResource) {
            onStartResource((RumRawEvent.StartResource) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.AddError) {
            onAddError((RumRawEvent.AddError) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.AddLongTask) {
            onAddLongTask((RumRawEvent.AddLongTask) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.ApplicationStarted) {
            onApplicationStarted((RumRawEvent.ApplicationStarted) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.UpdateViewLoadingTime) {
            onUpdateViewLoadingTime((RumRawEvent.UpdateViewLoadingTime) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.AddCustomTiming) {
            onAddCustomTiming((RumRawEvent.AddCustomTiming) rumRawEvent, dataWriter);
        } else if (rumRawEvent instanceof RumRawEvent.KeepAlive) {
            onKeepAlive((RumRawEvent.KeepAlive) rumRawEvent, dataWriter);
        } else {
            delegateEventToChildren(rumRawEvent, dataWriter);
        }
        if (isViewComplete()) {
            return null;
        }
        return this;
    }

    public RumContext getRumContext() {
        RumContext rumContext = this.parentScope.getRumContext();
        if (!Intrinsics.areEqual((Object) rumContext.getSessionId(), (Object) this.sessionId)) {
            this.sessionId = rumContext.getSessionId();
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
            this.viewId = uuid;
        }
        String str = this.viewId;
        String str2 = this.name;
        String str3 = this.url;
        RumScope rumScope = this.activeActionScope;
        RumActionScope rumActionScope = rumScope instanceof RumActionScope ? (RumActionScope) rumScope : null;
        return RumContext.copy$default(rumContext, (String) null, (String) null, str, str2, str3, rumActionScope == null ? null : rumActionScope.getActionId$dd_sdk_android_release(), this.type, 3, (Object) null);
    }

    public boolean isActive() {
        return !this.stopped;
    }

    private final void onStartView(RumRawEvent.StartView startView, DataWriter<Object> dataWriter) {
        if (!this.stopped) {
            this.stopped = true;
            RumRawEvent rumRawEvent = startView;
            sendViewUpdate(rumRawEvent, dataWriter);
            delegateEventToChildren(rumRawEvent, dataWriter);
        }
    }

    private final void onStopView(RumRawEvent.StopView stopView, DataWriter<Object> dataWriter) {
        DataWriter<Object> dataWriter2 = dataWriter;
        RumRawEvent rumRawEvent = stopView;
        delegateEventToChildren(rumRawEvent, dataWriter2);
        Object obj = this.keyRef.get();
        if ((Intrinsics.areEqual(stopView.getKey(), obj) || obj == null) && !this.stopped) {
            GlobalRum.INSTANCE.updateRumContext$dd_sdk_android_release(RumContext.copy$default(getRumContext(), (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, RumViewType.NONE, 3, (Object) null), new RumViewScope$onStopView$1(this));
            this.attributes.putAll(stopView.getAttributes());
            this.stopped = true;
            sendViewUpdate(rumRawEvent, dataWriter2);
        }
    }

    private final void onStartAction(RumRawEvent.StartAction startAction, DataWriter<Object> dataWriter) {
        delegateEventToChildren(startAction, dataWriter);
        if (!this.stopped) {
            if (this.activeActionScope == null) {
                updateActiveActionScope(RumActionScope.Companion.fromEvent(this, startAction, this.serverTimeOffsetInMs, this.rumEventSourceProvider));
                this.pendingActionCount++;
            } else if (startAction.getType() != RumActionType.CUSTOM || startAction.getWaitForStop()) {
                Logger devLogger = RuntimeUtilsKt.getDevLogger();
                String format = String.format(Locale.US, ACTION_DROPPED_WARNING, Arrays.copyOf(new Object[]{startAction.getType(), startAction.getName()}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
                Logger.w$default(devLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
            } else {
                RumScope fromEvent = RumActionScope.Companion.fromEvent(this, startAction, this.serverTimeOffsetInMs, this.rumEventSourceProvider);
                this.pendingActionCount++;
                fromEvent.handleEvent(new RumRawEvent.SendCustomActionNow((Time) null, 1, (DefaultConstructorMarker) null), dataWriter);
            }
        }
    }

    private final void onStartResource(RumRawEvent.StartResource startResource, DataWriter<Object> dataWriter) {
        delegateEventToChildren(startResource, dataWriter);
        if (!this.stopped) {
            this.activeResourceScopes.put(startResource.getKey(), RumResourceScope.Companion.fromEvent(this, RumRawEvent.StartResource.copy$default(startResource, (String) null, (String) null, (String) null, addExtraAttributes(startResource.getAttributes()), (Time) null, 23, (Object) null), this.firstPartyHostDetector, this.serverTimeOffsetInMs, this.rumEventSourceProvider));
            this.pendingResourceCount++;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void onAddError(com.datadog.android.rum.internal.domain.scope.RumRawEvent.AddError r43, com.datadog.android.core.internal.persistence.DataWriter<java.lang.Object> r44) {
        /*
            r42 = this;
            r0 = r42
            r1 = r44
            r2 = r43
            com.datadog.android.rum.internal.domain.scope.RumRawEvent r2 = (com.datadog.android.rum.internal.domain.scope.RumRawEvent) r2
            r0.delegateEventToChildren(r2, r1)
            boolean r3 = r0.stopped
            if (r3 == 0) goto L_0x0010
            return
        L_0x0010:
            com.datadog.android.rum.internal.domain.RumContext r3 = r42.getRumContext()
            com.datadog.android.core.internal.CoreFeature r4 = com.datadog.android.core.internal.CoreFeature.INSTANCE
            com.datadog.android.log.internal.user.MutableUserInfoProvider r4 = r4.getUserInfoProvider$dd_sdk_android_release()
            com.datadog.android.core.model.UserInfo r4 = r4.getUserInfo()
            java.util.Map r5 = r43.getAttributes()
            java.util.Map r5 = r0.addExtraAttributes(r5)
            java.lang.String r6 = "_dd.error.is_crash"
            java.lang.Object r6 = r5.remove(r6)
            boolean r7 = r6 instanceof java.lang.Boolean
            r8 = 0
            if (r7 == 0) goto L_0x0034
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            goto L_0x0035
        L_0x0034:
            r6 = r8
        L_0x0035:
            com.datadog.android.core.internal.CoreFeature r7 = com.datadog.android.core.internal.CoreFeature.INSTANCE
            com.datadog.android.core.internal.net.info.NetworkInfoProvider r7 = r7.getNetworkInfoProvider$dd_sdk_android_release()
            com.datadog.android.core.model.NetworkInfo r7 = r7.getLatestNetworkInfo()
            java.lang.String r9 = r43.getType()
            if (r9 != 0) goto L_0x0059
            java.lang.Throwable r9 = r43.getThrowable()
            if (r9 != 0) goto L_0x004e
        L_0x004b:
            r16 = r8
            goto L_0x005b
        L_0x004e:
            java.lang.Class r9 = r9.getClass()
            if (r9 != 0) goto L_0x0055
            goto L_0x004b
        L_0x0055:
            java.lang.String r9 = r9.getCanonicalName()
        L_0x0059:
            r16 = r9
        L_0x005b:
            java.lang.Throwable r9 = r43.getThrowable()
            java.lang.String r23 = ""
            if (r9 != 0) goto L_0x0066
        L_0x0063:
            r9 = r23
            goto L_0x006d
        L_0x0066:
            java.lang.String r9 = r9.getMessage()
            if (r9 != 0) goto L_0x006d
            goto L_0x0063
        L_0x006d:
            r10 = r9
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            boolean r10 = kotlin.text.StringsKt.isBlank(r10)
            r11 = 1
            r10 = r10 ^ r11
            if (r10 == 0) goto L_0x009e
            java.lang.String r10 = r43.getMessage()
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r9)
            if (r10 != 0) goto L_0x009e
            java.lang.String r10 = r43.getMessage()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.StringBuilder r10 = r12.append(r10)
            java.lang.String r12 = ": "
            java.lang.StringBuilder r10 = r10.append(r12)
            java.lang.StringBuilder r9 = r10.append(r9)
            java.lang.String r9 = r9.toString()
            goto L_0x00a2
        L_0x009e:
            java.lang.String r9 = r43.getMessage()
        L_0x00a2:
            r12 = r9
            com.datadog.android.rum.internal.domain.Time r9 = r43.getEventTime()
            long r9 = r9.getTimestamp()
            long r13 = r0.serverTimeOffsetInMs
            long r25 = r9 + r13
            com.datadog.android.rum.model.ErrorEvent$Error r38 = new com.datadog.android.rum.model.ErrorEvent$Error
            r9 = 0
            com.datadog.android.rum.RumErrorSource r10 = r43.getSource()
            com.datadog.android.rum.model.ErrorEvent$ErrorSource r13 = com.datadog.android.rum.internal.domain.scope.RumEventExtKt.toSchemaSource(r10)
            java.lang.String r10 = r43.getStacktrace()
            if (r10 != 0) goto L_0x00cc
            java.lang.Throwable r10 = r43.getThrowable()
            if (r10 != 0) goto L_0x00c8
            r14 = r8
            goto L_0x00cd
        L_0x00c8:
            java.lang.String r10 = com.datadog.android.core.internal.utils.ThrowableExtKt.loggableStackTrace(r10)
        L_0x00cc:
            r14 = r10
        L_0x00cd:
            boolean r10 = r43.isFatal()
            r15 = 0
            if (r10 != 0) goto L_0x00e0
            if (r6 != 0) goto L_0x00d8
            r6 = r15
            goto L_0x00dc
        L_0x00d8:
            boolean r6 = r6.booleanValue()
        L_0x00dc:
            if (r6 == 0) goto L_0x00df
            goto L_0x00e0
        L_0x00df:
            r11 = r15
        L_0x00e0:
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r11)
            r17 = 0
            r18 = 0
            com.datadog.android.rum.internal.RumErrorSourceType r6 = r43.getSourceType()
            com.datadog.android.rum.model.ErrorEvent$SourceType r19 = com.datadog.android.rum.internal.domain.scope.RumEventExtKt.toSchemaSourceType(r6)
            r20 = 0
            r21 = 705(0x2c1, float:9.88E-43)
            r22 = 0
            r10 = r38
            r11 = r9
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            java.lang.String r6 = r3.getActionId()
            if (r6 != 0) goto L_0x0105
            r39 = r8
            goto L_0x010c
        L_0x0105:
            com.datadog.android.rum.model.ErrorEvent$Action r9 = new com.datadog.android.rum.model.ErrorEvent$Action
            r9.<init>(r6)
            r39 = r9
        L_0x010c:
            java.lang.String r6 = r3.getViewId()
            if (r6 != 0) goto L_0x0115
            r10 = r23
            goto L_0x0116
        L_0x0115:
            r10 = r6
        L_0x0116:
            java.lang.String r13 = r3.getViewName()
            java.lang.String r6 = r3.getViewUrl()
            if (r6 != 0) goto L_0x0123
            r12 = r23
            goto L_0x0124
        L_0x0123:
            r12 = r6
        L_0x0124:
            com.datadog.android.rum.model.ErrorEvent$View r9 = new com.datadog.android.rum.model.ErrorEvent$View
            r31 = r9
            r11 = 0
            r14 = 0
            r15 = 18
            r16 = 0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)
            com.datadog.android.rum.model.ErrorEvent$Usr r6 = new com.datadog.android.rum.model.ErrorEvent$Usr
            r32 = r6
            java.lang.String r9 = r4.getId()
            java.lang.String r10 = r4.getName()
            java.lang.String r11 = r4.getEmail()
            java.util.Map r4 = r4.getAdditionalProperties()
            r6.<init>(r9, r10, r11, r4)
            com.datadog.android.rum.model.ErrorEvent$Connectivity r33 = com.datadog.android.rum.internal.domain.scope.RumEventExtKt.toErrorConnectivity(r7)
            com.datadog.android.rum.model.ErrorEvent$Application r4 = new com.datadog.android.rum.model.ErrorEvent$Application
            r27 = r4
            java.lang.String r6 = r3.getApplicationId()
            r4.<init>(r6)
            com.datadog.android.rum.model.ErrorEvent$ErrorEventSession r9 = new com.datadog.android.rum.model.ErrorEvent$ErrorEventSession
            r29 = r9
            java.lang.String r10 = r3.getSessionId()
            com.datadog.android.rum.model.ErrorEvent$ErrorEventSessionType r11 = com.datadog.android.rum.model.ErrorEvent.ErrorEventSessionType.USER
            r12 = 0
            r13 = 4
            r9.<init>(r10, r11, r12, r13, r14)
            com.datadog.android.rum.internal.domain.event.RumEventSourceProvider r3 = r0.rumEventSourceProvider
            com.datadog.android.rum.model.ErrorEvent$ErrorEventSource r30 = r3.getErrorEventSource()
            com.datadog.android.rum.model.ErrorEvent$Context r3 = new com.datadog.android.rum.model.ErrorEvent$Context
            r37 = r3
            r3.<init>(r5)
            com.datadog.android.rum.model.ErrorEvent$Dd r3 = new com.datadog.android.rum.model.ErrorEvent$Dd
            r36 = r3
            com.datadog.android.rum.model.ErrorEvent$DdSession r4 = new com.datadog.android.rum.model.ErrorEvent$DdSession
            com.datadog.android.rum.model.ErrorEvent$Plan r5 = com.datadog.android.rum.model.ErrorEvent.Plan.PLAN_1
            r4.<init>(r5)
            r5 = 2
            r3.<init>(r4, r8, r5, r8)
            com.datadog.android.rum.model.ErrorEvent r3 = new com.datadog.android.rum.model.ErrorEvent
            r24 = r3
            r28 = 0
            r34 = 0
            r35 = 0
            r40 = 772(0x304, float:1.082E-42)
            r41 = 0
            r24.<init>(r25, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41)
            r1.write(r3)
            boolean r3 = r43.isFatal()
            r4 = 1
            if (r3 == 0) goto L_0x01ac
            long r6 = r0.errorCount
            long r6 = r6 + r4
            r0.errorCount = r6
            long r6 = r0.crashCount
            long r6 = r6 + r4
            r0.crashCount = r6
            r0.sendViewUpdate(r2, r1)
            goto L_0x01b1
        L_0x01ac:
            long r1 = r0.pendingErrorCount
            long r1 = r1 + r4
            r0.pendingErrorCount = r1
        L_0x01b1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.domain.scope.RumViewScope.onAddError(com.datadog.android.rum.internal.domain.scope.RumRawEvent$AddError, com.datadog.android.core.internal.persistence.DataWriter):void");
    }

    private final void onAddCustomTiming(RumRawEvent.AddCustomTiming addCustomTiming, DataWriter<Object> dataWriter) {
        this.customTimings.put(addCustomTiming.getName(), Long.valueOf(Math.max(addCustomTiming.getEventTime().getNanoTime() - this.startedNanos, 1)));
        sendViewUpdate(addCustomTiming, dataWriter);
    }

    private final void onKeepAlive(RumRawEvent.KeepAlive keepAlive, DataWriter<Object> dataWriter) {
        RumRawEvent rumRawEvent = keepAlive;
        delegateEventToChildren(rumRawEvent, dataWriter);
        if (!this.stopped) {
            sendViewUpdate(rumRawEvent, dataWriter);
        }
    }

    private final void delegateEventToChildren(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        delegateEventToResources(rumRawEvent, dataWriter);
        delegateEventToAction(rumRawEvent, dataWriter);
    }

    private final void delegateEventToAction(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        RumScope rumScope = this.activeActionScope;
        if (rumScope != null && rumScope.handleEvent(rumRawEvent, dataWriter) == null) {
            updateActiveActionScope((RumScope) null);
        }
    }

    private final void updateActiveActionScope(RumScope rumScope) {
        this.activeActionScope = rumScope;
        GlobalRum.INSTANCE.updateRumContext$dd_sdk_android_release(getRumContext(), new RumViewScope$updateActiveActionScope$1(this));
    }

    private final void delegateEventToResources(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        Iterator<Map.Entry<String, RumScope>> it = this.activeResourceScopes.entrySet().iterator();
        while (it.hasNext()) {
            if (((RumScope) it.next().getValue()).handleEvent(rumRawEvent, dataWriter) == null) {
                it.remove();
            }
        }
    }

    private final void onResourceSent(RumRawEvent.ResourceSent resourceSent, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual((Object) resourceSent.getViewId(), (Object) this.viewId)) {
            this.pendingResourceCount--;
            this.resourceCount++;
            sendViewUpdate(resourceSent, dataWriter);
        }
    }

    private final void onActionSent(RumRawEvent.ActionSent actionSent, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual((Object) actionSent.getViewId(), (Object) this.viewId)) {
            this.pendingActionCount--;
            this.actionCount++;
            sendViewUpdate(actionSent, dataWriter);
        }
    }

    private final void onErrorSent(RumRawEvent.ErrorSent errorSent, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual((Object) errorSent.getViewId(), (Object) this.viewId)) {
            this.pendingErrorCount--;
            this.errorCount++;
            sendViewUpdate(errorSent, dataWriter);
        }
    }

    private final void onLongTaskSent(RumRawEvent.LongTaskSent longTaskSent, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual((Object) longTaskSent.getViewId(), (Object) this.viewId)) {
            this.pendingLongTaskCount--;
            this.longTaskCount++;
            if (longTaskSent.isFrozenFrame()) {
                this.pendingFrozenFrameCount--;
                this.frozenFrameCount++;
            }
            sendViewUpdate(longTaskSent, dataWriter);
        }
    }

    private final void onResourceDropped(RumRawEvent.ResourceDropped resourceDropped) {
        if (Intrinsics.areEqual((Object) resourceDropped.getViewId(), (Object) this.viewId)) {
            this.pendingResourceCount--;
        }
    }

    private final void onActionDropped(RumRawEvent.ActionDropped actionDropped) {
        if (Intrinsics.areEqual((Object) actionDropped.getViewId(), (Object) this.viewId)) {
            this.pendingActionCount--;
        }
    }

    private final void onErrorDropped(RumRawEvent.ErrorDropped errorDropped) {
        if (Intrinsics.areEqual((Object) errorDropped.getViewId(), (Object) this.viewId)) {
            this.pendingErrorCount--;
        }
    }

    private final void onLongTaskDropped(RumRawEvent.LongTaskDropped longTaskDropped) {
        if (Intrinsics.areEqual((Object) longTaskDropped.getViewId(), (Object) this.viewId)) {
            this.pendingLongTaskCount--;
            if (longTaskDropped.isFrozenFrame()) {
                this.pendingFrozenFrameCount--;
            }
        }
    }

    private final void sendViewUpdate(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        String str;
        String str2;
        Double d;
        Double d2;
        Double d3;
        Double d4;
        ViewEvent.LongTask longTask;
        ViewEvent.FrozenFrame frozenFrame;
        Double d5;
        UserInfo userInfo;
        RumContext rumContext;
        Double d6;
        boolean isViewComplete = isViewComplete();
        if (this.viewUpdatePredicate.canUpdateView(isViewComplete, rumRawEvent)) {
            this.attributes.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
            this.version++;
            long resolveViewDuration = resolveViewDuration(rumRawEvent);
            RumContext rumContext2 = getRumContext();
            UserInfo userInfo2 = CoreFeature.INSTANCE.getUserInfoProvider$dd_sdk_android_release().getUserInfo();
            ViewEvent.CustomTimings resolveCustomTimings = resolveCustomTimings();
            VitalInfo vitalInfo = this.lastMemoryInfo;
            VitalInfo vitalInfo2 = this.lastFrameRateInfo;
            Boolean resolveRefreshRateInfo = resolveRefreshRateInfo(vitalInfo2);
            long j = this.eventTimestamp;
            String viewId2 = rumContext2.getViewId();
            String str3 = viewId2 == null ? "" : viewId2;
            String viewName = rumContext2.getViewName();
            if (viewName == null) {
                str = "";
            } else {
                str = viewName;
            }
            String viewUrl = rumContext2.getViewUrl();
            if (viewUrl == null) {
                str2 = "";
            } else {
                str2 = viewUrl;
            }
            Long l = this.loadingTime;
            ViewEvent.LoadingType loadingType2 = this.loadingType;
            Long l2 = l;
            ViewEvent.Action action = new ViewEvent.Action(this.actionCount);
            long j2 = j;
            ViewEvent.Resource resource = new ViewEvent.Resource(this.resourceCount);
            ViewEvent.Error error = new ViewEvent.Error(this.errorCount);
            ViewEvent.Crash crash = new ViewEvent.Crash(this.crashCount);
            ViewEvent.Error error2 = error;
            ViewEvent.LongTask longTask2 = new ViewEvent.LongTask(this.longTaskCount);
            ViewEvent.FrozenFrame frozenFrame2 = new ViewEvent.FrozenFrame(this.frozenFrameCount);
            boolean z = !isViewComplete;
            Double d7 = this.cpuTicks;
            if (d7 == null) {
                d = d7;
                d2 = null;
            } else {
                d = d7;
                d2 = Double.valueOf((d7.doubleValue() * ((double) ONE_SECOND_NS)) / ((double) resolveViewDuration));
            }
            if (vitalInfo == null) {
                d3 = null;
            } else {
                d3 = Double.valueOf(vitalInfo.getMeanValue());
            }
            if (vitalInfo == null) {
                d4 = null;
            } else {
                d4 = Double.valueOf(vitalInfo.getMaxValue());
            }
            if (vitalInfo2 == null) {
                longTask = longTask2;
                frozenFrame = frozenFrame2;
                d5 = null;
            } else {
                longTask = longTask2;
                frozenFrame = frozenFrame2;
                d5 = Double.valueOf(vitalInfo2.getMeanValue() * this.refreshRateScale);
            }
            if (vitalInfo2 == null) {
                rumContext = rumContext2;
                userInfo = userInfo2;
                d6 = null;
            } else {
                rumContext = rumContext2;
                userInfo = userInfo2;
                d6 = Double.valueOf(vitalInfo2.getMinValue() * this.refreshRateScale);
            }
            ViewEvent.View view = new ViewEvent.View(str3, (String) null, str2, str, l2, loadingType2, resolveViewDuration, (Long) null, (Long) null, (Long) null, (Long) null, (Number) null, (Long) null, (Long) null, (Long) null, (Long) null, resolveCustomTimings, Boolean.valueOf(z), resolveRefreshRateInfo, action, error2, crash, longTask, frozenFrame, resource, (List) null, d3, d4, d, d2, d5, d6, 33619842, (DefaultConstructorMarker) null);
            ViewEvent.Usr usr = new ViewEvent.Usr(userInfo.getId(), userInfo.getName(), userInfo.getEmail(), userInfo.getAdditionalProperties());
            DataWriter<Object> dataWriter2 = dataWriter;
            dataWriter2.write(new ViewEvent(j2, new ViewEvent.Application(rumContext.getApplicationId()), (String) null, new ViewEvent.ViewEventSession(rumContext.getSessionId(), ViewEvent.Type.USER, (Boolean) null, 4, (DefaultConstructorMarker) null), this.rumEventSourceProvider.getViewEventSource(), view, usr, (ViewEvent.Connectivity) null, (ViewEvent.Synthetics) null, (ViewEvent.CiTest) null, new ViewEvent.C0867Dd(new ViewEvent.DdSession(ViewEvent.Plan.PLAN_1), (String) null, this.version, 2, (DefaultConstructorMarker) null), new ViewEvent.Context(this.attributes), 900, (DefaultConstructorMarker) null));
        }
    }

    private final long resolveViewDuration(RumRawEvent rumRawEvent) {
        long nanoTime = rumRawEvent.getEventTime().getNanoTime() - this.startedNanos;
        if (nanoTime > 0) {
            return nanoTime;
        }
        Logger devLogger = RuntimeUtilsKt.getDevLogger();
        String format = String.format(Locale.US, NEGATIVE_DURATION_WARNING_MESSAGE, Arrays.copyOf(new Object[]{this.name}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
        Logger.w$default(devLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
        return 1;
    }

    private final Boolean resolveRefreshRateInfo(VitalInfo vitalInfo) {
        if (vitalInfo == null) {
            return null;
        }
        return Boolean.valueOf(vitalInfo.getMeanValue() < 55.0d);
    }

    private final ViewEvent.CustomTimings resolveCustomTimings() {
        if (!this.customTimings.isEmpty()) {
            return new ViewEvent.CustomTimings(new LinkedHashMap(this.customTimings));
        }
        return null;
    }

    private final Map<String, Object> addExtraAttributes(Map<String, ? extends Object> map) {
        Map<String, Object> mutableMap = MapsKt.toMutableMap(map);
        mutableMap.putAll(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
        return mutableMap;
    }

    private final void onUpdateViewLoadingTime(RumRawEvent.UpdateViewLoadingTime updateViewLoadingTime, DataWriter<Object> dataWriter) {
        if (Intrinsics.areEqual(updateViewLoadingTime.getKey(), this.keyRef.get())) {
            this.loadingTime = Long.valueOf(updateViewLoadingTime.getLoadingTime());
            this.loadingType = updateViewLoadingTime.getLoadingType();
            sendViewUpdate(updateViewLoadingTime, dataWriter);
        }
    }

    private final void onApplicationStarted(RumRawEvent.ApplicationStarted applicationStarted, DataWriter<Object> dataWriter) {
        String str;
        this.pendingActionCount++;
        RumContext rumContext = getRumContext();
        UserInfo userInfo = CoreFeature.INSTANCE.getUserInfoProvider$dd_sdk_android_release().getUserInfo();
        long j = this.eventTimestamp;
        ActionEvent.Action action = new ActionEvent.Action(ActionEvent.ActionType.APPLICATION_START, UUID.randomUUID().toString(), Long.valueOf(getStartupTime(applicationStarted)), (ActionEvent.Target) null, (ActionEvent.Error) null, (ActionEvent.Crash) null, (ActionEvent.LongTask) null, (ActionEvent.Resource) null, 248, (DefaultConstructorMarker) null);
        String viewId2 = rumContext.getViewId();
        String str2 = viewId2 == null ? "" : viewId2;
        String viewName = rumContext.getViewName();
        String viewUrl = rumContext.getViewUrl();
        if (viewUrl == null) {
            str = "";
        } else {
            str = viewUrl;
        }
        ActionEvent.View view = r18;
        ActionEvent.View view2 = new ActionEvent.View(str2, (String) null, str, viewName, (Boolean) null, 18, (DefaultConstructorMarker) null);
        ActionEvent.Usr usr = r3;
        ActionEvent.Usr usr2 = new ActionEvent.Usr(userInfo.getId(), userInfo.getName(), userInfo.getEmail(), userInfo.getAdditionalProperties());
        ActionEvent.Application application = r2;
        ActionEvent.Application application2 = new ActionEvent.Application(rumContext.getApplicationId());
        ActionEvent.ActionEventSession actionEventSession = r18;
        ActionEvent.ActionEventSession actionEventSession2 = new ActionEvent.ActionEventSession(rumContext.getSessionId(), ActionEvent.ActionEventSessionType.USER, (Boolean) null, 4, (DefaultConstructorMarker) null);
        ActionEvent.Source actionEventSource = this.rumEventSourceProvider.getActionEventSource();
        ActionEvent.Context context = r0;
        ActionEvent.Context context2 = new ActionEvent.Context(GlobalRum.INSTANCE.getGlobalAttributes$dd_sdk_android_release());
        ActionEvent.C0863Dd dd = r0;
        ActionEvent.C0863Dd dd2 = new ActionEvent.C0863Dd(new ActionEvent.DdSession(ActionEvent.Plan.PLAN_1), (String) null, 2, (DefaultConstructorMarker) null);
        dataWriter.write(new ActionEvent(j, application, (String) null, actionEventSession, actionEventSource, view, usr, (ActionEvent.Connectivity) null, (ActionEvent.Synthetics) null, (ActionEvent.CiTest) null, dd, context, action, 900, (DefaultConstructorMarker) null));
    }

    private final long getStartupTime(RumRawEvent.ApplicationStarted applicationStarted) {
        return Math.max(applicationStarted.getEventTime().getNanoTime() - applicationStarted.getApplicationStartupNanos(), 1);
    }

    private final void onAddLongTask(RumRawEvent.AddLongTask addLongTask, DataWriter<Object> dataWriter) {
        String str;
        DataWriter<Object> dataWriter2 = dataWriter;
        delegateEventToChildren(addLongTask, dataWriter2);
        if (!this.stopped) {
            RumContext rumContext = getRumContext();
            UserInfo userInfo = CoreFeature.INSTANCE.getUserInfoProvider$dd_sdk_android_release().getUserInfo();
            Map<String, Object> addExtraAttributes = addExtraAttributes(MapsKt.mapOf(TuplesKt.m78to(RumAttributes.LONG_TASK_TARGET, addLongTask.getTarget())));
            NetworkInfo latestNetworkInfo = CoreFeature.INSTANCE.getNetworkInfoProvider$dd_sdk_android_release().getLatestNetworkInfo();
            long timestamp = addLongTask.getEventTime().getTimestamp() + this.serverTimeOffsetInMs;
            boolean z = addLongTask.getDurationNs() > FROZEN_FRAME_THRESHOLD_NS;
            long millis = timestamp - TimeUnit.NANOSECONDS.toMillis(addLongTask.getDurationNs());
            LongTaskEvent.LongTask longTask = new LongTaskEvent.LongTask((String) null, addLongTask.getDurationNs(), Boolean.valueOf(z), 1, (DefaultConstructorMarker) null);
            String actionId = rumContext.getActionId();
            LongTaskEvent.Action action = actionId == null ? null : new LongTaskEvent.Action(actionId);
            String viewId2 = rumContext.getViewId();
            String str2 = viewId2 == null ? "" : viewId2;
            String viewName = rumContext.getViewName();
            String viewUrl = rumContext.getViewUrl();
            if (viewUrl == null) {
                str = "";
            } else {
                str = viewUrl;
            }
            LongTaskEvent.View view = r27;
            LongTaskEvent.View view2 = new LongTaskEvent.View(str2, (String) null, str, viewName, 2, (DefaultConstructorMarker) null);
            LongTaskEvent.Usr usr = r6;
            LongTaskEvent.Usr usr2 = new LongTaskEvent.Usr(userInfo.getId(), userInfo.getName(), userInfo.getEmail(), userInfo.getAdditionalProperties());
            LongTaskEvent.Connectivity longTaskConnectivity = RumEventExtKt.toLongTaskConnectivity(latestNetworkInfo);
            LongTaskEvent.Application application = r3;
            LongTaskEvent.Application application2 = new LongTaskEvent.Application(rumContext.getApplicationId());
            LongTaskEvent.LongTaskEventSession longTaskEventSession = r27;
            LongTaskEvent.LongTaskEventSession longTaskEventSession2 = new LongTaskEvent.LongTaskEventSession(rumContext.getSessionId(), LongTaskEvent.Type.USER, (Boolean) null, 4, (DefaultConstructorMarker) null);
            LongTaskEvent.Source longTaskEventSource = this.rumEventSourceProvider.getLongTaskEventSource();
            LongTaskEvent.Context context = r2;
            LongTaskEvent.Context context2 = new LongTaskEvent.Context(addExtraAttributes);
            LongTaskEvent.C0865Dd dd = r2;
            LongTaskEvent.C0865Dd dd2 = new LongTaskEvent.C0865Dd(new LongTaskEvent.DdSession(LongTaskEvent.Plan.PLAN_1), (String) null, 2, (DefaultConstructorMarker) null);
            dataWriter2.write(new LongTaskEvent(millis, application, (String) null, longTaskEventSession, longTaskEventSource, view, usr, longTaskConnectivity, (LongTaskEvent.Synthetics) null, (LongTaskEvent.CiTest) null, dd, context, longTask, action, 772, (DefaultConstructorMarker) null));
            this.pendingLongTaskCount++;
            if (z) {
                this.pendingFrozenFrameCount++;
            }
        }
    }

    private final boolean isViewComplete() {
        return this.stopped && this.activeResourceScopes.isEmpty() && ((this.pendingActionCount + this.pendingResourceCount) + this.pendingErrorCount) + this.pendingLongTaskCount <= 0;
    }

    private final void detectRefreshRateScale(Object obj) {
        Activity activity;
        Display display = null;
        if (obj instanceof Activity) {
            activity = (Activity) obj;
        } else if (obj instanceof Fragment) {
            activity = ((Fragment) obj).getActivity();
        } else {
            activity = obj instanceof android.app.Fragment ? ((android.app.Fragment) obj).getActivity() : null;
        }
        if (activity != null) {
            if (this.buildSdkVersionProvider.version() >= 30) {
                display = activity.getDisplay();
            } else {
                Object systemService = activity.getSystemService("window");
                WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
                if (windowManager != null) {
                    display = windowManager.getDefaultDisplay();
                }
            }
            if (display != null) {
                this.refreshRateScale = 60.0d / ((double) display.getRefreshRate());
            }
        }
    }

    @Metadata(mo20734d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JM\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0000¢\u0006\u0002\b R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000¨\u0006!"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumViewScope$Companion;", "", "()V", "ACTION_DROPPED_WARNING", "", "FROZEN_FRAME_THRESHOLD_NS", "", "getFROZEN_FRAME_THRESHOLD_NS$dd_sdk_android_release", "()J", "NEGATIVE_DURATION_WARNING_MESSAGE", "ONE_SECOND_NS", "getONE_SECOND_NS$dd_sdk_android_release", "RUM_CONTEXT_UPDATE_IGNORED_AT_ACTION_UPDATE_MESSAGE", "RUM_CONTEXT_UPDATE_IGNORED_AT_STOP_VIEW_MESSAGE", "SLOW_RENDERED_THRESHOLD_FPS", "", "fromEvent", "Lcom/datadog/android/rum/internal/domain/scope/RumViewScope;", "parentScope", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent$StartView;", "firstPartyHostDetector", "Lcom/datadog/android/core/internal/net/FirstPartyHostDetector;", "cpuVitalMonitor", "Lcom/datadog/android/rum/internal/vitals/VitalMonitor;", "memoryVitalMonitor", "frameRateVitalMonitor", "timeProvider", "Lcom/datadog/android/core/internal/time/TimeProvider;", "rumEventSourceProvider", "Lcom/datadog/android/rum/internal/domain/event/RumEventSourceProvider;", "fromEvent$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumViewScope.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long getONE_SECOND_NS$dd_sdk_android_release() {
            return RumViewScope.ONE_SECOND_NS;
        }

        public final long getFROZEN_FRAME_THRESHOLD_NS$dd_sdk_android_release() {
            return RumViewScope.FROZEN_FRAME_THRESHOLD_NS;
        }

        public final RumViewScope fromEvent$dd_sdk_android_release(RumScope rumScope, RumRawEvent.StartView startView, FirstPartyHostDetector firstPartyHostDetector, VitalMonitor vitalMonitor, VitalMonitor vitalMonitor2, VitalMonitor vitalMonitor3, TimeProvider timeProvider, RumEventSourceProvider rumEventSourceProvider) {
            Intrinsics.checkNotNullParameter(rumScope, "parentScope");
            Intrinsics.checkNotNullParameter(startView, "event");
            Intrinsics.checkNotNullParameter(firstPartyHostDetector, "firstPartyHostDetector");
            Intrinsics.checkNotNullParameter(vitalMonitor, "cpuVitalMonitor");
            Intrinsics.checkNotNullParameter(vitalMonitor2, "memoryVitalMonitor");
            Intrinsics.checkNotNullParameter(vitalMonitor3, "frameRateVitalMonitor");
            Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
            Intrinsics.checkNotNullParameter(rumEventSourceProvider, "rumEventSourceProvider");
            return new RumViewScope(rumScope, startView.getKey(), startView.getName(), startView.getEventTime(), startView.getAttributes(), firstPartyHostDetector, vitalMonitor, vitalMonitor2, vitalMonitor3, timeProvider, rumEventSourceProvider, (BuildSdkVersionProvider) null, (ViewUpdatePredicate) null, (RumViewType) null, 14336, (DefaultConstructorMarker) null);
        }
    }
}

package com.datadog.android.rum.internal.debug;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.internal.monitor.NoOpAdvancedRumMonitor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u0000 02\u00020\u00012\u00020\u0002:\u00010B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001a\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010&\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010'\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010(\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020$H\u0016J\u0010\u0010*\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010+\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0016\u0010,\u001a\u00020\"2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00120.H\u0016J\u0016\u0010/\u001a\u00020\"2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00120.H\u0003R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0004¢\u0006\u0002\n\u0000¨\u00061"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/debug/UiRumDebugListener;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Lcom/datadog/android/rum/internal/debug/RumDebugListener;", "()V", "advancedRumMonitor", "Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "getAdvancedRumMonitor", "()Lcom/datadog/android/rum/internal/monitor/AdvancedRumMonitor;", "advancedRumMonitor$delegate", "Lkotlin/Lazy;", "rumViewsContainer", "Landroid/widget/LinearLayout;", "getRumViewsContainer$dd_sdk_android_release", "()Landroid/widget/LinearLayout;", "setRumViewsContainer$dd_sdk_android_release", "(Landroid/widget/LinearLayout;)V", "viewsSnapshot", "", "", "createDebugTextView", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "viewName", "alpha", "", "dpToPx", "dp", "", "findContentView", "Landroid/widget/FrameLayout;", "activity", "Landroid/app/Activity;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "onReceiveRumActiveViews", "viewNames", "", "showRumViewsInfo", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: UiRumDebugListener.kt */
public final class UiRumDebugListener implements Application.ActivityLifecycleCallbacks, RumDebugListener {
    /* access modifiers changed from: private */
    public static final int ACTIVE_COLOR = Color.rgb(99, 44, 166);
    public static final String CANNOT_FIND_CONTENT_VIEW_MESSAGE = "Cannot enable RUM debugging, because cannot find root content view";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DEFAULT_ALPHA = 200;
    public static final String MISSING_RUM_MONITOR_TYPE = "Cannot enable RUM debugging, because global RUM monitor doesn't implement %s";
    private final Lazy advancedRumMonitor$delegate = LazyKt.lazy(UiRumDebugListener$advancedRumMonitor$2.INSTANCE);
    private LinearLayout rumViewsContainer;
    private final List<String> viewsSnapshot = new ArrayList();

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(bundle, "outState");
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public final LinearLayout getRumViewsContainer$dd_sdk_android_release() {
        return this.rumViewsContainer;
    }

    public final void setRumViewsContainer$dd_sdk_android_release(LinearLayout linearLayout) {
        this.rumViewsContainer = linearLayout;
    }

    private final AdvancedRumMonitor getAdvancedRumMonitor() {
        return (AdvancedRumMonitor) this.advancedRumMonitor$delegate.getValue();
    }

    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (!(getAdvancedRumMonitor() instanceof NoOpAdvancedRumMonitor)) {
            FrameLayout findContentView = findContentView(activity);
            if (findContentView == null) {
                Logger.w$default(RuntimeUtilsKt.getDevLogger(), CANNOT_FIND_CONTENT_VIEW_MESSAGE, (Throwable) null, (Map) null, 6, (Object) null);
                return;
            }
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(1);
            this.rumViewsContainer = linearLayout;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 80;
            Unit unit = Unit.INSTANCE;
            findContentView.addView(linearLayout, layoutParams);
            getAdvancedRumMonitor().setDebugListener(this);
        }
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (!(getAdvancedRumMonitor() instanceof NoOpAdvancedRumMonitor)) {
            FrameLayout findContentView = findContentView(activity);
            if (findContentView != null) {
                findContentView.removeView(this.rumViewsContainer);
            }
            this.rumViewsContainer = null;
            getAdvancedRumMonitor().setDebugListener((RumDebugListener) null);
            this.viewsSnapshot.clear();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0058, code lost:
        if (r3 != false) goto L_0x005a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceiveRumActiveViews(java.util.List<java.lang.String> r7) {
        /*
            r6 = this;
            java.lang.String r0 = "viewNames"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.util.List<java.lang.String> r0 = r6.viewsSnapshot
            monitor-enter(r0)
            java.util.List<java.lang.String> r1 = r6.viewsSnapshot     // Catch:{ all -> 0x007a }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x007a }
            if (r1 != 0) goto L_0x005a
            java.util.List<java.lang.String> r1 = r6.viewsSnapshot     // Catch:{ all -> 0x007a }
            int r1 = r1.size()     // Catch:{ all -> 0x007a }
            int r2 = r7.size()     // Catch:{ all -> 0x007a }
            if (r1 != r2) goto L_0x005a
            java.util.List<java.lang.String> r1 = r6.viewsSnapshot     // Catch:{ all -> 0x007a }
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch:{ all -> 0x007a }
            java.lang.Iterable r1 = kotlin.collections.CollectionsKt.withIndex(r1)     // Catch:{ all -> 0x007a }
            boolean r2 = r1 instanceof java.util.Collection     // Catch:{ all -> 0x007a }
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0035
            r2 = r1
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x007a }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x007a }
            if (r2 == 0) goto L_0x0035
        L_0x0033:
            r3 = r4
            goto L_0x0058
        L_0x0035:
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x007a }
        L_0x0039:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x007a }
            if (r2 == 0) goto L_0x0033
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x007a }
            kotlin.collections.IndexedValue r2 = (kotlin.collections.IndexedValue) r2     // Catch:{ all -> 0x007a }
            java.lang.Object r5 = r2.getValue()     // Catch:{ all -> 0x007a }
            int r2 = r2.getIndex()     // Catch:{ all -> 0x007a }
            java.lang.Object r2 = kotlin.collections.CollectionsKt.getOrNull(r7, r2)     // Catch:{ all -> 0x007a }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r2)     // Catch:{ all -> 0x007a }
            r2 = r2 ^ r3
            if (r2 == 0) goto L_0x0039
        L_0x0058:
            if (r3 == 0) goto L_0x0076
        L_0x005a:
            java.util.List<java.lang.String> r1 = r6.viewsSnapshot     // Catch:{ all -> 0x007a }
            r1.clear()     // Catch:{ all -> 0x007a }
            java.util.List<java.lang.String> r1 = r6.viewsSnapshot     // Catch:{ all -> 0x007a }
            r2 = r7
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x007a }
            r1.addAll(r2)     // Catch:{ all -> 0x007a }
            android.widget.LinearLayout r1 = r6.getRumViewsContainer$dd_sdk_android_release()     // Catch:{ all -> 0x007a }
            if (r1 != 0) goto L_0x006e
            goto L_0x0076
        L_0x006e:
            com.datadog.android.rum.internal.debug.UiRumDebugListener$$ExternalSyntheticLambda0 r2 = new com.datadog.android.rum.internal.debug.UiRumDebugListener$$ExternalSyntheticLambda0     // Catch:{ all -> 0x007a }
            r2.<init>(r6, r7)     // Catch:{ all -> 0x007a }
            r1.post(r2)     // Catch:{ all -> 0x007a }
        L_0x0076:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007a }
            monitor-exit(r0)
            return
        L_0x007a:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.rum.internal.debug.UiRumDebugListener.onReceiveRumActiveViews(java.util.List):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: onReceiveRumActiveViews$lambda-4$lambda-3  reason: not valid java name */
    public static final void m146onReceiveRumActiveViews$lambda4$lambda3(UiRumDebugListener uiRumDebugListener, List list) {
        Intrinsics.checkNotNullParameter(uiRumDebugListener, "this$0");
        Intrinsics.checkNotNullParameter(list, "$viewNames");
        uiRumDebugListener.showRumViewsInfo(list);
    }

    private final void showRumViewsInfo(List<String> list) {
        LinearLayout linearLayout = this.rumViewsContainer;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            if (list.isEmpty()) {
                Context context = linearLayout.getContext();
                Intrinsics.checkNotNullExpressionValue(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
                linearLayout.addView(createDebugTextView(context, "No active RUM View", 200));
                return;
            }
            for (IndexedValue indexedValue : CollectionsKt.withIndex(CollectionsKt.reversed(list))) {
                Context context2 = linearLayout.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
                linearLayout.addView(createDebugTextView(context2, (String) indexedValue.getValue(), (int) (((double) 255) * Math.pow(0.75d, (double) (indexedValue.getIndex() + 1)))));
            }
        }
    }

    private final TextView createDebugTextView(Context context, String str, int i) {
        TextView textView = new TextView(context);
        textView.setGravity(1);
        int i2 = ACTIVE_COLOR;
        textView.setBackgroundColor(Color.argb(i, (i2 >> 16) & 255, (i2 >> 8) & 255, i2 & 255));
        textView.setTextColor(-1);
        int dpToPx = dpToPx(2.0f, context);
        textView.setPadding(dpToPx, dpToPx, dpToPx, dpToPx);
        textView.setText(str);
        return textView;
    }

    private final FrameLayout findContentView(Activity activity) {
        KeyEvent.Callback callback;
        View decorView = activity.getWindow().getDecorView();
        ViewGroup viewGroup = decorView instanceof ViewGroup ? (ViewGroup) decorView : null;
        if (viewGroup == null) {
            callback = null;
        } else {
            callback = viewGroup.findViewById(16908290);
        }
        if (callback instanceof FrameLayout) {
            return (FrameLayout) callback;
        }
        return null;
    }

    private final int dpToPx(float f, Context context) {
        return (int) (((double) (f * context.getResources().getDisplayMetrics().density)) + 0.5d);
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/debug/UiRumDebugListener$Companion;", "", "()V", "ACTIVE_COLOR", "", "getACTIVE_COLOR", "()I", "CANNOT_FIND_CONTENT_VIEW_MESSAGE", "", "DEFAULT_ALPHA", "MISSING_RUM_MONITOR_TYPE", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: UiRumDebugListener.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getACTIVE_COLOR() {
            return UiRumDebugListener.ACTIVE_COLOR;
        }
    }
}

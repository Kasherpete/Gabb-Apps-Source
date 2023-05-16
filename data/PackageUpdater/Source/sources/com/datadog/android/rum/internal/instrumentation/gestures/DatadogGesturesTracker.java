package com.datadog.android.rum.internal.instrumentation.gestures;

import android.content.Context;
import android.view.Window;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.rum.tracking.InteractionPredicate;
import com.datadog.android.rum.tracking.ViewAttributesProvider;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u001f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/gestures/DatadogGesturesTracker;", "Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesTracker;", "targetAttributesProviders", "", "Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "interactionPredicate", "Lcom/datadog/android/rum/tracking/InteractionPredicate;", "([Lcom/datadog/android/rum/tracking/ViewAttributesProvider;Lcom/datadog/android/rum/tracking/InteractionPredicate;)V", "getInteractionPredicate$dd_sdk_android_release", "()Lcom/datadog/android/rum/tracking/InteractionPredicate;", "getTargetAttributesProviders$dd_sdk_android_release", "()[Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "[Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "equals", "", "other", "", "generateGestureDetector", "Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesDetectorWrapper;", "context", "Landroid/content/Context;", "window", "Landroid/view/Window;", "generateGestureDetector$dd_sdk_android_release", "hashCode", "", "startTracking", "", "stopTracking", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DatadogGesturesTracker.kt */
public final class DatadogGesturesTracker implements GesturesTracker {
    private final InteractionPredicate interactionPredicate;
    private final ViewAttributesProvider[] targetAttributesProviders;

    public DatadogGesturesTracker(ViewAttributesProvider[] viewAttributesProviderArr, InteractionPredicate interactionPredicate2) {
        Intrinsics.checkNotNullParameter(viewAttributesProviderArr, "targetAttributesProviders");
        Intrinsics.checkNotNullParameter(interactionPredicate2, "interactionPredicate");
        this.targetAttributesProviders = viewAttributesProviderArr;
        this.interactionPredicate = interactionPredicate2;
    }

    public final ViewAttributesProvider[] getTargetAttributesProviders$dd_sdk_android_release() {
        return this.targetAttributesProviders;
    }

    public final InteractionPredicate getInteractionPredicate$dd_sdk_android_release() {
        return this.interactionPredicate;
    }

    public void startTracking(Window window, Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        if (window != null) {
            Window.Callback callback = window.getCallback();
            if (callback == null) {
                callback = new NoOpWindowCallback();
            }
            window.setCallback(new WindowCallbackWrapper(window, callback, generateGestureDetector$dd_sdk_android_release(context, window), this.interactionPredicate, (Function1) null, this.targetAttributesProviders, 16, (DefaultConstructorMarker) null));
        }
    }

    public void stopTracking(Window window, Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        if (window != null) {
            Window.Callback callback = window.getCallback();
            if (callback instanceof WindowCallbackWrapper) {
                WindowCallbackWrapper windowCallbackWrapper = (WindowCallbackWrapper) callback;
                if (!(windowCallbackWrapper.getWrappedCallback() instanceof NoOpWindowCallback)) {
                    window.setCallback(windowCallbackWrapper.getWrappedCallback());
                } else {
                    window.setCallback((Window.Callback) null);
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj == null ? null : obj.getClass())) {
            return false;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type com.datadog.android.rum.internal.instrumentation.gestures.DatadogGesturesTracker");
        DatadogGesturesTracker datadogGesturesTracker = (DatadogGesturesTracker) obj;
        return Arrays.equals(this.targetAttributesProviders, datadogGesturesTracker.targetAttributesProviders) && Intrinsics.areEqual((Object) this.interactionPredicate.getClass(), (Object) datadogGesturesTracker.interactionPredicate.getClass());
    }

    public int hashCode() {
        int hashCode = 527 + Arrays.hashCode(this.targetAttributesProviders) + 17;
        return hashCode + (hashCode * 31) + this.interactionPredicate.getClass().hashCode();
    }

    public String toString() {
        return "DatadogGesturesTracker(" + ArraysKt.joinToString$default((Object[]) this.targetAttributesProviders, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + ")";
    }

    public final GesturesDetectorWrapper generateGestureDetector$dd_sdk_android_release(Context context, Window window) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(window, "window");
        return new GesturesDetectorWrapper(context, new GesturesListener(new WeakReference(window), this.targetAttributesProviders, this.interactionPredicate));
    }
}

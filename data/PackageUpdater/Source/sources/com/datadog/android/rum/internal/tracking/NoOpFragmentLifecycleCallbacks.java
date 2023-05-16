package com.datadog.android.rum.internal.tracking;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\b¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/NoOpFragmentLifecycleCallbacks;", "T", "Landroid/app/Activity;", "Lcom/datadog/android/rum/internal/tracking/FragmentLifecycleCallbacks;", "()V", "register", "", "activity", "(Landroid/app/Activity;)V", "unregister", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpFragmentLifecycleCallbacks.kt */
public final class NoOpFragmentLifecycleCallbacks<T extends Activity> implements FragmentLifecycleCallbacks<T> {
    public void register(T t) {
        Intrinsics.checkNotNullParameter(t, "activity");
    }

    public void unregister(T t) {
        Intrinsics.checkNotNullParameter(t, "activity");
    }
}

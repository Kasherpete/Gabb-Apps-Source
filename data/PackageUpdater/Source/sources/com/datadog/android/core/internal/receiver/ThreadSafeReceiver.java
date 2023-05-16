package com.datadog.android.core.internal.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/core/internal/receiver/ThreadSafeReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "isRegistered", "Ljava/util/concurrent/atomic/AtomicBoolean;", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "registerReceiver", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "filter", "Landroid/content/IntentFilter;", "unregisterReceiver", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ThreadSafeReceiver.kt */
public abstract class ThreadSafeReceiver extends BroadcastReceiver {
    private final AtomicBoolean isRegistered = new AtomicBoolean(false);

    public final AtomicBoolean isRegistered() {
        return this.isRegistered;
    }

    public final Intent registerReceiver(Context context, IntentFilter intentFilter) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(intentFilter, "filter");
        Intent registerReceiver = context.registerReceiver(this, intentFilter);
        this.isRegistered.set(true);
        return registerReceiver;
    }

    public final void unregisterReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        if (this.isRegistered.compareAndSet(true, false)) {
            context.unregisterReceiver(this);
        }
    }
}

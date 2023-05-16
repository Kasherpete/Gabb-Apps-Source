package com.datadog.android.core.internal.persistence;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\n"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/NoOpDataReader;", "Lcom/datadog/android/core/internal/persistence/DataReader;", "()V", "drop", "", "data", "Lcom/datadog/android/core/internal/persistence/Batch;", "dropAll", "lockAndReadNext", "release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpDataReader.kt */
public final class NoOpDataReader implements DataReader {
    public void drop(Batch batch) {
        Intrinsics.checkNotNullParameter(batch, MPDbAdapter.KEY_DATA);
    }

    public void dropAll() {
    }

    public Batch lockAndReadNext() {
        return null;
    }

    public void release(Batch batch) {
        Intrinsics.checkNotNullParameter(batch, MPDbAdapter.KEY_DATA);
    }
}

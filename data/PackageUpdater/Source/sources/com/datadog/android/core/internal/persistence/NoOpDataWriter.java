package com.datadog.android.core.internal.persistence;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\bJ\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/NoOpDataWriter;", "T", "", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "()V", "write", "", "element", "(Ljava/lang/Object;)V", "data", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpDataWriter.kt */
public final class NoOpDataWriter<T> implements DataWriter<T> {
    public void write(T t) {
        Intrinsics.checkNotNullParameter(t, "element");
    }

    public void write(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, MPDbAdapter.KEY_DATA);
    }
}

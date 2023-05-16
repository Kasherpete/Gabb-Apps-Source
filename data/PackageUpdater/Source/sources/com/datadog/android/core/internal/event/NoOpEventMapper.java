package com.datadog.android.core.internal.event;

import com.datadog.android.event.EventMapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\u0015\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/core/internal/event/NoOpEventMapper;", "T", "", "Lcom/datadog/android/event/EventMapper;", "()V", "equals", "", "other", "hashCode", "", "map", "event", "(Ljava/lang/Object;)Ljava/lang/Object;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpEventMapper.kt */
public final class NoOpEventMapper<T> implements EventMapper<T> {
    public int hashCode() {
        return 0;
    }

    public T map(T t) {
        Intrinsics.checkNotNullParameter(t, "event");
        return t;
    }

    public boolean equals(Object obj) {
        return obj instanceof NoOpEventMapper;
    }
}

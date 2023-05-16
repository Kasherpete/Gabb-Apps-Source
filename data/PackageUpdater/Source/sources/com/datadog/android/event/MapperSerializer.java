package com.datadog.android.event;

import com.datadog.android.core.internal.persistence.Serializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B!\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\u0010\u0007J\u0017\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/event/MapperSerializer;", "T", "", "Lcom/datadog/android/core/internal/persistence/Serializer;", "eventMapper", "Lcom/datadog/android/event/EventMapper;", "serializer", "(Lcom/datadog/android/event/EventMapper;Lcom/datadog/android/core/internal/persistence/Serializer;)V", "getEventMapper$dd_sdk_android_release", "()Lcom/datadog/android/event/EventMapper;", "serialize", "", "model", "(Ljava/lang/Object;)Ljava/lang/String;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: MapperSerializer.kt */
public final class MapperSerializer<T> implements Serializer<T> {
    private final EventMapper<T> eventMapper;
    private final Serializer<T> serializer;

    public MapperSerializer(EventMapper<T> eventMapper2, Serializer<T> serializer2) {
        Intrinsics.checkNotNullParameter(eventMapper2, "eventMapper");
        Intrinsics.checkNotNullParameter(serializer2, "serializer");
        this.eventMapper = eventMapper2;
        this.serializer = serializer2;
    }

    public final EventMapper<T> getEventMapper$dd_sdk_android_release() {
        return this.eventMapper;
    }

    public String serialize(T t) {
        Intrinsics.checkNotNullParameter(t, "model");
        T map = this.eventMapper.map(t);
        if (map == null) {
            return null;
        }
        return this.serializer.serialize(map);
    }
}

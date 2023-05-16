package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.rum.internal.domain.RumContext;
import com.datadog.android.rum.internal.domain.scope.RumViewScope;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J \u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/NoOpRumScope;", "Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "()V", "getRumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "handleEvent", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "", "isActive", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpRumScope.kt */
public final class NoOpRumScope implements RumScope {
    public RumScope handleEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        Intrinsics.checkNotNullParameter(dataWriter, "writer");
        return null;
    }

    public boolean isActive() {
        return false;
    }

    public RumContext getRumContext() {
        return new RumContext((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (RumViewScope.RumViewType) null, 127, (DefaultConstructorMarker) null);
    }
}

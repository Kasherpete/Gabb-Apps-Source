package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.rum.internal.domain.RumContext;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\ba\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J \u0010\u0004\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH&J\b\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/RumScope;", "", "getRumContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "handleEvent", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "writer", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "isActive", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumScope.kt */
public interface RumScope {
    RumContext getRumContext();

    RumScope handleEvent(RumRawEvent rumRawEvent, DataWriter<Object> dataWriter);

    boolean isActive();
}

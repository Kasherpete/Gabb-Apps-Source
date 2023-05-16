package com.datadog.android.rum.internal.ndk;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.log.model.LogEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/NoOpNdkCrashHandler;", "Lcom/datadog/android/rum/internal/ndk/NdkCrashHandler;", "()V", "handleNdkCrash", "", "logWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "Lcom/datadog/android/log/model/LogEvent;", "rumWriter", "", "prepareData", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpNdkCrashHandler.kt */
public final class NoOpNdkCrashHandler implements NdkCrashHandler {
    public void handleNdkCrash(DataWriter<LogEvent> dataWriter, DataWriter<Object> dataWriter2) {
        Intrinsics.checkNotNullParameter(dataWriter, "logWriter");
        Intrinsics.checkNotNullParameter(dataWriter2, "rumWriter");
    }

    public void prepareData() {
    }
}

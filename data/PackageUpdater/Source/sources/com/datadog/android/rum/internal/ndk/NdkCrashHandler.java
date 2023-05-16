package com.datadog.android.rum.internal.ndk;

import com.datadog.android.core.internal.persistence.DataWriter;
import com.datadog.android.log.model.LogEvent;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\ba\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/NdkCrashHandler;", "", "handleNdkCrash", "", "logWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "Lcom/datadog/android/log/model/LogEvent;", "rumWriter", "prepareData", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NdkCrashHandler.kt */
public interface NdkCrashHandler {
    void handleNdkCrash(DataWriter<LogEvent> dataWriter, DataWriter<Object> dataWriter2);

    void prepareData();
}

package com.datadog.android.core.internal.persistence;

import com.datadog.android.core.internal.data.upload.Flusher;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/PersistenceStrategy;", "T", "", "getFlusher", "Lcom/datadog/android/core/internal/data/upload/Flusher;", "getReader", "Lcom/datadog/android/core/internal/persistence/DataReader;", "getWriter", "Lcom/datadog/android/core/internal/persistence/DataWriter;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PersistenceStrategy.kt */
public interface PersistenceStrategy<T> {
    Flusher getFlusher();

    DataReader getReader();

    DataWriter<T> getWriter();
}

package com.datadog.android.core.internal.persistence;

import java.util.List;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\ba\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/DataWriter;", "T", "", "write", "", "element", "(Ljava/lang/Object;)V", "data", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DataWriter.kt */
public interface DataWriter<T> {
    void write(T t);

    void write(List<? extends T> list);
}
